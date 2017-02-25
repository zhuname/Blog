package  com.cz.mts.system.web;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
		}
		return returnObject;
		
	}
	
	
	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/update")
	public @ResponseBody
	ReturnDatas saveorupdate(Model model,AppUser appUser,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			appUserService.saveorupdate(appUser);
			
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
					
					if(appUser.getPhone()!=null){
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
						returnObject.setMessage(MessageUtils.UPDATE_ERROR);
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
		}
		
		return returnObject;
	}
	

}
