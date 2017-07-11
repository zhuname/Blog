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
import com.cz.mts.system.entity.GiveAward;
import com.cz.mts.system.entity.JoinActivity;
import com.cz.mts.system.service.IActivityService;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.IAwardsService;
import com.cz.mts.system.service.IGiveAwardService;
import com.cz.mts.system.service.IJoinActivityService;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-07-03 16:07:00
 * @see com.cz.mts.system.web.GiveAward
 */
@Controller
@RequestMapping(value="/system/giveaward")
public class GiveAwardController  extends BaseController {
	@Resource
	private IGiveAwardService giveAwardService;
	@Resource
	private IJoinActivityService joinActivityService;
	@Resource
	private IActivityService activityService;
	@Resource
	private IAppUserService appUserService;
	@Resource
	private IAwardsService awardsService;
	
	private String listurl="/system/giveaward/giveawardList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param giveAward
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,GiveAward giveAward) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, giveAward);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param giveAward
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,GiveAward giveAward) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		
		String activityId=request.getParameter("activityId");
		
		Finder finder = Finder.getSelectFinder(GiveAward.class).append(" where 1=1 ");
		
		if(StringUtils.isNotBlank(activityId)){
			finder.append(" and awardId in (select id from t_awards where activityId=:activityId )");
			finder.setParam("activityId", Integer.parseInt(activityId));
		}
		
		List<GiveAward> datas=giveAwardService.findListDataByFinder(finder,page,GiveAward.class,giveAward);
		
		for (GiveAward giveAward2 : datas) {
			
				if(giveAward2.getUserId()!=null){
				
				AppUser appUser=appUserService.findAppUserById(giveAward2.getUserId());
				
				if(appUser!=null){
					
					giveAward2.setAppUser(appUser);
					
				}
				
				if(giveAward2.getAwardId()!=null){
					
					Awards awards=awardsService.findAwardsById(giveAward2.getAwardId());
					
					if(awards!=null){
						
						giveAward2.setAwards(awards);
						
					}
					
				}
				
				
			}
			
		}
		
		
		returnObject.setQueryBean(giveAward);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,GiveAward giveAward) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = giveAwardService.findDataExportExcel(null,listurl, page,GiveAward.class,giveAward);
		String fileName="giveAward"+GlobalStatic.excelext;
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
		return "/system/giveaward/giveawardLook";
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
		  GiveAward giveAward = giveAwardService.findGiveAwardById(id);
		   returnObject.setData(giveAward);
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
	ReturnDatas saveorupdate(Model model,GiveAward giveAward,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
			if(giveAward.getUserId()!=null&&giveAward.getJoinUserId()!=null&&giveAward.getAwardId()!=null){
				
				 Finder finder = Finder.getSelectFinder(GiveAward.class).append(" where userId=:userId and awardId=:awardId and joinUserId=:joinUserId");
				  finder.setParam("userId", giveAward.getUserId());
				  finder.setParam("awardId", giveAward.getAwardId());
				  finder.setParam("joinUserId", giveAward.getJoinUserId());
				  List<GiveAward> giveAwards = giveAwardService.queryForList(finder, GiveAward.class);
				  
				  
				  //判断是否已经有获奖的记录了
				  if(giveAwards!=null&&giveAwards.size()>0){
					    logger.error("已获奖");
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("已获奖");
				  }else{

						Awards awards = awardsService.findAwardsById(giveAward.getAwardId());
						
						if(awards!=null){
							
							
							//更新同城活动参与表中该用户的奖项id
							Finder joinFinder = new Finder("select * from t_join_activity where userId=:userId and activityId = :activityId");
							joinFinder.setParam("userId", giveAward.getJoinUserId());
							joinFinder.setParam("activityId", awards.getActivityId());
							List<JoinActivity> joinActivities = joinActivityService.queryForList(joinFinder, JoinActivity.class);
							if(null != joinActivities && joinActivities.size() > 0){
								for (JoinActivity joinActivity : joinActivities) {
									joinActivity.setAwardId(giveAward.getAwardId());
									joinActivityService.update(joinActivity,true);
								}
							}
							
							
							Activity activity=activityService.findActivityById(awards.getActivityId());
							
							if(activity!=null){
								
								activity.setWinPrizePerson(activity.getWinPrizePerson()+1);
								activityService.update(activity, true);
							}
							
							awards.setRemainCount(awards.getRemainCount()-1);
							awardsService.update(awards, true);
							
						}
						
						giveAwardService.saveorupdate(giveAward);
						
				  }
				
			}else {
				logger.error("参数缺失");
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("参数缺失");
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
		return "/system/giveaward/giveawardCru";
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
				giveAwardService.deleteById(id,GiveAward.class);
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
			giveAwardService.deleteByIds(ids,GiveAward.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
