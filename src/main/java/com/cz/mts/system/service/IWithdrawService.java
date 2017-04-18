package com.cz.mts.system.service;

import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.system.entity.Withdraw;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:29
 * @see com.cz.mts.system.service.Withdraw
 */
public interface IWithdrawService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Withdraw findWithdrawById(Object id) throws Exception;
	
	/**
	 * 申请提现
	 * @author wj
	 * @param withdraw
	 * @throws Exception
	 */
	ReturnDatas applyWithdraw(Withdraw withdraw) throws Exception;
	
	
	/**
	 * 待审核统计
	 * @author wj
	 * @return
	 * @throws Exception
	 */
	Integer statics() throws Exception;
	
	
	
}
