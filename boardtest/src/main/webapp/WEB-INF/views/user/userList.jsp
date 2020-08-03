<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../include/header.jsp"%>
<div class="row"> 
 	<div class="col-lg-12"> 
 		<h3 class="page-header">게시판</h3> 
 	</div> 
 </div> 

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
				</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>userid</th>
								<th>username</th>
							</tr>
						</thead>
						<c:forEach items="${user}" var="board">
							<tr>
								<td><c:out value="${board.userid}" /></td>
								<td><c:out value="${board.name}"/></td>
							</tr>
						</c:forEach>
					</table>
					
				</div>
			</div>
		</div>
	</div>
<%@include file="../include/footer.jsp"%>