package modules.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 自定义的拦截器
 * 这个拦截器不能拦截xml的serverlet请求
 * @author Administrator
 *
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {

	public CommonInterceptor(){
		System.out.println("过滤器构造!");
	}
	
	/**
	 * 该方法在访问Controller之前被调用，这里可以改变Controller的请求方向。
     * 比如判断url将原来的Controller请求重定向到其他Controller的请求上
     * 这里将项目名/fengjing/的请求指定到/login/home这个Controller的请求上
     * 不继续原来的请求 则返回false，继续原来的请求返回true
	 */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String host = request.getRemoteHost();
        String url = request.getRequestURI();
        System.out.println("IP为---->>> " + host + " <<<-----访问了1系统" + "url=" + url);
        if("/fengjing/".equals(url)){
        	request.getRequestDispatcher("/login/home").forward(request, response);
        	return false;//不继续原来的请求
        }
        //拦截到请求动作  然后继续其他请求 返回true
        return true;
    }

    /**
     * 这个方法是在preHandle返回true
     * 在业务处理器处理请求执行完成后，生成视图之前执行。请求执行完成后（调用了Service并返回ModelAndView，但未进行页面渲染），有机会修改ModelAndView
     * 在这个方法里如果要将原来的跳转页面换成其它的跳转页面就在这个方法里面处理
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	String host = request.getRemoteHost();
        String url = request.getRequestURI();
        System.out.println("IP为---->>> " + host + " <<<-----访问了2系统"+"url="+url);
        if (modelAndView != null){
        	//modelAndView.setViewName("h5qingnianbang/index" ); 修改这个将要跳转的页面添加进去
        }
    }

    /**
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。
     * 该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行， 这个方法的主要作用是用于清理资源的，
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String host = request.getRemoteHost();
        System.out.println("IP为---->>> " + host + " <<<-----访问了3系统");
    }
}
