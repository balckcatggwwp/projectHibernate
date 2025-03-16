function login() {
       event.preventDefault(); // 防止頁面刷新

       let account = document.getElementById("account").value;
       let password = document.getElementById("password").value;

       fetch("LoginServlet", {
           method: "POST",
           headers: { "Content-Type": "application/x-www-form-urlencoded" },
           body: "account=" + encodeURIComponent(account) + "&password=" + encodeURIComponent(password)
       })
       .then(response => response.json()) // 解析 JSON
       .then(data => {
           if (data.success) {
               alert("登入成功！歡迎 " + data.name);
               localStorage.setItem("employee_name", data.name);
               localStorage.setItem("permission", data.permission);
               window.location.href = "dashboard.html"; // 跳轉到主頁
           } else {
               alert("登入失敗：" + data.message);
           }
       })
       .catch(error => console.error("錯誤：", error));
   }