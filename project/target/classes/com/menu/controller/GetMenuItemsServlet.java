package com.menu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;
import com.menu.bean.MenuBean;
import com.menu.model.movietheaterDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetMenuItems")
public class GetMenuItemsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private movietheaterDao menuDAO;

    @Override
    public void init() throws ServletException {
        try {
            menuDAO = new movietheaterDao();  // ✅ 使用 Hibernate DAO
        } catch (Exception e) {
            throw new ServletException("❌ DAO 初始化失敗", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        String menuName = request.getParameter("menu_name");

        try {
            Gson gson = new Gson();

            if (menuName != null && !menuName.trim().isEmpty()) {
                // ✅ 查詢單一商品
                MenuBean menu = menuDAO.getMenuByName(menuName.trim());
                if (menu != null) {
                    out.print(gson.toJson(menu));
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("{\"error\": \"❌ 未找到該商品\"}");
                }
            } else {
                // ✅ 查詢所有商品
                List<MenuBean> menuList = menuDAO.getAllMenus();
                out.print(gson.toJson(menuList));
            }

            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\": \"❌ 系統錯誤，請稍後再試\"}");
        }
    }
}
