
package com.cz.mts.system.schema;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.lucene.store.SleepingLockWrapper;
import org.eclipse.jetty.util.log.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cz.mts.frame.common.BaseLogger;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.HttpUtils;
import com.cz.mts.frame.util.JPushUtil;
import com.cz.mts.system.entity.Activity;
import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.ApplyMedal;
import com.cz.mts.system.entity.Awards;
import com.cz.mts.system.entity.Card;
import com.cz.mts.system.entity.City;
import com.cz.mts.system.entity.MediaPackage;
import com.cz.mts.system.entity.PosterPackage;
import com.cz.mts.system.entity.UserCard;
import com.cz.mts.system.entity.UserMedal;
import com.cz.mts.system.service.IActivityService;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.IApplyMedalService;
import com.cz.mts.system.service.ICardService;
import com.cz.mts.system.service.ICityService;
import com.cz.mts.system.service.IMedalService;
import com.cz.mts.system.service.IMediaPackageService;
import com.cz.mts.system.service.IPosterPackageService;
import com.cz.mts.system.service.IUserCardService;
import com.cz.mts.system.service.IUserMedalService;
import com.cz.mts.system.service.NotificationService;
import com.sun.xml.internal.xsom.impl.scd.Axis;
@Component
public class CardSchema extends BaseLogger{
	
	@Resource
	private ICardService cardService;
	@Resource
	private NotificationService notificationService;
	@Resource
	private IUserCardService userCardService;
	@Resource
	private IAppUserService appUserService;
	@Resource
	private IApplyMedalService applyMedalService;
	@Resource
	private IMedalService medalService;
	@Resource
	private IUserMedalService userMedalService;
	@Resource
	private IPosterPackageService posterPackageService;
	@Resource
	private IMediaPackageService mediaPackageService;
	@Resource
	private ICityService cityService;
	@Resource
	private IActivityService activityService;
	
	/**
	 * 卡券到期前三天发推送
	 * @throws Exception
	 */
	@Scheduled(cron="0 0 12 * * ?")
	public void cardEnding() throws Exception{
		logger.info("*****************判断卡券即将到期******************");
		Finder finder = new Finder("SELECT * FROM t_card WHERE DATE(DATE_SUB(endTime,INTERVAL 3 DAY)) = DATE(NOW()) AND isDel=0 AND `status`!=4");
		List<Card> cards = cardService.queryForList(finder,Card.class);
		if(null != cards && cards.size() > 0){
			for (Card card : cards) {
				//查询userCard表中是否存在该卡券的记录
				Finder finder2 = new Finder("SELECT * FROM t_user_card WHERE `status`!=0 and `status` != 3 AND cardId=:cardId");
				finder2.setParam("cardId", card.getId());
				List<UserCard> userCards = userCardService.queryForList(finder2,UserCard.class);
				if(null != userCards && userCards.size() > 0){
					for (UserCard userCard : userCards) {
						//查询用户信息
						AppUser appUser2 = appUserService.findAppUserById(userCard.getUserId());
						if(null != appUser2 && 1 == appUser2.getIsPush()){
							//对该用户发推送
							notificationService.notify(13, userCard.getCardId(), userCard.getUserId());
						}
					}
				}
			}
		}
	}
	
	
	/**
	 * 卡券到期
	 * @throws Exception
	 */
	@Scheduled(cron="0 0/10 * * * ?")
	public void cardEnded() throws Exception{
		logger.info("*****************判断卡券到期******************");
		Finder finder = new Finder("SELECT * FROM t_card WHERE endTime <= NOW() AND isDel=0 AND `status`!=4");
		List<Card> cards = cardService.queryForList(finder,Card.class);
		if(null != cards && cards.size() > 0){
			for (Card card : cards) {
				//查询用户信息
				AppUser appUser = appUserService.findAppUserById(card.getUserId());
				if(null != appUser && 1 == appUser.getIsPush()){
					//给发布人发推送
					notificationService.notify(6, card.getId(), card.getUserId());
				}
				card.setStatus(4);
				cardService.update(card,true);
				
				//查询userCard表中是否存在该卡券的记录
				Finder finder2 = new Finder("SELECT * FROM t_user_card WHERE `status`=1 AND cardId=:cardId");
				finder2.setParam("cardId", card.getId());
				List<UserCard> userCards = userCardService.queryForList(finder2,UserCard.class);
				if(null != userCards && userCards.size() > 0){
					for (UserCard userCard : userCards) {
						userCard.setStatus(3);
						userCardService.update(userCard,true);
					}
				}
				
			}
		}
	}
	
	
	/**
	 *领取次数更新提醒
	 */
	@Scheduled(cron="0 0 */1 * * ?")
	public void updateLqNum() throws Exception{
		logger.info("*****************领取次数更新提醒******************");
		Finder finder = new Finder("SELECT * FROM t_app_user WHERE currentLqNum != lqNum");
		List<AppUser> appUsers = appUserService.queryForList(finder,AppUser.class);
		if(null != appUsers && appUsers.size() > 0){
			for (AppUser appUser : appUsers) {
				appUser.setCurrentLqNum(appUser.getLqNum());
				appUser.setCurrentShareNum(appUser.getShareNum());
				appUserService.update(appUser,true);
				if(1 == appUser.getIsPush()){
					//给用户发推送
					notificationService.notify(12, appUser.getId(), appUser.getId(), appUser.getCurrentLqNum()+"");
				}
			}
		}
		
	}
	
