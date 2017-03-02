package com.cz.mts.system.service;

import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.system.entity.MoneyDetail;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:27
 * @see com.cz.mts.system.service.MoneyDetail
 */
public interface IMoneyDetailService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	MoneyDetail findMoneyDetailById(Object id) throws Exception;
	
	/**
	 * 统计已领红包金额和已领总人数
	 * @author wj
	 * @param moneyDetail
	 * @return
	 * @throws Exception
	 */
	ReturnDatas statics(MoneyDetail moneyDetail,Page page) throws Exception;
	
	
	
}
