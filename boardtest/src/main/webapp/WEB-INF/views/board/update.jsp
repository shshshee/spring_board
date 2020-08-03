<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../include/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h3 class="page-header">게시물 수정</h3>
	</div>
</div>


<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<!-- /.panel-heading -->
			<div class="panel-body">

				<form role="form" action="/board/update" method="post">
					<div class="form-group">
						<label>글번호</label> <input class="form-control" name='bno'
							value='<c:out value="${board.bno }"/>' readonly="readonly">
					</div>
					<div class="form-group">
						<label>제목</label> <input class="form-control" name="title" 
						value='<c:out value="${board.title }"/>'>
					</div>
					<div class="form-group">
						<label>내용</label>
						<textarea class="form-control" rows="5" name="content" <c:out
								value="${board.content}" />></textarea>
					</div>
					<div class="form-group">
						<label>작성자</label> <input class="form-control" name='writer'
							value='<c:out value="${board.writer}"/>' readonly="readonly">
					</div>
						<div class="form-group">
						<label>날짜</label> <input class="form-control" name='regdate'
							value='<fmt:formatDate pattern = "yyyy-MM-dd HH:MM:SS" value = "${board.regdate}" />'
							readonly="readonly">
					</div>
					<button type="submit"  data-oper="update" class="btn btn-default">수정</button>
					<button type="submit"  data-oper="delete" class="btn btn-default">삭제</button>
					<!-- <button type="submit" data-oper="gotolist" class="btn btn-default">뒤로가기</button> -->
				
				</form>

			</div>
			<!-- end panel body -->
		</div>
	</div>
</div>
	
<script type="text/javascript">

$(document).ready(function() {
	var formobj = $("form");
	
	$("button").on("click",function(e){
		e.preventDefault();
		var operation = $(this).data("oper");
		console.log(operation);
		if(operation ==="delete"){
			console.log("click delete----------");
			formobj.attr("action", "/board/deleteBoard");
		}
/* 		else if(operation ==="gotolist"){
			console.log("click list----------");
			formobj.attr("action","/board/list");
		} */
		formobj.submit();
	});
});
</script>
	
	

<%@include file="../include/footer.jsp"%>