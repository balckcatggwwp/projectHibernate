package com.movieNews.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import com.movieNews.dao.movieNewsDao;

@WebServlet("/UpdateNews")
@MultipartConfig
public class UpdateNews extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	    private static final String UPLOAD_DIR = "uploads";

	    public UpdateNews() {
	        super();
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	 int id = Integer.parseInt(request.getParameter("id"));
	         String title = request.getParameter("title");
	         String summary = request.getParameter("summary");
	         String content = request.getParameter("content");
	         Part imagePart = request.getPart("image");

	         String uploadPath = getServletContext().getRealPath("/" + UPLOAD_DIR);
		        File uploadDir = new File(uploadPath);
		        if (!uploadDir.exists()) {
		            uploadDir.mkdirs();
		        }
	         String imagePath = null;
		        if (imagePart != null && imagePart.getSize() > 0) {
		            // 取得檔案名稱
		            String fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
		            String filePath = uploadPath + File.separator + fileName;
		            imagePart.write(filePath);

		            // 儲存資料庫的圖片路徑使用相對路徑
		            
		            imagePath = "/" + UPLOAD_DIR + "/" + fileName;

		        }

	         movieNewsDao dao = new movieNewsDao();
	         dao.UpdateNews(title, content, summary, imagePath, id);
	         
		            response.sendRedirect(request.getContextPath() + "/GetAllNews"); // 成功則跳轉至公告列表
		        
	    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
