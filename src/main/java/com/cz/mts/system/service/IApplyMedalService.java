package com.cz.mts.system.service;

import com.cz.mts.system.entity.ApplyMedal;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:24
 * @see com.cz.mts.system.service.ApplyMedal
 */
public interface IApplyMedalService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	ApplyMedal findApplyMedalById(Object id) throws Exception;
	
	
	/**
	 * 待审核统计接口
	 * @author wj
	 * @return
	 * @throws Exception
	 */
	Integer statics() throws Exception;
	
	
	
}
