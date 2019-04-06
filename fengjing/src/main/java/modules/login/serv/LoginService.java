package modules.login.serv;

import modules.login.pojo.Account;

/**
 * 登录业务
 * @author Administrator
 *
 */
public interface LoginService {
	/**
	 * 获取系统所有账号
	 * @return
	 */
	public Account getAllAccount();
}
