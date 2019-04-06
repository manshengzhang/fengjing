package modules.login.dao;

import modules.login.pojo.Account;

public interface LoginDao {
	/**
	 * 获取系统所有账号
	 * @return
	 */
	public Account getAllAccount();
}
