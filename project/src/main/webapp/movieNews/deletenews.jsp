<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>電影公告列表</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            max-width: 1200px;
            margin: 50px auto;
            background-color: #ffffff;
            padding: 20px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #333;
            text-align: center;
        }
        .news-item {
            border-bottom: 1px solid #ddd;
            padding: 20px;
            margin-bottom: 20px;
        }
        .news-item:last-child {
            border-bottom: none;
        }
        .news-title {
            font-size: 1.5em;
            color: #007bff;
            text-decoration: none;
        }
        .news-title:hover {
            color: #0056b3;
        }
        .news-meta {
            font-size: 0.9em;
            color: #888;
        }
        .news-summary {
            margin-top: 10px;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>電影公告</h1>
    
    <!-- 使用 JSTL 顯示公告列表 -->
    <c:forEach var="news" items="${news}">
        <div class="news-item">
            <a href="news-detail.jsp?id=${news.id}" class="news-title">${news.title}</a>
            <div class="news-meta">發佈日期：${news.publishDate}</div>
            <div class="news-summary">${news.summary}</div>
            
            <!-- 刪除按鈕 -->
        <form action="../DeleteNews" method="post" style="margin-top: 10px;">
            <input type="hidden" name="id" value="${news.id}">
            <button type="submit" onclick="return confirm('確定要刪除這則公告嗎？');">刪除</button>
        </form>
        </div>
    </c:forEach>

</div>

</body>
</html>
