package com.menu.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.menu.bean.MenuBean;
import com.tick.util.HibernateUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;


@WebServlet("/InsertM")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class InsertServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertServ() {
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

		// 取得前端輸入的資料
		String menuName = request.getParameter("menu_name");
		String stockStr = request.getParameter("stock");
		String imageUrl = request.getParameter("image_url");
		String unitPriceStr = request.getParameter("unit_price");
		String description = request.getParameter("description");
		String status = request.getParameter("status");
		String category = request.getParameter("category");

		// 轉換數值型別
		int stock = (stockStr != null && !stockStr.isEmpty()) ? Integer.parseInt(stockStr) : 0;
		int unitPrice = (unitPriceStr != null && !unitPriceStr.isEmpty()) ? Integer.parseInt(unitPriceStr) : 0;
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
            // **圖片上傳處理**
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
        } catch (Exception e) {
            request.setAttribute("errorMessage", "圖片上傳失敗：" + e.getMessage());
        }
		try {
			// 建立新的 MenuBean 物件
			MenuBean menu = new MenuBean();
			menu.setMenuName(menuName);
			menu.setStock(stock);
			menu.setImageUrl(imageUrl);
			menu.setUnitPrice(unitPrice);
			menu.setDescription(description);
			menu.setStatus(status);
			menu.setCategory(category);

			// 插入資料
			session.save(menu);
			// 成功後導向到菜單列表頁面
			request.setAttribute("success", true);
			request.getRequestDispatcher("/hw2/Insertmovie.jsp").forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();
			try {
				response.sendRedirect("error.jsp");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
