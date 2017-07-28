package  com.cz.mts.system.web;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.Map;

import javax.annotation.Resource;
import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Appoint;
import com.cz.mts.system.entity.Card;
import com.cz.mts.system.entity.MediaPackage;
import com.cz.mts.system.entity.MoneyDetail;
import com.cz.mts.system.entity.PosterPackage;
import com.cz.mts.system.exception.ParameterErrorException;
import com.cz.mts.system.entity.SysSysparam;
import com.cz.mts.system.entity.UserCard;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.IAppointService;
import com.cz.mts.system.service.ICardService;
import com.cz.mts.system.service.IMediaPackageService;
import com.cz.mts.system.service.IMoneyDetailService;
import com.cz.mts.system.service.IPosterPackageService;
import com.cz.mts.system.service.IRedCityService;
import com.cz.mts.system.service.ISysSysparamService;
import com.cz.mts.system.service.NotificationService;
import com.cz.mts.system.service.impl.PosterPackageServiceImpl;
import com.cz.mts.frame.annotation.SecurityApi;
import com.cz.mts.frame.controller.BaseController;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.MessageUtils;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-07-06 14:42:38
 * @see com.cz.mts.system.web.Appoint
 */
@Controller
@RequestMapping(value="/system/appoint")
public class AppointController  extends BaseController {
	@Resource
	private IAppointService appointService;
	@Resource
	private IRedCityService redCityService;
	@Resource
	private IPosterPackageService posterPackageService;
	@Resource
	private IMediaPackageService mediaPackageService;
	@Resource
	private IAppUserService appUserService;
	@Resource
	private ICardService cardService;
	@Resource
	private ISysSysparamService sysparamService;
	@Resource
	private IMoneyDetailService moneyDetailService;
	@Resource
	private NotificationService notificationService;
	
	
	private String listurl="/appoint/appointList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param appoint
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,Appoint appoint) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, appoint);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * 我的预约列表
	 * @author wml
	 * @param request
	 * @param model
	 * @param appoint
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,Appoint appoint) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		
		Finder finder=Finder.getSelectFinder(Appoint.class).append(" where 1=1 and status!=0");
		
		// ==执行分页查询
		List<Appoint> datas=appointService.findListDataByFinder(finder,page,Appoint.class,appoint);
		if(null != datas && datas.size()> 0){
			
			for (Appoint ap : datas) {
				if(null != ap.getPackageUserId()){
					AppUser appUser = appUserService.findAppUserById(ap.getPackageUserId());
					if(null != appUser){
						ap.setAppUser(appUser);
					}
				}
				
				
				//1给海报红包加一个预约数量   2给视频红包加一个预约数量
				switch (ap.getType()) {
				case 1:
					
					PosterPackage posterPackageApp=posterPackageService.findById(ap.getItemId(), PosterPackage.class);
					
					//查询海报红包
					if(posterPackageApp!=null){
						ap.setTitle(posterPackageApp.getTitle());
					}
					
					break;
				case 2:
					
					MediaPackage mediaPackageApp=mediaPackageService.findById(ap.getItemId(), MediaPackage.class);
					
					if(mediaPackageApp!=null){
						ap.setTitle(mediaPackageApp.getTitle());
					}
					
					break;
				}
				
				
				
				
			}
		}
		
		returnObject.setQueryBean(appoint);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,Appoint appoint) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = appointService.findDataExportExcel(null,listurl, page,Appoint.class,appoint);
		String fileName="appoint"+GlobalStatic.excelext;
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
		return "/system/appoint/appointLook";
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
		  Appoint appoint = appointService.findAppointById(id);
		   returnObject.setData(appoint);
		}else{
		returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;
		
	}
	
	
	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/update/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas saveorupdate(Model model,Appoint appoint,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
			Object id = appointService.saveorupdate(appoint);
			
			appoint = appointService.findAppointById(id);
			
		} catch (ParameterErrorException e) {
			logger.error("参数缺失");
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数缺失");
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		returnObject.setData(appoint);
		return returnObject;
	
	}
	
	/**
	 * 进入修改页面,APP端可以调用 lookjson 获取json格式数据
	 */
	@RequestMapping(value = "/update/pre")
	public String updatepre(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/system/appoint/appointCru";
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
				appointService.deleteById(id,Appoint.class);
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
			appointService.deleteByIds(ids,Appoint.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
	}
	
	
	/**
	 * 预约列表
	 * @author wj
	 * @param request
	 * @param model
	 * @param appoint
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/appointList/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas appointListjson(HttpServletRequest request, Model model,Appoint appoint) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		if(null == appoint.getType() || null == appoint.getItemId()){
			returnObject.setMessage("参数缺失");
			returnObject.setStatus(ReturnDatas.ERROR);
		}else{
			// ==执行分页查询
			Finder finder = Finder.getSelectFinder(Appoint.class).append(" where 1=1 and status != 0 and type=:type and itemId=:itemId");
			finder.setParam("type", appoint.getType());
			finder.setParam("itemId", appoint.getItemId());
			List<Appoint> datas=appointService.findListDataByFinder(finder,page,Appoint.class,null);
			if(null != datas && datas.size() > 0){
				for (Appoint ap : datas) {
					if(null != ap.getUserId()){
						AppUser appUser = appUserService.findAppUserById(ap.getUserId());
						if(null != appUser){
							ap.setAppUser(appUser);
						}
					}
				}
			}
			returnObject.setData(datas);
		}
		
		
		returnObject.setQueryBean(appoint);
		returnObject.setPage(page);
		
		return returnObject;
	}
	
	
	
	
	/**
	 * 预约统计列表
	 * @author wj
	 * @param request
	 * @param model
	 * @param appoint
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/appointStatics/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas appointStaticjson(HttpServletRequest request, Model model,Appoint appoint) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		if(null == appoint.getType() || null == appoint.getItemId()){
			returnObject.setMessage("参数缺失");
			returnObject.setStatus(ReturnDatas.ERROR);
		}else{
			Finder finder = Finder.getSelectFinder(Appoint.class).append(" where 1=1 and status != 0 and type=:type and itemId=:itemId");
			finder.setParam("type", appoint.getType());
			finder.setParam("itemId", appoint.getItemId());
			//查询预约次数以及预约总金额
			Integer appointCount = 0;
			Double appintMoney = 0.0;
			Page newPage = new Page();
			newPage.setPageSize(100000);
			List<Appoint> allAppoints = appointService.findListDataByFinder(finder,newPage,Appoint.class,null);
			if(null != allAppoints && allAppoints.size() > 0){
				appointCount = allAppoints.size();
				for (Appoint app : allAppoints) {
					if(null != app.getMoney()){
						appintMoney += app.getMoney();
					}
				}
			}else{
				appointCount = 0;
				appintMoney = 0.0;
			}
			
			Integer appoinPerson = 0;
			//预约总人数
			Finder sumFinder = new Finder("SELECT * FROM t_appoint WHERE type=:type and itemId=:itemId  and status != 0 GROUP BY userId");
			sumFinder.setParam("type", appoint.getType());
			sumFinder.setParam("itemId", appoint.getItemId());
			List appList = appointService.queryForList(sumFinder);
			if(null != appList && appList.size() > 0){
				appoinPerson = appList.size();
			}else{
				appoinPerson = 0;
			}
			
			
			
			Map<String, Object> map = new HashMap<>();
			map.put("appointCount", appointCount);
			map.put("appintMoney", new BigDecimal(appintMoney).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
			map.put("appoinPerson", appoinPerson);
			returnObject.setData(map);
		}
		
		returnObject.setQueryBean(appoint);
		returnObject.setPage(page);
		
		return returnObject;
	}
	
	
	
	/**
	 * 预约卡券兑换接口
	 * @author wj
	 * 
	 */
	@RequestMapping("/changeCardjson/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas changeCardjson(Model model,HttpServletRequest request,HttpServletResponse response,Appoint appoint,Integer userId) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try { 
			
			if(appoint.getCardCode()==null){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("参数缺失");
				return returnObject;
			}
			
			Page page=newPage(request);
			
			page.setPageSize(1);
			
			Finder finder=Finder.getSelectFinder(Appoint.class).append(" where 1=1 ");

			//接口传过来一个userId但是查的时候并不需要userid  只要一个code  所以把这个userid置为null
			appoint.setUserId(null);
			
			//查出来的卡券
			List<Appoint> appoints=appointService.findListDataByFinder(finder, page, Appoint.class, appoint);
			
			if(appoints.size()==0){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("此预约不存在");
				return returnObject;
			}
			
			Appoint appo = appoints.get(0);
			
			//查询卡券信息
//			Card card=null;
			PosterPackage posterPackage = new PosterPackage();
			MediaPackage mediaPackage = new MediaPackage();
			
			//判断是否兑换
			if(appo.getStatus() !=null && appo.getStatus() != 1){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("此预约已兑换");
				return returnObject;
			}
			
			if(null != appo.getItemId()){
				if(null != appo.getType() && 1 == appo.getType()){
					posterPackage = posterPackageService.findPosterPackageById(appo.getItemId());
//					if(null != posterPackage && null != posterPackage.getCardId()){
//						card=cardService.findCardById(posterPackage.getCardId());
//					}
				}
				if(null != appo.getType() && 2 == appo.getType()){
					mediaPackage = mediaPackageService.findMediaPackageById(appo.getItemId());
//					if(null != mediaPackage && null != mediaPackage.getCardId()){
//						card=cardService.findCardById(mediaPackage.getCardId());
//					}
				}
				//判断预约的状态
				if(null != appo.getStatus() && 2 == appo.getStatus() ){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("该预约已兑换，暂不能兑换");
					return returnObject;
				}
				if(userId.intValue()!=appo.getPackageUserId().intValue()){
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("只能兑换自己发布的预约");
					return returnObject;
				}
			}
			
			//改变状态
			appo.setStatus(2);
			appo.setChangeTime(new Date());
			
			if(null != appo.getType() && 1 == appo.getType()){
				if(null != posterPackage && null != posterPackage.getUserId()){
					AppUser user = appUserService.findAppUserById(posterPackage.getUserId());
					if(null != user && 1 == user.getIsPush() && null != posterPackage.getId()){
						//给自己发推送
						notificationService.notify(42, posterPackage.getId(), user.getId());
						
					}
				}
			}
			
			if(null != appo.getType() && 2 == appo.getType()){
				if(null != mediaPackage && null != mediaPackage.getUserId()){
					AppUser user = appUserService.findAppUserById(mediaPackage.getUserId());
					if(null != user && 1 == user.getIsPush() && null != mediaPackage.getId()){
						//给自己发推送
						notificationService.notify(43, mediaPackage.getId(), user.getId());
						
					}
				}
			}
			
			appointService.update(appo,true);
			
			
			//手续费比例
			BigDecimal appointCharge = new BigDecimal(0.0);
			
			SysSysparam sysSysparam = sysparamService.findSysSysparamById("appointCharge");
			if(sysSysparam!=null){
				appointCharge=new BigDecimal(sysSysparam.getValue());
			}
			
			AppUser appUser=appUserService.findAppUserById(userId);
			if(appUser!=null){
				BigDecimal sumMoney=new BigDecimal(0.0);
				BigDecimal appointChargeMoney = new BigDecimal(0.0);
				//判断是否关闭预约手续费
				if(null == appUser.getIsAppointFee()){
					appUser.setIsAppointFee(0);
				}
				if(0 == appUser.getIsAppointFee()){
					//算出这次总共是多少钱的收益
					if(appo.getMoney()!=null){
						appointChargeMoney = new BigDecimal(appo.getMoney()).multiply(appointCharge);
						sumMoney = new BigDecimal(appo.getMoney()).subtract(appointChargeMoney);
					}
				}else{
					sumMoney = new BigDecimal(appo.getMoney());
				}
				if(appUser.getBalance()==null){
					appUser.setBalance(sumMoney.doubleValue());
				}else{
					appUser.setBalance(appUser.getBalance()+sumMoney.doubleValue());
				}
				
				appUserService.update(appUser,true);
				appointService.update(appo,true);
				//先保存收益记录
				MoneyDetail moneyDetail=new MoneyDetail();
				moneyDetail.setUserId(userId);
				moneyDetail.setCreateTime(new Date());
				moneyDetail.setType(15);
				moneyDetail.setMoney(+sumMoney.doubleValue());
				moneyDetail.setItemId(appo.getId());
				moneyDetail.setBalance(appUser.getBalance());
				moneyDetail.setPlateMoney(appointChargeMoney.doubleValue());
				moneyDetailService.save(moneyDetail);
				
			}
			
		} catch (Exception e) {
			String errorMessage = e.getLocalizedMessage();
			logger.error(errorMessage);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		
		return returnObject;
	
	}

}
