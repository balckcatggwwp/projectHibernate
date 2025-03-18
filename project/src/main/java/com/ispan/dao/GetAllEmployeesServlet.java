package com.ispan.dao;

import java.io.IOException;

import java.util.List;

import javax.naming.NamingException;

import com.ispan.bean.EmpBean;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hw2/GetAllEmployees")
public class GetAllEmployeesServlet extends HttpServlet {
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
        try {
            List<EmpBean> employees = employeeDAO.getAllEmployees();
            request.setAttribute("employees", employees);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "資料庫錯誤：" + e.getMessage());
        }

        request.getRequestDispatcher("/hw2/GetAllEmployees.jsp").forward(request, response);
    }
}
