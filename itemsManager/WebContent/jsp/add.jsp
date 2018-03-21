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
	function upload(){
		var formData=new FormData;
		formData.append("newFile",$("#newFile")[0].files[0]);
		$.ajax({
			type:'post',
			url:'${pageContext.request.contextPath}/ItemsController/upload',
			data:formData,
			dataType:'json',
			processData:false,
			contentType:false,
			success:function(data){
				$("#image").attr("src","${pageContext.request.contextPath}"+data.url);
				$("#imageLocation").val(data.url);
			},error:function(){
				alert('你错了');
			}
		})
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
	<form action="${pageContext.request.contextPath }/ItemsController/add" method="post">
		<table>
			<tr>
				<td>物品名称</td>
				<td><input type="text" name="iName"></td>
			</tr>
			<tr>
				<td>入库时间</td>
				<td><input type="date" name="inTime"></td>
			</tr>
			<tr>
				<td>出库时间</td>
				<td><input type="date" name="outTime"></td>
			</tr>
			<tr>
				<td>价格</td>
				<td><input type="text" name="iPrice"></td>
			</tr>
			<tr>
				<td>种类</td>
				<td>
					<select name="cId">
						<c:forEach items="${kList }" var="kinds">
							<option value="${kinds.kId }">${kinds.kName }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>物品状态</td>
				<td>
					<input type="radio" name="state" value="1">已出库
					<input type="radio" name="state" value="2">未出库
				</td>
			</tr>
			<tr>
				<td>上传图片</td>
				<td>
					<img alt="图片预览" src="" id="image">
					<input type="file" id="newFile" onchange="upload()" value="上传">
					<input type="text" id="imageLocation" name="url">
				</td>
			</tr>
			<tr>
				<td>操作</td>
				<td>
					<input type="submit" value="添加">
					<input type="button" value="返回" onclick="window.history.go(-1)">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>