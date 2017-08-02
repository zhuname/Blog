package  com.cz.mts.system.web;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cz.mts.frame.annotation.SecurityApi;
import com.cz.mts.frame.controller.BaseController;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.HttpUtils;
import com.cz.mts.frame.util.MessageUtils;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Circle;
import com.cz.mts.system.entity.CityCircle;
import com.cz.mts.system.exception.ParameterErrorException;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.ICircleService;
import com.cz.mts.system.service.ICityCircleService;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-07-06 09:32:38
 * @see com.cz.mts.system.web.CityCircle
 */
@Controller
@RequestMapping(value="/system/citycircle")
public class CityCircleController  extends BaseController {
	@Resource
	private ICityCircleService cityCircleService;
	@Resource
	private IAppUserService appUserService;
	@Resource
	private ICircleService circleService;
	
	private String listurl="/citycircle/citycircleList";
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * @param request
	 * @param model
	 * @param cityCircle
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,CityCircle cityCircle) 
			throws Exception {
		ReturnDatas returnObject = adminListjson(request, model, cityCircle);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param cityCircle
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/adminList/json")
	public @ResponseBody
	ReturnDatas adminListjson(HttpServletRequest request, Model model,CityCircle cityCircle) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		
		
		Finder finder =Finder.getSelectFinder(CityCircle.class).append(" where 1=1 and status=1 ");
		
			
			if(StringUtils.isNotBlank(cityCircle.getUserName())){
				finder.append(" and userId in (select id from t_app_user where name like '%"+cityCircle.getUserName()+"%')");
			}
			
		
		if(StringUtils.isNotBlank(cityCircle.getTitle())){
			finder.append(" and itemId in (select id from t_circle where content like '%"+cityCircle.getTitle()+"%')");
		}
		
		
		List<CityCircle> datas=cityCircleService.findListDataByFinder(finder,page,CityCircle.class,cityCircle);
		
		for (CityCircle cityCircle2 : datas) {
			//刷新城事圈儿事件
			if(cityCircle2.getUserId()!=null){
				
				AppUser appUser = appUserService.findAppUserById(cityCircle2.getUserId());
				if(appUser!=null && StringUtils.isNotBlank(appUser.getName())){
					cityCircle2.setUserName(appUser.getName());
				}
			}
			
			if(cityCircle2.getItemId()!=null){
				Circle circle=circleService.findCircleById(cityCircle2.getItemId());
				if(circle!=null){
					cityCircle2.setTitle(circle.getContent());
				}
			}
		}
		
		returnObject.setQueryBean(cityCircle);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param cityCircle
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,CityCircle cityCircle) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		
		
		Finder finder =Finder.getSelectFinder(CityCircle.class).append(" where 1=1 and status=1 ");
		
		if(cityCircle.getAppUser()!=null){
			
			if(StringUtils.isNotBlank(cityCircle.getAppUser().getName())){
				
				finder.append(" and userId in (select id from t_app_user where name=:name)");
				finder.setParam("name", cityCircle.getAppUser().getName());
				
			}
			
		}
		
		if(StringUtils.isNotBlank(cityCircle.getTitle())){
			
				finder.append(" and itemId in (select id from t_circle where content=:title)");
				finder.setParam("title", cityCircle.getTitle());
			
		}
		
		
		List<CityCircle> datas=cityCircleService.findListDataByFinder(finder,page,CityCircle.class,cityCircle);
		
		for (CityCircle cityCircle2 : datas) {
			//刷新城事圈儿事件
			if(cityCircle2.getUserId()!=null){
				
				AppUser appUser = appUserService.findAppUserById(cityCircle2.getUserId());
				if(appUser!=null){
					
					cityCircle2.setAppUser(appUser);
					
				}
			}
			
			if(cityCircle2.getItemId()!=null){
				
				Circle circle=circleService.findCircleById(cityCircle2.getItemId());
				
				if(circle!=null){
					
					cityCircle2.setTitle(circle.getContent());
					
				}
				
			}
			
		}
		
		returnObject.setQueryBean(cityCircle);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,CityCircle cityCircle) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = cityCircleService.findDataExportExcel(null,listurl, page,CityCircle.class,cityCircle);
		String fileName="cityCircle"+GlobalStatic.excelext;
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
		return "/system/citycircle/citycircleLook";
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
		  CityCircle cityCircle = cityCircleService.findCityCircleById(id);
		   returnObject.setData(cityCircle);
		}else{
		returnObject.setStatus(ReturnDatas.ERROR);
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
	ReturnDatas saveorupdate(Model model,CityCircle cityCircle,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
			Object id = cityCircleService.saveorupdate(cityCircle);
			CityCircle cCircle = cityCircleService.findCityCircleById(id);
			returnObject.setData(cCircle);
			
			
		} catch (ParameterErrorException e) {
			logger.error("参数缺失");
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数缺失");
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
		return "/system/citycircle/citycircleCru";
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
				cityCircleService.deleteById(id,CityCircle.class);
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
			cityCircleService.deleteByIds(ids,CityCircle.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}
	

}