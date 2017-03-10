package com.cz.mts.system.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.cz.mts.frame.cached.ICached;
import com.cz.mts.frame.entity.IBaseEntity;
import com.cz.mts.frame.shiro.ShiroRedisCacheManager;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.SerializeUtil;
import com.cz.mts.system.entity.ConfigBean;
import com.cz.mts.system.entity.PosterPackage;
import com.cz.mts.system.service.BaseSpringrainServiceImpl;
import com.cz.mts.system.service.IPosterPackageService;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:27
 * @see com.cz.mts.system.service.impl.PosterPackage
 */
@Service("posterPackageService")
public class PosterPackageServiceImpl extends BaseSpringrainServiceImpl implements IPosterPackageService {

	@Autowired
	private RedisCacheManager cacheManager ;
	@Autowired
	private RedisConnectionFactory redisConnectionFactory ;
   
    @Override
	public String  save(Object entity ) throws Exception{
	      PosterPackage posterPackage=(PosterPackage) entity;
	       return super.save(posterPackage).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      PosterPackage posterPackage=(PosterPackage) entity;
		 return super.saveorupdate(posterPackage).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 PosterPackage posterPackage=(PosterPackage) entity;
	return super.update(posterPackage);
    }
    @Override
	public PosterPackage findPosterPackageById(Object id) throws Exception{
	 return super.findById(id,PosterPackage.class);
	}
	
/**
 * 列表查询,每个service都会重载,要把sql语句封装到service中,Finder只是最后的方案
 * @param finder
 * @param page
 * @param clazz
 * @param o
 * @return
 * @throws Exception
 */
        @Override
    public <T> List<T> findListDataByFinder(Finder finder, Page page, Class<T> clazz,
			Object o) throws Exception{
			 return super.findListDataByFinder(finder,page,clazz,o);
			}
	/**
	 * 根据查询列表的宏,导出Excel
	 * @param finder 为空则只查询 clazz表
	 * @param ftlurl 类表的模版宏
	 * @param page 分页对象
	 * @param clazz 要查询的对象
	 * @param o  querybean
	 * @return
	 * @throws Exception
	 */
		@Override
	public <T> File findDataExportExcel(Finder finder,String ftlurl, Page page,
			Class<T> clazz, Object o)
			throws Exception {
			 return super.findDataExportExcel(finder,ftlurl,page,clazz,o);
		}
		

		
	@Override
	public Integer snatch(String userId, String packageId) throws Exception {
		// TODO Auto-generated method stub
		//框架本身写法
//		Cache<Object, Object> cached  = cacheManager.getCache(GlobalStatic.cacheKey);
//		Cache cache = cacheManager.getCache(GlobalStatic.cacheKey);
		
		//获取jedis客户端
		Jedis jedis = (Jedis) redisConnectionFactory.getConnection().getNativeConnection() ;
		//判断是否有这个红包
//		if(jedis.exists(GlobalStatic.posterPackageL+packageId)){
//			
//		}
//		//载入lua脚本
		String sha = jedis.scriptLoad(GlobalStatic.luaScript);
//		//入参:待抢小红包列表，已抢小红包列表，已抢人map，抢包人id
//		Object object = jedis.eval(GlobalStatic.luaScript, 4, GlobalStatic.posterPackageL+packageId, GlobalStatic.posterPackageConsumedList +packageId, GlobalStatic.posterPackageConsumedMap +packageId, userId);  
		HashMap<String, String> map = new HashMap<>() ;
		jedis.sadd(GlobalStatic.posterPackage, GlobalStatic.posterPackageL+packageId) ;  //待抢小红包
		jedis.sadd(GlobalStatic.posterPackage, GlobalStatic.posterPackageConsumedList +packageId) ;  //已抢小红包List
		jedis.sadd(GlobalStatic.posterPackage, GlobalStatic.posterPackageConsumedMap +packageId) ;  //已抢小红包Map
		
		
		
		
		return null;
	}
		
	

}
