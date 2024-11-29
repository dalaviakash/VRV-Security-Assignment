package org.jspider.securityapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/admin")
    public String getMessege(){
        return "welcome to admin";
    }

    @GetMapping("/manager")
    public String getInfo(){
        return "welcome to manager";
    }

    @GetMapping("/employee")
    public String getData(){
        return "Application employee";
    }

}
