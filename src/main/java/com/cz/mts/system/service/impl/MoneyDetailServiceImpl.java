package com.cz.mts.system.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.LmediaPackage;
import com.cz.mts.system.entity.LposterPackage;
import com.cz.mts.system.entity.Medal;
import com.cz.mts.system.entity.MediaPackage;
import com.cz.mts.system.entity.MoneyDetail;
import com.cz.mts.system.entity.PosterPackage;
import com.cz.mts.system.entity.UserMedal;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.ILmediaPackageService;
import com.cz.mts.system.service.ILposterPackageService;
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
import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;


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
	@Resource
	private ILposterPackageService lposterPackageService;
	@Resource
	private ILmediaPackageService lmediaPackageService;
	
   
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
			page.setPageSize(10000);
			List<MoneyDetail> moneyDetails = findListDataByFinder(null, page, MoneyDetail.class, moneyDetail);
			MoneyDetail monDetail = new MoneyDetail();
			if(null != moneyDetails && moneyDetails.size() > 0){
				Double sumMoney = 0.0;
				for (MoneyDetail md : moneyDetails) {
					sumMoney += md.getMoney();
				}
				
				monDetail.setSumMoney(new BigDecimal(sumMoney).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
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
		
		LposterPackage lposterPackage = null;
		LmediaPackage lmediaPackage = null;
		
		if(null != moneyDetail.getType() && null != moneyDetail.getItemId()){
			Finder finderAll = Finder.getSelectFinder(MoneyDetail.class).append(" where itemId=:itemId and type=:type");
			finderAll.setParam("itemId", moneyDetail.getItemId());
			finderAll.setParam("type", moneyDetail.getType());
			//如果是海报红包
			if(1 == moneyDetail.getType()){
				//查询已抢金额
				Finder finder = new Finder("SELECT SUM(money)  FROM t_money_detail WHERE type=:type AND itemId=:itemId");
				finder.setParam("type", moneyDetail.getType());
				finder.setParam("itemId", moneyDetail.getItemId());
				List<java.util.Map<String, Object>> lists = super.queryForList(finder);
				if(null != lists && lists.size() > 0){
					for (java.util.Map<String, Object> map : lists) {
					  for (String k : map.keySet()){ 
						  if(null == map.get(k)){
							  moneyCount = 0.0;
						  }else{
							  moneyCount = Double.parseDouble(map.get(k).toString());
						  }
						}  
					}
				}
				PosterPackage posterPackage = posterPackageService.findPosterPackageById(moneyDetail.getItemId());
				if(null != posterPackage){
					if(null != posterPackage.getSumMoney()){
						remainMoney = posterPackage.getSumMoney() - moneyCount;
					}else{
						remainMoney = 0.0;
					}
					
					//判断红包剩余待抢次数是否为0
					if(null != posterPackage.getNum() && 0 == posterPackage.getNum()){
						Finder finderMax = new Finder("SELECT userId FROM t_l_poster_package WHERE money=(SELECT MAX(money) FROM t_l_poster_package WHERE packageId=:packageId) AND packageId=:packageId");
						finderMax.setParam("packageId", posterPackage.getId());
						List<LposterPackage> lposterPackages = queryForList(finderMax, LposterPackage.class);
						if(null != lposterPackages && lposterPackages.size() > 0){
							lposterPackage = lposterPackages.get(0);
						}
						
					}
					
				}
			}
			//如果是视频红包
			if(2 == moneyDetail.getType()){
				//查询已抢金额
				Finder finder = new Finder("SELECT SUM(money)  FROM t_money_detail WHERE type=:type AND itemId=:itemId");
				finder.setParam("type", moneyDetail.getType());
				finder.setParam("itemId", moneyDetail.getItemId());
				List<java.util.Map<String, Object>> lists = super.queryForList(finder);
				if(null != lists && lists.size() > 0){
					for (java.util.Map<String, Object> map : lists) {
					  for (String k : map.keySet()){  
						  if(null == map.get(k)){
							  moneyCount = 0.0;
						  }else{
							  moneyCount = Double.parseDouble(map.get(k).toString());
						  }
						}  
					}
				}
				MediaPackage mediaPackage = mediaPackageService.findMediaPackageById(moneyDetail.getItemId());
				if(null != mediaPackage){
					if(null != mediaPackage.getSumMoney()){
						remainMoney = mediaPackage.getSumMoney() - moneyCount;
					}else{
						remainMoney = 0.0;
					}
					
					//判断红包剩余待抢次数是否为0
					if(null != mediaPackage.getNum() && 0 == mediaPackage.getNum()){
						Finder finderMax = new Finder("SELECT userId FROM t_l_media_package WHERE money=(SELECT MAX(money) FROM t_l_media_package WHERE packageId=:packageId) AND packageId=:packageId");
						finderMax.setParam("packageId", mediaPackage.getId());
						List<LmediaPackage> lmediaPackages = queryForList(finderMax, LmediaPackage.class);
						if(null != lmediaPackages && lmediaPackages.size() > 0){
							lmediaPackage = lmediaPackages.get(0);
						}
						
					}
					
				}
			}
			List<MoneyDetail> datas = queryForList(finderAll, MoneyDetail.class, page);
			if(null != datas && datas.size() > 0){
				for (MoneyDetail md : datas) {
					//1.海报红包 2视频红包
					if(1 == md.getType()){
						if(null != lposterPackage){
							if(null != md.getUserId() && md.getUserId().intValue() == lposterPackage.getUserId().intValue()){
								md.setIsLuck(1);
							}else{
								md.setIsLuck(0);
							}
						}else{
							md.setIsLuck(0);
						}
						
					}
					
					if(2 == md.getType()){
						if(null != lmediaPackage){
							if(null != md.getUserId() && md.getUserId().intValue() == lmediaPackage.getUserId().intValue()){
								md.setIsLuck(1);
							}else{
								md.setIsLuck(0);
							}
						}else{
							md.setIsLuck(0);
						}
					}
					
					
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
					
//					UserMedal userMedal = new UserMedal();
//					userMedal.setUserId(md.getUserId());
//					Page newPage = new Page();
//					//获取勋章列表
//					List<UserMedal> userMedals = userMedalService.findListDataByFinder(null, newPage, UserMedal.class, userMedal);
//					if(null != userMedals && userMedals.size() > 0){
//						for (UserMedal um : userMedals) {
//							if(null != um.getMedalId()){
//								Medal medal = medalService.findMedalById(um.getMedalId());
//								if(null != medal){
//									um.setMedal(medal);
//								}
//							}
//						}
//						md.setUserMedals(userMedals);
//					}
					if(null != appUser.getUserMedals()){
						md.setUserMedals(appUser.getUserMedals());
					}
					md.setRemainMoney(remainMoney);
					md.setMoneyCount(moneyCount);
				}
			}
			returnObject.setQueryBean(moneyDetail);
			returnObject.setData(datas);
			returnObject.setPage(page);
		}else{
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数缺失");
		}
		return returnObject;
	}
	
	@Override
	public ReturnDatas rechargeStatics(MoneyDetail moneyDetail,Page page) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		moneyDetail.setType(4);
		Finder finder = Finder.getSelectFinder(MoneyDetail.class).append(" where 1=1 ");
		if(StringUtils.isNotBlank(moneyDetail.getUserName())){
			finder.append(" and userId in(select id from t_app_user where INSTR(`name`,:userName)>0 )");
			finder.setParam("userName", moneyDetail.getUserName());
		}
		Double sumMoney = 0.0;
		List<MoneyDetail> moneyDetails = findListDataByFinder(finder, page, MoneyDetail.class, moneyDetail);
		if(null != moneyDetails && moneyDetails.size() > 0){
			for (MoneyDetail md : moneyDetails) {
				if(null != md.getUserId()){
					AppUser appUser = appUserService.findAppUserById(md.getUserId());
					if(null != appUser){
						if(StringUtils.isNotBlank(appUser.getName())){
							md.setUserName(appUser.getName());
						}
					}
				}
			}
		}
		
		Page pageNew = new Page();
		pageNew.setPageSize(10000);
		List<MoneyDetail> moneyDetailss = findListDataByFinder(finder, pageNew, MoneyDetail.class, moneyDetail);
		if(null != moneyDetailss && moneyDetailss.size() > 0){
			for (MoneyDetail md : moneyDetailss) {
				//计算总计金额
				sumMoney += md.getMoney();
			}
		}
		
		HashMap<String, Object> map=new HashMap<String,Object>();  
		map.put("sumMoney", new BigDecimal(sumMoney).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		returnObject.setMap(map);
		returnObject.setData(moneyDetails);
		returnObject.setPage(page);
		returnObject.setQueryBean(moneyDetail);
		return returnObject;
	}

}
