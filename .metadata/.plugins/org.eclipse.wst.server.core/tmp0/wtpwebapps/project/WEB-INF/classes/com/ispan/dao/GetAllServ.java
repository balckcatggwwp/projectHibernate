package com.ispan.dao;

import java.io.IOException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.ispan.bean.MenuBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetAllM")
public class GetAllServ extends HttpServlet {
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Session session = sessionFactory.openSession()) {
            movietheaterDao menuDAO = new movietheaterDao(session);

            // 🔹 調用 Hibernate `getAllmenus()` 方法
            List<MenuBean> menuList = menuDAO.getAllMenus();

            // ✅ 確保 `menuList` 不是空的，否則顯示錯誤訊息
            if (menuList == null || menuList.isEmpty()) {
                request.setAttribute("errorMessage", "❌ 沒有找到菜單品項。");
            } else {
                request.setAttribute("menuList", menuList);
            }

            // 🔹 導向 JSP 顯示菜單列表
            request.getRequestDispatcher("/hw2/GetAllmovie.jsp").forward(request, response);
        } catch (Exception e) {
            // 🔹 如果發生錯誤，顯示錯誤訊息
            request.setAttribute("errorMessage", "❌ 資料庫錯誤: " + e.getMessage());
            request.getRequestDispatcher("/hw2/GetAllmovie.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        sessionFactory.close();
    }
}
