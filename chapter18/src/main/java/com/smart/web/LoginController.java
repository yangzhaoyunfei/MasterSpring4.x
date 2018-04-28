package com.smart.web;

import com.smart.cons.CommonConstant;
import com.smart.domain.User;
import com.smart.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Description: 用户登录,登录注销的控制器
 *
 * @author yangzhaoyunfei yangzhaoyunfei@qq.com
 * @date 2018/4/29
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    private UserService userService;

    /**
     * 基于构造函数的自动注入
     *
     * @param userService
     */
    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登陆
     *
     * @param request
     * @param user    登录表单对应的user对象
     * @return
     */
    @RequestMapping("/doLogin")
    public ModelAndView login(HttpServletRequest request, User user) {

        //根据登录表单中的用户名查找数据库中的user,以判断user是否存在
        User dbUser = userService.getUserByUserName(user.getUserName());

        ModelAndView mav = new ModelAndView();
        //设置视图名
        mav.setViewName("forward:/login.jsp");

        if (dbUser == null) {
            //用户不存在,则设置错误消息
            mav.addObject("errorMsg", "用户名不存在");
        } else if (!dbUser.getPassword().equals(user.getPassword())) {
            //用户存在,但密码不正确,设置错误消息
            mav.addObject("errorMsg", "用户密码不正确");
        } else if (dbUser.getLocked() == User.USER_LOCK) {
            //用户存在,且密码正确,但状态为被锁定
            mav.addObject("errorMsg", "用户已经被锁定,不能登录。");
        } else {

            /*登录验证通过后,设置一些信息*/
            dbUser.setLastIp(request.getRemoteAddr());
            dbUser.setLastVisit(new Date());
            //登录成功保存日志
            userService.loginSuccess(dbUser);
            setSessionUser(request, dbUser);
            String toUrl = (String) request.getSession().getAttribute(CommonConstant.LOGIN_TO_URL);
            //todo-yangzhaoyunfei 为什么要移除这个属性
            request.getSession().removeAttribute(CommonConstant.LOGIN_TO_URL);
            //如果当前会话中没有保存登录之前的请求URL,则将登录前url设置为主页,使登陆后直接跳转到主页
            if (StringUtils.isEmpty(toUrl)) {
                toUrl = "/index.html";
            }
            mav.setViewName("redirect:" + toUrl);
        }

        //返回模型和视图
        return mav;
    }

    /**
     * 登录注销
     *
     * @param session
     * @return
     */
    @RequestMapping("/doLogout")
    public String logout(HttpSession session) {

        //移除session中的user
        session.removeAttribute(CommonConstant.USER_CONTEXT);

        //返回视图名,重定向到主页,
        return "forward:/index.jsp";
    }

}
