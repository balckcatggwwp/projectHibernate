<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>新增，修改結果</title>
            <style>
                input {
                    margin-bottom: 15px;
                }
            </style>
        </head>

        <body>
            <div class="container">
            <c:forEach var="Info" items="${Info}">
                <h2>電影資料</h2>

                <label>電影ID:</label>
                <input type="text" name="id" readonly value="${Info.id}"><br>

                <label>電影名稱:</label>
                <input type="text" name="name" readonly value="${Info.name}"><br>

                <label>分級:</label>
                <input type="text" name="rating" readonly value="${Info.rating}"><br>

                <label>類型:</label>
                <input type="text" name="genre" readonly value="${Info.genre}"><br>

                <label>導演:</label>
                <input type="text" name="director" readonly value="${Info.director}"><br>

                <label>演員:</label>
                <input type="text" name="actor" readonly value="${Info.actor}"><br>

                <label>片長(分):</label>
                <input type="text" name="runtime" readonly value="${Info.runtime}"><br>

                <label>上映日期:</label>
                <input type="text" name="releaseDate" readonly value="${Info.releaseDate}"><br>

                <label>上映狀態:</label>
                <input type="text" name="status" readonly value="${Info.status}"><br>

                <label>電影介紹:</label>
                <input type="text" name="description" readonly value="${Info.description}"><br>

                <label>圖片(路徑):</label>
                <input type="text" name="image" readonly value="${Info.image}"><br>

                <label>預告(路徑):</label>
                <input type="text" name="trailer" readonly value="${Info.trailer}"><br>
                
        		</c:forEach>

                <a href="http://localhost:8080/project/han/JSP-zh/select.html">回查詢首頁</a>

            </div>
        </body>

        </html>