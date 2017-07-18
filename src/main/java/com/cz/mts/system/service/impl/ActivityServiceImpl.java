package com.cz.mts.system.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cz.mts.frame.entity.IBaseEntity;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.Page;
import com.cz.mts.system.entity.Activity;
import com.cz.mts.system.entity.ApplyMedal;
import com.cz.mts.system.entity.Awards;
import com.cz.mts.system.entity.RedCity;
import com.cz.mts.system.exception.ParameterErrorException;
import com.cz.mts.system.service.BaseSpringrainServiceImpl;
import com.cz.mts.system.service.IActivityService;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-07-03 16:06:04
 * @see com.cz.mts.system.service.impl.Activity
 */
@Service("activityService")
public class ActivityServiceImpl extends BaseSpringrainServiceImpl implements IActivityService {

   
    @Override
	public String  save(Object entity ) throws Exception{
	      Activity activity=(Activity) entity;
	       return super.save(activity).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      Activity activity=(Activity) entity;
		 return super.saveorupdate(activity).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 Activity activity=(Activity) entity;
	return super.update(activity);
    }
    @Override
	public Activity findActivityById(Object id) throws Exception{
	 return super.findById(id,Activity.class);
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
	public Activity addOrUpdate(Object entity, String awardsJson,String citys) throws Exception {
		// TODO Auto-generated method stub
		
		Activity activity = (Activity) entity;
		
		//基础参数
		if (activity.getType()==null||StringUtils.isBlank(activity.getContent())||StringUtils.isBlank(activity.getJoinExplain())||activity.getEndTime()==null||activity.getLongitude()==null||
				activity.getLatitude()==null||activity.getPhone()==null||activity.getUserId()==null) {
			throw new ParameterErrorException();
		}
		
		//图片视频判断
		switch (activity.getType()) {
		case 1:
			if(activity.getImage()==null){
				throw new ParameterErrorException();
			}
			break;
		case 2:
			if(activity.getMediaUrl()==null||activity.getMediaImage()==null){
				throw new ParameterErrorException();
			}
			break;
		}
		
		
		//保存基本参数
		activity.setCreateTime(new Date());
		activity.setStatus(1);
		activity.setTopCount(0);
		activity.setCommentCount(0);
		activity.setWinPrizePerson(0);
		activity.setIsDel(0);
		
		
		//判断用户有没有免审核勋章
		Finder finder=Finder.getSelectFinder(ApplyMedal.class).append(" where status=2 and userId=:userId and medalId in (select id from t_medal where status=4) and isEndStatus != 1");
		finder.setParam("userId",activity.getUserId());
		
		List<ApplyMedal> applyMedals = super.queryForList(finder, ApplyMedal.class);
		
		//免审核直接状态通过
		if(applyMedals!=null&&applyMedals.size()>0)
			activity.setStatus(3);
		
		Object id = null;
		if(activity.getId()==null){
			id = super.save(activity);
		}else {
			id=activity.getId();
			super.update(activity, true);
		}
		
		//从数据库拿出来
		activity = super.findById(id,Activity.class);
		
		//删除保存的奖项列表
		Awards awardsDel=new Awards();
		awardsDel.setActivityId(Integer.parseInt(id.toString()));
		super.deleteByEntity(awardsDel);
		
		
		//保存新的数据
		List<Awards> awardss = new ArrayList<>();
		
		int winSum=0;
		
		//json转成list
		JSONArray jsonArray = JSONArray.fromObject(awardsJson);
		for (int i = 0; i < jsonArray.size(); i++) {
			Awards awards = new Awards();  
			awards.setActivityId(Integer.parseInt(id.toString()));
			awards.setTitle(jsonArray.getJSONObject(i).getString("title"));
			awards.setContent(jsonArray.getJSONObject(i).getString("content"));
			awards.setSumCount(jsonArray.getJSONObject(i).getInt("sumCount"));
			awards.setRemainCount(awards.getSumCount());
			awardss.add(awards);
			winSum += awards.getSumCount();
        }
		
		activity.setWinPrizePerson(winSum);
		
		super.update(activity, true);
		
		//删除保存的奖项列表
		RedCity redCityDel=new RedCity();
		redCityDel.setPackageId(Integer.parseInt(id.toString()));
		super.deleteByEntity(redCityDel);
		
		//保存新的数据
		List<RedCity> redCitys = new ArrayList<>();
		
		//json转成list
		JSONArray jsonArrayRedCity = JSONArray.fromObject(citys);
		for (int i = 0; i < jsonArrayRedCity.size(); i++) {
			RedCity redCity = new RedCity();
			redCity.setCityId(jsonArrayRedCity.getJSONObject(i).getInt("cityId"));
			redCity.setPackageId(Integer.parseInt(id.toString()));
			redCity.setType(jsonArrayRedCity.getJSONObject(i).getInt("type"));
			redCitys.add(redCity);
	    }
		
		//保存城市
		super.save(redCitys);
		
		//保存奖项
		super.save(awardss);
		
		return activity;
		
	}
	
}
