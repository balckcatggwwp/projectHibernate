<%@page import="java.util.List"%>
<%@page import="com.menu.bean.MenuBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>🍽 菜單列表</title>

<!-- Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- DataTables -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">

<style>
body {
	background-color: #f8f9fa;
}

table {
	background-color: white;
}

th {
	background-color: #007bff;
	color: white;
	text-align: center;
}

td {
	text-align: center;
	vertical-align: middle;
}

img {
	width: 100px;
	height: auto;
	border-radius: 5px;
}

.btn-back {
	display: block;
	width: 200px;
	margin: 20px auto;
	padding: 10px;
	text-align: center;
	background-color: #28a745;
	color: white;
	text-decoration: none;
	border-radius: 5px;
}

.btn-back:hover {
	background-color: #218838;
}

.btn-sm {
	margin-right: 5px;
}
</style>
</head>
<body>

	<!-- 顯示刪除成功或錯誤訊息 -->
	<c:if test="${not empty errorMessage}">
		<div class="alert alert-danger text-center">${errorMessage}</div>
	</c:if>

	<div class="container mt-5">
		<h2 class="text-center mb-4">🍽 菜單一覽</h2>

		<table class="table table-bordered table-hover" id="menuTable">
			<thead class="table-dark">
				<tr>
					<th>商品名稱</th>
					<th>庫存</th>
					<th>圖片</th>
					<th>單價</th>
					<th>介紹</th>
					<th>狀態</th>
					<th>類別</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty menuList}">
						<c:forEach var="mn" items="${menuList}">
							<tr>
								<td>${mn.menuName}</td>
								<td>${mn.stock}</td>
								<td><img
									src="${pageContext.request.contextPath}${mn.imageUrl}"
									alt="商品圖片" class="img-thumbnail"></td>
								<td>${mn.unitPrice}</td>
								<td>${mn.description}</td>
								<td>${mn.status}</td>
								<td>${mn.category}</td>
								<td>
									<button class="edit-btn btn btn-warning"
										data-menu-name="${mn.menuName}">修改</button>
									<button class="btn btn-danger delete-btn"
										data-menu-name="${mn.menuName}">刪除</button>
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="8" class="text-center text-danger">❌ 沒有找到菜單品項。</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>

		<!-- 返回按鈕 -->
		<a href="<%=request.getContextPath()%>/hw2/menupage.html"
			class="btn-back">返回查詢頁面</a>
	</div>

	<!-- JavaScript: jQuery + DataTables -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>

	<!-- 啟用 DataTables -->
	<script>
        $(document).ready(function(){
        	if ($("#menuTable tbody tr").length > 1) { 
                $('#menuTable').DataTable({
                    "language": {
                        "lengthMenu": "顯示 _MENU_ 筆記錄",
                        "zeroRecords": "沒有符合的記錄",
                        "info": "顯示第 _START_ 筆到第 _END_ 筆，共 _TOTAL_ 筆",
                        "infoEmpty": "沒有可顯示的記錄",
                        "infoFiltered": "(從 _MAX_ 筆記錄中篩選)",
                        "search": "搜尋：",
                        "paginate": {
                            "first": "第一頁",
                            "last": "最後一頁",
                            "next": "下一頁",
                            "previous": "上一頁"
                        }
                    }
                });
            } else {
                console.log("❌ 沒有資料，不初始化 DataTables");
            }
        });

        // 修改按鈕
        document.addEventListener("DOMContentLoaded", function() {
            document.querySelectorAll(".edit-btn").forEach(button => {
                button.addEventListener("click", function() {
                    let menuName = this.getAttribute("data-menu-name");
                    if (!menuName) {
                        console.error("❌ 無法獲取 menuName");
                        return;
                    }
                    let encodedMenuName = encodeURIComponent(menuName);
                    window.location.href = `/project/hw2/Updatemovie.html?menu_name=` + 
                    		encodedMenuName;
                });
            });
        });

        // 刪除按
        document.addEventListener("DOMContentLoaded", function () {
            document.querySelectorAll(".delete-btn").forEach(button => {
                button.addEventListener("click", function () {
                    let menuName = this.getAttribute("data-menu-name");
                    if (confirm("確定要刪除「" + menuName + "」嗎？")) {
                        fetch("/project/DeleteM", {
                            method: "POST",
                            headers: { "Content-Type": "application/x-www-form-urlencoded" },
                            body: "menu_name=" + encodeURIComponent(menuName)
                        })
                        .then(response => response.json())
                        .then(data => {
                            if (data.success) {
                                alert("✅ 刪除成功！");
                                location.reload();
                            } else {
                                alert("❌ 刪除失敗：" + data.message);
                            }
                        })
                        .catch(error => {
                            console.error("❌ 刪除請求錯誤:", error);
                            alert("❌ 系統錯誤，請稍後再試！");
                        });
                    }
                });
            });
        });
    </script>
</body>
</html>
