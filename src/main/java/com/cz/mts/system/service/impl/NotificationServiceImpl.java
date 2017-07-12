package com.cz.mts.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.JPushUtil;
import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Message;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.IMessageService;
import com.cz.mts.system.service.NotificationService;


@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {
	@Resource
	private IMessageService messageService;
	@Resource
	private IAppUserService appUserService;
	

	@Override
	public Integer notify(Integer type, Integer id, Integer userId,
			String... extend) throws Exception {
		// TODO Auto-generated method stub
		Map<String, String> other = new HashMap<String, String>() ;
		//message的type:推送给的人，1用户2商品商家
		//		  itemId:目标id   0代表固定位置
		//		  pushType：与推送文档的type一致
		Message message = null ;
		try {
			switch (type) {
			case 1:
				other.put("url", extend[1]);
				JPushUtil.sendAllPushNotification(extend[0], type+"", other, "");
				try {
					//查询所有的用户
					Finder finder = new Finder("SELECT * FROM t_app_user WHERE isPush=1");
					List<AppUser> appUsers = appUserService.queryForList(finder,AppUser.class);
					if(null != appUsers && appUsers.size() > 0){
						for (AppUser appUser : appUsers) {
							message=new Message(null, 1, appUser.getId(), new Date(), extend[0], null, extend[1], 0, "系统推送", 1);
							messageService.save(message);
						}
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			break;
			case 2:
				JPushUtil.sendJPushNotification("您发布的视频红包已被人领取哦，请及时关注领取动态", type+"", id, userId, "");
				try {
					message=new Message(null, 2, userId, new Date(), extend[0], id, "您发布的视频红包已被人领取哦，请及时关注领取动态", 0, "红包领取", 2);
					messageService.save(message);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			break;
			case 3:
				JPushUtil.sendJPushNotification("您发布的视频红包已被领取结束，您可操作再次发放或查看红包领取情况", type+"", id, userId,"");
				try {
					message=new Message(null, 3, userId, new Date(), "您发布的视频红包已被领取结束，您可操作再次发放或查看红包领取情况", id, "", 0, "红包领取完", 2);
					messageService.save(message);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			break;
			case 4:
				JPushUtil.sendJPushNotification("您发布的卡券已被人兑换，请及时关注卡券动态。", type+"", id, userId, "");
				message=new Message(null, 4, userId, new Date(), "您发布的卡券已被人兑换，请及时关注卡券动态。", id, "", 0, "兑换卡券", 2);
				messageService.save(message);
			break;
			case 5:
				JPushUtil.sendJPushNotification("您发布的卡券已被全部领取，您可操作再次发放或查看卡券领取状况", type+"", id, userId,"");
				message=new Message(null, 5, userId, new Date(), "您发布的卡券已被全部领取，您可操作再次发放或查看卡券领取状况", id, null, 0, "兑换完成", 2);
				messageService.save(message);
			break;
			case 6:
				JPushUtil.sendJPushNotification("您发布的卡券已过期，您可操作再次发放或查看卡券领取状况", type+"", id, userId, "");
				message=new Message(null, 6, userId, new Date(), "您发布的卡券已过期，您可操作再次发放或查看卡券领取状况", id, null, 0, "卡券到期", 2);
				messageService.save(message);
			break;
			case 7:
				JPushUtil.sendJPushNotification("恭喜您，您被授予“"+extend[0]+"”勋章。", type+"", id, userId, "");
				message=new Message(null, 7, userId, new Date(), "恭喜您，您被授予“"+extend[0]+"”勋章。", id, null, 0, "勋章授予", 2);
				messageService.save(message);
			break;
			case 8:
				JPushUtil.sendJPushNotification("您发布的视频信息审核失败，请及时查看原因并进行修改，然后再次提交", type+"", id, userId, "");
				message=new Message(null, 8, userId, new Date(), "您发布的视频信息审核失败，请及时查看原因并进行修改，然后再次提交", id, null, 0, "审核失败信息", 2);
				messageService.save(message);
			break;
			case 9:
				JPushUtil.sendJPushNotification("您发布的视频信息审核成功，已经发布上线，感谢您的配合", type+"", id, userId, "");
				try {
					message=new Message(null, 9, userId, new Date(), "您发布的视频信息审核成功，已经发布上线，感谢您的配合", id, null, 0, "审核成功信息", 2);
					messageService.save(message);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			break;
			case 10:
				JPushUtil.sendJPushNotification("您关注的“"+extend[0]+"”发布了一个“"+extend[1]+"”红包，金额"+extend[2]+"，赶快去领取吧。", type+"", id, userId, "");
				message=new Message(null, 10, userId, new Date(), "您关注的"+extend[0]+"发布了一个"+extend[1]+"红包，金额"+extend[2]+"，赶快去领取吧。", id, null, 0, "关注人发布红包", 2);
				messageService.save(message);
			break;
			case 11:
				JPushUtil.sendJPushNotification("您关注的“"+extend[0]+"”发布了一个“"+extend[1]+"”卡券，赶快去领取吧", type+"", id, userId, "");
				message=new Message(null, 11, userId, new Date(), "您关注的“"+extend[0]+"”发布了一个“"+extend[1]+"”卡券，赶快去领取吧", id, null, 0, "关注人发布卡券", 2);
				messageService.save(message);
			break;
			case 12:
				JPushUtil.sendJPushNotification("恭喜您获得"+extend[0]+"次红包领取机会，赶快去领取吧。", type+"", id, userId, "");
				message = new Message(null, 12, userId, new Date(), "恭喜您获得"+extend[0]+"次红包领取机会，赶快去领取吧。", id, null, 0, "领取次数更新提醒", 2);
				messageService.save(message);
			break;
			case 13:
				JPushUtil.sendJPushNotification("您兑换的卡券即将到期，请及时进行使用哦。", type+"", id, userId, "");
				message = new Message(null, 13, userId, new Date(), "您兑换的卡券即将到期，请及时进行使用哦。", id, null, 0, "卡券兑换到期信息", 2);
				messageService.save(message);
			break;
			case 14:
				JPushUtil.sendJPushNotification("您的卡券已被使用，如非本人操作，请及时联系客服", type+"", id, userId, "");
				message = new Message(null, 14, userId, new Date(), "您的卡券已被使用，如非本人操作，请及时联系客服", id, null, 0, "卡券兑换信息", 2);
				messageService.save(message);
			break;
			case 15:
				JPushUtil.sendJPushNotification("您发布的海报红包已被人领取哦，请及时关注领取动态", type+"", id, userId, "");
				try {
					message=new Message(null, 15, userId, new Date(), "您发布的海报红包已被人领取哦，请及时关注领取动态", id, null, 0, "红包领取", 2);
					messageService.save(message);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			break;
			case 16:
				JPushUtil.sendJPushNotification("您发布的海报红包已被领取结束，您可操作再次发放或查看红包领取情况", type+"", id, userId, "");
				try {
					message=new Message(null, 16, userId, new Date(), "您发布的海报红包已被领取结束，您可操作再次发放或查看红包领取情况", id, "", 0, "红包领取完", 2);
					messageService.save(message);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			break;
			
			case 17:
				JPushUtil.sendJPushNotification("您发布的海报信息审核失败，请及时查看原因并进行修改，然后再次提交", type+"", id, userId, "");
				message=new Message(null, 17, userId, new Date(), "您发布的海报信息审核失败，请及时查看原因并进行修改，然后再次提交", id, null, 0, "审核失败信息", 2);
				messageService.save(message);
			break;
			case 18:
				JPushUtil.sendJPushNotification("您发布的卡券信息审核失败，请及时查看原因并进行修改，然后再次提交", type+"", id, userId, "");
				message=new Message(null, 18, userId, new Date(), "您发布的卡券信息审核失败，请及时查看原因并进行修改，然后再次提交", id, null, 0, "审核失败信息", 2);
				messageService.save(message);
			break;
			case 19:
				JPushUtil.sendJPushNotification("您发布的海报信息审核成功，已经发布上线，感谢您的配合", type+"", id, userId, "");
				try {
					message=new Message(null, 19, userId, new Date(), "您发布的海报信息审核成功，已经发布上线，感谢您的配合", id, null, 0, "审核成功信息", 2);
					messageService.save(message);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			break;
			case 20:
				JPushUtil.sendJPushNotification("您发布的卡券信息审核成功，已经发布上线，感谢您的配合", type+"", id, userId, "");
				try {
					message=new Message(null, 20, userId, new Date(), "您发布的卡券信息审核成功，已经发布上线，感谢您的配合", id, null, 0, "审核成功信息", 2);
					messageService.save(message);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			break;
			
			case 21:
				JPushUtil.sendJPushNotification("恭喜您中奖了！", type+"", id, userId, "");
				try {
					message=new Message(null, 21, userId, new Date(), "恭喜您中奖了！", id, null, 0, "中奖信息", 2);
					messageService.save(message);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			break;
			case 22:
				JPushUtil.sendJPushNotification("您发布的同城活动审核成功，已经发布上线，感谢您的配合！", type+"", id, userId, "");
				try {
					message=new Message(null, 22, userId, new Date(), "您发布的同城活动审核成功，已经发布上线，感谢您的配合!", id, null, 0, "同城活动审核成功信息", 2);
					messageService.save(message);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			break;
			case 23:
				JPushUtil.sendJPushNotification("您发布的同城活动审核失败，请及时查看原因并进行修改，然后再次提交！", type+"", id, userId, "");
				try {
					message=new Message(null, 23, userId, new Date(), "您发布的同城活动审核失败，请及时查看原因并进行修改，然后再次提交!", id, null, 0, "同城活动审核失败信息", 2);
					messageService.save(message);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			break;
			case 24:
				JPushUtil.sendJPushNotification("您发布的同城活动已经过期，请注意查看！", type+"", id, userId, "");
				try {
					message=new Message(null, 24, userId, new Date(), "您发布的同城活动已经过期，请注意查看!", id, null, 0, "同城活动到期", 2);
					messageService.save(message);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			break;
			case 25:
				JPushUtil.sendJPushNotification("您发布的海报红包已经超过100个人浏览了哟", type+"", id, userId, "");
				try {
					message=new Message(null, 25, userId, new Date(), "您发布的海报红包已经超过100个人浏览了哟", id, null, 0, "海报红包超过100人浏览", 2);
					messageService.save(message);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			break;
			case 26:
				JPushUtil.sendJPushNotification("您发布的视频红包已经超过100个人浏览了哟", type+"", id, userId, "");
				try {
					message=new Message(null, 26, userId, new Date(), "您发布的视频红包已经超过100个人浏览了哟", id, null, 0, "视频红包超过100人浏览", 2);
					messageService.save(message);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			break;
			case 27:
				JPushUtil.sendJPushNotification("您发布的同城活动已经超过100个人浏览了哟", type+"", id, userId, "");
				try {
					message=new Message(null, 27, userId, new Date(), "您发布的同城活动已经超过100个人浏览了哟", id, null, 0, "同城活动超过100人浏览", 2);
					messageService.save(message);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			break;
			case 28:
				JPushUtil.sendJPushNotification("您发布的海报红包已经超过50个人点赞了哟", type+"", id, userId, "");
				try {
					message=new Message(null, 28, userId, new Date(), "您发布的海报红包已经超过50个人点赞了哟", id, null, 0, "海报红包超过50人点赞", 2);
					messageService.save(message);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			break;
			case 29:
				JPushUtil.sendJPushNotification("您发布的视频红包已经超过50个人点赞了哟", type+"", id, userId, "");
				try {
					message=new Message(null, 29, userId, new Date(), "您发布的视频红包已经超过50个人点赞了哟", id, null, 0, "视频红包超过50人点赞", 2);
					messageService.save(message);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			break;
			case 30:
				JPushUtil.sendJPushNotification("您发布的同城活动已经超过50个人点赞浏览了哟", type+"", id, userId, "");
				try {
					message=new Message(null, 30, userId, new Date(), "您发布的同城活动已经超过50个人点赞浏览了哟", id, null, 0, "同城活动超过50人点赞", 2);
					messageService.save(message);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			break;
			case 31:
				JPushUtil.sendJPushNotification("您参与的同城活动内容已经超过50个人点赞浏览了哟", type+"", id, userId, "");
				try {
					message=new Message(null, 31, userId, new Date(), "您参与的同城活动内容已经超过50个人点赞浏览了哟", id, null, 0, "同城活动参与超过50人点赞", 2);
					messageService.save(message);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			break;
			case 32:
				JPushUtil.sendJPushNotification("您发布的同城活动有人参与了哟，请注意查看", type+"", id, userId, "");
				try {
					message=new Message(null, 32, userId, new Date(), "您发布的同城活动有人参与了哟，请注意查看", id, null, 0, "同城活动被人参与", 2);
					messageService.save(message);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			break;
			case 33:
				JPushUtil.sendJPushNotification("您发布的同城圈已经超过50人点赞了哟，请注意查看", type+"", id, userId, "");
				try {
					message=new Message(null, 33, userId, new Date(), "您发布的同城圈已经超过50人点赞了哟，请注意查看", id, null, 0, "同城圈超过50人点赞", 2);
					messageService.save(message);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			break;
			case 34:
				JPushUtil.sendJPushNotification("您发布的朋友圈被人打赏了哟，请注意查看", type+"", id, userId, "");
				try {
					message=new Message(null, 34, userId, new Date(), "您发布的朋友圈被人打赏了哟，请注意查看", id, null, 0, "同城圈被人打赏", 2);
					messageService.save(message);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			break;
			case 35:
				JPushUtil.sendJPushNotification("您的勋章即将到期，请注意查看", type+"", id, userId, "");
				try {
					message=new Message(null, 35, userId, new Date(), "您的勋章即将到期，请注意查看", id, null, 0, "勋章有效期前三天提醒", 2);
					messageService.save(message);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			break;
			case 36:
				JPushUtil.sendJPushNotification("您发布的视频红包已经领取完毕并且已经超过三天将自动下线，请知悉", type+"", id, userId, "");
				try {
					message=new Message(null, 36, userId, new Date(), "您发布的视频红包已经领取完毕并且已经超过三天将自动下线，请知悉", id, null, 0, "视频红包领取完毕三天下线", 2);
					messageService.save(message);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			break;
			case 37:
				JPushUtil.sendJPushNotification("您发布的海报红包已经领取完毕并且已经超过三天将自动下线，请知悉", type+"", id, userId, "");
				try {
					message=new Message(null, 37, userId, new Date(), "您发布的海报红包已经领取完毕并且已经超过三天将自动下线，请知悉", id, null, 0, "勋章有效期前三天提醒", 2);
					messageService.save(message);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			break;
			
			
			
			
			
			
			
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 2 ;
		}
		return 1;
	}
	
	public static void main(String[] args) {
		JPushUtil.sendJPushNotification("test", "1", 0, 66,"");
		
	}
}
