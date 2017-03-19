package com.cz.mts.frame.util;
/**
 * 全局的静态变量,用于全局变量的存放
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2013-03-19 11:08:15
 * @see com.cz.mts.frame.util.GlobalStatic
 */
public class GlobalStatic {
	public static  String rootdir=null;
	public static  String webinfodir=null;
	public static final String tempRootpath = System.getProperty("user.dir") + "/temp/";
	public static final int excelPageSize=1000;
	public static final  String suffix=".html";
	public static final String excelext=".xls";
	public static final String exportexcel="exportexcel";//是否是导出操作的key
	public static final String dataUpdate="更新";
	public static final String dataSave="保存";
	public static final String dataDelete="删除";
	public static final String cacheKey="springraincache";
	public static final String qxCacheKey="springrainqxcache";
	public static final String staticHtmlCacheKey="statichtmlcache";
	
	
	public static final String defaultCharset="UTF-8";
	
	public static final String tableExt="_history_";
	public static final String frameTableAlias="frameTableAlias";
	public static final String pageurlName="pageurlName";
	public static final String returnDatas="returnDatas";
	

	//认证
	//public static final String reloginsession="shiro-reloginsession";
	//认证
	public static final String authenticationCacheName="shiro-authenticationCacheName";
	//授权
	public static final String authorizationCacheName="shiro-authorizationCacheName";
	//realm名称
	public static final String authorizingRealmName="shiroDbAuthorizingRealmName";
	
	//缓存用户最后有效的登陆sessionId
	public static final String keeponeCacheName="shiro-keepone-session";
	
	
	/**
	 * 默认验证码参数名称
	 */
	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";

	/**
	 * 登录次数超出allowLoginNum时，存储在session记录是否展示验证码的key默认名称
	 */
	public static final String DEFAULT_SHOW_CAPTCHA_KEY_ATTRIBUTE = "showCaptcha";

	/**
	 * 默认在session中存储的登录次数名称
	 */
	public static final String DEFAULT_LOGIN_NUM_KEY_ATTRIBUTE = "loginNum";
	  //允许登录次数，当登录次数大于该数值时，会在页面中显示验证码
	public static final Integer allowLoginNum=1;
	
	
	
	static{
		String path= GlobalStatic.class.getClassLoader().getResource("").getPath();
		path = path.replace("\\", "/");
		int _info=path.indexOf("/WEB-INF/classes");
		if(_info>0){
			path=path.substring(0, _info);
		}
		webinfodir=path+"/WEB-INF";
		rootdir=path;
		
	}
	
	
	/**
	 * 红包内容
	 * *************** begin*****************
	 */
	
	//海报红包
	public static final String posterPackage = "posterPackage" ;
	//待抢小红包队列（剩余待抢的红包队列），后边append大红包的id
	public static final String posterPackageL = "pPackageL_" ;
	//已消费队列，后边append大红包的id
	public static final String posterPackageConsumedList = "pPackageCL_" ;
	//已抢到红包的用户map，后边append大红包的id
	public static final String posterPackageConsumedMap = "pPackageCM_" ;
	
	//视频红包
	public static final String mediaPackage = "mediaPackage" ;
	//待抢小红包队列（剩余待抢的红包队列），后边append大红包的id
	public static final String mediaPackageL = "mPackageL_" ;
	//已消费队列，后边append大红包的id
	public static final String mediaPackageConsumedList = "mPackageCL_" ;
	//已抢到红包的用户map，后边append大红包的id
	public static final String mediaPackageConsumedMap = "mPackageCM_" ;
	
//  -- 函数：尝试获得红包，如果成功，则返回json字符串，如果不成功，则返回空  
//  -- 参数：红包队列名， 已消费的队列名，去重的Map名，用户ID  
//  -- 返回值：nil 或者 json字符串，包含用户ID：userId，红包ID：id，红包金额：money
	public static final String luaScript = 
//			 "local bConsumed = redis.call('hexists', KEYS[3], KEYS[4]);\n"  
//          + "print('bConsumed:' ,bConsumed);\n"  
            "if redis.call('hexists', KEYS[3], KEYS[4]) ~= 0 then\n"  
            + "return nil\n"  
            + "else\n"  
            + "local hongBao = redis.call('rpop', KEYS[1]);\n"  
//          + "print('hongBao:', hongBao);\n"  
            + "if hongBao then\n"  
            + "local x = cjson.decode(hongBao);\n"   //解析json字符串为对象
            + "x['userId'] = KEYS[4];\n"  
            + "local re = cjson.encode(x);\n"  //对象转为json字符串
            + "redis.call('hset', KEYS[3], KEYS[4], KEYS[4]);\n"  
            + "redis.call('lpush', KEYS[2], re);\n"  
            + "return re;\n"  
            + "end\n"  
            + "end\n"  
            + "return nil";  
	
	/**
	 * ******************** end *********************
	 */
	

}
