package com.pyp.cast.store.filter;

import com.pyp.cast.store.domain.PO.Admin;
import com.pyp.cast.store.domain.PO.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@WebFilter(filterName = "AdminPriFilter",urlPatterns = {"/admin/order/*", "/admin/product/*", "/admin/user/*",
                                                        "/admin/home.jsp", "/admin/bottom.jsp","/admin/left.jsp",
                                                        "/admin/top.jsp","/admin/welcome.jsp",
                                                        "/adminOrder/findAllOrdersWithPage.do","/adminOrder/findOrderItemsByOid.do", "/adminOrder/updateOrderStatuByOid.do",
                                                         "/adminPro/*","/adminUser/*", "/admin/logout.do"})
public class AdminPriFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("我这是被调用了吗");
        HttpServletRequest req=(HttpServletRequest)request;
        Admin admin=(Admin) req.getSession().getAttribute("admin");
        if(null==admin){
            System.out.println("请登录后在访问");
            req.setAttribute("msg", "请登录后再访问");
            req.getRequestDispatcher("/admin/index.jsp").forward(req, response);
            return;
        }
        System.out.println("管理员："+admin);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
