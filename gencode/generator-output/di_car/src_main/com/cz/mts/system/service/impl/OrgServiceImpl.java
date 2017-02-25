package com.cz.mts.system.service.impl;

import java.io.File;
import java.util.List;
import org.springframework.stereotype.Service;
import com.cz.mts.system.entity.Org;
import com.cz.mts.system.service.IOrgService;
import com.cz.mts.frame.entity.IBaseEntity;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.Page;
import com.cz.mts.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:27
 * @see com.cz.mts.system.service.impl.Org
 */
@Service("orgService")
public class OrgServiceImpl extends BaseSpringrainServiceImpl implements IOrgService {

   
    @Override
	public String  save(Object entity ) throws Exception{
	      Org org=(Org) entity;
	       return super.save(org).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      Org org=(Org) entity;
		 return super.saveorupdate(org).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 Org org=(Org) entity;
	return super.update(org);
    }
    @Override
	public Org findOrgById(Object id) throws Exception{
	 return super.findById(id,Org.class);
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
