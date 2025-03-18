package com.ispan.dao;

import com.ispan.bean.MenuBean;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class movietheaterDao {
    private final Session session;

    // ✅ 透過建構子傳入 Hibernate Session
    public movietheaterDao(Session session) {
        this.session = session;
    }

    // ✅ 新增菜單（使用 persist()）
    public boolean insertMenu(MenuBean menu) {
        Transaction tx = session.beginTransaction();
        try {
            session.persist(menu);  
            tx.commit();
            System.out.println("✅ 新增成功：" + menu.getMenuName());
            return true;
        } catch (Exception e) {
            tx.rollback();
            System.out.println("❌ 新增菜單失敗：" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // ✅ 刪除菜單（使用 remove()）
    public boolean deleteMenu(int menuId) {
        Transaction tx = session.beginTransaction();
        try {
            MenuBean menu = session.get(MenuBean.class, menuId);
            if (menu != null) {
                session.remove(menu);  
                tx.commit();
                System.out.println("✅ 刪除成功：" + menu.getMenuName());
                return true;
            } else {
                System.out.println("❌ 找不到菜單：" + menuId);
                return false;
            }
        } catch (Exception e) {
            tx.rollback();
            System.out.println("❌ 刪除菜單失敗：" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // ✅ 更新菜單（使用 merge()）
    public boolean updateMenu(MenuBean menu) {
        Transaction tx = session.beginTransaction();
        try {
            session.merge(menu);  
            tx.commit();
            System.out.println("✅ 更新成功：" + menu.getMenuName());
            return true;
        } catch (Exception e) {
            tx.rollback();
            System.out.println("❌ 更新菜單失敗：" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // ✅ 查詢單筆菜單（使用 find()）
    public MenuBean getMenuById(int menuId) {
        MenuBean menu = session.find(MenuBean.class, menuId);  
        if (menu != null) {
            System.out.println("✅ 查詢成功：" + menu.getMenuName());
        } else {
            System.out.println("❌ 找不到菜單：" + menuId);
        }
        return menu;
    }

    // ✅ 查詢所有菜單
    public List<MenuBean> getAllMenus() {
        Query<MenuBean> query = session.createQuery("FROM MenuBean ORDER BY category, menuName", MenuBean.class);
        List<MenuBean> menuList = query.getResultList();
        System.out.println("✅ 查詢所有菜單，共 " + menuList.size() + " 筆資料");
        return menuList;
    }
    
    public int getPriceByMenuName(String menuName) {
        String hql = "SELECT unitPrice FROM MenuBean WHERE menuName = :menuName";
        Integer price = session.createQuery(hql, Integer.class)
                              .setParameter("menuName", menuName)
                              .uniqueResult();
        return price != null ? price : 0;
    }
}
