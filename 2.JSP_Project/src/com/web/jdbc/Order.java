package com.web.jdbc;

import java.util.Objects;

public class Order {

	private int orderNumber;
	private int tableNumber;
	private String menuName;
	private int orderQuantity;
	private int orderState;
	private String orderTime;
	
	public Order(int tableNumber, String menuName, int orderQuantity) {
		super();
		this.tableNumber = tableNumber;
		this.menuName = menuName;
		this.orderQuantity = orderQuantity;

	}
	
	public Order(int tableNumber, String menuName, int orderQuantity, int orderState) {
		super();
		this.tableNumber = tableNumber;
		this.menuName = menuName;
		this.orderQuantity = orderQuantity;
		this.orderState = orderState;
	}
	
	public Order(int tableNumber, String menuName, int orderQuantity, int orderState, String orderTime) {
		super();
		this.tableNumber = tableNumber;
		this.menuName = menuName;
		this.orderQuantity = orderQuantity;
		this.orderState = orderState;
		this.orderTime = orderTime;
	}
	
	
	
	public Order(String menuName, int orderQuantity, int orderState, String orderTime) {
		super();
		this.menuName = menuName;
		this.orderQuantity = orderQuantity;
		this.orderState = orderState;
		this.orderTime = orderTime;
	}

	public Order() {
		
		
	}
	
	
	
	public Order(int orderNumber, int tableNumber, String menuName, int orderQuantity, int orderState) {
		super();
		this.orderNumber = orderNumber;
		this.tableNumber = tableNumber;
		this.menuName = menuName;
		this.orderQuantity = orderQuantity;
		this.orderState = orderState;
	}

	public int getTableNumber() {
		return tableNumber;
	}
	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}
	@Override
	public int hashCode() {
		return Objects.hash(menuName, tableNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(menuName, other.menuName) && tableNumber == other.tableNumber;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public int getOrderState() {
		return orderState;
	}
	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
}
