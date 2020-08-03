 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../include/header.jsp"%>
<!-- 상세보기 페이지 -->
 <div class="row"> 
<div class="col-lg-12"> 
 		<h3 class="page-header"></h3> 
 	</div> 
 	<!-- /.col-lg-12 --> 
 </div>
 <!-- /.row --> 
<% String name=(String)session.getAttribute("name"); %>
 <c:set var="writer" value="${board.writer}"/>
 <c:set var="name" value="${sessionScope.name }"/>
 <div class="row"> 
 	<div class="col-lg-12"> 
 		<div class="panel panel-default"> 
			<div class="panel-heading"><h1><c:out value="${board.title}"/></h1></div> 
 			<!-- /.panel-heading --> 
 			<div class="panel-body"> 
 				<div> <!-- 작성자 출력  -->
 					<label>글번호 : </label> <c:out value="${board.bno}"/>   <c:out value="${name }"/>
 					<div></div>
 					 <label>작성자 : </label> <c:out value="${writer}"/>
				</div> 
 				<div class="form-group">
 				<label>내용</label> 
 					<textarea class="form-control" rows="3" name='content' 
 						readonly="readonly"><c:out value="${board.content}" /></textarea> 
 				</div>
 				
 				<!-- 글쓴이인 경우 수정버튼 활성화  -->
				<c:choose> 
					<c:when test='${ name eq writer }'>
						<button data-oper='update' class="btn btn-default"
							onclick="location.href='/board/update?bno=<c:out value="${board.bno }"/>'">수정</button>
						<button data-oper='list' class="btn btn-default">메인화면</button>
					</c:when>
					<c:otherwise>
						<button data-oper='list' class="btn btn-default">메인화면</button>
					</c:otherwise>
				</c:choose>
				<form id='operForm' action="/board/update" method="get">
					<input type='hidden' id='bno' name='bno'
						value='<c:out value="${board.bno}"/>'>
				</form>

			</div> 
 			<!--  end panel-body --> 
 		</div> 
 		<!--  end panel-body --> 
 	</div> 
 	<!-- end panel --> 
 </div> 
 <!-- /.row --> 
 
 
 <script type="text/javascript">
	$(document).ready(function() {
		var operForm = $("#operForm");
		$("button[data-oper='update']").on("click", function(e) {
			operForm.attr("action", "/board/update").submit();
		});
		$("button[data-oper='list']").on("click", function(e) {
			operForm.find("#bno").remove();
			operForm.attr("action", "/board/list")
			operForm.submit();
		});
	});
</script>

<%@include file="../include/footer.jsp"%>