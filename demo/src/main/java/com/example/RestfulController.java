package com.example;

import java.util.List;
import com.example.TestDto;
import com.example.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestfulController {
	
	@Autowired
	private TestService service;

    // テキストが返る※@RestControllerが付いているので、@ResponseBodyが不要
    @RequestMapping(value = "/api/text/")
    public String getTestMember() {
        return "aaaa";
    }
    
    // JSON返却する例※jackson.coreとjackson-databindがPOMに必要
    @RequestMapping("/testJson/api/json/")
    public List<TestDto> json() {
        List<TestDto> tests = service.getTestAll();
        return tests;
    }
}
