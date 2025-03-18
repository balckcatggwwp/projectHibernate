package com.ispan.dao;

import java.io.IOException;
import javax.naming.NamingException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hw2/Delete")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmployeeDAO employeeDAO;

    @Override
    public void init() throws ServletException {
        try {
            System.out.println("有進來 DeleteServlet"); // ✅ 修正錯誤訊息
            employeeDAO = new EmployeeDAO();
        } catch (NamingException e) {
            throw new ServletException("DAO 初始化失敗", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String empno = request.getParameter("empno");
        boolean success = false;
        String errorMessage = null;

        // 🔹 **檢查 empno 是否為空**
        if (empno == null || empno.trim().isEmpty()) {
            errorMessage = "請輸入員工編號！";
        } else {
            try {
                success = employeeDAO.deleteEmployee(empno);
                if (!success) errorMessage = "員工編號不存在";
            } catch (Exception e) {
                e.printStackTrace(); // ✅ **加上錯誤輸出，方便 debug**
                errorMessage = "資料庫錯誤：" + e.getMessage();
            }
        }

        // 🔹 **確保 `errorMessage` 不為 null**
        request.setAttribute("success", success);
        request.setAttribute("errorMessage", errorMessage != null ? errorMessage : "");

        // 🔹 **確保 `Delete.jsp` 的路徑正確**
        request.getRequestDispatcher("/hw2/Delete.jsp").forward(request, response);
    }
}
