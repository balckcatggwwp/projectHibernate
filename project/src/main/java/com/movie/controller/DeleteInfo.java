package com.movie.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.tick.util.HibernateUtil;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.movie.bean.InfoBean;
import com.movie.model.InfoService;

@WebServlet("/DeleteInfo")
public class DeleteInfo extends HttpServlet {
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
			
			InfoService infoService = new InfoService(session);
			Integer key = Integer.parseInt( request.getParameter("id"));
			infoService.delete(key);
			
			List<InfoBean> resultBean = infoService.findAll();
			request.setAttribute("Info", resultBean);
			request.getRequestDispatcher("/han/JSP-zh/GetAll.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}