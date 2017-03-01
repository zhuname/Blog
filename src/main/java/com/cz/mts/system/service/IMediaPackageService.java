package com.cz.mts.system.service;

import java.util.List;

import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.system.entity.MediaPackage;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:26
 * @see com.cz.mts.system.service.MediaPackage
 */
public interface IMediaPackageService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	MediaPackage findMediaPackageById(Object id) throws Exception;
	
	/**
	 * 视频红包列表
	 * @author wj
	 * @param mediaPackage
	 * @param page
	 * @param appUserId
	 * @return
	 * @throws Exception
	 */
	ReturnDatas list(MediaPackage mediaPackage,Page page,String appUserId) throws Exception;
	
	
	
}
