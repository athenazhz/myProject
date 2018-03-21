<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		init(1);
	})
	function init(page){
		var cId=$("#cId").val();
		var iName=$("#iName").val();
		var minPrice=$("#minPrice").val();
		var maxPrice=$("#maxPrice").val();
		var state=$("#state").val();
		$.ajax({
			type:'post',
			url:'${pageContext.request.contextPath}/ItemsController/getList',
			data:{'page':page,'cId':cId,'iName':iName,'minPrice':minPrice,'maxPrice':maxPrice,'state':state},
			dataType:'json',
			success:function(data){
				$("#cId option").not("#cId option:first").remove();
				$(data.kList).each(function(index,items){
					$("#cId").append("<option value='"+items.kId+"'>"+items.kName+"</option>")
				})
				$("#cId").val(data.cId);
				$("table tr").not("table tr:first").not("table tr:last").remove();
				$(data.pageInfo.list).each(function(index,items){
					var state="已出库";
					if(items.state==2){
						state="未出库";
					}
					var tr="<tr>"+
							   	"<td><input type='checkbox' name='check' value='"+items.iId+"'></td>"+
							   	"<td>"+(index+1)+"</td>"+
							   	"<td>"+items.iName+"</td>"+
							   	"<td>"+items.inTime+"</td>"+
							   	"<td>"+items.outTime+"</td>"+
							   	"<td>"+items.iPrice+"</td>"+
							   	"<td>"+items.kinds.kName+"</td>"+
							   	"<td>"+state+"</td>"+
							   	"<td>"+
							   		"<a href='#' class='del' value='"+items.iId+"'>删除</a>"+
							   		"<a href='#' class='detail' value='"+items.iId+"'>修改</a>"+
							   	"</td>"+
						   "</tr>";
					$("table tr:last").before(tr);		   
				})
				var pageNum=data.pageInfo.pageNum;
				var pages=data.pageInfo.pages;
				var total=data.pageInfo.total;
				$("#label1").text("共"+total+"条数据");
				$("#label2").text(pageNum+"/"+pages);
				$("#firstPage").attr("href","javascript:to_page(1)");
				$("#prePage").attr("href","javascript:to_page("+(pageNum-1)+")");
				$("#nextPage").attr("href","javascript:to_page("+(pageNum+1)+")");
				$("#lastPage").attr("href","javascript:to_page("+pages+")");
			},error:function(){
				alert('错了');
			}
		})
	}
	function search(){
		init(1);
	}
	function to_page(page){
		init(page);
	}
	function jump(){
		var page=$("#pageNum").val();
		init(page);
	}
	$(document).on("click",".detail",function(){
		var iId=$(this).attr("value");
		window.location.href='${pageContext.request.contextPath}/ItemsController/switchDetail?iId='+iId;
	})
	$(document).on("click",".del",function(){
		var iId=$(this).attr("value");
		window.location.href='${pageContext.request.contextPath}/ItemsController/del?iId='+iId;
	})
	function pdel(){
		var arr=new Array();
		var length=$("input[name='check']:checked").length;
		if(length==0){
			alert("未选中");
		}else{
			$("input[name='check']:checked").each(function(index,items){
				var id=$(this).val();
				arr.push(id);
			})
			$.ajax({
					type:'post',
					url:'${pageContext.request.contextPath}/ItemsController/del',
					data:{'iId':arr.toString()},
					success:function(){
						alert(arr+'删除成功');
						init(1);
					},error:function(){
						alert('cuole ');
					}
			})
		}
	}
</script>
<style type="text/css">
	table,tr,td{
		border:1px red solid;
		border-collapse: collapse;
		margin:0 auto;
	}
	td{
		text-align: center;
		width:12.5%;
	}
</style>
<body>
	<h1>物品管理系统</h1>
	<a href="${pageContext.request.contextPath }/ItemsController/switchAdd">新增</a>
	<form action="#" method="post">
		种类<select id="cId">
			<option value="0">全部</option>
		</select>
		物品名称<input type="text" id="iName">
		价格区间<input type="text" id="minPrice">--<input type="text" id="maxPrice">
		状态<select id="state">
			<option value="0">全部</option>
			<option value="1">已出库</option>
			<option value="2">未出库</option>
		</select>
		<input type="button" value="搜索" onclick="search()">
	</form>
	<table>
		<tr>
			<td><input type="button" value="批量删除" onclick="pdel()"></td>
			<td>序号</td>
			<td>物品名称</td>
			<td>入库时间</td>
			<td>出库时间</td>
			<td>价格</td>
			<td>种类</td>
			<td>物品状态</td>
			<td>操作</td>
		</tr>
		<tr>
			<td colspan="9">
				<label id="label1"></label>
				<a href="#" id="firstPage">首页</a>
				<a href="#" id="prePage">上一页</a>
				<label id="label2"></label>
				<a href="#" id="nextPage">下一页</a>
				<a href="#" id="lastPage">尾页</a>
				<input type="text" id="pageNum">
				<input type="button" value="go" onclick="jump()">
			</td>
		</tr>
	</table>
</body>
</html>