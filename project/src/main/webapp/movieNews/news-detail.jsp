<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ page import="java.sql.*" %>
            <%@ page import="javax.naming.*, javax.sql.DataSource" %>
                <%@ page import="com.movieNews.utils.Utils" %>
                    <% String idParam=request.getParameter("id"); if (idParam==null || idParam.trim().isEmpty()) {
                        out.println("公告ID無效！"); return; } int id=Integer.parseInt(idParam); Connection
                        connection=Utils.getConnection(); PreparedStatement preparedStatement=null; ResultSet
                        resultSet=null; String sql="SELECT * FROM news WHERE id = ?" ; try {
                        preparedStatement=connection.prepareStatement(sql); preparedStatement.setInt(1, id);
                        resultSet=preparedStatement.executeQuery(); if (resultSet.next()) { String
                        title=resultSet.getString("title"); String summary=resultSet.getString("summary"); String
                        content=resultSet.getString("content"); String imageUrl=resultSet.getString("image_url"); String
                        finalImageUrl="" ; if (imageUrl !=null && !imageUrl.isEmpty()) { if
                        (imageUrl.startsWith("http")) { finalImageUrl=imageUrl; } else {
                        finalImageUrl=request.getContextPath() + imageUrl; } } // **這裡修正，確保 JSP 內能取到 finalImageUrl**
                        request.setAttribute("finalImageUrl", finalImageUrl); request.setAttribute("title", title);
                        request.setAttribute("summary", summary); request.setAttribute("content", content);
                        request.setAttribute("publishDate", resultSet.getDate("publish_date")); } else {
                        out.println("公告未找到！"); } } catch (Exception e) { e.printStackTrace(); out.println("資料庫錯誤！"); }
                        finally { Utils.closeResource(connection, preparedStatement, resultSet); } %>

                        <html lang="zh-TW">

                        <head>
                            <meta charset="UTF-8">
                            <meta name="viewport" content="width=device-width, initial-scale=1.0">
                            <title>${title}</title>
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
                                }

                                .news-content {
                                    margin-top: 20px;
                                    font-size: 12px;
                                }

                                .news-image {
                                    max-width: 100%;
                                    height: auto;
                                    margin-top: 20px;
                                }

                                .news-summary {
                                    margin-top: 20px;
                                    color: #555;
                                }
                            </style>
                        </head>

                        <body>
                            <div class="container">
                                <h1>${title}</h1>
                                <div class="news-meta">發佈日期：${publishDate}</div>
                                <div class="news-summary">${summary}</div>

                                <div class="news-content">
                                    <p>${content}</p>
                                    <c:if test="${not empty finalImageUrl}">
                                        <img src="${finalImageUrl}" alt="公告圖片" class="news-image" />
                                    </c:if>
                                </div>

                                <!-- 返回公告列表按鈕 -->
                                <a href="${pageContext.request.contextPath}/GetAllNews" class="btn">返回公告列表</a>
                            </div>
                        </body>

                        </html>