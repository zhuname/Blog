package com.cz.mts.system.service.impl;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cz.mts.frame.entity.IBaseEntity;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.Page;
import com.cz.mts.system.entity.ConfigBean;
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
//		@Cacheable(value = GlobalStatic.cacheKey, key = "'ConfigData'")
		@CachePut(value = GlobalStatic.cacheKey, key = "'ConfigData'")
		public ConfigBean findParamBean() throws Exception {
			Finder finder=Finder.getSelectFinder(Configuration.class);
			List<Configuration> list = super.queryForList(finder, Configuration.class);
			//利用反射将list对象转化为Bean
			ConfigBean config = new ConfigBean() ;
			//获取类
			Class clazz = config.getClass() ;
			Iterator<Configuration> iter = list.iterator() ;
			while(iter.hasNext()){
				Configuration configuration = iter.next() ;
				String code = configuration.getCode() ;
				String val = configuration.getValue() ;
				//获取属性
				Field field = clazz.getDeclaredField(code) ;
				//打破封装性，但是会导致java对象的属性不安全
				field.setAccessible(true);
				//给configBean对象的属性赋值
				field.set(config, val);
			}
			return config ;
		}
		
		
		
		@CachePut(value = GlobalStatic.cacheKey, key = "'ConfigData'")
		@Override
		public ConfigBean saveOrUpdate(Configuration config) throws Exception {
			ConfigBean configBean = null ;
			if(config != null){
				//更新数据库
				update(config, true);
				configBean = findParamBean() ;
			}
			return configBean;
		}


}
