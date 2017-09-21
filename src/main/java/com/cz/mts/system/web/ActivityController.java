package  com.cz.mts.system.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

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
import com.cz.mts.system.entity.ApplyMedal;
import com.cz.mts.system.entity.Awards;
import com.cz.mts.system.entity.City;
import com.cz.mts.system.entity.JoinActivity;
import com.cz.mts.system.entity.Medal;
import com.cz.mts.system.entity.MediaPackage;
import com.cz.mts.system.entity.Oper;
import com.cz.mts.system.entity.RedCity;
import com.cz.mts.system.entity.UserMedal;
import com.cz.mts.system.exception.ParameterErrorException;
import com.cz.mts.system.service.IActivityService;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.IAttentionService;
import com.cz.mts.system.service.IAwardsService;
import com.cz.mts.system.service.ICityService;
import com.cz.mts.system.service.ICollectService;
import com.cz.mts.system.service.IJoinActivityService;
import com.cz.mts.system.service.IOperService;
import com.cz.mts.system.service.IRedCityService;
import com.cz.mts.system.service.NotificationService;


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
	private IOperService operService;
	@Resource
	private IAppUserService appUserService;
	@Resource
	private IJoinActivityService joinActivityService;
	@Resource
	private ICollectService collectService;
	@Resource
	private IAttentionService attentionService;
	@Resource
	private NotificationService notificationService;
	@Resource
	private ICityService cityService;
	@Resource
	private IRedCityService redCityService;
	
	
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
		
		Finder finder = Finder.getSelectFinder(Activity.class).append(" where 1=1 and isDel=0");
	
		if(StringUtils.isNotBlank(activity.getUserName())){
			finder.append(" and userId in (select id from t_app_user where name like '%"+activity.getUserName()+"%')");
		}
		
		if(StringUtils.isNotBlank(activity.getCityIds())){
			finder.append(" and id in( SELECT DISTINCT(packageId) FROM t_red_city WHERE cityId=:cityId and type=4)");
			finder.setParam("cityId", Integer.parseInt(activity.getCityIds()));
		}
		
		if(null != activity.getType()){
			finder.append(" and type=:type");
			finder.setParam("type", activity.getType());
		}
		if(StringUtils.isNotBlank(activity.getContent())){
			finder.append(" and content like '%"+activity.getContent()+"%'");
		}
		if(StringUtils.isNotBlank(activity.getPhone())){
			finder.append(" and phone like '%"+activity.getPhone()+"%'");
		}
		if(null != activity.getStatus()){
			finder.append(" and status = :status");
			finder.setParam("status", activity.getStatus());
		}
		
		// ==执行分页查询
		List<Activity> datas=activityService.findListDataByFinder(finder,page,Activity.class,null);
		if(null != datas && datas.size() > 0){
			for (Activity activity2 : datas) {
				if(activity2.getUserId()!=null){
					  
					  AppUser appUser=appUserService.findAppUserById(activity2.getUserId());
					  
					  if(appUser!=null && StringUtils.isNotBlank(appUser.getName())){
						  
						  activity2.setUserName(appUser.getName());
						  
					  }
					  
				  }
				
				if(StringUtils.isNotBlank(activity2.getImage())){
					if(activity2.getImage().contains(";")){
						activity2.setImage(activity2.getImage().split(";")[0]);
					}else{
						activity2.setImage(activity2.getImage());
					}
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
			  
			  if(null != activity.getStatus()){
				  switch (activity.getStatus()) {
					case 1:
						activity.setStatusName("待审核");
						break;
					case 2:
						activity.setStatusName("审核失败");
						break;
					case 3:
						activity.setStatusName("审核通过");
						break;
					case 4:
						activity.setStatusName("已过期");
						break;
				}
			  }
			  
			  
		  }
		  
		  String appuserId = request.getParameter("appuserId");
		  
		  if(StringUtils.isNotBlank(appuserId)){
				
			 
			  Finder finderJoin = Finder.getSelectFinder(JoinActivity.class).append(" where userId=:userId and activityId =:id");
			  finderJoin.setParam("userId", appuserId);
			  finderJoin.setParam("id", id);
			  List<JoinActivity> joinActivities = joinActivityService.queryForList(finderJoin, JoinActivity.class);
//			  
//			  //查询评论次数
//			  activity.setCommentCount(0);
//			  
//			  for (JoinActivity joinActivity : joinActivities) {
//				if(joinActivity.getCommentCount()!=null){
//					
//					activity.setCommentCount(joinActivity.getCommentCount()+activity.getCommentCount());
//					
//				}
//			  }
			  
			  if(joinActivities!=null&&joinActivities.size()>0){
				  activity.setIsPart(1);
			  }else {
				  activity.setIsPart(0);
			  }
			  
			  	//查询是否点赞
			  	Page newPage=new Page();
				Finder operFinder=Finder.getSelectFinder(Oper.class).append(" where userId=:userId and itemId=:itemId and type=6");
				operFinder.setParam("userId", Integer.parseInt(appuserId));
				operFinder.setParam("itemId", activity.getId());
				List<Oper> isOpers = operService.findListDataByFinder(operFinder, newPage, Oper.class, null);
				if(isOpers!=null&&isOpers.size()>0){
					activity.setIsOper(1);
				}else {
					activity.setIsOper(0);
				}
			
			}
		  
		  if(activity.getViewedCount()==null){
			  activity.setViewedCount(1);
		  }else {
			  if(activity.getStatus()!=3&&activity.getStatus()!=4){
				  activity.setViewedCount(activity.getViewedCount()+1);
			  }
			  if((activity.getViewedCount()%100)==0){
				  //每100个人浏览的情况下，发推送
				  notificationService.notify(27, activity.getId(), activity.getUserId());
			  }
		  }
		  
		  
		  activityService.update(activity,true);
		  
		  
			String cityIds= ""; 
			 //返回城市名称
			 Finder finder = new Finder("SELECT * FROM t_red_city WHERE packageId=:id AND type=4");
			 finder.setParam("id", Integer.parseInt(strId));
			 List<RedCity> redCities = redCityService.queryForList(finder,RedCity.class);
			 if(null != redCities && redCities.size() > 0){
				 for (RedCity redCity : redCities) {
					 if(null != redCity.getCityId() && 0 != redCity.getCityId()){
						 cityIds += redCity.getCityId()+",";
					 }
					if(null != redCity.getCityId()){
						City city = cityService.findCityById(redCity.getCityId());
						if(null != city && StringUtils.isNotBlank(city.getName())){
							redCity.setCityName(city.getName());
						}
					}
				}
				 activity.setCityIds(cityIds);
				 activity.setRedCities(redCities);
			 }
		  
		  
		  
		  
		  
		  
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
			
			finder.append(" and id in (select packageId from t_red_city where (cityId=:cityId or cityId=0) and type=4)");
			finder.setParam("cityId", Integer.parseInt(cityId));
			
		}
		
		if(activity.getUserId()!=null){
			
			finder.append(" and userId=:userId");
			finder.setParam("userId", activity.getUserId());
			
		}
		
		if(activity.getStatus()==null){
			
			finder.append(" and (status=3 or status=4)");
			
		}else {
			finder.append(" and status = :status");
			finder.setParam("status", activity.getStatus());
		}
		
		//搜索昵称/标题
		if(StringUtils.isNotBlank(selectTitle)){
			finder.append(" and (content like :selectTitle or userId in (select id from t_app_user where name like :selectTitle))");
			finder.setParam("selectTitle", "%"+selectTitle+"%");
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
				finder.append(",endTime ASC");
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
		
		//List<Activity> datas=activityService.findListDataByFinder(finder,page,Activity.class,null);
		List<Activity> datas=activityService.queryForList(finder, Activity.class, page);
		
		if(null != datas && datas.size() > 0){
			for (Activity activity2 : datas) {
				
				if(StringUtils.isNotBlank(appuserId)){
					
					//查询是否点赞
					Page newPage=new Page();
					Finder operFinder=Finder.getSelectFinder(Oper.class).append(" where userId=:userId and itemId=:itemId and type=6");
					operFinder.setParam("userId", Integer.parseInt(appuserId));
					operFinder.setParam("itemId", activity2.getId());
					List<Oper> isOpers = operService.findListDataByFinder(operFinder, newPage, Oper.class, null);
					if(isOpers!=null&&isOpers.size()>0){
						activity2.setIsOper(1);
					}else {
						activity2.setIsOper(0);
					}
				
					
				}
				
			}
		}
		
		
		
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
				Object id = activityService.addOrUpdate(activity, awards,cityIds);
				returnObject.setData(id);
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
		return "/activity/activityCru";
	}
	
	/**
	 * 假删除
	 * @author wml
	 * @param model
	 * @param applyMedal
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete/json")
	public @ResponseBody
	ReturnDatas deletejson(Model model,Activity activity,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if(null != activity.getId()){
				activity.setIsDel(1);
				activityService.update(activity,true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
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
	
	/**
	 * 通过认证
	 * @author wml
	 * @param model
	 * @param applyMedal
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/check/json")
	public @ResponseBody
	ReturnDatas checkjson(Model model,Activity activity,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if(null != activity.getId()){
				activity.setStatus(3);
				activity.setAduitSuccessTime(new Date());
				activityService.update(activity,true);
				Activity at = activityService.findActivityById(activity.getId());
				if(null != at && null != at.getUserId()){
					AppUser appUser = appUserService.findAppUserById(at.getUserId());
					if(null != appUser && null != appUser.getIsPush() && 1 == appUser.getIsPush()){
						notificationService.notify(22, at.getId(), at.getUserId());
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	/**
	 * 拒绝认证
	 * @author wml
	 * @param model
	 * @param applyMedal
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/fail/json")
	public @ResponseBody
	ReturnDatas failjson(Model model,Activity activity,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if(null != activity.getId()){
				activity.setStatus(2);
				activity.setAduitFailTime(new Date());
//				notificationService.notify(23, activity.getId(), activity.getUserId());
				activityService.update(activity,true);
				
				Activity at = activityService.findActivityById(activity.getId());
				if(null != at && null != at.getUserId()){
					AppUser appUser = appUserService.findAppUserById(at.getUserId());
					if(null != appUser && null != appUser.getIsPush() && 1 == appUser.getIsPush()){
						notificationService.notify(23, at.getId(), at.getUserId());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	

	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/update")
	public @ResponseBody
	ReturnDatas saveorupdateAdmin(Model model,Activity activity,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if(null == activity.getId()){
				activityService.saveorupdate(activity);
			}else{
				//cityIds是否为空
				if(StringUtils.isNotBlank(activity.getCityIds())){
					String cityIds[] = activity.getCityIds().split(",");
					if(null != cityIds && cityIds.length > 0){
						//删除红包表中的记录
						Finder finder = new Finder("DELETE FROM t_red_city WHERE type=4 and packageId=:packageId");
						finder.setParam("packageId", activity.getId());
						redCityService.update(finder);
						for (String cid : cityIds) {
							Integer cityId = Integer.parseInt(cid);
							//更新redCity表
							RedCity redCity = new RedCity();
							redCity.setCityId(cityId);
							redCity.setPackageId(activity.getId());
							redCity.setType(4);
							redCityService.save(redCity);
						}
					}
				}else{
					//删除红包表中的记录
					Finder finder = new Finder("DELETE FROM t_red_city WHERE type=4 and packageId=:packageId");
					finder.setParam("packageId", activity.getId());
					redCityService.update(finder);
					//更新redCity表
					RedCity redCity = new RedCity();
					redCity.setCityId(0);
					redCity.setPackageId(activity.getId());
					redCity.setType(4);
					redCityService.save(redCity);
				}
				
				
				activityService.update(activity,true);
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
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
        
        return result;
    }    
	
	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/getPoi/json")
	public @ResponseBody
	ReturnDatas getPoi(Model model,Activity activity,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			
				String lat = request.getParameter("lat");
			     String lon = request.getParameter("lon");
			     String url =
			            "http://api.map.baidu.com/geocoder/v2/?ak=NgMZRcGV4cz2tc9tvQD7M5VkgB7gUT3g&callback=renderReverse&location="
			                + lat + "," + lon + "&output=json&pois=1";
			   
			    //自己定义的http类，post方法大家可以去网上搜
			            String jsonStr = sendPost(url,null);
			            System.out.println( new String(jsonStr.getBytes("gbk"), "utf-8"));
			            
			//renderReverse&&renderReverse({"status":0,"result":{"location":{"lng":116.322987,"lat":39.983424071404},"formatted_address":"北京市海淀区中关村大街27号1101-08   
			// 室","business":"人民大学,中关村,苏州街","addressComponent":{"city":"北京市","district":"海淀区","province":"北京市","street":"中关村大街","street_number":"27号1101-08
			//室"},"cityCode":131}})
			           returnObject.setData(new String(jsonStr.getBytes("gbk"), "utf-8"));
			         
			
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	
	
	

}
