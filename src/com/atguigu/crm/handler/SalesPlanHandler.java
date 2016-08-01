package com.atguigu.crm.handler;

import java.text.ParseException;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.SalesPlan;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.SalesChanceService;
import com.atguigu.crm.service.SalesPlanService;

@RequestMapping(value="/plan")
@Controller
public class SalesPlanHandler {
	
	@Autowired
	private SalesChanceService salesChanceService;
	
	@Autowired
	private SalesPlanService salesPlanService;
	
	
	/**
	 * 查看详细信息
	 * @param chanceId
	 * @return
	 */
	@RequestMapping(value="/chance/detail/{chanceId}",method=RequestMethod.GET)
	public String querySales(@PathVariable("chanceId") Integer chanceId){
		
		salesPlanService.querySalesDetail(chanceId);
		
		return "plan/detail";
	}
	
	
	/**
	 * 终止计划
	 * @param chanceId
	 * @param map
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value="/chance/stop/{chanceId}",method=RequestMethod.PUT)
	public String fail(@PathVariable("chanceId") Integer chanceId,Map<String,Object> map,RedirectAttributes attributes){
		
		
		salesPlanService.fail(chanceId);
		
		attributes.addFlashAttribute("message", "开发失败");
		
		return "redirect:/plan/chance/list";
	}
	
	
	@RequestMapping(value="/execution/{chanceId}")
	public String toExecutionPage(@PathVariable("chanceId") Integer chanceId){
		
		
		return "plan/execute";
	}
	
	
	/**
	 * 完成计划,开发成功
	 * @param chanceId
	 * @param map
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value="/chance/finish/{chanceId}",method=RequestMethod.PUT)
	public String finish(@PathVariable("chanceId") Integer chanceId,Map<String,Object> map,
							RedirectAttributes attributes){
		
		System.out.println("chanceId="+chanceId);
		salesPlanService.update(chanceId);
		
		attributes.addFlashAttribute("message", "更新成功！");
		
		return "redirect:/plan/chance/list";
	}
	
	
	/**
	 * 显示销售计划列表（不带查询条件的分页）
	 * @param request
	 * @param pageNoStr
	 * @param map
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/chance/list")
	public String getList(HttpServletRequest request,
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			Map<String, Object> map) throws ParseException {
		
		User user = (User) request.getSession().getAttribute("user");
		
		
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		
		Page<SalesChance> page = salesChanceService.getPage3(pageNo);

		
		
		map.put("page", page);
		
		
		return "plan/chance/list";
	}

	/**
	 * 遍历集合. 在转为一个查询字符串. 注意: key 前面需要添加 "search_" 前缀
	 * @param params
	 * @return
	 */
	private String parseRequestParams2QueryString(Map<String, Object> params) {

		StringBuilder result = new StringBuilder("");

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			Object val = entry.getValue();

			if (val == null || val.toString().trim().equals("")) {
				continue;
			}

			result.append("search_").append(key).append("=").append(val)
					.append("&");
		}

		if (result.length() > 1) {
			result = result.replace(result.length() - 1, result.length(), "");
		}
		return result.toString();
	}
}
