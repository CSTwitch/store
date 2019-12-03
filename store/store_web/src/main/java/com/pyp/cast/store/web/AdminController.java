package com.pyp.cast.store.web;

import com.pyp.cast.store.domain.PO.Admin;
import com.pyp.cast.store.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IAdminService adminService;

    /**
     * 用户登陆
     * @param session 保存用户状态
     * @param admin  表单提交的账号、密码
     * @return
     * @throws Exception
     */
    @RequestMapping("/userLogin.do")
    public ModelAndView userLogin(HttpSession session, Admin admin) throws Exception {
        ModelAndView mv = new ModelAndView();
        Admin admin01 = adminService.findAdminByUserName(admin.getAdname());
        //如果账号不存在，重定向回登陆界面
        if (admin01 == null){
            mv.setViewName("forward:/admin/index.jsp");
            mv.addObject("msg","账号不存在!");
            return mv;
        }
        //用户界面提交的账号密码
        String adname = admin.getAdname();
        String password = admin.getPassword();

        //数据库查询得到的账号密码
        String admin01name = admin01.getAdname();
        String admin01password = admin01.getPassword();

        //两者对比，查看登陆信息是否一致；一致则保存进session域中，并转发到首页;不同则返回到登陆界面
        if (adname.equals(admin01name) && password.equals(admin01password)){
            session.setAttribute("admin",admin01);
            mv.setViewName("redirect:/admin/home.jsp");
            return mv;
        }else {
            mv.setViewName("forward:/admin/index.jsp");
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
        session.removeAttribute("admin");
        session.invalidate();
        mv.setViewName("redirect:/admin/index.jsp");
        return mv;
    }
}
