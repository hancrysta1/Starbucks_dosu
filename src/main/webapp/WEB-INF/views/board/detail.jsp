<%@ page import="com.multi.spring2.board.domain.Board" %>
<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="board" value="${requestScope.board}"/>
<html>
<script>
    window.onload = ()=>{


    }
    modify = ()=>{
        const formObj = document.querySelector("form");
        formObj.action = "./modify"
        formObj.method = "post"
        formObj.submit()
        return false
    }
    remove = ()=>{
        const formObj = document.querySelector("form");
        formObj.action = "./remove"
        formObj.method = "get"
        formObj.submit()
        return false
    }
</script>
<style>
    input[name=boardNo]{
        border: none;
    }
    input[name=boardTitle], textarea {
        background-color: beige;
        border: none;
    }
    textarea{
        width: 50%;
        height: 100px;
        display: block;
        /*border: none;*/
        outline: none;
        resize: none;
    }
</style>
<body>
<h1>게시글 상세내용</h1>
<form>
글번호 : <input type="text" name="boardNo" value="${board.boardNo}" readonly><br>
글제목 : <input type="text" name="boardTitle" value="${board.boardTitle}"><br>
글내용 : <textarea name="boardContent">${board.boardContent}</textarea><br>
작성자 : ${board.boardWriter}
</form>
<br>
<button onclick="modify()">수정</button>
<button onclick="remove()">삭제</button>
</body>
</html>