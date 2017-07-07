package  com.cz.mts.system.web;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.cz.mts.system.entity.Appoint;
import com.cz.mts.system.entity.MediaPackage;
import com.cz.mts.system.entity.PosterPackage;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.IAppointService;
import com.cz.mts.system.service.IMediaPackageService;
import com.cz.mts.system.service.IPosterPackageService;
import com.cz.mts.system.service.IRedCityService;
import com.cz.mts.system.service.impl.PosterPackageServiceImpl;
import com.cz.mts.frame.annotation.SecurityApi;
import com.cz.mts.frame.controller.BaseController;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.MessageUtils;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-07-06 14:42:38
 * @see com.cz.mts.system.web.Appoint
 */
@Controller
@RequestMapping(value="/system/appoint")
public class AppointController  extends BaseController {
	@Resource
	private IAppointService appointService;
	@Resource
	private IRedCityService redCityService;
	@Resource
	private IPosterPackageService posterPackageService;
	@Resource
	private IMediaPackageService mediaPackageService;
	@Resource
	private IAppUserService appUserService;
	
	
	private String listurl="/system/appoint/appointList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param appoint
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,Appoint appoint) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, appoint);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * 我的预约列表
	 * @author wml
	 * @param request
	 * @param model
	 * @param appoint
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,Appoint appoint) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<Appoint> datas=appointService.findListDataByFinder(null,page,Appoint.class,appoint);
		if(null != datas && datas.size()> 0){
			for (Appoint ap : datas) {
				if(null != ap.getUserId()){
					AppUser appUser = appUserService.findAppUserById(ap.getUserId());
					if(null != appUser){
						ap.setAppUser(appUser);
					}
				}
			}
		}
		
		returnObject.setQueryBean(appoint);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,Appoint appoint) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = appointService.findDataExportExcel(null,listurl, page,Appoint.class,appoint);
		String fileName="appoint"+GlobalStatic.excelext;
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
		return "/system/appoint/appointLook";
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
		  Appoint appoint = appointService.findAppointById(id);
		   returnObject.setData(appoint);
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
	ReturnDatas saveorupdate(Model model,Appoint appoint,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
		
			appointService.saveorupdate(appoint);
			
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
		return "/system/appoint/appointCru";
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
				appointService.deleteById(id,Appoint.class);
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
			appointService.deleteByIds(ids,Appoint.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
	}
	
	
	/**
	 * 预约列表
	 * @author wj
	 * @param request
	 * @param model
	 * @param appoint
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/appointList/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas appointListjson(HttpServletRequest request, Model model,Appoint appoint) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		Finder finder = Finder.getSelectFinder(Appoint.class).append(" where 1=1 and status != 0 and type=:type and itemId=:itemId");
		finder.setParam("type", appoint.getType());
		finder.setParam("itemId", appoint.getItemId());
		List<Appoint> datas=appointService.findListDataByFinder(finder,page,Appoint.class,null);
		if(null != datas && datas.size() > 0){
			for (Appoint ap : datas) {
				if(null != ap.getUserId()){
					AppUser appUser = appUserService.findAppUserById(ap.getUserId());
					if(null != appUser){
						ap.setAppUser(appUser);
					}
				}
			}
		}
		
		//查询预约次数以及预约总金额
		Integer appointCount = 0;
		Double appintMoney = 0.0;
		Page newPage = new Page();
		List<Appoint> allAppoints = appointService.findListDataByFinder(finder,newPage,Appoint.class,null);
		if(null != allAppoints && allAppoints.size() > 0){
			appointCount = allAppoints.size();
			for (Appoint app : allAppoints) {
				if(null != app.getMoney()){
					appintMoney += app.getMoney();
				}
			}
		}else{
			appointCount = 0;
			appintMoney = 0.0;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appointCount", appointCount);
		map.put("appintMoney", new BigDecimal(appintMoney).setScale(2).doubleValue());
		returnObject.setMap(map);
		returnObject.setQueryBean(appoint);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}

}
