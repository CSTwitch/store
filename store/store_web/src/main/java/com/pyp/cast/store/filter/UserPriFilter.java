package com.pyp.cast.store.filter;

import com.pyp.cast.store.domain.PO.Admin;
import com.pyp.cast.store.domain.PO.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "UserPriFilter",urlPatterns = {"/jsp/cart.jsp", "/jsp/footer.jsp", "/jsp/header.jsp", "/jsp/order_info.jsp", "/jsp/order_list.jsp",
                                                       "/jsp/product_info.jsp", "/jsp/product_list.jsp","/index.jsp",
                                                        "/product/*","/order/*",
                                                        "/user/logout.do"})
            public class UserPriFilter implements Filter {
                @Override
                public void init(FilterConfig filterConfig) throws ServletException {

                }

                @Override
                public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                    System.out.println("我这是被调用了吗");
                    HttpServletRequest req=(HttpServletRequest)request;
                    User user=(User) req.getSession().getAttribute("user");
                    if(null==user){
                        System.out.println("请登录后在访问");
                        req.setAttribute("msg", "请登录后再访问");
                        req.getRequestDispatcher("/jsp/login.jsp").forward(req, response);
                        return;
        }
        System.out.println("管理员："+user);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
