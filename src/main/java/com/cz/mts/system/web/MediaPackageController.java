package  com.cz.mts.system.web;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cz.mts.frame.annotation.SecurityApi;
import com.cz.mts.frame.controller.BaseController;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.JsonUtils;
import com.cz.mts.frame.util.MessageUtils;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Attention;
import com.cz.mts.system.entity.Card;
import com.cz.mts.system.entity.Category;
import com.cz.mts.system.entity.City;
import com.cz.mts.system.entity.LmediaPackage;
import com.cz.mts.system.entity.LposterPackage;
import com.cz.mts.system.entity.Medal;
import com.cz.mts.system.entity.MediaPackage;
import com.cz.mts.system.entity.MoneyDetail;
import com.cz.mts.system.entity.Oper;
import com.cz.mts.system.entity.PosterPackage;
import com.cz.mts.system.entity.RedCity;
import com.cz.mts.system.entity.UserCard;
import com.cz.mts.system.entity.UserMedal;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.IAttentionService;
import com.cz.mts.system.service.ICardService;
import com.cz.mts.system.service.ICategoryService;
import com.cz.mts.system.service.ICityService;
import com.cz.mts.system.service.ICollectService;
import com.cz.mts.system.service.IMedalService;
import com.cz.mts.system.service.IMediaPackageService;
import com.cz.mts.system.service.IMoneyDetailService;
import com.cz.mts.system.service.IOperService;
import com.cz.mts.system.service.IRedCityService;
import com.cz.mts.system.service.IUserCardService;
import com.cz.mts.system.service.IUserMedalService;
import com.cz.mts.system.service.NotificationService;


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
	@Resource
	private ICityService cityService;
	@Resource
	private IOperService operService;
	@Resource
	private IUserCardService userCardService;
	@Resource
	private NotificationService notificationService;
	@Resource
	private ICardService cardService;
	
	
	
	private String listurl="/mediapackage/mediapackageList";
	
	
	   
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
		ReturnDatas returnObject = listAdminJson(request, model, mediaPackage);
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
	ReturnDatas listjson(HttpServletRequest request, Model model,MediaPackage mediaPackage ,Integer personType) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		String appUserId = request.getParameter("appUserId");
		String selectType = request.getParameter("selectType");
		returnObject = mediaPackageService.list(mediaPackage, page, appUserId , personType,selectType);
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
		  Integer id= 0;
		  if(StringUtils.isNotBlank(strId)){
			 id= Integer.parseInt(strId);
			  MediaPackage mediaPackage = mediaPackageService.findMediaPackageById(id);
			  
			  if(null != mediaPackage && 3 == mediaPackage.getStatus()){
				 if(null == mediaPackage.getScanNum()){
					 mediaPackage.setScanNum(0);
				 }
				 mediaPackage.setScanNum(mediaPackage.getScanNum() + 1);
				 
				 //超过100人浏览
				 if(mediaPackage.getScanNum() % 100 == 0 && mediaPackage.getScanNum() / 100 > 0){
					 notificationService.notify(26, mediaPackage.getId(), mediaPackage.getUserId(),mediaPackage.getScanNum()+"");
				 }
				 mediaPackageService.update(mediaPackage,true);
			 }
			  
			  
			  //查询发红包的用户
			 if(mediaPackage != null && mediaPackage.getUserId() != null){
				 AppUser appUser = appUserService.findAppUserById(mediaPackage.getUserId());
				 if(appUser!=null){
					 mediaPackage.setAppUser(appUser);
				 }
				 
				 if(null != appUser.getUserMedals()){
					 mediaPackage.setUserMedals(appUser.getUserMedals());
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
			 
			 
			 //是否关注
			 if(mediaPackage!=null&&StringUtils.isNotBlank(appUserId)){
				 Attention attention=new Attention();
				 attention.setUserId(Integer.parseInt(appUserId));
				 attention.setItemId(mediaPackage.getUserId());
				// ==构造分页请求
					Page page = newPage(request);
					// ==执行分页查询
					List<Attention> datas=attentionService.findListDataByFinder(null,page,Attention.class,attention);
					if(datas!=null&&datas.size()>0){
						mediaPackage.setIsAttention(1);
					}else{
						mediaPackage.setIsAttention(0);
					}
					
					
					 //是否点赞过
					 Oper oper = new Oper();
					 oper.setUserId(Integer.parseInt(appUserId));
					 oper.setItemId(mediaPackage.getId());
					 oper.setType(3);
					 Page operPage = newPage(request);
					 List<Oper> opers = operService.findListDataByFinder(null, operPage, Oper.class, oper);
					 if(null != opers && opers.size() > 0){
						 mediaPackage.setIsTop(1);
					 }else{
						 mediaPackage.setIsTop(0);
					 }
			 }
			 
			 if(null != mediaPackage && null != mediaPackage.getCardId()){
				 Card card = cardService.findCardById(mediaPackage.getCardId());
				 if(null != card && StringUtils.isNotBlank(card.getTitle())){
					 mediaPackage.setCardTitle(card.getTitle());
				 }
			 }
			 
			 //返回分类名称
			 if(mediaPackage != null && mediaPackage.getCategoryId() != null){
				 Category category = categoryService.findCategoryById(mediaPackage.getCategoryId());
				 if(category != null){
					 if(StringUtils.isNotBlank(category.getName())){
						 mediaPackage.setCategoryName(category.getName());
					 }
				 }
			 }
			 
			//返回卡券分类名称以及卡券分类图片
			 if(null != mediaPackage && null != mediaPackage.getCardCategoryId()){
				 Category category = categoryService.findCategoryById(mediaPackage.getCardCategoryId());
				 if(null != category){
					 if(StringUtils.isNotBlank(category.getName())){
						 mediaPackage.setCardCategoryName(category.getName());
					 }
					 if(StringUtils.isNotBlank(category.getImage())){
						 mediaPackage.setCardCategoryImage(category.getImage());
					 }
				 }
			 }
			 
			 
			 //返回卡券已领取数量
			 if(null != mediaPackage && null != mediaPackage.getCardId()){
				 Integer lqNum = 0;
				 if(StringUtils.isNotBlank(appUserId)){
					 //返回该用户已领取的数量
					 Finder userCardFinder = new Finder("SELECT COUNT(id) as lqNum FROM t_user_card WHERE cardId=:cardId  AND `status`!=0");
					 userCardFinder.setParam("cardId", mediaPackage.getCardId());
					 List<UserCard> userCards = userCardService.queryForList(userCardFinder, UserCard.class);
					 if(null != userCards && userCards.size() > 0){
						 UserCard userCard = userCards.get(0);
						 if(null != userCard && null != userCard.getLqNum() ){
							 lqNum = userCards.get(0).getLqNum();
						 }
					 }
				 }
				 mediaPackage.setCardLqNum(lqNum);
			 }
			 
			 
			 
			 
			 
			 String cityIds= ""; 
			//返回城市名称
			 Finder finder = new Finder("SELECT * FROM t_red_city WHERE packageId=:id AND type=2");
			 finder.setParam("id", Integer.parseInt(strId));
			 List<RedCity> redCities = redCityService.queryForList(finder,RedCity.class);
			 if(null != redCities && redCities.size() > 0){
				 for (RedCity redCity : redCities) {
					 if(null != redCity.getCityId() && 0 != redCity.getCityId()){
						 cityIds += redCity.getCityId()+",";
					 }
					if(null != redCity.getCityId()){
						City city = cityService.findCityById(redCity.getCityId());
						if(city!=null){
							redCity.setCityName(city.getName());
						}
					}
				}
				 mediaPackage.setCityIds(cityIds);
				 mediaPackage.setRedCities(redCities);
			 }
			 
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
			if(null == mediaPackage.getId()){
				mediaPackageService.saveorupdate(mediaPackage);
			}else{
				//cityIds是否为空
				if(StringUtils.isNotBlank(mediaPackage.getCityIds())){
					String cityIds[] = mediaPackage.getCityIds().split(",");
					if(null != cityIds && cityIds.length > 0){
						//删除红包表中的记录
						Finder finder = new Finder("DELETE FROM t_red_city WHERE type=2 and packageId=:packageId");
						finder.setParam("packageId", mediaPackage.getId());
						redCityService.update(finder);
						for (String cid : cityIds) {
							Integer cityId = Integer.parseInt(cid);
							//更新redCity表
							RedCity redCity = new RedCity();
							redCity.setCityId(cityId);
							redCity.setPackageId(mediaPackage.getId());
							redCity.setType(2);
							redCityService.save(redCity);
						}
					}
				}else{
					//删除红包表中的记录
					Finder finder = new Finder("DELETE FROM t_red_city WHERE type=2 and packageId=:packageId");
					finder.setParam("packageId", mediaPackage.getId());
					redCityService.update(finder);
					//更新redCity表
					RedCity redCity = new RedCity();
					redCity.setCityId(0);
					redCity.setPackageId(mediaPackage.getId());
					redCity.setType(2);
					redCityService.save(redCity);
				}
				
				if(0 == mediaPackage.getIsRelevance()){
					mediaPackage.setCardId(0);
				}
				
				mediaPackageService.update(mediaPackage,true);
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
		return "/mediapackage/mediapackageCru";
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
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
		}
		
		return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);
		
	}
	
	/**
	 * 新增视频红包
	 * 新增/修改操作吗,返回json格式数据
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
				if(mediaPackage.getCategoryId()==null||mediaPackage.getUserId()==null||mediaPackage.getLqNum()==null){
					
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
				mediaPackage.setBalance(mediaPackage.getSumMoney());
				mediaPackage.setNum(mediaPackage.getLqNum());
				mediaPackage.setIsDel(0);
				mediaPackage.setShareNum(0);
				mediaPackage.setIsValid(0);
				
				//保证没人最少领取0.01
				if(mediaPackage.getSumMoney()/mediaPackage.getLqNum() >= 0.01){
					Object id=mediaPackageService.saveorupdate(mediaPackage);
					returnObject.setData(mediaPackageService.findMediaPackageById(id));
					
					if(cityIds!=null){
					
						String[] cityId=cityIds.split(",");
					
							for (String string : cityId) {
								RedCity redCity=new RedCity();
								redCity.setCityId(Integer.parseInt(string));
								redCity.setPackageId(Integer.parseInt(id.toString()));
								redCity.setType(2);
								redCityService.save(redCity);
							}
					
					}else{
						RedCity redCity=new RedCity();
						redCity.setCityId(0);
						redCity.setPackageId(Integer.parseInt(id.toString()));
						redCity.setType(2);
						redCityService.save(redCity);
					}
				}else{
					returnObject.setMessage("每个人的领取金额最少是0.01元");
					returnObject.setStatus(ReturnDatas.ERROR);
					return returnObject;
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
				
				mediaPackage.setBalance(mediaPackage.getSumMoney());
				
				mediaPackage.setPayMoney(0.0);
				
				mediaPackage.setStatus(0);
				
				mediaPackage.setPayType(null);
				
				mediaPackage.setNum(mediaPackage.getLqNum());
				
				mediaPackage.setPayTime(null);
				
				mediaPackage.setWxCode(null);
				
				mediaPackage.setTradeNo(null);
				
				//每个人应该最少领取金额是0.01
				if(mediaPackage.getSumMoney()/mediaPackage.getLqNum() >= 0.01){
					RedCity redCitySelect = new RedCity();
					
					redCitySelect.setPackageId(mediaPackage.getId());
					redCitySelect.setType(2);
					
					// ==构造分页请求
					Page page = newPage(request);
					// ==执行分页查询
					List<RedCity> datas=redCityService.findListDataByFinder(null,page,RedCity.class,redCitySelect);
					
					for (RedCity redCity : datas) {
						
						redCityService.deleteByEntity(redCity);
						
					}
					
					if(cityIds!=null){
						
						String[] cityId=cityIds.split(",");
					
							for (String string : cityId) {
								RedCity redCity=new RedCity();
								redCity.setCityId(Integer.parseInt(string));
								redCity.setPackageId(mediaPackage.getId());
								redCity.setType(2);
								redCityService.save(redCity);
							}
					
					}else{
						RedCity redCity=new RedCity();
						redCity.setCityId(0);
						redCity.setPackageId(mediaPackage.getId());
						redCity.setType(2);
						redCityService.save(redCity);
					}
					
					
					Object id=mediaPackageService.update(mediaPackage,true);
					
					returnObject.setData(mediaPackageService.findMediaPackageById(id));
				}else{
					returnObject.setMessage("每个人的领取金额最少是0.01元");
					returnObject.setStatus(ReturnDatas.ERROR);
					return returnObject;
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
	
	@RequestMapping("/listadmin/json")
	public @ResponseBody
	ReturnDatas listAdminJson(HttpServletRequest request, Model model,MediaPackage mediaPackage) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		mediaPackage.setIsDel(0);
		Finder finder = Finder.getSelectFinder(MediaPackage.class).append("where 1 = 1");
		if(StringUtils.isNotBlank(mediaPackage.getCategoryName())){
			finder.append(" and categoryId in(select id from t_category where type=2 and INSTR(`name`,:categoryName)>0 )");
			finder.setParam("categoryName", mediaPackage.getCategoryName());
		}
		if(StringUtils.isNotBlank(mediaPackage.getName())){
			finder.append(" and userId in(select id from t_app_user where INSTR(`name`,:userName)>0 )");
			finder.setParam("userName", mediaPackage.getName());
		}
		
		if(StringUtils.isNotBlank(mediaPackage.getStartTime())){
			finder.append(" and payTime > :startTime ");
			finder.setParam("startTime", mediaPackage.getStartTime());
		}
		
		if(StringUtils.isNotBlank(mediaPackage.getEnddTime())){
			finder.append(" and payTime < :endTime ");
			finder.setParam("endTime", mediaPackage.getEnddTime());
		}
		if(StringUtils.isNotBlank(mediaPackage.getCityIds())){
			finder.append(" and id in( SELECT DISTINCT(packageId) FROM t_red_city WHERE cityId=:cityId and type=2)");
			finder.setParam("cityId", Integer.parseInt(mediaPackage.getCityIds()));
		}
		finder.append(" and status!=0");
		List<MediaPackage> datas = mediaPackageService.findListDataByFinder(finder,page,MediaPackage.class,mediaPackage);
		Double sumPayMoney = 0.0;
		Double sumBalance = 0.0;
		if(null != datas && datas.size() > 0){
			for (MediaPackage mp : datas) {
				
				if(null != mp.getCardId() && 0 != mp.getCardId()){
					Card card = cardService.findCardById(mp.getCardId());
					if(null != card && StringUtils.isNotBlank(card.getTitle())){
						mp.setCardTitle(card.getTitle());
					}
				}
				
				
				//获取用户名称
				if(null != mp.getUserId()){
					AppUser appUser = appUserService.findAppUserById(mp.getUserId());
					if(null != appUser){
						if(StringUtils.isNotBlank(appUser.getName())){
							mp.setName(appUser.getName());
						}
					}
				}
				
				//获取分类名称
				if(null != mp.getCategoryId()){
					Category category = categoryService.findCategoryById(mp.getCategoryId());
					if(null != category){
						if(StringUtils.isNotBlank(category.getName())){
							mp.setCategoryName(category.getName());
						}
					}
				}
				
				if(null != mp.getPayType()){
					if(1 == mp.getPayType()){
						mp.setPayName("支付宝");
					}
					if(2 == mp.getPayType()){
						mp.setPayName("微信");
					}
					if(3 == mp.getPayType()){
						mp.setPayName("余额支付");
					}
				}
			}
		}
		
		Page pageNew = new Page();
		pageNew.setPageSize(10000);
		List<MediaPackage> mediaPackages = mediaPackageService.findListDataByFinder(finder,pageNew,MediaPackage.class,mediaPackage);
		if(mediaPackages != null && mediaPackages.size() > 0){
			for (MediaPackage mpp : mediaPackages) {
				if(null == mpp.getSumMoney()){
					mpp.setSumMoney(0.0);
				}
				sumPayMoney += mpp.getSumMoney();
				
				if(null == mpp.getBalance()){
					mpp.setBalance(0.0);
				}
				sumBalance += mpp.getBalance();
			}
		}
		
		Double sumOverMoney = new BigDecimal(sumPayMoney).subtract(new BigDecimal(sumBalance)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		HashMap<String, Object> map=new HashMap<String,Object>();  
		map.put("sumPayMoney", new BigDecimal(sumPayMoney).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		map.put("sumBalance", new BigDecimal(sumBalance).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		map.put("sumOverMoney", sumOverMoney);
		
		returnObject.setMap(map);
		returnObject.setQueryBean(mediaPackage);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}

	
	/**
	 * 
	 * 后台统计接口
	 * @author wj
	 * @param request
	 * @param model
	 * @param mediaPackage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/tongji/json")
	public @ResponseBody
	ReturnDatas tongjijson(HttpServletRequest request, Model model,MediaPackage mediaPackage) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Finder finder=new Finder("SELECT SUM(payMoney) FROM t_media_package ;");

		Double sum=mediaPackageService.queryForObject(finder, Double.class);
		
		if(sum==null){
			sum=0.0;
		}
		
		returnObject.setData(sum);
		
		return returnObject;
	}
	
	/**
	 * 
	 * 后台统计接口
	 * @author wj
	 * @param request
	 * @param model
	 * @param mediaPackage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/balance/json")
	public @ResponseBody
	ReturnDatas balancejson(HttpServletRequest request, Model model,MediaPackage mediaPackage) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Finder finder=new Finder("SELECT SUM(balance) FROM t_media_package ;");
		Double sum=mediaPackageService.queryForObject(finder, Double.class);
		if(sum==null){
			sum=0.0;
		}
		
		returnObject.setData(sum);
		
		return returnObject;
	}
	
	/**
	 * 抢红包
	 * @param request
	 * @param model
	 * @param id 红包id
	 * @param userId  操作人id
	 * @return
	 * @author wxy
	 * @date 2017年2月28日
	 */
	@RequestMapping("/snatch/json")
	@SecurityApi
	public @ResponseBody 
	ReturnDatas snatchjson(HttpServletRequest request, Model model,String id,String userId,String osType,String command){
		
		ReturnDatas result =  new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.UPDATE_SUCCESS);
		
		if(StringUtils.isBlank(id) || StringUtils.isBlank(userId)) {
			return new ReturnDatas(ReturnDatas.ERROR, "参数缺失!") ;
		}else {
			
			try {
				String data =  (String) mediaPackageService.snatch(userId, id,osType,command) ;
				if(data.indexOf("{") == -1 ){  //说明是个错误结果
					result.setMessage(data);
					result.setStatus(ReturnDatas.ERROR);
				}else {
					LmediaPackage lpp = JsonUtils.readValue(data, LmediaPackage.class) ;
					result.setData(lpp);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.setStatus(ReturnDatas.ERROR);
				result.setMessage("系统异常");
			}
			
			return result ;
		}
		
	}
	
	
	/**
	 * 审核红包
	 * @param request
	 * @param id 红包id
	 * @param type 审核类型 1通过 0拒绝
	 * @param failReason 失败原因
	 * @return
	 * @author wxy
	 * @date 2017年3月16日
	 */
	@RequestMapping("/check/json")
	public @ResponseBody
	ReturnDatas checkJson(HttpServletRequest request, String id,String type,String failReason){
		if(StringUtils.isBlank(id) || StringUtils.isBlank(type)){
			return new ReturnDatas(ReturnDatas.ERROR, "参数缺失") ;
		}else {
			try {
				Object object = mediaPackageService.check(id, type, failReason) ;
				if(object == null){
					return new ReturnDatas(ReturnDatas.ERROR, "红包不存在") ;
				}else {
					return new ReturnDatas(ReturnDatas.SUCCESS, object.toString()) ;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ReturnDatas(ReturnDatas.ERROR, "系统异常") ;
			}
		}
	}
	
	
	/**
	 * 删除视频红包
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteadmin")
	public @ResponseBody ReturnDatas deleteadmin(HttpServletRequest request) throws Exception {

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
	
	
}
