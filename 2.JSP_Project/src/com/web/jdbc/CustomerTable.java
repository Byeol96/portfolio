package com.web.jdbc;

public class CustomerTable {
	
	private int tableNo;
	private int tableRow;
	private int tableCol;
	
	public int getTableRow() {
		return tableRow;
	}
	public void setTableRow(int tableRow) {
		this.tableRow = tableRow;
	}
	public int getTableNo() {
		return tableNo;
	}
	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}
	public int getTableCol() {
		return tableCol;
	}
	public void setTableCol(int tableCol) {
		this.tableCol = tableCol;
	}
	public CustomerTable(int tableNo, int tableRow, int tableCol) {
		super();
		this.tableNo = tableNo;
		this.tableRow = tableRow;
		this.tableCol = tableCol;
	}
	public CustomerTable(int tableRow, int tableCol) {
		super();
		this.tableRow = tableRow;
		this.tableCol = tableCol;
	}
	
	
}





   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   