package com.cz.mts.system.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.cz.mts.frame.entity.IBaseEntity;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.JsonUtils;
import com.cz.mts.frame.util.Page;
import com.cz.mts.system.entity.ConfigBean;
import com.cz.mts.system.entity.LposterPackage;
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
	public Object snatch(String userId, String packageId) throws Exception {
		// TODO Auto-generated method stub
		//框架本身写法
//		Cache<Object, Object> cached  = cacheManager.getCache(GlobalStatic.cacheKey);
//		Cache cache = cacheManager.getCache(GlobalStatic.cacheKey);
		
		//获取jedis客户端
		Jedis jedis = (Jedis) redisConnectionFactory.getConnection().getNativeConnection() ;
		//判断是否有这个红包
		if(jedis.exists(GlobalStatic.posterPackageL+packageId)){
			return null ;
		}
//		//载入lua脚本
		String sha = jedis.scriptLoad(GlobalStatic.luaScript);
//		//入参:待抢小红包列表，已抢小红包列表，已抢人map，抢包人id
		Object object = jedis.eval(GlobalStatic.luaScript, 4, GlobalStatic.posterPackageL, GlobalStatic.posterPackageConsumedList +packageId, GlobalStatic.posterPackageConsumedMap +packageId, userId);  
		if(object == null){  //代表已抢
			return null ;
		}
		synchronized (this) {
			//实现持久化
			//已抢红包的list,NO-SQL中的
			List<String> list = jedis.lrange(GlobalStatic.posterPackageConsumedList + packageId , 0 , -1) ;
			
			if(list != null && list.size() !=0){
//				Finder.getUpdateFinder(Finder.getTableName(LposterPackage.class))
				//已抢红包的list，mysql中的
				Finder finder = Finder.getSelectFinder(Finder.getTableName(LposterPackage.class)).append("where packageId = :packageId and userId != null") ;
				finder.setParam("packageId", Integer.valueOf(packageId)) ;
				List<LposterPackage> listMysql = super.queryForList(finder,LposterPackage.class) ;
				//现在判断，如果mysql的list size比nosql中的大，说明是脏数据，因为java明确说明：对象锁不一定会再一个线程结束后给第二个排队的线程
				//这样有可能是第三个或者第四个抢红包的人获得这个锁，造成脏数据的问题
				if(list.size() >= listMysql.size()){   //可以进行批量更新已抢红包了
					
					List<LposterPackage> listPersistence = new ArrayList<LposterPackage>() ;
					Iterator<String> iter = list.iterator() ;
					while(iter.hasNext()){
						String lppStr = iter.next() ;
						LposterPackage lpp = JsonUtils.readValue(lppStr, LposterPackage.class) ;
						listPersistence.add(lpp) ;
						
					}
					//批量更新小红包表
					super.update(listPersistence) ;
				}
			}
			
		}
		return object;
//		HashMap<String, String> map = new HashMap<>() ;
//		jedis.sadd(GlobalStatic.posterPackage, GlobalStatic.posterPackageL+packageId) ;  //待抢小红包
//		jedis.sadd(GlobalStatic.posterPackage, GlobalStatic.posterPackageConsumedList +packageId) ;  //已抢小红包List
//		jedis.sadd(GlobalStatic.posterPackage, GlobalStatic.posterPackageConsumedMap +packageId) ;  //已抢小红包Map
//		
//		JSONObject json1 = new JSONObject() ;
//		JSONObject json2 = new JSONObject() ;
//		JSONObject json3 = new JSONObject() ;
//		json1.put("userId", "") ;
//		json1.put("money", 2.0) ;
//		json2.put("userId", null);
//		json2.put("money", 4.0) ;
//		json3.put("userId", null);
//		json3.put("money", 6.0) ;
//		
//		jedis.lpush(GlobalStatic.posterPackageL+packageId, json1.toString(),json2.toString(),json3.toString()) ;
		
		
	
	}
		
	

}
