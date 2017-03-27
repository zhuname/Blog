package com.cz.mts.system.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Card;
import com.cz.mts.system.entity.Medal;
import com.cz.mts.system.entity.UserMedal;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.ICardService;
import com.cz.mts.system.service.IMedalService;
import com.cz.mts.system.service.IUserMedalService;
import com.cz.mts.frame.entity.IBaseEntity;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:25
 * @see com.cz.mts.system.service.impl.Card
 */
@Service("cardService")
public class CardServiceImpl extends BaseSpringrainServiceImpl implements ICardService {

	@Resource
	private IAppUserService appUserService;
	@Resource
	private IUserMedalService userMedalService;
	@Resource
	private IMedalService medalService;
   
    @Override
	public String  save(Object entity ) throws Exception{
	      Card card=(Card) entity;
	       return super.save(card).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      Card card=(Card) entity;
		 return super.saveorupdate(card).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 Card card=(Card) entity;
	return super.update(card);
    }
    @Override
	public Card findCardById(Object id) throws Exception{
	 return super.findById(id,Card.class);
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
	public ReturnDatas list(Card card,Page page) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		if(null == card.getStatus()){
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数缺失");
		}else{
			// ==执行分页查询
			card.setIsDel(0);
			page.setOrder("createTime");
			page.setSort("desc");
			List<Card> datas = findListDataByFinder(null,page,Card.class,card);
			if(null != datas && datas.size() > 0){
				for (Card cd : datas) {
					if(null != cd.getUserId()){
						AppUser appUser = appUserService.findAppUserById(cd.getUserId());
						if(null != appUser){
							cd.setAppUser(appUser);
						}
						UserMedal userMedal = new UserMedal();
						userMedal.setUserId(cd.getUserId());
						//查询勋章列表
						List<UserMedal> userMedals = userMedalService.findListDataByFinder(null, page, UserMedal.class, userMedal);
						if(null != userMedals && userMedals.size() > 0){
							for (UserMedal um : userMedals) {
								if(null != um.getMedalId()){
									Medal medal = medalService.findMedalById(um.getMedalId());
									if(null != medal){
										um.setMedal(medal);
									}
								}
							}
							cd.setUserMedals(userMedals);
						}
					}
				}
			}
			returnObject.setQueryBean(card);
			returnObject.setPage(page);
			returnObject.setData(datas);
		}
		return returnObject;
	}

}
