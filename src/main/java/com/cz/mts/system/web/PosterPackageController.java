package  com.cz.mts.system.web;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cz.mts.frame.annotation.SecurityApi;
import com.cz.mts.frame.cached.ICached;
import com.cz.mts.frame.controller.BaseController;
import com.cz.mts.frame.shiro.ShiroRedisCacheManager;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.MessageUtils;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Attention;
import com.cz.mts.system.entity.Category;
import com.cz.mts.system.entity.City;
import com.cz.mts.system.entity.MoneyDetail;
import com.cz.mts.system.entity.PosterPackage;
import com.cz.mts.system.entity.RedCity;
import com.cz.mts.system.entity.Snatch;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.IAttentionService;
import com.cz.mts.system.service.ICategoryService;
import com.cz.mts.system.service.ICityService;
import com.cz.mts.system.service.IMoneyDetailService;
import com.cz.mts.system.service.IPosterPackageService;
import com.cz.mts.system.service.IRedCityService;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:27
 * @see com.cz.mts.system.web.PosterPackage
 */
@Controller
@RequestMapping(value="/system/posterpackage")
public class PosterPackageController  extends BaseController {
	@Resource
	private IPosterPackageService posterPackageService;
	@Resource
	private ICityService cityService;
	
	private String listurl="/posterpackage/posterpackageList";
	
	
	@Resource
	private IRedCityService redCityService;
	
	@Resource
	private IAppUserService appUserService;
	
	@Resource
	private IMoneyDetailService moneyDetailService;
	
	@Resource
	private ICategoryService categoryService;
	
