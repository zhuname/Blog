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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.ApplyMedal;
import com.cz.mts.system.entity.Medal;
import com.cz.mts.system.entity.UserMedal;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.IApplyMedalService;
import com.cz.mts.system.service.IMedalService;
import com.cz.mts.system.service.IUserMedalService;
import com.cz.mts.system.service.NotificationService;
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
 * @version  2017-02-24 15:17:24
 * @see com.cz.mts.system.web.ApplyMedal
 */
@Controller
@RequestMapping(value="/system/applymedal")
public class ApplyMedalController  extends BaseController {
	@Resource
	private IApplyMedalService applyMedalService;
	@Resource
	private IUserMedalService userMedalService;
	@Resource
	private IAppUserService appUserService;
	@Resource
	private IMedalService medalService;
	@Resource
	private NotificationService notificationService;
	
	
	private String listurl="/applymedal/applymedalList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param applyMedal
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,ApplyMedal applyMedal) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, applyMedal);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param applyMedal
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,ApplyMedal applyMedal) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		
		Finder finder=Finder.getSelectFinder(applyMedal).append(" where 1=1");
		
		if(StringUtils.isNotBlank(applyMedal.getUserName())){
			finder.append(" and userId in (select id from t_app_user where name like '%"+applyMedal.getUserName()+"%')");
		}
		
		if(StringUtils.isNotBlank(applyMedal.getStartTime())){
			finder.append(" and applyTime > :startTime ");
			finder.setParam("startTime", applyMedal.getStartTime());
		}
		
		if(StringUtils.isNotBlank(applyMedal.getEndTime())){
			finder.append(" and applyTime < :endTime ");
			finder.setParam("endTime", applyMedal.getEndTime());
		}
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<ApplyMedal> datas=applyMedalService.findListDataByFinder(finder,page,ApplyMedal.class,applyMedal);
		if(null != datas && datas.size() > 0){
			for (ApplyMedal am : datas) {
				if(null != am.getUserId()){
					AppUser appUser = appUserService.findAppUserById(am.getUserId());
					if(null != appUser && StringUtils.isNotBlank(appUser.getName())){
						am.setUserName(appUser.getName());
					}
				}
				
				if(null != am.getMedalId()){
					Medal medal = medalService.findMedalById(am.getMedalId());
					if(null != medal){
						if(StringUtils.isNotBlank(medal.getName())){
							am.setMedalName(medal.getName());
						}
						if(null != medal.getStatus()){
							am.setMedalStatus(medal.getStatus());
						}
					}
				}
			}
		}
		returnObject.setQueryBean(applyMedal);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,ApplyMedal applyMedal) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = applyMedalService.findDataExportExcel(null,listurl, page,ApplyMedal.class,applyMedal);
		String fileName="applyMedal"+GlobalStatic.excelext;
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
		return "/system/applymedal/applymedalLook";
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
		  ApplyMedal applyMedal = applyMedalService.findApplyMedalById(id);
		   returnObject.setData(applyMedal);
		}else{
		returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;
		
	}
	
	
	/**
	 * 申请勋章认证接口
	 * @author wj
	 * @param model
	 * @param applyMedal
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/update/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas saveorupdatejson(Model model,ApplyMedal applyMedal,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if(null == applyMedal){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("参数缺失");
			}else{
				//首先判断该用户是否已经认证通过并且没有过期
				Finder successFinder = Finder.getSelectFinder(ApplyMedal.class).append(" where 1=1 and status=2 and isEndStatus=0 and medalId=:medalId");
				successFinder.setParam("medalId", applyMedal.getMedalId());
				List successList = applyMedalService.queryForList(successFinder);
				if(null != successList && successList.size() > 0){
					returnObject.setMessage("您已经拥有该勋章，暂不能申请");
					returnObject.setStatus(ReturnDatas.ERROR);
				}else{
					//判断该用户是否有申请中的勋章
					Finder applyFinder = Finder.getSelectFinder(ApplyMedal.class).append(" where 1=1 and status=1 and isEndStatus=0 and medalId=:medalId"); 
					applyFinder.setParam("medalId", applyMedal.getMedalId());
					List applyList = applyMedalService.queryForList(applyFinder);
					if(null != applyList && applyList.size() > 0){
						returnObject.setMessage("您申请的勋章正在认证中，暂不能申请");
						returnObject.setStatus(ReturnDatas.ERROR);
					}else{
						if(null == applyMedal.getId()){
							applyMedal.setApplyTime(new Date());
							applyMedal.setStatus(1);
							applyMedal.setIsEndStatus(0);
							applyMedalService.saveorupdate(applyMedal);
						}else{
							applyMedalService.update(applyMedal,true);
						}
					}
					
				}
				
				
				
				
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
		return "/system/applymedal/applymedalCru";
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
				applyMedalService.deleteById(id,ApplyMedal.class);
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
			applyMedalService.deleteByIds(ids,ApplyMedal.class);
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
	ReturnDatas checkjson(Model model,ApplyMedal applyMedal,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if(null != applyMedal.getId()){
				applyMedal.setStatus(2);
				applyMedal.setOperTime(new Date());
				applyMedal.setEndMedalTime(applyMedal.getEndMedalTime());
				applyMedalService.update(applyMedal,true);
				
				
				UserMedal userMedal=new UserMedal();
				ApplyMedal applyMedal2=applyMedalService.findApplyMedalById(applyMedal.getId());
				
				if(applyMedal2!=null){
					userMedal.setMedalId(applyMedal2.getMedalId());
					userMedal.setUserId(applyMedal2.getUserId());
					userMedal.setCreateTime(new Date());
					userMedal.setEndMedalTime(applyMedal2.getEndMedalTime());
					userMedal.setIsEndStatus(applyMedal2.getIsEndStatus());
					Medal medal = medalService.findMedalById(userMedal.getMedalId());
					if(null != medal && StringUtils.isNotBlank(medal.getName())){
						notificationService.notify(7, userMedal.getMedalId(), userMedal.getUserId(), medal.getName());
					}
					
				}
				
				userMedalService.save(userMedal);
				
				
				
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
	ReturnDatas failjson(Model model,ApplyMedal applyMedal,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if(null != applyMedal.getId()){
				applyMedal.setStatus(3);
				applyMedal.setOperTime(new Date());
				applyMedalService.update(applyMedal,true);
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
	

}
