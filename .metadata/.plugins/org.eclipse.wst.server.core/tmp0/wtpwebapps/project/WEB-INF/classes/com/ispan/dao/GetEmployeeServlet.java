package com.ispan.dao;

import java.io.IOException;


import javax.naming.NamingException;

import com.ispan.bean.EmpBean;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetEmployee")
public class GetEmployeeServlet extends HttpServlet {
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String empno = request.getParameter("empno");

        if (empno == null || empno.trim().isEmpty()) {
            request.setAttribute("errorMessage", "請輸入員工編號！");
            request.getRequestDispatcher("/hw1/Select.jsp").forward(request, response);
            return;
        }

        try {
            EmpBean emp = employeeDAO.getEmployeeByEmpno(empno);
            if (emp == null) {
                request.setAttribute("errorMessage", "查無此員工！");
            } else {
                request.setAttribute("emp", emp);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "資料庫錯誤：" + e.getMessage());
        }

        request.getRequestDispatcher("/hw1/Select.jsp").forward(request, response);
    }
}
