package com.atguigu.crm.handler;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.SalesChanceService;

@RequestMapping("/chance")
@Controller
public class SalesChanceHandler {

	@Autowired
	private SalesChanceService salesChanceService;

	/*
	 * 带查询条件的分页
	 */
	@RequestMapping(value = "/list2")
	public String getList2(HttpServletRequest request,
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			Map<String, Object> map) throws ParseException {

		// 1.获取以"search_"开头的请求参数
		Map<String, Object> params = WebUtils.getParametersStartingWith(
				request, "search_");

		// 2. 把请求参数的 Map 再转为查询的字符串. 回传给目标页面
		String queryString = parseRequestParams2QueryString(params);
		map.put("queryString", queryString);
		//System.out.println(queryString);

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		Page<SalesChance> page = salesChanceService.getPage2(pageNo, params);

		map.put("page", page);

		return "chance/list";

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

	/**
	 * 销售机会更新成功
	 * @param salesChance
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String update(SalesChance salesChance, RedirectAttributes attributes) {

		salesChanceService.update(salesChance);
		attributes.addFlashAttribute("message", "修改成功！");

		return "redirect:/chance/list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String input(@PathVariable("id") Integer id, Map<String, Object> map) {

		map.put("chance", salesChanceService.getById(id));
		return "chance/input";
	}
	
	/**
	 * 保存销售机会
	 * @param salesChance
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String save(SalesChance salesChance, RedirectAttributes attributes) {

		salesChanceService.save(salesChance);
		// RedirectAttributes给页面返回一个消息，放在请求域中
		attributes.addFlashAttribute("message", "添加成功！");

		// 重定向到另一个url。放在了请求域中的message，重定向，原先页面上可以获取到。
		// 可以理解为：先向原页面添加message。然后原页面再重定向到“/chance/list”的url
		return "redirect:/chance/list";
	}

	/**
	 * 到编辑页面
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String input(Map<String, Object> map) {

		map.put("chance", new SalesChance());

		return "chance/input";
	}

	/**
	 * 删除销售机会
	 * @param idStr
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") String idStr) {

		int id = -1;
		try {
			id = Integer.parseInt(idStr);
		} catch (Exception e) {
		}
		salesChanceService.delete(id);

		return "redirect:/chance/list";
	}

	
	/**
	 * 分页显示销售机会的列表
	 * @param pageNoStr
	 * @param map
	 * @return
	 */
	@RequestMapping("/list")
	public String getList(
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			Map<String, Object> map) {

		int pageNo = 1;

		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}

		Page<SalesChance> page = salesChanceService.getPage(pageNo);

		map.put("page", page);

		return "chance/list";
	}

}
