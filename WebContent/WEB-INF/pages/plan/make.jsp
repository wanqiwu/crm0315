<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>制定计划</title>
	<script type="text/javascript">
	$(function(){
		//单击保存按钮发送Ajax请求
		$("button[id^='save']").click(function(){
			var id = $(this).next(":hidden").attr("id");
		 	var todo = $("#todo-" + id).val();
			var args={"id":id,"todo":todo};
			
			var url = "${ctp}/plan/update-ajax";
			
			$.post(url , args , function(data){
				if(data == 1){
					alert("保存成功");
				}else{
					alert("保存失败");
				}
				
			}); 
			return false;
		});
		
		//删除
		$("button[id^='delete']").click(function(){
			var id = $(this).prev(":hidden").attr("id");
			var args={"id":id};
			
			var url = "${ctp}/plan/delete-ajax";
			$.post(url , args , function(data){
				if(data == 1){
					$("#plan-"+id).remove();
					alert("删除成功!");
				}else{
					alert("删除失败!");
				};
				
			});
			return false;
		});
		
		
		
		
		
		
		//【属于新建后添加的信息】需要把保存的函数给独立出来!
		 function savePlan(button){
			 var flag = confirm("确定要保存吗?");
				
				if(flag){
					var id = $(button).next(":hidden").val();
				 	var todo = $("#todo-" + id).val();
					var args={"id":id,"todo":todo};
					var url = "${ctp}/plan/update-ajax";
					var type="json";
					$.get(url , args , function(data){
						/* if(data == 1){
							alert("保存成功");
						}else{
							alert("保存失败");
						} */
						
				},type); 
			};
		}
		//【属于新建后添加的信息】需要把删除的函数给独立出来!
		function deletePlan(button){
			var flag = confirm("确定要删除吗?");
			
			if(flag){
				var id = $(button).prev(":hidden").val();
				var args={"id":id};
				var url = "${ctp}/plan/delete-ajax";
				var type="json";
				alert(id+"测试");
				$.get(url , args , function(data){
					
					/* if(data == 1){
						//$("#plan-"+id).remove();
						alert("删除成功!");
					}else{
						alert("删除失败!");
					} */
				},type);
				
			};
		}; 
		
		
		//【难点】新建
		$("#newPlan").click(function(){
			var url="${ctp}/plan/add-ajax";
			var chanceId=$(this).val();
			var date = $("#date").val();
			var todo = $("#todo").val();
			var args={"chance.id":chanceId,"date":date,"todo":todo};
			
			//返回的信息是一个SalesPlan对象
			alert("1111");
			$.post(url , args , function(data){
				if(data.id != null && data.id > 0){
					alert(data.id);
					//创建成功过后，新添加一个一行，显示计划
				    var $tr = $("<tr id='plan-"+data.id+"'></tr>");
					$tr.append("<td  align='center' size='50'>" + date)
					   .append("<input type='text' id='todo-"+data.id+"'value='" + todo + "'/>")
					   .append("<button  id='save-"+data.id+"'>保存</button>")
					   .append("<input type='hidden' value='"+data.id+"'/>")
					   .append("<button  id='delete-"+data.id + "'>删除</button></td>");
					 
					
					 $tr.find("#save-"+data.id).click(function(){
						 
					});
					$tr.find("#delete-"+data.id).click(function(){
						
					}); 
					
					$("#tableNewPlan").append($tr); 
					
					
				}else{
					alert("创建失败");
				}
			});
			return false;
		});
		
	});
	</script>
</head>

<body class="main" >
	<span class="page_title">制定计划</span>
	<div class="button_bar">
		<button class="common_button" id="execute" 
			onclick="window.location.href='${ctp }/plan/execution?id=${chance.id}'">
			执行计划
		</button>
		<button class="common_button" onclick="javascript:history.go(-1);">
			返回
		</button>
	</div>
	
		<form action="${ctp }/plan/make" method="post">
		<table  class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					编号
				</th>

				<td>
					${chance.id}
				</td>
				<th>
					机会来源
				</th>

				<td>
					${chance.source}
				</td>
			</tr>
			<tr>
				<th>
					客户名称
				</th>
				<td>
					${chance.custName}
				</td>
				<th>
					成功机率（%）
				</th>

				<td>
					${chance.rate}
				</td>
			</tr>
			<tr>
				<th>
					概要
				</th>
				<td colspan="3">
					${chance.title}
				</td>
			</tr>
			<tr>
				<th>
					联系人
				</th>

				<td>
					${chance.contact}
				</td>
				<th>
					联系人电话
				</th>

				<td>
					${chance.contactTel}
				</td>
			</tr>
			<tr>
				<th>
					机会描述
				</th>
				<td colspan="3">
					${chance.description}
				</td>
			</tr>
			<tr>
				<th>
					创建人
				</th>
				<td>
					${chance.createBy.name}
				</td>
				<th>
					创建时间
				</th>
				<td>
					<fmt:formatDate value="${chance.createDate }" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<th>
					指派给
				</th>
				<td>
					${chance.designee.name}
				</td>

			</tr>
		</table>

		<br />
		
		<table id="tableNewPlan" class="data_list_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th width="200px">
					日期
				</th>
				<th>
					计划项
				</th>
			</tr>
			<!--  对计划的执行状态进行判断，已经执行的计划只能读，不能修改-->
			<c:if test="${empty chance.salesPlans }">
				<tr>
					<td>目前没有执行过计划</td>
				</tr>
			</c:if>
			<c:if test="${not empty chance.salesPlans }">
				<c:forEach items="${chance.salesPlans }" var="plan">
					<tr id="plan-${plan.id}">
						<td class="list_data_text">
							<fmt:formatDate value="${plan.date }" pattern="yyyy-MM-dd"/>
							&nbsp;
						</td>
						<td class="list_data_ltext">
							<c:if test="${plan.result == null }">
								<input type="text" size="50"
									value="${plan.todo}" id="todo-${plan.id}"/>
								<%-- <button class="common_button" id="save-${plan.id}"> --%>
								<button class="common_button" id="save-${plan.id}">
									保存
								</button>
								<input type="hidden" id="${plan.id }"/>
								<button class="common_button" id="delete-${plan.id}">
									删除
								</button>
							</c:if>
							<c:if test="${plan.result != null }">
								<input type="text" size="50"
									value="${plan.todo}" readonly="readonly"/>
								<input type="text" size="50"
									value="${plan.result}" readonly="readonly"/>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<div class="button_bar">
			<!-- <button class="common_button" onclick="document.forms[0].submit();"> -->
			<button class="common_button" id="newPlan" value="${chance.id }">
				新建
			</button>
		</div>
		<input type="hidden" name="chance.id" value="${chance.id}" />
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					日期
					<br />
					(格式: yyyy-mm-dd)
				</th>
				<td>
					<input type="text" name="date" id="date" />
					&nbsp;
				</td>
				<th>
					计划项
				</th>
				<td>
					<input type="text" name="todo" size="50" id="todo" />
					&nbsp;
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
