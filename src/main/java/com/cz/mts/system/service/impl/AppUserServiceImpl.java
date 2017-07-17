package com.cz.mts.system.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.print.attribute.standard.Media;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Appoint;
import com.cz.mts.system.entity.Attention;
import com.cz.mts.system.entity.Card;
import com.cz.mts.system.entity.Circle;
import com.cz.mts.system.entity.CityCircle;
import com.cz.mts.system.entity.Collect;
import com.cz.mts.system.entity.LmediaPackage;
import com.cz.mts.system.entity.LposterPackage;
import com.cz.mts.system.entity.Medal;
import com.cz.mts.system.entity.MediaPackage;
import com.cz.mts.system.entity.Menu;
import com.cz.mts.system.entity.MoneyDetail;
import com.cz.mts.system.entity.PosterPackage;
import com.cz.mts.system.entity.Role;
import com.cz.mts.system.entity.RoleMenu;
import com.cz.mts.system.entity.User;
import com.cz.mts.system.entity.UserCard;
import com.cz.mts.system.entity.UserMedal;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.ICardService;
import com.cz.mts.system.service.ICircleService;
import com.cz.mts.system.service.ILmediaPackageService;
import com.cz.mts.system.service.ILposterPackageService;
import com.cz.mts.system.service.IPosterPackageService;
import com.cz.mts.system.service.IUserMedalService;
import com.cz.mts.system.service.NotificationService;
import com.cz.mts.frame.entity.IBaseEntity;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.JsonUtils;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.system.service.BaseSpringrainServiceImpl;
import com.cz.mts.system.web.AttenThreadController;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:23
 * @see com.cz.mts.system.service.impl.AppUser
 */
@Service("appUserService")
public class AppUserServiceImpl extends BaseSpringrainServiceImpl implements IAppUserService {

	@Resource
	private ICardService cardService;
	@Resource
	private NotificationService notificationService;
	@Resource
	private IUserMedalService userMedalService;
	@Resource
	private ICircleService circleService;
	@Resource
	private IAppUserService appUserService;
	@Resource
	private ILposterPackageService lposterPackageService;
	@Resource
	private IPosterPackageService posterPackageService;
	@Autowired
	private RedisConnectionFactory redisConnectionFactory ;
	@Resource
	private ILmediaPackageService lmediaPackageService;
	
   
    @Override
	public String  save(Object entity ) throws Exception{
	      AppUser appUser=(AppUser) entity;
	       return super.save(appUser).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      AppUser appUser=(AppUser) entity;
		 return super.saveorupdate(appUser).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 AppUser appUser=(AppUser) entity;
	return super.update(appUser);
    }
    @Override
	public AppUser findAppUserById(Object id) throws Exception{
    	
    	AppUser appUser = new AppUser();
    	
    	appUser=super.findById(id,AppUser.class);
    	
    	Page newPage = new Page();
    	if(null != appUser){
    		UserMedal userMedal = new UserMedal();
     		userMedal.setUserId(appUser.getId());
     		userMedal.setIsEndStatus(0);
     		//查询勋章列表
     		List<UserMedal> userMedals = super.findListDataByFinder(null, newPage, UserMedal.class, userMedal);
     		if(null != userMedals && userMedals.size() > 0){
     			for (UserMedal um : userMedals) {
     				if(null != um.getMedalId()){
     					Medal medal = super.findById(um.getMedalId(), Medal.class);
     					if(null != medal){
     						um.setMedal(medal);
     					}
     				}
     			}
     			appUser.setUserMedals(userMedals);
     		}
        	
    	}

    	
	 return appUser;
	 
	
	 
	 
	 
	}
	
/**
 * 列表查询,每个service都会重载,要把sql语句封装到service中,Finder只是最后的方案
 * @param finder
 * @param page
 * @param clazz
 * @param o
 * @return
 * @throws Exception
 */
        @Override
    public <T> List<T> findListDataByFinder(Finder finder, Page page, Class<T> clazz,
			Object o) throws Exception{
			 return super.findListDataByFinder(finder,page,clazz,o);
			}
	/**
	 * 根据查询列表的宏,导出Excel
	 * @param finder 为空则只查询 clazz表
	 * @param ftlurl 类表的模版宏
	 * @param page 分页对象
	 * @param clazz 要查询的对象
	 * @param o  querybean
	 * @return
	 * @throws Exception
	 */
		@Override
	public <T> File findDataExportExcel(Finder finder,String ftlurl, Page page,
			Class<T> clazz, Object o)
			throws Exception {
			 return super.findDataExportExcel(finder,ftlurl,page,clazz,o);
		}

