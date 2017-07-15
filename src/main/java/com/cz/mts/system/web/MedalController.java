package  com.cz.mts.system.web;

import java.io.File;
import java.util.ArrayList;
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

import com.cz.mts.system.entity.ApplyMedal;
import com.cz.mts.system.entity.Medal;
import com.cz.mts.system.entity.UserMedal;
import com.cz.mts.system.service.IApplyMedalService;
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
 * @version  2017-02-24 15:17:26
 * @see com.cz.mts.system.web.Medal
 */
@Controller
@RequestMapping(value="/system/medal")
public class MedalController  extends BaseController {
	@Resource
	private IMedalService medalService;
	@Resource
	private IApplyMedalService applyMedalService;
	@Resource
	private IUserMedalService userMedalService;
	
	private String listurl="/medal/medalList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param medal
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,Medal medal) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, medal);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param medal
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,Medal medal) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		page.setPageSize(10000);
		// ==执行分页查询
		Finder finder = Finder.getSelectFinder(Medal.class).append(" where 1=1 ");
		if(StringUtils.isNotBlank(medal.getName())){
			finder.append(" and name like '%"+medal.getName()+"%'");
			medal.setName(null);
		}
		List<Medal> datas=medalService.findListDataByFinder(finder,page,Medal.class,medal);
		returnObject.setQueryBean(medal);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,Medal medal) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = medalService.findDataExportExcel(null,listurl, page,Medal.class,medal);
		String fileName="medal"+GlobalStatic.excelext;
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
		return "/system/medal/medalLook";
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
		  Medal medal = medalService.findMedalById(id);
		   returnObject.setData(medal);
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
	ReturnDatas saveorupdate(Model model,Medal medal,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
			medalService.saveorupdate(medal);
			
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
		return "/medal/medalCru";
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
			  
			  Finder finderAppLy=Finder.getSelectFinder(ApplyMedal.class," id ").append(" where 1=1 and medalId=:medalId");
			  finderAppLy.setParam("medalId", strId);
			  List<Integer> applyIds=applyMedalService.queryForList(finderAppLy, Integer.class);
			  applyMedalService.deleteByIds(applyIds, ApplyMedal.class);
			  
			  Finder finderUser=Finder.getSelectFinder(UserMedal.class," id ").append(" where 1=1 and medalId=:medalId");
			  finderUser.setParam("medalId", strId);
			  List<Integer> userIds=userMedalService.queryForList(finderUser, Integer.class);
			  userMedalService.deleteByIds(userIds, UserMedal.class);
			  
			 id= java.lang.Integer.valueOf(strId.trim());
				medalService.deleteById(id,Medal.class);
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
			medalService.deleteByIds(ids,Medal.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}
	
	/**
	 * 所有勋章列表
	 * @author wj
	 * @param request
	 * @param model
	 * @param medal
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/all/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas alljson(HttpServletRequest request, Model model,Medal medal) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		if(null == medal.getUserId()){
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数缺失");
		}else{
			Finder finder = new Finder("SELECT a.*,md.* FROM t_medal md LEFT JOIN (SELECT m.id,am.`status` as applyStatus,am.userId from t_medal m LEFT JOIN t_apply_medal am ON am.medalId=m.id WHERE am.userId=:userId and isEndStatus != 1)a ON md.id=a.id");
			finder.setParam("userId", medal.getUserId());
			List datas = medalService.queryForList(finder);
			returnObject.setData(datas);
		}
		return returnObject;
	}

}
