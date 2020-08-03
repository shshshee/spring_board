
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../include/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h3 class="page-header">새글작성</h3>
	</div>
</div>
<% String name = (String)session.getAttribute("name");%>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">게시물 작성</div>
			<!-- /.panel-heading -->
			<div class="panel-body">

				<form name="form" id="form" role="form" action="${pageContext.request.contextPath}/board/saveBoard" method="post">
					<div class="form-group">
						<label>제목</label> <input class="form-control" name="title">
					</div>
					<div class="form-group">
						<label>내용</label>
						<textarea class="form-control" rows="5" name="content"></textarea>
					</div>
					<!-- 값을 hidden 값으로 넘겨줌  -->
					<input type=hidden id= "writer" name="writer" value="<%=name%>">
				</form>
					<button type="button" id="btnSave" class="btn btn-sm btn-primary">저장</button>
					<button type="button" id="btnList" class="btn btn-sm btn-primary">뒤로가기</button>
			</div>
			<!-- end panel body -->
		</div>
	</div>
</div>
	
<script>
//글저장
$(document).on('click', '#btnSave', function(e){ //저장 
	
	e.preventDefault();
	$("#form").submit();
	});
	
//뒤로가기 버튼 
$(document).on('click', '#btnList', function(e){
	e.preventDefault();
	location.href="${pageContext.request.contextPath}/board/list";
});
</script>

<%@include file="../include/footer.jsp"%>