package com.pyp.cast.store.web;

import com.github.pagehelper.PageInfo;
import com.pyp.cast.store.domain.PO.Product;
import com.pyp.cast.store.domain.PO.User;
import com.pyp.cast.store.service.IProductService;
import com.pyp.cast.store.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    /**
     * 客户查询所有产品并进行分页
     * @param num
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAllProduct.do")
    public ModelAndView findAllProduct(String num) throws Exception {
        ModelAndView mv = new ModelAndView();
        int curNum=Integer.parseInt(num);
        PageModel pageModel = productService.findAllProductWithpage(curNum);
        mv.addObject("pageModel",pageModel);
        mv.setViewName("product_list");
        return mv;
    }

    /**
     * 查看某个产品的详细信息
     * @param pid
     * @return
     * @throws Exception
     */
    @RequestMapping("/findProductByPid.do")
    public ModelAndView findProductByPid(HttpSession session,String pid) throws Exception {
        ModelAndView mv = new ModelAndView();
        User user = (User) session.getAttribute("user");
        Product product = productService.findProductByPid(user.getUid(),pid);
        mv.addObject("product",product);
        mv.setViewName("product_info");
        return mv;
    }


}
