package com.cz.mts.system.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cz.mts.frame.entity.IBaseEntity;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.Page;
import com.cz.mts.system.entity.Configuration;
import com.cz.mts.system.service.BaseSpringrainServiceImpl;
import com.cz.mts.system.service.IConfigurationService;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:26
 * @see com.cz.mts.system.service.impl.Configuration
 */
@Service("configurationService")
public class ConfigurationServiceImpl extends BaseSpringrainServiceImpl implements IConfigurationService {

   
    @Override
	public String  save(Object entity ) throws Exception{
	      Configuration configuration=(Configuration) entity;
	       return super.save(configuration).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      Configuration configuration=(Configuration) entity;
		 return super.saveorupdate(configuration).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 Configuration configuration=(Configuration) entity;
	return super.update(configuration);
    }
    @Override
	public Configuration findConfigurationById(Object id) throws Exception{
	 return super.findById(id,Configuration.class);
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
		@Cacheable(value = GlobalStatic.cacheKey, key = "'findListParamData'")
		public List<Configuration> findListParamData() throws Exception {
			Finder finder=Finder.getSelectFinder(Configuration.class);
			return super.queryForList(finder, Configuration.class);
		}

}
