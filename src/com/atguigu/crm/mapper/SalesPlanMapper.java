package com.atguigu.crm.mapper;

//<<<<<<< .working
import java.util.List;
import java.util.Map;
//=======
//>>>>>>> .merge-right.r17
import java.util.Map;
import java.util.UUID;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.SalesPlan;


public interface SalesPlanMapper {
	
	Customer getByCustName(String custName);
	
	SalesChance getByChanceId(Integer id);
	
	void updateDevelop();
	//name对应sales_chances表中的cust_name
	void saveCustomers1(String name,UUID no,String state);
	
	//name对应sales_chances表中的contact
	void saveContacts(String name,String tel,Integer customerId);

	Integer getTotalElements(Long userId);

	List<SalesChance> getContent(Map<String, Object> map);

	SalesChance getById(Long id);

	int deletePlan(Integer id);

	int updateTodo(SalesPlan salesPlan);

	int savePlan(SalesPlan salesPlan);

	int save(SalesPlan salesPlan);

	void saveContacts(String contact, String contactTel, int i);

	void saveCustomers(String custName, UUID no, String state);
	
}
