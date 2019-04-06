package modules.login.pojo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

/**
 * 系统账号信息
 * 请定义标准的javabean
 * 字段不能有下划线
 * 采用驼峰规则
 * @author Administrator
 *
 */
public class Account implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8928415979427172220L;

    private String id;
    @NotBlank(message="用户名不能为空")
    private String uname;
    private String upassSalt;
    private String remark;
    private String host;
    private String createTime;
    private String loginTime;
    private String flag;
    private String loginEquipment;
    
    public Account() {
        super();
    }

    /**
     * 显示系统账号的登录信息
     */
	@Override
    public String toString() {
        return "系统账号注册信息: [id=" + id + "," +
        				 " uname=" + uname +  "," +
        		         " upass_salt=" + upassSalt + "," +
        				 " host="+ host +  "," +
        		         " create_time="+ createTime + "," +
        		         " login_time=" + loginTime +  "," +
        		         " login_equipment=" + loginEquipment + "]";
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpassSalt() {
		return upassSalt;
	}

	public void setUpassSalt(String upassSalt) {
		this.upassSalt = upassSalt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getLoginEquipment() {
		return loginEquipment;
	}

	public void setLoginEquipment(String loginEquipment) {
		this.loginEquipment = loginEquipment;
	}
}
