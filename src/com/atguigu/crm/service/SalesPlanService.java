package com.atguigu.crm.service;

//<<<<<<< .working

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//=======
//>>>>>>> .merge-right.r17
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.SalesChance;
//<<<<<<< .working
import com.atguigu.crm.entity.SalesPlan;
//=======
//>>>>>>> .merge-right.r17
import com.atguigu.crm.mapper.SalesPlanMapper;
import com.atguigu.crm.orm.Page;

@Service
public class SalesPlanService {

	@Autowired
	private SalesPlanMapper salesPlanMapper;
//<<<<<<< .working

///=======
	
//>>>>>>> .merge-right.r17
	@Transactional
	public Page<SalesChance> getPage(int pageNo, Long userId) {
		Page<SalesChance> page = new Page<SalesChance>();
		//1、设置page对象的页码
		page.setPageNo(pageNo);
		//2、查询总记录数
		Integer totalElements = salesPlanMapper.getTotalElements(userId);
		page.setTotalElements(totalElements);
		
//<<<<<<< .working
		//3、查询List集合
		int first = (page.getPageNo()-1)*(page.getPageSize())+1;
		int end = first + page.getPageSize();
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("firstIndex", first);
		map.put("endIndex", end);
		map.put("userId", userId);
		List<SalesChance> list = salesPlanMapper.getContent(map);
		page.setContent(list);
//=======
		salesPlanMapper.updateDevelop();
//>>>>>>> .merge-right.r17
		
//<<<<<<< .working
		return page;
	}
//<<<<<<< .working

	@Transactional
	public SalesChance getById(Long id) {
		SalesChance sc = salesPlanMapper.getById(id);
		return sc;
	}



	@Transactional
	public int updateTodo(SalesPlan salesPlan) {
		System.out.println(salesPlan);
		int row = salesPlanMapper.updateTodo(salesPlan);
		return row;
	}	
	@Transactional
	public int deletePlan(Integer id) {
		int row = salesPlanMapper.deletePlan(id);
		return row;
	}

	@Transactional
	public int savePlan(SalesPlan salesPlan) {
		int row = salesPlanMapper.savePlan(salesPlan);
		return row;
	}

	public int save(SalesPlan salesPlan) {
		int row = salesPlanMapper.save(salesPlan);
		return row;
	}

	
	public void querySalesDetail(Integer chanceId) {
		// TODO Auto-generated method stub
		
	}

	public void fail(Integer chanceId) {
		// TODO Auto-generated method stub
		
	}
//=======
//>>>>>>> .merge-right.r17

	public void update(Integer chanceId) {
		// TODO Auto-generated method stub
		
	}

	
}
