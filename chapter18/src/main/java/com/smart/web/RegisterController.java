
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
 * Description: 用户注册控制器
 *
 * @author yangzhaoyunfei yangzhaoyunfei@qq.com
 * @date 2018/4/30
 */
@Controller
public class RegisterController extends BaseController {

    private UserService userService;

    /**
     * Spring团队建议的,基于构造函数的自动注入,(自动注入有:字段注入,settr函数注入,constructor函数注入)
     */
    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登录
     *
     * 当表单信息提交到这里时,Spring MVC 自动进行数据绑定
     *
     * @param request
     * @param user    注册表单对应的user
     * @return 数据模型 和 视图
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
