<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSTL Page</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css"/>">
</head>
<body>
<h1>Hello World!</h1>
<h2>This is simple JSTL page!</h2>
Go to: <a href="<c:url value="/" />">JSP Page</a> |
<br/>
<br/>
<c:forEach var="i" begin="1" end="10" step="1">
    <c:out value="${i}" />,
</c:forEach>
</body>
</html>
