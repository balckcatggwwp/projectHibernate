<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>公告管理</title>
<style>
.btn {
	margin-top: 10px;
	padding: 5px 10px;
	background-color: #007bff;
	color: white;
	border: none;
	cursor: pointer;
	text-align: center;
	border-radius: 5px;
}

.btn:hover {
	background-color: #0056b3;
}

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

.search-box {
	text-align: center;
	margin-bottom: 20px;
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

.news-image {
	max-width: 300px;
	max-height: 200px;
	width: auto;
	height: auto;
}

li {
	list-style: none;
	display: flex;
	align-items: center;
	gap: 10px;
	font-size: 18px;
	color: #FFD700;
	background-color: #222;
	width: 80%;
	height: 60px;
	margin: 5px 0;
	margin-left: 10%;
	border-radius: 5px;
	cursor: pointer;
	transition: all 0.3s ease;
	justify-content: center;
}

li:hover {
	background-color: #FFCC00;
	color: #000;
	transform: scale(1.1);
}

li:active {
	position: relative;
	top: 2px;
	background-color: #B22222;
	color: #FFF;
}

figure {
	float: left;
	background-color: #000;
	padding: 20px 0;
	font-size: 100px;
	color: #FFD700;
	width: 10%;
	height: 1000x;
	text-align: center;
}

ul {
	display: flex;
	flex-direction: column;
	gap: 10px;
	padding-left: 0;
}

li a {
	display: block;
	width: 100%;
	padding: 10px;
	text-decoration: none;
	color: black;
}

li a:active {
	position: relative;
	top: 2px;
	background-color: #B22222;
	color: white;
}
</style>
</head>

<body>
	<div class="container">
		<h1>公告管理</h1>
		<div>
			<a href="http://localhost:8080/project/backEnd.view/HomePage.html">會員</a> <a
				href="http://localhost:8080/project/findticketAll">訂票</a> <a
				href="http://localhost:8080/project/JSP-zh/select.html">電影介紹</a> <a
				href="http://localhost:8080/project/hw2/menupage.html">訂餐</a>
		</div>
		<!-- 搜尋功能 -->
		<div class="search-box">
			<form action="SearchNews" method="get">
				<input type="text" name="searchQuery" placeholder="搜尋公告" required>
				<button type="submit" class="btn">搜尋</button>
			</form>
		</div>

		<!-- 顯示公告列表 -->
		<c:if test="${not empty news}">
			<c:forEach var="news" items="${news}">
				<div class="news-item">
					<a href="movieNews/news-detail.jsp?id=${news.id}"
						class="news-title">${news.title}</a>
					<div class="news-meta">
						發佈日期：
						<fmt:formatDate value="${news.publishDate}"
							pattern="yyyy-MM-dd HH:mm:ss" />
					</div>
					<div class="news-summary">${news.summary}</div>
					<c:if test="${not empty news.imageUrl}">
						<img src="${pageContext.request.contextPath}${news.imageUrl}"
							alt="公告圖片" class="news-image" />
					</c:if>
					<br>
					<form action="movieNews/editNews.jsp" method="get"
						style="display: inline;">
						<input type="hidden" name="id" value="${news.id}">
						<button type="submit" class="btn"
							onclick="return confirm('確定要修改這則公告嗎？');">修改</button>
					</form>
					<form action="DeleteNews" method="post" style="display: inline;">
						<input type="hidden" name="id" value="${news.id}">
						<button type="submit" class="btn"
							onclick="return confirm('確定要刪除這則公告嗎？');">刪除</button>
					</form>
				</div>
			</c:forEach>
		</c:if>

		<!-- 無搜尋結果時顯示訊息 -->
		<c:if test="${empty news}">
			<p>沒有符合搜尋條件的公告。</p>
		</c:if>

		<!-- 新增公告按鈕 -->
		<form action="/project/movieNews/addnews.jsp" method="post">
			<button type="submit" class="btn">新增公告</button>
		</form>
		<br> <a href="http://localhost:8080/project/GetAllNews">返回公告列表</a>
	</div>
</body>
</html>
