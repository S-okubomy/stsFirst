package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;;

@Controller
@RequestMapping("/test")
public class TestView1 
{
    @RequestMapping(method=RequestMethod.GET)
    public String hello() {
        return "hello";
    }
}
