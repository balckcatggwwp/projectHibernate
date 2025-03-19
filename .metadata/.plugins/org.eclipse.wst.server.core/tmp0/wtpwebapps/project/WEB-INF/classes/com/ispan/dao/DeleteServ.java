package com.ispan.dao;

import java.io.IOException;
import java.io.PrintWriter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteM")
public class DeleteServ extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SessionFactory sessionFactory;

    @Override
    public void init() throws ServletException {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Exception e) {
            throw new ServletException("❌ Hibernate 初始化失敗", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String menuIdStr = request.getParameter("menu_id");
        boolean success = false;
        String errorMessage = null;

        if (menuIdStr == null || menuIdStr.trim().isEmpty()) {
            errorMessage = "❌ 錯誤: 請提供有效的菜單 ID";
        } else {
            try {
                int menuId = Integer.parseInt(menuIdStr);

                try (Session session = sessionFactory.openSession()) {
                    movietheaterDao menuDAO = new movietheaterDao(session);
                    session.beginTransaction();
                    success = menuDAO.deleteMenu(menuId);
                    session.getTransaction().commit();
                }
                
                if (!success) {
                    errorMessage = "❌ 錯誤: 該菜單不存在或刪除失敗";
                }
            } catch (NumberFormatException e) {
                errorMessage = "❌ 錯誤: 無效的菜單 ID";
            } catch (Exception e) {
                e.printStackTrace();
                errorMessage = "❌ 資料庫錯誤：" + e.getMessage();
            }
        }

        // ✅ 回傳 JSON 給前端
        PrintWriter out = response.getWriter();
        out.write("{\"success\": " + success + ", \"message\": \"" + (success ? "刪除成功" : errorMessage) + "\"}");
        out.flush();
        out.close();
    }

    @Override
    public void destroy() {
        sessionFactory.close();
    }
}

