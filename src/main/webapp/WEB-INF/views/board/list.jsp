<%@ page import="com.multi.spring2.board.domain.Board" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--    ${pageScope.list} --%>
<%--<% List<Board> list = (List<Board>) request.getAttribute("list"); %>--%>
<%--<c:set var="list" value="${requestScope.list}"/>--%>
<%--    ${sessionScope.list}  --%>
<%--    ${applicationScope.list}  --%>

<c:set var="list" value="${list}"/>
<!-- pageScope.list, requestScope.list, sessionScope.list, applicationScope.list -->

<html>
<head>
    <title>게시글 목록</title>
</head>
<body>

<h1>제시글 목록</h1>
<div class="search">
    <form>
        검색어 :
        <input type="text" name="word" placeholder="글번호 또는 제목 또는 작성자">
        <button>검색</button>
    </form>
</div>

<table border="1">
<tr><th>글번호</th>
    <th>제목</th>
<%--    <th>내용</th>--%>
    <th>작성자</th>
</tr>
<c:if test="${empty list}">
    <tr><td colspan="3">글이 없습니다</td></tr>
</c:if>
<c:choose>
    <c:when test="${empty list}">
        <tr><td colspan="3">글이 없습니다</td></tr>
    </c:when>
    <c:otherwise>
        <tr><td colspan="3">글이 있습니다</td></tr>
    </c:otherwise>
</c:choose>
<c:forEach var="board" items="${list}">
<tr>
<%--    <td><%=board.getBoardNo()%></td>--%>
    <td><a href="./detail?boardNo=${board.boardNo}">
        <c:out value="${board.boardNo}"/>
        </a>
    </td>
    <td>${board.boardTitle}</td>
<%--    <td>${board.boardContent}</td>--%>
    <td>${board.boardWriter}</td>
</tr>
<%--  }
--%>
</c:forEach>
</table>
</body>
</html>
