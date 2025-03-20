<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>後台管理</title>
            <link rel="stylesheet" href="//cdn.datatables.net/2.2.2/css/dataTables.dataTables.min.css">
            <style>
                .btn-group {
                    display: flex;
                    /* 讓按鈕橫向排列 */
                    gap: 10px;
                    /* 設定按鈕之間的間距 */
                    align-items: center;
                    /* 垂直置中 */
                }

                .btn-group button {
                    padding: 5px 10px;
                    border: none;
                    cursor: pointer;
                    background-color: #007bff;
                    /* 藍色按鈕 */
                    color: white;
                    border-radius: 5px;
                }

                .btn-group button:hover {
                    background-color: #0056b3;
                    /* 深藍色 */
                }

                a {
                    font-size: 24px
                }
            </style>
        </head>

        <body>
            <table id="all" class="cell-border" style="width:100%">
                <thead>
                    <tr>
                        <th>電影ID</th>
                        <th>電影名稱</th>
                        <th>分級</th>
                        <th>類型</th>
                        <th>導演</th>
                        <th>演員</th>
                        <th>片長(分)</th>
                        <th>上映日期</th>
                        <th>上映狀態</th>
                        <th>電影介紹</th>
                        <th>圖片(路徑)</th>
                        <th>預告(路徑)</th>
                        <th>功能操作</th>
                    </tr>
                <tbody>
                    <c:forEach items="${Info}" var="Info">
                        <tr>
                            <td>${Info.id}</td>
                            <td>${Info.name}</td>
                            <td>${Info.rating}</td>
                            <td>${Info.genre}</td>
                            <td>${Info.director}</td>
                            <td>${Info.actor}</td>
                            <td>${Info.runtime}</td>
                            <td>${Info.releaseDate}</td>
                            <td>${Info.status}</td>
                            <td>${Info.description}</td>
                            <td>${Info.image}</td>
                            <td>${Info.trailer}</td>
                            <td>
                                <div class="btn-group">
                                    <button class="update">修改</button>
                                    <button class="delete">刪除</button>
                                </div>
                            </td>
                    </c:forEach>
                </tbody>
            </table>
            <a href="http://localhost:8080/project/han/JSP-zh/select.html">回查詢首頁</a>
            <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
            <script src="https://cdn.datatables.net/2.2.2/js/dataTables.min.js"></script>
            <script>
                $(document).ready(function () {
                    $('#all').DataTable({
                        lengthChange: false,
                        info: false,
                        searching: false
                    });
                });
                $('.update').click(function () {
                    let id = $(this).closest('tr').children('td').eq(0).text()
                    window.location.href = "GetOneInfo?id=" + id;

                })
                $('.delete').click(function () {
                    let id = $(this).closest('tr').children('td').eq(0).text()
                    window.alert("刪除成功!")
                    window.location.href = "DeleteInfo?id=" + id;

                })
            </script>

        </body>

        </html>