	@Resource
	private IAttentionService attentionService;
	//定义一个默认大小的链表队列
	private final LinkedBlockingQueue<Snatch> queue = new LinkedBlockingQueue<>() ;
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param posterPackage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,PosterPackage posterPackage) 
			throws Exception {
		ReturnDatas returnObject = listAminJson(request, model, posterPackage);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * @author wml
	 * @param request
	 * @param model
	 * @param posterPackage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,PosterPackage posterPackage) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		//List<PosterPackage> datas=posterPackageService.findListDataByFinder(null,page,PosterPackage.class,posterPackage);
		
		/*Finder finder=Finder.getSelectFinder(PosterPackage.class).append("select p.*,u.header FROM t_poster_package p LEFT JOIN t_app_user u ON p.userId = u.id WHERE p.id = 1");
		returnObject.setData(posterPackageService.queryForList(finder,PosterPackage.class));*/
		
		if(StringUtils.isNotBlank(posterPackage.getTitle())){
			Finder finder1=Finder.getSelectFinder(PosterPackage.class, "p.id,p.title,u.header as userHeader ,p.balance,u.name as userName,p.image,p.lookNum,p.status  ").append(" p LEFT JOIN t_app_user u ON p.userId = u.id WHERE p.userId IN (SELECT id FROM t_app_user WHERE `name`= :title ) OR p.title = :title and p.isDel = 0");
			finder1.setParam("title", posterPackage.getTitle());
			returnObject.setData(posterPackageService.queryForList(finder1,page));
		} else {
			Finder finder1=Finder.getSelectFinder(PosterPackage.class, "p.id,p.title,u.header as userHeader ,p.balance,u.name as userName,p.image,p.lookNum,p.status  ").append(" p LEFT JOIN t_app_user u ON p.userId = u.id WHERE  p.isDel = 0");
			if(posterPackage.getUserId()!=null){
				
				finder1.append(" and p.userId = :userId");
				
				finder1.setParam("userId", posterPackage.getUserId());
				
			}
			
			if(posterPackage.getStatus()!=null){
				
				finder1.append(" and p.status = :status");
				
				finder1.setParam("status", posterPackage.getStatus());
			}
			
			if(posterPackage.getCategoryId()!=null){
				
				finder1.append(" and p.categoryId = :categoryId");
				
				finder1.setParam("categoryId", posterPackage.getCategoryId());
				
			}

			returnObject.setData(posterPackageService.queryForList(finder1,page));
		}
		
		returnObject.setQueryBean(posterPackage);
		returnObject.setPage(page);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,PosterPackage posterPackage) throws Exception{
		
		// ==构造分页请求
		Page page = newPage(request);
		File file = posterPackageService.findDataExportExcel(null,listurl, page,PosterPackage.class,posterPackage);
		String fileName="posterPackage"+GlobalStatic.excelext;
		downFile(response, file, fileName,true);
		return;
	}
	
		/**
	 * 查看操作,调用APP端lookjson方法
	 */
	@RequestMapping(value = "/look")
	public String look(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception {
		ReturnDatas returnObject = lookjson(model, request, response,null);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/system/posterpackage/posterpackageLook";
	}

	
	/**
	 * 查看还包红包详情
	 * 查看的Json格式数据,为APP端提供数据
	 * @author wml
	 */
	@RequestMapping(value = "/look/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response,String appUserId) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
			 PosterPackage posterPackage = posterPackageService.findPosterPackageById(id);
			 
			 if(StringUtils.isNotBlank(appUserId)){
				 if(posterPackage.getLookNum()==null){
					 posterPackage.setLookNum(1);
				 }else{
					 posterPackage.setLookNum(posterPackage.getLookNum()+1);
				 }
			 }
			 
			 posterPackageService.update(posterPackage);
			 
			 //查询发红包的用户
			 if(posterPackage!=null&&posterPackage.getUserId()!=null){
				 AppUser appUser=appUserService.findAppUserById(posterPackage.getUserId());
				 if(appUser!=null){
					 posterPackage.setAppUser(appUser);
				 }
			 }
			 
			 //是否领取  look 1 为领取过的
			 if(posterPackage!=null&&StringUtils.isNotBlank(appUserId)){
				 MoneyDetail moneyDetail=new MoneyDetail();
				 moneyDetail.setUserId(Integer.parseInt(appUserId));
				 moneyDetail.setItemId(posterPackage.getId());
				 moneyDetail.setType(1);
				// ==构造分页请求
					Page page = newPage(request);
					// ==执行分页查询
					List<MoneyDetail> datas=moneyDetailService.findListDataByFinder(null,page,MoneyDetail.class,moneyDetail);
					if(datas!=null&&datas.size()>0){
						posterPackage.setIsLook(1);
					}else{
						posterPackage.setIsLook(0);
					}
			 }
			 
			 //是否关注
			 if(posterPackage!=null&&StringUtils.isNotBlank(appUserId)){
				 Attention attention=new Attention();
				 attention.setUserId(Integer.parseInt(appUserId));
				 attention.setItemId(posterPackage.getUserId());
				// ==构造分页请求
					Page page = newPage(request);
					// ==执行分页查询
					List<Attention> datas=attentionService.findListDataByFinder(null,page,Attention.class,attention);
					if(datas!=null&&datas.size()>0){
						posterPackage.setIsAtt(1);
					}else{
						posterPackage.setIsAtt(0);
					}
			 }
			 
			 //返回分类名称
			 if(posterPackage != null && posterPackage.getCategoryId() != null){
				 Category category = categoryService.findCategoryById(posterPackage.getCategoryId());
				 if(category != null){
					 if(StringUtils.isNotBlank(category.getName())){
						 posterPackage.setCategoryName(category.getName());
					 }
				 }
			 }
			 
			 
			 //返回城市名称
			 Finder finder = new Finder("SELECT * FROM t_red_city WHERE packageId=:id AND type=1");
			 finder.setParam("id", Integer.parseInt(strId));
			 List<RedCity> redCities = redCityService.queryForList(finder,RedCity.class);
			 if(null != redCities && redCities.size() > 0){
				 for (RedCity redCity : redCities) {
					if(null != redCity.getCityId()){
						City city = cityService.findCityById(redCity.getCityId());
						if(StringUtils.isNotBlank(city.getName())){
							redCity.setCityName(city.getName());
						}
					}
				}
				 posterPackage.setRedCities(redCities);
			 }
			 
			 returnObject.setData(posterPackage);
		}else{
			 returnObject.setMessage("参数缺失");
			 returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;
		
	}
	
	
	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/update")
	public @ResponseBody
	ReturnDatas saveorupdate(Model model,PosterPackage posterPackage,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
		
			posterPackageService.saveorupdate(posterPackage);
			
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	
	/**
	 * 进入修改页面,APP端可以调用 lookjson 获取json格式数据
	 */
	@RequestMapping(value = "/update/pre")
	public String updatepre(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		ReturnDatas returnObject = lookjson(model, request, response,null);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/system/posterpackage/posterpackageCru";
	}
	
	/**
	 * 删除海报红包接口
	 * @author wj
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delete/json")
	@SecurityApi
	public @ResponseBody ReturnDatas delete(HttpServletRequest request) throws Exception {

			// 执行删除
		try {
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
				PosterPackage posterPackage = posterPackageService.findPosterPackageById(id);
				if(null != posterPackage){
					posterPackage.setIsDel(1);
					posterPackageService.update(posterPackage,true);
				}
				return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_SUCCESS);
			} else {
				return new ReturnDatas(ReturnDatas.ERROR,"参数缺失");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new ReturnDatas(ReturnDatas.WARNING, MessageUtils.DELETE_WARNING);
	}
	
	/**
	 * 删除多条记录
	 * 
	 */
	@RequestMapping("/delete/more")
	public @ResponseBody
	ReturnDatas deleteMore(HttpServletRequest request, Model model) {
		String records = request.getParameter("records");
		if(StringUtils.isBlank(records)){
			 return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		String[] rs = records.split(",");
		if (rs == null || rs.length < 1) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_NULL_FAIL);
		}
		try {
			List<String> ids = Arrays.asList(rs);
			posterPackageService.deleteByIds(ids,PosterPackage.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
	}
	
	
	/**
	 * 抢红包
	 * @param request
	 * @param model
	 * @param id 红包id
	 * @param userId  操作人id
	 * @return
	 * @author wxy
	 * @date 2017年2月28日
	 */
	@RequestMapping("/snatch/json")
//	@SecurityApi
	public @ResponseBody 
	ReturnDatas snatchjson(HttpServletRequest request, Model model,String id,String userId){
		
		if(StringUtils.isBlank(id) || StringUtils.isBlank(userId)) {
			return new ReturnDatas(ReturnDatas.ERROR, "参数缺失!") ;
		}else {
			
//			ICached cached = cacheManager.getCached() ;
//			try {
//				AppUser user = (AppUser) cached.getCached(userId.getBytes()) ;
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			try {
				posterPackageService.snatch(userId, id) ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ReturnDatas result =  new ReturnDatas(ReturnDatas.SUCCESS,
					MessageUtils.UPDATE_SUCCESS);
//			result.setData(snatch2);
			return result ;
		}
		
	}
	
	/**
	 * 发布、修改红包接口
	 * 
	 */
	@RequestMapping("/update/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas saveorupdateJson(Model model,PosterPackage posterPackage,HttpServletRequest request,HttpServletResponse response,String cityIds) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		Object id= null;
		try {
			
			
			if(posterPackage.getId()==null){
				
				//必须有的参数
				
				if(posterPackage.getUserId()==null||posterPackage.getCategoryId()==null){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("参数缺失");
					return returnObject;
				}
				
				if(posterPackage.getCategoryId()!=null){
					
					Category category=categoryService.findById(posterPackage.getCategoryId(), Category.class);
					
					if(category==null){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("分类不存在");
						return returnObject;
					}
					
				}
				
				
				
				
				posterPackage.setStatus(0);
				
				posterPackage.setIsDel(0);
				
				posterPackage.setCreateTime(new Date());
				
				posterPackage.setBalance(posterPackage.getSumMoney());
				
				//生成验证码
				Long code=new Date().getTime();
				posterPackage.setCode("P"+code);
				
				id=posterPackageService.save(posterPackage);
				
				if(cityIds!=null){
					
					String[] cityId=cityIds.split(",");
					
					for (String string : cityId) {
						RedCity redCity=new RedCity();
						redCity.setCityId(Integer.parseInt(string));
						redCity.setPackageId(Integer.parseInt(id.toString()));
						redCity.setType(1);
						redCityService.save(redCity);
					}
					
				}
				
				
				returnObject.setData(posterPackageService.findPosterPackageById(id));
				
			}else {
				
				//如果分类改了就判断下
				if(posterPackage.getCategoryId()!=null){
					
					Category category=categoryService.findById(posterPackage.getCategoryId(), Category.class);
					
					if(category==null){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("分类不存在");
						return returnObject;
					}
					
				}
				
				id=posterPackageService.update(posterPackage, true);
				returnObject.setData(posterPackageService.findPosterPackageById(id));
				
			}
			
		} catch (Exception e) {
			
			if(posterPackage.getId()==null){
				posterPackageService.deleteById(id, PosterPackage.class);
			}
			
			e.printStackTrace();
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	
	/**
	 * 后台展示列表
	 * @param request
	 * @param model
	 * @param posterPackage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listAdmin/json")
	public @ResponseBody
	ReturnDatas listAminJson(HttpServletRequest request, Model model,PosterPackage posterPackage) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		posterPackage.setIsDel(0);
		List<PosterPackage> datas = posterPackageService.findListDataByFinder(null,page,PosterPackage.class,posterPackage);
		if(null != datas && datas.size() > 0){
			for (PosterPackage pc : datas) {
				if(StringUtils.isNotBlank(pc.getImage())){
					String[] image = pc.getImage().split(";");
					pc.setImage(image[0]);
				}
				
				//获取分类名称
				if(null != pc.getCategoryId()){
					Category category = categoryService.findCategoryById(pc.getCategoryId());
					if(null != category){
						if(StringUtils.isNotBlank(category.getName())){
							pc.setCategoryName(category.getName());
						}
					}
				}
				//获取用户名称
				if(null != pc.getUserId()){
					AppUser appUser = appUserService.findAppUserById(pc.getUserId());
					if(null != appUser){
						if(StringUtils.isNotBlank(appUser.getName())){
							pc.setAppUserName(appUser.getName());
						}
					}
				}
				
				if(null != pc.getPayType()){
					if(1 == pc.getPayType()){
						pc.setPayName("支付宝");
					}
					if(2 == pc.getPayType()){
						pc.setPayName("微信");
					}
					if(3 == pc.getPayType()){
						pc.setPayName("余额支付");
					}
				}
				
				
			}
		}
		returnObject.setQueryBean(posterPackage);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject; 
	}

}
