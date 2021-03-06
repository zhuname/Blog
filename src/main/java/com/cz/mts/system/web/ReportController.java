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

import com.cz.mts.system.entity.Activity;
import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Card;
import com.cz.mts.system.entity.Circle;
import com.cz.mts.system.entity.JoinActivity;
import com.cz.mts.system.entity.MediaPackage;
import com.cz.mts.system.entity.PosterPackage;
import com.cz.mts.system.entity.Report;
import com.cz.mts.system.service.IActivityService;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.ICardService;
import com.cz.mts.system.service.ICircleService;
import com.cz.mts.system.service.IJoinActivityService;
import com.cz.mts.system.service.IMediaPackageService;
import com.cz.mts.system.service.IPosterPackageService;
import com.cz.mts.system.service.IReportService;
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
 * @version  2017-07-07 14:31:44
 * @see com.cz.mts.system.web.Report
 */
@Controller
@RequestMapping(value="/system/report")
public class ReportController  extends BaseController {
	@Resource
	private IReportService reportService;
	@Resource
	private IAppUserService appUserService;
	@Resource
	private IJoinActivityService joinActivityService;
	@Resource
	private ICircleService circleService;
	@Resource
	private IPosterPackageService posterPackageService;
	@Resource
	private IMediaPackageService mediaPackageService;
	@Resource
	private ICardService cardService;
	@Resource
	private IActivityService activityService;
	
	private String listurl="/report/reportList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param report
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,Report report) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, report);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param report
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,Report report) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		
		Finder finder = Finder.getSelectFinder(Report.class).append(" where 1=1");
		if(StringUtils.isNotBlank(report.getContent())){
			finder.append(" and content like '%"+report.getContent()+"%'");
		}
		if(StringUtils.isNotBlank(report.getOperUserName())){
			finder.append(" and operUserId in (select id from t_app_user where name like '%"+report.getOperUserName()+"%')");
		}
		if(StringUtils.isNotBlank(report.getReportedUserName())){
			finder.append(" and reportedUserId in (select id from t_app_user where name like '%"+report.getReportedUserName()+"%')");
		}
		if(null != report.getType()){
			finder.append(" and type=:type");
			finder.setParam("type", report.getType());
		}
		
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<Report> datas=reportService.findListDataByFinder(finder,page,Report.class,null);
		if(null != datas && datas.size() > 0){
			for (Report rp : datas) {
				//举报人的名称
				if(null != rp.getOperUserId()){
					AppUser appUser = appUserService.findAppUserById(rp.getOperUserId());
					if(null != appUser && StringUtils.isNotBlank(appUser.getName())){
						rp.setOperUserName(appUser.getName());
					}
				}
				
				//被举报人的名称
				if(null != rp.getReportedUserId()){
					AppUser appUser = appUserService.findAppUserById(rp.getReportedUserId());
					if(null != appUser && StringUtils.isNotBlank(appUser.getName())){
						rp.setReportedUserName(appUser.getName());
					}
				}
				if(null != rp.getItemId()){
					if(null != rp.getType()){
						//1同城活动参与   2城事圈 3海报红包举报  4视频红包举报
						if(1 == rp.getType()){
							JoinActivity joinActivity = joinActivityService.findJoinActivityById(rp.getItemId());
							if(null != joinActivity && StringUtils.isNotBlank(joinActivity.getContent())){
								rp.setItemContent(joinActivity.getContent());
							}else{
								rp.setItemContent("暂无链接目标");
							}
						}
						
						if(2 == rp.getType()){
							Circle circle = circleService.findCircleById(rp.getItemId());
							if(null != circle && StringUtils.isNotBlank(circle.getContent())){
								rp.setItemContent(circle.getContent());
							}else{
								rp.setItemContent("暂无链接目标");
							}
						}
						if(3 == rp.getType()){
							PosterPackage posterPackage = posterPackageService.findPosterPackageById(rp.getItemId());
							if(null != posterPackage && StringUtils.isNotBlank(posterPackage.getTitle())){
								rp.setItemContent(posterPackage.getTitle());
							}else{
								rp.setItemContent("暂无链接目标");
							}
						}
						
						if(4 == rp.getType()){
							MediaPackage mediaPackage = mediaPackageService.findMediaPackageById(rp.getItemId());
							if(null != mediaPackage && StringUtils.isNotBlank(mediaPackage.getTitle())){
								rp.setItemContent(mediaPackage.getTitle());
							}else{
								rp.setItemContent("暂无链接目标");
							}
						}
						
						if(5 == rp.getType()){
							Card card = cardService.findCardById(rp.getItemId());
							if(null != card && StringUtils.isNotBlank(card.getTitle())){
								rp.setItemContent(card.getTitle());
							}else{
								rp.setItemContent("暂无链接目标");
							}
						}
						
						if(6 == rp.getType()){
							Activity activity = activityService.findActivityById(rp.getItemId());
							if(null != activity && StringUtils.isNotBlank(activity.getContent())){
								rp.setItemContent(activity.getContent());
							}else{
								rp.setItemContent("暂无链接目标");
							}
						}
					}
				}
				
				
			}
		}
		returnObject.setQueryBean(report);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,Report report) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = reportService.findDataExportExcel(null,listurl, page,Report.class,report);
		String fileName="report"+GlobalStatic.excelext;
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
		return "/system/report/reportLook";
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
		  Report report = reportService.findReportById(id);
		   returnObject.setData(report);
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
	ReturnDatas saveorupdate(Model model,Report report,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
			if(report.getOperUserId()==null||report.getItemId()==null||report.getContent()==null||report.getType()==null||report.getReportedUserId()==null){
				logger.error("参数缺失");
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("参数缺失");
				return returnObject;
			}
		
			report.setCreateTime(new Date());
			reportService.saveorupdate(report);
			
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
		return "/system/report/reportCru";
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
				reportService.deleteById(id,Report.class);
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
			reportService.deleteByIds(ids,Report.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
