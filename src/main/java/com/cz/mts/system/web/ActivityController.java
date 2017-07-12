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

import com.cz.mts.frame.annotation.SecurityApi;
import com.cz.mts.frame.controller.BaseController;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.MessageUtils;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.system.entity.Activity;
import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Awards;
import com.cz.mts.system.entity.JoinActivity;
import com.cz.mts.system.exception.ParameterErrorException;
import com.cz.mts.system.service.IActivityService;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.IAttentionService;
import com.cz.mts.system.service.IAwardsService;
import com.cz.mts.system.service.ICollectService;
import com.cz.mts.system.service.IJoinActivityService;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-07-03 16:06:04
 * @see com.cz.mts.system.web.Activity
 */
@Controller
@RequestMapping(value="/system/activity")
public class ActivityController  extends BaseController {
	@Resource
	private IActivityService activityService;
	@Resource
	private IAwardsService awardsService;
	@Resource
	private IAppUserService appUserService;
	@Resource
	private IJoinActivityService joinActivityService;
	@Resource
	private ICollectService collectService;
	@Resource
	private IAttentionService attentionService;
	
	private String listurl="/activity/activityList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param activity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,Activity activity) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, activity);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param activity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,Activity activity) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		
		
		
		
		// ==执行分页查询
		List<Activity> datas=activityService.findListDataByFinder(null,page,Activity.class,activity);
		
		for (Activity activity2 : datas) {
			
			if(activity2.getUserId()!=null){
				  
				  AppUser appUser=appUserService.findAppUserById(activity2.getUserId());
				  
				  if(appUser!=null){
					  
					  activity2.setAppUser(appUser);
					  
				  }
				  
			  }
			
		}
		
		returnObject.setQueryBean(activity);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,Activity activity) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = activityService.findDataExportExcel(null,listurl, page,Activity.class,activity);
		String fileName="activity"+GlobalStatic.excelext;
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
		return "/system/activity/activityLook";
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
		  Activity activity = activityService.findActivityById(id);
		  
		  if(activity!=null){
			  
			  Finder finder = Finder.getSelectFinder(Awards.class).append(" where activityId=:activityId");
			  finder.setParam("activityId", id);
			  activity.setAwardss(awardsService.queryForList(finder, Awards.class));
			  
			  
			  if(activity.getUserId()!=null){
				  
				  AppUser appUser=appUserService.findAppUserById(activity.getUserId());
				  
				  if(appUser!=null){
					  
					  activity.setAppUser(appUser);
					  
				  }
				  
			  }
			  
			  
		  }
		  
		  String appuserId = request.getParameter("appuserId");
		  
		  if(StringUtils.isNotBlank(appuserId)){
				
			 
			  Finder finderJoin = Finder.getSelectFinder(JoinActivity.class).append(" where userId=:userId and activityId =:id");
			  finderJoin.setParam("userId", appuserId);
			  finderJoin.setParam("id", id);
			  List<JoinActivity> joinActivities = joinActivityService.queryForList(finderJoin, JoinActivity.class);
			  
			  //查询评论次数
			  activity.setCommentCount(0);
			  
			  for (JoinActivity joinActivity : joinActivities) {
				if(joinActivity.getCommentCount()!=null){
					
					activity.setCommentCount(joinActivity.getCommentCount()+activity.getCommentCount());
					
				}
			  }
			  
			  if(joinActivities!=null&&joinActivities.size()>0){
				  activity.setIsPart(1);
			  }else {
				  activity.setIsPart(0);
			  }
			
			}
		  
		  if(activity.getViewedCount()==null){
			  activity.setViewedCount(1);
		  }else {
			  activity.setViewedCount(activity.getViewedCount()+1);
		  }
		  
		  activityService.update(activity);
		  
		   returnObject.setData(activity);
		}else{
		returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;
		
	}
	
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param activity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/appList/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas appListjson(HttpServletRequest request, Model model,Activity activity) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		
		String cityId=request.getParameter("cityId");
		
		String appuserId = request.getParameter("appuserId");
		
		String sort = request.getParameter("sort");
		
		String selectTitle = request.getParameter("selectTitle");
		
		Finder finder =Finder.getSelectFinder(Activity.class).append(" where 1=1 ");
		
		if(StringUtils.isNotBlank(cityId)){
			
			finder.append(" and id in (select packageId from t_red_city where (cityId=:cityId or cityId=0))");
			finder.setParam("cityId", Integer.parseInt(cityId));
			
		}
		
		if(activity.getStatus()==null){
			
			finder.append(" and (status=3 or status=5)");
			
		}else {
			finder.append(" and status = :status");
			finder.setParam("status", activity.getStatus());
		}
		
		//搜索昵称/标题
		if(StringUtils.isNotBlank(selectTitle)){
			finder.append(" and (content like :selectTitle or userId in (select id from t_app_user where name like :selectTitle))");
			finder.setParam("selectTitle", selectTitle);
		}
		
		finder.append(" order by status ASC");
		
		if(StringUtils.isNotBlank(sort)){
			
			//1最新发布 2参与人数 3截止时间
			switch (sort) {
			case "1":
				finder.append(",aduitSuccessTime DESC");
				break;
			case "2":
				finder.append(",joinCount DESC");
				break;
			case "3":
				finder.append(",endTime DESC");
				break;
			default:
				finder.append(",aduitSuccessTime DESC");
				break;
			}
		}else {
			finder.append(",aduitSuccessTime DESC");
		}
		
		
		if(StringUtils.isNotBlank(appuserId)){
			
			AppUser appUser = appUserService.findAppUserById(Integer.parseInt(appuserId));
			if(appUser!=null){
				
				appUser.setActivityScanTime(new Date());
				appUserService.update(appUser, true);
				
			}
			
		}
		
		//order如果不为空的时候base里面会默认一个desc id  所以这块为空
		page.setOrder(null);
		page.setSort(null);
		
		List<Activity> datas=activityService.findListDataByFinder(finder,page,Activity.class,activity);
		returnObject.setQueryBean(activity);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param activity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myAppList/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas myAppListjson(HttpServletRequest request, Model model,Activity activity) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		
		String appuserId = request.getParameter("appuserId");
		
		Finder finder =Finder.getSelectFinder(Activity.class).append(" where 1=1 ");
		
		if(StringUtils.isNotBlank(appuserId)){
			
			finder.append(" and id in (select activityId from t_join_activity where userId=:userId )");
			finder.setParam("userId", Integer.parseInt(appuserId));
			
		}
		
		finder.append(" and (status=3 or status=5)");
		
		finder.append(" order by status ASC");
		
		finder.append(",aduitSuccessTime DESC");
		
		
		//order如果不为空的时候base里面会默认一个desc id  所以这块为空
		page.setOrder(null);
		page.setSort(null);
		
		List<Activity> datas=activityService.findListDataByFinder(finder,page,Activity.class,activity);
		returnObject.setQueryBean(activity);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/update/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas saveorupdate(Model model,Activity activity,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
			String awards= request.getParameter("awards");
			String cityIds=request.getParameter("cityIds");
			if(StringUtils.isNotBlank(awards)||StringUtils.isNotBlank(cityIds)){
				activityService.addOrUpdate(activity, awards,cityIds);
				
			}else {
				logger.error("参数缺失");
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("参数缺失");
			}
			
			
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
		return "/system/activity/activityCru";
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
				activityService.deleteById(id,Activity.class);
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
			activityService.deleteByIds(ids,Activity.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
