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

import com.cz.mts.system.entity.Activity;
import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Attention;
import com.cz.mts.system.entity.Circle;
import com.cz.mts.system.entity.Collect;
import com.cz.mts.system.entity.Oper;
import com.cz.mts.system.entity.Shield;
import com.cz.mts.system.exception.ParameterErrorException;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.IAttentionService;
import com.cz.mts.system.service.ICircleService;
import com.cz.mts.system.service.ICollectService;
import com.cz.mts.system.service.IOperService;
import com.cz.mts.system.service.IShieldService;
import com.cz.mts.frame.annotation.SecurityApi;
import com.cz.mts.frame.controller.BaseController;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.MessageUtils;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Circle;
import com.cz.mts.system.exception.ParameterErrorException;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.ICircleService;
import com.cz.mts.system.service.IShieldService;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-07-05 15:32:31
 * @see com.cz.mts.system.web.Circle
 */
@Controller
@RequestMapping(value="/system/circle")
public class CircleController  extends BaseController {
	@Resource
	private ICircleService circleService;
	@Resource
	private IAppUserService appUserService;
	@Resource
	private IShieldService shieldService;
	@Resource
	private ICollectService collectService;
	@Resource
	private IAttentionService attentionService;
	@Resource
	private IOperService operService;
	
	
	private String listurl="/system/circle/circleList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param circle
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,Circle circle) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, circle);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param circle
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,Circle circle) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		page.setOrder("id");
		page.setSort("desc");
		// ==执行分页查询
		String selectTitle = request.getParameter("selectTitle");
		
		String appuserId = request.getParameter("appuserId");
		
		String sort = request.getParameter("sort");
		
		Finder finder =Finder.getSelectFinder(Circle.class).append(" where 1=1 ");
		
		//搜索昵称/标题
		if(StringUtils.isNotBlank(selectTitle)){
			finder.append(" and (content like :selectTitle or userId in (select id from t_app_user where name like :selectTitle))");
			finder.setParam("selectTitle", selectTitle);
		}
		
		if(StringUtils.isNotBlank(sort)){
			
			//1最新发布 2关注的 3屏蔽的
			switch (sort) {
			case "1":
				page.setOrder("id");
				page.setSort("desc");
				
				if(StringUtils.isNotBlank(appuserId)){
					finder.append(" and id not In (select itemId from t_shield where userId=:userId)");
					finder.setParam("userId", Integer.parseInt(appuserId));
				}
				break;
			case "2":
				if(StringUtils.isNotBlank(appuserId)){
					finder.append(" and userId In (select itemId from t_attention where userId=:userId)");
					finder.setParam("userId", Integer.parseInt(appuserId));
				}
				break;
			case "3":
				if(StringUtils.isNotBlank(appuserId)){
					finder.append(" and id In (select itemId from t_shield where userId=:userId)");
					finder.setParam("userId", Integer.parseInt(appuserId));
				}
				break;
			}
		}
		
		List<Circle> datas=circleService.findListDataByFinder(finder,page,Circle.class,circle);
		
		//刷新同城圈儿事件
		if(StringUtils.isNotBlank(appuserId)){
			
			AppUser appUser = appUserService.findAppUserById(Integer.parseInt(appuserId));
			if(appUser!=null){
				
				appUser.setCircleScanTime(new Date());
				appUserService.update(appUser, true);
				
			}
			
		}
		
		for (Circle circle2 : datas) {
			
			if(circle2.getUserId()!=null){
				  AppUser appUser=appUserService.findAppUserById(circle2.getUserId());
				  if(appUser!=null){
					  circle2.setAppUser(appUser);
				  }
			  }
			
			
			if(StringUtils.isNotBlank(appuserId)){
				
				//查询是否关注
				Finder operFinder=Finder.getSelectFinder(Attention.class).append(" where type=6 and userId=:userId and itemId=:itemId ");
				operFinder.setParam("userId", Integer.parseInt(appuserId));
				operFinder.setParam("itemId", circle2.getId());
				List<Oper> opers = operService.findListDataByFinder(operFinder, page, Oper.class, null);
				if(opers!=null&&opers.size()>0){
					circle2.setIsOper(1);
				}else {
					circle2.setIsOper(0);
				}
				
				//查询是否关注
				Finder shieldFinder=Finder.getSelectFinder(Shield.class).append(" where userId=:userId and itemId=:itemId ");
				shieldFinder.setParam("userId", Integer.parseInt(appuserId));
				shieldFinder.setParam("itemId", circle2.getId());
				List<Shield> shields = operService.findListDataByFinder(shieldFinder, page, Shield.class, null);
				if(shields!=null&&shields.size()>0){
					circle2.setIsShield(1);
				}else {
					circle2.setIsShield(0);
				}
				
				//查询是否关注
				Finder attenFinder=Finder.getSelectFinder(Attention.class).append(" where userId=:userId and itemId=:itemId ");
				attenFinder.setParam("userId", Integer.parseInt(appuserId));
				attenFinder.setParam("itemId", circle2.getUserId());
				List<Attention> attens = attentionService.findListDataByFinder(attenFinder, page, Attention.class, null);
				if(attens!=null&&attens.size()>0){
					circle2.setIsAttr(1);
				}else {
					circle2.setIsAttr(0);
				}
			
			}
			
		}
		
		returnObject.setQueryBean(circle);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,Circle circle) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = circleService.findDataExportExcel(null,listurl, page,Circle.class,circle);
		String fileName="circle"+GlobalStatic.excelext;
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
		return "/system/circle/circleLook";
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
		  String  appuserId=request.getParameter("appuserId");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
		  Circle circle = circleService.findCircleById(id);
		  
		  if(circle!=null&&circle.getUserId()!=null){
			  AppUser appUser=appUserService.findAppUserById(circle.getUserId());
			  if(appUser!=null){
				  circle.setAppUser(appUser);
			  }
			  
			  
			  if(StringUtils.isNotBlank(appuserId)){
					
					//查询是否关注
				  	Page page = newPage(request);
					Finder operFinder=Finder.getSelectFinder(Oper.class).append(" where type=6 and userId=:userId and itemId=:itemId ");
					operFinder.setParam("userId", Integer.parseInt(appuserId));
					operFinder.setParam("itemId", circle.getId());
					List<Oper> opers = operService.findListDataByFinder(operFinder, page, Oper.class, null);
					if(opers!=null&&opers.size()>0){
						circle.setIsOper(1);
					}else {
						circle.setIsOper(0);
					}
					
					
					//查询是否关注
					Finder shieldFinder=Finder.getSelectFinder(Shield.class).append(" where userId=:userId and itemId=:itemId ");
					shieldFinder.setParam("userId", Integer.parseInt(appuserId));
					shieldFinder.setParam("itemId", circle.getId());
					List<Shield> shields = operService.findListDataByFinder(shieldFinder, page, Shield.class, null);
					if(shields!=null&&shields.size()>0){
						circle.setIsShield(1);
					}else {
						circle.setIsShield(0);
					}
					
					//查询是否关注
					Finder attenFinder=Finder.getSelectFinder(Attention.class).append(" where userId=:userId and itemId=:itemId ");
					attenFinder.setParam("userId", Integer.parseInt(appuserId));
					attenFinder.setParam("itemId", circle.getUserId());
					List<Attention> attens = attentionService.findListDataByFinder(attenFinder, page, Attention.class, null);
					if(attens!=null&&attens.size()>0){
						circle.setIsAttr(1);
					}else {
						circle.setIsAttr(0);
					}
				
				}
		  }
		returnObject.setData(circle);
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
	ReturnDatas saveorupdate(Model model,Circle circle,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
			circleService.saveorupdate(circle);
			
		} catch (ParameterErrorException e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
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
		return "/system/circle/circleCru";
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
				circleService.deleteById(id,Circle.class);
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
			circleService.deleteByIds(ids,Circle.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
