package com.menu.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.naming.NamingException;

import com.menu.bean.MenuBean;
import com.menu.model.movietheaterDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/UpdateM")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class UpdateServ extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private movietheaterDao movieDao;

    @Override
    public void init() throws ServletException {
        try {
            movieDao = new movietheaterDao();
        } catch (Exception e) {
            throw new ServletException("DAO 初始化失敗", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menuName = request.getParameter("menu_name");

        if (menuName == null || menuName.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/hw2/Updatemovie.html");
            return;
        }

        try {
            MenuBean mn = movieDao.getMenuByName(menuName);
            request.setAttribute("mn", mn);
            if (mn == null) {
                request.setAttribute("errorMessage", "找不到菜單名稱：" + menuName);
            } else {
                request.setAttribute("mn", mn);
            }

            request.getRequestDispatcher("/hw2/Updatemovie.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/hw2/Updatemovie.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String errorMessage = null;
        boolean success = false;

        try {
            // ✅ 接收所有欄位
            int menuId = Integer.parseInt(request.getParameter("menu_id")); // hidden 欄位
            String menuName = request.getParameter("menu_name");
            int stock = Integer.parseInt(request.getParameter("stock"));
            int unitPrice = Integer.parseInt(request.getParameter("unit_price"));
            String description = request.getParameter("description");
            String status = request.getParameter("status");
            String category = request.getParameter("category");
            String imageUrl = request.getParameter("existing_image_url");

            // ✅ 處理圖片上傳
            Part filePart = request.getPart("imageFile");
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String uploadPath = getServletContext().getRealPath("/hw2/uploads");
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdirs();
                String filePath = uploadPath + File.separator + fileName;
                filePart.write(filePath);
                imageUrl = "/hw2/uploads/" + fileName;
            }

            // ✅ 建立實體並帶入 ID
            MenuBean menu = new MenuBean();
            menu.setMenu_id(menuId); // 非常重要
            menu.setMenuName(menuName);
            menu.setStock(stock);
            menu.setUnitPrice(unitPrice);
            menu.setDescription(description);
            menu.setStatus(status);
            menu.setCategory(category);
            menu.setImageUrl(imageUrl);

            // ✅ 執行更新
            success = movieDao.updateMenu(menu);
            if (!success) {
                errorMessage = "❌ 更新失敗，菜單不存在！";
            }

        } catch (NumberFormatException e) {
            errorMessage = "❌ 數值格式錯誤：" + e.getMessage();
        } catch (Exception e) {
            errorMessage = "❌ 發生錯誤：" + e.getMessage();
            e.printStackTrace();
        }

        // ✅ 將結果傳到 JSP 顯示
        request.setAttribute("success", success);
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("/hw2/Updatemovie.jsp").forward(request, response);
    }
}
