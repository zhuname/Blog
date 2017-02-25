package com.cz.mts.system.service;

import java.util.List;

import com.cz.mts.system.entity.SysSysparam;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:29
 * @see com.cz.mts.system.service.SysSysparam
 */
public interface ISysSysparamService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SysSysparam findSysSysparamById(Object id) throws Exception;
	
	/**
	 * 查询常量表
	 * @author wj
	 * @return
	 * @throws Exception
	 */
	List<SysSysparam> findListParamData() throws Exception;
	
	
	
}
