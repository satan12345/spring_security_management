package com.itheima.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    /**
     *
     * @return
     */
    //@Secured("ROLE_PRODUCT")//security内部使用的注解
    //@RolesAllowed("ROLE_PRODUCT")//jsr250使用的注解
    @PreAuthorize("hasAnyRole('ROLE_PRODUCT')")//spring的el表达式
    @RequestMapping("/findAll")
    public String findAll(){
        return "product-list";
    }
}
