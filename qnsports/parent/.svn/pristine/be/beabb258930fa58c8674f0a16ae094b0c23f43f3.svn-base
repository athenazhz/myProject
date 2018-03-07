<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/console/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	
	<head>
		<title>qingniao-add</title>
		<script type="text/javascript">
		//图片上传
			function ImgUpload() {
				var opt = {
						url : "/upload/imgUploadToFastDFS.do",
						type : "post",
						dataType : "json",
						success : function(data) {
							$("#allimg").attr("src",data.url);
							$("#himg").val(data.path);
						}
				}
				$("#brandForm").ajaxSubmit(opt);
			}
			
		</script>
	</head>
	<body>
		<div class="box-positon">
			<div class="rpos">当前位置: 品牌管理 - 添加</div>
			<form class="ropt">
				<input type="submit" onclick="" value="返回列表" class="return-button"/>
			</form>
			<div class="clear"></div>
		</div>
		<div class="body-box" style="float:right">
			<form id="brandForm" action="/brand/add.do" method="post" enctype="multipart/form-data">
				<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
					<tbody>
						<tr>
							<td width="20%" class="pn-flabel pn-flabel-h">
								<span class="pn-frequired">*</span> 品牌名称:
							</td>
							<td width="80%" class="pn-fcontent">
								<input type="text" class="required" name="brandName" maxlength="100" />
							</td>
						</tr>
						<tr>
							<td width="20%" class="pn-flabel pn-flabel-h">
								<span class="pn-frequired">*</span> 品牌LOGO:
							</td>
							<td width="80%" class="pn-fcontent">
								注:该尺寸图片必须为90x150。
							</td>
						</tr>
						<tr>
							<td width="20%" class="pn-flabel pn-flabel-h"></td>
							<td width="80%" class="pn-fcontent">
								<img width="100" height="100" id="allimg" />
								<input type="file" name="picfile" onchange="ImgUpload()" />
								<input type="hidden" name="brandLogo" id="himg" />	<!-- 回显图片的路径 -->
							</td>
						</tr>
						<tr>
							<td width="20%" class="pn-flabel pn-flabel-h">
								品牌描述:</td>
							<td width="80%" class="pn-fcontent">
								<input type="text" class="required" name="brandDescription" maxlength="80" size="60" />
							</td>
						</tr>

						<tr>
							<td width="20%" class="pn-flabel pn-flabel-h">
								官方网站:</td>
							<td width="80%" class="pn-fcontent">
								<input type="text" class="required" name="brandUrl" maxlength="80" size="60" />
							</td>
						</tr>
						<tr>
							<td width="20%" class="pn-flabel pn-flabel-h">
								品牌的状态:</td>
							<td width="80%" class="pn-fcontent">
								<input type="radio" name="brandStatus" value="1" checked="checked" />在售
								<input type="radio" name="brandStatus" value="2" />停售
							</td>
						</tr>
					</tbody>
					<tbody>
						<tr>
							<td class="pn-fbutton" colspan="2">
								<input type="submit" class="submit" value="提交" /> &nbsp; <input type="reset" class="submit" value="重置" />
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</body>

</html>