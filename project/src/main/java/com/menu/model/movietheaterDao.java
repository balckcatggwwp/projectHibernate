package com.menu.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.menu.bean.MenuBean;
import com.menu.interfac.ImenuDAO;
import com.tick.util.HibernateUtil;

public class movietheaterDao implements ImenuDAO {

    private SessionFactory sessionFactory;

    public movietheaterDao() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    // **新增菜單**
    @Override
    public boolean insertMenu(MenuBean menu) {
        boolean success = false;
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(menu);
            tx.commit();
            success = true;
            System.out.println("✅ 新增成功：" + menu.getMenuName());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("❌ 新增菜單失敗：" + e.getMessage());
            e.printStackTrace();
        }
        return success;
    }

    // **更新菜單**
    @Override
    public boolean updateMenu(MenuBean menu) {
        boolean success = false;
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            // 先用 menu_id 查出舊資料，確保 session 有管理該 entity
            MenuBean existingMenu = session.get(MenuBean.class, menu.getMenu_id());

            if (existingMenu == null) {
                System.out.println("❌ 更新失敗，找不到 menu_id：" + menu.getMenu_id());
                return false;
            }

            // 更新欄位
            existingMenu.setMenuName(menu.getMenuName());
            existingMenu.setStock(menu.getStock());
            existingMenu.setImageUrl(menu.getImageUrl());
            existingMenu.setUnitPrice(menu.getUnitPrice());
            existingMenu.setDescription(menu.getDescription());
            existingMenu.setStatus(menu.getStatus());
            existingMenu.setCategory(menu.getCategory());

            // 不需要呼叫 session.update()，Hibernate 自動追蹤實體變更
            tx.commit();
            success = true;
            System.out.println("✅ 更新成功：" + menu.getMenuName());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("❌ 更新菜單失敗：" + e.getMessage());
            e.printStackTrace();
        }
        return success;
    }


    // **刪除菜單**
    @Override
    public boolean deleteMenu(String menuName) {
        boolean success = false;
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Query<?> query = session.createQuery("DELETE FROM MenuBean WHERE menuName = :menuName");
            query.setParameter("menuName", menuName);
            int rows = query.executeUpdate();
            tx.commit();
            success = rows > 0;
            System.out.println(success ? "✅ 成功刪除菜單：" + menuName : "❌ 刪除失敗，菜單不存在：" + menuName);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("❌ 刪除菜單失敗：" + e.getMessage());
            e.printStackTrace();
        }
        return success;
    }

    // **查詢單筆菜單**
    @Override
    public MenuBean getMenuByName(String menuName) {
        Transaction transaction = null;
        MenuBean menu = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            System.out.println("🔍 查詢 SQL：menu_name = " + menuName);

            Query<MenuBean> query = session.createQuery(
                "FROM MenuBean WHERE menuName = :name", MenuBean.class);
            query.setParameter("name", menuName.trim());  // ✅ 確保沒有多餘空格

            menu = query.uniqueResult();

            if (menu == null) {
                System.out.println("❌ 找不到菜單：" + menuName);
            } else {
                System.out.println("✅ 找到菜單：" + menu.getMenuName());
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return menu;
    }
    // **查詢所有菜單**
    @Override
 // ✅ 使用 Hibernate 查詢所有菜單
    public List<MenuBean> getAllMenus() {
        List<MenuBean> menuList = null;
        try (Session session = sessionFactory.openSession()) {
            Query<MenuBean> query = session.createQuery("FROM MenuBean ORDER BY category, menuName", MenuBean.class);
            menuList = query.getResultList();
            System.out.println("✅ 查詢成功，共 " + menuList.size() + " 筆資料");
        } catch (Exception e) {
            System.out.println("❌ 查詢所有菜單失敗：" + e.getMessage());
            e.printStackTrace();
        }
        return menuList;
    }

    // **查詢菜單價格**
    @Override
    public int getPriceByMenuName(String menuName) {
        int price = 0;
        try (Session session = sessionFactory.openSession()) {
            Query<Integer> query = session.createQuery("SELECT unit_price FROM MenuBean WHERE menu_name = :menuName", Integer.class);
            query.setParameter("menuName", menuName);
            price = query.uniqueResult();
            System.out.println("✅ 查詢價格成功：" + menuName + " = " + price);
        } catch (Exception e) {
            System.out.println("❌ 查詢價格失敗：" + e.getMessage());
            e.printStackTrace();
        }
        return price;
    }

}
