package com.cz.mts.system.service;

import com.cz.mts.system.entity.PosterPackage;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:27
 * @see com.cz.mts.system.service.PosterPackage
 */
public interface IPosterPackageService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	PosterPackage findPosterPackageById(Object id) throws Exception;
	
	/**
	 * 抢红包
	 * @param userId  抢红包人id
	 * @param packageId 被抢红包id
	 * @param osType 抢包人系统类型
	 * @param osType 加密红包的密码
	 * @return 抢红包的详情 （{}）
	 * @throws Exception
	 * @author wxy
	 * @date 2017年3月6日
	 */
	Object snatch(String userId,String packageId,String osType ,String pwd) throws Exception ;
	
	/**
	 * 审核红包
	 * @param packageId 红包id
	 * @param type 1通过 0拒绝
	 * @param failReason 拒绝原因
	 * @return
	 * @throws Exception
	 * @author wxy
	 * @date 2017年3月17日
	 */
	Object check(String packageId,String type,String failReason) throws Exception ;
	
}
