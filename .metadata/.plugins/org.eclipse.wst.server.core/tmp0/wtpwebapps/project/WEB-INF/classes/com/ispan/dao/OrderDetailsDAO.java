package com.ispan.dao;

import com.ispan.bean.OrderDetailsBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDAO {
    private DataSource dataSource;

    // **初始化連線池**
    public OrderDetailsDAO() throws NamingException {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/movietheater");
            System.out.println("✅ DAO 初始化成功！");
        } catch (NamingException e) {
            System.out.println("❌ DAO 初始化失敗：" + e.getMessage());
            e.printStackTrace(); // 🔥 這行可以印出完整錯誤資訊
            throw e;
        }
    }

    // **新增訂單明細**
    public void insertOrderDetail(OrderDetailsBean orderDetail) {
        String sql = "INSERT INTO order_details (order_id, menu_name, quantity, purchase_price, subtotal) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderDetail.getOrder_id());
            pstmt.setString(2, orderDetail.getMenu_name());
            pstmt.setInt(3, orderDetail.getQuantity());
            pstmt.setInt(4, orderDetail.getPurchase_price());
            pstmt.setInt(5, orderDetail.getSubtotal());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // **透過 order_id 查詢訂單明細**
    public List<OrderDetailsBean> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetailsBean> orderDetailsList = new ArrayList<>();
        String sql = "SELECT detail_id, order_id, menu_name, quantity, purchase_price, subtotal FROM order_details WHERE order_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                OrderDetailsBean orderDetail = new OrderDetailsBean(
                        rs.getInt("detail_id"),
                        rs.getInt("order_id"),
                        rs.getString("menu_name"),
                        rs.getInt("quantity"),
                        rs.getInt("purchase_price"),
                        rs.getInt("subtotal")
                );
                orderDetailsList.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetailsList;
    }

    // **更新訂單明細 (數量 & 小計)**
    public void updateOrderDetail(int detailId, int newQuantity, int newSubtotal) {
        String sql = "UPDATE order_details SET quantity = ?, subtotal = ? WHERE detail_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, newQuantity);
            pstmt.setInt(2, newSubtotal);
            pstmt.setInt(3, detailId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // **刪除訂單明細**
    public void deleteOrderDetail(int detailId) {
        String sql = "DELETE FROM order_details WHERE detail_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, detailId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
