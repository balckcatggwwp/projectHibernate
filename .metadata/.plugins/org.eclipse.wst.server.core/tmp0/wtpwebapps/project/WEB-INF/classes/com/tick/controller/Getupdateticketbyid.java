package com.tick.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tick.bean.BookticketvuBean;
import com.tick.service.tickservice;
import com.tick.util.HibernateUtil;

@WebServlet("/Getupdateticketbyid")
public class Getupdateticketbyid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Getupdateticketbyid() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request,response);
//		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request,response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String tickid = request.getParameter("tickid");
//		System.out.println(tickid);
		try {
			SessionFactory factory = HibernateUtil.getSessionFactory();
			Session session =factory.getCurrentSession();
			tickservice tservice = new tickservice(session);
			BookticketvuBean book = tservice.Getticketbyid(tickid);
//			System.out.println(tick.getHallid());
//			System.out.println(bean.getStartdate());
		  
			request.setAttribute("tickinfo", book);
			request.getRequestDispatcher("/proupdate/update.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


}
