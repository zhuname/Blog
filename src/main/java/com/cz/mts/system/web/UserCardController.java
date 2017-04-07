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

import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Card;
import com.cz.mts.system.entity.Medal;
import com.cz.mts.system.entity.UserCard;
import com.cz.mts.system.entity.UserMedal;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.ICardService;
import com.cz.mts.system.service.IMedalService;
import com.cz.mts.system.service.IUserCardService;
import com.cz.mts.system.service.IUserMedalService;
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
 * @see com.cz.mts.system.web.UserCard
 */
@Controller
@RequestMapping(value="/system/usercard")
public class UserCardController  extends BaseController {
	@Resource
	private IUserCardService userCardService;
	
	@Resource
	private ICardService cardService;
	
	@Resource
	private IAppUserService appUserService;
	
	@Resource
	private IUserMedalService userMedalService;
	
	@Resource
	private IMedalService medalService;
	
	private String listurl="/system/usercard/usercardList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param userCard
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,UserCard userCard) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, userCard);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param userCard
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@SecurityApi
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,UserCard userCard) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		
		Finder finder=Finder.getSelectFinder(UserCard.class).append("where status!=0 ");
		if(null != userCard.getUserId()){
			finder.append(" and userId = :userId");
			finder.setParam("userId", userCard.getUserId());
		}
		
		finder.append(" ORDER BY `status` ASC,expTime ASC");
		
		// ==执行分页查询
		List<UserCard> datas=userCardService.findListDataByFinder(finder,page,UserCard.class,null);
		
		if(datas.size()>0){
			
			for (UserCard userCard2 : datas) {
				Card card = cardService.findCardById(userCard2.getCardId());
				 if(null != card.getUserId()){
					 AppUser appUser = appUserService.findAppUserById(card.getUserId());
					 if(null != appUser){
						 card.setAppUser(appUser);
					 }
					 
					 //查询勋章列表
					 Page page1 = new Page();
					 UserMedal userMedal = new UserMedal();
					 userMedal.setUserId(card.getUserId());
					List<UserMedal> userMedals = userMedalService.findListDataByFinder(null, page1, UserMedal.class, userMedal);
					if(null != userMedals && userMedals.size() > 0){
						for (UserMedal um : userMedals) {
							if(null != um.getMedalId()){
								Medal medal = medalService.findMedalById(um.getMedalId());
								if(null != medal){
									um.setMedal(medal);
								}
							}
						}
						card.setUserMedals(userMedals);
					}
				 }
				userCard2.setCard(card);
			}
		}
		
		returnObject.setQueryBean(userCard);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,UserCard userCard) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
		
		
	
		File file = userCardService.findDataExportExcel(null,listurl, page,UserCard.class,userCard);
		String fileName="userCard"+GlobalStatic.excelext;
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
		return "/system/usercard/usercardLook";
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
		  UserCard userCard = userCardService.findUserCardById(id);
		   returnObject.setData(userCard);
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
	ReturnDatas saveorupdate(Model model,UserCard userCard,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
		
			userCardService.saveorupdate(userCard);
			
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
		return "/system/usercard/usercardCru";
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
				userCardService.deleteById(id,UserCard.class);
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
			userCardService.deleteByIds(ids,UserCard.class);
		} catch (Exception e) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
