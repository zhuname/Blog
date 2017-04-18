package com.cz.mts.system.service;

import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.system.entity.Card;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:25
 * @see com.cz.mts.system.service.Card
 */
public interface ICardService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Card findCardById(Object id) throws Exception;
	
	/**
	 * 
	 * 获取卡券列表
	 * @author wj
	 * @param card
	 * @param page
	 * @return
	 * @throws Exception
	 */
	ReturnDatas list(Card card,Page page) throws Exception;
	
	/**
	 * 统计待审核
	 * @author wj
	 * @return
	 * @throws Exception
	 */
	Integer statics() throws Exception;
	
	
	
}
