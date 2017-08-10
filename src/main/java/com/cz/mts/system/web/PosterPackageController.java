package  com.cz.mts.system.web;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cz.mts.frame.annotation.SecurityApi;
import com.cz.mts.frame.controller.BaseController;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.JsonUtils;
import com.cz.mts.frame.util.MessageUtils;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Attention;
import com.cz.mts.system.entity.Card;
import com.cz.mts.system.entity.Category;
import com.cz.mts.system.entity.City;
import com.cz.mts.system.entity.LposterPackage;
import com.cz.mts.system.entity.Medal;
import com.cz.mts.system.entity.MediaPackage;
import com.cz.mts.system.entity.MoneyDetail;
import com.cz.mts.system.entity.Oper;
import com.cz.mts.system.entity.PosterPackage;
import com.cz.mts.system.entity.RedCity;
import com.cz.mts.system.entity.Snatch;
import com.cz.mts.system.entity.UserCard;
import com.cz.mts.system.entity.UserMedal;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.IAttentionService;
import com.cz.mts.system.service.ICardService;
import com.cz.mts.system.service.ICategoryService;
import com.cz.mts.system.service.ICityService;
import com.cz.mts.system.service.IMedalService;
import com.cz.mts.system.service.IMoneyDetailService;
import com.cz.mts.system.service.IOperService;
import com.cz.mts.system.service.IPosterPackageService;
import com.cz.mts.system.service.IRedCityService;
import com.cz.mts.system.service.IUserCardService;
import com.cz.mts.system.service.IUserMedalService;
import com.cz.mts.system.service.NotificationService;


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
	@Resource
	private IUserMedalService userMedalService;
	@Resource
	private IMedalService medalService;
	@Resource
	private IUserCardService userCardService;
	@Resource
	private NotificationService notificationService;
	
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
	@Resource
	private IOperService operService;
	@Resource
	private ICardService cardService;
	@Autowired  
	HttpSession session;
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
	ReturnDatas listjson(HttpServletRequest request, Model model,PosterPackage posterPackage,Integer personType) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		String selectType = request.getParameter("selectType");//1最新发布 2预约最多 3卡券最多
		String appUserId = request.getParameter("appUserId");
		String type = request.getParameter("type");//
		
		//更新user表的海报红包的最后浏览时间
		if(StringUtils.isNotBlank(appUserId)){
			AppUser appUser = appUserService.findAppUserById(Integer.parseInt(appUserId));
			if(appUser!=null){
				appUser.setPosterScanTime(new Date());
				appUserService.update(appUser, true);
			}
		}
		
		// ==执行分页查询
