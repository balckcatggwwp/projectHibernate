<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新增公告</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 50%;
            max-width: 800px;
            margin: 50px auto;
            background-color: #ffffff;
            padding: 20px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }
        h1 {
            color: #333;
            text-align: center;
        }
        label {
            font-size: 1.1em;
            display: block;
            margin: 10px 0 5px;
        }
        input[type="text"], textarea {
            width: 100%;
            padding: 8px;
            font-size: 1em;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 15px;
            font-size: 1em;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        input[type="file"] {
            margin-bottom: 15px;
        }
        .error {
            color: red;
            font-size: 1.1em;
            text-align: center;
        }
        .image-preview-container {
            text-align: center;
            margin-top: 10px;
        }
        .image-preview {
            display: none;
            width: 100%;
            max-height: 250px;
            object-fit: cover;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
        }
    </style>
    <script>
        function validateForm() {
            let title = document.getElementById("title").value.trim();
            let summary = document.getElementById("summary").value.trim();
            let content = document.getElementById("content").value.trim();

            if (title.length < 5) {
                alert("公告標題至少需要 5 個字");
                return false;
            }
            if (summary.length < 10) {
                alert("公告摘要至少需要 10 個字");
                return false;
            }
            if (content.length < 20) {
                alert("公告內容至少需要 20 個字");
                return false;
            }
            return true;
        }

        // 圖片預覽功能
        function previewImage(event) {
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
        }
    </script>
</head>
<body>

<div class="container">
    <h1>新增公告</h1>

    <!-- 顯示錯誤訊息 -->
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <!-- 公告新增表單 -->
    <form action="${pageContext.request.contextPath}/AddNews" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
        <label for="title">公告標題</label>
        <input type="text" id="title" name="title" required>

        <label for="summary">公告摘要</label>
        <textarea id="summary" name="summary" rows="4" required></textarea>

        <label for="content">公告內容</label>
        <textarea id="content" name="content" rows="6" required></textarea>

        <label for="image">公告圖片</label>
        <input type="file" id="image" name="image" accept="image/*" onchange="previewImage(event)">

        <!-- 圖片預覽區域 -->
        <div class="image-preview-container">
            <img id="imagePreview" class="image-preview" alt="圖片預覽">
        </div>

        <input type="submit" value="新增公告">
    </form>
    <a href="http://localhost:8080/Servlet/GetAllNews">返回公告列表</a>
</div>

</body>
</html>
