package com.pyp.cast.store.web;

import com.pyp.cast.store.domain.PO.Order;
import com.pyp.cast.store.domain.PO.OrderItem;
import com.pyp.cast.store.service.IAdminOrderService;
import com.pyp.cast.store.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/adminOrder")
public class AdminOrderController {
    @Autowired
    private IAdminOrderService adminOrderService;
    /**
     * 管理员查询所有订单并进行分页
     * @param num
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAllOrdersWithPage.do")
    public ModelAndView findAllOrdersWithPage(String num,String statu) throws Exception {
        ModelAndView mv = new ModelAndView();
        int curNum=Integer.parseInt(num);
        PageModel pageModel = adminOrderService.findAllOrdersWithPage(curNum,statu);
        //设置根路径
        pageModel.setUrl("/adminOrder/findAllOrdersWithPage.do?statu="+statu);
        mv.addObject("pageModel",pageModel);
        mv.setViewName("forward:/admin/order/list.jsp");
        return mv;
    }

    @RequestMapping("/findOrderItemsByOid.do")
    public @ResponseBody List<OrderItem> findOrderItemsByOid(String oid) throws Exception {
        return adminOrderService.findOrderItemsByOid(oid);
    }

    /**
     * 管理员发货，订单状态我为发货状态
     * 更改订单状态  statu=0：购物车内容  statu=1：订单已支付  statu=2：卖家已发货  statu=3：买家确认收货
     * @param oid
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateOrderStatuByOid.do")
    public ModelAndView updateOrderStatuByOid(String oid,String statu) throws Exception {
        ModelAndView mv = new ModelAndView();
        adminOrderService.updateOrderStatuByOid(oid,statu);
        mv.setViewName("forward:/adminOrder/findAllOrdersWithPage.do?statu=2&num=1");
        return mv;
    }

    /**
     * 用户签收，订单状态我为签收状态
     * 更改订单状态  statu=0：购物车内容  statu=1：订单已支付  statu=2：卖家已发货  statu=3：买家确认收货
     * @param oid
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateOrderStatuByOid1.do")
    public @ResponseBody String updateOrderStatuByOid1(String oid,String statu) throws Exception {
        adminOrderService.updateOrderStatuByOid(oid,statu);
        return "thank you!welcome to come again shopping.";
    }
}
