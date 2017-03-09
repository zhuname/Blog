package com.cz.mts.system.service;

import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:23
 * @see com.cz.mts.system.service.AppUser
 */
public interface IAppUserService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	AppUser findAppUserById(Object id) throws Exception;
	
	
	/**
	 * 余额支付接口
	 * @param userId
	 * @param type
	 * @param itemId
	 * @return
	 * @throws Exception
	 * @author wml
	 */
	Integer pay(Integer userId,Integer type,Integer itemId,String code)throws Exception;
	
	/**
	 * 第三方支付接口
	 * @param code  这个是id或者卡券的code
	 * @param type  1海报红包 2
	 * @param money
	 * @param wxCode
	 * @return
	 * @throws Exception
	 * @author wml
	 */
	Integer alipay(String code,Integer type,Double money,String wxCode,Integer payType  )throws Exception;
	
	/**
	 * 我的发布接口
	 * @author wj
	 * @param appUser
	 * @param page
	 * @return
	 * @throws Exception
	 */
	ReturnDatas getStatics(AppUser appUser,Page page) throws Exception;
	
}
