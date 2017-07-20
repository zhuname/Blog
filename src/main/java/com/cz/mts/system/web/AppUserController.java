package  com.cz.mts.system.web;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
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
import com.cz.mts.system.entity.ApplyMedal;
import com.cz.mts.system.entity.Attention;
import com.cz.mts.system.entity.Collect;
import com.cz.mts.system.entity.Medal;
import com.cz.mts.system.entity.Menu;
import com.cz.mts.system.entity.MoneyDetail;
import com.cz.mts.system.entity.Password;
import com.cz.mts.system.entity.PosterPackage;
import com.cz.mts.system.entity.Sms;
import com.cz.mts.system.entity.UserMedal;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.IApplyMedalService;
import com.cz.mts.system.service.IAttentionService;
import com.cz.mts.system.service.IMedalService;
import com.cz.mts.system.service.IMoneyDetailService;
import com.cz.mts.system.service.IPasswordService;
import com.cz.mts.system.service.ISmsService;
import com.cz.mts.system.service.IUserMedalService;
import com.cz.mts.system.service.NotificationService;


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
	@Resource
	private IApplyMedalService applyMedalService;
	@Resource
	private NotificationService notificationService;
	@Resource
	private IMoneyDetailService moneyDetailService;
	
	
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
		Finder finder = Finder.getSelectFinder(AppUser.class).append(" where 1=1 ");
		if(StringUtils.isNotBlank(appUser.getStartTime())){
			finder.append(" and createTime > :startTime ");
			finder.setParam("startTime", appUser.getStartTime());
		}
		
		if(StringUtils.isNotBlank(appUser.getEndTime())){
			finder.append(" and createTime < :endTime ");
			finder.setParam("endTime", appUser.getEndTime());
		}
		
		if(StringUtils.isNotBlank(appUser.getName())){
			finder.append(" and name like '%"+appUser.getName()+"%'");
		}
		if(StringUtils.isNotBlank(appUser.getPhone())){
			finder.append(" and phone like '%"+appUser.getPhone()+"%'");
		}
		if(StringUtils.isNotBlank(appUser.getCityName())){
			finder.append(" and cityName like '%"+appUser.getCityName()+"%'");
		}
		if(null != appUser.getIsBlack()){
			finder.append(" and isBlack = :isBlack");
			finder.setParam("isBlack", appUser.getIsBlack());
		}
		if(StringUtils.isNotBlank(appUser.getSex())){
			finder.append(" and sex = :sex");
			finder.setParam("sex", appUser.getSex());
		}
		
		Map<String, Object> hashMap = new HashMap<String, Object>();
		// ==执行分页查询
		List<AppUser> datas=appUserService.findListDataByFinder(finder,page,AppUser.class,null);
		if(datas != null && datas.size() > 0){
			for (AppUser au : datas) {
				if(StringUtils.isBlank(au.getHeader())){
					au.setHeader("http://106.15.60.65:22222/images/mts/share/header.png");
				}
			}
		}
		Page newPage = new Page();
		newPage.setPageSize(100000);
		Double sumMoney = 0.0;
		List<AppUser> appUsers = appUserService.findListDataByFinder(finder,newPage,AppUser.class,null);
		if(null != appUsers && appUsers.size() > 0){
			hashMap.put("sumPerson", appUsers.size());
			for (AppUser au : appUsers) {
				sumMoney += au.getBalance();
			}
			
		}else{
			hashMap.put("sumPerson", 0);
		}
		hashMap.put("sumMoney", new BigDecimal(sumMoney).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		returnObject.setMap(hashMap);
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
			 if(null != appUser){
				 
				 Finder finder = Finder.getSelectFinder(Attention.class).append("where itemId = :itemId");
				 finder.setParam("itemId", id);
				 Page page = new Page();
				 //获取别人关注我的列表
				 List<Attention> attentions = attentionService.findListDataByFinder(finder, page, Attention.class, null);
				 if(null != attentions && attentions.size() > 0){
					 appUser.setFansNum(attentions.size());
				 }else{
					 appUser.setFansNum(0);
				 }

				 if(StringUtils.isNotBlank(itemId)){
					 //获取我的关注列表
					 Finder fder = Finder.getSelectFinder(Attention.class).append("where userId = :userId and itemId = :itemId");
					 fder.setParam("userId", Integer.parseInt(itemId));
					 fder.setParam("itemId", id);
					 List<Attention> myAttens = attentionService.findListDataByFinder(fder, page, Attention.class, null);
					 if(null != myAttens && myAttens.size() > 0){
						 for (Attention at : myAttens) {
							at.setIsUpdate(0);
							attentionService.update(at,true);
						}
					 }
				 }

				 UserMedal userMedal=new UserMedal();
				 userMedal.setUserId(id);
				 userMedal.setIsEndStatus(0);

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
				 returnObject.setMessage("该用户不存在");
			 }
			 
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
								returnObject.setMessage("验证码输入错误，请重新发送验证码");
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
								if(StringUtils.isNotBlank(appUser.getName())){
									appUser.setName(appUser.getName());
								}else{
									appUser.setName(appUser.getPhone());
								}
								appUser.setPassword(SecUtils.encoderByMd5With32Bit(appUser.getPassword()));
								appUser.setCreateTime(new Date());
								appUser.setIsBlack(0);
								appUser.setCurrentLqNum(1);
								appUser.setCurrentShareNum(1);
								appUser.setLqNum(1);
								appUser.setShareNum(1);
								appUser.setIsUpdate(0);
								appUser.setFrozeBanlance(0.0);
								appUser.setLqNum(1);
								appUser.setIsBlack(0);
								appUser.setIsCloseFee(0);
								appUser.setBalance(0.0);
								appUser.setIsPush(1);
								Object id = appUserService.saveorupdate(appUser);
								
								returnObject.setData(appUserService.findById(id, AppUser.class));
							}
						}
					}
				}
			}else{
				AppUser aUser = appUserService.findAppUserById(appUser.getId());
				if(aUser != null){
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
					
					//判断该用户是否绑定qq
					if(StringUtils.isNotBlank(appUser.getQqNum())){
						//查询该账号是否被其他用户绑定过
						AppUser auQq = new AppUser();
						auQq.setQqNum(appUser.getQqNum());
						List<AppUser> appUsers = appUserService.findListDataByFinder(null, page, AppUser.class, auQq);
						if(null != appUsers && appUsers.size() > 0){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("该qq已被其他用户绑定");
							return returnObject;
						}
						
					}
					
					//判断该用户是否绑定微信
					if(StringUtils.isNotBlank(appUser.getWxNum())){
						//查询该账号是否被其他用户绑定过
						AppUser auWx = new AppUser();
						auWx.setWxNum(appUser.getWxNum());
						List<AppUser> appUsers = appUserService.findListDataByFinder(null, page, AppUser.class, auWx);
						if(null != appUsers && appUsers.size() > 0){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage("该微信已被其他用户绑定");
							return returnObject;
						}
					}
					
					appUserService.update(appUser,true);
					returnObject.setData(appUserService.findById(appUser.getId(), AppUser.class));
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("该用户不存在");
				}
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
		AppUser appUser = (AppUser) returnObject.getData();
		if(null != appUser){
			if(StringUtils.isNotBlank(appUser.getPassword())){
				//查询是否存在该密码
				Finder finder = Finder.getSelectFinder(Password.class).append(" where mdAfterPass = :mdAfterPass");
				finder.setParam("mdAfterPass", appUser.getPassword());
				Password password = appUserService.queryForObject(finder,Password.class);
				if(null != password){
					//将appUser表的password重置为加密前的密码
					appUser.setPassword(password.getMdBeforePass());
				}else{
					appUser.setPassword("");
				}
			}
		}
		returnObject.setData(appUser);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/appuser/appuserCru";
	}
	
	/**
	 * 拉黑操作
	 */
	@RequestMapping(value="/isblack")
	public @ResponseBody ReturnDatas isblack(HttpServletRequest request) throws Exception {
		// 执行删除
		try {
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
				AppUser appUser=appUserService.findAppUserById(id);
				
				if(appUser.getIsBlack()==0){
					appUser.setIsBlack(1);
				}else if(appUser.getIsBlack()==1){
					appUser.setIsBlack(0);
				}else {
					appUser.setIsBlack(1);
				}
				
				appUserService.update(appUser,true);
				
			} else {
				return new ReturnDatas(ReturnDatas.ERROR,"参数缺失");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS, MessageUtils.UPDATE_SUCCESS);
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
				return new ReturnDatas(ReturnDatas.ERROR,"参数缺失");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS, MessageUtils.UPDATE_SUCCESS);
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
				
				Sms sms=new Sms();
				Page page = new Page();
				sms.setPhone(appUser.getPhone());
				sms.setContent(content);
				sms.setType(4);
				List<Sms> smss=smsService.findListDataByFinder(null, page, Sms.class, sms);
				if(null != smss && smss.size() > 0 ){
					//删除该条记录
					smsService.deleteByEntity(smss.get(0));
					
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
					
					appUserService.update(user,true);
					
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
		
		if(StringUtils.isNotBlank(appUser.getPhone()) && StringUtils.isNotBlank(appUser.getPassword())){
			//判断手机号是否注册
			Finder finder = Finder.getSelectFinder(AppUser.class).append("where phone=:phone");
			finder.setParam("phone", appUser.getPhone());
			List appUsers = appUserService.queryForList(finder);
			if(null != appUsers && appUsers.size() > 0){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("该手机号已经注册");
			}else{
				appUser.setPassword(SecUtils.encoderByMd5With32Bit(appUser.getPassword()));
				appUser.setSign(null);
				List<AppUser> datas=appUserService.queryForListByEntity(appUser, page) ;  //findListDataByFinder(null,page,AppUser.class,appUser);
				if(datas!=null&&datas.size()>0){
					AppUser user = datas.get(0) ;
					if(user.getIsBlack() == 1){
						returnObject.setStatus(ReturnDatas.WARNING);
						returnObject.setMessage("黑名单成员！");
					}else {
						returnObject.setData(datas.get(0));
					}
				}else {
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("密码输入错误");
				}
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
		
		if(appUser.getQqNum()!=null||appUser.getWxNum()!=null){
			
			AppUser appUser2=new AppUser();
			appUser2.setQqNum(appUser.getQqNum());
			appUser2.setWxNum(appUser.getWxNum());
			
			List<AppUser> datas=appUserService.findListDataByFinder(null,page,AppUser.class,appUser2);
			if(datas.size()>0){
				returnObject.setData(datas.get(0));
			}else{
				//没有找到的话就是新增接口
				returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
				try {
					appUser.setCreateTime(new Date());
					appUser.setIsBlack(0);
					appUser.setCurrentLqNum(1);
					appUser.setCurrentShareNum(1);
					appUser.setLqNum(1);
					appUser.setShareNum(1);
					appUser.setIsUpdate(0);
					appUser.setFrozeBanlance(0.0);
					appUser.setLqNum(1);
					appUser.setIsBlack(0);
					appUser.setIsCloseFee(0);
					appUser.setBalance(0.0);
					appUser.setIsPush(1);
					
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
					appUserService.update(appUser,true);
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
			
			AppUser user = new AppUser();
			if(StringUtils.isNotBlank(appUser.getPhone())){
				user.setPhone(appUser.getPhone());
			}
			List<AppUser> datas = appUserService.findListDataByFinder(null,page,AppUser.class,user);
			if(null != datas && datas.size() > 0){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("该手机号已被绑定");
			}else{
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
						appUserService.update(appUserNewPhone,true);
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该用户不存在");
					}
					
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("暂未收到验证码");
				}
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
							appUserService.update(appRecord,true);
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
			
			//先判断手机号是否已经存在
			AppUser user = new AppUser();
			if(StringUtils.isNotBlank(appUser.getPhone())){
				user.setPhone(appUser.getPhone());
			}
			List<AppUser> datas = appUserService.findListDataByFinder(null,page,AppUser.class,user);
			if(null != datas && datas.size() > 0){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("该用户已注册");
			}else{
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
							
							appRecord.setPassword(SecUtils.encoderByMd5With32Bit(appUser.getPassword()));
						}
						appUserService.update(appRecord,true);
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("请再次获取验证码");
					}
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("该用户不存在");
				}
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
	ReturnDatas payjson(HttpServletRequest request, Model model,Integer userId,Integer type,Integer itemId,String code,String osType) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		if(userId==null||type==null){
			returnObject.setMessage("参数缺失");
			returnObject.setStatus(ReturnDatas.ERROR);
			return returnObject;
		}
		try {
			//支付 ，并且返回状态
			Integer result=appUserService.pay(userId, type, itemId,code,osType);
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
			}else if (result==6) {
				returnObject.setMessage("支付金额需大于0");
				returnObject.setStatus(ReturnDatas.ERROR);
			}
		} catch (Exception e) {
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
			Finder finder = new Finder("SELECT k.*,COUNT(ja.id) AS totalJoin FROM(SELECT j.*,COUNT(ap.id) AS totalAppoint FROM(SELECT h.*, COUNT(ci.id) AS totalCircle"
						+" FROM ( SELECT g.*, COUNT(ac.id) AS totalActivity FROM (SELECT f.*, COUNT(uc.id) AS totalUserCard FROM ("
						+" SELECT e.*, COUNT(um.id) AS totalMedal FROM ( SELECT d.*, COUNT(`at`.id) AS totalAttention FROM"
						+"  ( SELECT c.*, COUNT(co.id) AS totalCollect FROM ( SELECT b.*, COUNT(ca.id) AS totalCard FROM"
						+" (SELECT a.*, COUNT(pp.id) AS totalPoster FROM ( SELECT au.balance,COUNT(mp.id) AS totalMedia,"
						+" au.id FROM t_app_user au LEFT JOIN t_media_package mp ON mp.userId = au.id AND mp. STATUS != 0"
						+" AND mp.isDel != 1 WHERE au.id = :id ) a LEFT JOIN t_poster_package pp ON pp.userId = a.id"
						+" AND pp. STATUS != 0 AND pp.isDel != 1 ) b LEFT JOIN t_card ca ON ca.userId = b.id"
						+" AND ca.isDel != 1 ) c LEFT JOIN t_collect co ON co.userId = c.id ) d"
						+" LEFT JOIN t_attention `at` ON `at`.userId = d.id ) e LEFT JOIN t_user_medal um ON um.userId = e.id"
						+" ) f LEFT JOIN t_user_card uc ON uc.userId = f.id AND uc.`status` != 0 ) g"
						+" LEFT JOIN t_activity ac ON ac.userId = g.id AND ac.isDel != 1) h LEFT JOIN t_circle ci ON ci.userId = h.id"
						+" )j LEFT JOIN t_appoint ap ON ap.userId = j.id AND ap.`status` !=0)k LEFT JOIN t_join_activity ja ON ja.userId = k.id");
			finder.setParam("id", appUser.getId());
			List datas = appUserService.queryForList(finder);
			Object object = new Object();
			if(null != datas && datas.size() > 0){
				object = datas.get(0);
			}
			returnObject.setData(object);
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
			//同时清除关于该用户的勋章记录
			//清除t_apply_medal表中关于该用户的记录
//			Finder finder2 = new Finder("DELETE FROM t_apply_medal WHERE userId=:userId and STATUS=2");
//			finder2.setParam("userId", Integer.parseInt(userId));
//			applyMedalService.update(finder2);
//			
//			Finder finder = new Finder("DELETE FROM t_user_medal WHERE userId=:userId");
//			finder.setParam("userId", Integer.parseInt(userId));
//			userMedalService.update(finder);
			
		}else{
			String[] medalIds = str_medalIds.split(",");
			if(medalIds != null && medalIds.length > 0 ){
				
				for(String s:medalIds){
					if(StringUtils.isBlank(s)){
						continue;
					}else{
						
						//判断是否存在该用户的该勋章记录
						Finder finder = new Finder("SELECT * FROM t_user_medal WHERE userId=:userId and medalId=:medalId");
						finder.setParam("userId", Integer.parseInt(userId));
						finder.setParam("medalId", Integer.parseInt(s));
						List<UserMedal> userMedals = userMedalService.queryForList(finder, UserMedal.class);
						if(null != userMedals && userMedals.size() > 0){
							returnObject.setMessage("该勋章已经是您的勋章了，请勿重复授予！");
							returnObject.setStatus(ReturnDatas.ERROR);
							return returnObject;
						}else{
							Medal medal = medalService.findMedalById(Integer.parseInt(s));
							userMedal.setMedalId(Integer.parseInt(s));
							if(StringUtils.isNotBlank(userId)){
								userMedal.setUserId(Integer.parseInt(userId));
								userMedal.setCreateTime(new Date());
								userMedal.setIsEndStatus(0);
								userMedal.setEndMedalTime(userMedal.getEndMedalTime());
								userMedalService.save(userMedal);
							
								//向t_apply_medal表中插入数据
								ApplyMedal applyMedal = new ApplyMedal();
								applyMedal.setUserId(Integer.parseInt(userId));
								applyMedal.setMedalId(Integer.parseInt(s));
								applyMedal.setStatus(2);
								applyMedal.setOperTime(new Date());
								applyMedal.setType(medal.getType());
								applyMedal.setIntroduction("后台赋予");
								applyMedal.setEndMedalTime(userMedal.getEndMedalTime());
								applyMedal.setIsEndStatus(0);
								applyMedalService.save(applyMedal);
								
								AppUser appUser = appUserService.findAppUserById(Integer.parseInt(userId));
								if(null != appUser && 1 == appUser.getIsPush()){
									//给该用户发推送
									notificationService.notify(7, Integer.parseInt(s), Integer.parseInt(userId), medal.getName());
								}
								returnObject.setMessage("勋章授予成功！");
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
	
	
	/**
	 * 获取当前服务器时间
	 * @author wj
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getTime/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas getTimeJson(HttpServletRequest request,Model model) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        res = simpleDateFormat.format(date);
		returnObject.setData(res);
		return returnObject;
	}
	
	
	/**
	 * 后台修改信息
	 * 
	 * 
	 */
	@RequestMapping("/admin/update")
	public @ResponseBody
	ReturnDatas saveorupdate(Model model,AppUser appUser,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			Page page = newPage(request);
			if(null == appUser.getId()){
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
					appUser.setCurrentLqNum(1);
					appUser.setCurrentShareNum(1);
					appUser.setLqNum(1);
					appUser.setShareNum(1);
					appUser.setIsUpdate(0);
					appUser.setFrozeBanlance(0.0);
					appUser.setLqNum(1);
					appUser.setIsBlack(0);
					appUser.setIsCloseFee(0);
					appUser.setBalance(0.0);
					appUser.setIsPush(1);
					Object id = appUserService.saveorupdate(appUser);
					
					returnObject.setData(appUserService.findById(id, AppUser.class));
				}
			}else{
				AppUser aUser = appUserService.findAppUserById(appUser.getId());
				if(aUser != null){
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
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("该用户不存在");
				}
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
	 * 关闭卡券手续费操作
	 * @author wj
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/isCloseFee")
	public @ResponseBody ReturnDatas isCloseFee(HttpServletRequest request) throws Exception {
		// 执行删除
		try {
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
				AppUser appUser=appUserService.findAppUserById(id);
				
				if(appUser.getIsCloseFee()==0){
					appUser.setIsCloseFee(1);
				}else if(appUser.getIsCloseFee()==1){
					appUser.setIsCloseFee(0);
				}else {
					appUser.setIsCloseFee(1);
				}
				appUserService.update(appUser,true);
			} else {
				return new ReturnDatas(ReturnDatas.ERROR,"参数缺失");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS, MessageUtils.UPDATE_SUCCESS);
	}
	
	
	/**
	 * 首页平台统计（总用户、红包总金额、已领红包金额）
	 * @author wj
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/plateStatics/json")
	@SecurityApi
	public @ResponseBody ReturnDatas plateStaticsJson(Model model,AppUser appUser,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		try {
				Integer sumPerson = 0;
				Double posterSumMoney = 0.0;
				Double posterSnachMoney = 0.0;
				Double mediaSumMoney = 0.0;
				Double mediaSnachMoney = 0.0;
				
				//查询用户总个数
				Finder userFinder = new Finder("SELECT COUNT(*) as sumPerson FROM t_app_user WHERE isBlack = 0");
				List<AppUser> appUsers = appUserService.queryForList(userFinder,AppUser.class);
				if(null != appUsers && appUsers.size() > 0){
					sumPerson = appUsers.get(0).getSumPerson();
				}
				
				//查询海报红包总金额以及剩余金额
				Finder posterFinder = new Finder("SELECT SUM(sumMoney) as posterSumMoney,SUM(balance) AS posterRemainMoney FROM t_poster_package WHERE isDel = 0 AND `status`=3");
				List<AppUser> posterAppUsers = appUserService.queryForList(posterFinder,AppUser.class);
				if(null != posterAppUsers && posterAppUsers.size() > 0){
					AppUser posterAppUser = posterAppUsers.get(0);
					if(null != posterAppUser && null != posterAppUser.getPosterSumMoney()){
						posterSumMoney = posterAppUser.getPosterSumMoney();
					}
					if(null != posterAppUser && null != posterAppUser.getPosterRemainMoney()){
						posterSnachMoney = new BigDecimal(posterSumMoney).subtract(new BigDecimal(posterAppUser.getPosterRemainMoney())).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
					}
				}
				
				//查询视频红包总金额以及剩余金额
				Finder mediaFinder = new Finder("SELECT SUM(sumMoney) as mediaSumMoney,SUM(balance) AS mediaRemainMoney FROM t_media_package WHERE isDel = 0 AND `status`=3");
				List<AppUser> mediaAppUsers = appUserService.queryForList(mediaFinder,AppUser.class);
				if(null != mediaAppUsers && mediaAppUsers.size() > 0){
					AppUser mediaAppUser = mediaAppUsers.get(0);
					if(null != mediaAppUser && null != mediaAppUser.getMediaSumMoney()){
						mediaSumMoney = mediaAppUser.getMediaSumMoney();
					}
					if(null != mediaAppUser && null != mediaAppUser.getMediaRemainMoney()){
						mediaSnachMoney = new BigDecimal(mediaSumMoney).subtract(new BigDecimal(mediaAppUser.getMediaRemainMoney())).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
					}
				}
				
				Double sumMoney = new BigDecimal(posterSumMoney).add(new BigDecimal(mediaSumMoney)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				Double snachMoney = new BigDecimal(posterSnachMoney).add(new BigDecimal(mediaSnachMoney)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				
				Map<String, Object> map = new HashMap<>();
				map.put("sumPerson", sumPerson);
				map.put("sumMoney", sumMoney);
				map.put("snachMoney", snachMoney);
				returnObject.setData(map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return returnObject;
	} 
	
	
	/**
	 * 首页统计
	 * @author wj
	 * @param model
	 * @param appUser
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/indexStatics/json")
	@SecurityApi
	public @ResponseBody ReturnDatas indexStaticsJson(Model model,AppUser appUser,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		try {
			if( null != appUser.getCityId()){
				Double todayMoney = 0.0; //今日收益钱数
				Integer scanNum = 0;//浏览数量
				Integer topNum = 0;//点赞总数
				Integer commentNum = 0;//评论个数
				Integer attenNum = 0;//关注个数
				
				//统计别人浏览我的次数
				Finder scanFinder = new Finder("SELECT SUM(a.scanNum) AS scanNum FROM(SELECT SUM(lookNum) AS scanNum FROM t_poster_package pp WHERE userId = :userId AND isDel = 0 AND `status` != 0 AND `status` != 2 UNION"
                                        +"( SELECT SUM(scanNum) AS scanNum FROM t_media_package WHERE userId = :userId AND isDel = 0 AND `status` != 0 AND `status` != 2) UNION "
                                        + "(SELECT SUM(viewedCount) AS scanNum FROM t_activity WHERE userId=:userId AND isDel=0 AND `status` != 2))a");
				scanFinder.setParam("userId", appUser.getId());
				scanNum = moneyDetailService.queryForObject(scanFinder, Integer.class); 
				if(null == scanNum ){
					scanNum = 0;
				}
				
				//统计别人点赞我的个数
				Finder topFinder = new Finder("SELECT SUM(a.topCount) AS topNum FROM (SELECT SUM(topCount)as topCount FROM t_poster_package WHERE userId = :userId AND isDel = 0 AND `status` != 0 AND `status` != 2 "
						+ " UNION (SELECT SUM(topCount) as topCount from t_media_package WHERE userId = :userId AND isDel = 0 AND `status` != 0 AND `status` != 2)"
                        +"UNION (SELECT SUM(topCount)as topCount from t_join_activity WHERE userId=:userId ) "
                        + "UNION (SELECT SUM(topCount)as topCount FROM t_circle WHERE userId=:userId))a");
				
				topFinder.setParam("userId", appUser.getId());
				topNum = moneyDetailService.queryForObject(topFinder, Integer.class); 
				if(null == topNum ){
					topNum = 0;
				}
				
				//统计别人给我评论的个数
				Finder commentFinder = new Finder("SELECT SUM(a.commentCount) AS commentNum FROM (SELECT SUM(commentCount)as commentCount FROM t_poster_package WHERE userId = :userId AND isDel = 0 AND `status` != 0 AND `status` != 2 "
						+ " UNION (SELECT SUM(commentCount) as commentCount from t_media_package WHERE userId = :userId AND isDel = 0 AND `status` != 0 AND `status` != 2)"
                        +"UNION (SELECT SUM(commentCount)as commentCount from t_join_activity WHERE userId=:userId ) "
                        + "UNION (SELECT SUM(commentCount)as commentCount FROM t_circle WHERE userId=:userId))a");
				
				commentFinder.setParam("userId", appUser.getId());
				commentNum = moneyDetailService.queryForObject(commentFinder, Integer.class); 
				if(null == commentNum ){
					commentNum = 0;
				}
				
				//统计别人关注我的个数
				Finder atteFinder = new Finder("SELECT COUNT(*) as attenNum FROM t_attention WHERE itemId=:userId");
				atteFinder.setParam("userId", appUser.getId());
				attenNum = moneyDetailService.queryForObject(atteFinder, Integer.class);
				if(null == attenNum ){
					attenNum = 0;
				}
				
				
				//总收益统计
				Finder sumMoneyFinder = new Finder("SELECT SUM(money) as sumMoney FROM t_money_detail WHERE (type=1 OR type=2 OR type=8 OR type=16 OR type=15) and userId=:userId");
				sumMoneyFinder.setParam("userId", appUser.getId());
				Double sumMoney = moneyDetailService.queryForObject(sumMoneyFinder, Double.class);
				if(null == sumMoney){
					sumMoney = 0.0;
				}
				
				//今日收益统计
				Finder todayMoneyFinder = new Finder("SELECT SUM(money) as todayMoney FROM t_money_detail WHERE (type=1 OR type=2 OR type=8 OR type=16 OR type=15) and userId=:userId AND DATE_FORMAT(createTime,'%m-%d-%Y') = DATE_FORMAT(NOW(),'%m-%d-%Y')");
				todayMoneyFinder.setParam("userId", appUser.getId());
				todayMoney = moneyDetailService.queryForObject(todayMoneyFinder, Double.class);
				if(null == todayMoney){
					todayMoney = 0.0;
				}
				
				//查询海报红包的个数
				Finder posterFinder = new Finder("SELECT COUNT(*) as posterCount FROM t_poster_package WHERE isDel= 0 AND ((`status`=4 AND isValid!=1) OR `status`=3) AND succTime > (SELECT posterScanTime FROM t_app_user WHERE id=:userId) and id in( SELECT DISTINCT(packageId) FROM t_red_city WHERE (cityId=:cityId || cityId=0) and type=1)");
				posterFinder.setParam("userId", appUser.getId());
				posterFinder.setParam("cityId", appUser.getCityId());
				Integer posterCount = moneyDetailService.queryForObject(posterFinder, Integer.class);
				if(null == posterCount){
					posterCount = 0;
				}
				
				//查询海报红包待抢金额
				Finder posterMoneyFinder = new Finder("SELECT SUM(balance) as posterBalance FROM t_poster_package WHERE isDel= 0 AND `status`=3 and id in( SELECT DISTINCT(packageId) FROM t_red_city WHERE cityId=:cityId || cityId=0 and type=1)");
				posterMoneyFinder.setParam("cityId", appUser.getCityId());
				Double posterMoney = moneyDetailService.queryForObject(posterMoneyFinder, Double.class);
				if(null == posterMoney){
					posterMoney = 0.0;
				}
				
				//查询视频红包个数
				Finder mediaFinder = new Finder("SELECT COUNT(*) mediaCount FROM t_media_package WHERE isDel= 0 AND ((`status`=4 AND isValid!=1) OR `status`=3) AND succTime > (SELECT mediaScanTime FROM t_app_user WHERE id=:userId) and id in( SELECT DISTINCT(packageId) FROM t_red_city WHERE (cityId=:cityId || cityId=0) and type=2)");
				mediaFinder.setParam("userId", appUser.getId());
				mediaFinder.setParam("cityId", appUser.getCityId());
				Integer mediaCount = moneyDetailService.queryForObject(mediaFinder, Integer.class);
				if(null == mediaCount){
					mediaCount = 0;
				}
				
				//查询视频红包待抢金额
				Finder mediaMoneyFinder = new Finder("SELECT SUM(balance) as mediaBalance FROM t_media_package WHERE isDel= 0 AND `status`=3 and id in( SELECT DISTINCT(packageId) FROM t_red_city WHERE (cityId=:cityId || cityId=0) and type=2)");
				mediaMoneyFinder.setParam("cityId", appUser.getCityId());
				Double mediaMoney = moneyDetailService.queryForObject(mediaMoneyFinder, Double.class);
				if(null == mediaMoney){
					mediaMoney = 0.0;
				}
				
				//查询同城活动个数
				Finder activityFinder = new Finder("SELECT COUNT(*) activityCount FROM t_activity WHERE isDel= 0 AND (`status`=4 OR `status`=3) AND aduitSuccessTime > (SELECT activityScanTime FROM t_app_user WHERE id=:userId) and id in( SELECT DISTINCT(packageId) FROM t_red_city WHERE (cityId=:cityId || cityId=0) and type=4)");
				activityFinder.setParam("userId", appUser.getId());
				activityFinder.setParam("cityId", appUser.getCityId());
				Integer activityCount = moneyDetailService.queryForObject(activityFinder, Integer.class);
				if(null == activityCount){
					activityCount = 0;
				}
				
				//查询同城活动参与人数
				Finder joinFinder = new Finder("SELECT COUNT(id) AS joinCount FROM t_join_activity");
				Integer joinCount = moneyDetailService.queryForObject(joinFinder,Integer.class);
				if(null == joinCount){
					joinCount = 0;
				}
				
				//查询城事圈右上角
				Finder circleFinder = new Finder("SELECT count(*) FROM t_circle WHERE cityId=:cityId AND createTime>(SELECT circleScanTime FROM t_app_user WHERE id=:userId)");
				circleFinder.setParam("cityId", appUser.getCityId());
				circleFinder.setParam("userId", appUser.getId());
				Integer circleCount = moneyDetailService.queryForObject(circleFinder,Integer.class);
				if(null == circleCount){
					circleCount = 0;
				}
				
				//查询城事圈条数
				Finder cityCircleFinder = new Finder("SELECT count(*) FROM t_circle WHERE cityId=:cityId");
				cityCircleFinder.setParam("cityId", appUser.getCityId());
				cityCircleFinder.setParam("userId", appUser.getId());
				Integer cityCircleCount = moneyDetailService.queryForObject(cityCircleFinder,Integer.class);
				if(null == cityCircleCount){
					cityCircleCount = 0;
				}
				
				
				
				Map<String, Object> map = new HashMap<>();
				map.put("todayMoney", todayMoney);
				map.put("scanNum", scanNum);
				map.put("topNum", topNum);
				map.put("commentNum", commentNum);
				map.put("attenNum", attenNum);
				map.put("sumMoney", sumMoney);
				map.put("posterCount", posterCount);
				map.put("posterMoney", posterMoney);
				map.put("mediaCount", mediaCount);
				map.put("mediaMoney", mediaMoney);
				map.put("activityCount", activityCount);
				map.put("joinCount", joinCount);
				map.put("circleCount", circleCount);
				map.put("cityCircleCount", cityCircleCount);
				
				
				
				
				returnObject.setData(map);
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("参数缺失");
			}
			
				
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return returnObject;
	} 
	
	
	/**
	 * 增加余额操作
	 * @author wj
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addMoney")
	public @ResponseBody ReturnDatas addMoney(HttpServletRequest request) throws Exception {
		// 执行删除
		try {
		  String  strId=request.getParameter("id");
		  String strMoney = request.getParameter("money");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
			 Double money = Double.parseDouble(strMoney);
				AppUser appUser=appUserService.findAppUserById(id);
				if(null != appUser){
					if(null == appUser.getBalance()){
						appUser.setBalance(0.0);
					}
					appUser.setBalance(new BigDecimal(appUser.getBalance()).add(new BigDecimal(money)).doubleValue());
				}
				
				MoneyDetail moneyDetail = new MoneyDetail();
				moneyDetail.setCreateTime(new Date());
				moneyDetail.setType(17);
				moneyDetail.setMoney(+money);
				moneyDetail.setBalance(appUser.getBalance());
				moneyDetail.setItemId(id);
				moneyDetailService.save(moneyDetail);
				
				appUserService.update(appUser,true);
			} else {
				return new ReturnDatas(ReturnDatas.ERROR,"参数缺失");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS, MessageUtils.UPDATE_SUCCESS);
	}
	
	

}
