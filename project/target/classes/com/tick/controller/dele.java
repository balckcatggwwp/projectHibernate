package com.tick.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tick.service.tickservice;
import com.tick.util.HibernateUtil;



@WebServlet("/dele")
public class dele extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public dele() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request,response);
			
}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processAction(request,response);
	}
	private void processAction(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String  tickid=request.getParameter("tickid");
		Integer ticknu=Integer.parseInt(tickid);
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session =factory.getCurrentSession();
		tickservice tservice = new tickservice(session);
		tservice.delticket(ticknu);
//		System.out.println(tickid);
//		Dao dao=new Dao();
//		dao.delticket(ticknu);
		//request.getRequestDispatcher("/mm12/GetAllEmp.jsp").forward(request, response);
//			
		try {
			request.getRequestDispatcher("findticketAll").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
