<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hanjae
  Date: 2023/09/08
  Time: 4:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>errorPage</title>
</head>
<body>
<h4><c:out value="${exception.getMessage()}"/></h4>
<ul>
    <c:forEach var="stack" items="${exception.getStackTrace()}">
        <li><c:out value="${stack}"/></li>
    </c:forEach>
</ul>
</body>
</html>
