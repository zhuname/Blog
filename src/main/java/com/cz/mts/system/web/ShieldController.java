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
import com.cz.mts.system.entity.Collect;
import com.cz.mts.system.entity.Shield;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.IShieldService;
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
 * @version  2017-07-05 15:58:36
 * @see com.cz.mts.system.web.Shield
 */
@Controller
@RequestMapping(value="/system/shield")
public class ShieldController  extends BaseController {
	@Resource
	private IShieldService shieldService;
	@Resource
	private IAppUserService appUserService;
	
	private String listurl="/shield/shieldList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param shield
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,Shield shield) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, shield);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param shield
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,Shield shield) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		Finder finder = Finder.getSelectFinder(Shield.class).append(" where 1=1");
		if(StringUtils.isNotBlank(shield.getUserName())){
			finder.append(" and userId in (select id from t_app_user where name like '%"+shield.getUserName()+"%')");
		}
		if(StringUtils.isNotBlank(shield.getItemName())){
			finder.append(" and itemId in (select id from t_app_user where name like '%"+shield.getItemName()+"%')");
		}
		List<Shield> datas=shieldService.findListDataByFinder(finder,page,Shield.class,null);
		if(null != datas && datas.size() > 0){
			for (Shield sl : datas) {
				if(null != sl.getUserId()){
					AppUser appUser = appUserService.findAppUserById(sl.getUserId());
					if(null != appUser && StringUtils.isNotBlank(appUser.getName())){
						sl.setUserName(appUser.getName());
					}
				}
				if(null != sl.getItemId()){
					AppUser itemAppUser = appUserService.findAppUserById(sl.getItemId());
					if(null != itemAppUser && StringUtils.isNotBlank(itemAppUser.getName())){
						sl.setItemName(itemAppUser.getName());
					}
				}
			}
		}
		returnObject.setQueryBean(shield);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,Shield shield) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = shieldService.findDataExportExcel(null,listurl, page,Shield.class,shield);
		String fileName="shield"+GlobalStatic.excelext;
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
		return "/system/shield/shieldLook";
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
		  Shield shield = shieldService.findShieldById(id);
		   returnObject.setData(shield);
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
	ReturnDatas saveorupdate(Model model,Shield shield,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
			if(shield.getItemId()!=null&&shield.getUserId()!=null){
				Page page=new Page();
				// ==执行分页查询
				List<Shield> datas=shieldService.findListDataByFinder(null,page,Shield.class,shield);
				if(datas.size()>0){
					for (Shield shieldD : datas) {
						shieldService.deleteByEntity(shieldD);
					}
					returnObject.setData(0);
				}else {
					shield.setCreateTime(new Date());
					shieldService.saveorupdate(shield);
					returnObject.setData(1);
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
		return "/system/shield/shieldCru";
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
				shieldService.deleteById(id,Shield.class);
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
			shieldService.deleteByIds(ids,Shield.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
