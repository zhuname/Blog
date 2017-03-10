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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cz.mts.system.entity.City;
import com.cz.mts.system.entity.Province;
import com.cz.mts.system.service.ICityService;
import com.cz.mts.frame.annotation.SecurityApi;
import com.cz.mts.frame.controller.BaseController;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.MessageUtils;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;
import com.sun.tools.classfile.Annotation.element_value;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:25
 * @see com.cz.mts.system.web.City
 */
@Controller
@RequestMapping(value="/system/city")
public class CityController  extends BaseController {
	@Resource
	private ICityService cityService;
	
	private String listurl="/system/city/cityList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param city
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,City city) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, city);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param city
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,City city) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		page.setPageSize(100000);
		// ==执行分页查询
		List<City> datas=cityService.findListDataByFinder(null,page,City.class,city);
			returnObject.setQueryBean(city);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,City city) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = cityService.findDataExportExcel(null,listurl, page,City.class,city);
		String fileName="city"+GlobalStatic.excelext;
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
		return "/system/city/cityLook";
	}

	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
		  City city = cityService.findCityById(id);
		   returnObject.setData(city);
		}else{
		returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;
		
	}
	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/lookAdmin/json")
	public @ResponseBody
	ReturnDatas lookAdminjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
		  City city = cityService.findCityById(id);
		   returnObject.setData(city);
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
	@SecurityApi
	public @ResponseBody
	ReturnDatas saveorupdate(Model model,City city,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
		
			cityService.saveorupdate(city);
			
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
		return "/system/city/cityCru";
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
				cityService.deleteById(id,City.class);
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
			cityService.deleteByIds(ids,City.class);
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
	 * @param city
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getArea/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas getAreajson(HttpServletRequest request, Model model,Integer level,Integer fatherId) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		
		Province province=new Province();
		
		City city=new City();
		
		// ==构造分页请求
		Page page = newPage(request);
		page.setPageSize(10000);
		// ==执行分页查询
		if(level==1){
			List<Province> datas=cityService.findListDataByFinder(null,page,Province.class,province);
			returnObject.setQueryBean(province);
			returnObject.setData(datas);
		}else if(level==2){
			if(fatherId!=null){
				city.setFatherId(fatherId);
			}else {
				returnObject.setMessage("参数缺失");
				returnObject.setStatusCode(ReturnDatas.ERROR);
				return returnObject;
			}
			List<City> datas=cityService.findListDataByFinder(null,page,City.class,city);
			returnObject.setQueryBean(city);
			returnObject.setData(datas);
		}
		
		
		returnObject.setPage(page);
		return returnObject;
	}
	
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param city
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAreaAdmin/json")
	public @ResponseBody
	ReturnDatas getAreaAdminjson(HttpServletRequest request, Model model,Integer level,Integer fatherId) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		
		Province province=new Province();
		
		City city=new City();
		
		// ==构造分页请求
		Page page = newPage(request);
		page.setPageSize(10000);
		// ==执行分页查询
		if(level==1){
			List<Province> datas=cityService.findListDataByFinder(null,page,Province.class,province);
			returnObject.setQueryBean(province);
			returnObject.setData(datas);
		}else if(level==2){
			if(fatherId!=null){
				city.setFatherId(fatherId);
			}else {
				returnObject.setMessage("参数缺失");
				returnObject.setStatusCode(ReturnDatas.ERROR);
				return returnObject;
			}
			List<City> datas=cityService.findListDataByFinder(null,page,City.class,city);
			returnObject.setQueryBean(city);
			returnObject.setData(datas);
		}
		
		
		returnObject.setPage(page);
		return returnObject;
	}
	

}
