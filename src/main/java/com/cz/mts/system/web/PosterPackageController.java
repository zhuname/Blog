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

import com.cz.mts.frame.controller.BaseController;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.MessageUtils;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Category;
import com.cz.mts.system.entity.MoneyDetail;
import com.cz.mts.system.entity.PosterPackage;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.ICategoryService;
import com.cz.mts.system.service.IMoneyDetailService;
import com.cz.mts.system.service.IPosterPackageService;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:27
 * @see com.cz.mts.system.web.PosterPackage
 */
@Controller
@RequestMapping(value="/system/posterpackage")
public class PosterPackageController  extends BaseController {
	@Resource
	private IPosterPackageService posterPackageService;
	
	private String listurl="/system/posterpackage/posterpackageList";
	
	
	@Resource
	private IAppUserService appUserService;
	
	@Resource
	private IMoneyDetailService moneyDetailService;
	
	@Resource
	private ICategoryService categoryService;
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param posterPackage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,PosterPackage posterPackage) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, posterPackage);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param posterPackage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,PosterPackage posterPackage) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		//List<PosterPackage> datas=posterPackageService.findListDataByFinder(null,page,PosterPackage.class,posterPackage);
		
		/*Finder finder=Finder.getSelectFinder(PosterPackage.class).append("select p.*,u.header FROM t_poster_package p LEFT JOIN t_app_user u ON p.userId = u.id WHERE p.id = 1");
		returnObject.setData(posterPackageService.queryForList(finder,PosterPackage.class));*/
		Finder finder1=Finder.getSelectFinder(PosterPackage.class, "p.id,p.title,u.header as userHeader ,p.balance,u.name as userName,p.image,p.lookNum  ").append(" p LEFT JOIN t_app_user u ON p.userId = u.id");
		returnObject.setData(posterPackageService.queryForList(finder1,page));
		
		
		returnObject.setQueryBean(posterPackage);
		returnObject.setPage(page);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,PosterPackage posterPackage) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = posterPackageService.findDataExportExcel(null,listurl, page,PosterPackage.class,posterPackage);
		String fileName="posterPackage"+GlobalStatic.excelext;
		downFile(response, file, fileName,true);
		return;
	}
	
		/**
	 * 查看操作,调用APP端lookjson方法
	 */
	@RequestMapping(value = "/look")
	public String look(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception {
		ReturnDatas returnObject = lookjson(model, request, response,null);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/system/posterpackage/posterpackageLook";
	}

	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	public @ResponseBody
	ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response,String appUserId) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
			 PosterPackage posterPackage = posterPackageService.findPosterPackageById(id);
			 
			 
			 //查询发红包的用户
			 if(posterPackage!=null&&posterPackage.getUserId()!=null){
				 AppUser appUser=appUserService.findAppUserById(posterPackage.getUserId());
				 if(appUser!=null){
					 posterPackage.setAppUser(appUser);
				 }
			 }
			 
			 
			 //是否领取  look 1 为领取过的
			 if(posterPackage!=null&&StringUtils.isNotBlank(appUserId)){
				 MoneyDetail moneyDetail=new MoneyDetail();
				 moneyDetail.setUserId(Integer.parseInt(appUserId));
				 moneyDetail.setItemId(posterPackage.getId());
				 moneyDetail.setType(1);
				// ==构造分页请求
					Page page = newPage(request);
					// ==执行分页查询
					List<MoneyDetail> datas=moneyDetailService.findListDataByFinder(null,page,MoneyDetail.class,moneyDetail);
					if(datas!=null&&datas.size()>0){
						posterPackage.setIsLook(1);
					}else{
						posterPackage.setIsLook(0);
					}
			 }
			 
			 returnObject.setData(posterPackage);
		}else{
			 returnObject.setMessage("参数缺失");
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
	ReturnDatas saveorupdate(Model model,PosterPackage posterPackage,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
		
			posterPackageService.saveorupdate(posterPackage);
			
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
		ReturnDatas returnObject = lookjson(model, request, response,null);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/system/posterpackage/posterpackageCru";
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
				posterPackageService.deleteById(id,PosterPackage.class);
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
			posterPackageService.deleteByIds(ids,PosterPackage.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
	}
	
	/**
	 * 抢红包
	 * @param request
	 * @param model
	 * @param id 红包id
	 * @param userId  操作人id
	 * @return
	 * @author wxy
	 * @date 2017年2月28日
	 */
	@RequestMapping("/snatch/more")
	public @ResponseBody 
	ReturnDatas snatch(HttpServletRequest request, Model model,String id,String userId){
		
		
		
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.UPDATE_SUCCESS);
	}
	
	/**
	 * 发布、修改红包接口
	 * 
	 */
	@RequestMapping("/update/json")
	public @ResponseBody
	ReturnDatas saveorupdateJson(Model model,PosterPackage posterPackage,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			
			
			if(posterPackage.getId()==null){
				
				//必须有的参数
				
				if(posterPackage.getUserId()==null||posterPackage.getCategoryId()==null){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("参数缺失");
					return returnObject;
				}
				
				if(posterPackage.getCategoryId()!=null){
					
					Category category=categoryService.findById(posterPackage.getCategoryId(), Category.class);
					
					if(category==null){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("分类不存在");
						return returnObject;
					}
					
				}
				
				posterPackage.setStatus(0);
				
				posterPackage.setIsDel(0);
				
				posterPackage.setCreateTime(new Date());
				
				posterPackageService.save(posterPackage);
				
			}else {
				
				//如果分类改了就判断下
				if(posterPackage.getCategoryId()!=null){
					
					Category category=categoryService.findById(posterPackage.getCategoryId(), Category.class);
					
					if(category==null){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("分类不存在");
						return returnObject;
					}
					
				}
				
				posterPackageService.update(posterPackage, true);
				
			}
			
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}

}
