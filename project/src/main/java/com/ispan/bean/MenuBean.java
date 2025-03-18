package com.ispan.bean;



import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu")
public class MenuBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "menu_id")
	private int menuId;

	@Column(name = "menu_name", nullable = false, unique = true)
	private String menuName;

	@Column(name = "stock", nullable = false)
	private int stock;

	@Column(name = "image_url", length = 255)
	private String imageUrl;

	@Column(name = "unit_price")
	private double unitPrice;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@Column(name = "status", nullable = false)
	private String status;

	@Column(name = "category", nullable = false)
	private String category;

//    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<OrderDetails> orderDetails = new ArrayList<>();

	public MenuBean() {
	}

	public MenuBean(String menuName, int stock, String imageUrl, double unitPrice, String description, String status,
			String category) {
		this.menuName = menuName;
		this.stock = stock;
		this.imageUrl = imageUrl;
		this.unitPrice = unitPrice;
		this.description = description;
		this.status = status;
		this.category = category;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	}


	    