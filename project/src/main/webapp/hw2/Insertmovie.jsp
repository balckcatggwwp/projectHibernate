<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <title>新增菜單結果</title>

    <!-- 引入 Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            max-width: 500px;
            background: white;
            padding: 20px;
            margin-top: 50px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h2 {
            margin-bottom: 20px;
        }
        .alert {
            font-size: 18px;
        }
        .btn-back {
            width: 100%;
            margin-top: 10px;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>📌 新增菜單結果</h2>

        <%
            Boolean success = (Boolean) request.getAttribute("success");
            String errorMessage = (String) request.getAttribute("errorMessage");

            if (success != null && success) {
        %>
            <div class="alert alert-success">✅ 菜單新增成功！</div>
        <% } else { %>
            <div class="alert alert-danger">❌ 新增失敗！錯誤訊息：<%= (errorMessage != null && !errorMessage.isEmpty()) ? errorMessage : "未知錯誤" %></div>
        <% } %>

        <!-- 返回按鈕 -->
        <a href="<%= request.getContextPath() %>/hw2/Insertmovie.html" class="btn btn-primary btn-back">返回新增頁面</a>
    </div>

    <!-- 引入 Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
