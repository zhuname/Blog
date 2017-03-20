package  com.cz.mts.system.web;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomUtils;
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
import com.cz.mts.system.entity.Card;
import com.cz.mts.system.entity.Category;
import com.cz.mts.system.entity.City;
import com.cz.mts.system.entity.Medal;
import com.cz.mts.system.entity.MoneyDetail;
import com.cz.mts.system.entity.RedCity;
import com.cz.mts.system.entity.SysSysparam;
import com.cz.mts.system.entity.UserCard;
import com.cz.mts.system.entity.UserMedal;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.ICardService;
import com.cz.mts.system.service.ICategoryService;
import com.cz.mts.system.service.ICityService;
import com.cz.mts.system.service.IMedalService;
import com.cz.mts.system.service.IMoneyDetailService;
import com.cz.mts.system.service.IRedCityService;
import com.cz.mts.system.service.ISysSysparamService;
import com.cz.mts.system.service.IUserCardService;
import com.cz.mts.system.service.IUserMedalService;
import com.cz.mts.system.service.NotificationService;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:25
 * @see com.cz.mts.system.web.Card
 */
@Controller
@RequestMapping(value="/system/card")
public class CardController  extends BaseController {
	@Resource
	private ICardService cardService;
	@Resource
	private IAppUserService appUserService;
	@Resource
	private IUserCardService userCardService;
	@Resource
	private ISysSysparamService sysparamService;
	@Resource
	private IMoneyDetailService moneyDetailService;
	@Resource
	private IUserMedalService userMedalService;
	@Resource
	private IMedalService medalService;
	@Resource
	private ICategoryService categoryService;
	@Resource
	private IRedCityService redCityService;
	@Resource
	private ICityService cityService;
	
