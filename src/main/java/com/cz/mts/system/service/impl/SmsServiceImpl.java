package com.cz.mts.system.service.impl;

import java.io.File;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.cz.mts.frame.entity.IBaseEntity;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.SMSUtil;
import com.cz.mts.system.entity.Sms;
import com.cz.mts.system.service.BaseSpringrainServiceImpl;
import com.cz.mts.system.service.ISmsService;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:28
 * @see com.cz.mts.system.service.impl.Sms
 */
@Service("smsService")
public class SmsServiceImpl extends BaseSpringrainServiceImpl implements ISmsService {

   
    @Override
	public String  save(Object entity ) throws Exception{
	      Sms sms=(Sms) entity;
	       return super.save(sms).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      Sms sms=(Sms) entity;
		 return super.saveorupdate(sms).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 Sms sms=(Sms) entity;
	return super.update(sms);
    }
    @Override
	public Sms findSmsById(Object id) throws Exception{
	 return super.findById(id,Sms.class);
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
		public Sms saveContent(Sms sms) throws Exception{
			//生成随机码
			Random r = new Random();
			String x = r.nextInt(99999) + "";
			if (x.length() < 4) {
				for (int i = 0; i <= 4 - x.length(); i++) {
					x += "0";
				}
			}  
			String smscode = x;
			sms.setContent(smscode);
			
			SMSUtil.SendSMS(sms.getPhone(), smscode,sms.getType().toString());
			save(sms);
			return sms;
		}

}
