<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../include/header.jsp"%>
<div class="row"> 
 	<div class="col-lg-12"> 
 		<h3 class="page-header">게시판 </h3> 
 	</div> 
 </div> 

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					Board
					<button id='regBtn' type="button" class="btn btn-xs pull-right">새글작성</button>
				</div>


				<!-- /.panel-heading -->
				<div class="panel-body">
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
							</tr>
						</thead>
						<c:forEach items="${list}" var="board">
							<tr>
								<td><c:out value="${board.bno}" /></td>
								<td><a href='/board/get?bno=<c:out value="${board.bno}"/>'>
								<c:out value="${board.title}"/></a></td>
								<td><c:out value="${board.writer}" /></td>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:MM:SS" value="${board.regdate}" /></td>
							</tr>
						</c:forEach>
					</table>
					
				</div>
			</div>
		</div>
	</div>
	
<script type="text/javascript">
$(document).ready(function(){
	$(".move").on("click",function(e){
		e.preventDefault();
		actionForm.append("<input type='hidden' name='bno' value='"+$(this).attr("href")+"'>");
		actionForm.attr("action","/board/get");
		actionForm.submit();
	});
});

// 글작성페이지이동 
$(document).on('click', '#regBtn', function(e){
	e.preventDefault();
	location.href="${pageContext.request.contextPath}/board/register";
});



</script>
<%@include file="../include/footer.jsp"%>