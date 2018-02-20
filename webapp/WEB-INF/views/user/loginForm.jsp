<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jblog.css">
</head>
<body>
	<div class="center-content">
		
		<!--  mainheader include -->
		<c:import url="/WEB-INF/views/includes/mainheader.jsp"></c:import>

		
		<form class="login-form" method="post" action="${pageContext.request.contextPath }/user/login">
      		<label>아이디</label> 
      		<input type="text" name="id">
      		
      		<label>패스워드</label> 
      		<input type="text" name="password">
      		
      		<c:if test = "${param.result eq 'fail'}">			
					<P>로그인이 실패했습니다. 다시입력해주세요</P>
			</c:if>
			
      		<input type="submit" value="로그인">
		</form>
		
	</div>
	<div id="dialog-message" title="" style="display:none">
  		<p></p>
	</div>
</body>
</html>