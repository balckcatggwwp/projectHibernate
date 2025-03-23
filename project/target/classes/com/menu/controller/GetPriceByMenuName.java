package com.menu.controller;


import java.io.IOException;

import com.menu.model.movietheaterDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetPriceByMenuName")
public class GetPriceByMenuName extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private movietheaterDao menuDAO;

    @Override
    public void init() throws ServletException {
        try {
            menuDAO = new movietheaterDao(); // ✅ 改用 Hibernate DAO
        } catch (Exception e) {
            throw new ServletException("DAO 初始化失敗", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        String menuName = request.getParameter("menu_name");

        if (menuName == null || menuName.trim().isEmpty()) {
            response.getWriter().write("0"); // 預設價格 0
            return;
        }

        int price = 0;
        try {
            price = menuDAO.getPriceByMenuName(menuName.trim()); // 使用 Hibernate 查價格
        } catch (Exception e) {
            e.printStackTrace(); // 可選：紀錄錯誤訊息
        }

        response.getWriter().write(String.valueOf(price)); // 回傳價格
    }
}
