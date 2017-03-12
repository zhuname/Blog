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

import com.cz.mts.system.entity.Card;
import com.cz.mts.system.entity.LunboPic;
import com.cz.mts.system.entity.MediaPackage;
import com.cz.mts.system.entity.PosterPackage;
import com.cz.mts.system.entity.User;
import com.cz.mts.system.service.ICardService;
import com.cz.mts.system.service.ILunboPicService;
import com.cz.mts.system.service.IMediaPackageService;
import com.cz.mts.system.service.IPosterPackageService;
import com.cz.mts.frame.annotation.SecurityApi;
import com.cz.mts.frame.controller.BaseController;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.MessageUtils;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;
import com.sun.tools.classfile.Annotation.element_value;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:26
 * @see com.cz.mts.system.web.LunboPic
 */
@Controller
@RequestMapping(value="/system/lunbopic")
public class LunboPicController  extends BaseController {
	@Resource
	private ILunboPicService lunboPicService;
	@Resource
	private IPosterPackageService posterPackageService;
	@Resource
	private IMediaPackageService mediaPackageService;
	@Resource
	private ICardService cardService;
	
	private String listurl="/lunbopic/lunbopicList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param lunboPic
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,LunboPic lunboPic) 
			throws Exception {
		ReturnDatas returnObject = listadminjson(request, model, lunboPic);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param lunboPic
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,LunboPic lunboPic) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		
		if(lunboPic.getPosition()!=null&&lunboPic.getPosition()==4){
			Finder finder=Finder.getSelectFinder(LunboPic.class).append(" WHERE 1=1 and position= :position order by Rand() limit 1");
			finder.setParam("position", lunboPic.getPosition());
			returnObject.setData(lunboPicService.queryForList(finder,LunboPic.class));
		}else {
			List<LunboPic> datas=lunboPicService.findListDataByFinder(null,page,LunboPic.class,lunboPic);
			returnObject.setQueryBean(lunboPic);
			returnObject.setPage(page);
			returnObject.setData(datas);
		}
		
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,LunboPic lunboPic) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = lunboPicService.findDataExportExcel(null,listurl, page,LunboPic.class,lunboPic);
		String fileName="lunboPic"+GlobalStatic.excelext;
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
		return "/lunbopic/lunbopicLook";
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
		  LunboPic lunboPic = lunboPicService.findLunboPicById(id);
		   returnObject.setData(lunboPic);
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
	@SecurityApi
	public @ResponseBody
	ReturnDatas saveorupdate(Model model,LunboPic lunboPic,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if(null == lunboPic.getId()){
				lunboPic.setCreateTime(new Date());
				lunboPicService.saveorupdate(lunboPic);
			}else{
				lunboPicService.update(lunboPic,true);
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
		return "/lunbopic/lunbopicCru";
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
				lunboPicService.deleteById(id,LunboPic.class);
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
			lunboPicService.deleteByIds(ids,LunboPic.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}
	
	/**
	 * 后台轮播图列表
	 * @author wj
	 * @param request
	 * @param model
	 * @param lunboPic
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listadmin/json")
	public @ResponseBody
	ReturnDatas listadminjson(HttpServletRequest request, Model model,LunboPic lunboPic) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<LunboPic> datas=lunboPicService.findListDataByFinder(null,page,LunboPic.class,lunboPic);
		if(null != datas && datas.size() > 0){
			for (LunboPic lp : datas) {
				if(null != lp.getItemId()){
					//1红包 2视频 3卡券 4广告位
					if(null != lp.getPosition()){
						if(1 == lp.getPosition()){
							PosterPackage posterPackage = posterPackageService.findPosterPackageById(lp.getItemId());
							if(null != posterPackage){
								if(StringUtils.isNotBlank(posterPackage.getTitle())){
									lp.setItemName(posterPackage.getTitle());
								}
							}
						}
						
						if(2 == lp.getPosition()){
							MediaPackage mediaPackage = mediaPackageService.findMediaPackageById(lp.getItemId());
							if(null != mediaPackage){
								if(StringUtils.isNotBlank(mediaPackage.getTitle())){
									lp.setItemName(mediaPackage.getTitle());
								}
							}
						}
						
						if(3 == lp.getPosition()){
							Card card = cardService.findCardById(lp.getItemId());
							if(null != card){
								if(StringUtils.isNotBlank(card.getTitle())){
									lp.setItemName(card.getTitle());
								}
							}
						}
						
					}
				}
			}
		}
		returnObject.setQueryBean(lunboPic);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
}
