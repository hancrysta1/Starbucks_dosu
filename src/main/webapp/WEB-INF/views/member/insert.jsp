<%@ page import="com.multi.spring2.member.domain.MemberVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>
회원 가입한 id는 <%= memberVO.getId()%> <br>
회원 가입한 pw는 <%= memberVO.getPw()%> <br>
회원 가입한 name은 <%= memberVO.getName()%> <br>
회원 가입한 tel은 <%= memberVO.getTel()%> <br>

<hr color="red">
회원 가입한 id는 ${memberVO.id} <br> <%--memberVO.getId()--%>
회원 가입한 pw는 ${memberVO.pw} <br>
회원 가입한 name은 ${memberVO.name} <br>
회원 가입한 tel은 ${memberVO.tel} <br>

<%--<c:forEach items="${list}" var="memberVO">--%>

<%--</c:forEach>--%>

</body>
</html>
