package modules.login.ctrl;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import modules.login.pojo.Account;
import modules.login.serv.LoginService;

/**
 * 登录页面
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController{
	private Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;

	@RequestMapping(value="/home",method=RequestMethod.GET)
	public ModelAndView toLogin(@Valid Account account, BindingResult result, Model model) throws Exception {
        logger.info("toLogin in LoginController --> toLogin");
        Account all_Account = loginService.getAllAccount();
		model.addAttribute("account", "eeeee");
		System.out.println(all_Account);
        return new ModelAndView("ureport/ureport_home","account",account);//跳转到视图
    }
	
    public String indexPage(){
        return "ureport/ureport_home";
    }
}
