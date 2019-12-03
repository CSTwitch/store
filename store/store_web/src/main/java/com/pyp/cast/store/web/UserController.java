package com.pyp.cast.store.web;

import com.pyp.cast.store.domain.PO.User;
import com.pyp.cast.store.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    /**
     * 用户登陆
     * @param session 保存用户状态
     * @param user  表单提交的账号、密码
     * @return
     * @throws Exception
     */
    @RequestMapping("/userLogin.do")
    public ModelAndView userLogin(HttpSession session, User user) throws Exception {
        ModelAndView mv = new ModelAndView();
        User user01 = userService.findUserByUserName(user.getUsername());
        //如果账号不存在，重定向回登陆界面
        if (user01 == null){
            mv.setViewName("login");
            mv.addObject("msg","账号不存在!");
            return mv;
        }
        //用户界面提交的账号密码
        String username = user.getUsername();
        String userpassword = user.getPassword();

        //数据库查询得到的账号密码
        String user01name = user01.getUsername();
        String user01password = user01.getPassword();

        //两者对比，查看登陆信息是否一致；一致则保存进session域中，并转发到首页;不同则返回到登陆界面
        if (username.equals(user01name) && userpassword.equals(user01password)){
            session.setAttribute("user",user01);
            mv.setViewName("redirect:/product/findAllProduct.do?num=1");
            return mv;
        }else {
            mv.setViewName("login");
            mv.addObject("msg","密码不正确!");
            return mv;
        }
    }


    /*
     *用户退出
     */
    @RequestMapping("/logout.do")
    public ModelAndView logout(HttpSession session) throws Exception{
        ModelAndView mv = new ModelAndView();
        session.removeAttribute("user");
        session.invalidate();
        mv.setViewName("login");
        return mv;
    }

    /**
     * 用户注册
     * @param user 用户提交的注册信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/addUser.do")
    public ModelAndView addUser(User user) throws Exception {
        ModelAndView mv = new ModelAndView();
        if (userService.findUserByUserName(user.getUsername())!=null){
            String msg = "用户名已存在！";
            mv.setViewName("register");
            mv.addObject("msg",msg);
            return mv;
        }
        String uid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        user.setUid(uid);
        userService.addUser(user);
        mv.setViewName("login");
        return mv;
    }


}
