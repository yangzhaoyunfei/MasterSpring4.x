
package com.smart.web;

import com.smart.domain.User;
import com.smart.exception.UserExistException;
import com.smart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户注册控制器
 */
@Controller
public class RegisterController extends BaseController {
    /**
     * Spring团队建议的,基于构造函数的自动注入
     */
    private UserService userService;

    /**
     * Spring团队建议的,基于构造函数的自动注入
     */
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登录
     *
     * @param request
     * @param user    注册表单对应的user
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest request, User user) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/success");
        try {
            userService.register(user);
        } catch (UserExistException e) {
            mav.addObject("errorMsg", "用户名已经存在,请选择其它的名字。");
            //重定向到注册页面
            mav.setViewName("forward:/register.jsp");
        }
        setSessionUser(request, user);
        return mav;
    }

}