package com.ispan.dao;

import java.io.IOException;
import javax.naming.NamingException;
import com.ispan.bean.EmpBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hw2/Update")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmployeeDAO employeeDAO;

    @Override
    public void init() throws ServletException {
        try {
            employeeDAO = new EmployeeDAO();
        } catch (NamingException e) {
            throw new ServletException("DAO 初始化失敗", e);
        }
    }

    // **處理 GET 請求 - 查詢員工資料，顯示於 Update.jsp**
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String empno = request.getParameter("empno");

        if (empno == null || empno.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/hw2/Update.html"); // 返回更新頁面
            return;
        }

        try {
            EmpBean emp = employeeDAO.getEmployeeByEmpno(empno);

            if (emp == null) {
                request.setAttribute("errorMessage", "找不到該員工編號：" + empno);
            } else {
                request.setAttribute("emp", emp);
            }

            request.getRequestDispatcher("/hw2/Update.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/hw2/Update.html"); // 返回更新頁面
        }
    }

    // **處理 POST 請求 - 更新員工資料**
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String empno = request.getParameter("empno");
        String ename = request.getParameter("ename");
        String hiredate = request.getParameter("hiredate");
        String salaryStr = request.getParameter("salary");
        String deptnoStr = request.getParameter("deptno");
        String title = request.getParameter("title");

        boolean success = false;
        String errorMessage = null;
        int salary = 0;
        int deptno = 0;

        // 驗證 salary 是否為數字
        try {
            if (salaryStr != null && !salaryStr.isEmpty()) {
                salary = Integer.parseInt(salaryStr);
            }
        } catch (NumberFormatException e) {
            errorMessage = "薪水欄位必須是數字！";
        }

        // 驗證 deptno 是否為數字
        try {
            if (deptnoStr != null && !deptnoStr.isEmpty()) {
                deptno = Integer.parseInt(deptnoStr);
            }
        } catch (NumberFormatException e) {
            errorMessage = "部門編號必須是數字！";
        }

        // 若有錯誤，返回 Update.jsp
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/hw2/Update.jsp").forward(request, response);
            return;
        }

        // 創建 EmpBean
        EmpBean emp = new EmpBean(empno, ename, hiredate, String.valueOf(salary), String.valueOf(deptno), title);

        try {
            success = employeeDAO.updateEmployee(emp);
            if (!success) {
                errorMessage = "更新失敗，員工編號不存在！";
            }
        } catch (Exception e) {
            errorMessage = "資料庫錯誤：" + e.getMessage();
            e.printStackTrace();
        }

        request.setAttribute("success", success);
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("/hw2/Update.jsp").forward(request, response);
    }
}
