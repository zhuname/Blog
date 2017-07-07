package com.cz.mts.system.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cz.mts.system.entity.JoinActivity;
import com.cz.mts.system.exception.HaveUserErrorException;
import com.cz.mts.system.exception.ParameterErrorException;
import com.cz.mts.system.service.IJoinActivityService;
import com.cz.mts.frame.entity.IBaseEntity;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.Page;
import com.cz.mts.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-07-03 16:06:21
 * @see com.cz.mts.system.service.impl.JoinActivity
 */
@Service("joinActivityService")
public class JoinActivityServiceImpl extends BaseSpringrainServiceImpl implements IJoinActivityService {

   
    @Override
	public String  save(Object entity ) throws Exception{
	      JoinActivity joinActivity=(JoinActivity) entity;
	       return super.save(joinActivity).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      JoinActivity joinActivity=(JoinActivity) entity;
	      
	      if(joinActivity.getType()==null||joinActivity.getContent()==null||joinActivity.getUserId()==null||joinActivity.getActivityId()==null){
	    	  throw new ParameterErrorException();
	      }
	      
	      joinActivity.setCreateTime(new Date());
	      joinActivity.setTopCount(0);
	      joinActivity.setCommentCount(0);
	      
	      //判断是否参与
	      Finder finder = Finder.getSelectFinder(JoinActivity.class).append(" where 1=1 and userId=:userId and activityId=:activityId");
	      
	      finder.setParam("userId", joinActivity.getUserId());
	      finder.setParam("activityId", joinActivity.getActivityId());
	      
	      List<JoinActivity> datas=super.findListDataByFinder(finder,null,JoinActivity.class,null);
	      
	      if(datas!=null&&datas.size()>0){
	    	  
	    	  throw new HaveUserErrorException();
	      }
	      
		 return super.saveorupdate(joinActivity).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 JoinActivity joinActivity=(JoinActivity) entity;
	return super.update(joinActivity);
    }
    @Override
	public JoinActivity findJoinActivityById(Object id) throws Exception{
	 return super.findById(id,JoinActivity.class);
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

}
