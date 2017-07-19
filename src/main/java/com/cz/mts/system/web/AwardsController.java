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
import com.cz.mts.system.entity.Awards;
import com.cz.mts.system.service.IActivityService;
import com.cz.mts.system.service.IAwardsService;
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
 * @version  2017-07-03 16:07:17
 * @see com.cz.mts.system.web.Awards
 */
@Controller
@RequestMapping(value="/system/awards")
public class AwardsController  extends BaseController {
	@Resource
	private IAwardsService awardsService;
	@Resource
	private IActivityService activityService;
	
	private String listurl="/awards/awardsList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param awards
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,Awards awards) 
			throws Exception {
		ReturnDatas returnObject = adminListjson(request, model, awards);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * 后台奖项列表
	 * @param request
	 * @param model
	 * @param awards
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/adminList/json")
	public @ResponseBody
	ReturnDatas adminListjson(HttpServletRequest request, Model model,Awards awards) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		Finder finder = Finder.getSelectFinder(Awards.class).append(" where 1=1");
		if(StringUtils.isNotBlank(awards.getActivityName())){
			finder.append(" and activityId in (select id from t_activity where content like '%"+awards.getActivityName()+"%')");
		}
		if(StringUtils.isNotBlank(awards.getTitle())){
			finder.append(" and title like '%"+awards.getTitle()+"%'");
		}
		if(StringUtils.isNotBlank(awards.getContent())){
			finder.append(" and content like '%"+awards.getContent()+"%'");
		}
		// ==执行分页查询
		List<Awards> datas=awardsService.findListDataByFinder(finder,page,Awards.class,null);
		if(null != datas && datas.size() > 0){
			for (Awards ad : datas) {
				if(null != ad.getActivityId()){
					Activity activity = activityService.findActivityById(ad.getActivityId());
					if(null != activity && StringUtils.isNotBlank(activity.getContent())){
						ad.setActivityName(activity.getContent());
					}
				}
			}
		}
		returnObject.setQueryBean(awards);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param awards
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,Awards awards) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<Awards> datas=awardsService.findListDataByFinder(null,page,Awards.class,awards);
		returnObject.setQueryBean(awards);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,Awards awards) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = awardsService.findDataExportExcel(null,listurl, page,Awards.class,awards);
		String fileName="awards"+GlobalStatic.excelext;
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
		return "/system/awards/awardsLook";
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
		  Awards awards = awardsService.findAwardsById(id);
		   returnObject.setData(awards);
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
	ReturnDatas saveorupdate(Model model,Awards awards,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if(null == awards.getId()){
				awards.setRemainCount(awards.getSumCount());
				//查询同城活动
				Activity activity = activityService.findActivityById(awards.getActivityId());
				if(null != activity){
					if(null == activity.getSumWinPrizePerson()){
						activity.setSumWinPrizePerson(0);
					}
					activity.setSumWinPrizePerson(activity.getSumWinPrizePerson()+awards.getSumCount());
					activityService.update(activity,true);
				}
				awardsService.saveorupdate(awards);
			}else{
				awardsService.update(awards,true);
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
		return "/awards/awardsCru";
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
				awardsService.deleteById(id,Awards.class);
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
			awardsService.deleteByIds(ids,Awards.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
