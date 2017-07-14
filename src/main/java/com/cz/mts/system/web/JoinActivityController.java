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

import com.cz.mts.system.entity.Activity;
import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Attention;
import com.cz.mts.system.entity.Awards;
import com.cz.mts.system.entity.Collect;
import com.cz.mts.system.entity.JoinActivity;
import com.cz.mts.system.entity.Oper;
import com.cz.mts.system.exception.HaveUserErrorException;
import com.cz.mts.system.exception.ParameterErrorException;
import com.cz.mts.system.service.IActivityService;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.IAttentionService;
import com.cz.mts.system.service.IAwardsService;
import com.cz.mts.system.service.ICollectService;
import com.cz.mts.system.service.IJoinActivityService;
import com.cz.mts.system.service.IOperService;
import com.cz.mts.system.service.impl.AppUserServiceImpl;
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
 * @version  2017-07-03 16:06:21
 * @see com.cz.mts.system.web.JoinActivity
 */
@Controller
@RequestMapping(value="/system/joinactivity")
public class JoinActivityController  extends BaseController {
	@Resource
	private IJoinActivityService joinActivityService;
	@Resource
	private IAppUserService appUserService;
	@Resource
	private IAwardsService awardsService;
	@Resource
	private IOperService operService;
	@Resource
	private ICollectService collectService;
	@Resource
	private IAttentionService attentionService;
	@Resource
	private IActivityService activityService;
	
	private String listurl="/system/joinactivity/joinactivityList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param joinActivity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,JoinActivity joinActivity) 
			throws Exception {
		ReturnDatas returnObject = adminListjson(request, model, joinActivity);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param joinActivity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json") 
	@SecurityApi
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,JoinActivity joinActivity) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		String appuserId=request.getParameter("appuserId");
		String name = request.getParameter("name");
		String sort = request.getParameter("sort");
		String joinOrAward = request.getParameter("joinOrAward");
		
		
		
		Finder finder = Finder.getSelectFinder(JoinActivity.class).append(" where 1=1 ");
			
			if(StringUtils.isNotBlank(name)){
				finder.append(" and userId in (select id from t_app_user where name like :name)" );
				finder.setParam("name", name);
			}
			if(StringUtils.isNotBlank(joinOrAward) && "2".equals(joinOrAward)){
				finder.append(" and awardId is not null");
			}
			
			if(joinActivity.getUserId()!=null){
				
				finder.append(" and userId = :userId");
				finder.setParam("userId", joinActivity.getUserId());
				
			}
			
			if(joinActivity.getActivityId()!=null){
				
				finder.append(" and activityId=:activityId");
				finder.setParam("activityId", joinActivity.getActivityId());
				
			}
			
			
			if(StringUtils.isNotBlank(sort)){
				switch (sort) {
				case "1":
					page.setOrder("createTime");
					page.setSort("desc");
					break;

				case "2":
					page.setOrder("topCount");
					page.setSort("desc");
					break;
				case "3":
					page.setOrder("commentCount");
					page.setSort("desc");
					break;
				}
			}
			
			
		List<JoinActivity> datas=joinActivityService.queryForList(finder, JoinActivity.class, page);
		
