package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.crm.entity.SalesChance;


public interface SalesChanceMapper {
	
	/**
	 * SalesPlan的分页
	 */
	
	public int getTotalElements3();
	
	public List<SalesChance> getContent3(Map<String,Object> params);
	
	
	/**
	 * 带查询条件的分页
	 */
	public int getTotalElements2(Map<String,Object> params);
	
	public List<SalesChance> getContent2(Map<String,Object> params);
	
	/**
	 * 不带查询条件的分页
	 */
	public void update(SalesChance salesChance);
	
	public SalesChance getById(@Param(value="id") Integer id);
	
	public void save(SalesChance salesChance);
	
	public void delete(@Param(value="id") Integer id);
	
	public int getTotalElements();
	
	public List<SalesChance> getContent(Map<String,Object> params);
	
}
