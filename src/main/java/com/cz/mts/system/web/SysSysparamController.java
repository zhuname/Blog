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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cz.mts.system.entity.SysParamBean;
import com.cz.mts.system.entity.SysSysparam;
import com.cz.mts.system.service.ISysSysparamService;
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
 * @version  2017-02-24 15:17:29
 * @see com.cz.mts.system.web.SysSysparam
 */
@Controller
@RequestMapping(value="/system/syssysparam")
public class SysSysparamController  extends BaseController {
	@Resource
	private ISysSysparamService sysSysparamService;
	
	private String listurl="/syssysparam/syssysparamList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param sysSysparam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,SysSysparam sysSysparam) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, sysSysparam);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param sysSysparam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,SysSysparam sysSysparam) throws Exception{
		SysParamBean datas = sysSysparamService.findParamBean();
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,SysSysparam sysSysparam) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = sysSysparamService.findDataExportExcel(null,listurl, page,SysSysparam.class,sysSysparam);
		String fileName="sysSysparam"+GlobalStatic.excelext;
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
		return "/syssysparam/syssysparamLook";
	}

	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	public @ResponseBody
	ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		java.lang.String id=request.getParameter("code");
		if(StringUtils.isNotBlank(id)){
		  SysSysparam sysSysparam = sysSysparamService.findSysSysparamById(id);
		   returnObject.setData(sysSysparam);
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
	ReturnDatas saveorupdate(Model model,SysSysparam sysSysparam,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
			String code=sysSysparam.getCode();
			
			Finder finder=Finder.getSelectFinder(SysSysparam.class).append(" where 1=1 ");
			
			Page page=new Page();
			
			page.setPageSize(1);
			
			SysSysparam sysparamR = new SysSysparam();

			sysparamR.setCode(code);
			
			List<SysSysparam> sysSysparams=sysSysparamService.findListDataByFinder(finder, page, SysSysparam.class,sysparamR );
		
			if(sysSysparams.size()>0){
				
				sysSysparams.get(0).setValue(sysSysparam.getValue());
				
				sysSysparamService.update(sysSysparams.get(0), true);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
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
		return "/syssysparam/syssysparamCru";
	}
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value="/delete")
	public @ResponseBody ReturnDatas delete(HttpServletRequest request) throws Exception {

			// 执行删除
		try {
		java.lang.String id=request.getParameter("code");
		if(StringUtils.isNotBlank(id)){
				sysSysparamService.deleteById(id,SysSysparam.class);
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
			sysSysparamService.deleteByIds(ids,SysSysparam.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
	}
	
	/**
	 * 修改注册协议
	 * @author wj
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update/register")
	public String updateRegister(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception {
		
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Finder finder = new Finder("SELECT code,value FROM t_sys_sysparam WHERE `code`=:code");
		finder.setParam("code","userRegisterProtocol");
		List sysparams = sysSysparamService.queryForList(finder);
		returnObject.setData(sysparams.get(0));
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/syssysparam/updateRegister";
	}
	
	/**
	 * 修改app简介
	 * @author wj
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update/appIntroduce")
	public String updateAppIntroduce(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception {
		
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Finder finder = new Finder("SELECT code,value FROM t_sys_sysparam WHERE `code`=:code");
		finder.setParam("code","appIntroduce");
		List sysparams = sysSysparamService.queryForList(finder);
		returnObject.setData(sysparams.get(0));
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/syssysparam/updateIntroduce";
	}
	
	/**
	 * 修改加盟热线
	 * @author wj
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update/businessphone")
	public String updateBusinessPhone(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception {
		
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Finder finder = new Finder("SELECT code,value FROM t_sys_sysparam WHERE `code`=:code");
		finder.setParam("code","businessPhone");
		List sysparams = sysSysparamService.queryForList(finder);
		returnObject.setData(sysparams.get(0));
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/syssysparam/updateBusiness";
	}
	
	/**
	 * 修改客服热线
	 * @author wj
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update/kefuphone")
	public String updateKefuPhone(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception {
		
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Finder finder = new Finder("SELECT code,value FROM t_sys_sysparam WHERE `code`=:code");
		finder.setParam("code","kefuphone");
		List sysparams = sysSysparamService.queryForList(finder);
		returnObject.setData(sysparams.get(0));
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/syssysparam/updateKefu";
	}
	
	/**
	 * 修改卡券手续费
	 * @author wj
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update/cardCharge")
	public String updateCardCharge(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception {
		
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Finder finder = new Finder("SELECT code,value FROM t_sys_sysparam WHERE `code`=:code");
		finder.setParam("code","cardCharge");
		List sysparams = sysSysparamService.queryForList(finder);
		returnObject.setData(sysparams.get(0));
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/syssysparam/updateCardCharge";
	}
	
	/**
	 * 修改提现手续百分比
	 * @author wj
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update/chargePercent")
	public String updateChargePercent(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception {
		
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Finder finder = new Finder("SELECT code,value FROM t_sys_sysparam WHERE `code`=:code");
		finder.setParam("code","chargePercent");
		List sysparams = sysSysparamService.queryForList(finder);
		returnObject.setData(sysparams.get(0));
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/syssysparam/updateChargePercent";
	}
	
	/**
	 * 修改最低提现金额
	 * @author wj
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update/minimumCharge")
	public String updateMinimumCharge(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception {
		
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Finder finder = new Finder("SELECT code,value FROM t_sys_sysparam WHERE `code`=:code");
		finder.setParam("code","minimumCharge");
		List sysparams = sysSysparamService.queryForList(finder);
		returnObject.setData(sysparams.get(0));
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/syssysparam/updateMinimumCharge";
	}
	
	/**
	 * 超出多少元收手续费
	 * @author wj
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update/maxCharge")
	public String updateMaxCharge(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception {
		
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Finder finder = new Finder("SELECT code,value FROM t_sys_sysparam WHERE `code`=:code");
		finder.setParam("code","maxCharge");
		List sysparams = sysSysparamService.queryForList(finder);
		returnObject.setData(sysparams.get(0));
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/syssysparam/updateMaxCharge";
	}
	
	
	/**
	 * 修改提现说明
	 * @author wj
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update/withdrawIntroduce")
	public String updateWithdrawIntroduce(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception {
		
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Finder finder = new Finder("SELECT code,value FROM t_sys_sysparam WHERE `code`=:code");
		finder.setParam("code","withdrawIntroduce");
		List sysparams = sysSysparamService.queryForList(finder);
		returnObject.setData(sysparams.get(0));
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/syssysparam/updateWithdrawIntroduce";
	}
	

}
