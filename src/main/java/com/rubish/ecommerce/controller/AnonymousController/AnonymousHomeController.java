package com.rubish.ecommerce.controller.AnonymousController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("anonymous")
public class AnonymousHomeController {

    @GetMapping("home")
    public String openFirstPage(){
        return "anonymous/anonymousPage";
    }
}
