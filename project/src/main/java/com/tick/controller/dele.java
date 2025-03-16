package com.project.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.project.dao.Dao;



@WebServlet("/dele")
public class dele extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public dele() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String  tickid=request.getParameter("tickid");
//		System.out.println(tickid);
		Integer ticknu=Integer.parseInt(tickid);
		Dao dao=new Dao();
		dao.delticket(ticknu);
			//request.getRequestDispatcher("/mm12/GetAllEmp.jsp").forward(request, response);
//			
		request.getRequestDispatcher("findticketAll").forward(request, response);
			
}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
