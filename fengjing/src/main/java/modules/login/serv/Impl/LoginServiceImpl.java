package modules.login.serv.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import modules.login.dao.LoginDao;
import modules.login.pojo.Account;
import modules.login.serv.LoginService;

@Service(value="loginServiceImpl")
public class LoginServiceImpl implements LoginService{

	private Logger logger = Logger.getLogger(LoginServiceImpl.class);
	
	@Resource
	private LoginDao loginDao;
	
	/**
	 * 获取系统所有账号
	 * @author Administrator
	 * @return
	 */
	@Override	
	public Account getAllAccount(){
		logger.info(" find all Account in getAllAccount --> getAllAccount");
		return loginDao.getAllAccount();
	}
	
}
