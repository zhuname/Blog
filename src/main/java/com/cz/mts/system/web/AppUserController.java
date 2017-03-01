package  com.cz.mts.system.web;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Sms;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.ISmsService;
import com.cz.mts.system.service.impl.SmsServiceImpl;
import com.cz.mts.frame.controller.BaseController;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.MessageUtils;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.frame.util.SecUtils;
import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.service.IAppUserService;
import com.sun.tools.classfile.Annotation.element_value;


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
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	public @ResponseBody
	ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
		  AppUser appUser = appUserService.findAppUserById(id);
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
	public @ResponseBody
	ReturnDatas saveorupdate(Model model,AppUser appUser,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			Page page = newPage(request);
			AppUser user = new AppUser();
			if(StringUtils.isNoneBlank(appUser.getPhone())){
				user.setPhone(appUser.getPhone());
			}
			//判断手机号是否注册过
			List<AppUser> datas = appUserService.findListDataByFinder(null,page,AppUser.class,user);
			if(null != datas && datas.size() > 0){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("该用户已注册");
			}else{
				if(null == appUser.getId()){
					String content = request.getParameter("content");
					if(StringUtils.isBlank(appUser.getPhone()) || StringUtils.isBlank(appUser.getPassword()) || StringUtils.isBlank(content)){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("参数缺失");
					}else{
						appUser.setPassword(SecUtils.encoderByMd5With32Bit(appUser.getPassword()));
						appUser.setCreateTime(new Date());
						appUser.setIsBlack(0);
						Object id = appUserService.saveorupdate(appUser);
						returnObject.setData(appUserService.findById(id, AppUser.class));
					}
				}else{
					if(StringUtils.isNotBlank(appUser.getPassword())){
						appUser.setPassword(SecUtils.encoderByMd5With32Bit(appUser.getPassword()));
					}
					appUserService.update(appUser,true);
					returnObject.setData(appUserService.findById(appUser.getId(), AppUser.class));
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
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/system/appuser/appuserCru";
	}
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value="/delete")
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
				appUserService.update(user);
			}else{
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("该用户不存在");
			}
		}
		return returnObject;
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
	@RequestMapping("/login/json")
	public @ResponseBody
	ReturnDatas loginjson(HttpServletRequest request, Model model,AppUser appUser) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		
		if(appUser.getPhone()!=null&&appUser.getPassword()!=null){
			appUser.setPassword(SecUtils.encoderByMd5With32Bit(appUser.getPassword()));
			List<AppUser> datas=appUserService.findListDataByFinder(null,page,AppUser.class,appUser);
			if(datas!=null){
				returnObject.setData(datas.get(0));
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
	public @ResponseBody
	ReturnDatas loginSjson(HttpServletRequest request, Model model,AppUser appUser,String content) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		
		if(appUser.getQqNum()!=null||appUser.getWxNum()!=null||appUser.getSinaNum()!=null){
			appUser.setPassword(SecUtils.encoderByMd5With32Bit(appUser.getPassword()));
			List<AppUser> datas=appUserService.findListDataByFinder(null,page,AppUser.class,appUser);
			if(datas.size()>0){
				returnObject.setData(datas.get(0));
			}else{
				//没有找到的话就是新增接口
				returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
				try {
					appUser.setIsBlack(0);
					
					//有手机号的时候判断下是不是已经注册过的手机号了
					
					if(appUser.getPhone()!=null&&content!=null){
						Sms sms=new Sms();
						sms.setPhone(appUser.getPhone());
						sms.setContent(content);
						sms.setType(1);
						List<Sms> smss=smsService.findListDataByFinder(null, page, Sms.class, sms);
						
						if(smss.size()==0){
							returnObject.setStatus(ReturnDatas.ERROR);
							returnObject.setMessage(MessageUtils.UPDATE_ERROR);
							return returnObject;
						}
						
					}
					
					//看下手机号是不是已经呗注册过了
					AppUser appUserPhone=new AppUser();
					appUserPhone.setPhone(appUser.getPhone());
					List<AppUser> dataPhones=appUserService.findListDataByFinder(null,page,AppUser.class,appUserPhone);
					if(dataPhones.size()>0){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该手机号已经被注册");
						return returnObject;
					}
					
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
	public @ResponseBody 
	ReturnDatas modifytelJson(HttpServletRequest request, Model model,AppUser appUser) throws Exception{
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
	public @ResponseBody 
	ReturnDatas modifynewtelJson(HttpServletRequest request, Model model,AppUser appUser) throws Exception{
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
	public @ResponseBody 
	ReturnDatas changepwdJson(HttpServletRequest request, Model model,AppUser appUser) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		if(StringUtils.isBlank(appUser.getPassword()) || StringUtils.isBlank(appUser.getNewPwd()) || null == appUser.getId()){
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数缺失");
		}else{
			AppUser appRecord = appUserService.findAppUserById(appUser.getId());
			if(null != appRecord){
				if(StringUtils.isNotBlank(appRecord.getPassword())){
					//比较查询出来的密码和传过来的密码是否相等
					if(appRecord.getPassword().equals(SecUtils.encoderByMd5With32Bit(appUser.getPassword()))){
						appRecord.setPassword(SecUtils.encoderByMd5With32Bit(appUser.getNewPwd()));
						appUserService.update(appRecord);
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

}
