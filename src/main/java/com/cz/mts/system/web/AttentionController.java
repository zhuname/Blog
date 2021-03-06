package  com.cz.mts.system.web;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.cz.mts.system.entity.Collect;
import com.cz.mts.system.entity.LunboPic;
import com.cz.mts.system.entity.Medal;
import com.cz.mts.system.entity.UserMedal;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.IAttentionService;
import com.cz.mts.system.service.IMedalService;
import com.cz.mts.system.service.IUserMedalService;
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
 * @see com.cz.mts.system.web.Attention
 */
@Controller
@RequestMapping(value="/system/attention")
public class AttentionController  extends BaseController {
	@Resource
	private IAttentionService attentionService;
	@Resource
	private IAppUserService appUserService;
	@Resource
	private IUserMedalService userMedalService;
	@Resource
	private IMedalService medalService;
	
	private String listurl="/attention/attentionList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param attention
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,Attention attention) 
			throws Exception {
		ReturnDatas returnObject = fensiListjson(request, model, attention);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * 获取关注人列表
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param attention
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,Attention attention) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		/*// ==执行分页查询
		List<Attention> datas=attentionService.findListDataByFinder(null,page,Attention.class,attention);*/
		if(attention.getUserId()!=null){
			//更新appUser表中的isUpdate字段
			Finder fd2 = new Finder("UPDATE t_app_user SET isUpdate = 0 WHERE id in (SELECT userId FROM t_attention WHERE userId=:userId)");
			fd2.setParam("userId", attention.getUserId());
			appUserService.update(fd2);
			
			Finder finder=new Finder("SELECT *,att.isUpdate as isUpdate2 FROM t_app_user au LEFT JOIN t_attention att ON att.itemId = au.id WHERE att.userId= :id ");
			finder.setParam("id", attention.getUserId());
			
			page.setOrder("att.id");
			page.setSort("desc");
			
			List<Map<String, Object>> list = appUserService.queryForList(finder,page);
			if(null != list && list.size() > 0){
				for (Map<String, Object> map : list) {
					//返回勋章列表
					Finder finder2 = new Finder("SELECT * FROM t_user_medal WHERE userId=:userId and isEndStatus = 0");
					finder2.setParam("userId", Integer.parseInt(map.get("itemId").toString()));
					List<UserMedal> userMedals = userMedalService.queryForList(finder2,UserMedal.class);
					if(null != userMedals && userMedals.size() > 0){
						for (UserMedal userMedal : userMedals) {
							Medal medal = medalService.findMedalById(userMedal.getMedalId());
							if(null != medal){
								userMedal.setMedal(medal);
							}
						}
						map.put("userMedals", userMedals);
					}
					//查询是否关注
//					Finder attenFinder=Finder.getSelectFinder(Attention.class).append(" where userId=:userId and itemId=:itemId ");
//					attenFinder.setParam("userId", attention.getUserId());
//					attenFinder.setParam("itemId", Integer.parseInt(map.get("id").toString()));
//					List<Attention> attens = attentionService.findListDataByFinder(attenFinder, page, Attention.class, null);
//					if(attens!=null&&attens.size()>0){
//						map.put("isAttr", 1);
//					}else {
//						map.put("isAttr", 0);
//					}
				} 
				
			}
			
			returnObject.setData(list);
		}else {
			returnObject.setMessage("参数缺失");
		}
		
		returnObject.setQueryBean(attention);
		returnObject.setPage(page);
		return returnObject;
	}
	
	
	
	/**
	 * 获取关注人列表
	 * json数据,为APP提供数据
	 * @param request
	 * @param model
	 * @param attention
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/fensiList/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas fensiListjson(HttpServletRequest request, Model model,Attention attention) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		
		Finder finder = Finder.getSelectFinder(Attention.class).append(" where 1=1 ");
		
		if(attention.getAppUser()!=null){
			
			if(StringUtils.isNotBlank(attention.getAppUser().getName())){
				
				finder.append(" and userId in (select id from t_app_user where name=:name)");
				finder.setParam("name", attention.getAppUser().getName());
				
			}
			
		}
		
		if(attention.getItemUser()!=null){
			
			if(StringUtils.isNotBlank(attention.getItemUser().getName())){
				
				finder.append(" and itemId in (select id from t_app_user where name=:itemName)");
				finder.setParam("itemName", attention.getItemUser().getName());
				
			}
			
		}
		
		// ==执行分页查询
		List<Attention> datas=attentionService.findListDataByFinder(finder,page,Attention.class,attention);
		
		if(null != datas && datas.size() > 0){
			for (Attention attention2 : datas){
				
				//查询用户信息
				if(attention2.getUserId()!=null){
					
					AppUser appUser=appUserService.findAppUserById(attention2.getUserId());
					
					if(appUser!=null){
						
						attention2.setAppUser(appUser);
						
					}
					
					
					//是否关注
					Finder fd = Finder.getSelectFinder(Attention.class).append(" where userId=:itemId and itemId =:userId");
					fd.setParam("itemId", attention.getItemId());
					fd.setParam("userId", attention2.getUserId());
					List list = attentionService.queryForList(fd);
					if(null != list && list.size() > 0){
						attention2.setIsAttr(1);
					}else{
						attention2.setIsAttr(0);
					}
					
					
				}
				
				
				//查询用户信息
				if(attention2.getItemId()!=null){
					
					AppUser appUser=appUserService.findAppUserById(attention2.getItemId());
					
					if(appUser!=null){
						
						attention2.setItemUser(appUser);
						
					}
					
				}
				
			}
		}
		
		
		returnObject.setData(datas);
		returnObject.setQueryBean(attention);
		returnObject.setPage(page);
		return returnObject;
	}
	
	
	
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,Attention attention) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = attentionService.findDataExportExcel(null,listurl, page,Attention.class,attention);
		String fileName="attention"+GlobalStatic.excelext;
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
		return "/system/attention/attentionLook";
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
		  Attention attention = attentionService.findAttentionById(id);
		   returnObject.setData(attention);
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
	ReturnDatas saveorupdatejson(Model model,Attention attention,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			Page page=new Page();
			// ==执行分页查询
			List<Attention> datas=attentionService.findListDataByFinder(null,page,Attention.class,attention);
			
			if(datas.size()>0){
				//删除所有关注的
				for (Attention AttentionD : datas) {
					attentionService.deleteByEntity(AttentionD);
				}
				returnObject.setData(0);
				//更新app_user表的数据
				Finder finder = new Finder("UPDATE t_app_user SET attenedCount = attenedCount - 1 WHERE id=:userId");
				finder.setParam("userId", attention.getItemId());
				appUserService.update(finder);
			}else {
				attention.setIsUpdate(0);
				attentionService.saveorupdate(attention);
				returnObject.setData(1);
				//更新app_user表的数据
				Finder finder = new Finder("UPDATE t_app_user SET attenedCount = attenedCount + 1 WHERE id=:userId");
				finder.setParam("userId", attention.getItemId());
				appUserService.update(finder);
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
		return "/system/attention/attentionCru";
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
				attentionService.deleteById(id,Attention.class);
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
			attentionService.deleteByIds(ids,Attention.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}
	
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param collect
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/atten/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas attenjson(HttpServletRequest request, Model model,Attention attention) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<Attention> datas=attentionService.findListDataByFinder(null,page,Attention.class,attention);
		if(datas.size()>0){
			returnObject.setData(1);
		} else {
			returnObject.setData(0);
		}
		returnObject.setPage(page);
		return returnObject;
	}
	
	

}
