package  com.cz.mts.system.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cz.mts.frame.annotation.SecurityApi;
import com.cz.mts.frame.controller.BaseController;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.MessageUtils;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.frame.util.SecUtils;
import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Attention;
import com.cz.mts.system.entity.Collect;
import com.cz.mts.system.entity.Medal;
import com.cz.mts.system.entity.Menu;
import com.cz.mts.system.entity.Password;
import com.cz.mts.system.entity.Sms;
import com.cz.mts.system.entity.UserMedal;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.IAttentionService;
import com.cz.mts.system.service.IMedalService;
import com.cz.mts.system.service.IPasswordService;
import com.cz.mts.system.service.ISmsService;
import com.cz.mts.system.service.IUserMedalService;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:23
 * @see com.cz.mts.system.web.AppUser
 */
@Controller
@RequestMapping(value="/system/appuser")
public class AppUserController  extends BaseController {
	@Resource
	private IAppUserService appUserService;
	@Resource
	private IAttentionService attentionService;
	@Resource
	private IUserMedalService userMedalService;
	@Resource
	private IPasswordService passwordService;
	@Resource
	private IMedalService medalService;
	
	private String listurl="/appuser/appuserList";
	
	@Resource
	private ISmsService smsService;
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param appUser
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,AppUser appUser) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, appUser);
		List<AppUser> list = new ArrayList<>()  ;
		returnObject.setData(returnObject.getData());
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param appUser
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,AppUser appUser) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<AppUser> datas=appUserService.findListDataByFinder(null,page,AppUser.class,appUser);
			returnObject.setQueryBean(appUser);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,AppUser appUser) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = appUserService.findDataExportExcel(null,listurl, page,AppUser.class,appUser);
		String fileName="appUser"+GlobalStatic.excelext;
		downFile(response, file, fileName,true);
		return;
	}
	
	/**
	 * 查看操作,调用APP端lookjson方法
	 */
	@RequestMapping(value = "/look")
	public String look(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception {
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/system/appuser/appuserLook";
	}

	
	/**
	 * 获取个人信息接口
	 * @param id
	 * 查看的Json格式数据,为APP端提供数据
	 * @author wj
	 */
	@RequestMapping(value = "/look/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		  String  strId=request.getParameter("id");
		  String itemId = request.getParameter("itemId");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
			 AppUser appUser = appUserService.findAppUserById(id);
			 Finder finder = Finder.getSelectFinder(Attention.class).append("where itemId = :itemId");
			 finder.setParam("itemId", id);
			 Page page = new Page();
			 Attention attention = new Attention();
			 //获取别人关注我的列表
			 List<Attention> attentions = attentionService.findListDataByFinder(finder, page, Attention.class, attention);
			 if(null != attentions && attentions.size() > 0){
				 appUser.setFansNum(attentions.size());
			 }else{
				 appUser.setFansNum(0);
			 }


			 if(StringUtils.isNotBlank(itemId)){
				 //获取我的关注列表
				 Finder fder = Finder.getSelectFinder(Attention.class).append("where userId = :userId and itemId = :itemId");
				 fder.setParam("userId", id);
				 fder.setParam("itemId", Integer.parseInt(itemId));
				 List<Attention> myAttens = attentionService.findListDataByFinder(fder, page, Attention.class, attention);
				 if(null != myAttens && myAttens.size() > 0){
					 for (Attention at : myAttens) {
						at.setIsUpdate(0);
						attentionService.update(at);
					}
				 }
			 }

			 UserMedal userMedal=new UserMedal();
			 userMedal.setUserId(id);

			 //获取我的勋章列表
			 List<UserMedal> userMedals = userMedalService.findListDataByFinder(null, page, UserMedal.class, userMedal);
			 for (UserMedal userMedal2 : userMedals) {
				 
				 if(userMedal2.getMedalId()!=null){
					 
					 Medal medal=medalService.findMedalById(userMedal2.getMedalId());
					 if(medal!=null){
						 userMedal2.setMedal(medal);
					 }
					 
				 }
				 
			 }
			 if(null != userMedals && userMedals.size() > 0){
				 appUser.setUserMedals(userMedals);
			 }

			 returnObject.setData(appUser);

		}else{
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数缺失");
		}

		return returnObject;

	}

	
	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/update/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas saveorupdatejson(Model model,AppUser appUser,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			Page page = newPage(request);
			if(null == appUser.getId()){
				String content = request.getParameter("content");
				if(StringUtils.isBlank(appUser.getPhone()) || StringUtils.isBlank(appUser.getPassword()) || StringUtils.isBlank(content)){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("参数缺失");
				}else{
					//判断手机号是否注册
					AppUser user = new AppUser();
					if(StringUtils.isNotBlank(appUser.getPhone())){
						user.setPhone(appUser.getPhone());
					}
					List<AppUser> datas = appUserService.findListDataByFinder(null,page,AppUser.class,user);
					if(null != datas && datas.size() > 0){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该用户已注册");
					}else{
						
						//判断该用户是否发送验证码了
						if(appUser.getPhone()!=null&&content!=null){
							Sms sms=new Sms();
							sms.setPhone(appUser.getPhone());
							sms.setContent(content);
							sms.setType(1);
							List<Sms> smss=smsService.findListDataByFinder(null, page, Sms.class, sms);
							if(smss.size()==0){
								returnObject.setStatus(ReturnDatas.ERROR);
								returnObject.setMessage("请发送验证码");
							}else{
								//删除该条记录
								smsService.deleteByEntity(smss.get(0));
								//判断该密码在密码表中是否存在
								Finder finder = new Finder("SELECT * FROM t_password WHERE mdBeforePass=:mdBeforePass");
								finder.setParam("mdBeforePass", appUser.getPassword());
								List<Map<String, Object>> list = passwordService.queryForList(finder);
								if(list.isEmpty()){
									//向password表中插入数据
									Password password = new Password();
									password.setMdBeforePass(appUser.getPassword());
									password.setMdAfterPass(SecUtils.encoderByMd5With32Bit(appUser.getPassword()));
									passwordService.save(password);
								}
								
								appUser.setPassword(SecUtils.encoderByMd5With32Bit(appUser.getPassword()));
								appUser.setCreateTime(new Date());
								appUser.setIsBlack(0);
								Object id = appUserService.saveorupdate(appUser);
								
								returnObject.setData(appUserService.findById(id, AppUser.class));
							}
						}
					}
				}
			}else{
				if(StringUtils.isNotBlank(appUser.getPassword())){
					
					//判断该密码在密码表中是否存在
					Finder finder = new Finder("SELECT * FROM t_password WHERE mdBeforePass=:mdBeforePass");
					finder.setParam("mdBeforePass", appUser.getPassword());
					List<Map<String, Object>> list = passwordService.queryForList(finder);
					if(list.isEmpty()){
						//向password表中插入数据
						Password password = new Password();
						password.setMdBeforePass(appUser.getPassword());
						password.setMdAfterPass(SecUtils.encoderByMd5With32Bit(appUser.getPassword()));
						passwordService.save(password);
					}
					appUser.setPassword(SecUtils.encoderByMd5With32Bit(appUser.getPassword()));
				}
				appUserService.update(appUser,true);
				returnObject.setData(appUserService.findById(appUser.getId(), AppUser.class));
			}
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	
	/**
	 * 进入修改页面,APP端可以调用 lookjson 获取json格式数据
	 */
	@RequestMapping(value = "/update/pre")
	public String updatepre(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/appuser/appuserCru";
	}
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value="/delete")
	@SecurityApi
	public @ResponseBody ReturnDatas delete(HttpServletRequest request) throws Exception {

			// 执行删除
		try {
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
				appUserService.deleteById(id,AppUser.class);
				return new ReturnDatas(ReturnDatas.SUCCESS,
						MessageUtils.DELETE_SUCCESS);
			} else {
				return new ReturnDatas(ReturnDatas.WARNING,
						MessageUtils.DELETE_WARNING);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new ReturnDatas(ReturnDatas.WARNING, MessageUtils.DELETE_WARNING);
	}
	
	/**
	 * 删除多条记录
	 * 
	 */
	@RequestMapping("/delete/more")
	@SecurityApi
	public @ResponseBody
	ReturnDatas deleteMore(HttpServletRequest request, Model model) {
		String records = request.getParameter("records");
		if(StringUtils.isBlank(records)){
			 return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		String[] rs = records.split(",");
		if (rs == null || rs.length < 1) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_NULL_FAIL);
		}
		try {
			List<String> ids = Arrays.asList(rs);
			appUserService.deleteByIds(ids,AppUser.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}
	
	/**
	 * 忘记密码接口
	 * @author wj
	 */
	@RequestMapping(value = "/forget/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas forgetjson(Model model,HttpServletRequest request,HttpServletResponse response,AppUser appUser) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String content = request.getParameter("content");
		if(StringUtils.isBlank(content) || StringUtils.isBlank(appUser.getPhone()) || StringUtils.isBlank(appUser.getPassword())){
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数缺失");
		}else{
			//判断该用户是否存在
			AppUser appUserRecord = new AppUser();
			appUserRecord.setPhone(appUser.getPhone());
			AppUser user = appUserService.queryForObject(appUserRecord);
			if(null != user){
				user.setPassword(SecUtils.encoderByMd5With32Bit(appUser.getPassword()));
				//判断该密码在密码表中是否存在
				Finder finder = new Finder("SELECT * FROM t_password WHERE mdBeforePass=:mdBeforePass");
				finder.setParam("mdBeforePass", appUser.getPassword());
				List<Map<String, Object>> list = passwordService.queryForList(finder);
				if(list.isEmpty()){
					//向password表中插入数据
					Password password = new Password();
					password.setMdBeforePass(appUser.getPassword());
					password.setMdAfterPass(SecUtils.encoderByMd5With32Bit(appUser.getPassword()));
					passwordService.save(password);
				}
				
				appUserService.update(user);
				
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("该用户不存在");
			}
		}
		return returnObject;
	}
	
	
	/**
	 * 登录接口
	 * 
	 * @param request
	 * @param model
	 * @param appUser
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas loginjson(HttpServletRequest request, Model model,AppUser appUser) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		
		if(appUser.getPhone()!=null&&appUser.getPassword()!=null){
			appUser.setPassword(SecUtils.encoderByMd5With32Bit(appUser.getPassword()));
			List<AppUser> datas=appUserService.findListDataByFinder(null,page,AppUser.class,appUser);
			if(datas!=null&&datas.size()>0){
				returnObject.setData(datas.get(0));
			}else {
				returnObject.setStatus(ReturnDatas.WARNING);
				returnObject.setMessage("帐号密码错误");
			}
		}else {
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数缺失");
		}
		
		return returnObject;
	}
	
	
	/**
	 * 第三方登录接口json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param appUser
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/loginS/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas loginSjson(HttpServletRequest request, Model model,AppUser appUser,String content) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		
		if(appUser.getQqNum()!=null||appUser.getWxNum()!=null||appUser.getSinaNum()!=null){
			
			AppUser appUser2=new AppUser();
			appUser2.setQqNum(appUser.getQqNum());
			appUser2.setSinaNum(appUser.getSinaNum());
			appUser2.setWxNum(appUser.getWxNum());
			
			List<AppUser> datas=appUserService.findListDataByFinder(null,page,AppUser.class,appUser2);
			if(datas.size()>0){
				returnObject.setData(datas.get(0));
			}else{
				//没有找到的话就是新增接口
				returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
				try {
					appUser.setIsBlack(0);
					Object appuser=(Object) appUserService.saveorupdate(appUser);
					returnObject.setData(appUserService.findById(appuser, AppUser.class));
				} catch (Exception e) {
					String errorMessage = e.getLocalizedMessage();
					logger.error(errorMessage);
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage(MessageUtils.UPDATE_ERROR);
				}
				return returnObject;
			}
		}else {
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数缺失");
		}
		
		return returnObject;
	}
	
	/**
	 * 修改原绑定手机号
	 * @param request
	 * @param model
	 * @param appUser
	 * @param content
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/modifytel/json")
	@SecurityApi
	public @ResponseBody 
	ReturnDatas modifyteljson(HttpServletRequest request, Model model,AppUser appUser) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		String modifyTelContent = request.getParameter("content");
		// ==执行分页查询
		if(StringUtils.isBlank(modifyTelContent) || StringUtils.isBlank(appUser.getPhone())){
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数缺失");
		}else{
			//查找该用户是否存在
			List<AppUser> datas=appUserService.findListDataByFinder(null,page,AppUser.class,appUser);
			if(null != datas && datas.size() > 0){
				//判断验证码
				Sms sms=new Sms();
				sms.setPhone(appUser.getPhone());
				sms.setContent(modifyTelContent);
				sms.setType(2);
				List<Sms> smss=smsService.findListDataByFinder(null, page, Sms.class, sms);
				if(null != smss && smss.size() > 0 ){
					//删除该条记录
					smsService.deleteByEntity(smss.get(0));
					appUserService.saveorupdate(appUser);
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("该验证码不存在");
				}
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("该用户不存在");
			}
			
		}
		return returnObject;
	}
	
	/**
	 * 修改新绑定手机号
	 * @param request
	 * @param model
	 * @param appUser
	 * @param content
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/modifynewtel/json")
	@SecurityApi
	public @ResponseBody 
	ReturnDatas modifynewteljson(HttpServletRequest request, Model model,AppUser appUser) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		String contents = request.getParameter("content");
		if(StringUtils.isBlank(contents) || StringUtils.isBlank(appUser.getPhone()) || null == appUser.getId()){
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数缺失");
		}else{
			//查找该用户是否存在
			List<AppUser> datas = appUserService.findListDataByFinder(null,page,AppUser.class,appUser);
			if(null == datas || 0 == datas.size()){
				//判断验证码
				Sms sms=new Sms();
				sms.setPhone(appUser.getPhone());
				sms.setContent(contents);
				sms.setType(3);
				List<Sms> smss=smsService.findListDataByFinder(null, page, Sms.class, sms);
				if(null != smss && smss.size() > 0 ){
					//删除该条记录
					smsService.deleteByEntity(smss.get(0));
					//根据id查询信息
					AppUser appUserNewPhone = appUserService.findAppUserById(appUser.getId());
					if(null != appUserNewPhone){
						appUserNewPhone.setPhone(appUser.getPhone());
						appUserService.update(appUserNewPhone);
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该用户不存在");
					}
					
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("暂未收到验证码");
				}
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("该手机号已被绑定");
			}
			
		}
		return returnObject;
	}
	
	
	/**
	 * 修改密码接口
	 * @param request
	 * @param model
	 * @param appUser
	 * @param content
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/changepwd/json")
	@SecurityApi
	public @ResponseBody 
	ReturnDatas changepwdjson(HttpServletRequest request, Model model,AppUser appUser) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		if(StringUtils.isBlank(appUser.getPassword()) || StringUtils.isBlank(appUser.getNewPwd()) || null == appUser.getId()){
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数缺失");
		}else{
			
			//判断该密码在密码表中是否存在
			Finder finder = new Finder("SELECT * FROM t_password WHERE mdBeforePass=:mdBeforePass");
			finder.setParam("mdBeforePass", appUser.getNewPwd());
			List<Map<String, Object>> list = passwordService.queryForList(finder);
			if(list.isEmpty()){
				//向password表中插入数据
				Password password = new Password();
				password.setMdBeforePass(appUser.getNewPwd());
				password.setMdAfterPass(SecUtils.encoderByMd5With32Bit(appUser.getNewPwd()));
				passwordService.save(password);
			}
			
			
			AppUser appRecord = appUserService.findAppUserById(appUser.getId());
			if(null != appRecord){
				if(StringUtils.isNotBlank(appRecord.getPassword())){
					//比较查询出来的密码和传过来的密码是否相等
					if(appRecord.getPassword().equals(SecUtils.encoderByMd5With32Bit(appUser.getPassword()))){
						//判断新密码和旧密码是否相等
						if(!appRecord.getPassword().equals(SecUtils.encoderByMd5With32Bit(appUser.getNewPwd()))){
							appRecord.setPassword(SecUtils.encoderByMd5With32Bit(appUser.getNewPwd()));
							appUserService.update(appRecord);
						}else{
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("新旧密码不能相同");
						}
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("原密码输入错误");
					}
				}
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("该用户不存在");
			}
		}
		return returnObject;
	}
	
	/**
	 * 绑定手机号接口
	 * @author wj
	 * @param request
	 * @param model
	 * @param appUser
	 * @param content
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/bindphone/json")
	@SecurityApi
	public @ResponseBody 
	ReturnDatas bindPhonejson(HttpServletRequest request, Model model,AppUser appUser,String content) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		if(StringUtils.isBlank(appUser.getPhone()) || StringUtils.isBlank(content) || null == appUser.getId()){
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数缺失");
		}else{
			Page page = new Page();
			AppUser appRecord = appUserService.findAppUserById(appUser.getId());
			if(null != appRecord){
				//判断验证码
				Sms sms=new Sms();
				sms.setPhone(appUser.getPhone());
				sms.setContent(content);
				sms.setType(5);
				List<Sms> smss=smsService.findListDataByFinder(null, page, Sms.class, sms);
				if(null != smss && smss.size() > 0 ){
					//删除该条记录
					smsService.deleteByEntity(smss.get(0));
					//更新appUser表中的记录
					appRecord.setPhone(appUser.getPhone());
					appUserService.update(appRecord);
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("请再次获取验证码");
				}
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("该用户不存在");
			}
		}
		return returnObject;
	}
	
	
	/**
	 * 余额支付接口
	 * 
	 * @param request
	 * @param model
	 * @param appUser
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/pay/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas payjson(HttpServletRequest request, Model model,Integer userId,Integer type,Integer itemId,String code) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		
		
		if(userId==null||type==null){
			returnObject.setMessage("参数缺失");
			returnObject.setStatus(ReturnDatas.ERROR);
			return returnObject;
		}
		try {
			//支付 ，并且返回状态
			Integer result=appUserService.pay(userId, type, itemId,code);
			if(result==1){
				returnObject.setMessage("支付成功");
				returnObject.setStatus(ReturnDatas.SUCCESS);
			}else if (result==2) {
				returnObject.setMessage("参数缺失");
				returnObject.setStatus(ReturnDatas.ERROR);
			}else if (result==3) {
				returnObject.setMessage("用户不存在");
				returnObject.setStatus(ReturnDatas.ERROR);
			}else if (result==4) {
				returnObject.setMessage("目标信息不正确");
				returnObject.setStatus(ReturnDatas.ERROR);
			}else if (result==5) {
				returnObject.setMessage("用户余额不足");
				returnObject.setStatus(ReturnDatas.ERROR);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return returnObject;
	}
	
	/**
	 * 个人统计接口
	 * @author wj
	 * @param request
	 * @param model
	 * @param userId
	 * @param type
	 * @param itemId
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/statics/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas staticsjson(HttpServletRequest request, Model model,AppUser appUser) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		if(null == appUser.getId()){
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数缺失");
		}else{
			Finder finder = new Finder("SELECT f.*,COUNT(uc.id) as totalUserCard FROM(SELECT e.*,COUNT(um.id) as totalMedal FROM(SELECT d.*,COUNT(`at`.id) AS totalAttention FROM (SELECT c.*,COUNT(co.id) as totalCollect FROM(SELECT b.*,COUNT(ca.id) as totalCard FROM (SELECT a.*,COUNT(pp.id) AS totalPoster FROM(SELECT au.balance ,COUNT(mp.id) as totalMedia,au.id FROM t_app_user au LEFT JOIN t_media_package mp ON mp.userId=au.id WHERE au.id=:id) a LEFT JOIN t_poster_package pp ON pp.userId = a.id )b LEFT JOIN t_card ca ON ca.userId=b.id)c LEFT JOIN t_collect co ON co.userId=c.id)d LEFT JOIN t_attention at ON `at`.userId=d.id)e LEFT JOIN t_user_medal um ON um.userId=e.id)f LEFT JOIN t_user_card uc ON uc.userId=f.id AND uc.`status` != 0;");
			finder.setParam("id", appUser.getId());
			List datas = appUserService.queryForList(finder);
			returnObject.setData(datas);
		}
		return returnObject;
	}
	
	
	/**
	 * 我的发布数量接口
	 * @author wj
	 * @param request
	 * @param model
	 * @param collect
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/publish/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas publishjson(HttpServletRequest request, Model model,AppUser appUser) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		returnObject = appUserService.getStatics(appUser, page);
		return returnObject;
	}
	
	
	/**
	 * 赋予勋章
	 * @author wj
	 * @param request
	 * @param model
	 * @param userMedal
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/award/json")
	public @ResponseBody 
	ReturnDatas awardMedalJson(HttpServletRequest request,Model model,UserMedal userMedal) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String str_medalIds=request.getParameter("medalIds");
		String userId = request.getParameter("userId");
		if(StringUtils.isBlank(str_medalIds)){
			userMedal.setMedalId(null);
		}else{
			String[] medalIds = str_medalIds.split(",");
			if(medalIds != null && medalIds.length > 0 ){
				for(String s:medalIds){
					if(StringUtils.isBlank(s)){
						continue;
					}else{
						userMedal.setMedalId(Integer.parseInt(s));
						if(StringUtils.isNotBlank(userId)){
							userMedal.setUserId(Integer.parseInt(userId));
							//判断该用户是否申请过该勋章
							Finder finder = new Finder("SELECT * FROM t_user_medal WHERE userId=:userId AND medalId=:medalId");
							finder.setParam("userId", Integer.parseInt(userId));
							finder.setParam("medalId", Integer.parseInt(s));
							List list = userMedalService.queryForList(finder);
							if(list.isEmpty()){
								userMedal.setCreateTime(new Date());
								userMedalService.save(userMedal);
							}
						}
					}
				}
			}
		}
		
		return returnObject;
	}
	
	/**
	 * 查询勋章赋予详情
	 * @author wj
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/lookmedal/json")
	public @ResponseBody 
	ReturnDatas lookmedalJson(HttpServletRequest request,Model model) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			AppUser appUser = appUserService.findUserAndMedal(id);
			returnObject.setData(appUser);
		}else{
			returnObject.setStatus(ReturnDatas.ERROR);
		}
		
		return returnObject;
	}
	

}
