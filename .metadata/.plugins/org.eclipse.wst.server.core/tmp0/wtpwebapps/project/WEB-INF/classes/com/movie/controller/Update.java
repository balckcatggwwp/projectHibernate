package com.movie.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.tick.util.HibernateUtil;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.movie.bean.InfoBean;
import com.movie.model.InfoService;

@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request,response);
	}
	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			SessionFactory factory = HibernateUtil.getSessionFactory();
			Session session =factory.getCurrentSession();
			
			Integer id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String rating = request.getParameter("rating");
			String genre = request.getParameter("genre");
			String director = request.getParameter("director");
			String actor = request.getParameter("actor");
			Integer runtime = Integer.parseInt(request.getParameter("runtime"));
			Date releaseDate = java.sql.Date.valueOf(request.getParameter("releaseDate"));
			String status = request.getParameter("status");
			String description = request.getParameter("description");
			String image = request.getParameter("image");
			String trailer = request.getParameter("trailer");
			InfoService infoService = new InfoService(session);
			infoService.update(id, name, rating, genre, director, actor, runtime, releaseDate, status, description, image, trailer);
			
			InfoBean result = infoService.findById(id);
			List<InfoBean> resultBean = new ArrayList<InfoBean>();
			resultBean.add(result);
			
			request.setAttribute("Info", resultBean);
			request.getRequestDispatcher("/han/JSP-zh/Result.jsp").forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}