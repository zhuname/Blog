package com.cz.mts.system.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cz.mts.frame.entity.IBaseEntity;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.SecUtils;
import com.cz.mts.system.entity.Password;
import com.cz.mts.system.service.BaseSpringrainServiceImpl;
import com.cz.mts.system.service.IPasswordService;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-03-03 15:28:50
 * @see com.cz.mts.system.service.impl.Password
 */
@Service("passwordService")
public class PasswordServiceImpl extends BaseSpringrainServiceImpl implements IPasswordService {

   
    @Override
	public String  save(Object entity ) throws Exception{
	      Password password=(Password) entity;
	       return super.save(password).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      Password password=(Password) entity;
		 return super.saveorupdate(password).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 Password password=(Password) entity;
	return super.update(password);
    }
    @Override
	public Password findPasswordById(Object id) throws Exception{
	 return super.findById(id,Password.class);
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
	public String findMdAfterPass(String password) throws Exception{
		String mdAfterPass = "";
		//首先判断这个密码是否存在
		Finder finder = new Finder("SELECT * FROM t_password WHERE mdBeforePass=:password");
		finder.setParam("password", password);
		List<Map<String, Object>> datas = queryForList(finder);
		if(null != datas && datas.size() > 0){
			Map<String, Object> m = datas.get(0);
			for (String k : m.keySet())  
		      {  
				if(k.equals("mdAfterPass")){
					mdAfterPass =  m.get(k).toString();
				}
		      }  
		}else{
			//保存
			mdAfterPass = SecUtils.encoderByMd5With32Bit(password);
			Password pw = new Password();
			pw.setMdBeforePass(password);
			pw.setMdAfterPass(mdAfterPass);
			saveorupdate(pw);
		}
		return mdAfterPass;
	}
	
	
	@Override
	public String findMdBeforePass(String afterMdPass) throws Exception{
		String mdBeforePass = "";
		Finder finder = new Finder("SELECT * FROM t_password WHERE mdAfterPass=:afterMdPass");
		finder.setParam("afterMdPass", afterMdPass);
		List<Map<String, Object>> datas = queryForList(finder);
		if(null != datas && datas.size() > 0){
			Map<String, Object> m = datas.get(0);
			for (String k : m.keySet())  
		      {  
				if(k.equals("mdBeforePass")){
					mdBeforePass =  m.get(k).toString();
				}
		      }  
		}else{
			mdBeforePass = "暂无该密码";
		}
		return mdBeforePass;
	}

}
