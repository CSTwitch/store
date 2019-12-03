package com.pyp.cast.store.web;

import com.pyp.cast.store.domain.PO.Order;
import com.pyp.cast.store.domain.PO.User;
import com.pyp.cast.store.service.IAdminUserService;
import com.pyp.cast.store.service.IOrderService;
import com.pyp.cast.store.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/adminUser")
public class AdminUserController {
    @Autowired
    private IAdminUserService adminUserService;
    @Autowired
    private IOrderService orderService;

    /**
     * 查询所有用户信息
     * @param num
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAllUsersWithPage.do")
    public ModelAndView findAllUsersWithPage(String num) throws Exception {
        ModelAndView mv = new ModelAndView();
        int curNum=Integer.parseInt(num);
        PageModel pageModel = adminUserService.findAllProductWithpage(curNum);
        //设置根路径
        pageModel.setUrl("/adminUser/findAllUsersWithPage.do");
        mv.addObject("pageModel",pageModel);
        mv.setViewName("forward:/admin/user/list.jsp");
        return mv;
    }


    /**
     * 根据用户id查询他的浏览记录
     * @param uid
     * @param num
     * @return
     * @throws Exception
     */
    @RequestMapping("/findBrowseProductWithPage.do")
    public ModelAndView findBrowseProductWithPage(String uid, String num) throws Exception {
        ModelAndView mv = new ModelAndView();
        int cur = Integer.parseInt(num);
        PageModel pageModel = adminUserService.findBrowseProductWithPage(uid,cur);
        pageModel.setUrl("/adminUser/findBrowseProductWithPage.do?uid="+uid);
        mv.addObject("pageModel",pageModel);
        mv.setViewName("forward:/admin/user/user_browse_list.jsp");
        return mv;
    }

    /**
     * 根据用户id查询他的订单
     * @param uid
     * @param num
     * @return
     * @throws Exception
     */
    @RequestMapping("findUserOrdersWithPage.do")
    public ModelAndView findUserOrdersWithPage(String uid, String num) throws Exception {
        ModelAndView mv = new ModelAndView();
        int cur = Integer.parseInt(num);
        PageModel pageModel = orderService.findMyOrdersWithpage(uid,cur);
        pageModel.setUrl("/adminUser/findUserOrdersWithPage.do?uid="+uid);
        mv.addObject("pageModel",pageModel);
        mv.setViewName("forward:/admin/user/user_order_list.jsp");
        return mv;
    }

}