	@Resource
	private NotificationService notificationService;
	
	
	private String listurl="/card/cardList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param card
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,Card card) 
			throws Exception {
		ReturnDatas returnObject = listAminJson(request, model, card);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param card
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,Card card) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		returnObject = cardService.list(card, page);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,Card card) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = cardService.findDataExportExcel(null,listurl, page,Card.class,card);
		String fileName="card"+GlobalStatic.excelext;
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
		return "/card/cardLook";
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
			 Card card = cardService.findCardById(id);
			 if(null != card.getUserId()){
				 AppUser appUser = appUserService.findAppUserById(card.getUserId());
				 if(null != appUser){
					 card.setAppUser(appUser);
				 }
				 
				 //查询勋章列表
				 Page page = new Page();
				 UserMedal userMedal = new UserMedal();
				 userMedal.setUserId(card.getUserId());
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
					card.setUserMedals(userMedals);
				}
			 }
			 
			 //返回分类名称
			 if(card != null && card.getCatergoryId() != null){
				 Category category = categoryService.findCategoryById(card.getCatergoryId());
				 if(category != null){
					 if(StringUtils.isNotBlank(category.getName())){
						 card.setCategoryName(category.getName());
					 }
				 }
			 }
			 
			 
			 //返回城市名称
			 Finder finder = new Finder("SELECT * FROM t_red_city WHERE packageId=:id AND type=3");
			 finder.setParam("id", Integer.parseInt(strId));
			 List<RedCity> redCities = redCityService.queryForList(finder,RedCity.class);
			 if(null != redCities && redCities.size() > 0){
				 for (RedCity redCity : redCities) {
					if(null != redCity.getCityId()){
						City city = cityService.findCityById(redCity.getCityId());
						if(StringUtils.isNotBlank(city.getName())){
							redCity.setCityName(city.getName());
						}
					}
				}
				 card.setRedCities(redCities);
			 }
			 
			 returnObject.setData(card);
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
	ReturnDatas saveorupdate(Model model,Card card,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if(card.getId()==null){
				cardService.saveorupdate(card);
			}else{
				cardService.update(card,true);
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
		return "/card/cardCru";
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
					Card card = cardService.findCardById(id);
					if(null != card){
						card.setIsDel(1);
						cardService.update(card,true);
					}
					return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_SUCCESS);
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
			cardService.deleteByIds(ids,Card.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}
	
	
	/**
	 * 发布卡券
	 * @author wml
	 * 
	 */
	@RequestMapping("/update/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas saveorupdatejson(Model model,Card card,HttpServletRequest request,HttpServletResponse response,String cityIds) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
			//新增
			if(card.getId()==null){
				//判断必传参数
				if(card.getCatergoryId()==null||card.getUserId()==null||card.getConvertNum()==null){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("参数缺失");
					return returnObject;
				}
				
				if(card.getCatergoryId()!=null){
					
					Category category=cardService.findById(card.getCatergoryId(), Category.class);
					
					if(category==null){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("分类不存在");
						return returnObject;
					}
					
				}
				
				
				card.setIsDel(0);
				card.setNum(card.getConvertNum());
				card.setCreateTime(new Date());
				card.setStatus(1);
				Object id=cardService.saveorupdate(card);
				if(cityIds!=null){
					
					String[] cityId=cityIds.split(",");
					
					for (String string : cityId) {
						RedCity redCity=new RedCity();
						redCity.setCityId(Integer.parseInt(string));
						redCity.setPackageId(Integer.parseInt(id.toString()));
						redCity.setType(3);
						redCityService.save(redCity);
					}
					
				}else{
					RedCity redCity=new RedCity();
					redCity.setCityId(0);
					redCity.setPackageId(Integer.parseInt(id.toString()));
					redCity.setType(3);
					redCityService.save(redCity);
				}
				returnObject.setData(cardService.findCardById(id));
			}else{
				if(card.getCatergoryId()!=null){
					
					Category category=cardService.findById(card.getCatergoryId(), Category.class);
					
					if(category==null){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("分类不存在");
						return returnObject;
					}
					
				}
				Object id=cardService.update(card,true);
				returnObject.setData(cardService.findCardById(id));
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
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/payCard/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas payCardjson(Model model,HttpServletRequest request,HttpServletResponse response,Integer num,Integer cardId,Integer userId) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			
			if(cardId==null||userId==null||num==null){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("参数缺失");
				return returnObject;
			}
			
			//获取卡券信息
			Card card=cardService.findCardById(cardId);
			
			if(card==null){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("找不到此卡券");
				return returnObject;
			}
			//判断数量
			if(card.getNum()<num){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("卡券数量不足");
				return returnObject;
			}
			
			List<UserCard> userCards=new ArrayList<>();
			
			String code=new Date().getTime()+""+RandomUtils.nextInt(1, 9);
			
			//新增usercard
			for (int i = 0; i < num; i++) {
				UserCard userCar = new UserCard();
				
				String userCardCode=new Date().getTime()+""+RandomUtils.nextInt(1000, 9999);
				
				userCar.setCardCode(userCardCode);
				
				userCar.setUserId(userId);
				userCar.setCardId(cardId);
				
				if(card.getConvertMoney()==null||card.getConvertMoney()==0.0){
					userCar.setStatus(1);
				}else{
					userCar.setStatus(0);
				}
				
				userCar.setCreateTime(new Date());
				userCar.setCode(code);
				userCar.setSumMoney(card.getConvertMoney());
				userCar.setPhone(card.getPhone());
				userCar.setAdress(card.getAddress());
				
				userCar.setExpTime(card.getEndTime());
				
				userCards.add(userCar);
			}
			userCardService.save(userCards);
			
			card.setNum(card.getNum()-num);
			cardService.update(card, true);
			
			card.setUserCards(userCards);
			returnObject.setData(card);
			
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	
	
	/**
	 * 兑换卡券接口
	 * 
	 */
	@RequestMapping("/changeCardjson/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas changeCardjson(Model model,HttpServletRequest request,HttpServletResponse response,UserCard userCard,Integer userId) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try { 
			
			if(userCard.getCode()==null){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("参数缺失");
				return returnObject;
			}
			
			Page page=newPage(request);
			
			page.setPageSize(1);
			
			Finder finder=Finder.getSelectFinder(UserCard.class).append(" where 1=1 ");

			//接口传过来一个userId但是查的时候并不需要userid  只要一个code  所以把这个userid置为null
			userCard.setUserId(null);
			
			//查出来的单个卡券
			List<UserCard> usercards=userCardService.findListDataByFinder(finder, page, UserCard.class, userCard);
			
			if(usercards.size()==0){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("此卡券不存在");
				return returnObject;
			}
			
			//用户的购买的卡券信息
			UserCard usercard=usercards.get(0);
			
			//发布人发布的卡券信息
			Card card=null;
			
			//判断是否过期
			if(usercard.getExpTime()!=null){
				if(usercard.getExpTime().getTime()<new Date().getTime()){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("此卡券已过期");
					return returnObject;
				}
			}
			
			//判断是否兑换
			if(usercard.getStatus()!=null&&usercard.getStatus()!=1){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("此卡券已兑换");
				return returnObject;
			}
			
			
			if(usercard.getCardId()!=null){
				card=cardService.findCardById(usercard.getCardId());
			}
			
			if(card==null&&userId!=card.getUserId()){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("此卡券不存在");
				return returnObject;
			}
			
			//改变状态
			usercard.setStatus(2);
			usercard.setChangeTime(new Date());
			userCardService.update(usercard, true);
			
			//给发布人发推送
			notificationService.notify(4, card.getId(), card.getUserId());
			
			//手续费比例
			BigDecimal cardCharge=new BigDecimal(0.0);
			
			SysSysparam sysSysparam = sysparamService.findSysSysparamById("cardCharge");
			if(sysSysparam!=null){
				cardCharge=new BigDecimal(sysSysparam.getValue());
			}
			
			AppUser appUser=appUserService.findAppUserById(userId);
			if(appUser!=null){
				
				BigDecimal sumMoney=new BigDecimal(0.0);
				
				//算出这次总共是多少钱的收益
				if(usercard.getSumMoney()!=null){
					sumMoney=new BigDecimal(usercard.getSumMoney()).multiply(cardCharge);
					sumMoney=new BigDecimal(usercard.getSumMoney()).subtract(sumMoney);
				}
				
				if(appUser.getBalance()==null){
					appUser.setBalance(sumMoney.doubleValue());
				}else{
					appUser.setBalance(appUser.getBalance()+sumMoney.doubleValue());
				}
				
				appUserService.update(appUser,true);
				
				
				//先保存收益记录
				MoneyDetail moneyDetail=new MoneyDetail();
				moneyDetail.setUserId(userId);
				moneyDetail.setCreateTime(new Date());
				moneyDetail.setType(8);
				moneyDetail.setMoney(sumMoney.doubleValue());
				moneyDetail.setItemId(usercard.getId());
				moneyDetail.setBalance(appUser.getBalance());
				
				moneyDetailService.save(moneyDetail);
				
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
	 * 后台展示列表
	 * @author wj
	 * @param request
	 * @param model
	 * @param card
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listAdmin/json")
	public @ResponseBody
	ReturnDatas listAminJson(HttpServletRequest request, Model model,Card card) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		card.setIsDel(0);
		List<Card> datas = cardService.findListDataByFinder(null,page,Card.class,card);
		if(null != datas && datas.size() > 0){
			for (Card cd : datas) {
				if(null != cd.getUserId()){
					AppUser appUser = appUserService.findAppUserById(cd.getUserId());
					if(null != appUser){
						if(StringUtils.isNotBlank(appUser.getName())){
							cd.setUserName(appUser.getName());
						}
					}
				}
				
				if(null != cd.getCatergoryId()){
					Category category = categoryService.findCategoryById(cd.getCatergoryId());
					if(null != category){
						if(StringUtils.isNotBlank(category.getName())){
							cd.setCategoryName(category.getName());
						}
					}
				}
			}
		}
		returnObject.setQueryBean(card);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	/**
	 * 审核通过
	 * @author wj
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/check/confirm")
	public @ResponseBody
	ReturnDatas checkConfirm(HttpServletRequest request,Model model,Card card) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String id = request.getParameter("id");
		card = cardService.findCardById(Integer.parseInt(id));
		if(null != card){
			card.setStatus(2);
			card.setSuccTime(new Date());
			cardService.update(card,true);
			notificationService.notify(20, Integer.parseInt(id), card.getUserId());
		}
		return returnObject;
	}
	
	/**
	 * 审核拒绝
	 * @author wj
	 * @param request
	 * @param model
	 * @param card
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/check/refuse")
	public @ResponseBody
	ReturnDatas checkRefuse(HttpServletRequest request,Model model,Card card) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String id = request.getParameter("id");
		String refuseReason = request.getParameter("reason");
		card = cardService.findCardById(Integer.parseInt(id));
		if(null != card){
			card.setStatus(3);
			card.setFailTime(new Date());
			card.setFailReason(refuseReason);
			cardService.update(card,true);
			notificationService.notify(18, Integer.parseInt(id), card.getUserId());
		}
		return returnObject;
	}
	
}
