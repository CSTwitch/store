package com.pyp.cast.store.web;

import com.pyp.cast.store.domain.PO.Product;
import com.pyp.cast.store.service.IProductService;
import com.pyp.cast.store.utils.PageModel;
import com.pyp.cast.store.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/adminPro")
public class AdminProductController {
    @Autowired
    private IProductService productService;

    /**
     * 添加商品
     * @param session
     * @param picture
     * @param product
     * @return
     * @throws Exception
     */
    @RequestMapping("/addProduct.do")
    public ModelAndView addProduct(HttpSession session, MultipartFile picture,Product product) throws Exception {
        ModelAndView mv=new ModelAndView();
        String path = session.getServletContext().getRealPath("/img/products/");
        UploadUtils uploadUtils = new UploadUtils();
        String fileName = uploadUtils.upload(picture,path);
        product.setPimage("img"+"/products/"+fileName);
        productService.addProduct(product);
        mv.setViewName("forward:/adminPro/findAllProductsWithPage.do?num=1");
        return mv;
    }

    /**
     * 管理员查询所有产品并进行分页
     * @param num
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAllProductsWithPage.do")
    public ModelAndView findAllProductsWithPage(String num) throws Exception {
        ModelAndView mv = new ModelAndView();
        int curNum=Integer.parseInt(num);
        PageModel pageModel = productService.findAllProductWithpage(curNum);
        //设置根路径
        pageModel.setUrl("/adminPro/findAllProductsWithPage.do");
        mv.addObject("pageModel",pageModel);
        mv.setViewName("forward:/admin/product/list.jsp");
        return mv;
    }

    /**
     * 查看某个产品的详细信息
     * @param pid
     * @return
     * @throws Exception
     */
    @RequestMapping("/findProductByPid.do")
    public ModelAndView findProductByPid(String pid) throws Exception {
        ModelAndView mv = new ModelAndView();
        Product product = productService.findProductByPid02(pid);
        mv.addObject("product",product);
        mv.setViewName("forward:/admin/product/edit.jsp");
        return mv;
    }


    /**
     * 修改商品信息
     * @param session
     * @param picture
     * @param product
     * @return
     * @throws Exception
     */
    @RequestMapping("updateProductByPid.do")
    public ModelAndView updateProductByPid(HttpSession session, MultipartFile picture,Product product) throws Exception {
        ModelAndView mv = new ModelAndView();
        String path = session.getServletContext().getRealPath("/img/products/");
        UploadUtils uploadUtils = new UploadUtils();
        String fileName = uploadUtils.upload(picture,path);
        product.setPimage("img"+"/products/"+fileName);
        productService.updateProductByPid(product);
        mv.setViewName("forward:/adminPro/findAllProductsWithPage.do?num=1");
        return mv;
    }

    /**
     * 根据商品id删除商品信息
     * @param pid
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteProductByPid.do")
    public ModelAndView deleteProductByPid(String pid) throws Exception {
        ModelAndView mv = new ModelAndView();
        productService.deleteProductByPid(pid);
        mv.setViewName("forward:/adminPro/findAllProductsWithPage.do?num=1");
        return mv;
    }
}
