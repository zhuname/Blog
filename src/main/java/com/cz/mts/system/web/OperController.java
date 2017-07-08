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
import com.cz.mts.system.entity.Circle;
import com.cz.mts.system.entity.JoinActivity;
import com.cz.mts.system.entity.MediaPackage;
import com.cz.mts.system.entity.Oper;
import com.cz.mts.system.entity.PosterPackage;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.ICircleService;
import com.cz.mts.system.service.IJoinActivityService;
import com.cz.mts.system.service.IMediaPackageService;
import com.cz.mts.system.service.IOperService;
import com.cz.mts.system.service.IPosterPackageService;
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
 * @version  2017-07-05 15:15:34
 * @see com.cz.mts.system.web.Oper
 */
@Controller
@RequestMapping(value="/system/oper")
public class OperController  extends BaseController {
	@Resource
	private IOperService operService;
	@Resource
	private IAppUserService appUserService;
	@Resource
	private IPosterPackageService posterPackageService;
	@Resource
	private IMediaPackageService mediaPackageService;
	@Resource
	private ICircleService circleService;
	@Resource
	private IJoinActivityService joinActivityService;
	
	
	private String listurl="/system/oper/operList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param oper
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,Oper oper) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, oper);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * 点赞/评论列表
	 * @author wj
	 * @param request
	 * @param model
	 * @param oper
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,Oper oper) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		page.setPageSize(100000);
		// ==执行分页查询
		List<Oper> datas=operService.findListDataByFinder(null,page,Oper.class,oper);
		if(null != datas && datas.size() > 0){
			for (Oper op : datas) {
				if(null != op.getUserId()){
					AppUser appUser = appUserService.findAppUserById(op.getUserId());
					if(null != appUser){
						op.setAppUser(appUser);
					}
				}
			}
		}
		returnObject.setQueryBean(oper);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,Oper oper) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = operService.findDataExportExcel(null,listurl, page,Oper.class,oper);
		String fileName="oper"+GlobalStatic.excelext;
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
		return "/system/oper/operLook";
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
		  Oper oper = operService.findOperById(id);
		   returnObject.setData(oper);
		}else{
		returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;
		
	}
	
	
	/**
	 * 操作接口（点赞/评论）
	 * @author wj
	 * 
	 */
	@RequestMapping("/update/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas saveorupdate(Model model,Oper oper,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if(null == oper.getUserId() || null == oper.getItemId() || null == oper.getType()){
				returnObject.setMessage("参数缺失");
				returnObject.setStatus(ReturnDatas.ERROR);
			}else{
				oper.setCreateTime(new Date());
				Integer type = oper.getType();
				PosterPackage posterPackage = null;
				MediaPackage mediaPackage = null;
				Circle circle = null;
				JoinActivity joinActivity = null;
				if(1 == type || 2 == type){
					posterPackage = posterPackageService.findPosterPackageById(oper.getItemId());
				}
				if(3 == type || 4 == type){
					mediaPackage = mediaPackageService.findMediaPackageById(oper.getItemId());
				}
				if(5 == type || 6 == type){
					joinActivity = joinActivityService.findJoinActivityById(oper.getItemId());
				}
				if(7 == type || 8 == type){
					circle = circleService.findCircleById(oper.getItemId());
				}
				//更新相应的表的点赞次数和评论次数 //1海报点赞  2海报评论 3视频点赞  4视频评论 5同城活动参与评论 6同城活动参与点赞 7同城圈点赞 8同城圈评论
				switch (type) {
				case 1:
					if(null != posterPackage){
						if(null == posterPackage.getTopCount()){
							posterPackage.setTopCount(0);
						}
						posterPackage.setTopCount(posterPackage.getTopCount() + 1);
						posterPackageService.update(posterPackage,true);
					}
					break;

				case 2:
					if(null != posterPackage){
						if(null == posterPackage.getCommentCount()){
							posterPackage.setCommentCount(0);
						}
						posterPackage.setCommentCount(posterPackage.getCommentCount() + 1);
						posterPackageService.update(posterPackage,true);
					}
					break;
				case 3:
					if(null != mediaPackage){
						if(null == mediaPackage.getTopCount()){
							mediaPackage.setTopCount(0);
						}
						mediaPackage.setTopCount(mediaPackage.getTopCount() + 1);
						mediaPackageService.update(mediaPackage,true);
					}
					break;
				case 4:
					if(null != mediaPackage){
						if(null == mediaPackage.getCommentCount()){
							mediaPackage.setCommentCount(0);
						}
						mediaPackage.setCommentCount(mediaPackage.getCommentCount() + 1);
						mediaPackageService.update(mediaPackage,true);
					}
					break;
				case 5:
					if(null != joinActivity){
						if(null == joinActivity.getCommentCount()){
							joinActivity.setCommentCount(0);
						}
						joinActivity.setCommentCount(joinActivity.getCommentCount() + 1);
						joinActivityService.update(joinActivity,true);
					}
					break;
				case 6:
					if(null != joinActivity){
						if(null == joinActivity.getTopCount()){
							joinActivity.setTopCount(0);
						}
						joinActivity.setTopCount(joinActivity.getTopCount() + 1);
						joinActivityService.update(joinActivity,true);
					}
					break;
				case 7:
					if(null != circle){
						if(null == circle.getTopCount()){
							circle.setTopCount(0);
						}
						circle.setTopCount(circle.getTopCount() + 1);
						circleService.update(circle,true);
					}
					break;
				case 8:
					if(null != circle){
						if(null == circle.getCommentCount()){
							circle.setCommentCount(0);
						}
						circle.setCommentCount(circle.getCommentCount() + 1);
						circleService.update(circle,true);
					}
					break;
				}
				operService.saveorupdate(oper);
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
		return "/system/oper/operCru";
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
				operService.deleteById(id,Oper.class);
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
			operService.deleteByIds(ids,Oper.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
