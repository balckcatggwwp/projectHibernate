package com.menu.controller;

import java.io.IOException;
import java.util.List;

import com.menu.bean.MenuBean;
import com.menu.model.movietheaterDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetAllM")
public class GetAllServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GetAllServ() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processAction(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processAction(request, response);
    }

    private void processAction(HttpServletRequest request, HttpServletResponse response) {
        try {
            // ✅ 初始化 DAO
            movietheaterDao movieDao = new movietheaterDao();

            // ✅ 取得菜單資料
            List<MenuBean> menuList = movieDao.getAllMenus();
            System.out.println("✅ Servlet 查到 menu 筆數：" + (menuList != null ? menuList.size() : "null"));

            // ✅ 設定屬性給 JSP 使用
            if (menuList == null || menuList.isEmpty()) {
                request.setAttribute("errorMessage", "❌ 沒有找到菜單品項。");
            } else {
                request.setAttribute("menuList", menuList);
            }

            // ✅ 導向 JSP 顯示資料
            request.getRequestDispatcher("/hw2/GetAllmovie.jsp").forward(request, response);

        } catch (Exception e) {
            // ✅ 發生錯誤時導向 JSP 顯示錯誤訊息
            e.printStackTrace();
            request.setAttribute("errorMessage", "❌ 發生錯誤：" + e.getMessage());
            try {
                request.getRequestDispatcher("/hw2/GetAllmovie.jsp").forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
