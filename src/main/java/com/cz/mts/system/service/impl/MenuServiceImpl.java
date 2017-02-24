package com.cz.mts.system.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cz.mts.frame.entity.IBaseEntity;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.Page;
import com.cz.mts.system.entity.Menu;
import com.cz.mts.system.entity.RoleMenu;
import com.cz.mts.system.service.BaseSpringrainServiceImpl;
import com.cz.mts.system.service.IMenuService;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 11:23:52
 * @see com.cz.mts.system.service.impl.Menu
 */
@Service("menuService")
public class MenuServiceImpl extends BaseSpringrainServiceImpl implements IMenuService {

   
    @Override
	public String  save(Object entity ) throws Exception{
	      Menu menu=(Menu) entity;
	       return super.save(menu).toString();
	}
    
    @Override
	@Cacheable(value = GlobalStatic.cacheKey, key = "'getNameByPageurl_'+#pageurl")
	public String getNameByPageurl(String pageurl) throws Exception {
		if(StringUtils.isBlank(pageurl)){
			return null;
		}
		Finder finder = Finder.getSelectFinder(Menu.class,"name").append(" WHERE pageurl=:pageurl ");
		finder.setParam("pageurl", pageurl);
		List<String> list = queryForList(finder,String.class);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		
		return list.toString();
	}


    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      Menu menu=(Menu) entity;
		 return super.saveorupdate(menu).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 Menu menu=(Menu) entity;
	return super.update(menu);
    }
    @Override
	public Menu findMenuById(Object id) throws Exception{
	 return super.findById(id,Menu.class);
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
