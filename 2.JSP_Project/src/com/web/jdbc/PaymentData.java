package com.web.jdbc;

import java.util.Objects;

public class PaymentData {

	private int table_no;
	private String menu_name;
	private int menu_quantity;
	private int menu_price;
	
	public PaymentData(int table_no, String menu_name, int menu_quantity, int menu_price) {
		super();
		this.table_no = table_no;
		this.menu_name = menu_name;
		this.menu_quantity = menu_quantity;
		this.menu_price = menu_price;
	}



	@Override
	public int hashCode() {
		return Objects.hash(menu_name, table_no);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentData other = (PaymentData) obj;
		return Objects.equals(menu_name, other.menu_name) && table_no == other.table_no;
	}

	public int getTable_no() {
		return table_no;
	}

	public void setTable_no(int table_no) {
		this.table_no = table_no;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public int getMenu_quantity() {
		return menu_quantity;
	}

	public void setMenu_quantity(int menu_quantity) {
		this.menu_quantity = menu_quantity;
	}

	public int getMenu_price() {
		return menu_price;
	}

	public void setMenu_price(int menu_price) {
		this.menu_price = menu_price;
	}
	
	
}