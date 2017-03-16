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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cz.mts.frame.annotation.SecurityApi;
import com.cz.mts.frame.controller.BaseController;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.MessageUtils;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Attention;
import com.cz.mts.system.entity.Medal;
import com.cz.mts.system.entity.MediaPackage;
import com.cz.mts.system.entity.MoneyDetail;
import com.cz.mts.system.entity.PosterPackage;
import com.cz.mts.system.entity.UserMedal;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.IMedalService;
import com.cz.mts.system.service.IMediaPackageService;
import com.cz.mts.system.service.IMoneyDetailService;
import com.cz.mts.system.service.IPosterPackageService;
import com.cz.mts.system.service.IUserMedalService;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:27
 * @see com.cz.mts.system.web.MoneyDetail
 */
@Controller
@RequestMapping(value="/system/moneydetail")
public class MoneyDetailController  extends BaseController {
	@Resource
	private IMoneyDetailService moneyDetailService;
	@Resource
	private IAppUserService appUserService;
	@Resource
	private IUserMedalService userMedalService;
	@Resource
	private IMedalService medalService;
	@Resource
	private IPosterPackageService posterPackageService;
	@Resource
	private IMediaPackageService mediaPackageService;
	
	private String listurl="/system/moneydetail/moneydetailList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param moneyDetail
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,MoneyDetail moneyDetail) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, moneyDetail);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * 领取红包人列表
	 * @author wj
	 * @param request
	 * @param model
	 * @param moneyDetail
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,MoneyDetail moneyDetail) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		returnObject = moneyDetailService.list(moneyDetail, page);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,MoneyDetail moneyDetail) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = moneyDetailService.findDataExportExcel(null,listurl, page,MoneyDetail.class,moneyDetail);
		String fileName="moneyDetail"+GlobalStatic.excelext;
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
		return "/system/moneydetail/moneydetailLook";
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
		  MoneyDetail moneyDetail = moneyDetailService.findMoneyDetailById(id);
		   returnObject.setData(moneyDetail);
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
	ReturnDatas saveorupdate(Model model,MoneyDetail moneyDetail,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
		
			moneyDetailService.saveorupdate(moneyDetail);
			
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
		return "/system/moneydetail/moneydetailCru";
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
				moneyDetailService.deleteById(id,MoneyDetail.class);
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
			moneyDetailService.deleteByIds(ids,MoneyDetail.class);
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
	 * @param moneyDetail
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/money/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas moneyjson(HttpServletRequest request, Model model,MoneyDetail moneyDetail) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		page.setPageSize(20);
		if(null != moneyDetail.getUserId()){
			List<MoneyDetail> datas=moneyDetailService.findListDataByFinder(null,page,MoneyDetail.class,moneyDetail);
			if(null != datas && datas.size() > 0){
				for (MoneyDetail md : datas) {
					AppUser appUser = appUserService.findAppUserById(md.getUserId());
					if(null != appUser){
						md.setAppUser(appUser);
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该用户不存在");
					}
					
					if(null != md.getItemId()){
						if(1 == md.getType()){
							//查询海报
							PosterPackage posterPackage = posterPackageService.findPosterPackageById(md.getItemId());
							if(posterPackage != null && StringUtils.isNotBlank(posterPackage.getTitle())){
								md.setContent(posterPackage.getTitle());
							}
						}
						
						if(2 == md.getType()){
							//查询视频
							MediaPackage mediaPackage = mediaPackageService.findMediaPackageById(md.getItemId());
							if(mediaPackage != null && StringUtils.isNotBlank(mediaPackage.getTitle())){
								md.setContent(mediaPackage.getTitle());
							}
						}
					}
				}
			}
			returnObject.setQueryBean(moneyDetail);
			returnObject.setData(datas);
			returnObject.setPage(page);
		}else{
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数缺失");
		}
		return returnObject;
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
	@RequestMapping("/listuser/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas listuserjson(HttpServletRequest request, Model model,MoneyDetail moneyDetail) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		if(moneyDetail.getItemId()!=null){
			Finder finder=new Finder("SELECT * FROM t_app_user au WHERE id IN (SELECT userId FROM t_money_detail mon WHERE mon.itemId = :itemId AND mon.type=3)");
			finder.setParam("itemId", moneyDetail.getItemId());
			returnObject.setData(appUserService.queryForList(finder,page));
		}else {
			returnObject.setMessage("参数缺失");
		}
		
		returnObject.setQueryBean(moneyDetail);
		returnObject.setPage(page);
		return returnObject;
	}
	
	@RequestMapping("/statics/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas staticsjson(HttpServletRequest request,Model model,MoneyDetail moneyDetail) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Page page = newPage(request);
		returnObject = moneyDetailService.statics(moneyDetail, page);
		return returnObject;
	}
	

}
