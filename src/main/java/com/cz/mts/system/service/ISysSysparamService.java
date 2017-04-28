package com.cz.mts.system.service;

import java.util.List;

import com.cz.mts.system.entity.SysParamBean;
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
	SysParamBean findParamBean() throws Exception;
	
	/**
	 * 新增或者修改公共参数
	 * @param param
	 * @return
	 * @throws Exception
	 */
	SysParamBean saveOrUpdate(SysSysparam param) throws  Exception ;

	
	
	
}
