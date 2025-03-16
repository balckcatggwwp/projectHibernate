package com.project.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.project.bean.BookticketBean;
import com.project.bean.ShowtimeBean;
import com.project.dao.Dao;

@WebServlet("/findseatbytimehall")
public class findseatbytimehall extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public findseatbytimehall() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String showtimeid = request.getParameter("showtimeid");
		String hallid = request.getParameter("hallid");
	
		
	try {
		Dao dao =new Dao();
		List<BookticketBean> seats =dao.findseatbytime(showtimeid,hallid);
		
		Gson gson = new Gson();
		String json = gson.toJson(seats);
		response.getWriter().write(json);
//		System.out.println(tick.getHallid());
//		System.out.println(ticks);
//		request.setAttribute("tickinfo", ticks);
//		request.getRequestDispatcher("/profind/datatable.jsp").forward(request, response);
	} catch (Exception e) {
		// TODO: handle exception
	}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
