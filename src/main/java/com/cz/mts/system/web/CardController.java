package  com.cz.mts.system.web;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

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
import com.cz.mts.system.entity.Attention;
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
import com.cz.mts.system.service.IAttentionService;
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
	private IAttentionService attentionService;
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
				 page.setOrder("createTime");
				 page.setSort("desc");
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
			 
			 String cityIds= ""; 
			 //返回城市名称
			 Finder finder = new Finder("SELECT * FROM t_red_city WHERE packageId=:id AND type=3");
			 finder.setParam("id", Integer.parseInt(strId));
			 List<RedCity> redCities = redCityService.queryForList(finder,RedCity.class);
			 if(null != redCities && redCities.size() > 0){
				 for (RedCity redCity : redCities) {
					 if(null != redCity.getCityId() && 0 != redCity.getCityId()){
						 cityIds += redCity.getCityId()+",";
					 }
					if(null != redCity.getCityId()){
						City city = cityService.findCityById(redCity.getCityId());
						if(null != city && StringUtils.isNotBlank(city.getName())){
							redCity.setCityName(city.getName());
						}
					}
				}
				 card.setCityIds(cityIds);
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
				//cityIds是否为空
				if(StringUtils.isNotBlank(card.getCityIds())){
					String cityIds[] = card.getCityIds().split(",");
					//删除红包表中的记录
					Finder finder = new Finder("DELETE FROM t_red_city WHERE type=3 and packageId=:packageId");
					finder.setParam("packageId", card.getId());
					redCityService.update(finder);
					for (String cid : cityIds) {
						Integer cityId = Integer.parseInt(cid);
						//更新redCity表
						RedCity redCity = new RedCity();
						redCity.setCityId(cityId);
						redCity.setPackageId(card.getId());
						redCity.setType(3);
						redCityService.save(redCity);
					}
				}
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
	 * 后台删除操作
	 */
	@RequestMapping(value="/deleteadmin")
	public @ResponseBody ReturnDatas deleteadmin(HttpServletRequest request) throws Exception {

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
				card.setShareNum(0);
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
				card.setStatus(1);
				cardService.update(card,true);
				returnObject.setData(cardService.findCardById(card.getId()));
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
	 * 用户购买审核通过的卡券接口
	 * @author wml
	 * @param model
	 * @param request
	 * @param response
	 * @param num
	 * @param cardId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/payCard/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas payCardjson(Model model,HttpServletRequest request,HttpServletResponse response,Integer num,Integer cardId,Integer userId,String osType) throws Exception{
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
			
			//判断是否是自己购买
			if(null != card.getUserId() && userId.intValue() == card.getUserId().intValue()){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("自己不能购买自己发布的卡券");
				return returnObject;
			}
			//判断数量
			if(card.getNum()<num){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("卡券数量不足");
				return returnObject;
			}
			
			//判断卡券的状态
			if(2 != card.getStatus()){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("此卡券暂不能购买");
				return returnObject;
			}
			List<UserCard> userCards=new ArrayList<>();
			
			String code=new Date().getTime()+""+RandomUtils.nextInt(1, 9);
			
			//新增usercard
			for (int i = 0; i < num; i++) {
				UserCard userCar = new UserCard();
				
				/***生成6位不重复的兑换码开始**/
				String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
					"g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
					"t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
					"6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
					"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
					"W", "X", "Y", "Z" };

				StringBuffer shortBuffer = new StringBuffer();
				String uuid = UUID.randomUUID().toString().replace("-", "");
				for (int j = 0; j < 6; j++) {
					String str = uuid.substring(j * 4, j * 4 + 4);
					int x = Integer.parseInt(str, 16);
					shortBuffer.append(chars[x % 0x3E]);
				}
				/***生成6位不重复的兑换码结束**/
				
				userCar.setCardCode(shortBuffer.toString());
				
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
				userCar.setOsType(osType);
				
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
			
			if(userCard.getCardCode()==null){
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
			//判断卡券的状态
			if(2 != card.getStatus()){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("此卡券暂不能兑换");
				return returnObject;
			}
			
			//改变状态
			usercard.setStatus(2);
			usercard.setChangeTime(new Date());
			Object id = userCardService.update(usercard, true);
			UserCard uc = userCardService.findUserCardById(id);
			if(null != uc && null != uc.getUserId()){
				AppUser user = appUserService.findAppUserById(uc.getUserId());
				if(null != user && 1 == user.getIsPush() && null != uc.getCardId()){
					//给自己发推送
					notificationService.notify(14, uc.getCardId(), user.getId());
					
				}
			}
			
			//手续费比例
			BigDecimal cardCharge=new BigDecimal(0.0);
			
			SysSysparam sysSysparam = sysparamService.findSysSysparamById("cardCharge");
			if(sysSysparam!=null){
				cardCharge=new BigDecimal(sysSysparam.getValue());
			}
			
			AppUser appUser=appUserService.findAppUserById(userId);
			if(appUser!=null){
				
				BigDecimal sumMoney=new BigDecimal(0.0);
				BigDecimal cardChargeMoney = new BigDecimal(0.0);
				
				//判断是否关闭卡券手续费
				if(null != appUser.getIsCloseFee() && 0 == appUser.getIsCloseFee()){
					//算出这次总共是多少钱的收益
					if(usercard.getSumMoney()!=null){
						cardChargeMoney=new BigDecimal(usercard.getSumMoney()).multiply(cardCharge);
						sumMoney=new BigDecimal(usercard.getSumMoney()).subtract(cardChargeMoney);
					}
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
				moneyDetail.setPlateMoney(cardChargeMoney.doubleValue());
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
		Finder finder = Finder.getSelectFinder(Card.class).append("where 1=1");
		if(StringUtils.isNotBlank(card.getCategoryName())){
			finder.append(" and catergoryId in(select id from t_category where type=3 and INSTR(`name`,:categoryName)>0 )");
			finder.setParam("categoryName", card.getCategoryName());
		}
		if(StringUtils.isNotBlank(card.getUserName())){
			finder.append(" and userId in(select id from t_app_user where INSTR(`name`,:userName)>0 )");
			finder.setParam("userName", card.getUserName());
		}
		
		if(StringUtils.isNotBlank(card.getStartTime())){
			finder.append(" and endTime > :startTime ");
			finder.setParam("startTime", card.getStartTime());
		}
		
		if(StringUtils.isNotBlank(card.getEnddTime())){
			finder.append(" and endTime < :endTime ");
			finder.setParam("endTime", card.getEnddTime());
		}
		if(null != card.getStatus()){
			if(5 == card.getStatus()){
				card.setStatus(null);
				finder.append(" and status=2 and num=0");
			}
		}
		
		List<Card> datas = cardService.findListDataByFinder(finder,page,Card.class,card);
		if(null != datas && datas.size() > 0){
			for (Card cd : datas) {
				if(null != cd.getStatus()){
					if(1 == cd.getStatus()){
						cd.setCardStatus("审核中");
					}
					if(2 == cd.getStatus()){
						cd.setCardStatus("审核成功");
					}
					if(3 == cd.getStatus()){
						cd.setCardStatus("审核失败");
					}
					if(4 == cd.getStatus()){
						cd.setCardStatus("已过期");
					}
					if(2 == cd.getStatus() && 0 == cd.getNum()){
						cd.setCardStatus("已领完");
					}
				}
				
				
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
		AppUser appUser = appUserService.findAppUserById(card.getUserId());
		if(null != card){
			card.setStatus(2);
			card.setSuccTime(new Date());
			cardService.update(card,true);
			if(null != appUser && 1 == appUser.getIsPush()){
				notificationService.notify(20, Integer.parseInt(id), card.getUserId());
			}
			
			//更新attention表中的isUpdate字段
			Finder finderAtte = new Finder("UPDATE t_attention SET isUpdate = 1 WHERE itemId = :itemId");
			finderAtte.setParam("itemId", card.getUserId());
			attentionService.update(finderAtte);
			//更新appUser表中的isUpdate字段
			Finder finderAppUser = new Finder("UPDATE t_app_user SET isUpdate = 1 WHERE id in (SELECT DISTINCT userId FROM t_attention WHERE itemId = :itemId)");
			finderAppUser.setParam("itemId",  card.getUserId());
			appUserService.update(finderAppUser);
			
			
			//查询接收推送的用户
			Finder finderSelect = new Finder("SELECT * FROM t_attention WHERE itemId = :itemId");
			finderSelect.setParam("itemId", card.getUserId());
			List<Attention> attentions = cardService.queryForList(finderSelect,Attention.class);
			for (Attention attention : attentions) {
				AttenThreadController attenThreadController = new AttenThreadController(null, null, attention, card, notificationService, appUser);
				attenThreadController.run();
			}
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
		AppUser appUser = appUserService.findAppUserById(card.getUserId());
		if(null != card){
			card.setStatus(3);
			card.setFailTime(new Date());
			card.setFailReason(refuseReason);
			cardService.update(card,true);
			if(null != appUser && 1 == appUser.getIsPush()){
				notificationService.notify(18, Integer.parseInt(id), card.getUserId());
			}
		}
		return returnObject;
	}
	
}