		for (JoinActivity joinActivity2 : datas) {
			
			//查询用户信息
			if(joinActivity2.getUserId()!=null){
				
				AppUser appUser=appUserService.findAppUserById(joinActivity2.getUserId());
				
				if(appUser!=null){
					
					joinActivity2.setAppUser(appUser);
					
				}
				
			}
			
			//如果颁奖的话查询颁奖信息
			if(joinActivity2.getAwardId()!=null){
				
				Awards awards=awardsService.findAwardsById(joinActivity2.getAwardId());
				
				if(awards!=null){
					
					joinActivity2.setAwards(awards);
					
				}
				
				
			}
			
			//获取评论列表
			Finder finderOper = Finder.getSelectFinder(Oper.class).append(" where 1=1 and type=5 and itemId=:itemId");
			
			finderOper.setParam("itemId", joinActivity2.getId());
			
			List<Oper> opers = operService.queryForList(finderOper,Oper.class);
			if(null != opers && opers.size() > 0){
				for (Oper oper : opers) {
					if(oper.getUserId()!=null){
						AppUser appUser = appUserService.findAppUserById(oper.getUserId());
						if(appUser!=null){
							
							oper.setNickName(appUser.getName());
						}
					}
				}
				joinActivity2.setOpers(opers);
			}
			
			
			
			if(StringUtils.isNotBlank(appuserId)){
					
					/*//查询是否收藏
					Finder collFinder=Finder.getSelectFinder(Collect.class).append(" where type = 4 and userId=:userId and itemId=:itemId ");
					collFinder.setParam("userId", Integer.parseInt(appuserId));
					collFinder.setParam("itemId", joinActivity2.getActivityId());
					List<Collect> collects = collectService.findListDataByFinder(collFinder, page, Collect.class, null);
					if(collects!=null&&collects.size()>0){
						joinActivity2.setIsColl(1);
					}else {
						joinActivity2.setIsColl(0);
					}*/
					
					//查询是否关注
					Page newPage = new Page();
					Finder attenFinder=Finder.getSelectFinder(Attention.class).append(" where userId=:userId and itemId=:itemId ");
					attenFinder.setParam("userId", Integer.parseInt(appuserId));
					attenFinder.setParam("itemId", joinActivity2.getUserId());
					List<Attention> attens = attentionService.findListDataByFinder(attenFinder, newPage, Attention.class, null);
					if(attens!=null&&attens.size()>0){
						joinActivity2.setIsAttr(1);
					}else {
						joinActivity2.setIsAttr(0);
					}
					
					
					//查询是否点赞
					Finder operFinder=Finder.getSelectFinder(Oper.class).append(" where userId=:userId and itemId=:itemId and type=6");
					operFinder.setParam("userId", Integer.parseInt(appuserId));
					operFinder.setParam("itemId", joinActivity2.getId());
					List<Oper> isOpers = operService.findListDataByFinder(operFinder, newPage, Oper.class, null);
					if(isOpers!=null&&isOpers.size()>0){
						joinActivity2.setIsOper(1);
					}else {
						joinActivity2.setIsOper(0);
					}
					
			}
			
			
			if(joinActivity2.getActivityId()!=null){
				
				Activity activity = activityService.findActivityById(joinActivity2.getActivityId());
				
				if(activity!=null){
					
					joinActivity2.setActivityUserId(activity.getUserId());
					
				}
				
			}
			
		}
		
		
		returnObject.setQueryBean(joinActivity);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param joinActivity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/adminList/json") 
	public @ResponseBody
	ReturnDatas adminListjson(HttpServletRequest request, Model model,JoinActivity joinActivity) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		Finder finder = Finder.getSelectFinder(JoinActivity.class).append(" where 1=1 ");
				
		List<JoinActivity> datas=joinActivityService.queryForList(finder, JoinActivity.class, page);		
		
		returnObject.setQueryBean(joinActivity);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,JoinActivity joinActivity) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = joinActivityService.findDataExportExcel(null,listurl, page,JoinActivity.class,joinActivity);
		String fileName="joinActivity"+GlobalStatic.excelext;
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
		return "/system/joinactivity/joinactivityLook";
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
		  JoinActivity joinActivity = joinActivityService.findJoinActivityById(id);
		   returnObject.setData(joinActivity);
		}else{
		returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;
		
	}
	
	
	/**
	 * 同城活动参与接口
	 * @author wj
	 * 
	 */
	@RequestMapping("/update/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas saveorupdate(Model model,JoinActivity joinActivity,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			joinActivityService.saveorupdate(joinActivity);
			
		} catch (HaveUserErrorException e) {
			logger.error("已参与");
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("已参与");
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
		return "/system/joinactivity/joinactivityCru";
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
				joinActivityService.deleteById(id,JoinActivity.class);
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
			joinActivityService.deleteByIds(ids,JoinActivity.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
