package com.cz.mts.system.service.impl;

import java.io.File;
import java.util.List;
import org.springframework.stereotype.Service;
import com.cz.mts.system.entity.FwlogHistory2017;
import com.cz.mts.system.service.IFwlogHistory2017Service;
import com.cz.mts.frame.entity.IBaseEntity;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.Page;
import com.cz.mts.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 11:23:51
 * @see com.cz.mts.system.service.impl.FwlogHistory2017
 */
@Service("fwlogHistory2017Service")
public class FwlogHistory2017ServiceImpl extends BaseSpringrainServiceImpl implements IFwlogHistory2017Service {

   
    @Override
	public String  save(Object entity ) throws Exception{
	      FwlogHistory2017 fwlogHistory2017=(FwlogHistory2017) entity;
	       return super.save(fwlogHistory2017).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      FwlogHistory2017 fwlogHistory2017=(FwlogHistory2017) entity;
		 return super.saveorupdate(fwlogHistory2017).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 FwlogHistory2017 fwlogHistory2017=(FwlogHistory2017) entity;
	return super.update(fwlogHistory2017);
    }
    @Override
	public FwlogHistory2017 findFwlogHistory2017ById(Object id) throws Exception{
	 return super.findById(id,FwlogHistory2017.class);
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
