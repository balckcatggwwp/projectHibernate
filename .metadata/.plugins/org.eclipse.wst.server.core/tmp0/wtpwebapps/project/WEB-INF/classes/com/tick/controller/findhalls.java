	package com.tick.controller;
	
	import jakarta.servlet.ServletException;
	import jakarta.servlet.annotation.WebServlet;
	import jakarta.servlet.http.HttpServlet;
	import jakarta.servlet.http.HttpServletRequest;
	import jakarta.servlet.http.HttpServletResponse;
	import java.io.IOException;
	import java.util.List;
	
	import org.hibernate.Session;
	import org.hibernate.SessionFactory;
	
	import com.google.gson.Gson;
	import com.tick.bean.HallsBean;
	import com.tick.service.tickservice;
	import com.tick.util.HibernateUtil;
	
	@WebServlet("/findhalls")
	public class findhalls extends HttpServlet {
		private static final long serialVersionUID = 1L;
	       
	    public findhalls() {
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
			try {
				SessionFactory factory = HibernateUtil.getSessionFactory();
				Session session =factory.getCurrentSession();
				tickservice tservice = new tickservice(session);
				List<HallsBean> halls = tservice.findhalls();
	//			Dao dao =new Dao();
	//			List<HallsBean> halls =dao.findhalls();
	//		System.out.println(ticks);
				Gson gson = new Gson();
				String json = gson.toJson(halls);
				response.getWriter().write(json);
	//		System.out.println(tick.getHallid());
	//		System.out.println(ticks);
	//		request.setAttribute("tickinfo", ticks);
	//		request.getRequestDispatcher("/profind/datatable.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	
	}
