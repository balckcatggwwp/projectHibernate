package com.movieNews.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.movieNews.bean.MovieNewsBean;
import com.movieNews.dao.movieNewsDao;
import com.tick.util.HibernateUtil;

@WebServlet("/GetAllNews")
public class GetAllNews extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetAllNews() {
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

	private void processAction(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			

			movieNewsDao dao = new movieNewsDao(session);
			List<MovieNewsBean> newsList = dao.findAll();

			

			request.setAttribute("news", newsList);
			request.getRequestDispatcher("/movieNews/newsList.jsp").forward(request, response);

		} catch (Exception e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
			request.setAttribute("error", "查詢新聞失敗！");
			request.getRequestDispatcher("/movieNews/newsList.jsp").forward(request, response);
		}
	}
}