//			Finder finder1=Finder.getSelectFinder(PosterPackage.class, "p.command,p.userId,p.id,p.title,u.header as userHeader ,p.encrypt,p.balance,u.name as userName,p.image,p.lookNum,p.status,p.failReason  ").append(" p LEFT JOIN t_app_user u ON p.userId = u.id WHERE  p.isDel = 0");
			Finder finder1 = new Finder("SELECT a.*,c.num FROM(SELECT p.command,p.cardId,p.userId,p.categoryId,p.appointCount,p.id,p.title,u.header as userHeader ,p.encrypt,p.balance,u.name as userName,p.image,"
					+ "p.lookNum,p.status,p.failReason,p.isValid,p.createTime FROM t_poster_package p LEFT JOIN t_app_user u ON p.userId = u.id WHERE  p.isDel = 0)a LEFT JOIN t_card c "
					+ "ON a.cardId=c.id where 1=1");
			
			if(StringUtils.isNotBlank(posterPackage.getTitle())){
				finder1.append(" and (a.userId IN (SELECT id FROM t_app_user WHERE INSTR(`name`,:title)>0 ) OR INSTR(a.title,:title)>0 )"); 
				finder1.setParam("title", posterPackage.getTitle());
			}
			
			
			if(posterPackage.getUserId()!=null){
				
				finder1.append(" and a.userId = :userId");
				
				finder1.setParam("userId", posterPackage.getUserId());
				
			}
			
			//1首页（通过和抢完三天内的。排序：已抢完的默认排到最下边，最新发布的在最上边） 2个人主页（通过和已抢完的所有） 3我的发布（传什么状态查什么状态）
			if(StringUtils.isNotBlank(type)){
				switch (type) {
				case "1":
					finder1.append(" and (a.status = 3 or a.status = 4 ) and a.isValid != 1");
					break;
				case "2":
					finder1.append(" and (a.status = 3 or a.status = 4 )");
					break;
				case "3":
					finder1.append(" and a.status = :status");
					finder1.setParam("status", posterPackage.getStatus());
					break;
				}
			}
			
			if(posterPackage.getCategoryId()!=null){
				
				finder1.append(" and a.categoryId = :categoryId");
				
				finder1.setParam("categoryId", posterPackage.getCategoryId());
				
			}
			//如果cityId不等于空
			if(null != posterPackage.getCityId()){
				finder1.append(" and a.id in( SELECT packageId FROM t_red_city WHERE (cityId=:cityId or cityId=0) and type=1 group by packageId)");
				finder1.setParam("cityId", posterPackage.getCityId());
			}else{
				finder1.append(" and a.id in( SELECT packageId FROM t_red_city WHERE type=1 group by packageId)");
			}
			
			//筛选
			if(StringUtils.isNotBlank(selectType)){
				switch (selectType) {//1最新发布 2预约最多 3卡券最多，如果筛选金额最多的话，客户端不传该字段
				case "1":
					finder1.append(" order by a.createTime desc");
					break;
				case "2":
					finder1.append(" order by a.appointCount IS NULL,a.appointCount desc,a.balance desc");
					break;
				case "3":
					finder1.append(" order by c.num IS NULL,c.num DESC,a.balance desc");
					break;
				}
			}else{
				finder1.append(" order by a.status asc,a.balance desc,a.createTime desc");
			}
			
			List<Map<String, Object>> list = posterPackageService.queryForList(finder1,page);
		
			if(null != list && list.size() > 0){
				for (Map<String, Object> map : list) {
					 //返回城市名称
					 Finder finder = new Finder("SELECT * FROM t_red_city WHERE packageId=:id AND type=1");
					 finder.setParam("id", Integer.parseInt(map.get("id").toString()));
					 List<RedCity> redCities = redCityService.queryForList(finder,RedCity.class);
					 if(null != redCities && redCities.size() > 0){
						 for (RedCity redCity : redCities) {
							if(null != redCity.getCityId()){
								City city = cityService.findCityById(redCity.getCityId());
								if(city!=null){
									redCity.setCityName(city.getName());
								}
							}
						}
						 map.put("redCities", redCities);
					 }
					 
					 //返回分类名称
					 if(map.get("categoryId") != null){
						 Category category = categoryService.findCategoryById(Integer.parseInt(map.get("categoryId").toString()));
						 if(category != null){
							 if(StringUtils.isNotBlank(category.getName())){
								 map.put("categoryName", category.getName());
							 }
						 }
					 }
					 
					 
					 //是否点赞过
					 if(StringUtils.isNotBlank(appUserId)){
						 Oper oper = new Oper();
						 oper.setUserId(Integer.parseInt(appUserId));
						 oper.setItemId(Integer.parseInt(map.get("id").toString()));
						 oper.setType(1);
						 Page operPage = newPage(request);
						 List<Oper> opers = operService.findListDataByFinder(null, operPage, Oper.class, oper);
						 if(null != opers && opers.size() > 0){
							 map.put("isTop", 1);
						 }else{
							 map.put("isTop", 0);
						 }
					 }
					
					 
				}
			}
			returnObject.setData(list);
		
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
		
		if(StringUtils.isNotBlank(appUserId)||session.getAttribute("appUserSessionId")!=null){
			  if(StringUtils.isNotBlank(appUserId)){
				  appUserId=appUserId;
			  }else if (session.getAttribute("appUserSessionId")!=null) {
				  appUserId = session.getAttribute("appUserSessionId").toString();
			}
		}
		
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
			 PosterPackage posterPackage = posterPackageService.findPosterPackageById(id);
			 
			 if(null != posterPackage && 3 == posterPackage.getStatus()){
				 if(posterPackage.getLookNum()==null){
					 posterPackage.setLookNum(0);
				 }
				 posterPackage.setLookNum(posterPackage.getLookNum()+1);
				 
				 //如果浏览次数超过100，发推送
				 if(posterPackage.getLookNum() % 100 == 0 && posterPackage.getLookNum() / 100 > 0){
					 notificationService.notify(25, posterPackage.getId(), posterPackage.getUserId(),posterPackage.getLookNum()+"");
				 }
				 
				 
			 }
			 
			 Page newPage = new Page();
			 newPage.setPageSize(1000000);
			 //查询评论个数
			 Oper commentOper = new Oper();
			 commentOper.setType(2);
			 commentOper.setItemId(id);
			 List<Oper> commentNums = operService.findListDataByFinder(null, newPage, Oper.class, commentOper);
			 if(null != commentNums && commentNums.size() > 0){
				 posterPackage.setCommentCount(commentNums.size());
			 }
			 
			 //查询点赞个数
			 Oper topOper = new Oper();
			 topOper.setType(1);
			 topOper.setItemId(id);
			 List<Oper> topNums = operService.findListDataByFinder(null, newPage, Oper.class, topOper);
			 if(null != topNums && topNums.size() > 0){
				 posterPackage.setTopCount(topNums.size());
			 }
			 posterPackageService.update(posterPackage,true);
			 
			 //查询发红包的用户
			 if(posterPackage!=null&&posterPackage.getUserId()!=null){
				 AppUser appUser=appUserService.findAppUserById(posterPackage.getUserId());
				 if(appUser!=null){
					 posterPackage.setAppUser(appUser);
				 }
			
				 if(null != appUser.getUserMedals()){
					 posterPackage.setUserMedals(appUser.getUserMedals());
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
			 
			 
			 
			 //是否关注以及是否点赞过
			 if(posterPackage!=null&&StringUtils.isNotBlank(appUserId)){
				 //是否关注过
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
				 
				 //是否点赞过
				 Oper oper = new Oper();
				 oper.setUserId(Integer.parseInt(appUserId));
				 oper.setItemId(posterPackage.getId());
				 oper.setType(1);
				 Page operPage = newPage(request);
				 List<Oper> opers = operService.findListDataByFinder(null, operPage, Oper.class, oper);
				 if(null != opers && opers.size() > 0){
					 posterPackage.setIsTop(1);
				 }else{
					 posterPackage.setIsTop(0);
				 }
				 
			 }
			 
			 
			 if(null != posterPackage && null != posterPackage.getCardId()){
				 Card card = cardService.findCardById(posterPackage.getCardId());
				 if(null != card && StringUtils.isNotBlank(card.getTitle())){
					 posterPackage.setCardTitle(card.getTitle());
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
			 
			 //返回卡券分类名称以及卡券分类图片
			 if(null != posterPackage && null != posterPackage.getCardCategoryId()){
				 Category category = categoryService.findCategoryById(posterPackage.getCardCategoryId());
				 if(null != category){
					 if(StringUtils.isNotBlank(category.getName())){
						 posterPackage.setCardCategoryName(category.getName());
					 }
					 if(StringUtils.isNotBlank(category.getImage())){
						 posterPackage.setCardCategoryImage(category.getImage());
					 }
				 }
			 }
			 
			 
			 //返回卡券已领取数量
			 if(null != posterPackage && null != posterPackage.getCardId()){
				 Integer lqNum = 0;
//				 if(StringUtils.isNotBlank(appUserId)){
					 //返回该用户已领取的数量
					 Finder userCardFinder = new Finder("SELECT COUNT(id) as lqNum FROM t_user_card WHERE cardId=:cardId AND `status`!=0");
					 userCardFinder.setParam("cardId", posterPackage.getCardId());
					 List<UserCard> userCards = userCardService.queryForList(userCardFinder, UserCard.class);
					 if(null != userCards && userCards.size() > 0){
						 UserCard userCard = userCards.get(0);
						 if(null != userCard && null != userCard.getLqNum() ){
							 lqNum = userCards.get(0).getLqNum();
						 }
					 }
//				 }
				 posterPackage.setCardLqNum(lqNum);
			 }
			 
			 
			 
			 
			String cityIds= ""; 
			 //返回城市名称
			 Finder finder = new Finder("SELECT * FROM t_red_city WHERE packageId=:id AND type=1");
			 finder.setParam("id", Integer.parseInt(strId));
			 List<RedCity> redCities = redCityService.queryForList(finder,RedCity.class);
			 if(null != redCities && redCities.size() > 0){
				 for (RedCity redCity : redCities) {
					 if(null != redCity.getCityId() && 0 != redCity.getCityId()){
						 cityIds += redCity.getCityId()+",";
					 }
					if(null != redCity.getCityId()){
						City city = cityService.findCityById(redCity.getCityId());
						if(null != city && StringUtils.isNotBlank(city.getName())){
							redCity.setCityName(city.getName());
						}
					}
				}
				 posterPackage.setCityIds(cityIds);
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
			if(posterPackage.getId() == null){
				posterPackageService.saveorupdate(posterPackage);
			}else{
				//cityIds是否为空
				if(StringUtils.isNotBlank(posterPackage.getCityIds())){
					String cityIds[] = posterPackage.getCityIds().split(",");
					if(null != cityIds && cityIds.length > 0){
						//删除红包表中的记录
						Finder finder = new Finder("DELETE FROM t_red_city WHERE type=1 and packageId=:packageId");
						finder.setParam("packageId", posterPackage.getId());
						redCityService.update(finder);
						for (String cid : cityIds) {
							Integer cityId = Integer.parseInt(cid);
							//更新redCity表
							RedCity redCity = new RedCity();
							redCity.setCityId(cityId);
							redCity.setPackageId(posterPackage.getId());
							redCity.setType(1);
							redCityService.save(redCity);
						}
					}
					
				}else{
					//删除红包表中的记录
					Finder finder = new Finder("DELETE FROM t_red_city WHERE type=1 and packageId=:packageId");
					finder.setParam("packageId", posterPackage.getId());
					redCityService.update(finder);
					//更新redCity表
					RedCity redCity = new RedCity();
					redCity.setCityId(0);
					redCity.setPackageId(posterPackage.getId());
					redCity.setType(1);
					redCityService.save(redCity);
				}
				if(0 == posterPackage.getIsRelevance()){
					posterPackage.setCardId(0);
				}
				posterPackageService.update(posterPackage,true);
			}
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
		return "/posterpackage/posterpackageCru";
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
	@SecurityApi
	public @ResponseBody 
	ReturnDatas snatchjson(HttpServletRequest request, Model model,String id,String userId,String osType,String command){
		
		ReturnDatas result =  new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.UPDATE_SUCCESS);
		
		if(StringUtils.isBlank(id) || StringUtils.isBlank(userId)) {
			return new ReturnDatas(ReturnDatas.ERROR, "参数缺失!") ;
		}else {
			try {
				String data =  (String) posterPackageService.snatch(userId, id,osType,command) ;
				if(data.indexOf("{") == -1 ){  //说明是个错误结果
					result.setMessage(data);
					result.setStatus(ReturnDatas.ERROR);
				}else {
					LposterPackage lpp = JsonUtils.readValue(data, LposterPackage.class) ;
					result.setData(lpp);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.setStatus(ReturnDatas.ERROR);
				result.setMessage("系统异常");
			}
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
				if(posterPackage.getUserId()==null||posterPackage.getCategoryId()==null||posterPackage.getLqNum()==null){
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
				
				
				posterPackage.setNum(posterPackage.getLqNum());
				
				posterPackage.setStatus(0);
				
				posterPackage.setIsDel(0);
				
				posterPackage.setCreateTime(new Date());
				
				posterPackage.setShareNum(0);
				
				posterPackage.setBalance(posterPackage.getSumMoney());
				
				posterPackage.setIsValid(0);
				
				//生成验证码
				Long code=new Date().getTime();
				posterPackage.setCode("P"+code);
				
				
				//保证每人最少领取0.01
				if(posterPackage.getSumMoney()/posterPackage.getLqNum() >= 0.01){

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
						
					}else {
						RedCity redCity=new RedCity();
						redCity.setCityId(0);
						redCity.setPackageId(Integer.parseInt(id.toString()));
						redCity.setType(1);
						redCityService.save(redCity);
					}
					
					
					returnObject.setData(posterPackageService.findPosterPackageById(id));
				}else{
					returnObject.setMessage("每个人的领取金额最少是0.01元");
					returnObject.setStatus(ReturnDatas.ERROR);
					return returnObject;
				}
				
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
				
				posterPackage.setPayMoney(0.0);
				
				posterPackage.setNum(posterPackage.getLqNum());
				
				posterPackage.setStatus(0);
				
				posterPackage.setBalance(posterPackage.getSumMoney());
				
				posterPackage.setPayType(null);
				
				posterPackage.setPayTime(null);
				
				posterPackage.setWxCode(null);
				
				posterPackage.setTradeNo(null);
				
				
				//保证没人最少领取0.01
				if(posterPackage.getSumMoney()/posterPackage.getLqNum() >= 0.01){
					id=posterPackageService.update(posterPackage, true);
					
					RedCity redCitySelect = new RedCity();
					
					redCitySelect.setPackageId(posterPackage.getId());
					redCitySelect.setType(1);
					
					// ==构造分页请求
					Page page = newPage(request);
					// ==执行分页查询
					List<RedCity> datas=redCityService.findListDataByFinder(null,page,RedCity.class,redCitySelect);
					
					for (RedCity redCity : datas) {
						
						redCityService.deleteByEntity(redCity);
						
					}
					
					
					if(cityIds!=null){
						
						String[] cityId=cityIds.split(",");
						
						for (String string : cityId) {
							RedCity redCity=new RedCity();
							redCity.setCityId(Integer.parseInt(string));
							redCity.setPackageId(posterPackage.getId());
							redCity.setType(1);
							redCityService.save(redCity);
						}
						
					}else {
						RedCity redCity=new RedCity();
						redCity.setCityId(0);
						redCity.setPackageId(posterPackage.getId());
						redCity.setType(1);
						redCityService.save(redCity);
					}
					
					
					returnObject.setData(posterPackageService.findPosterPackageById(posterPackage.getId()));
				}else{
					returnObject.setMessage("每个人的领取金额最少是0.01元");
					returnObject.setStatus(ReturnDatas.ERROR);
					return returnObject;
				}
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
		Finder finder = Finder.getSelectFinder(PosterPackage.class).append(" where 1=1");
		if(StringUtils.isNotBlank(posterPackage.getCategoryName())){
			finder.append(" and categoryId in(select id from t_category where type=1 and INSTR(`name`,:categoryName)>0 )");
			finder.setParam("categoryName", posterPackage.getCategoryName());
		}
		if(StringUtils.isNotBlank(posterPackage.getUserName())){
			finder.append(" and userId in(select id from t_app_user where INSTR(`name`,:userName)>0 )");
			finder.setParam("userName", posterPackage.getUserName());
		}
		
		if(StringUtils.isNotBlank(posterPackage.getStartTime())){
			finder.append(" and payTime > :startTime ");
			finder.setParam("startTime", posterPackage.getStartTime());
		}
		
		if(StringUtils.isNotBlank(posterPackage.getEnddTime())){
			finder.append(" and payTime < :endTime ");
			finder.setParam("endTime", posterPackage.getEnddTime());
		}
		if(StringUtils.isNotBlank(posterPackage.getCityIds())){
			finder.append(" and id in( SELECT DISTINCT(packageId) FROM t_red_city WHERE cityId=:cityId and type=1) ");
			finder.setParam("cityId", Integer.parseInt(posterPackage.getCityIds()));
		}
		if(StringUtils.isNotBlank(posterPackage.getTitle())){
			finder.append(" and title like '%"+posterPackage.getTitle()+"%'");
		}
		finder.append(" and status!=0");
		
		Double sumPayMoney = 0.0;
		Double sumBalance = 0.0;
		List<PosterPackage> datas = posterPackageService.findListDataByFinder(finder,page,PosterPackage.class,posterPackage);
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
				if(null != posterPackage.getCardId() && 0 != posterPackage.getCardId()){
					Card card = cardService.findCardById(posterPackage.getCardId());
					if(null != card && StringUtils.isNotBlank(card.getTitle())){
						pc.setCardTitle(card.getTitle());
					}
				}
				
			}
		}
		
		Page pageNew = new Page();
		pageNew.setPageSize(10000);
		List<PosterPackage> posterPackages = posterPackageService.findListDataByFinder(finder,pageNew,PosterPackage.class,posterPackage);
		if(posterPackages != null && posterPackages.size() > 0){
			for (PosterPackage pps : posterPackages) {
				if(null == pps.getSumMoney()){
					pps.setSumMoney(0.0);
				}
				sumPayMoney += pps.getSumMoney();
				
				if(null == pps.getBalance()){
					pps.setBalance(0.0);
				}
				sumBalance += pps.getBalance();
			}
		}
		
		Double sumOverMoney = new BigDecimal(sumPayMoney).subtract(new BigDecimal(sumBalance)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		HashMap<String, Object> map=new HashMap<String,Object>();  
		map.put("sumPayMoney", new BigDecimal(sumPayMoney).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		map.put("sumBalance", new BigDecimal(sumBalance).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		map.put("sumOverMoney", sumOverMoney);
		returnObject.setMap(map);
		returnObject.setQueryBean(posterPackage);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject; 
	}
	
	/**
	 * 审核红包
	 * @param request
	 * @param id 红包id
	 * @param type 审核类型 1通过 0拒绝
	 * @param failReason 失败原因
	 * @return
	 * @author wxy
	 * @date 2017年3月16日
	 */
	@RequestMapping("/check/json")
	public @ResponseBody
	ReturnDatas checkJson(HttpServletRequest request, String id,String type,String failReason){
		if(StringUtils.isBlank(id) || StringUtils.isBlank(type)){
			return new ReturnDatas(ReturnDatas.ERROR, "参数缺失") ;
		}else {
			try {
				Object object = posterPackageService.check(id, type, failReason) ;
				if(object == null){
					return new ReturnDatas(ReturnDatas.ERROR, "红包不存在") ;
				}else {
					return new ReturnDatas(ReturnDatas.SUCCESS, object.toString()) ;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ReturnDatas(ReturnDatas.ERROR, "系统异常") ;
			}
		}
	}

	
	
	/**
	 * 
	 * 后台统计接口
	 * @author wj
	 * @param request
	 * @param model
	 * @param mediaPackage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/tongji/json")
	public @ResponseBody
	ReturnDatas tongjijson(HttpServletRequest request, Model model,MediaPackage mediaPackage) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Finder finder=new Finder("SELECT SUM(payMoney) FROM t_media_package ;");

		Double sum=posterPackageService.queryForObject(finder, Double.class);
		
		if(sum==null){
			
			sum=0.0;
			
		}
		
		returnObject.setData(sum);
		
		return returnObject;
	}
	
	/**
	 * 
	 * 后台统计接口
	 * @author wj
	 * @param request
	 * @param model
	 * @param mediaPackage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/balance/json")
	public @ResponseBody
	ReturnDatas balancejson(HttpServletRequest request, Model model,MediaPackage mediaPackage) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Finder finder=new Finder("SELECT SUM(balance) FROM t_media_package ;");
		
		Double sum=posterPackageService.queryForObject(finder, Double.class);
		
		
		if(sum==null){
			sum=0.0;
		}
		
		returnObject.setData(sum);
		
		return returnObject;
	}
	
	
	/**
	 * 删除海报红包
	 * @author wj
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteadmin")
	public @ResponseBody ReturnDatas deleteadmin(HttpServletRequest request) throws Exception {

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
	
	
	
	
}
