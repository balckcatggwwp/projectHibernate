package com.ispan.dao;

import java.io.IOException;


import javax.naming.NamingException;

import com.ispan.bean.EmpBean;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Insert")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EmployeeDAO employeeDAO;

    @Override
    public void init() throws ServletException {
        try {
        	System.out.println("有進來InsertServlet");
            employeeDAO = new EmployeeDAO();
        } catch (NamingException e) {
            throw new ServletException("DAO 初始化失敗", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmpBean emp = new EmpBean(
            request.getParameter("empno"),
            request.getParameter("ename"),
            request.getParameter("hiredate"),
            request.getParameter("salary"),
            request.getParameter("deptno"),
            request.getParameter("title")
        );

        try {
            boolean success = employeeDAO.insertEmployee(emp);
            request.setAttribute("success", success);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "資料庫錯誤：" + e.getMessage());
        }
        request.getRequestDispatcher("/hw2/Insert.jsp").forward(request, response);

    }
}
