package  com.cz.mts.system.web;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
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
import com.cz.mts.system.entity.Card;
import com.cz.mts.system.entity.MoneyDetail;
import com.cz.mts.system.entity.SysSysparam;
import com.cz.mts.system.entity.Withdraw;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.IMoneyDetailService;
import com.cz.mts.system.service.ISysSysparamService;
import com.cz.mts.system.service.IWithdrawService;
import com.cz.mts.frame.annotation.SecurityApi;
import com.cz.mts.frame.controller.BaseController;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.MessageUtils;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;
import com.mysql.jdbc.jdbc2.optional.SuspendableXAConnection;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:29
 * @see com.cz.mts.system.web.Withdraw
 */
@Controller
@RequestMapping(value="/system/withdraw")
public class WithdrawController  extends BaseController {
	@Resource
	private IWithdrawService withdrawService;
	@Resource
	private IAppUserService appUserService;
	@Resource
	private ISysSysparamService sysSysparamService;
	@Resource
	private IMoneyDetailService moneyDetailService;
	
	private String listurl="/withdraw/withdrawList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param withdraw
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,Withdraw withdraw) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, withdraw);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param withdraw
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,Withdraw withdraw) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		Finder finder = Finder.getSelectFinder(Withdraw.class).append(" where 1=1 ");
		if(StringUtils.isNotBlank(withdraw.getUserName())){
			finder.append(" and userId in(select id from t_app_user where INSTR(`name`,:userName)>0 )");
			finder.setParam("userName", withdraw.getUserName());
		}
		List<Withdraw> datas=withdrawService.findListDataByFinder(finder,page,Withdraw.class,withdraw);
		if(datas != null && datas.size() > 0){
			for (Withdraw wd : datas) {
				if(null != wd.getUserId()){
					AppUser appUser = appUserService.findAppUserById(wd.getUserId());
					if(null != appUser){
						if(StringUtils.isNotBlank(appUser.getName())){
							wd.setUserName(appUser.getName());
						}
					}
				}
			}
		}
		returnObject.setQueryBean(withdraw);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,Withdraw withdraw) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = withdrawService.findDataExportExcel(null,listurl, page,Withdraw.class,withdraw);
		String fileName="withdraw"+GlobalStatic.excelext;
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
		return "/withdraw/withdrawLook";
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
		  Withdraw withdraw = withdrawService.findWithdrawById(id);
		   returnObject.setData(withdraw);
		}else{
		returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;
		
	}
	
	
	/**
	 * 申请提现接口
	 * @author wj
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/update/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas saveorupdate(Model model,Withdraw withdraw,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
//		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			returnObject = withdrawService.applyWithdraw(withdraw);
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
		return "/withdraw/withdrawCru";
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
				withdrawService.deleteById(id,Withdraw.class);
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
			withdrawService.deleteByIds(ids,Withdraw.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}
	
	/**
	 * 提现申请被拒绝
	 * @author wj
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/check/refuse")
	public @ResponseBody
	ReturnDatas checkRefuse(HttpServletRequest request, Model model,Withdraw withdraw) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		BigDecimal balance,frozenMoney;
		String reason = request.getParameter("reason");
		String id = request.getParameter("id");
		withdraw = withdrawService.findWithdrawById(Integer.parseInt(id));
		if(null != withdraw){
			withdraw.setStatus(3);
			withdraw.setReason(reason);
			withdraw.setOperTime(new Date());
			withdrawService.update(withdraw,true);
			
			//更改appUser表中的信息
			if(null != withdraw.getUserId()){
				AppUser appUser = appUserService.findAppUserById(withdraw.getUserId());
				if(null != appUser){
					if(null == appUser.getBalance()){
						balance = new BigDecimal(0);
					}else{
						balance = new BigDecimal(appUser.getBalance());
					}
					
					if(null == appUser.getFrozeBanlance()){
						frozenMoney = new BigDecimal(0);
					}else{
						frozenMoney = new BigDecimal(appUser.getFrozeBanlance());
					}
					balance = balance.add(new BigDecimal(withdraw.getMoney()));
					frozenMoney = frozenMoney.subtract(new BigDecimal(withdraw.getMoney()));
					appUser.setBalance(balance.doubleValue());
					appUser.setFrozeBanlance(frozenMoney.doubleValue());
					appUserService.update(appUser,true);
					
					
					//更新moneyDetail表中的信息
					MoneyDetail moneyDetail = new MoneyDetail();
					moneyDetail.setUserId(withdraw.getUserId());
					moneyDetail.setCreateTime(new Date());
					moneyDetail.setType(9);
					moneyDetail.setMoney(withdraw.getMoney());
					moneyDetail.setBalance(appUser.getBalance());
					moneyDetail.setPayType(appUser.getWithdrawType());
					moneyDetail.setItemId(Integer.parseInt(id));
					moneyDetailService.save(moneyDetail);
				}
				
			}
		}
		
		return returnObject;
		
	}
	
	
	/**
	 * 审核通过
	 * @author wj
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/check/confirm")
	public @ResponseBody
	ReturnDatas checkConfirm(HttpServletRequest request,Model model,Withdraw withdraw) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String id = request.getParameter("id");
		withdraw = withdrawService.findWithdrawById(Integer.parseInt(id));
		if(null != withdraw){
			withdraw.setStatus(2);
			withdraw.setOperTime(new Date());
			withdrawService.update(withdraw,true);
			
			//更新appUser表中的信息
			if(null != withdraw.getUserId()){
				BigDecimal frozenMoney;
				AppUser appUser = appUserService.findAppUserById(withdraw.getUserId());
				if(null != appUser){
					if(null == appUser.getFrozeBanlance()){
						frozenMoney = new BigDecimal(0);
					}else{
						frozenMoney = new BigDecimal(appUser.getFrozeBanlance());
					}
					frozenMoney = frozenMoney.subtract(new BigDecimal(withdraw.getMoney()));
					appUser.setFrozeBanlance(frozenMoney.doubleValue());
					appUserService.update(appUser,true);
				}
				
				//查询该用户的申请中的状态
				Finder finder = new Finder("select * from t_money_detail where userId = :userId and type=10 and payType = :payType");
				finder.setParam("userId", withdraw.getUserId());
				finder.setParam("payType", withdraw.getWithdrawType());
				List<MoneyDetail> moneyDetails = moneyDetailService.queryForList(finder,MoneyDetail.class);
				if(moneyDetails != null && moneyDetails.size() > 0){
					MoneyDetail moneyDetail = moneyDetails.get(0);
					moneyDetail.setType(7);
					moneyDetailService.update(moneyDetail,true);
				}
			}
			
		}
		return returnObject;
	}

}