	/**
	 * 剩余数量更新提醒
	 */
	@Scheduled(cron="0 0/10 * * * ?")
	public void updateSyNum() throws Exception{
		logger.info("*****************剩余数量更新提醒******************");
		Finder finder = Finder.getSelectFinder(Card.class).append(" where 1=1 and status = 2 ");
		List<Card> cards = cardService.queryForList(finder,Card.class);
		if(null != cards && cards.size() > 0){
			for (Card card : cards) {

				Finder finderUser=Finder.getSelectFinder(UserCard.class).append(" where status=0 and cardId="+card.getId());
				List<UserCard> userCards=userCardService.queryForList(finderUser,UserCard.class);
				if(null != userCards && userCards.size() > 0){
					int num=0;

					for (UserCard userCard : userCards) {
						userCardService.deleteById(userCard.getId(), UserCard.class);
						num += 1;
					}

					card.setNum(card.getNum() + num);
				}
				cardService.update(card,true);
			}
		}
		
	}
	
	
//	public void updateNum() throws Exception{
//		logger.info("card表剩余数量更新");
//		Finder finder = Finder.getSelectFinder(Card.class).append(" where 1=1 and status = 2 ");
//		List<Card> cards = cardService.queryForList(finder,Card.class);
//		if(null != cards && cards.size() > 0){
//			for (Card card : cards) {
//
//				Finder finderUser=Finder.getSelectFinder(UserCard.class).append(" where status != 0 and cardId="+card.getId());
//				List<UserCard> userCards=userCardService.queryForList(finderUser,UserCard.class);
//				if(null != userCards && userCards.size() > 0){
//					card.setNum(card.getConvertNum() - userCards.size());
//				}
//				cardService.update(card,true);
//			}
//		}
//	}
	
	
	/**
	 * 勋章过期更改成已过期
	 */
	@Scheduled(cron="0 0/10 * * * ?")
	public void updateMedalStatus() throws Exception{
		logger.info("勋章过期定时任务");
		Finder finder = Finder.getSelectFinder(ApplyMedal.class).append("where 1=1 and `status`=2 AND endMedalTime <= NOW() AND isEndStatus!=1 ");
		List<ApplyMedal> applyMedals = applyMedalService.queryForList(finder,ApplyMedal.class);
		if(null != applyMedals && applyMedals.size() > 0){
			for (ApplyMedal applyMedal : applyMedals) {
				applyMedal.setIsEndStatus(1);
				applyMedalService.update(applyMedal,true);
			}
		}
		
		Finder finder2 = Finder.getSelectFinder(UserMedal.class).append("where 1=1 and endMedalTime <= NOW() AND isEndStatus!=1");
		List<UserMedal> userMedals = userMedalService.queryForList(finder2, UserMedal.class);
		if(null != userMedals && userMedals.size() > 0){
			for (UserMedal userMedal : userMedals) {
				userMedal.setIsEndStatus(1);
				userMedalService.update(userMedal,true);
			}
		}
	}
	
	
	/**
	 * 勋章有效期前三天发推送
	 * 
	 */
	@Scheduled(cron="0 0/15 * * * ?")
	public void beEndMedal() throws Exception{
		logger.info("勋章有效期前三天发推送");
		Finder finder = Finder.getSelectFinder(UserMedal.class).append(" WHERE endMedalTime IS NOT NULL AND DATE(DATE_SUB(endMedalTime,INTERVAL 3 DAY)) = DATE(NOW())");
		List<UserCard> userCards = userCardService.queryForList(finder,UserCard.class);
		if(null != userCards && userCards.size() > 0){
			for (UserCard userCard : userCards) {
				notificationService.notify(35, userCard.getId(), userCard.getUserId());
			}
		}
	}
	
	
	
	
	/**
	 * 海报红包领取完毕之后超过三天自动下线
	 */
	@Scheduled(cron="0 0/15 * * * ?")
	public void endPosterPackage() throws Exception{
		logger.info("海报红包下线定时任务");
		Finder finder = Finder.getSelectFinder(PosterPackage.class).append("where 1=1 and `status`=4 AND DATE_ADD(endTime,INTERVAL 3 DAY) <= NOW() AND isValid != 1");
		List<PosterPackage> posterPackages = posterPackageService.queryForList(finder, PosterPackage.class);
		if(null != posterPackages && posterPackages.size() > 0){
			for (PosterPackage posterPackage : posterPackages) {
				posterPackage.setIsValid(1);
				posterPackageService.update(posterPackage,true);
				notificationService.notify(37, posterPackage.getId(), posterPackage.getUserId());
			}
		}
	}
	
