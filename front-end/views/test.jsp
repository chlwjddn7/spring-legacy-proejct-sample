<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List" %>
<%@ page import="kr.co.edu.common.vo.Test" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		Hello World Test.jsp
		<c:forEach var="item" items="${testList}" varStatus="status">
			<p>${status.index} : <c:out value="${item.testTitle}" /></p>
		</c:forEach>
		<%
			List<Test> testList = (List<Test>) request.getAttribute("testList");
			for (int i = 0; i < testList.size(); i++) {
		%>
			<p><%=i%> : <%=testList.get(i).getTestTitle()%></p>
		<%
			}
		%>
	</body>
	
</html>