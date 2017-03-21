package com.cz.mts.system.schema;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cz.mts.frame.common.BaseLogger;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Card;
import com.cz.mts.system.entity.UserCard;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.ICardService;
import com.cz.mts.system.service.IUserCardService;
import com.cz.mts.system.service.NotificationService;
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
	
	/**
	 * 卡券到期
	 * @throws Exception
	 */
	@Scheduled(cron="0 0/5 * * * ?")
	public void cardEnd() throws Exception{
		/*logger.info("*****************判断卡券到期******************");
		Finder finder = new Finder("SELECT * FROM t_card WHERE isDel=0 AND NOW()>endTime;");
		List<Card> cards = cardService.queryForList(finder,Card.class);
		if(null != cards && cards.size() > 0){
			for (Card card : cards) {
				//给发布人发推送
				notificationService.notify(6, card.getId(), card.getUserId());
				card.setStatus(4);
				cardService.update(card,true);
				
				//查询userCard表中是否存在该卡券的记录
				Finder finder2 = new Finder("SELECT * FROM t_user_card WHERE `status`!=0 AND cardId=:cardId");
				finder2.setParam("cardId", card.getId());
				List<UserCard> userCards = userCardService.queryForList(finder2,UserCard.class);
				if(null != userCards && userCards.size() > 0){
					for (UserCard userCard : userCards) {
						//对该用户发推送
						notificationService.notify(13, userCard.getCardId(), userCard.getUserId());
					}
				}
			}
		}*/
	}
	
	@Scheduled(cron="0 0 */1 * * ?")
	public void updateLqNum() throws Exception{
		/*logger.info("*****************领取次数更新提醒******************");
		Finder finder = new Finder("SELECT * FROM t_app_user");
		List<AppUser> appUsers = appUserService.queryForList(finder,AppUser.class);
		if(null != appUsers && appUsers.size() > 0){
			for (AppUser appUser : appUsers) {
				appUser.setCurrentLqNum(appUser.getLqNum());
				appUser.setCurrentShareNum(appUser.getShareNum());
				appUserService.update(appUser,true);
				//给用户发推送
				notificationService.notify(12, appUser.getId(), appUser.getId(), appUser.getCurrentLqNum()+"");
			}
		}*/
		
	}

}
