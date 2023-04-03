package com.web.jdbc;

public class MenuData {
	
	private int categoryId;
	private int price;
	private String menuName;

	
	public MenuData(int categoryId, int price, String menuName) {
		super();
		this.categoryId = categoryId;
		this.price = price;
		this.menuName = menuName;
	
	}
	
	public MenuData() {}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuNmae) {
		this.menuName = menuNmae;
	}

	@Override
	public String toString() {
		return "MenuData [categoryId=" + categoryId + ", price=" + price + ", menuName=" + menuName
				 + "]";
	}
	
	
}
