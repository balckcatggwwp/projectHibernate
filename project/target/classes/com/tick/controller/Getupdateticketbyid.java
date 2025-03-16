package com.project.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.project.bean.BookticketBean;
import com.project.dao.Dao;

@WebServlet("/Getupdateticketbyid")
public class Getupdateticketbyid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Getupdateticketbyid() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	String tickid = request.getParameter("tickid");
//	System.out.println(tickid);
	try {
		Dao dao =new Dao();
	  BookticketBean bean =dao.Getticketbyid(tickid);
//		System.out.println(tick.getHallid());
//		System.out.println(bean.getStartdate());
	  
		request.setAttribute("tickinfo", bean);
		request.getRequestDispatcher("/proupdate/update.jsp").forward(request, response);
	} catch (Exception e) {
		// TODO: handle exception
	}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
