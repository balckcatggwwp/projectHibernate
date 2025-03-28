package com.movieNews.dao;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.movieNews.bean.MovieNewsBean;

public class movieNewsDao {

    private Session session;

    public movieNewsDao(Session session) {
        this.session = session;
    }

    // 新增
    public void insert(MovieNewsBean news) {
        if (news != null) {
            session.persist(news);
        }
    }

    // 刪除
    public void delete(int id) {
        MovieNewsBean news = session.get(MovieNewsBean.class, id);
        if (news != null) {
            session.remove(news);
            session.flush();
        }
    }

    // 修改
    public void update(MovieNewsBean updatedNews) {
        MovieNewsBean news = session.get(MovieNewsBean.class, updatedNews.getId());
        if (news != null) {
            news.setTitle(updatedNews.getTitle());
            news.setContent(updatedNews.getContent());
            news.setSummary(updatedNews.getSummary());
            news.setImageUrl(updatedNews.getImageUrl());
            news.setPublishDate(updatedNews.getPublishDate());
            news.setUpdatedAt(updatedNews.getUpdatedAt());
            news.setStatus(updatedNews.getStatus());
            session.flush();
        }
    }

    // 查全部
    public List<MovieNewsBean> findAll() {
        Query<MovieNewsBean> query = session.createQuery("from MovieNewsBean", MovieNewsBean.class);
        return query.list();
    }

    // 查單筆 by ID
    public MovieNewsBean findById(int id) {
        return session.get(MovieNewsBean.class, id);
    }

    // 查詢狀態為 active 的新聞
    public List<MovieNewsBean> findActiveNews() {
        Query<MovieNewsBean> query = session.createQuery("from MovieNewsBean where status = :status", MovieNewsBean.class);
        query.setParameter("status", "active");
        return query.list();
    }

    // 模糊查詢標題
    public List<MovieNewsBean> searchByTitle(String keyword) {
        Query<MovieNewsBean> query = session.createQuery("from MovieNewsBean where title like :keyword", MovieNewsBean.class);
        query.setParameter("keyword", "%" + keyword + "%");
        return query.list();
    }
}
