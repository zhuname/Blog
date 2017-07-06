package com.cz.mts.system.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cz.mts.system.entity.Circle;
import com.cz.mts.system.exception.ParameterErrorException;
import com.cz.mts.system.service.ICircleService;
import com.cz.mts.frame.entity.IBaseEntity;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.Page;
import com.cz.mts.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-07-05 15:32:31
 * @see com.cz.mts.system.service.impl.Circle
 */
@Service("circleService")
public class CircleServiceImpl extends BaseSpringrainServiceImpl implements ICircleService {

   
    @Override
	public String  save(Object entity ) throws Exception{
	      Circle circle=(Circle) entity;
	       return super.save(circle).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      Circle circle=(Circle) entity;
	      
	      
	      if(circle.getType()==null||circle.getContent()==null||circle.getUserId()==null||circle.getCityId()==null){
	    	  throw new ParameterErrorException();
	      }
	      
	      circle.setTopCount(0);
	      circle.setCommentCount(0);
	      circle.setSumMoney(0.0);
	      circle.setCount(0);
	      circle.setCreateTime(new Date());
	      
		 return super.saveorupdate(circle).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 Circle circle=(Circle) entity;
	return super.update(circle);
    }
    @Override
	public Circle findCircleById(Object id) throws Exception{
	 return super.findById(id,Circle.class);
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
