package com.movieNews.service;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteNews")
public class DeleteNews extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteNews() {
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
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.trim().isEmpty()) {
            request.setAttribute("error", "公告ID無效！");
            request.getRequestDispatcher("/movieNews/getAllNews.jsp").forward(request, response);
            return;
        }

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        Transaction tx = null;

        try {
            int id = Integer.parseInt(idParam);
            tx = session.beginTransaction();

            MovieNewsDao dao = new MovieNewsDao(session);
            boolean success = dao.deleteNews(id);

            tx.commit();

            if (success) {
                response.sendRedirect(request.getContextPath() + "/GetAllNews");
            } else {
                request.setAttribute("error", "刪除公告失敗，請重試！");
                request.getRequestDispatcher("/movieNews/getAllNews.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            if (tx != null) tx.rollback();
            request.setAttribute("error", "公告ID格式錯誤！");
            request.getRequestDispatcher("/movieNews/getAllNews.jsp").forward(request, response);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            request.setAttribute("error", "系統錯誤！");
            request.getRequestDispatcher("/movieNews/getAllNews.jsp").forward(request, response);
        }
    }
}
