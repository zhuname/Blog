package com.cz.mts.system.service.impl;

import java.io.File;
import java.util.List;
import org.springframework.stereotype.Service;
import com.cz.mts.system.entity.Oper;
import com.cz.mts.system.service.IOperService;
import com.cz.mts.frame.entity.IBaseEntity;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.Page;
import com.cz.mts.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-07-05 15:15:34
 * @see com.cz.mts.system.service.impl.Oper
 */
@Service("operService")
public class OperServiceImpl extends BaseSpringrainServiceImpl implements IOperService {

   
    @Override
	public String  save(Object entity ) throws Exception{
	      Oper oper=(Oper) entity;
	       return super.save(oper).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      Oper oper=(Oper) entity;
		 return super.saveorupdate(oper).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 Oper oper=(Oper) entity;
	return super.update(oper);
    }
    @Override
	public Oper findOperById(Object id) throws Exception{
	 return super.findById(id,Oper.class);
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
