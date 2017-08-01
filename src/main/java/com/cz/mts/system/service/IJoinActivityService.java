package com.cz.mts.system.service;

import com.cz.mts.system.entity.JoinActivity;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-07-03 16:06:21
 * @see com.cz.mts.system.service.JoinActivity
 */
public interface IJoinActivityService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	JoinActivity findJoinActivityById(Object id) throws Exception;
	
	
	
}
