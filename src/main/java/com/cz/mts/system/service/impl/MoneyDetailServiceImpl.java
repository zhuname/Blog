package com.cz.mts.system.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Medal;
import com.cz.mts.system.entity.MediaPackage;
import com.cz.mts.system.entity.MoneyDetail;
import com.cz.mts.system.entity.PosterPackage;
import com.cz.mts.system.entity.UserMedal;
import com.cz.mts.system.service.IAppUserService;
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
 * @version  2017-02-24 15:17:27
 * @see com.cz.mts.system.service.impl.MoneyDetail
 */
@Service("moneyDetailService")
public class MoneyDetailServiceImpl extends BaseSpringrainServiceImpl implements IMoneyDetailService {

	@Resource
	private IAppUserService appUserService;
	@Resource
	private IUserMedalService userMedalService;
	@Resource
	private IMedalService medalService;
	@Resource
	private IPosterPackageService posterPackageService;
	@Resource
	private IMediaPackageService mediaPackageService;
   
    @Override
	public String  save(Object entity ) throws Exception{
	      MoneyDetail moneyDetail=(MoneyDetail) entity;
	       return super.save(moneyDetail).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      MoneyDetail moneyDetail=(MoneyDetail) entity;
		 return super.saveorupdate(moneyDetail).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 MoneyDetail moneyDetail=(MoneyDetail) entity;
	return super.update(moneyDetail);
    }
    @Override
	public MoneyDetail findMoneyDetailById(Object id) throws Exception{
	 return super.findById(id,MoneyDetail.class);
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
	public ReturnDatas statics(MoneyDetail moneyDetail,Page page) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		if(null != moneyDetail.getItemId() && null != moneyDetail.getType()){
			List<MoneyDetail> moneyDetails = findListDataByFinder(null, page, MoneyDetail.class, moneyDetail);
			MoneyDetail monDetail = new MoneyDetail();
			if(null != moneyDetails && moneyDetails.size() > 0){
				Double sumMoney = 0.0;
				for (MoneyDetail md : moneyDetails) {
					sumMoney += md.getMoney();
				}
				monDetail.setSumMoney(sumMoney);
				monDetail.setSumPerson(moneyDetails.size());
			}else{
				monDetail.setSumMoney(0.0);
				monDetail.setSumPerson(0);
			}
			returnObject.setStatus(ReturnDatas.SUCCESS);
			returnObject.setData(monDetail);
		}else{
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数缺失");
		}
		return returnObject;
	}
	
	@Override
	public ReturnDatas list(MoneyDetail moneyDetail,Page page) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		//红包已抢金额
		Double moneyCount = 0.0;
		//红包剩余金额
		Double remainMoney = 0.0;
		
		if(null != moneyDetail.getType() && null != moneyDetail.getItemId()){
			//如果是海报红包
			if(1 == moneyDetail.getType()){
				PosterPackage posterPackage = posterPackageService.findPosterPackageById(moneyDetail.getItemId());
				if(null != posterPackage){
					if(null != posterPackage.getSumMoney()){
						remainMoney = posterPackage.getSumMoney() - moneyCount;
					}else{
						remainMoney = 0.0;
					}
				}
			}
			//如果是视频红包
			if(2 == moneyDetail.getType()){
				MediaPackage mediaPackage = mediaPackageService.findMediaPackageById(moneyDetail.getItemId());
				if(null != mediaPackage){
					if(null != mediaPackage.getSumMoney()){
						remainMoney = mediaPackage.getSumMoney() - moneyCount;
					}else{
						remainMoney = 0.0;
					}
				}
			}
			List<MoneyDetail> datas = findListDataByFinder(null,page,MoneyDetail.class,moneyDetail);
			if(null != datas && datas.size() > 0){
				for (MoneyDetail md : datas) {
					if(null == md.getMoney()){
						md.setMoney(0.0);
					}
					moneyCount += md.getMoney();
					AppUser appUser = appUserService.findAppUserById(md.getUserId());
					if(null != appUser){
						md.setAppUser(appUser);
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该用户不存在");
					}
					
					UserMedal userMedal = new UserMedal();
					userMedal.setUserId(md.getUserId());
					//获取勋章列表
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
						md.setUserMedals(userMedals);
					}
					md.setRemainMoney(remainMoney);
				}
			}
			returnObject.setQueryBean(moneyDetail);
			returnObject.setData(datas);
		}else{
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数缺失");
		}
		return returnObject;
	}

}
