<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/resources/style.css" />
    <title>JSP Page</title>
</head>
<body>
<h1>Hello World!</h1>

<h2>This is simple JSP page!</h2>
Go to: <a href="/jstl">JSTL page</a>
<br/>
<br/>
<%
    for(int i=1;i<=10;i++)
    {
%>
<%=i%>,
<%
    }
%>
<br/>
</body>
</html>