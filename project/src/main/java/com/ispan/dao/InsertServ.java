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

@WebServlet("/InsertM")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class InsertServ extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SessionFactory sessionFactory;

    @Override
    public void init() throws ServletException {
        try {
            System.out.println("✅ InsertServlet 初始化");
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Exception e) {
            throw new ServletException("❌ Hibernate 初始化失敗", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");

        // **取得表單參數**
        String menuName = request.getParameter("menu_name");
        int stock = Integer.parseInt(request.getParameter("stock"));
        int unitPrice = Integer.parseInt(request.getParameter("unit_price"));
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        String category = request.getParameter("category");
        String imageUrl = ""; // 預設為空字串，避免 null 錯誤

        // **處理圖片上傳**
        try {
            Part filePart = request.getPart("imageFile");
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                // **取得上傳目錄**
                String uploadPath = getServletContext().getRealPath("/hw2/uploads");
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdirs();

                // **完整的檔案路徑**
                String filePath = uploadPath + File.separator + fileName;
                filePart.write(filePath);

                // **存入資料庫的 image_url 只存相對網址**
                imageUrl = "/hw2/uploads/" + fileName;
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "❌ 圖片上傳失敗：" + e.getMessage());
        }

        // **建立 MenuBean**
        MenuBean menu = new MenuBean(menuName, stock, imageUrl, unitPrice, description, status, category);

        boolean success = false;
        try (Session session = sessionFactory.openSession()) {
            movietheaterDao menuDAO = new movietheaterDao(session);

            session.beginTransaction();
            success = menuDAO.insertMenu(menu);
            session.getTransaction().commit();
        } catch (Exception e) {
            request.setAttribute("errorMessage", "❌ 資料庫錯誤：" + e.getMessage());
        }

        // **回傳結果到 JSP**
        request.setAttribute("success", success);
        request.getRequestDispatcher("/hw2/Insertmovie.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        sessionFactory.close();
    }
}
