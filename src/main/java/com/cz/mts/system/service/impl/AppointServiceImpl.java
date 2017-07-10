package com.cz.mts.system.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.print.attribute.standard.Media;

import org.springframework.stereotype.Service;

import com.cz.mts.system.entity.Appoint;
import com.cz.mts.system.entity.MediaPackage;
import com.cz.mts.system.entity.PosterPackage;
import com.cz.mts.system.exception.ParameterErrorException;
import com.cz.mts.system.service.IAppointService;
import com.cz.mts.frame.entity.IBaseEntity;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.Page;
import com.cz.mts.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-07-06 14:42:38
 * @see com.cz.mts.system.service.impl.Appoint
 */
@Service("appointService")
public class AppointServiceImpl extends BaseSpringrainServiceImpl implements IAppointService {

   
    @Override
	public String  save(Object entity ) throws Exception{
	      Appoint appoint=(Appoint) entity;
	       return super.save(appoint).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      Appoint appoint=(Appoint) entity;
	      
	      
	  	/***生成6位不重复的兑换码开始**/
			String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
				"g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
				"t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
				"6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
				"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z" };

			StringBuffer shortBuffer = new StringBuffer();
			String uuid = UUID.randomUUID().toString().replace("-", "");
			for (int j = 0; j < 6; j++) {
				String str = uuid.substring(j * 4, j * 4 + 4);
				int x = Integer.parseInt(str, 16);
				shortBuffer.append(chars[x % 0x3E]);
			}
			/***生成6位不重复的兑换码结束**/
			
			appoint.setCardCode(shortBuffer.toString());
	      
			
			if(appoint.getType()==null||appoint.getItemId()==null||appoint.getPhone()==null||appoint.getUserId()==null){
				throw new ParameterErrorException();
			}
			
			appoint.setCode("A"+new Date().getTime());
			
			
	      
		 return super.saveorupdate(appoint).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 Appoint appoint=(Appoint) entity;
	return super.update(appoint);
    }
    @Override
	public Appoint findAppointById(Object id) throws Exception{
	 return super.findById(id,Appoint.class);
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
