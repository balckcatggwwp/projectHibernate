<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="zh-TW">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>修改電影資料</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    background-color: #f4f4f4;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    height: 100vh;
                }

                .container {
                    background: white;
                    padding: 20px;
                    border-radius: 10px;
                    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                    width: 400px;
                    text-align: center;
                }

                h2 {
                    text-align: center;
                    margin-bottom: 20px;
                }

                label {
                    font-weight: bold;
                    display: block;
                    margin-top: 10px;
                }

                input {
                    width: 90%;
                    padding: 8px;
                    margin-top: 5px;
                    border: 1px solid #ccc;
                    border-radius: 5px;
                    display: block;
                    margin-left: auto;
                    margin-right: auto;
                }

                button {
                    width: 90%;
                    padding: 10px;
                    background: #007bff;
                    color: white;
                    border: none;
                    border-radius: 5px;
                    cursor: pointer;
                    margin-top: 20px;
                }

                button:hover {
                    background: #0056b3;
                }
            </style>
        </head>

        <body>
            <div class="container">
                <h2>修改電影資料</h2>
                    <form action="Update" method="post">
                        <label>電影ID:</label>
                        <input type="text" name="id" readonly value="${Info.id}">

                        <label>電影名稱:</label>
                        <input type="text" name="name" value="${Info.name}">

                        <label>分級:</label>
                        <input type="text" name="rating" value="${Info.rating}">

                        <label>類型:</label>
                        <input type="text" name="genre" value="${Info.genre}">

                        <label>導演:</label>
                        <input type="text" name="director" value="${Info.director}">

                        <label>演員:</label>
                        <input type="text" name="actor" value="${Info.actor}">

                        <label>片長(分):</label>
                        <input type="text" name="runtime" value="${Info.runtime}">

                        <label>上映日期:</label>
                        <input type="text" name="releaseDate" value="${Info.releaseDate}">

                        <label>上映狀態:</label>
                        <input type="text" name="status" value="${Info.status}">

                        <label>電影介紹:</label>
                        <input type="text" name="description" value="${Info.description}">

                        <label>圖片(路徑):</label>
                        <input type="text" name="image" value="${Info.image}">

                        <label>預告(路徑):</label>
                        <input type="text" name="trailer" value="${Info.trailer}">

                        <button type="submit">送出</button>
                    </form>
                
            </div>
        </body>

        </html>