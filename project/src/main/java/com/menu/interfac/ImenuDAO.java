package com.menu.interfac;

import java.util.List;

import com.menu.bean.MenuBean;

public interface ImenuDAO {
	    boolean insertMenu(MenuBean menu);
	    boolean updateMenu(MenuBean menu);
	    boolean deleteMenu(String menuName);
	    MenuBean getMenuByName(String menuName);
	    List<MenuBean> getAllMenus();
	    
	    int getPriceByMenuName(String menuName);
	}

