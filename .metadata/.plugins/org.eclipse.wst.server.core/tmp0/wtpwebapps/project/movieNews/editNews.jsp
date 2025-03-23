<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.naming.*, javax.sql.DataSource" %>
<%@ page import="com.movieNews.utils.Utils" %>

<%
    String idParam = request.getParameter("id");
    if (idParam == null || idParam.trim().isEmpty()) {
        out.println("公告ID無效！");
        return;
    }

    int id = Integer.parseInt(idParam);
    Connection connection = Utils.getConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String sql = "SELECT * FROM news WHERE id = ?";
    
    try {
        // 獲取資料庫連線
      

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String title = resultSet.getString("title");
            String summary = resultSet.getString("summary");
            String content = resultSet.getString("content");
            String imageUrl = resultSet.getString("image_url");

            request.setAttribute("title", title);
            request.setAttribute("summary", summary);
            request.setAttribute("content", content);
            request.setAttribute("imageUrl", imageUrl);
        } else {
            out.println("公告未找到！");
        }
    } catch (Exception e) {
        e.printStackTrace();
        out.println("資料庫錯誤！");
    } finally {
    	Utils.closeResource(connection, preparedStatement, resultSet);
    }
%>

<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>編輯公告</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            width: 90%;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"], textarea, input[type="file"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        textarea {
            resize: none;
            height: 120px;
        }

        .btn {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            font-size: 16px;
        }

        .btn:hover {
            background-color: #0056b3;
        }

        .image-preview {
            width: 100%;
            max-height: 200px;
            object-fit: cover;
            border-radius: 5px;
            display: none;
            margin-top: 10px;
        }

        .error-message {
            color: red;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>編輯公告</h1>

        <!-- 顯示錯誤訊息 -->
        <c:if test="${not empty error}">
            <div class="error-message">
                <p>${error}</p>
            </div>
        </c:if>

        <!-- 編輯公告表單 -->
        <form action="../UpdateNews" method="post" enctype="multipart/form-data">
            <!-- 隱藏公告ID -->
            <input type="hidden" name="id" value="<%= id %>"/>

            <div class="form-group">
                <label for="title">標題：</label>
                <input type="text" name="title" value="${title}" required>
            </div>

            <div class="form-group">
                <label for="summary">摘要：</label>
                <input type="text" name="summary" value="${summary}" required>
            </div>

            <div class="form-group">
                <label for="content">內容：</label>
                <textarea name="content" required>${content}</textarea>
            </div>

            <div class="form-group">
                <label for="image">圖片：</label>
                <input type="file" name="image" id="imageInput" accept="image/*">
                <img id="imagePreview" class="image-preview" src="${imageUrl}" alt="公告圖片">
            </div>

            <button type="submit" class="btn">更新公告</button>
        </form>
    </div>

    <script>
        document.getElementById("imageInput").addEventListener("change", function(event) {
            const file = event.target.files[0];
            if (file) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    const preview = document.getElementById("imagePreview");
                    preview.src = e.target.result;
                    preview.style.display = "block";
                };
                reader.readAsDataURL(file);
            }
        });
    </script>
</body>
</html>