<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>销售机会管理</title>
	<script type="text/javascript">
		$(function(){
			
		  /*  开发成功 */	
		   $("img[id^='finish-']").click(function(){
			   var url = $(this).attr("onclick");
				$("#hiddenForm").attr("action", url);
				$("#_method").val("PUT");
				$("#hiddenForm").submit();
				alert(url);
				return false;
		   });
		   
		   /* 查看 */
		   $("img[id^='query-']").click(function(){
			  	 var url = $(this).attr("onclick");
				$("#hiddenForm").attr("action", url);
				$("#_method").val("GET");
				$("#hiddenForm").submit();
				alert(url);
				return false;
		   });
		   
		});
			
	</script>
</head>

<body class="main">

	<form action="" id="hiddenForm" method="POST">
		<input type="hidden" name="_method" id="_method"/>
	</form>

	<form id="command" action="${ctp}/chance/list2" method="post">
		<div class="page_title">
			销售机会管理
		</div>
		<div class="button_bar">
			<button class="common_button" id="new">
				新建
			</button>
			<button class="common_button" onclick="document.forms[1].submit();">
				查询
			</button>
		</div>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th class="input_title">
					客户名称
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_custName" />
				</td>
				<th class="input_title">
					概要
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_title" />
				</td>
				<th class="input_title">
					联系人
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_contact" />
				</td>
			</tr>
		</table>
		<!-- 列表数据 -->
		<br />
		
		<c:if test="${empty page.content }">
			没有数据
		</c:if>
		
		<c:if test="${not empty page.content }">
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>
						编号
					</th>
					<th>
						客户名称
					</th>
					<th>
						概要
					</th>
					<th>
						联系人
					</th>
					<th>
						联系人电话
					</th>
					<th>
						创建时间
					</th>
					<th>
						状态
					</th>
					<th>
						操作
					</th>
				</tr>
				<c:forEach items="${page.content }" var="chance">
					<tr>
						<td class="list_data_number">${chance.id }</td>
						<td class="list_data_text">${chance.custName }</td>
						<td class="list_data_text">${chance.title }</td>
						<td class="list_data_text">${chance.contact }</td>
						<td class="list_data_text">${chance.contactTel }</td>
						<td class="list_data_text">
							<fmt:formatDate value="${chance.createDate }" pattern="yyyy-MM-dd"/>
						</td>
						<td class="list_data_text">
							<script type="text/javascript">
								switch('${chance.status}')
								{
									case '2':
										document.write('开发中');
										break;
									case '3':
										document.write('开发成功');
										break;
									case '4':
									    document.write('开发失败');
									   	break;
								}
							</script>
						</td>
						<td class="list_data_op">
							<c:if test="${chance.status==2 }">
								<img onclick="window.location.href='${ctp }/plan/make?id=${chance.id}'"
									title="制定计划" src="${ctp }/static/images/bt_plan.gif" class="op_button" />
								<img onclick="${ctp }/plan/execution/${chance.id}"
									title="执行计划" src="${ctp }/static/images/bt_feedback.gif" class="op_button" />
								<img onclick="${ctp }/plan/chance/finish/${chance.id}" id="finish-${chance.id }"
									title="开发成功" src="${ctp }/static/images/bt_yes.gif" class="op_button" />
							</c:if>
							<c:if test='${chance.status!=2}'>
								<img onclick="${ctp }/plan/chance/detail/${chance.id}" id="query-${chance.id}"
									title="查看" src="${ctp }/static/images/bt_detail.gif" class="op_button" />
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
			<tags:page page="${page }" queryString="${queryString }" list="plan/chance/list"></tags:page>
		</c:if>
	</form>
</body>
</html>
