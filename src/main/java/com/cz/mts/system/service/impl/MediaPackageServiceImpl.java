package com.cz.mts.system.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cz.mts.frame.entity.IBaseEntity;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Attention;
import com.cz.mts.system.entity.Collect;
import com.cz.mts.system.entity.Medal;
import com.cz.mts.system.entity.MediaPackage;
import com.cz.mts.system.entity.MoneyDetail;
import com.cz.mts.system.entity.UserMedal;
import com.cz.mts.system.service.BaseSpringrainServiceImpl;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.IAttentionService;
import com.cz.mts.system.service.ICollectService;
import com.cz.mts.system.service.IMedalService;
import com.cz.mts.system.service.IMediaPackageService;
import com.cz.mts.system.service.IMoneyDetailService;
import com.cz.mts.system.service.IUserMedalService;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:26
 * @see com.cz.mts.system.service.impl.MediaPackage
 */
@Service("mediaPackageService")
public class MediaPackageServiceImpl extends BaseSpringrainServiceImpl implements IMediaPackageService {
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
	
   
    @Override
	public String  save(Object entity ) throws Exception{
	      MediaPackage mediaPackage=(MediaPackage) entity;
	       return super.save(mediaPackage).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      MediaPackage mediaPackage=(MediaPackage) entity;
		 return super.saveorupdate(mediaPackage).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 MediaPackage mediaPackage=(MediaPackage) entity;
	return super.update(mediaPackage);
    }
    @Override
	public MediaPackage findMediaPackageById(Object id) throws Exception{
	 return super.findById(id,MediaPackage.class);
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
	public ReturnDatas list(MediaPackage mediaPackage,Page page,String appUserId) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		List<MediaPackage> dataList = findListDataByFinder(null,page,MediaPackage.class,mediaPackage);
		if(StringUtils.isBlank(appUserId) || null == mediaPackage.getUserId() || null == mediaPackage.getStatus() || null == mediaPackage.getCategoryId()){
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数缺失");
		}else{
			if(null != dataList && dataList.size() > 0){
				for (MediaPackage mp : dataList) {
					//返回发布人的信息
					if(null != mp.getUserId()){
						AppUser appUser = appUserService.findAppUserById(mp.getUserId());
						if(null != appUser){
							mp.setAppUser(appUser);
						}
						UserMedal userMedal = new UserMedal();
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
							mp.setUserMedals(userMedals);
						}
						//返回是否关注
						Attention attention = new Attention();
						attention.setUserId(Integer.parseInt(appUserId));
						attention.setItemId(mp.getUserId());
						List<Attention> attentions = attentionService.findListDataByFinder(null, page, Attention.class, attention);
						if(null != attentions && attentions.size() > 0){
							mp.setIsAttention(1);
						}else{
							mp.setIsAttention(0);
						}
					}
					
					//返回是否收藏
					Collect collect = new Collect();
					collect.setUserId(Integer.parseInt(appUserId));
					collect.setItemId(mp.getId());
					collect.setType(2);
					List<Collect> collects = collectService.findListDataByFinder(null, page, Collect.class, collect);
					if(null != collects && collects.size() > 0){
						mp.setIsCollect(1);
					}else{
						mp.setIsCollect(0);
					}
					//已抢红包列表
					MoneyDetail moneyDetail = new MoneyDetail();
					moneyDetail.setItemId(mp.getId());
					moneyDetail.setType(2);
					List<MoneyDetail> moneyDetails = moneyDetailService.findListDataByFinder(null, page, MoneyDetail.class, moneyDetail);
					if(null != moneyDetails && moneyDetails.size() > 0){
						for (MoneyDetail md : moneyDetails) {
							if(null != md.getUserId()){
								AppUser appUser = appUserService.findAppUserById(md.getUserId());
								if(null != appUser){
									md.setAppUser(appUser);
								}
							}
						}
						mp.setMoneyDetails(moneyDetails);
					}
				}
			}
			returnObject.setPage(page);
			returnObject.setData(dataList);
			returnObject.setQueryBean(mediaPackage);
		}
		return returnObject;
	}

}
