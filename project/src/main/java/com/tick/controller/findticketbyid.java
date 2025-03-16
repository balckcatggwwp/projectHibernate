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

@WebServlet("/findticketbyid")
public class findticketbyid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public findticketbyid() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	String tickid = request.getParameter("tickid");
	try {
		Dao dao =new Dao();
		List<BookticketBean>  tick =dao.findticketbyid(tickid);
//		System.out.println(tick.getHallid());
		request.setAttribute("tickinfo", tick);
		request.getRequestDispatcher("/profind/datatable.jsp").forward(request, response);
	} catch (Exception e) {
		// TODO: handle exception
	}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
