package  com.cz.mts.system.web;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cz.mts.frame.annotation.SecurityApi;
import com.cz.mts.frame.controller.BaseController;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.MessageUtils;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Attention;
import com.cz.mts.system.entity.Card;
import com.cz.mts.system.entity.Medal;
import com.cz.mts.system.entity.MediaPackage;
import com.cz.mts.system.entity.MoneyDetail;
import com.cz.mts.system.entity.PosterPackage;
import com.cz.mts.system.entity.UserCard;
import com.cz.mts.system.entity.UserMedal;
import com.cz.mts.system.entity.Withdraw;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.ICardService;
import com.cz.mts.system.service.IMedalService;
import com.cz.mts.system.service.IMediaPackageService;
import com.cz.mts.system.service.IMoneyDetailService;
import com.cz.mts.system.service.IPosterPackageService;
import com.cz.mts.system.service.IUserCardService;
import com.cz.mts.system.service.IUserMedalService;
import com.cz.mts.system.service.IWithdrawService;
import com.cz.mts.system.service.impl.CardServiceImpl;
import com.cz.mts.system.service.impl.UserCardServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:27
 * @see com.cz.mts.system.web.MoneyDetail
 */
@Controller
@RequestMapping(value="/system/moneydetail")
public class MoneyDetailController  extends BaseController {
	@Resource
	private IMoneyDetailService moneyDetailService;
	@Resource
	private IAppUserService appUserService;
	@Resource
	private IUserMedalService userMedalService;
	@Resource
	private IUserCardService userCardService;
	@Resource
	private IMedalService medalService;
	@Resource
	private IPosterPackageService posterPackageService;
	@Resource
	private IMediaPackageService mediaPackageService;
	@Resource
	private IWithdrawService withdrawService;
	@Resource
	private ICardService cardService;
	
	
	private String listurl="/moneydetail/moneydetailList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param moneyDetail
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list1")
	public String list1(HttpServletRequest request, Model model,MoneyDetail moneyDetail) 
			throws Exception {
		
		moneyDetail.setType(1);
		
		ReturnDatas returnObject = listadminjson(request, model, moneyDetail);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param moneyDetail
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list2")
	public String list2(HttpServletRequest request, Model model,MoneyDetail moneyDetail) 
			throws Exception {
		
		moneyDetail.setType(2);
		
		ReturnDatas returnObject = listadminjson(request, model, moneyDetail);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param moneyDetail
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list3")
	public String list3(HttpServletRequest request, Model model,MoneyDetail moneyDetail) 
			throws Exception {
		
		moneyDetail.setType(3);
		
		ReturnDatas returnObject = listadminjson(request, model, moneyDetail);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	
	/**
	 * 领取红包人列表
	 * @author wml
	 * @param request
	 * @param model
	 * @param moneyDetail
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listadmin/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas listadminjson(HttpServletRequest request, Model model,MoneyDetail moneyDetail) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		
		// ==构造分页请求
		Page page = newPage(request);
		Finder finder = Finder.getSelectFinder(MoneyDetail.class).append(" where 1=1 ");
		if(StringUtils.isNotBlank(moneyDetail.getUserName())){
			finder.append(" and userId in(select id from t_app_user where INSTR(`name`,:userName)>0 )");
			finder.setParam("userName", moneyDetail.getUserName());
		}
		
		if(StringUtils.isNotBlank(moneyDetail.getStartTime())){
			finder.append(" and createTime > :startTime ");
			finder.setParam("startTime", moneyDetail.getStartTime());
		}
		
		if(StringUtils.isNotBlank(moneyDetail.getEndTime())){
			finder.append(" and createTime < :endTime ");
			finder.setParam("endTime", moneyDetail.getEndTime());
		}
		
		// ==执行分页查询
		List<MoneyDetail> datas=moneyDetailService.findListDataByFinder(finder,page,MoneyDetail.class,moneyDetail);
		
		
		for (MoneyDetail moneyDetail2 : datas) {
			
			if(moneyDetail2.getType()==1){
				
				PosterPackage posterPackage=posterPackageService.findPosterPackageById(moneyDetail2.getItemId());
				
				if(posterPackage!=null){
					
					moneyDetail2.setItemName(posterPackage.getTitle());
					
				}
				
				
			}else if (moneyDetail2.getType()==2) {
				
				MediaPackage mediaPackage=mediaPackageService.findMediaPackageById(moneyDetail2.getItemId());
				
				if(mediaPackage!=null){
					moneyDetail2.setItemName(mediaPackage.getTitle());
				}
				
			}else if (moneyDetail2.getType()==3) {
				
				Card card=cardService.findCardById(moneyDetail2.getItemId());
				
				if(card!=null){
					
					moneyDetail2.setItemName(card.getTitle());
					
				}
				
			}
			
			
			if(moneyDetail2.getUserId()!=null){
				
				AppUser appUser=appUserService.findAppUserById(moneyDetail2.getUserId());
				
				if(appUser!=null){
					moneyDetail2.setUserName(appUser.getName());
				}
				
			}
			
		}
		
		
		
		returnObject.setQueryBean(moneyDetail);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
		
		
	}
	
	
	/**
	 * 领取红包人列表
	 * @author wj
	 * @param request
	 * @param model
	 * @param moneyDetail
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,MoneyDetail moneyDetail) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		returnObject = moneyDetailService.list(moneyDetail, page);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,MoneyDetail moneyDetail) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = moneyDetailService.findDataExportExcel(null,listurl, page,MoneyDetail.class,moneyDetail);
		String fileName="moneyDetail"+GlobalStatic.excelext;
		downFile(response, file, fileName,true);
		return;
	}
	
		/**
	 * 查看操作,调用APP端lookjson方法
	 */
	@RequestMapping(value = "/look")
	public String look(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception {
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/moneydetail/moneydetailLook";
	}

	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	public @ResponseBody
	ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
		  MoneyDetail moneyDetail = moneyDetailService.findMoneyDetailById(id);
		   returnObject.setData(moneyDetail);
		}else{
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
	ReturnDatas saveorupdate(Model model,MoneyDetail moneyDetail,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
		
			moneyDetailService.saveorupdate(moneyDetail);
			
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
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/moneydetail/moneydetailCru";
	}
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value="/delete")
	public @ResponseBody ReturnDatas delete(HttpServletRequest request) throws Exception {

			// 执行删除
		try {
		  String  strId=request.getParameter("id");
		  java.lang.Integer id=null;
		  if(StringUtils.isNotBlank(strId)){
			 id= java.lang.Integer.valueOf(strId.trim());
				moneyDetailService.deleteById(id,MoneyDetail.class);
				return new ReturnDatas(ReturnDatas.SUCCESS,
						MessageUtils.DELETE_SUCCESS);
			} else {
				return new ReturnDatas(ReturnDatas.WARNING,
						MessageUtils.DELETE_WARNING);
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
			moneyDetailService.deleteByIds(ids,MoneyDetail.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param moneyDetail
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/money/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas moneyjson(HttpServletRequest request, Model model,MoneyDetail moneyDetail) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		page.setPageSize(20);
		if(null != moneyDetail.getUserId()){
			List<MoneyDetail> datas=moneyDetailService.findListDataByFinder(null,page,MoneyDetail.class,moneyDetail);
			if(null != datas && datas.size() > 0){
				for (MoneyDetail md : datas) {
					AppUser appUser = appUserService.findAppUserById(md.getUserId());
					if(null != appUser){
						md.setAppUser(appUser);
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该用户不存在");
					}
					
					if(null != md.getItemId()){
						if(1 == md.getType()){
							//查询海报
							PosterPackage posterPackage = posterPackageService.findPosterPackageById(md.getItemId());
							if(posterPackage != null && StringUtils.isNotBlank(posterPackage.getTitle())){
								md.setContent(posterPackage.getTitle());
							}
						}
						
						if(2 == md.getType()){
							//查询视频
							MediaPackage mediaPackage = mediaPackageService.findMediaPackageById(md.getItemId());
							if(mediaPackage != null && StringUtils.isNotBlank(mediaPackage.getTitle())){
								md.setContent(mediaPackage.getTitle());
							}
						}
						
						
						if( 8 == md.getType()){
							
							UserCard userCard=userCardService.findUserCardById(md.getItemId());
							
							if(userCard!=null){
								
								Card card=cardService.findCardById(userCard.getCardId());
								
								if(card!=null){
									
									md.setContent(card.getTitle());
									
								}
								
							}
							
						}
						
						//提现失败
						if(9 == md.getType()){
							Withdraw withdraw = withdrawService.findWithdrawById(md.getItemId());
							if(withdraw != null && StringUtils.isNotBlank(withdraw.getReason())){
								md.setReason(withdraw.getReason());
							}
						}
					}
				}
			}
			returnObject.setQueryBean(moneyDetail);
			returnObject.setData(datas);
			returnObject.setPage(page);
		}else{
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数缺失");
		}
		return returnObject;
	}
	
	
	/**
	 * 获取关注人列表
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param attention
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listuser/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas listuserjson(HttpServletRequest request, Model model,MoneyDetail moneyDetail) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		if(moneyDetail.getItemId()!=null){
			Finder finder=new Finder("SELECT au.*,mon.`status` AS cardStatus FROM t_user_card mon LEFT JOIN t_app_user au ON au.id=mon.userId WHERE mon.cardId = :itemId AND mon.status!=0 GROUP BY au.id  order by mon.id");
			finder.setParam("itemId", moneyDetail.getItemId());
			
			
			List<AppUser> appUsers=appUserService.findListDataByFinder(finder, page, AppUser.class,null);
			
			for (AppUser appUser : appUsers) {
				
				Page pageM=new Page();
				pageM.setPageSize(1000);
				Finder finderM=new Finder("select * FROM t_medal WHERE id in (SELECT medalId FROM t_user_medal WHERE userId= :userId)");
				finderM.setParam("userId", appUser.getId());
				List<Medal> medals=medalService.findListDataByFinder(finderM, pageM, Medal.class, null);
				appUser.setMedals(medals);
				
			}
			
			returnObject.setData(appUsers);
			
			
		}else {
			returnObject.setMessage("参数缺失");
		}
		
		returnObject.setQueryBean(moneyDetail);
		returnObject.setPage(page);
		return returnObject;
	}
	
	/**
	 * 统计已领红包金额和已领总人数
	 * @author wj
	 * @param request
	 * @param model
	 * @param moneyDetail
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/statics/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas staticsjson(HttpServletRequest request,Model model,MoneyDetail moneyDetail) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Page page = newPage(request);
		returnObject = moneyDetailService.statics(moneyDetail, page);
		return returnObject;
	}
	
	
	/**
	 * 充值统计
	 * @author wj
	 * @param request
	 * @param model
	 * @param moneyDetail
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/recharge/statics")
	public @ResponseBody
	ReturnDatas rechargeStatics(HttpServletRequest request,Model model,MoneyDetail moneyDetail) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Page page = new Page();
		returnObject = moneyDetailService.rechargeStatics(moneyDetail, page);
		return returnObject;
	}
	
	@RequestMapping("/recharge/result")
	public String rechargeResult(HttpServletRequest request, Model model,MoneyDetail moneyDetail) 
			throws Exception {
		ReturnDatas returnObject = rechargeStatics(request, model, moneyDetail);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/moneydetail/moneydetailStatics";
	}
	
}
