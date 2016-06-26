package com.example;

import com.example.TestDto;
import com.example.Test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TestService {

	public List<TestDto> getTestAll() {
	    List<TestDto> resultList = convertToDto();
	    return resultList;
	}
	
	private List<TestDto> convertToDto() {
		
		// テスト用に詰める
		List<Test> testList = new ArrayList<Test>();
		Test test = new Test();
		test.setId(17);
		test.setName("hogehoge");
		testList.add(test);
		
		test = new Test();
		test.setId(18);
		test.setName("hogehogehoge");
		testList.add(test);
		
		test = new Test();
		test.setId(23);
		test.setName("芳賀");
		testList.add(test);
		
	    List<TestDto> resultList = new ArrayList<TestDto>() ;
	    for (Test entity : testList) {
	        TestDto dto = new TestDto();
	        BeanUtils.copyProperties(entity, dto);
	        resultList.add(dto);
	    }
	    return resultList;
	}
    
}


