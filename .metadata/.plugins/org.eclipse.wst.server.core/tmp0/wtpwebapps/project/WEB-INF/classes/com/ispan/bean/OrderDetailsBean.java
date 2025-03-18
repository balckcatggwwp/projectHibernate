package com.ispan.bean;

import java.io.Serializable;

public class OrderDetailsBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private int detail_id; // 訂單明細 ID (主鍵)
    private int order_id; // 關聯訂單 ID
    private String menu_name; // 餐點名稱
    private int quantity; // 購買數量
    private int purchase_price; // 購買價格 (歷史價格快照)
    private int subtotal; // 小計 (quantity * purchasePrice)

    public OrderDetailsBean() {}

    public OrderDetailsBean(int detail_id, int order_id, String menu_name, int quantity, int purchase_price, int subtotal) {
        this.detail_id = detail_id;
        this.order_id = order_id;
        this.menu_name = menu_name;
        this.quantity = quantity;
        this.purchase_price = purchase_price;
        this.subtotal = subtotal;
    }

	public int getDetail_id() {
		return detail_id;
	}

	public void setDetail_id(int detail_id) {
		this.detail_id = detail_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPurchase_price() {
		return purchase_price;
	}

	public void setPurchase_price(int purchase_price) {
		this.purchase_price = purchase_price;
	}

	public int getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
    public String toString() {
        return "OrderDetailsBean{" +
                "orderDetailId=" + detail_id +
                ", orderId=" + order_id +
                ", menuName='" + menu_name + '\'' +
                ", quantity=" + quantity +
                ", purchasePrice=" + purchase_price +
                ", subtotal=" + subtotal +
                '}';
}
}