	/**
	 * 视频红包领取完毕之后超过三天自动下线
	 */
	@Scheduled(cron="0 0/15 * * * ?")
	public void endMediaPackage() throws Exception{
		logger.info("视频红包下线定时任务");
		Finder finder = Finder.getSelectFinder(MediaPackage.class).append("where 1=1 and `status`=4 AND DATE_ADD(endTime,INTERVAL 3 DAY) <= NOW() AND isValid != 1");
		List<MediaPackage> mediaPackages = mediaPackageService.queryForList(finder, MediaPackage.class);
		if(null != mediaPackages && mediaPackages.size() > 0){
			for (MediaPackage mediaPackage : mediaPackages) {
				mediaPackage.setIsValid(1);
				mediaPackageService.update(mediaPackage,true);
				notificationService.notify(36, mediaPackage.getId(), mediaPackage.getUserId());
			}
		}
	}
	
	
	/***
	 * 同城活动到期
	 */
	@Scheduled(cron="0 0/20 * * * ?")
	public void endActivity() throws Exception{
		logger.info("同城活动到期定时任务");
		Finder finder = Finder.getSelectFinder(Activity.class).append(" where `status`=3 AND isDel=0 AND NOW() >= endTime");
		List<Activity> activities = activityService.queryForList(finder, Activity.class);
		if(null != activities && activities.size() > 0){
			for (Activity activity : activities) {
				activity.setStatus(4);
				activityService.update(activity,true);
				//给发布人发推送
				notificationService.notify(24, activity.getId(), activity.getUserId());
			}
		}
	}
	
	
	
	/**
	 * 自动更新天气
	 * 
	 */
	@Scheduled(cron="0 0 0 * * ?")
	public void tianqi() throws Exception{
		logger.info("获取天气");
		
		Finder finder=Finder.getSelectFinder(City.class).append(" where open=1 ");
		
		List<City> citys=cityService.queryForList(finder, City.class);
		
		for (City city : citys) {
		
		String host = "http://jisutqybmf.market.alicloudapi.com";
	    String path = "/weather/query";
	    String method = "GET";
	    String appcode = "3cfec02bee26451fa2743d25adc4a5f2";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("city", city.getName());
	    /* querys.put("citycode", "citycode");
	    querys.put("cityid", "cityid");
	    querys.put("ip", "ip");
	    querys.put("location", "location");*/

	    try {
	    	/**
	    	* 重要提示如下:
	    	* HttpUtils请从
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
	    	* 下载
	    	*
	    	* 相应的依赖请参照
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
	    	*/
	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
	    	//System.out.println(response.toString());
	    	//获取response的body
	    	//System.out.println(EntityUtils.toString(response.getEntity()));
	    	JSONArray jsonArray = JSONArray.fromObject("["+EntityUtils.toString(response.getEntity())+"]");
	    	for (int i = 0; i < jsonArray.size(); i++) {
				
				JSONArray jsonCity = JSONArray.fromObject("["+jsonArray.getJSONObject(i).getString("result")+"]");
				System.out.println(jsonCity.toString());
				for (int k = 0; k < jsonCity.size(); k++) {
					
					city.setWeather(jsonCity.getJSONObject(k).getString("weather"));
					city.setDate(jsonCity.getJSONObject(k).getString("date"));
					city.setTemphigh(jsonCity.getJSONObject(k).getString("temphigh"));
					city.setTemplow(jsonCity.getJSONObject(k).getString("templow"));
					
					cityService.update(city, true);
				}
	        }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    TimeUnit.SECONDS.sleep(10);
	    
		}
	}
	

}
