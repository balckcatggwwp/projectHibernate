package com.ispan.dao;

import java.io.IOException;
import java.util.List;
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

@WebServlet("/GetMenuItems") // ✅ API 路徑不變
public class GetMenuItemsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SessionFactory sessionFactory;
    private Gson gson = new Gson(); // ✅ 使用 Gson 來轉換 JSON

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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String menuIdStr = request.getParameter("menu_id");
        try (Session session = sessionFactory.openSession()) {
            movietheaterDao menuDAO = new movietheaterDao(session);

            if (menuIdStr != null && !menuIdStr.trim().isEmpty()) {
                // ✅ 查詢單筆菜單（根據 menu_id）
                int menuId = Integer.parseInt(menuIdStr);
                MenuBean menu = menuDAO.getMenuById(menuId);
                if (menu != null) {
                    response.getWriter().write(gson.toJson(menu));
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"error\": \"❌ 未找到該商品\"}");
                }
            } else {
                // ✅ 查詢所有菜單
                List<MenuBean> menuList = menuDAO.getAllMenus();
                response.getWriter().write(gson.toJson(menuList));
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"❌ 無效的 menu_id\"}");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"❌ 無法取得菜單，請稍後再試！\"}");
        }
    }

    @Override
    public void destroy() {
        sessionFactory.close();
    }
}
