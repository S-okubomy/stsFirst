package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;;

@Controller
@RequestMapping("/test2")
public class TestView2 
{
    @RequestMapping(method=RequestMethod.GET)
    public String hello() {
        return "kabuCalList";
    }
}
