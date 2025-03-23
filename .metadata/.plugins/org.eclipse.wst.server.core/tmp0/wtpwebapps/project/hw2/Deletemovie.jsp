<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>🗑️ 刪除菜單結果</title>

    <!-- 引入 Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .result-container {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 400px;
        }
        .alert {
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 10px;
            font-size: 18px;
        }
    </style>
</head>
<body>

    <div class="result-container">
        <h2>🗑️ 刪除菜單結果</h2>

        <%
            Boolean success = (Boolean) request.getAttribute("success");
            String errorMessage = (String) request.getAttribute("errorMessage");

            if (success != null && success) {
        %>
            <div class="alert alert-success">
                ✅ 成功刪除菜單！
            </div>
        <% } else { %>
            <div class="alert alert-danger">
                ❌ 刪除失敗: <%= (errorMessage != null && !errorMessage.isEmpty()) ? errorMessage : "未知錯誤" %>
            </div>
        <% } %>

        <!-- 返回刪除頁面 -->
        <a href="<%= request.getContextPath() %>/hw2/Deletemovie.html" class="btn btn-primary mt-3">返回刪除頁面</a>
    </div>

    <!-- 引入 Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
