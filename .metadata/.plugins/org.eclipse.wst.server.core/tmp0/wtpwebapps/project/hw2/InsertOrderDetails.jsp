<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新增訂單明細結果</title>
</head>
<body>
    <h2>訂單明細新增結果</h2>

    <%
        String success = request.getParameter("success");
        String orderId = request.getParameter("order_id");

        if ("true".equals(success)) {
    %>
        <p style="color: green;">✅ 訂單明細新增成功！</p>
        <p>訂單 ID: <%= orderId %></p>
    <%
        } else if ("false".equals(success)) {
    %>
        <p style="color: red;">❌ 訂單明細新增失敗，請檢查輸入內容！</p>
    <%
        }
    %>

    <br>
    <a href="InsertOrderDetails.html">返回新增頁面</a>
</body>
</html>
