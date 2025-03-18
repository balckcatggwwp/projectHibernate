package com.ispan.dao;

import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.google.gson.Gson;
import com.ispan.bean.MenuBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetMenu")
public class GetMenu extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SessionFactory sessionFactory;
    private Gson gson = new Gson(); // ✅ 使用 Gson 來回傳 JSON

    @Override
    public void init() throws ServletException {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Exception e) {
            throw new ServletException("❌ Hibernate 初始化失敗", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String menuIdStr = request.getParameter("menu_id");

        // ✅ 確保 `menu_id` 不是空的
        if (menuIdStr == null || menuIdStr.trim().isEmpty()) {
            response.getWriter().write(gson.toJson(new ErrorResponse("❌ 錯誤: menu_id 不能為空")));
            return;
        }

        try {
            int menuId = Integer.parseInt(menuIdStr);

            try (Session session = sessionFactory.openSession()) {
                movietheaterDao menuDAO = new movietheaterDao(session);
                MenuBean menu = menuDAO.getMenuById(menuId);

                if (menu != null) {
                    // ✅ 轉換 `MenuBean` 為 JSON
                    response.getWriter().write(gson.toJson(menu));
                } else {
                    response.getWriter().write(gson.toJson(new ErrorResponse("❌ 找不到該菜單")));
                }
            }
        } catch (NumberFormatException e) {
            response.getWriter().write(gson.toJson(new ErrorResponse("❌ 錯誤: 無效的 `menu_id`")));
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write(gson.toJson(new ErrorResponse("❌ 資料庫錯誤：" + e.getMessage())));
        }
    }

    @Override
    public void destroy() {
        sessionFactory.close();
    }

    // ✅ 定義錯誤訊息回應類別
    private class ErrorResponse {
        private boolean success = false;
        private String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getError() {
            return error;
        }
    }
}
