package com.cz.mts.system.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import cn.jpush.api.push.model.notification.IosNotification;

import com.cz.mts.frame.entity.IBaseEntity;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.JsonUtils;
import com.cz.mts.frame.util.Page;
import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Attention;
import com.cz.mts.system.entity.LposterPackage;
import com.cz.mts.system.entity.MoneyDetail;
import com.cz.mts.system.entity.PosterPackage;
import com.cz.mts.system.service.BaseSpringrainServiceImpl;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.ILposterPackageService;
import com.cz.mts.system.service.IMoneyDetailService;
import com.cz.mts.system.service.IPosterPackageService;
import com.cz.mts.system.service.NotificationService;
import com.cz.mts.system.web.AttenThreadController;
import com.google.gson.JsonArray;


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
	@Autowired
	private IAppUserService appUserService ;
	@Autowired
	private ILposterPackageService lposterPackageService ;
	@Autowired
	private IMoneyDetailService moneyDetailService ;
	@Autowired
	private NotificationService notificationService;
	
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
	public Object snatch(String userId, String packageId,String osType,String pwd) throws Exception {
		// TODO Auto-generated method stub
		//框架本身写法
//		Cache<Object, Object> cached  = cacheManager.getCache(GlobalStatic.cacheKey);
//		Cache cache = cacheManager.getCache(GlobalStatic.cacheKey);
		
		//获取红包
		PosterPackage _package = findById(packageId, PosterPackage.class) ;
		AppUser appUser = appUserService.findAppUserById(_package.getUserId());
		if(_package != null && _package.getStatus() == 3){  //通过审核的红包才能抢
			if(_package.getEncrypt() == 0){  //看是否是加密红包，如果不是
				//看这个用户是否能在此小时内能抢
				AppUser _user = findById(userId, AppUser.class) ;
				if(_user != null && _user.getCurrentLqNum() > 0){
					//不用管了，可以往下走了，不走实际逻辑
				}else{
					return "本时间段没有可抢机会" ;
				}
			}else {  //加密红包
				if(StringUtils.isBlank(pwd)){
					return "请输入加密口令" ;
				}else {
					if(!pwd.equals(_package.getCommand())){
						return  "加密口令错误" ;
					}
				}
			}
		}
		
		
		//获取jedis客户端
		Jedis jedis = (Jedis) redisConnectionFactory.getConnection().getNativeConnection() ;
		//判断是否有这个红包
		if(!jedis.exists(GlobalStatic.posterPackageL+packageId)){
			return "没有该待抢红包" ;
		}
//		//载入lua脚本
		String sha = jedis.scriptLoad(GlobalStatic.luaScript);
//		//入参:待抢小红包列表，已抢小红包列表，已抢人map，抢包人id
		Object object = jedis.eval(GlobalStatic.luaScript, 4, GlobalStatic.posterPackageL+packageId, GlobalStatic.posterPackageConsumedList +packageId, GlobalStatic.posterPackageConsumedMap +packageId, userId);  
		if(object == null){  //代表已抢
			return "红包已抢" ;
		}
		synchronized (this) {
			//实现持久化
			//已抢红包的list,NO-SQL中的
			List<String> list = jedis.lrange(GlobalStatic.posterPackageConsumedList + packageId , 0 , -1) ;
			
			if(list != null && list.size() !=0){
				AppUser _user = findById(userId, AppUser.class) ;
				if(null != _user &&  _user.getIsPush() != null && 1 == _user.getIsPush()){
					//给发布人发推送
					notificationService.notify(15, Integer.parseInt(packageId), _package.getUserId());
				}
				
				//已抢红包的list，mysql中的
				Finder finder = Finder.getSelectFinder(LposterPackage.class).append("where packageId = :packageId and !ISNULL(userId) ") ;
				finder.setParam("packageId", Integer.valueOf(packageId)) ;
				List<LposterPackage> listMysql = super.queryForList(finder,LposterPackage.class) ;
				//现在判断，如果mysql的list size比nosql中的大，说明是脏数据，因为java明确说明：对象锁不一定会再一个线程结束后给第二个排队的线程
				//这样有可能是第三个或者第四个抢红包的人获得这个锁，造成脏数据的问题
				if(list.size() >= listMysql.size()){   //可以进行批量更新已抢红包了
					//最后需要持久化的小红包
					List<LposterPackage> listPersistence = new ArrayList<LposterPackage>() ;
					Iterator<String> iter = list.iterator() ;
					while(iter.hasNext()){
						
						String lppStr = iter.next() ;
						LposterPackage lpp = JsonUtils.readValue(lppStr, LposterPackage.class) ;
						listPersistence.add(lpp) ;
						
					}
					//批量更新小红包表
					lposterPackageService.update(listPersistence) ;
					//开始解决用户表的余额
					//移除已经持久化的
					//listNosql是需要更新的list
					List<String> listNosql = list ;
					List<LposterPackage> _listNosql = JSONArray.toList(JSONArray.fromObject(listNosql), LposterPackage.class) ;
					_listNosql.removeAll(listMysql) ;
					Iterator<LposterPackage> iterNosql = _listNosql.iterator() ;
					//需要更新的已抢金额
					BigDecimal money = new BigDecimal(0) ;
					while(iterNosql.hasNext()){
						LposterPackage lpp = iterNosql.next() ;
						//转换成bean
//						LposterPackage lpp = JsonUtils.readValue(lppStr, LposterPackage.class) ;
						//金额累计
						money = money.add(new BigDecimal(lpp.getMoney())) ;
						Integer _userId = lpp.getUserId() ;
						AppUser user = super.findById(_userId, AppUser.class) ;
						BigDecimal nowBalance = new BigDecimal(user.getBalance()).add(new BigDecimal(lpp.getMoney())) ;
						user.setBalance(nowBalance.doubleValue());
						user.setCurrentLqNum(user.getCurrentLqNum() - 1);  //剩余待抢次数
						//更新用户余额
						appUserService.saveorupdate(user) ;
						//更新明细表
						MoneyDetail md = new MoneyDetail();
						md.setCreateTime(new Date());
						md.setUserId(_userId);
						md.setType(1);  //海报红包
						md.setMoney(lpp.getMoney());
						md.setBalance(nowBalance.doubleValue());
						md.setItemId(lpp.getPackageId());
						md.setOsType(osType);
						moneyDetailService.saveorupdate(md) ;
					}
					
					//解决红包表的数据
					//本次更新的已抢次数
					Integer num = _listNosql.size() ;
					//这里需要再取一次，防止并发造成pp数据不一致
					PosterPackage pp = findById(packageId, PosterPackage.class) ;
					pp.setNum(pp.getNum() - num);
					pp.setBalance((new BigDecimal(pp.getBalance()).subtract(money)).doubleValue());
					//看红包是否抢完
					if(pp.getNum() == 0){
						pp.setStatus(4);
						pp.setEndTime(new Date());
						if(null != appUser && 1 == appUser.getIsPush()){
							//给发布人发推送
							notificationService.notify(16, pp.getId(), pp.getUserId());
						}
					}
					super.saveorupdate(pp) ;
				}
			}
			
		}
		return object;
	
	}

	@Override
	public Object check(String packageId, String type,String failReason) throws Exception {
		// TODO Auto-generated method stub
		PosterPackage pp = findById(Integer.valueOf(packageId), PosterPackage.class) ;
		AppUser appUser = appUserService.findAppUserById(pp.getUserId());
		if(pp == null)
			return null ;
		if("0".equals(type)){  //拒绝
			pp.setStatus(2);
			pp.setFailTime(new Date());
			pp.setFailReason(failReason);
			if(null != appUser && 1 == appUser.getIsPush()){
				//给发布人发推送
				notificationService.notify(17, Integer.parseInt(packageId), pp.getUserId());
			}
			
			if(appUser!=null){
				appUser.setBalance(pp.getPayMoney()+appUser.getBalance());
				super.update(appUser, true);
				
				//记录用户的余额记录
				MoneyDetail moneyDetail=new MoneyDetail();
				moneyDetail.setBalance(appUser.getBalance());
				moneyDetail.setCreateTime(new Date());
				moneyDetail.setItemId(pp.getId());
				moneyDetail.setMoney(pp.getPayMoney());
				moneyDetail.setType(11);
				moneyDetail.setUserId(appUser.getId());
				super.save(moneyDetail);
				
			}
			
		}else {  //审核通过
			pp.setStatus(3);
			pp.setSuccTime(new Date());
			if(null != appUser && 1 == appUser.getIsPush()){
				//给发布人发推送
				notificationService.notify(19, Integer.parseInt(packageId), pp.getUserId());
			}
			
			//更新attention表中的isUpdate字段
			Finder finderAtte = new Finder("UPDATE t_attention SET isUpdate = 1 WHERE itemId = :itemId");
			finderAtte.setParam("itemId", pp.getUserId());
			super.update(finderAtte);
			//更新appUser表中的isUpdate字段
			Finder finderAppUser = new Finder("UPDATE t_app_user SET isUpdate = 1 WHERE id in (SELECT userId FROM t_attention WHERE itemId = :itemId)");
			finderAppUser.setParam("itemId",  pp.getUserId());
			super.update(finderAppUser);
			
			
			//查询接收推送的用户
			Finder finderSelect = new Finder("SELECT * FROM t_attention WHERE itemId = :itemId");
			finderSelect.setParam("itemId", pp.getUserId());
			List<Attention> attentions = super.queryForList(finderSelect,Attention.class);
			for (Attention attention : attentions) {
				AttenThreadController attenThreadController = new AttenThreadController(pp, null, attention, null, notificationService, appUser);
				attenThreadController.run();
			}
			
			//先看看分几个人，要是分一个人的话就不用分了，直接生成一个就好了
			if(pp.getLqNum() != null && pp.getLqNum() == 1){
				LposterPackage lp = new LposterPackage();
				Double money = new Double(String.valueOf(pp.getSumMoney())) ;
				lp.setMoney(money);
				lp.setPackageId(Integer.valueOf(packageId));
				lposterPackageService.save(lp) ;
			}else {
				//开始分小红包
				Long total = (new BigDecimal(pp.getSumMoney()).multiply(new BigDecimal(100))).longValue() ;
				long[] moneys = generate(total, pp.getLqNum(), total, 1) ;
				List<LposterPackage> list = new ArrayList<>() ;
				for (int i = 0; i < moneys.length; i++) {
					LposterPackage lp = new LposterPackage();
					Double money = new Double(String.valueOf(moneys[i])) ;
					lp.setMoney(money/100);
					lp.setPackageId(Integer.valueOf(packageId));
					
					list.add(lp) ;
				}
				//批量保存小红包
				lposterPackageService.save(list) ;
			}
			//如果没有异常，就往redis中存放
			//获取jedis客户端
			Jedis jedis = (Jedis) redisConnectionFactory.getConnection().getNativeConnection() ;
			jedis.sadd(GlobalStatic.posterPackage, GlobalStatic.posterPackageL+packageId) ;  //待抢小红包
			jedis.sadd(GlobalStatic.posterPackage, GlobalStatic.posterPackageConsumedList +packageId) ;  //已抢小红包List
			jedis.sadd(GlobalStatic.posterPackage, GlobalStatic.posterPackageConsumedMap +packageId) ;  //已抢小红包Map
//			
			//因为不能像hibernate一样保存后就持久化，所以要重新取一下
			Finder finder = Finder.getSelectFinder(LposterPackage.class).append("where packageId = :packageId ") ;
			finder.setParam("packageId", Integer.valueOf(packageId)) ;
			List<LposterPackage> listPersistence = super.queryForList(finder, LposterPackage.class) ;
			
			Iterator<LposterPackage> iter = listPersistence.iterator() ;
			while(iter.hasNext()){
				LposterPackage lpWhile = iter.next() ;
				String lpStr = JsonUtils.writeValueAsString(lpWhile) ;
				
				jedis.lpush(GlobalStatic.posterPackageL+packageId, lpStr) ;
			}
		}
		super.saveorupdate(pp) ;
		return "审核成功";
	}
		
	/**
	 * **********************************************************************************
	 * *********************************生成红包工具-----start------*************************************
	 * ********************************************************************************
	 */
	static Random random = new Random();  
    static {  
        random.setSeed(System.currentTimeMillis());  
    }
    /** 
     * 生产min和max之间的随机数，但是概率不是平均的，从min到max方向概率逐渐加大。 
     * 先平方，然后产生一个平方值范围内的随机数，再开方，这样就产生了一种“膨胀”再“收缩”的效果。 
     *  
     * @param min 
     * @param max 
     * @return 
     */  
    static long xRandom(long min, long max) {  
    	if(max-min == 0){   //防止0开方
    		return 1 ;  //此时随机生成的都是1分钱
    	}
        return sqrt(nextLong(sqr(max - min)));  
    }
    
    static long sqrt(long n) {  
        // 改进为查表？  
        return (long) Math.sqrt(n);  
    }  
  
    static long sqr(long n) {  
        // 查表快，还是直接算快？  
        return n * n;  
    }  
      
    static long nextLong(long n) {  
        return random.nextInt((int) n);  
    }  
  
    static long nextLong(long min, long max) {  
        return random.nextInt((int) (max - min + 1)) + min;  
    }  
    
    /** 
     *  
     * @param total 
     *            红包总额   单位：分
     * @param count 
     *            红包个数  
     * @param max 
     *            每个小红包的最大额  单位：分
     * @param min 
     *            每个小红包的最小额  单位：分
     * @return 存放生成的每个小红包的值的数组 
     */  
    public static long[] generate(long total, int count, long max, long min) {  
        long[] result = new long[count];  
  
        long average = total / count;  
  
        long a = average - min;  
        long b = max - min;  
  
        //  
        //这样的随机数的概率实际改变了，产生大数的可能性要比产生小数的概率要小。  
        //这样就实现了大部分红包的值在平均数附近。大红包和小红包比较少。  
        long range1 = sqr(average - min);  
        long range2 = sqr(max - average);  
  
        for (int i = 0; i < result.length; i++) {  
            //因为小红包的数量通常是要比大红包的数量要多的，因为这里的概率要调换过来。  
            //当随机数>平均值，则产生小红包  
            //当随机数<平均值，则产生大红包  
            if (nextLong(min, max) > average) {  
                // 在平均线上减钱  
//              long temp = min + sqrt(nextLong(range1));  
                long temp = min + xRandom(min, average);  
                result[i] = temp;  
                total -= temp;  
            } else {  
                // 在平均线上加钱  
//              long temp = max - sqrt(nextLong(range2));  
                long temp = max - xRandom(average, max);  
                result[i] = temp;  
                total -= temp;  
            }  
        }  
        // 如果还有余钱，则尝试加到小红包里，如果加不进去，则尝试下一个。  
        while (total > 0) {  
            for (int i = 0; i < result.length; i++) {  
                if (total > 0 && result[i] < max) {  
                    result[i]++;  
                    total--;  
                }  
            }  
        }  
        // 如果钱是负数了，还得从已生成的小红包中抽取回来  
        while (total < 0) {  
            for (int i = 0; i < result.length; i++) {  
                if (total < 0 && result[i] > min) {  
                    result[i]--;  
                    total++;  
                }  
            }  
        }  
        return result;  
    }  
    /**
	 * **********************************************************************************
	 * *********************************生成红包工具-----end------*************************************
	 * ********************************************************************************
	 */

}
