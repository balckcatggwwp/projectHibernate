package com.ispan.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.ispan.bean.MenuBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/UpdateM")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10, // 10MB
                 maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UpdateServ extends HttpServlet {
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

    // **處理 GET 請求 - 顯示更新頁面**
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int menuId = Integer.parseInt(request.getParameter("menu_id")); // 改用 menu_id 來查詢

        if (menuId <= 0) {
            response.sendRedirect(request.getContextPath() + "/hw2/Updatemovie.html");
            return;
        }

        try (Session session = sessionFactory.openSession()) {
            movietheaterDao menuDAO = new movietheaterDao(session);
            MenuBean mn = menuDAO.getMenuById(menuId); // ✅ 用 Hibernate 查詢

            if (mn == null) {
                request.setAttribute("errorMessage", "找不到菜單 ID：" + menuId);
            } else {
                request.setAttribute("mn", mn);
            }

            request.getRequestDispatcher("/hw2/Updatemovie.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/hw2/Updatemovie.html");
        }
    }

    // **處理 POST 請求 - 更新菜單**
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int menuId = Integer.parseInt(request.getParameter("menu_id")); // 改用 menu_id
        String menuName = request.getParameter("menu_name");
        int stock = 0, unitPrice = 0;
        String imageUrl = request.getParameter("existing_image_url"); // 預設為原圖片
        String errorMessage = null;

        try {
            stock = Integer.parseInt(request.getParameter("stock"));
        } catch (NumberFormatException e) {
            errorMessage = "❌ 庫存欄位必須是數字！";
        }

        try {
            unitPrice = Integer.parseInt(request.getParameter("unit_price"));
        } catch (NumberFormatException e) {
            errorMessage = "❌ 單價欄位必須是數字！";
        }

        String description = request.getParameter("description");
        String status = request.getParameter("status");
        String category = request.getParameter("category");

        // **處理圖片上傳**
        Part filePart = request.getPart("imageFile");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            // **將圖片存到 /hw2/uploads 目錄**
            String uploadPath = getServletContext().getRealPath("/hw2/uploads");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            String filePath = uploadPath + File.separator + fileName;
            filePart.write(filePath);

            // **存入資料庫的 image_url 只存相對網址**
            imageUrl = "/hw2/uploads/" + fileName;
        }

        // **如果有錯誤，回到 Update 頁面**
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/hw2/Updatemovie.jsp").forward(request, response);
            return;
        }

        // **更新資料庫**
        boolean success = false;
        try (Session session = sessionFactory.openSession()) {
            movietheaterDao menuDAO = new movietheaterDao(session);
            session.beginTransaction();

            // **先查詢該筆資料**
            MenuBean mn = menuDAO.getMenuById(menuId);
            if (mn == null) {
                errorMessage = "❌ 更新失敗，菜單 ID 不存在！";
            } else {
                // **更新資料**
                mn.setMenuName(menuName);
                mn.setStock(stock);
                mn.setImageUrl(imageUrl);
                mn.setUnitPrice(unitPrice);
                mn.setDescription(description);
                mn.setStatus(status);
                mn.setCategory(category);

                success = menuDAO.updateMenu(mn);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            errorMessage = "❌ 資料庫錯誤：" + e.getMessage();
            e.printStackTrace();
        }

        request.setAttribute("success", success);
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("/hw2/Updatemovie.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        sessionFactory.close();
    }
}

