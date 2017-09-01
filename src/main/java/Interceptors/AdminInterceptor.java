package Interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 管理员拦截器
 * Created by acer on 2017/8/1.
 */
public class AdminInterceptor implements HandlerInterceptor{

    /**
     * 检验session中的管理员是否存在，存在则返回true，不存在则返回false
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     * @time 2017年8月1日9:30:24
     */
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Object admin = httpServletRequest.getSession().getAttribute("admin");
        if(admin != null){
            return true;
        }
        httpServletResponse.sendRedirect("../500");
        return false;
    }


    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
