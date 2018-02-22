<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
</head>
<body>

	<div id="container">
		
		<!--  header include -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

		
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath }/${authUser.id}/admin/basic">기본설정</a></li>
					<li class="selected"><a href="${pageContext.request.contextPath }/${authUser.id}/admin/category">카테고리</a></li>
					<li><a href="${pageContext.request.contextPath }/${authUser.id}/admin/write">글작성</a></li>
				</ul>
				
		      	<table class="admin-cat">
		      		<tr id= "cate-area">
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>		  
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input id="addBtn" type="button" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
			</div>
		</div>

		<!--  footer include -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div>
</body>

<script type="text/javascript">
$(document).ready(function(){
	fetchlist();
});

$(".admin-cat").on("click",".delBtn", function(){
	var cateNo = $(this).data("no");
	
	$.ajax({
		//Controller에 보내는형
		url : "${pageContext.request.contextPath }/admin/delete",		
		type : "post",
		data : {cateNo : cateNo},			
		
		//Controller 받아올때
		dataType : "json",
		success : function(cateNo){
			console.log("제거");
			$(cateNo).remove();
			
			location.reload();
		},
		
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});

});

	

$("#addBtn").on("click", function(){
	console.log(addBtn);
	
	var name = $("[name=name]").val();
	var description = $("[name=desc]").val();
	
	var cateVo= {		
						userNo : '${sessionScope.authUser.userNo}',
						cateName : name,
						description : description
	}
	
	$.ajax({
		//Controller에 보내는형
		url : "${pageContext.request.contextPath }/admin/addcate",		
		type : "post",
		contentType : "application/json", 
		data : JSON.stringify(cateVo),			//메소드임 그래서 기능으로보고()

		//Controller 받아올때
		dataType : "json",
		success : function(cateVo){
			console.log(cateVo);
			render(cateVo, "down");
		},
		
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
		$("[name=name]").val(""); 
		$("[name=desc]").val(""); 

});

function fetchlist(){
	
	$.ajax({
		//Controller로 보낼때
		url : "${pageContext.request.contextPath }/admin/catelist",		
		type : "post",
		data : {userNo : '${sessionScope.authUser.userNo}'},			

		//Controller에서 받아올때
		dataType : "json",
		success : function(clist){
			console.log(clist);
			
			for(var i=0; i<clist.length; i++){
				render(clist[i], "down");
			}

		},
		
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
		
	});
}

function render(CateVo, updown){
	
	var str ="";
		str +=" 		<tr>";
		str +="            <td>[" + CateVo.cateNo + "]</td>";
		str +=" 		   <td>" + CateVo.cateName +"</td>"	;
		str +=" 		   <td>" + "</td>";
		str +="			   <td>" + CateVo.description +"</td>";
		str +=" 		   <td><img src=${pageContext.request.contextPath}/assets/images/delete.jpg class='delBtn' data-no="+ CateVo.cateNo +"></td>";
		str +="         </tr>";


	if(updown =="up"){
		$("#cate-area").prepend(str);
		
	} else if(updown =="down"){
		$("#cate-area").after(str);
		
	} else {
		console.log("오류");
	}
}

</script>
</html>