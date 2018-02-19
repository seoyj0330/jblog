<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
</head>
<body>
	<div class="center-content">
		
		<!--  mainheader include -->
		<c:import url="/WEB-INF/views/includes/mainheader.jsp"></c:import>
 		
		<form class="join-form" id="join-form" method="post" action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label>
			<input type="text" name="userName"  value="" />
			
			<label class="block-label" for="id">아이디</label>
			<input type="text" name="id"  value="" />
			<input id="btn-checkid" type="button" value="id 중복체크">
			<div id= "checkMsg"></div>
			<img id="img-checkid" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">
			<p class="form-error">
			</p>

			<label class="block-label" for="password">패스워드</label>
			<input type="password" name="password"  value="" />

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form>
	</div>

</body>
<script type="text/javascript">
	$("#btn-checkid").on("click",function(){
		var id = $("[name=id]").val();
		console.log(id);
		
		$.ajax({
			
			//Controller에 보내는형
			url : "${pageContext.request.contextPath }/user/api/id",		
			type : "post",
			/*  contentType : "application/json",  */
			data : {id: id},			//앞은 글자(구별할 수 있는 key값), 뒤에는 실제 데이터 
			
			//Controller에서 받아올 형태
			dataType : "json",
			success : function(result){
				console.log(result);
				if(result==true) {
					$("#checkMsg").text("사용할 수 있는 아이디입니다.");
				} else {
					$("#checkMsg").text("사용 중인 아이디입니다.");
				}
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});

</script>

</html>