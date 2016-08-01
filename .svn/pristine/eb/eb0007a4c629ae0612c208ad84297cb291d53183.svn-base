<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>执行计划</title>
<script type="text/javascript">
	$(function() {

		//保存执行的计划
		$(":button[id^='saveresult']").click(function(){
			alert("111111");
			var url = "${ctp}/plan/executePlan";
			var result = $(this).prev("input").val();
			var id = $(this).val();
			var args={"id":id,"result":result};
			$.post(url,args,function(data){
				if(data != null){
					//注意，此处无法使用this关键字获取
					$("#input-"+id).val(data);
					$("#input-"+id).attr("disabled","disabled");
					$("#saveresult-"+id).hide();
					}else{
						alert("执行失败");
					}
			});
		return false;
		});
		
		
		
		//终止开发
		$(".common_button").click(function(){
			
			var url = $(this).attr("onclick");
			
			alert(url);
			
			$("form").attr("action",url).submit();
			
			return false;
		});
		
	});
</script>
</head>

<body class="main">
	<span class="page_title">执行计划</span>
	<div class="button_bar">
		<button class="common_button"
			onclick="${ctp}/plan/chance/stop/${chance.id }">终止开发</button>
		<button class="common_button"
			onclick="window.location.href='${ctp}/plan/make/${chance.id }'">制定计划</button>
		<button class="common_button" onclick="javascript:history.go(-1);">返回</button>
		<button class="common_button"
			onclick="window.location.href='${ctp}/chance/finish/${chance.id }'">开发成功</button>
	</div>
	<form action="${ctp }/plan/execute" method="post">
	
		<input type="hidden" name="_method" value="PUT" id="_method"/>
		
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>编号</th>
				<td>${chance.id }&nbsp;</td>

				<th>机会来源</th>
				<td>${chance.source }&nbsp;</td>
			</tr>
			<tr>
				<th>客户名称</th>
				<td>${chance.custName }&nbsp;</td>

				<th>成功机率（%）</th>
				<td>${chance.rate }&nbsp;</td>
			</tr>

			<tr>
				<th>概要</th>
				<td colspan="3">${chance.title }&nbsp;</td>
			</tr>

			<tr>
				<th>联系人</th>
				<td>${chance.contact }&nbsp;</td>
				<th>联系人电话</th>
				<td>${chance.contactTel }&nbsp;</td>
			</tr>
			<tr>
				<th>机会描述</th>
				<td colspan="3">${chance.description }&nbsp;</td>
			</tr>
			<tr>
				<th>创建人</th>
				<td>${chance.createBy.name}&nbsp;</td>
				<th>创建时间</th>
				<td><fmt:formatDate value="${chance.createDate }"
						pattern="yyyy-MM-dd" />&nbsp;</td>
			</tr>
			<tr>
				<th>指派给</th>
				<td>${chance.designee.name}&nbsp;</td>
			</tr>
		</table>

		<br />
		<c:if test="${not empty chance.salesPlans }">
			<table class="data_list_table" border="0" cellPadding="3" cellSpacing="0">
				<tr>
					<th width="200px">日期</th>
					<th>计划</th>
					<th>执行效果</th>
				</tr>
				<c:forEach items="${chance.salesPlans }" var="plan">
					<tr class="list_data_text" >
						<td class="list_data_text">
						<fmt:formatDate value="${plan.date }" pattern="yyyy-MM-dd" />&nbsp;</td> 
						<td class="list_data_ltext">${plan.todo}&nbsp;</td>
						
						<c:if test="${plan.result != null }">
							<td><input  type="text" value="${plan.result }" readonly="readonly"size="50"/></td>
						</c:if>
						<c:if test="${plan.result == null }">
							<td>
								<input type="text" value="${plan.result }" size="50" id="input-${plan.id }" />
								<button class="common_button" id="saveresult-${plan.id }" value="${plan.id }"  >保存</button>
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<c:if test="${empty chance.salesPlans }">
		还没有制定任何计划
	</c:if>
	</form>
</body>
</html>
