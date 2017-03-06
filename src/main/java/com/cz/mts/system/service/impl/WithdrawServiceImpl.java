package com.cz.mts.system.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.MoneyDetail;
import com.cz.mts.system.entity.SysSysparam;
import com.cz.mts.system.entity.Withdraw;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.IMoneyDetailService;
import com.cz.mts.system.service.ISysSysparamService;
import com.cz.mts.system.service.IWithdrawService;
import com.cz.mts.frame.entity.IBaseEntity;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.MessageUtils;
import com.cz.mts.frame.util.Page;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:29
 * @see com.cz.mts.system.service.impl.Withdraw
 */
@Service("withdrawService")
public class WithdrawServiceImpl extends BaseSpringrainServiceImpl implements IWithdrawService {
	@Resource
	private IAppUserService appUserService;
	@Resource
	private ISysSysparamService sysSysparamService;
	@Resource
	private IMoneyDetailService moneyDetailService;
   
    @Override
	public String  save(Object entity ) throws Exception{
	      Withdraw withdraw=(Withdraw) entity;
	       return super.save(withdraw).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      Withdraw withdraw=(Withdraw) entity;
		 return super.saveorupdate(withdraw).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 Withdraw withdraw=(Withdraw) entity;
	return super.update(withdraw);
    }
    @Override
	public Withdraw findWithdrawById(Object id) throws Exception{
	 return super.findById(id,Withdraw.class);
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
	public ReturnDatas applyWithdraw(Withdraw withdraw) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		if(null == withdraw.getUserId() || null == withdraw.getWithdrawType() || null == withdraw.getMoney()){
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数缺失");
		}else{
			//查询该用户是否有手机号
			AppUser appUserRecord = appUserService.findAppUserById(withdraw.getUserId());
			if(null != appUserRecord){
				if(StringUtils.isNotBlank(appUserRecord.getPhone())){
					//银行提现
					if(1 == withdraw.getWithdrawType()){
						appUserRecord.setWithdrawType(withdraw.getWithdrawType());
						appUserRecord.setBankName(withdraw.getBankName());
						appUserRecord.setBranchBank(withdraw.getBranchBank());
						appUserRecord.setOwnerName(withdraw.getOwnerName());
						appUserRecord.setOwnerPhone(withdraw.getOwnerPhone());
						appUserRecord.setCardNum(withdraw.getCardNum());
					}else if(2 == withdraw.getWithdrawType()){
						//支付宝
						appUserRecord.setWithdrawType(withdraw.getWithdrawType());
						appUserRecord.setOwnerName(withdraw.getOwnerName());
						appUserRecord.setCardNum(withdraw.getCardNum());
					}else{
						//微信
						appUserRecord.setWithdrawType(withdraw.getWithdrawType());
						appUserRecord.setWxName(withdraw.getOwnerName());
						appUserRecord.setWxAccount(withdraw.getCardNum());
						appUserRecord.setWxPhone(withdraw.getOwnerPhone());
					}
					appUserRecord.setBalance(appUserRecord.getBalance() - withdraw.getMoney());
					appUserRecord.setFrozeBanlance(withdraw.getMoney());
					appUserService.update(appUserRecord);
					withdraw.setCreateTime(new Date());
					withdraw.setStatus(1);
					//手续费
					Finder finder = new Finder("select * FROM t_sys_sysparam where id=9");
					SysSysparam sysSysparam = new SysSysparam();
					Page page = new Page();
					List<SysSysparam> sysparams = sysSysparamService.findListDataByFinder(finder, page, SysSysparam.class,sysSysparam );
					if(null != sysparams && sysparams.size() > 0){
						sysSysparam = sysparams.get(0);
					}
					Double factorage = Double.parseDouble(sysSysparam.getValue()) * withdraw.getMoney();
					//实际到账金额
					Double realMoney = withdraw.getMoney() - factorage;
					withdraw.setFactorage(factorage);
					withdraw.setRealMoney(realMoney);
					saveorupdate(withdraw);
					//向moneyDetail表中增加记录,申请成功和申请失败都需要更新moneyDetail表中的信息
					MoneyDetail moneyDetail = new MoneyDetail();
					moneyDetail.setUserId(withdraw.getUserId());
					moneyDetail.setCreateTime(new Date());
					moneyDetail.setType(10);
					moneyDetail.setMoney(withdraw.getMoney());
					moneyDetail.setBalance(appUserRecord.getBalance());
					moneyDetailService.saveorupdate(moneyDetail);
					returnObject.setStatus(MessageUtils.UPDATE_SUCCESS);
				}else{
					returnObject.setStatus(ReturnDatas.ERROR);
					returnObject.setMessage("请绑定手机号");
				}
			}
		}
		return returnObject;
	}

}