	@Override
	public Integer pay(Integer userId, Integer type, Integer itemId,String code,String osType)
			throws Exception {
		// TODO Auto-generated method stub
		
		
		if(userId==null||type==null){
			return 2;
		}
		
		//查询支付用户
		AppUser appUser=super.findById(userId, AppUser.class);
		if(appUser==null){
			return 3;
		}
		
		//判断购买的是什么类型的红包  1支付的海报红包   2支付的视频红包   3支付的卡券红包    4预约支付    5打赏
		switch (type) {
		case 1:
			if(itemId==null){
				return 2;
			}
			//查询出来海报红包的数据
			PosterPackage posterPackage=super.findById(itemId, PosterPackage.class);
			if(posterPackage==null||posterPackage.getSumMoney()==null||userId.intValue()!=posterPackage.getUserId().intValue()){
				return 4;
			}
			
			//判断用户余额足不足
			if(appUser.getBalance()<posterPackage.getSumMoney()){
				return 5;
			}
			
			if(posterPackage.getSumMoney() <=0){
				return 6;
			}
			
			//扣除余额并且加到余额记录
			appUser.setBalance(new BigDecimal(appUser.getBalance()).subtract(new BigDecimal(posterPackage.getSumMoney())).doubleValue());
			super.update(appUser, true);
			//改变红包状态
			//查询该用户是否有免审核勋章
			Finder finderUserMedal = Finder.getSelectFinder(UserMedal.class).append("where 1=1 and userId=:userId AND medalId in (SELECT id FROM t_medal WHERE STATUS=1) AND (isEndStatus IS NULL OR isEndStatus != 1)");
			finderUserMedal.setParam("userId", userId);
			List<UserMedal> userMedals = userMedalService.queryForList(finderUserMedal, UserMedal.class);
			if(null != userMedals && userMedals.size() > 0){
				//说明有免审核勋章
				posterPackage.setStatus(3);
				
				 //更新attention表中的isUpdate字段
				Finder finderAtte = new Finder("UPDATE t_attention SET isUpdate = 1 WHERE itemId = :itemId");
				finderAtte.setParam("itemId", posterPackage.getUserId());
				super.update(finderAtte);
				//更新appUser表中的isUpdate字段
				Finder finderAppUser = new Finder("UPDATE t_app_user SET isUpdate = 1 WHERE id in (SELECT DISTINCT userId FROM t_attention WHERE itemId = :itemId)");
				finderAppUser.setParam("itemId",  posterPackage.getUserId());
				super.update(finderAppUser);
				
				//查询接收推送的用户
				Finder finderSelect = new Finder("SELECT * FROM t_attention WHERE itemId = :itemId");
				finderSelect.setParam("itemId", posterPackage.getUserId());
				List<Attention> attentions = super.queryForList(finderSelect,Attention.class);
				for (Attention attention : attentions) {
					AttenThreadController attenThreadController = new AttenThreadController(posterPackage, null, attention, null, notificationService, appUser);
					attenThreadController.run();
				}
				
				//先看看分几个人，要是分一个人的话就不用分了，直接生成一个就好了
				if(posterPackage.getLqNum() != null && posterPackage.getLqNum() == 1){
					LposterPackage lp = new LposterPackage();
					Double money = new Double(String.valueOf(posterPackage.getSumMoney())) ;
					lp.setMoney(money);
					lp.setPackageId(itemId);
					lposterPackageService.save(lp) ;
				}else {
					//开始分小红包
					Long total = (new BigDecimal(posterPackage.getSumMoney()).multiply(new BigDecimal(100))).longValue() ;
					long[] moneys = generate(total, posterPackage.getLqNum(), total, 1) ;
					List<LposterPackage> list = new ArrayList<>() ;
					for (int i = 0; i < moneys.length; i++) {
						LposterPackage lp = new LposterPackage();
						Double money = new Double(String.valueOf(moneys[i])) ;
						lp.setMoney(money/100);
						lp.setPackageId(itemId);
						
						list.add(lp) ;
					}
					//批量保存小红包
					lposterPackageService.save(list) ;
				}
				//如果没有异常，就往redis中存放
				//获取jedis客户端
				Jedis jedis = (Jedis) redisConnectionFactory.getConnection().getNativeConnection() ;
				jedis.sadd(GlobalStatic.posterPackage, GlobalStatic.posterPackageL+itemId) ;  //待抢小红包
				jedis.sadd(GlobalStatic.posterPackage, GlobalStatic.posterPackageConsumedList +itemId) ;  //已抢小红包List
				jedis.sadd(GlobalStatic.posterPackage, GlobalStatic.posterPackageConsumedMap +itemId) ;  //已抢小红包Map
//				
				//因为不能像hibernate一样保存后就持久化，所以要重新取一下
				Finder finder = Finder.getSelectFinder(LposterPackage.class).append("where packageId = :packageId ") ;
				finder.setParam("packageId", itemId) ;
				List<LposterPackage> listPersistence = super.queryForList(finder, LposterPackage.class) ;
				
				Iterator<LposterPackage> iter = listPersistence.iterator() ;
				while(iter.hasNext()){
					LposterPackage lpWhile = iter.next() ;
					String lpStr = JsonUtils.writeValueAsString(lpWhile) ;
					
					jedis.lpush(GlobalStatic.posterPackageL+itemId, lpStr) ;
				}
				
				
				
				
				
				
				
				
				
			}else{
				posterPackage.setStatus(1);
			}
			posterPackage.setPayType(3);
			posterPackage.setPayMoney(posterPackage.getSumMoney());
			posterPackage.setPayTime(new Date());
			super.update(posterPackage,true);
			
			//记录用户的余额记录
			MoneyDetail moneyDetailp=new MoneyDetail();
			moneyDetailp.setBalance(appUser.getBalance());
			moneyDetailp.setCreateTime(new Date());
			moneyDetailp.setItemId(itemId);
			moneyDetailp.setMoney(-posterPackage.getSumMoney());
			moneyDetailp.setType(6);
			moneyDetailp.setPayType(3);
			moneyDetailp.setUserId(userId);
			moneyDetailp.setOsType(osType);
			super.save(moneyDetailp);
			
			break;
		case 2:
			if(itemId==null){
				return 2;
			}
			//查询视频红包的信息
			MediaPackage mediaPackage=super.findById(itemId, MediaPackage.class);
			if(mediaPackage==null||mediaPackage.getSumMoney()==null||userId.intValue()!=mediaPackage.getUserId().intValue()){
				return 4;
			}
			
			//判断用户余额足不足
			if(appUser.getBalance()<mediaPackage.getSumMoney()){
				return 5;
			}
			
			if(mediaPackage.getSumMoney() <=0){
				return 6;
			}
			
			//扣除余额并且加到余额记录
			appUser.setBalance(new BigDecimal(appUser.getBalance()).subtract(new BigDecimal(mediaPackage.getSumMoney())).doubleValue());
			super.update(appUser, true);
			//改变红包状态
			//查询该用户是否有免审核勋章
			Finder finderMedia = Finder.getSelectFinder(UserMedal.class).append("where 1=1 and userId=:userId AND medalId in (SELECT id FROM t_medal WHERE STATUS=2) AND (isEndStatus IS NULL OR isEndStatus != 1)");
			finderMedia.setParam("userId", userId);
			List<UserMedal> userMedalms = userMedalService.queryForList(finderMedia, UserMedal.class);
			if(null != userMedalms && userMedalms.size() > 0){
				//说明有免审核勋章
				mediaPackage.setStatus(3);
				
				//更新attention表中的isUpdate字段
				Finder finderAtte = new Finder("UPDATE t_attention SET isUpdate = 1 WHERE itemId = :itemId");
				finderAtte.setParam("itemId", mediaPackage.getUserId());
				super.update(finderAtte);
				//更新appUser表中的isUpdate字段
				Finder finderAppUser = new Finder("UPDATE t_app_user SET isUpdate = 1 WHERE id in (SELECT DISTINCT userId FROM t_attention WHERE itemId = :itemId)");
				finderAppUser.setParam("itemId",  mediaPackage.getUserId());
				appUserService.update(finderAppUser);
				
				//查询接收推送的用户
				Finder finderSelect = new Finder("SELECT * FROM t_attention WHERE itemId = :itemId");
				finderSelect.setParam("itemId", mediaPackage.getUserId());
				List<Attention> attentions = super.queryForList(finderSelect,Attention.class);
				for (Attention attention : attentions) {
					AttenThreadController attenThreadController = new AttenThreadController(null, mediaPackage, attention, null, notificationService, appUser);
					attenThreadController.run();
				}
				
				//先看看分几个人，要是分一个人的话就不用分了，直接生成一个就好了
				if(mediaPackage.getLqNum() != null && mediaPackage.getLqNum() == 1){
					LmediaPackage lp = new LmediaPackage();
					Double money = new Double(String.valueOf(mediaPackage.getSumMoney())) ;
					lp.setMoney(money);
					lp.setPackageId(itemId);
					lmediaPackageService.save(lp);
				}else {
					//开始分小红包
					Long total = (new BigDecimal(mediaPackage.getSumMoney()).multiply(new BigDecimal(100))).longValue() ;
					long[] moneys = generate(total, mediaPackage.getLqNum(), total, 1) ;
					List<LmediaPackage> list = new ArrayList<>() ;
					for (int i = 0; i < moneys.length; i++) {
						LmediaPackage lp = new LmediaPackage();
						Double money = new Double(String.valueOf(moneys[i])) ;
						lp.setMoney(money/100);
						lp.setPackageId(itemId);
						
						list.add(lp) ;
					}
					//批量保存小红包
					lmediaPackageService.save(list) ;
				}
				
				//如果没有异常，就往redis中存放
				//获取jedis客户端
				Jedis jedis = (Jedis) redisConnectionFactory.getConnection().getNativeConnection() ;
				HashMap<String, String> map = new HashMap<>() ;
				jedis.sadd(GlobalStatic.mediaPackage, GlobalStatic.mediaPackageL+itemId) ;  //待抢小红包
				jedis.sadd(GlobalStatic.mediaPackage, GlobalStatic.mediaPackageConsumedList +itemId) ;  //已抢小红包List
				jedis.sadd(GlobalStatic.mediaPackage, GlobalStatic.mediaPackageConsumedMap +itemId) ;  //已抢小红包Map
//				
				//因为不能像hibernate一样保存后就持久化，所以要重新取一下
				Finder finder = Finder.getSelectFinder(LmediaPackage.class).append("where packageId = :packageId ") ;
				finder.setParam("packageId", itemId) ;
				List<LmediaPackage> listPersistence = super.queryForList(finder, LmediaPackage.class) ;
				
				Iterator<LmediaPackage> iter = listPersistence.iterator() ;
				while(iter.hasNext()){
					LmediaPackage lpWhile = iter.next() ;
					String lpStr = JsonUtils.writeValueAsString(lpWhile) ;
					
					jedis.lpush(GlobalStatic.mediaPackageL+itemId, lpStr) ;
				}
				
				
				
				
			}else{
				mediaPackage.setStatus(1);
			}
			mediaPackage.setPayType(3);
			mediaPackage.setPayMoney(mediaPackage.getSumMoney());
			mediaPackage.setPayTime(new Date());
			super.update(mediaPackage,true);
			
			//记录用户的余额记录
			MoneyDetail moneyDetailM=new MoneyDetail();
			moneyDetailM.setBalance(appUser.getBalance());
			moneyDetailM.setCreateTime(new Date());
			moneyDetailM.setItemId(itemId);
			moneyDetailM.setMoney(-mediaPackage.getSumMoney());
			moneyDetailM.setType(5);
			moneyDetailM.setPayType(3);
			moneyDetailM.setUserId(userId);
			moneyDetailM.setOsType(osType);
			super.save(moneyDetailM);
			
			break;
		case 3:
			//卡券的列表
			if(StringUtils.isBlank(code)){
				return 2;
			}
			//查询出来所有的卡券
			Finder finder=Finder.getSelectFinder(UserCard.class).append(" where 1=1 and code= :code and userId= :userId ");
			finder.setParam("code", code);
			finder.setParam("userId", userId);
			List<UserCard> cards=super.queryForList(finder, UserCard.class);
			if(cards.size()==0){
				return 4;
			}
			//定义一个卡券的所有金额
			BigDecimal cardSum=new BigDecimal(0.0);
			
			if(cards.get(0).getSumMoney()!=null){
				cardSum=new BigDecimal(cards.size()).multiply(new BigDecimal(cards.get(0).getSumMoney()));
			}
			
			
			
			//判断用户余额足不足
			if(appUser.getBalance()<cardSum.doubleValue()){
				return 5;
			}
			
			if(cardSum.doubleValue() <=0){
				return 6;
			}
			
			
			//扣除余额并且加到余额记录
			appUser.setBalance(new BigDecimal(appUser.getBalance()).subtract(cardSum).doubleValue());
			super.update(appUser, true);
			Card card = cardService.findCardById(cards.get(0).getCardId());
			if(0.0 != cardSum.doubleValue()){
				//记录用户的余额记录
				MoneyDetail moneyDetailC=new MoneyDetail();
				moneyDetailC.setBalance(appUser.getBalance());
				moneyDetailC.setCreateTime(new Date());
				moneyDetailC.setItemId(itemId);
				moneyDetailC.setMoney(-cardSum.doubleValue());
				moneyDetailC.setType(3);
				moneyDetailC.setPayType(3);
				moneyDetailC.setUserId(userId);
				moneyDetailC.setOsType(cards.get(0).getOsType());
				moneyDetailC.setPublishUserId(card.getUserId());
				super.save(moneyDetailC);
			}
			
			for (UserCard userCard : cards) {
				userCard.setPayMoney(userCard.getSumMoney());
				userCard.setPayType(3);
				userCard.setPayTime(new Date());
				userCard.setStatus(1);
				userCard.setPublishUserId(card.getUserId());
			}
			
			super.update(cards, true);
			
			//更新card中的convertNum字段
			if(null != card){
				card.setConvertNum(card.getConvertNum()-cards.size());
				cardService.update(card, true);
				
				if(card.getNum().intValue()<=0){
					//查询用户信息
					if(null != appUser && 1 == appUser.getIsPush()){
						notificationService.notify(5, card.getId(), card.getUserId());
					}
				}
				
//				if(0 == card.getConvertNum()){
//					
//					card.setStatus(4);
//					cardService.update(card, true);
//					//查询用户信息
//					if(null != appUser && 1 == appUser.getIsPush()){
//						notificationService.notify(5, card.getId(), card.getUserId());
//					}
//				}
			}
			
			break;
			
		case 4:
			if(StringUtils.isBlank(code)){
				return 2;
			}
			//根据userId和code查询预约信息
			Finder finderAppoint=Finder.getSelectFinder(Appoint.class).append(" where 1=1 and code= :code and userId= :userId ");
			finderAppoint.setParam("code", code);
			finderAppoint.setParam("userId", userId);
			Appoint appoint = null;
			List<Appoint> appoints=super.queryForList(finderAppoint, Appoint.class);
			if(null != appoints && appoints.size() > 0){
				appoint = appoints.get(0);
			}
			if(appoint==null||appoint.getMoney()==null||userId.intValue()!=appoint.getUserId().intValue()){
				return 4;
			}
			
			//判断用户余额足不足
			if(appUser.getBalance()<appoint.getMoney()){
				return 5;
			}
			if(appoint.getMoney() <=0){
				return 6;
			}
			
			//扣除余额并且加到余额记录
			appUser.setBalance(new BigDecimal(appUser.getBalance()).subtract(new BigDecimal(appoint.getMoney())).doubleValue());
			super.update(appUser, true);
			//改变红包状态
			appoint.setStatus(1);
			appoint.setPayType(3);
			appoint.setPayMoney(appoint.getMoney());
			appoint.setPayTime(new Date());
			
			
			

			//1给海报红包加一个预约数量   2给视频红包加一个预约数量
			switch (appoint.getType()) {
			case 1:
				
				PosterPackage posterPackageApp=super.findById(appoint.getItemId(), PosterPackage.class);
				
				//查询海报红包
				if(posterPackageApp!=null){
					
					if(posterPackageApp.getAppointCount()==null){
						posterPackageApp.setAppointCount(1);
					}else {
						posterPackageApp.setAppointCount(posterPackageApp.getAppointCount()+1);
					}
					super.update(posterPackageApp);
				}
				
				break;
			case 2:
				
				MediaPackage mediaPackageApp=super.findById(appoint.getItemId(), MediaPackage.class);
				
				if(mediaPackageApp!=null){
					
					if(mediaPackageApp.getAppointCount()==null){
						mediaPackageApp.setAppointCount(1);
						
					}else {
						mediaPackageApp.setAppointCount(mediaPackageApp.getAppointCount()+1);
					}
					super.update(mediaPackageApp);
				}
				
				break;
			}
			
			
			super.update(appoint,true);
			
			//记录用户的余额记录
			MoneyDetail moneyDetailA=new MoneyDetail();
			moneyDetailA.setBalance(appUser.getBalance());
			moneyDetailA.setCreateTime(new Date());
			moneyDetailA.setItemId(itemId);
			moneyDetailA.setMoney(-appoint.getMoney());
			moneyDetailA.setType(13);
			moneyDetailA.setPayType(3);
			moneyDetailA.setUserId(userId);
			moneyDetailA.setOsType(osType);
			super.save(moneyDetailA);
			
			break;
		case 5:
			if(StringUtils.isBlank(code)){
				return 2;
			}
			//根据userId和code查询预约信息
			Finder finderCircle=Finder.getSelectFinder(CityCircle.class).append(" where 1=1 and code= :code and userId= :userId ");
			finderCircle.setParam("code", code);
			finderCircle.setParam("userId", userId);
			CityCircle cityCircle = null;
			List<CityCircle> cityCircles = super.queryForList(finderCircle, CityCircle.class);
			if(null != cityCircles && cityCircles.size() > 0){
				cityCircle = cityCircles.get(0);
			}
			
			if(cityCircle==null||cityCircle.getMoney()==null||userId.intValue()!=cityCircle.getUserId().intValue()){
				return 4;
			}
			
			//判断用户余额足不足
			if(appUser.getBalance()<cityCircle.getMoney()){
				return 5;
			}
			
			if(cityCircle.getMoney() <=0){
				return 6;
			}
			//扣除余额并且加到余额记录
			appUser.setBalance(new BigDecimal(appUser.getBalance()).subtract(new BigDecimal(cityCircle.getMoney())).doubleValue());
			super.update(appUser, true);
			//改变状态
			cityCircle.setStatus(1);
			cityCircle.setPayType(3);
			cityCircle.setPayMoney(cityCircle.getMoney());
			cityCircle.setPayTime(new Date());
			
			//更新circle表的打赏总金额字段
			Finder circleFinder = new Finder("UPDATE t_circle SET sumMoney=sumMoney + :sumMoney WHERE id=:id");
			circleFinder.setParam("sumMoney", cityCircle.getMoney());
			circleFinder.setParam("id", cityCircle.getItemId());
			super.update(circleFinder);
			
			
			super.update(cityCircle,true);
			
			//记录用户的余额记录
			MoneyDetail moneyDetailC=new MoneyDetail();
			moneyDetailC.setBalance(appUser.getBalance());
			moneyDetailC.setCreateTime(new Date());
			moneyDetailC.setItemId(itemId);
			moneyDetailC.setMoney(-cityCircle.getMoney());
			moneyDetailC.setType(14);
			moneyDetailC.setPayType(3);
			moneyDetailC.setUserId(userId);
			moneyDetailC.setOsType(osType);
			super.save(moneyDetailC);
			
			//更新被打赏用户的记录
			Circle circle = circleService.findCircleById(itemId);
			if(null != circle && null != circle.getUserId()){
				AppUser circleAppUser = appUserService.findAppUserById(circle.getUserId());
				if(null != circleAppUser){
					if(null == circleAppUser.getBalance()){
						circleAppUser.setBalance(0.0);
					}
					circleAppUser.setBalance(new BigDecimal(circleAppUser.getBalance()).add(new BigDecimal(cityCircle.getMoney())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					
					MoneyDetail moneyDetailB=new MoneyDetail();
					moneyDetailB.setBalance(circleAppUser.getBalance());
					moneyDetailB.setCreateTime(new Date());
					moneyDetailB.setItemId(userId);
					moneyDetailB.setMoney(+cityCircle.getMoney());
					moneyDetailB.setType(16);
					moneyDetailB.setUserId(circleAppUser.getId());
					moneyDetailB.setOsType(osType);
					super.save(moneyDetailB);
					
					notificationService.notify(34, itemId, circle.getUserId());
					appUserService.update(circleAppUser,true);
				}
			}
			
			
			break;
			
		}
		
		return 1;
	}

	@Override
	public Integer alipay(String aliCode, Integer type, Double money,String wxCode, Integer payType) throws Exception {
		// TODO Auto-generated method stub
				
				String[] codes=aliCode.split("_");
				
				Integer itemId = null;
				if(type!=3){
					
					itemId=Integer.parseInt(codes[0].toString());
					
				}
				
				String code = codes[0].toString();
				
				//判断购买的是什么类型的红包  1支付的海报红包   2支付的视频红包   3支付的卡券红包  4预约支付   5打赏
				switch (type) {
				case 1:
					if(itemId==null){
						return 2;
					}
					//查询出来海报红包的数据
					PosterPackage posterPackage=super.findById(itemId, PosterPackage.class);
					if(posterPackage==null||posterPackage.getSumMoney()==null){
						return 4;
					}
					

					//看是不是第一次进来
					MoneyDetail moneyDetailP=new MoneyDetail();
					moneyDetailP.setItemId(itemId);
					moneyDetailP.setAliTrade(wxCode);
					moneyDetailP.setType(6);
					moneyDetailP.setUserId(posterPackage.getUserId());
					
					Page pageP=new Page();
					List<MoneyDetail> moneyDetailsP=super.findListDataByFinder(null, pageP, MoneyDetail.class	, moneyDetailP);
					
					if(moneyDetailsP.size()>0){
						return 4;
					}
					
					AppUser appUser=this.findAppUserById(posterPackage.getUserId());
					//改变红包状态
					//查询该用户是否有免审核勋章
					Finder finderUserMedal = Finder.getSelectFinder(UserMedal.class).append("where 1=1 and userId=:userId AND medalId in (SELECT id FROM t_medal WHERE STATUS=1) AND (isEndStatus IS NULL OR isEndStatus != 1)");
					finderUserMedal.setParam("userId", posterPackage.getUserId());
					List<UserMedal> userMedals = userMedalService.queryForList(finderUserMedal, UserMedal.class);
					if(null != userMedals && userMedals.size() > 0){
						//说明有免审核勋章
						posterPackage.setStatus(3);
						
						 //更新attention表中的isUpdate字段
						Finder finderAtte = new Finder("UPDATE t_attention SET isUpdate = 1 WHERE itemId = :itemId");
						finderAtte.setParam("itemId", posterPackage.getUserId());
						super.update(finderAtte);
						//更新appUser表中的isUpdate字段
						Finder finderAppUser = new Finder("UPDATE t_app_user SET isUpdate = 1 WHERE id in (SELECT DISTINCT userId FROM t_attention WHERE itemId = :itemId)");
						finderAppUser.setParam("itemId",  posterPackage.getUserId());
						super.update(finderAppUser);
						
						//查询接收推送的用户
						Finder finderSelect = new Finder("SELECT * FROM t_attention WHERE itemId = :itemId");
						finderSelect.setParam("itemId", posterPackage.getUserId());
						List<Attention> attentions = super.queryForList(finderSelect,Attention.class);
						for (Attention attention : attentions) {
							AttenThreadController attenThreadController = new AttenThreadController(posterPackage, null, attention, null, notificationService, appUser);
							attenThreadController.run();
						}
						
						//先看看分几个人，要是分一个人的话就不用分了，直接生成一个就好了
						if(posterPackage.getLqNum() != null && posterPackage.getLqNum() == 1){
							LposterPackage lp = new LposterPackage();
							Double money1 = new Double(String.valueOf(posterPackage.getSumMoney())) ;
							lp.setMoney(money1);
							lp.setPackageId(itemId);
							lposterPackageService.save(lp) ;
						}else {
							//开始分小红包
							Long total = (new BigDecimal(posterPackage.getSumMoney()).multiply(new BigDecimal(100))).longValue() ;
							long[] moneys = generate(total, posterPackage.getLqNum(), total, 1) ;
							List<LposterPackage> list = new ArrayList<>() ;
							for (int i = 0; i < moneys.length; i++) {
								LposterPackage lp = new LposterPackage();
								Double money1 = new Double(String.valueOf(moneys[i])) ;
								lp.setMoney(money1/100);
								lp.setPackageId(itemId);
								
								list.add(lp) ;
							}
							//批量保存小红包
							lposterPackageService.save(list) ;
						}
						//如果没有异常，就往redis中存放
						//获取jedis客户端
						Jedis jedis = (Jedis) redisConnectionFactory.getConnection().getNativeConnection() ;
						jedis.sadd(GlobalStatic.posterPackage, GlobalStatic.posterPackageL+itemId) ;  //待抢小红包
						jedis.sadd(GlobalStatic.posterPackage, GlobalStatic.posterPackageConsumedList +itemId) ;  //已抢小红包List
						jedis.sadd(GlobalStatic.posterPackage, GlobalStatic.posterPackageConsumedMap +itemId) ;  //已抢小红包Map
//						
						//因为不能像hibernate一样保存后就持久化，所以要重新取一下
						Finder finder = Finder.getSelectFinder(LposterPackage.class).append("where packageId = :packageId ") ;
						finder.setParam("packageId", itemId) ;
						List<LposterPackage> listPersistence = super.queryForList(finder, LposterPackage.class) ;
						
						Iterator<LposterPackage> iter = listPersistence.iterator() ;
						while(iter.hasNext()){
							LposterPackage lpWhile = iter.next() ;
							String lpStr = JsonUtils.writeValueAsString(lpWhile) ;
							
							jedis.lpush(GlobalStatic.posterPackageL+itemId, lpStr) ;
						}
						
						
						
						
						
						
						
						
						
					}else{
						posterPackage.setStatus(1);
					}
					posterPackage.setPayType(payType);
					if(payType==1){
						posterPackage.setTradeNo(wxCode);
					}else if (payType==2) {
						posterPackage.setWxCode(wxCode);
					}
					posterPackage.setPayMoney(posterPackage.getSumMoney());
					posterPackage.setPayTime(new Date());
					super.update(posterPackage,true);
					
					
					
					//记录用户的余额记录
					MoneyDetail moneyDetail=new MoneyDetail();
					moneyDetail.setBalance(appUser.getBalance());
					moneyDetail.setCreateTime(new Date());
					moneyDetail.setItemId(itemId);
					moneyDetail.setMoney(-posterPackage.getSumMoney());
					moneyDetail.setType(6);
					moneyDetail.setAliTrade(wxCode);
					moneyDetail.setPayType(payType);
					moneyDetail.setUserId(posterPackage.getUserId());
					super.save(moneyDetail);
					
					break;
				case 2:
					//查询视频红包的信息
					MediaPackage mediaPackage=super.findById(itemId, MediaPackage.class);
					if(mediaPackage==null||mediaPackage.getSumMoney()==null){
						return 4;
					}
					
					//看是不是第一次进来
					MoneyDetail moneyDetailMM=new MoneyDetail();
					moneyDetailMM.setItemId(itemId);
					moneyDetailMM.setType(5);
					moneyDetailMM.setAliTrade(wxCode);
					moneyDetailMM.setUserId(mediaPackage.getUserId());
					
					Page pageM=new Page();
					List<MoneyDetail> moneyDetails=super.findListDataByFinder(null, pageM, MoneyDetail.class, moneyDetailMM);
					
					if(moneyDetails.size()>0){
						return 4;
					}
					
					AppUser appUserM=this.findAppUserById(mediaPackage.getUserId());
					//改变红包状态
					//查询该用户是否有免审核勋章
					Finder finderMedia = Finder.getSelectFinder(UserMedal.class).append("where 1=1 and userId=:userId AND medalId in (SELECT id FROM t_medal WHERE STATUS=2) AND (isEndStatus IS NULL OR isEndStatus != 1)");
					finderMedia.setParam("userId", mediaPackage.getUserId());
					List<UserMedal> userMedalms = userMedalService.queryForList(finderMedia, UserMedal.class);
					if(null != userMedalms && userMedalms.size() > 0){
						//说明有免审核勋章
						mediaPackage.setStatus(3);
						
						//更新attention表中的isUpdate字段
						Finder finderAtte = new Finder("UPDATE t_attention SET isUpdate = 1 WHERE itemId = :itemId");
						finderAtte.setParam("itemId", mediaPackage.getUserId());
						super.update(finderAtte);
						//更新appUser表中的isUpdate字段
						Finder finderAppUser = new Finder("UPDATE t_app_user SET isUpdate = 1 WHERE id in (SELECT DISTINCT userId FROM t_attention WHERE itemId = :itemId)");
						finderAppUser.setParam("itemId",  mediaPackage.getUserId());
						appUserService.update(finderAppUser);
						
						//查询接收推送的用户
						Finder finderSelect = new Finder("SELECT * FROM t_attention WHERE itemId = :itemId");
						finderSelect.setParam("itemId", mediaPackage.getUserId());
						List<Attention> attentions = super.queryForList(finderSelect,Attention.class);
						for (Attention attention : attentions) {
							AttenThreadController attenThreadController = new AttenThreadController(null, mediaPackage, attention, null, notificationService, appUserM);
							attenThreadController.run();
						}
						
						//先看看分几个人，要是分一个人的话就不用分了，直接生成一个就好了
						if(mediaPackage.getLqNum() != null && mediaPackage.getLqNum() == 1){
							LmediaPackage lp = new LmediaPackage();
							Double money1 = new Double(String.valueOf(mediaPackage.getSumMoney())) ;
							lp.setMoney(money1);
							lp.setPackageId(itemId);
							lmediaPackageService.save(lp);
						}else {
							//开始分小红包
							Long total = (new BigDecimal(mediaPackage.getSumMoney()).multiply(new BigDecimal(100))).longValue() ;
							long[] moneys = generate(total, mediaPackage.getLqNum(), total, 1) ;
							List<LmediaPackage> list = new ArrayList<>() ;
							for (int i = 0; i < moneys.length; i++) {
								LmediaPackage lp = new LmediaPackage();
								Double money1 = new Double(String.valueOf(moneys[i])) ;
								lp.setMoney(money1/100);
								lp.setPackageId(itemId);
								
								list.add(lp) ;
							}
							//批量保存小红包
							lmediaPackageService.save(list) ;
						}
						
						//如果没有异常，就往redis中存放
						//获取jedis客户端
						Jedis jedis = (Jedis) redisConnectionFactory.getConnection().getNativeConnection() ;
						HashMap<String, String> map = new HashMap<>() ;
						jedis.sadd(GlobalStatic.mediaPackage, GlobalStatic.mediaPackageL+itemId) ;  //待抢小红包
						jedis.sadd(GlobalStatic.mediaPackage, GlobalStatic.mediaPackageConsumedList +itemId) ;  //已抢小红包List
						jedis.sadd(GlobalStatic.mediaPackage, GlobalStatic.mediaPackageConsumedMap +itemId) ;  //已抢小红包Map
//						
						//因为不能像hibernate一样保存后就持久化，所以要重新取一下
						Finder finder = Finder.getSelectFinder(LmediaPackage.class).append("where packageId = :packageId ") ;
						finder.setParam("packageId", itemId) ;
						List<LmediaPackage> listPersistence = super.queryForList(finder, LmediaPackage.class) ;
						
						Iterator<LmediaPackage> iter = listPersistence.iterator() ;
						while(iter.hasNext()){
							LmediaPackage lpWhile = iter.next() ;
							String lpStr = JsonUtils.writeValueAsString(lpWhile) ;
							
							jedis.lpush(GlobalStatic.mediaPackageL+itemId, lpStr) ;
						}
						
						
						
						
					}else{
						mediaPackage.setStatus(1);
					}
					mediaPackage.setPayType(payType);
					if(payType==1){
						mediaPackage.setTradeNo(wxCode);
					}else if (payType==2) {
						mediaPackage.setWxCode(wxCode);
					}
					mediaPackage.setPayMoney(mediaPackage.getSumMoney());
					mediaPackage.setPayTime(new Date());
					super.update(mediaPackage,true);
					
					
					
					//记录用户的余额记录
					MoneyDetail moneyDetailM=new MoneyDetail();
					moneyDetailM.setBalance(appUserM.getBalance());
					moneyDetailM.setCreateTime(new Date());
					moneyDetailM.setItemId(itemId);
					moneyDetailM.setMoney(-mediaPackage.getSumMoney());
					moneyDetailM.setType(5);
					moneyDetailM.setAliTrade(wxCode);
					moneyDetailM.setPayType(payType);
					moneyDetailM.setUserId(mediaPackage.getUserId());
					super.save(moneyDetailM);
					
					break;
				case 3:
					//卡券的列表
					if(StringUtils.isBlank(code)){
						return 2;
					}
					//查询出来所有的卡券
					Finder finder=Finder.getSelectFinder(UserCard.class).append(" where 1=1 and code= :code ");
					finder.setParam("code", code);
					List<UserCard> cards=super.queryForList(finder, UserCard.class);
					if(cards.size()==0){
						return 4;
					}
					//定义一个卡券的所有金额
					BigDecimal cardSum=new BigDecimal(0.0);
					
					if(cards.get(0).getSumMoney()!=null){
						cardSum=new BigDecimal(cards.size()).multiply(new BigDecimal(cards.get(0).getSumMoney()));
					}
					AppUser appUserC=this.findAppUserById(cards.get(0).getUserId());
					
					//看是不是第一次进来
					MoneyDetail moneyDetailCC=new MoneyDetail();
					moneyDetailCC.setCode(code);
					moneyDetailCC.setType(3);
					moneyDetailCC.setAliTrade(wxCode);
					moneyDetailCC.setUserId(cards.get(0).getUserId());
					
					Page pageC=new Page();
					List<MoneyDetail> moneyDetailsC=super.findListDataByFinder(null, pageC, MoneyDetail.class	, moneyDetailCC);
					
					if(moneyDetailsC.size()>0){
						return 4;
					}
					Card card = cardService.findCardById(cards.get(0).getCardId());
					for (UserCard userCard : cards) {
						userCard.setPayMoney(userCard.getSumMoney());
						userCard.setPayType(payType);
						userCard.setPayTime(new Date());
						userCard.setStatus(1);
						if(payType==1){
							userCard.setTradeNo(wxCode);
						}else if (payType==2) {
							userCard.setWxCode(wxCode);
						}
						
						userCard.setPublishUserId(card.getUserId());
						
					}
					
					
					if(0.0 != cardSum.doubleValue()){
						//记录用户的余额记录
						MoneyDetail moneyDetailC=new MoneyDetail();
						moneyDetailC.setBalance(appUserC.getBalance());
						moneyDetailC.setCreateTime(new Date());
						moneyDetailC.setCode(code);
						moneyDetailC.setMoney(-cardSum.doubleValue());
						moneyDetailC.setType(3);
						moneyDetailC.setAliTrade(wxCode);
						moneyDetailC.setPayType(payType);
						moneyDetailC.setUserId(card.getUserId());
						super.save(moneyDetailC);
					}
					
					super.update(cards, true);
					
					
					//更新card中的convertNum字段
					if(null != card){
						card.setConvertNum(card.getConvertNum()-cards.size());
						cardService.update(card, true);
						
						if(0 == card.getNum()){
							//用户信息
							if(null != appUserC && 1 == appUserC.getIsPush()){
								notificationService.notify(5, card.getId(), card.getUserId());
							}
						}
					}
					
					
					break;
					
					
				case 4:
					
					if(itemId==null){
						return null;
					}
					
					//看是不是第一次进来
					MoneyDetail moneyDetailRR=new MoneyDetail();
					moneyDetailRR.setItemId(itemId);
					moneyDetailRR.setType(4);
					moneyDetailRR.setUserId(itemId);
					moneyDetailRR.setAliTrade(wxCode);
					
					Page pageRR=new Page();
					List<MoneyDetail> moneyDetailsR=super.findListDataByFinder(null, pageRR, MoneyDetail.class	, moneyDetailRR);
					
					if(moneyDetailsR.size()>0){
						return 4;
					}
					
					AppUser appuser=super.findById(itemId, AppUser.class);
					if(appuser!=null){
						if(appuser.getBalance()!=null){
							appuser.setBalance(appuser.getBalance()+money);
						}else {
							appuser.setBalance(money);
						}
					}else {
						return null;
					}
					
					//记录用户的余额记录
					MoneyDetail moneyDetailr=new MoneyDetail();
					moneyDetailr.setBalance(appuser.getBalance());
					moneyDetailr.setCreateTime(new Date());
					moneyDetailr.setItemId(itemId);
					moneyDetailr.setMoney(+money);
					moneyDetailr.setType(4);
					moneyDetailr.setAliTrade(wxCode);
					moneyDetailr.setPayType(payType);
					moneyDetailr.setUserId(itemId);
					super.save(moneyDetailr);
					
					super.update(appuser,true);
					
					break;
					
					//预约支付
				case 5:
					//查询预约支付的信息
					
					if(StringUtils.isBlank(code)){
						return 2;
					}
					Finder finderAppoint=Finder.getSelectFinder(Appoint.class).append(" where 1=1 and code= :code ");
					finderAppoint.setParam("code", code);
					Appoint appoint = null;
					List<Appoint> appoints=super.queryForList(finderAppoint, Appoint.class);
					if(null != appoints && appoints.size() > 0){
						appoint = appoints.get(0);
					}
					if(appoint==null||appoint.getMoney()==null){
						return 4;
					}
					//看是不是第一次进来
					MoneyDetail moneyDetailA=new MoneyDetail();
					moneyDetailA.setCode(code);
					moneyDetailA.setType(13);
					moneyDetailA.setAliTrade(wxCode);
					moneyDetailA.setUserId(appoint.getUserId());
					
					Page pageA=new Page();
					List<MoneyDetail> moneyDetailas=super.findListDataByFinder(null, pageA, MoneyDetail.class, moneyDetailA);
					
					if(moneyDetailas.size()>0){
						return 4;
					}
					
					//改变状态
					appoint.setStatus(1);
					appoint.setPayType(payType);
					if(payType==1){
						appoint.setTradeNo(wxCode);
					}else if (payType==2) {
						appoint.setWxCode(wxCode);
					}
					appoint.setPayMoney(appoint.getMoney());
					appoint.setPayTime(new Date());
					
					//1给海报红包加一个预约数量   2给视频红包加一个预约数量
					switch (appoint.getType()) {
					case 1:
						
						PosterPackage posterPackageApp=super.findById(appoint.getItemId(), PosterPackage.class);
						
						//查询海报红包
						if(posterPackageApp!=null){
							
							if(posterPackageApp.getAppointCount()==null){
								posterPackageApp.setAppointCount(1);
							}else {
								posterPackageApp.setAppointCount(posterPackageApp.getAppointCount()+1);
							}
							super.update(posterPackageApp);
						}
						
						break;
					case 2:
						
						MediaPackage mediaPackageApp=super.findById(appoint.getItemId(), MediaPackage.class);
						
						if(mediaPackageApp!=null){
							
							if(mediaPackageApp.getAppointCount()==null){
								mediaPackageApp.setAppointCount(1);
								
							}else {
								mediaPackageApp.setAppointCount(mediaPackageApp.getAppointCount()+1);
							}
							super.update(mediaPackageApp);
						}
						
						break;
					}
					
					
					super.update(appoint,true);
					
					AppUser appUserA=this.findAppUserById(appoint.getUserId());
					
					//记录用户的余额记录
					MoneyDetail moneyDetailAp=new MoneyDetail();
					moneyDetailAp.setBalance(appUserA.getBalance());
					moneyDetailAp.setCreateTime(new Date());
					moneyDetailAp.setCode(code);
					moneyDetailAp.setMoney(-appoint.getMoney());
					moneyDetailAp.setType(13);
					moneyDetailAp.setAliTrade(wxCode);
					moneyDetailAp.setPayType(payType);
					moneyDetailAp.setUserId(appoint.getUserId());
					super.save(moneyDetailAp);
					
					
					break;
					
				case 6:
					if(StringUtils.isBlank(code)){
						return 2;
					}
					Finder finderCircle=Finder.getSelectFinder(CityCircle.class).append(" where 1=1 and code= :code");
					finderCircle.setParam("code", code);
					CityCircle cityCircle = null;
					List<CityCircle> cityCircles=super.queryForList(finderCircle, CityCircle.class);
					if(null != cityCircles && cityCircles.size() > 0){
						cityCircle = cityCircles.get(0);
					}
					if(cityCircle==null||cityCircle.getMoney()==null){
						return 4;
					}
					
					//看是不是第一次进来
					MoneyDetail moneyDetailC=new MoneyDetail();
					moneyDetailC.setCode(code);
					moneyDetailC.setType(14);
					moneyDetailC.setAliTrade(wxCode);
					moneyDetailC.setUserId(cityCircle.getUserId());
					
					Page pageD=new Page();
					List<MoneyDetail> moneyDetailcs=super.findListDataByFinder(null, pageD, MoneyDetail.class, moneyDetailC);
					
					if(moneyDetailcs.size()>0){
						return 4;
					}
					
					//改变状态
					cityCircle.setStatus(1);
					cityCircle.setPayType(payType);
					if(payType==1){
						cityCircle.setTradeNo(wxCode);
					}else if (payType==2) {
						cityCircle.setWxCode(wxCode);
					}
					cityCircle.setPayMoney(cityCircle.getMoney());
					cityCircle.setPayTime(new Date());
					
					//更新circle表的打赏总金额字段
					Finder circleFinder = new Finder("UPDATE t_circle SET sumMoney=sumMoney + :sumMoney WHERE id=:id");
					circleFinder.setParam("sumMoney", cityCircle.getMoney());
					circleFinder.setParam("id", cityCircle.getItemId());
					super.update(circleFinder);
					
					super.update(cityCircle,true);
					
					AppUser appUserD=this.findAppUserById(cityCircle.getUserId());
					
					//记录用户的余额记录
					MoneyDetail moneyDetailD=new MoneyDetail();
					moneyDetailD.setBalance(appUserD.getBalance());
					moneyDetailD.setCreateTime(new Date());
					moneyDetailD.setCode(code);
					moneyDetailD.setMoney(-cityCircle.getMoney());
					moneyDetailD.setType(14);
					moneyDetailD.setAliTrade(wxCode);
					moneyDetailD.setPayType(payType);
					moneyDetailD.setUserId(cityCircle.getUserId());
					super.save(moneyDetailD);
					
					
					
					//更新被打赏用户的记录
					Circle circle = circleService.findCircleById(cityCircle.getItemId());
					if(null != circle && null != circle.getUserId()){
						AppUser circleAppUser = appUserService.findAppUserById(circle.getUserId());
						if(null != circleAppUser){
							if(null == circleAppUser.getBalance()){
								circleAppUser.setBalance(0.0);
							}
							circleAppUser.setBalance(new BigDecimal(circleAppUser.getBalance()).add(new BigDecimal(cityCircle.getMoney())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							
							MoneyDetail moneyDetailB=new MoneyDetail();
							moneyDetailB.setBalance(circleAppUser.getBalance());
							moneyDetailB.setCreateTime(new Date());
							moneyDetailB.setItemId(cityCircle.getUserId());
							moneyDetailB.setMoney(+cityCircle.getMoney());
							moneyDetailB.setType(16);
							moneyDetailB.setUserId(circleAppUser.getId());
							super.save(moneyDetailB);
							
							notificationService.notify(34, circle.getId(), circle.getUserId());
							
							appUserService.update(circleAppUser,true);
						}
					}
					
					
					break;
				}
		return null;
	}
	
	@Override
	public ReturnDatas getStatics(AppUser appUser,Page page) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		if(null == appUser.getId()){
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数缺失");
		}else{
			//查询海报数量
			Finder posterFinder = new Finder("SELECT id FROM t_poster_package WHERE userId=:userId and (status = 3 or status=4) AND isDel=0");
			posterFinder.setParam("userId", appUser.getId());
			List posterList = queryForList(posterFinder);
			if(null != posterList && posterList.size() > 0){
				appUser.setPosterCount(posterList.size());
			}else{
				appUser.setPosterCount(0);
			}
			//查询视频数量
			Finder mediaFinder = new Finder("SELECT id FROM t_media_package WHERE userId=:userId AND isDel=0 and (status = 3 or status=4) ");
			mediaFinder.setParam("userId", appUser.getId());
			List mediaList = queryForList(mediaFinder);
			if(null != mediaList && mediaList.size() > 0){
				appUser.setMediaCount(mediaList.size());
			}else{
				appUser.setMediaCount(0);
			}
			
			//查询卡券数量
			Finder cardFinder = new Finder("SELECT id FROM t_card WHERE userId=:userId AND isDel=0 and status = 2 ");
			cardFinder.setParam("userId", appUser.getId());
			List cardList = queryForList(cardFinder);
			if(null != cardList && cardList.size() > 0){
				appUser.setCardCount(cardList.size());
			}else{
				appUser.setCardCount(0);
			}
			
			//查询同城活动数量
			Finder activityFinder = new Finder("SELECT id FROM t_activity WHERE userId=:userId AND isDel=0 and (status = 3 OR `status`=4) ");
			activityFinder.setParam("userId", appUser.getId());
			List activityList = queryForList(activityFinder);
			if(null != activityList && activityList.size() > 0){
				appUser.setActivityCount(activityList.size());
			}else{
				appUser.setActivityCount(0);
			}
			
			//查询同城圈数量
			Finder circleFinder = new Finder("SELECT id FROM t_circle where userId = :userId");
			circleFinder.setParam("userId", appUser.getId());
			List circleList = queryForList(circleFinder);
			if(null != circleList && circleList.size() > 0){
				appUser.setCityCircleCount(circleList.size());
			}else{
				appUser.setCityCircleCount(0);
			}
					
			returnObject.setData(appUser);
		}
		return returnObject;
	}
	
	
	
	@Override
	public AppUser findUserAndMedal(String userId) throws Exception {
		if (StringUtils.isBlank(userId)) {
			return null;
		}
		AppUser appUser = super.findById(userId, AppUser.class);
		if (appUser == null) {
			return null;
		}
		List<Medal> medals = findMedalByUserId(userId);
		appUser.setMedals(medals);
		return appUser;
	}
	
	
	
	@Override
	public List<Medal> findMedalByUserId(String userId) throws Exception{
		if(StringUtils.isBlank(userId)){
			return null;
		}
		Finder finder = new Finder(
				"SELECT m.* from ").append(Finder.getTableName(Medal.class)).append(" m,").append(Finder.getTableName(UserMedal.class)).append(" um where um.userId = :userId and um.medalId=m.id order by m.id");
		finder.setParam("userId", Integer.parseInt(userId));
		return super.queryForList(finder,Medal.class);
		
	}
	
	
	
	
	
	/**
	 * **********************************************************************************
	 * *********************************生成红包工具-----start------*************************************
	 * ********************************************************************************
	 */
	static Random random = new Random();  
    static {  
        random.setSeed(System.currentTimeMillis());  
    }
    /** 
     * 生产min和max之间的随机数，但是概率不是平均的，从min到max方向概率逐渐加大。 
     * 先平方，然后产生一个平方值范围内的随机数，再开方，这样就产生了一种“膨胀”再“收缩”的效果。 
     *  
     * @param min 
     * @param max 
     * @return 
     */  
    static long xRandom(long min, long max) {  
    	if(max-min == 0){   //防止0开方
    		return 1 ;  //此时随机生成的都是1分钱
    	}
        return sqrt(nextLong(sqr(max - min)));  
    }
    
    static long sqrt(long n) {  
        // 改进为查表？  
        return (long) Math.sqrt(n);  
    }  
  
    static long sqr(long n) {  
        // 查表快，还是直接算快？  
        return n * n;  
    }  
      
    static long nextLong(long n) {  
        return random.nextInt((int) n);  
    }  
  
    static long nextLong(long min, long max) {  
        return random.nextInt((int) (max - min + 1)) + min;  
    }  
    	
	
	  /** 
     *  
     * @param total 
     *            红包总额   单位：分
     * @param count 
     *            红包个数  
     * @param max 
     *            每个小红包的最大额  单位：分
     * @param min 
     *            每个小红包的最小额  单位：分
     * @return 存放生成的每个小红包的值的数组 
     */  
    public static long[] generate(long total, int count, long max, long min) {  
        long[] result = new long[count];  
  
        long average = total / count;  
  
        long a = average - min;  
        long b = max - min;  
  
        //  
        //这样的随机数的概率实际改变了，产生大数的可能性要比产生小数的概率要小。  
        //这样就实现了大部分红包的值在平均数附近。大红包和小红包比较少。  
        long range1 = sqr(average - min);  
        long range2 = sqr(max - average);  
  
        for (int i = 0; i < result.length; i++) {  
            //因为小红包的数量通常是要比大红包的数量要多的，因为这里的概率要调换过来。  
            //当随机数>平均值，则产生小红包  
            //当随机数<平均值，则产生大红包  
            if (nextLong(min, max) > average) {  
                // 在平均线上减钱  
//              long temp = min + sqrt(nextLong(range1));  
                long temp = min + xRandom(min, average);  
                result[i] = temp;  
                total -= temp;  
            } else {  
                // 在平均线上加钱  
//              long temp = max - sqrt(nextLong(range2));  
                long temp = max - xRandom(average, max);  
                result[i] = temp;  
                total -= temp;  
            }  
        }  
        // 如果还有余钱，则尝试加到小红包里，如果加不进去，则尝试下一个。  
        while (total > 0) {  
            for (int i = 0; i < result.length; i++) {  
                if (total > 0 && result[i] < max) {  
                    result[i]++;  
                    total--;  
                }  
            }  
        }  
        // 如果钱是负数了，还得从已生成的小红包中抽取回来  
        while (total < 0) {  
            for (int i = 0; i < result.length; i++) {  
                if (total < 0 && result[i] > min) {  
                    result[i]--;  
                    total++;  
                }  
            }  
        }  
        return result;  
    }  
    /**
	 * **********************************************************************************
	 * *********************************生成红包工具-----end------*************************************
	 * ********************************************************************************
	 */
	

}
