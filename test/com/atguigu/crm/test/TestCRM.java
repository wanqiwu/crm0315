package com.atguigu.crm.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.SalesChanceService;

public class TestCRM {
	
	
	private ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private SalesChanceService salesChanceService = ioc.getBean(SalesChanceService.class);
	
	@Test
	public void testPage(){
		
		Page<SalesChance> page = salesChanceService.getPage(2);
		System.out.println(page.getTotalElements());
		System.out.println(page.getTotalPages());
		
	}
	
}
