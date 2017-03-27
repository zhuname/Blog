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

import com.cz.mts.system.entity.Category;
import com.cz.mts.system.service.ICategoryService;
import com.cz.mts.frame.annotation.SecurityApi;
import com.cz.mts.frame.controller.BaseController;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.MessageUtils;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:25
 * @see com.cz.mts.system.web.Category
 */
@Controller
@RequestMapping(value="/system/category")
public class CategoryController  extends BaseController {
	@Resource
	private ICategoryService categoryService;
	
	private String listurl="/categoryList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一 
	 * @param request
	 * @param model
	 * @param category
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,Category category) 
			throws Exception {
		category.setType(1);
		category.setIsDel(0);
		ReturnDatas returnObject = listadminjson(request, model, category);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param category
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list1")
	public String list1(HttpServletRequest request, Model model,Category category) 
			throws Exception {
		category.setType(2);
		category.setIsDel(0);
		ReturnDatas returnObject = listadminjson(request, model, category);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param category
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list2")
	public String list2(HttpServletRequest request, Model model,Category category) 
			throws Exception {
		category.setType(3);
		category.setIsDel(0);
		ReturnDatas returnObject = listadminjson(request, model, category);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param category
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listAdmin/json")
	public @ResponseBody
	ReturnDatas listadminjson(HttpServletRequest request, Model model,Category category) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		page.setPageSize(10000);
		List<Category> datas=categoryService.findListDataByFinder(null,page,Category.class,category);
			returnObject.setQueryBean(category);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param category
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,Category category) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		page.setPageSize(10000);
		List<Category> datas=categoryService.findListDataByFinder(null,page,Category.class,category);
			returnObject.setQueryBean(category);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,Category category) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = categoryService.findDataExportExcel(null,listurl, page,Category.class,category);
		String fileName="category"+GlobalStatic.excelext;
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
		return "/system/category/categoryLook";
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
		  Category category = categoryService.findCategoryById(id);
		   returnObject.setData(category);
		}else{
		returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;
		
	}
	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/lookadmin/json")
	public @ResponseBody
	ReturnDatas lookadminjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
		  Category category = categoryService.findCategoryById(id);
		   returnObject.setData(category);
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
	ReturnDatas saveorupdate(Model model,Category category,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			category.setIsDel(0);
			categoryService.save(category);
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
		ReturnDatas returnObject = lookadminjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/category/categoryCru";
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
			 Category category = categoryService.findCategoryById(id);
			 if(null != category){
				 category.setIsDel(1);
				 categoryService.update(category,true);
			 }
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
			categoryService.deleteByIds(ids,Category.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}
	
	
	/**
	 * 获取分类
	 * 
	 * @param request
	 * @param model
	 * @param category
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/category/json")
	public @ResponseBody
	ReturnDatas categoryjson(HttpServletRequest request, Model model,Category category) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		page.setPageSize(10000);
		List<Category> datas=categoryService.findListDataByFinder(null,page,Category.class,category);
			returnObject.setQueryBean(category);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}

}
