<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Member CRUD Form</title>
    <link rel="stylesheet" href="resources/css/out.css">
</head>
<body>
<div class="container">
    <h2>Board CRUD Form</h2>

    <!-- Board Table -->
    <table>
        <thead>
        <tr>
            <th>항목명</th>
            <th>입력폼</th>
        </tr>
        </thead>
        <tbody>
        <!-- Sample data row for demonstration -->
        <tr>
            <td class="center">게시판글쓰기</td>
            <td>
                <!-- Board Insert Form -->
                <form action="./board/write" method="post">
                    <label for="title">title</label>
                    <input type="text" id="title" name="boardTitle" required>
                    <label for="content">content</label>
                    <input type="text" id="content" name="boardContent" required>
                    <label for="writer">Writer</label>
                    <input type="text" id="writer" name="boardWriter" required>
                    <button type="submit">Add Board</button>
                </form>
            </td>
        </tr>
        <tr>
            <td class="center">게시판 글 삭제</td>
            <td>
                <!-- Board Delete Form -->
                <form action="delete" method="get">
                    <input type="text" name="no">
                    <button type="submit" class="btn-edit">Delete Board</button>
                </form>
            </td>
        </tr>
        <tr>
            <td class="center">게시판 글 수정</td>
            <td>
                <!-- Board Update Form -->
                <form action="update" method="post">
                    <label for="no">Update Board by NO</label>
                    <input type="text" id="no" name="no" required>
                    <label for="content">New Content</label>
                    <input type="text" id="content" name="boardContent">
                    <button type="submit">Update Board</button>
                </form>
            </td>
        </tr>
        <tr>
            <td class="center">게시판 정보 검색</td>
            <td>
                <!-- Board Search Form -->
                <form action="one" method="get">
                    <label for="no">Search Board by NO</label>
                    <input type="text" name="no"required>
                    <button type="submit">Search</button>
                </form>
            </td>
        </tr>
        <tr>
            <td class="center">게시판 전체 검색</td>
            <td>
                <!-- Board All -->
                <a href="./board/list">
                    <button>Search All</button>
                </a>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
