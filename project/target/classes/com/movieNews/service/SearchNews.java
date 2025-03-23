package com.movieNews.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.movieNews.bean.MovieNewsBean;
import com.movieNews.dao.movieNewsDao;

@WebServlet("/SearchNews")
public class SearchNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchNews() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  String searchQuery = request.getParameter("searchQuery");
	        
	        if (searchQuery == null || searchQuery.trim().isEmpty()) {
	            // 如果沒有提供搜尋條件，重定向到所有公告頁面
	            response.sendRedirect("GetAllNews");
	            return;
	        }

	        // 調用 DAO 層進行搜尋
	        movieNewsDao dao = new movieNewsDao();
	        List<MovieNewsBean> newsList = dao.searchNews(searchQuery);

	        // 將搜尋結果傳遞給 JSP 頁面
	        request.setAttribute("news", newsList);
	        request.setAttribute("searchQuery", searchQuery); // 傳遞搜尋關鍵字以顯示在頁面

	        
	        request.getRequestDispatcher("/movieNews/newsList.jsp").forward(request, response);
	    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
