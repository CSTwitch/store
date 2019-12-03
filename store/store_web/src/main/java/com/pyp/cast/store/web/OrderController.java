package com.pyp.cast.store.web;

import com.pyp.cast.store.domain.PO.Order;
import com.pyp.cast.store.domain.PO.OrderItem;
import com.pyp.cast.store.domain.PO.User;
import com.pyp.cast.store.service.IOrderService;
import com.pyp.cast.store.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    /**
     * 查看该用户的购物车
     * @param sessione
     * @return
     * @throws Exception
     */
    @RequestMapping("/findNoPaidOrderByUid.do")
    public ModelAndView findNoPaidOrderByUid(HttpSession sessione) throws Exception {
        ModelAndView mv = new ModelAndView();
        User user = (User) sessione.getAttribute("user");
        List<OrderItem> orderItems = orderService.findNoPaidOrderItemsByUid(user.getUid());
        mv.addObject("orderItems",orderItems);
        mv.setViewName("cart");
        return mv;
    }

    /**
     * 添加产品到购物车
     * @param session
     * @param pid 商品编号
     * @param quantity 商品数量
     * @return
     * @throws Exception
     */
    @RequestMapping("/addCartItemByUid.do")
    public ModelAndView addCartItemByUid(HttpSession session, String pid,String quantity) throws Exception {
        ModelAndView mv = new ModelAndView();
        User user = (User) session.getAttribute("user");
        orderService.addCartItemByUid(user,pid,quantity);
        mv.setViewName("redirect:/order/findNoPaidOrderByUid.do");
        return mv;
    }

    /**
     * 移除购物车中的某件商品
     * @param session 获取用户信息
     * @param pid 商品id
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteCartItemByUidAndPid.do")
    public ModelAndView deleteCartItemByUidAndPid(HttpSession session,String pid) throws Exception {
        ModelAndView mv = new ModelAndView();
        User user = (User) session.getAttribute("user");
        orderService.deleteCartItemByUidAndPid(user.getUid(),pid);
        mv.setViewName("redirect:/order/findNoPaidOrderByUid.do");
        return mv;
    }

    /**
     * 移除购物车中的某件商品
     * @param session 获取用户信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteAllCartByUid.do")
    public ModelAndView deleteAllCartByUid(HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView();
        User user = (User) session.getAttribute("user");
        orderService.deleteAllCartByUid(user.getUid());
        mv.setViewName("redirect:/order/findNoPaidOrderByUid.do");
        return mv;
    }

    /**
     * 跳转到结算购物车的界面
     * @param sessione
     * @return
     * @throws Exception
     */
    @RequestMapping("/jumpToOrderInfo.do")
    public ModelAndView jumpToOrderInfo(HttpSession sessione) throws Exception {
        ModelAndView mv = new ModelAndView();
        //用户登陆信息
        User user = (User) sessione.getAttribute("user");
        //购物车商品信息
        List<OrderItem> orderItems = orderService.findNoPaidOrderItemsByUid(user.getUid());
        //购物车总价格
        int total_price=0;
        for (OrderItem orderItem:orderItems) {
            int price = Integer.parseInt(orderItem.getTotal_price());
            total_price = total_price+price;
        }
        //对应的订单ID
        Order order = orderService.findNoPaidOrderByUid(user.getUid());
        String oid = order.getOid();

        mv.addObject("orderItems",orderItems);
        mv.addObject("total_price",total_price);
        mv.addObject("oid",oid);
        mv.setViewName("order_info");
        return mv;
    }

    /**
     * 结算购物车，订单状态我为已支付状态
     * 更改订单状态  statu=0：购物车内容  statu=1：订单已支付  statu=2：卖家已发货  statu=3：买家确认收货
     * @param order
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateOrderStatuByOid.do")
    public ModelAndView updateOrderStatuByOid(Order order) throws Exception {
        ModelAndView mv = new ModelAndView();
        //订单下单时间
        Date date = new Date();
        order.setOrdertime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date));
        orderService.updateOrderStatuByOid(order);
        mv.setViewName("redirect:/order/findMyOrdersWithPage.do?num=1");
        return mv;
    }

    @RequestMapping("findMyOrdersWithPage.do")
    public ModelAndView findMyOrdersWithPage(HttpSession session,String num) throws Exception {
        ModelAndView mv = new ModelAndView();
        User user = (User)session.getAttribute("user");
        String uid = user.getUid();
        int cur = Integer.parseInt(num);
        PageModel pageModel = orderService.findMyOrdersWithpage(uid,cur);
        mv.addObject("pageModel",pageModel);
        mv.setViewName("order_list");
        return mv;
    }

}
