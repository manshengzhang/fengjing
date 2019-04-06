package modules.login.dao;

import modules.login.pojo.Account;

/**
 * 登录接口
 * @author Administrator
 *
 */
public interface LoginDao {
	/**
	 * 获取系统所有账号
	 * @return
	 */
	public Account getAllAccount();
}
