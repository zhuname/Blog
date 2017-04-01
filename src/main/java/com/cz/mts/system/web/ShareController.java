package  com.cz.mts.system.web;

import java.io.File;
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
import com.cz.mts.system.entity.MediaPackage;
import com.cz.mts.system.entity.PosterPackage;
import com.cz.mts.system.entity.Share;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.ICardService;
import com.cz.mts.system.service.IMediaPackageService;
import com.cz.mts.system.service.IPosterPackageService;
import com.cz.mts.system.service.IShareService;
import com.cz.mts.frame.annotation.SecurityApi;
import com.cz.mts.frame.controller.BaseController;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.MessageUtils;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:28
 * @see com.cz.mts.system.web.Share
 */
@Controller
@RequestMapping(value="/system/share")
public class ShareController  extends BaseController {
	@Resource
	private IShareService shareService;
	@Resource
	private IAppUserService appUserService;
	@Resource
	private IPosterPackageService posterPackageService;
	@Resource
	private IMediaPackageService mediaPackageService;
	@Resource
	private ICardService cardService;
	
	private String listurl="/share/shareList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param share
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,Share share) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, share);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param share
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,Share share) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<Share> datas=shareService.findListDataByFinder(null,page,Share.class,share);
			returnObject.setQueryBean(share);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,Share share) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = shareService.findDataExportExcel(null,listurl, page,Share.class,share);
		String fileName="share"+GlobalStatic.excelext;
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
		return "/system/share/shareLook";
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
		  Share share = shareService.findShareById(id);
		   returnObject.setData(share);
		}else{
		returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;
		
	}
	
	
	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 分享接口
	 * 
	 */
	@RequestMapping("/update/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas saveorupdatejson(Model model,Share share,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if(null == share.getUserId() || null == share.getShareType() || null == share.getId() || null == share.getType()){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("参数缺失");
			}else{
				//查询appUser表中的信息
				AppUser appUser = appUserService.findAppUserById(share.getUserId());
				if(null != appUser){
					if(null != appUser.getCurrentShareNum()){
						if(appUser.getCurrentShareNum() > 0){
							//向share表中加入记录
							share.setShareTime(new Date());
							shareService.saveorupdate(share);
							
							//更新appUser表中的当前可领取次数字段
							appUser.setCurrentShareNum(appUser.getCurrentShareNum());
							appUserService.update(appUser);
						}
					}
					
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("该用户不存在");
				}
				
				//海报红包
				if(1 == share.getType()){
					PosterPackage posterPackage = posterPackageService.findPosterPackageById(share.getId());
					if(null != posterPackage){
						if(null == posterPackage.getShareNum()){
							posterPackage.setShareNum(0);
						}
						posterPackage.setShareNum(posterPackage.getShareNum() + 1);
						posterPackageService.update(posterPackage,true);
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该红包不存在");
					}
				}
				
				//视频红包
				if(1 == share.getType()){
					MediaPackage mediaPackage = mediaPackageService.findMediaPackageById(share.getId());
					if(null != mediaPackage){
						if(null == mediaPackage.getShareNum()){
							mediaPackage.setShareNum(0);
						}
						mediaPackage.setShareNum(mediaPackage.getShareNum() + 1);
						mediaPackageService.update(mediaPackage,true);
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该红包不存在");
					}
				}
				
				//卡券红包
				if(1 == share.getType()){
					Card card = cardService.findCardById(share.getId());
					if(null != card){
						if(null == card.getShareNum()){
							card.setShareNum(0);
						}
						card.setShareNum(card.getShareNum() + 1);
						cardService.update(card,true);
					}else{
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("该卡券不存在");
					}
				}
				
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
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/system/share/shareCru";
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
				shareService.deleteById(id,Share.class);
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
			shareService.deleteByIds(ids,Share.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
