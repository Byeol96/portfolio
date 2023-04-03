package com.web.jdbc;

import java.util.Objects;

public class CashUpData implements Comparable<CashUpData> {
	
	private String menu_name;
	private int menu_quantity;
	private int menu_price;
	
	public CashUpData(String menu_name, int menu_quantity, int menu_price) {
		super();
		this.menu_name = menu_name;
		this.menu_quantity = menu_quantity;
		this.menu_price = menu_price;
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

	@Override
	public int hashCode() {
		return Objects.hash(menu_name);
	}
	
	@Override
	public int compareTo(CashUpData cashUpData) {
        // TODO Auto-generated method stub
        if (this.menu_price*this.menu_quantity < cashUpData.menu_price*cashUpData.menu_quantity) {
            return -1;
        } else if (this.menu_price*this.menu_quantity == cashUpData.menu_price*cashUpData.menu_quantity) {
            return 0;
        } else {
            return 1;
        }
    }
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CashUpData other = (CashUpData) obj;
		return Objects.equals(menu_name, other.menu_name);
	}

	@Override
	public String toString() {
		return "CashUpData [menu_name=" + menu_name + ", menu_quantity=" + menu_quantity + ", menu_price=" + menu_price
				+ "]";
	}
	
	
}