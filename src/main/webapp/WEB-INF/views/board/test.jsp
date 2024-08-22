<%@ page import="java.util.Enumeration" %><%--
  Created by IntelliJ IDEA.
  User: student
  Date: 2024-08-14
  Time: 오전 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Enumeration<String> en =request.getAttributeNames();
    while (en.hasMoreElements()) {
        out.print(en.nextElement() + "<br>");

    }
%>
<hr>
<%=request.getAttribute("board")%>
</body>
</html>
