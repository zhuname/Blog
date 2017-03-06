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
import com.cz.mts.system.entity.Attention;
import com.cz.mts.system.entity.Collect;
import com.cz.mts.system.entity.LunboPic;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.IAttentionService;
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
	
	private String listurl="/system/attention/attentionList";
	
	
	   
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
		ReturnDatas returnObject = listjson(request, model, attention);
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
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,Attention attention) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		/*// ==执行分页查询
		List<Attention> datas=attentionService.findListDataByFinder(null,page,Attention.class,attention);*/
		if(attention.getUserId()!=null){
			Finder finder=new Finder("SELECT *,att.isUpdate as isUpdate2 FROM t_app_user au LEFT JOIN t_attention att ON att.userId = au.id WHERE att.userId= :id  order by att.id");
			finder.setParam("id", attention.getUserId());
			returnObject.setData(appUserService.queryForList(finder,page));
		}else {
			returnObject.setMessage("参数缺失");
		}
		
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
	public @ResponseBody
	ReturnDatas saveorupdate(Model model,Attention attention,HttpServletRequest request,HttpServletResponse response) throws Exception{
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
			}else {
				attention.setIsUpdate(0);
				attentionService.saveorupdate(attention);
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
