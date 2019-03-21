package com.crh.demo.annotation.userdemo;

import org.springframework.web.bind.annotation.RequestMapping;

import com.crh.demo.annotation.annotation.CustomOne;
@CustomOne(name="/a")
public class UserCustomOne {
	@CustomOne(name="/b",value=1)
	public void doSomeThingOne(String str) {
		System.out.println("str++++"+str);
	}
	@RequestMapping(value="/c")
	public void doSomeThingTwo(String str) {
		System.out.println("str++++"+str);
	}

}
