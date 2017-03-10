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

import com.cz.mts.frame.annotation.SecurityApi;
import com.cz.mts.frame.controller.BaseController;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.MessageUtils;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Attention;
import com.cz.mts.system.entity.Category;
import com.cz.mts.system.entity.Collect;
import com.cz.mts.system.entity.Medal;
import com.cz.mts.system.entity.MediaPackage;
import com.cz.mts.system.entity.MoneyDetail;
import com.cz.mts.system.entity.RedCity;
import com.cz.mts.system.entity.User;
import com.cz.mts.system.entity.UserMedal;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.IAttentionService;
import com.cz.mts.system.service.ICategoryService;
import com.cz.mts.system.service.ICollectService;
import com.cz.mts.system.service.IMedalService;
import com.cz.mts.system.service.IMediaPackageService;
import com.cz.mts.system.service.IMoneyDetailService;
import com.cz.mts.system.service.IRedCityService;
import com.cz.mts.system.service.IUserMedalService;
import com.sun.tools.javac.util.Assert;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:26
 * @see com.cz.mts.system.web.MediaPackage
 */
@Controller
@RequestMapping(value="/system/mediapackage")
public class MediaPackageController  extends BaseController {
	@Resource
	private IMediaPackageService mediaPackageService;
	@Resource
	private IAppUserService appUserService;
	@Resource
	private IMoneyDetailService moneyDetailService;
	@Resource
	private IUserMedalService userMedalService;
	@Resource
	private IMedalService medalService;
	@Resource
	private ICollectService collectService;
	@Resource
	private IAttentionService attentionService;
	@Resource
	private IRedCityService redCityService;
	@Resource
	private ICategoryService categoryService;
	
	
	private String listurl="/system/mediapackage/mediapackageList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param mediaPackage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,MediaPackage mediaPackage) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, mediaPackage);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * 
	 * 获取视频列表
	 * @author wj
	 * @param request
	 * @param model
	 * @param mediaPackage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,MediaPackage mediaPackage) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		String appUserId = request.getParameter("appUserId");
		returnObject = mediaPackageService.list(mediaPackage, page, appUserId);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,MediaPackage mediaPackage) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = mediaPackageService.findDataExportExcel(null,listurl, page,MediaPackage.class,mediaPackage);
		String fileName="mediaPackage"+GlobalStatic.excelext;
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
		return "/system/mediapackage/mediapackageLook";
	}

	
	/**
	 * 视频详情接口
	 * @param id 视频id
	 * @param appUserId 用户id
	 * @author wj
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		  String  strId=request.getParameter("id");
		  String appUserId = request.getParameter("appUserId");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
			  MediaPackage mediaPackage = mediaPackageService.findMediaPackageById(id);
			  //查询发红包的用户
			 if(mediaPackage != null && mediaPackage.getUserId() != null){
				 AppUser appUser = appUserService.findAppUserById(mediaPackage.getUserId());
				 if(appUser!=null){
					 mediaPackage.setAppUser(appUser);
				 }
				 
				 //查询该用户的勋章列表
				 UserMedal userMedal = new UserMedal();
				 userMedal.setUserId(mediaPackage.getUserId());
				 Page page = new Page();
				 //查询勋章列表
				 List<UserMedal> userMedals = userMedalService.findListDataByFinder(null, page, UserMedal.class, userMedal);
				 if(null != userMedals && userMedals.size() > 0){
					for (UserMedal um : userMedals) {
						if(null != um.getMedalId()){
							Medal medal = medalService.findMedalById(um.getMedalId());
							if(null != medal){
								um.setMedal(medal);
							}
						}
					 }
					mediaPackage.setUserMedals(userMedals);
				 }
			  }
			 //是否领取  look 1 为领取过的
			 if(mediaPackage!=null&&StringUtils.isNotBlank(appUserId)){
				 MoneyDetail moneyDetail=new MoneyDetail();
				 moneyDetail.setUserId(Integer.parseInt(appUserId));
				 moneyDetail.setItemId(mediaPackage.getId());
				 moneyDetail.setType(2);
				// ==构造分页请求
					Page page = newPage(request);
					// ==执行分页查询
					List<MoneyDetail> datas = moneyDetailService.findListDataByFinder(null,page,MoneyDetail.class,moneyDetail);
					if(datas != null && datas.size() > 0){
						mediaPackage.setIsLook(1);
					}else{
						mediaPackage.setIsLook(0);
					}
			 }
			 if(null == mediaPackage.getScanNum()){
				 mediaPackage.setScanNum(0);
			 }
			 mediaPackage.setScanNum(mediaPackage.getScanNum() + 1);
			 mediaPackageService.update(mediaPackage);
		   returnObject.setData(mediaPackage);
		}else{
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数缺失");
		}
		return returnObject;
		
	}
	
	
	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/update")
	public @ResponseBody
	ReturnDatas saveorupdate(Model model,MediaPackage mediaPackage,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
		
			mediaPackageService.saveorupdate(mediaPackage);
			
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
		return "/system/mediapackage/mediapackageCru";
	}
	
	/**
	 * 删除视频红包接口
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delete/json")
	@SecurityApi
	public @ResponseBody ReturnDatas deletejson(HttpServletRequest request) throws Exception {

			// 执行删除
		try {
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
				MediaPackage mediaPackage = mediaPackageService.findMediaPackageById(id);
				if(null != mediaPackage){
					mediaPackage.setIsDel(1);
					mediaPackageService.update(mediaPackage,true);
				}
				return new ReturnDatas(ReturnDatas.SUCCESS,
						MessageUtils.DELETE_SUCCESS);
			} else {
				return new ReturnDatas(ReturnDatas.ERROR,"参数缺失");
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
			mediaPackageService.deleteByIds(ids,MediaPackage.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}
	
	/**
	 * 新增视频红包
	 * 新增/修改 操作吗,返回json格式数据
	 * @author wwwwwwwwwwwwwwwwwmmmmmmmmmmmmmmmmmmmmmmllllllllllllllllll
	 */
	@RequestMapping("/update/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas saveorupdatejson(Model model,MediaPackage mediaPackage,HttpServletRequest request,HttpServletResponse response,String cityIds) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			
			//新增
			if(mediaPackage.getId()==null){
				
				//判断必传参数
				if(mediaPackage.getCategoryId()==null||mediaPackage.getUserId()==null||mediaPackage.getNum()==null){
					
					returnObject.setStatus(ReturnDatas.ERROR);
					
					returnObject.setMessage("参数缺失");
					
					return returnObject;
					
				}
				
				if(mediaPackage.getCategoryId()!=null){
					
					Category category=categoryService.findById(mediaPackage.getCategoryId(), Category.class);
					
					if(category==null){
						
						returnObject.setStatus(ReturnDatas.ERROR);
						
						returnObject.setMessage("分类不存在");
						
						return returnObject;
						
					}
			    
				}
				
				//生成验证码
				Long code=new Date().getTime();
				mediaPackage.setCode("M"+code);
				
				mediaPackage.setStatus(0);
				mediaPackage.setScanNum(0);
				mediaPackage.setCreateTime(new Date());
				mediaPackage.setNum(mediaPackage.getNum());
				Object id=mediaPackageService.saveorupdate(mediaPackage);
				returnObject.setData(mediaPackageService.findMediaPackageById(id));
				
				String[] cityId=cityIds.split(",");
				
				for (String string : cityId) {
					RedCity redCity=new RedCity();
					redCity.setCityId(Integer.parseInt(string));
					redCity.setPackageId(Integer.parseInt(id.toString()));
					redCity.setType(1);
					redCityService.save(redCity);
				}
				
			}else{
				
				if(mediaPackage.getCategoryId()!=null){
					
					Category category=categoryService.findById(mediaPackage.getCategoryId(), Category.class);
					
					if(category==null){
						
						returnObject.setStatus(ReturnDatas.ERROR);
						
						returnObject.setMessage("分类不存在");
						
						return returnObject;
						
					}
				
				}
				
				Object id=mediaPackageService.update(mediaPackage,true);
				
				returnObject.setData(mediaPackageService.findMediaPackageById(id));
				
			}
			
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}

}
