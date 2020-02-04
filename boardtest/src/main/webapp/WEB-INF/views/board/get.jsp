<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../include/header.jsp"%>
<!-- 상세보기 페이지 -->
 <div class="row"> 
 	<div class="col-lg-12"> 
 		<h3 class="page-header">게시판</h3> 
 	</div> 
 	<!-- /.col-lg-12 --> 
 </div> 
 <!-- /.row --> 
 <div class="row"> 
 	<div class="col-lg-12"> 
 		<div class="panel panel-default"> 
			<div class="panel-heading">상세보기</div> 
 			<!-- /.panel-heading --> 
 			<div class="panel-body"> 
 				<div class="form-group"> 
 					<label>글번호</label> <input class="form-control" name='bno' 
 						value='<c:out value="${board.bno}"/>' readonly="readonly"> 
 				</div> 
 				<div class="form-group"> 
 					<label>제목</label> <input class="form-control" name='title' 
 						value='<c:out value="${board.title}"/>' readonly="readonly"> 
 				</div> 
 				<div class="form-group"> 
 					<label>내용</label> 
 					<textarea class="form-control" rows="3" name='content' 
 						readonly="readonly"><c:out value="${board.content}" /></textarea> 
 				</div> 
 				<div class="form-group"> 
 					<label>작성자</label> <input class="form-control" name='writer' 
 						value='<c:out value="${board.writer}"/>' readonly="readonly"> 
 				</div> 
 
 				<button type=button id='btnList' class="btn btn-sm btn-primary" >목록으로</button>
 				<button type=button id='btnDelete' class="btn btn-sm btn-primary" >삭제</button> 
 
 				 
 			</div> 
 			<!--  end panel-body --> 
 		</div> 
 		<!--  end panel-body --> 
 	</div> 
 	<!-- end panel --> 
 </div> 
 <!-- /.row --> 
 <script>
 
	//목록으로 이동
	$(document).on('click', '#btnList', function(){
		location.href = "${pageContext.request.contextPath}/board/list";

	});
	
	//삭제 
	$(document).on('click', '#btnDelete', function(){
    var url = "${pageContext.request.contextPath}/board/deleteBoard";
    url = url + "?bno=" + ${board.bno};
		location.href = url;
	});

 </script>

<%@include file="../include/footer.jsp"%>