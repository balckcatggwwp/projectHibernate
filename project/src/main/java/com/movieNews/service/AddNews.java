package com.movieNews.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.movieNews.bean.MovieNewsBean;
import com.movieNews.dao.movieNewsDao;
import com.tick.util.HibernateUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/AddNews")
@MultipartConfig
public class AddNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "images";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			

			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String summary = request.getParameter("summary");
			Part imagePart = request.getPart("image");

			if (title == null || title.trim().isEmpty() ||
				content == null || content.trim().isEmpty() ||
				summary == null || summary.trim().isEmpty()) {
				request.setAttribute("error", "標題、摘要和內容不能為空！");
				request.getRequestDispatcher("/movieNews/addnews.jsp").forward(request, response);
				return;
			}

			// 處理圖片上傳
			String uploadPath = getServletContext().getRealPath("/" + UPLOAD_DIR);
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) uploadDir.mkdirs();

			String imagePath = null;
			if (imagePart != null && imagePart.getSize() > 0) {
				String fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
				String filePath = uploadPath + File.separator + fileName;
				imagePart.write(filePath);
				imagePath = "/" + UPLOAD_DIR + "/" + fileName;
			}

			// 儲存資料
			movieNewsDao dao = new movieNewsDao(session);
			MovieNewsBean news = new MovieNewsBean();
			news.setTitle(title);
			news.setContent(content);
			news.setSummary(summary);
			news.setImageUrl(imagePath);
			news.setPublishDate(Date.valueOf(LocalDate.now()));
			news.setCreatedAt(Date.valueOf(LocalDate.now()));
			news.setUpdatedAt(Date.valueOf(LocalDate.now()));
			news.setStatus("active");

			dao.insert(news);

			
			response.sendRedirect(request.getContextPath() + "/GetAllNews");
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
			request.setAttribute("error", "公告新增失敗，請重試！");
			request.getRequestDispatcher("/movieNews/addnews.jsp").forward(request, response);
		}
	}
}

