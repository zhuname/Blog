package com.cz.mts.system.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cz.mts.system.entity.Activity;
import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Attention;
import com.cz.mts.system.entity.Card;
import com.cz.mts.system.entity.Collect;
import com.cz.mts.system.entity.Medal;
import com.cz.mts.system.entity.MediaPackage;
import com.cz.mts.system.entity.MoneyDetail;
import com.cz.mts.system.entity.PosterPackage;
import com.cz.mts.system.entity.User;
import com.cz.mts.system.entity.UserMedal;
import com.cz.mts.system.service.IActivityService;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.IAttentionService;
import com.cz.mts.system.service.ICardService;
import com.cz.mts.system.service.ICollectService;
import com.cz.mts.system.service.IMedalService;
import com.cz.mts.system.service.IMediaPackageService;
import com.cz.mts.system.service.IMoneyDetailService;
import com.cz.mts.system.service.IPosterPackageService;
import com.cz.mts.system.service.IUserMedalService;
import com.cz.mts.frame.entity.IBaseEntity;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:25
 * @see com.cz.mts.system.service.impl.Collect
 */
@Service("collectService")
public class CollectServiceImpl extends BaseSpringrainServiceImpl implements ICollectService {
	@Resource
	private IPosterPackageService posterPackageService;
	@Resource
	private IMediaPackageService mediaPackageService;
	@Resource
	private ICardService cardService;
	@Resource
	private IAppUserService appUserService;
	@Resource
	private IUserMedalService userMedalService;
	@Resource
	private IMedalService medalService;
	@Resource
	private IAttentionService attentionService;
	@Resource
	private IMoneyDetailService moneyDetailService;
	@Resource
	private IActivityService activityService;
   
    @Override
	public String  save(Object entity ) throws Exception{
	      Collect collect=(Collect) entity;
	       return super.save(collect).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      Collect collect=(Collect) entity;
		 return super.saveorupdate(collect).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 Collect collect=(Collect) entity;
	return super.update(collect);
    }
    @Override
	public Collect findCollectById(Object id) throws Exception{
	 return super.findById(id,Collect.class);
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
	public ReturnDatas list(Collect collect,Page page) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		if(null == collect.getUserId() || null == collect.getType()){
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数缺失");
		}else{
			// ==执行分页查询
			collect.setOsType(null);
			page.setOrder("id");
			page.setSort("desc");
			List<Collect> datas = findListDataByFinder(null,page,Collect.class,collect);
			if(null != datas && datas.size() > 0){
				for (Collect ct : datas) {
					//计算收藏个数
					ct.setCollectNum(datas.size());
					//判断类型： 1海报红包   2视频红包   3卡券  4同城活动
					if(1 == collect.getType()){
						//查询海报红包的信息
						PosterPackage posterPackage = posterPackageService.findPosterPackageById(ct.getItemId());
						if(null != posterPackage && null != posterPackage.getUserId()){
							AppUser appUser = appUserService.findAppUserById(posterPackage.getUserId());
							if(null != appUser){
								posterPackage.setAppUser(appUser);
							}
						}
						ct.setPosterPackage(posterPackage);
					}else if(3 == collect.getType()){
						//查询卡券信息
						Card card = cardService.findCardById(ct.getItemId());
						if(null != card && null != card.getUserId()){
							AppUser appUser = appUserService.findAppUserById(card.getUserId());
							if(null != appUser){
								card.setAppUser(appUser);
							}
							if(null != appUser.getUserMedals()){
								card.setUserMedals(appUser.getUserMedals());
							}
						}
						ct.setCard(card);
					}else if(2 == collect.getType()){
						//查询视频红包信息
						MediaPackage mediaPackage = mediaPackageService.findMediaPackageById(ct.getItemId());
						if(null != mediaPackage && null != mediaPackage.getUserId()){
							AppUser appUser = appUserService.findAppUserById(mediaPackage.getUserId());
							if(null != appUser){
								mediaPackage.setAppUser(appUser);
							}
							if(null != appUser.getUserMedals()){
								mediaPackage.setUserMedals(appUser.getUserMedals());
							}
							mediaPackage.setIsCollect(1);
							
							//返回是否关注
							Attention attention = new Attention();
							attention.setUserId(ct.getUserId());
							attention.setItemId(mediaPackage.getUserId());
							Page attePage = new Page();
							List<Attention> attentions = attentionService.findListDataByFinder(null, attePage, Attention.class, attention);
							if(null != attentions && attentions.size() > 0){
								mediaPackage.setIsAttention(1);
							}else{
								mediaPackage.setIsAttention(0);
							}
							
							//已抢红包列表
							MoneyDetail moneyDetail = new MoneyDetail();
							moneyDetail.setItemId(mediaPackage.getId());
							moneyDetail.setType(2);
							Page moneyPage = new Page();
							List<MoneyDetail> moneyDetails = moneyDetailService.findListDataByFinder(null, moneyPage, MoneyDetail.class, moneyDetail);
							if(null != moneyDetails && moneyDetails.size() > 0){
								for (MoneyDetail md : moneyDetails) {
									if(null != md.getUserId()){
										AppUser appUserU = appUserService.findAppUserById(md.getUserId());
										if(null != appUserU){
											md.setAppUser(appUserU);
										}
									}
								}
								mediaPackage.setMoneyDetails(moneyDetails);
							}
							ct.setMediaPackage(mediaPackage);
						}
					}else{
						//同城活动
						Activity activity = activityService.findActivityById(ct.getItemId());
						ct.setActivity(activity);
					}
					
				}
			}
			returnObject.setQueryBean(collect);
			returnObject.setPage(page);
			returnObject.setData(datas);
		}
		return returnObject;
	}
		
	@Override	
	public ReturnDatas getStatics(Collect collect,Page page) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		if(null == collect.getUserId()){
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数缺失");
		}else{
			//查询海报数量
			Finder posterFinder = new Finder("SELECT id FROM t_collect WHERE userId=:userId AND type=1 ;");
			posterFinder.setParam("userId", collect.getUserId());
			List posterList = queryForList(posterFinder);
			if(null != posterList && posterList.size() > 0){
				collect.setPosterCount(posterList.size());
			}else{
				collect.setPosterCount(0);
			}
			//查询视频数量
			Finder mediaFinder = new Finder("SELECT id FROM t_collect WHERE userId=:userId AND type=2 ;");
			mediaFinder.setParam("userId", collect.getUserId());
			List mediaList = queryForList(mediaFinder);
			if(null != mediaList && mediaList.size() > 0){
				collect.setMediaCount(mediaList.size());
			}else{
				collect.setMediaCount(0);
			}
			
			//查询卡券数量
			Finder cardFinder = new Finder("SELECT id FROM t_collect WHERE userId=:userId AND type=3 ;");
			cardFinder.setParam("userId", collect.getUserId());
			List cardList = queryForList(cardFinder);
			if(null != cardList && cardList.size() > 0){
				collect.setCardCount(cardList.size());
			}else{
				collect.setCardCount(0);
			}
			
			//查询同城活动数量
			Finder activityFinder = new Finder("SELECT id FROM t_collect WHERE userId=:userId AND type=4 ;");
			activityFinder.setParam("userId", collect.getUserId());
			List activityList = queryForList(activityFinder);
			if(null != activityList && activityList.size() > 0){
				collect.setActivityCount(activityList.size());
			}else{
				collect.setActivityCount(0);
			}
			returnObject.setData(collect);
		}
		return returnObject;
	}

}
