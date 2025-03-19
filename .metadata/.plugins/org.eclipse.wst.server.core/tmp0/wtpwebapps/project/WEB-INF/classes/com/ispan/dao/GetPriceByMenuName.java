package com.ispan.dao;

import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetPriceByMenuName")
public class GetPriceByMenuName extends HttpServlet {
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        String menuName = request.getParameter("menu_name");

        if (menuName == null || menuName.trim().isEmpty()) {
            response.getWriter().write("0"); // 預設價格 0
            return;
        }

        try (Session session = sessionFactory.openSession()) {
            movietheaterDao menuDAO = new movietheaterDao(session);
            int price = menuDAO.getPriceByMenuName(menuName);
            response.getWriter().write(String.valueOf(price)); // 回傳價格
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("0"); // 發生錯誤時回傳 0
        }
    }

    @Override
    public void destroy() {
        sessionFactory.close();
    }
}
