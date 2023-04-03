package com.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

class DataUtil {

	private DataSource dataSource;

	public DataUtil(DataSource datasource) {

		this.dataSource = datasource;
	}

	public void close(Connection conn, Statement mySt, ResultSet myRs) {

		try {
			if (conn != null)
				conn.close();
			if (mySt != null)
				mySt.close();
			if (myRs != null)
				myRs.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	/** */
	public String tableListString() throws SQLException {
		String tableList = "";
		Connection conn = null;
		Statement mySt = null;
		ResultSet myRs = null;

		try {
			conn = dataSource.getConnection();
			String sql = "SELECT table_no FROM customertable";
			mySt = conn.createStatement();
			myRs = mySt.executeQuery(sql);
			if (myRs.next())
				tableList += myRs.getString("table_no");

			while (myRs.next()) {
				tableList += "," + myRs.getString("table_no");
			}
			return tableList;
		} finally {
			close(conn, mySt, myRs);

		}

	}
	/** 모든 주문 내역중 결제완료되지 않은 내역(상태가 1,2,3인 ) 전부를 뽑아줌 */
	public ArrayList<Order> OrderData() {

		Connection conn = null;
		Statement mySt = null;
		ResultSet myRs = null;
		ArrayList<Order> orderList = new ArrayList<>();
		Order order;

		try {
			conn = dataSource.getConnection();
			String sql = "SELECT table_no,menu_name,quantity FROM orderlist WHERE orderState!=4";
			mySt = conn.createStatement();
			myRs = mySt.executeQuery(sql);

			while (myRs.next()) {

				order = new Order(myRs.getInt("table_no"), myRs.getString("menu_name"), myRs.getInt("quantity"));
				orderList.add(order);
			}

			return orderList;

		} catch (Exception e) {

			e.printStackTrace();
			return orderList;

		} finally {
			close(conn, mySt, myRs);

		}

	}
	/** 주문 내역(OrderClass 의 객체 ArrayList)을 넣어주면 계란 1 계란 1 를 계란 2 로 합쳐서 반환해주는 함수 */
	public ArrayList<Order> OrderDataMerge(ArrayList<Order> order) {
		// TODO Auto-generated method stub
		
		ArrayList<Order> orderDatas = new ArrayList<>();
		
		for(Order a : order) {
			
			int resultIndex = orderDatas.indexOf(a); 
			if(resultIndex>=0) {
				
				orderDatas.get(resultIndex).setOrderQuantity(orderDatas.get(resultIndex)
						.getOrderQuantity()+a.getOrderQuantity());
				
			}else {
				
				orderDatas.add(a);
			}
		}
		
		
		return orderDatas;
		
	}
	/** 모든 주문 내역중 주문 상태를 매개로 받아 그 상태에 있는 주문 내역만을 뽑아주는 함수 */ 
	public ArrayList<Order> OrderData(int status) {

		Connection conn = null;
		Statement mySt = null;
		ResultSet myRs = null;
		ArrayList<Order> orderList = new ArrayList<>();
		Order order;

		try {
			conn = dataSource.getConnection();
			String sql = "SELECT order_no,table_no,menu_name,quantity FROM orderlist WHERE orderState=" + status;
			mySt = conn.createStatement();
			myRs = mySt.executeQuery(sql);

			while (myRs.next()) {

				order = new Order(myRs.getInt("order_no"), myRs.getInt("table_no"), myRs.getString("menu_name"),
						myRs.getInt("quantity"), status);
				orderList.add(order);
			}

			return orderList;

		} catch (Exception e) {
			
			System.out.println("OrderData 실행중 에러발생");
			e.printStackTrace();
			return orderList;
		} finally {
			close(conn, mySt, myRs);

		}

	}
	/** 테이블위치를 알려주는 데이터 출력 4*8 배열에 해당 위치에 해당 테이블 번호가 포함되서 전달*/
	public int[][] TableData() {

		Connection conn = null;
		Statement mySt = null;
		ResultSet myRs = null;
		int[][] tableLocation = { { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 } };

		try {
			conn = dataSource.getConnection();
			String sql = "select * FROM customertable";
			mySt = conn.createStatement();
			myRs = mySt.executeQuery(sql);

			while (myRs.next()) {

				int number = myRs.getInt("table_no");
				int rows = myRs.getInt("table_row");
				int cols = myRs.getInt("table_col");

				tableLocation[rows][cols] = number;
			}
			return tableLocation;

		} catch (Exception e) {

			e.printStackTrace();
			return tableLocation;
		} finally {
			close(conn, mySt, myRs);

		}
	}
	/** 주문 번호를 입력하면 주문의 상태를 바꿔주는 함수 1 ->2 / 2->3 */
	public void updateState(int orderNumber) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement mySt = null;

		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE orderlist SET orderState = IF( (orderState = 1) "
					+ "OR (orderState = 2) , orderState+1 ,orderState) WHERE order_no ="
					+ orderNumber;
			mySt = conn.createStatement();

			mySt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("updateState 실행중 에러발생");
			e.printStackTrace();
		} finally {
			close(conn, mySt, null);

		}

	}
	/** 모든 카테고리번호와 카테고리명을 하나로 묶어서 출력 */
	public List<CategoryData> categoryDatas() {
		
		List<CategoryData> categories = new ArrayList<>();

		Connection conn = null;
		Statement mySt = null;
		ResultSet myRs = null;

		try {
			conn = dataSource.getConnection();
			String sql = "select * FROM category";
			mySt = conn.createStatement();
			myRs = mySt.executeQuery(sql);

			while (myRs.next()) {
				int num = myRs.getInt("category_id");
				String name = myRs.getString("name");

				CategoryData category = new CategoryData(num, name);
				categories.add(category);
			}
			return categories;
		} catch (SQLException e) {
			e.printStackTrace();
			return categories;
		} finally {

			close(conn, mySt, myRs);
		}
	}
	/** 각 카테고리별로 ArrayList를 만들어서 모든 리스트를 다시 하나의 리스트로 합쳐서 반환해주는 함수 */
	public List<List<MenuData>> menuDatas() {

		List<List<MenuData>> menus = new ArrayList<>();
		Connection conn = null;
		Statement mySt = null;
		ResultSet myRs = null;

		int categoryCount = menuDataCount();

		for (int i = 0; i < categoryCount; i++) {

			menus.add(new ArrayList<MenuData>());
		}

		try {
			conn = dataSource.getConnection();
			String sql = "select * FROM menu";
			mySt = conn.createStatement();
			myRs = mySt.executeQuery(sql);

			while (myRs.next()) {

				// MenuData(int categoryId, int price, String menuNmae, String menuExplanation)
				int categoryId = myRs.getInt("category_id");
				int price = myRs.getInt("price");
				String menuName = myRs.getString("menu_name");
		

				MenuData menu = new MenuData(categoryId, price, menuName);

				menus.get(categoryId - 1).add(menu);
			}
			return menus;
		} catch (Exception e) {

			e.printStackTrace();
			return menus;
		} finally {

			close(conn, mySt, myRs);
		}

	}
	/** 메뉴명을 넣어주면 메뉴에대한 카테고리 번호와 가격 메뉴가격을 MenuData 클래스의 객체로 반환 */
	public MenuData menuData(String MenuName) {

		MenuData menu = new MenuData();
		Connection conn = null;
		Statement mySt = null;
		ResultSet myRs = null;

		System.out.println("3: " + MenuName);

		try {
			conn = dataSource.getConnection();
			String sql = "select * FROM menu WHERE menu_name ='" + MenuName + "'";
			System.out.println("4: " + sql);
			mySt = conn.createStatement();
			myRs = mySt.executeQuery(sql);

			while (myRs.next()) {

				// MenuData(int categoryId, int price, String menuNmae, String menuExplanation)
				int categoryId = myRs.getInt("category_id");
				int price = myRs.getInt("price");
				String menuName = myRs.getString("menu_name");

				menu.setCategoryId(categoryId);
				menu.setMenuName(menuName);
				menu.setPrice(price);

			}
			return menu;
		} catch (Exception e) {

			e.printStackTrace();
			return menu;
		} finally {

			close(conn, mySt, myRs);
		}

	}
	/** 카테고리의 개수를 출력해주는 함수*/
	private int menuDataCount() {

		Connection conn = null;
		Statement mySt = null;
		ResultSet myRs = null;
		int count = 0;

		try {
			conn = dataSource.getConnection();
			String sql = "SELECT count(distinct category_id) as count FROM menu";
			mySt = conn.createStatement();
			myRs = mySt.executeQuery(sql);

			myRs.next();
			count = myRs.getInt("count");
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return count;
		} finally {

			close(conn, mySt, myRs);
		}

	}
	/** 테이블 번호 테이블의 행과 열이 포함된 CustomerTable객체를 넣어주면 그 정보에 따라 테이블 추가 */
	public void addTable(CustomerTable newTable) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement mySt = null;

		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO customertable VALUES (?,?,?)";
			mySt = conn.prepareStatement(sql);

			mySt.setInt(1, newTable.getTableNo());
			mySt.setInt(2, newTable.getTableRow());
			mySt.setInt(3, newTable.getTableCol());

			mySt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			close(conn, mySt, null);
		}
	}
	/** 테이블의 행과 열이 포함된 CustomerTable 객체를 넣어주면 그 위치에 있는 테이블을 삭제*/
	public void deleteTable(CustomerTable newTable) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement mySt = null;

		try {
			conn = dataSource.getConnection();
			String sql = "DELETE FROM customertable WHERE table_row = ? AND table_col = ?;";
			mySt = conn.prepareStatement(sql);

			mySt.setInt(1, newTable.getTableRow());
			mySt.setInt(2, newTable.getTableCol());

			mySt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			close(conn, mySt, null);
		}
	}
	/** 현재의 행과 열이 포함된 객체와 바꿀 위치의 행과 열이 포함된CustomerTable 객체 2개를 넣어주면 위치를 Update해주는 함수*/
	public void changeTableLocation(CustomerTable originalLocationTable, CustomerTable newLocationTable) {
		// TODO Auto-generated method stub

		Connection conn = null;
		PreparedStatement mySt = null;

		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE customertable SET table_row = ? , table_col = ? WHERE table_row =? AND table_col =?;";
			mySt = conn.prepareStatement(sql);

			mySt.setInt(1, newLocationTable.getTableRow());
			mySt.setInt(2, newLocationTable.getTableCol());
			mySt.setInt(3, originalLocationTable.getTableRow());
			mySt.setInt(4, originalLocationTable.getTableCol());

			mySt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			close(conn, mySt, null);
		}
	}
	/** */
	public void changeTableNumber(CustomerTable newNumberTable) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement mySt = null;

		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE customertable SET table_no = ? WHERE table_row =? AND table_col =?;";
			mySt = conn.prepareStatement(sql);

			mySt.setInt(1, newNumberTable.getTableNo());
			mySt.setInt(2, newNumberTable.getTableRow());
			mySt.setInt(3, newNumberTable.getTableCol());

			mySt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			close(conn, mySt, null);
		}
	}
	/** 테이블번호 메뉴이름 String배열 , 메뉴 양 String 배열을 넣어주면 주문을 insert 하는 함수 */
	public void order(int tableNumber, String[] menuNames, String[] menuQuantity) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement mySt = null;
		try {
			conn = dataSource.getConnection();
			StringBuffer sql = new StringBuffer(" INSERT INTO orderlist(table_no,menu_name,quantity) VALUES(?,?,?) ");
			mySt = conn.prepareStatement(sql.toString());

			for (int i = 0; i < menuNames.length; i++) {
				mySt.setInt(1, tableNumber);
				mySt.setString(2, menuNames[i]);
				mySt.setString(3, menuQuantity[i]);
				mySt.addBatch();
				mySt.clearParameters();
			}
			mySt.executeBatch();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			close(conn, mySt, null);
		}
	}
	/** 테이블 번호 입력하면 결제되지 않은 테이블의 주문 정보를 Order객체에 담아 ArrayList형태로 출력*/
	public ArrayList<Order> orderList(int tableNumber) {
		// TODO Auto-generated method stubOrder
		
		ArrayList<Order> orders = new ArrayList<>();
		Connection conn = null;
		Statement mySt = null;
		ResultSet myRs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM orderlist WHERE orderState != 4 AND table_no = " + tableNumber;
			mySt = conn.createStatement();
			myRs = mySt.executeQuery(sql);

			while (myRs.next()) {

				Order order = new Order(myRs.getString("menu_name"), myRs.getInt("quantity"), myRs.getInt("orderState"),
						myRs.getString("orderTime"));
				orders.add(order);
			}
			return orders;

		} catch (Exception e) {
			e.printStackTrace();
			return orders;

		} finally {

			close(conn, mySt, myRs);
		}

	}

	public void deleteMenus(String[] deleteMenuNames) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement mySt = null;
		try {
			conn = dataSource.getConnection();
			StringBuffer sql = new StringBuffer("delete from menu where menu_name=?; ");
			mySt = conn.prepareStatement(sql.toString());

			for (int i = 0; i < deleteMenuNames.length; i++) {
				mySt.setString(1, deleteMenuNames[i]);
				mySt.addBatch();
				mySt.clearParameters();
			}
			mySt.executeBatch();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			close(conn, mySt, null);
		}
	}
	
	public ArrayList<CashUpData> cashUpData(String date) {
		// TODO Auto-generated method stub
		ArrayList<CashUpData> cashUpDatas = new ArrayList<>();
		Connection conn = null;
		Statement mySt = null;
		ResultSet myRs = null;
		
		try { 
			conn = dataSource.getConnection();
			String sql = "select * from orderlist natural join menu where DATE(orderTime) = '"+date+"'";
			mySt = conn.createStatement();
			myRs = mySt.executeQuery(sql);

			while (myRs.next()) {

				
				CashUpData cashUpData = new CashUpData(myRs.getString("menu_name"), myRs.getInt("quantity"),
						myRs.getInt("price"));
				cashUpDatas.add(cashUpData);
			}
			return cashUpDatas;

		} catch (Exception e) {
			e.printStackTrace();
			return cashUpDatas;

		} finally {

			close(conn, mySt, myRs);
		}
	}
	
	public ArrayList<CashUpData> cashUpData(String startDate, String endDate) {
		// TODO Auto-generated method stub
		ArrayList<CashUpData> cashUpDatas = new ArrayList<>();
		Connection conn = null;
		Statement mySt = null;
		ResultSet myRs = null;
		
		try { 
			conn = dataSource.getConnection();
			String sql = "select * from orderlist natural join menu where DATE(orderTime) between '"+startDate+"' AND '"+endDate+"'";
			mySt = conn.createStatement();
			myRs = mySt.executeQuery(sql);

			while (myRs.next()) {

				
				CashUpData cashUpData = new CashUpData(myRs.getString("menu_name"), myRs.getInt("quantity"),
						myRs.getInt("price"));
				cashUpDatas.add(cashUpData);
			}
			return cashUpDatas;

		} catch (Exception e) {
			e.printStackTrace();
			return cashUpDatas;

		} finally {

			close(conn, mySt, myRs);
		}
	}
	
	public ArrayList<CashUpData> cashUpDataMerge(ArrayList<CashUpData> inputCashUpDatas){
		
		ArrayList<CashUpData> cashUpDatas = new ArrayList<>();
		
		for(CashUpData a : inputCashUpDatas) {
			
			int resultIndex = cashUpDatas.indexOf(a); 
			if(resultIndex>=0) {
				
				cashUpDatas.get(resultIndex).setMenu_quantity(cashUpDatas.get(resultIndex).getMenu_quantity()+a.getMenu_quantity());
				
			}else {
				
				cashUpDatas.add(a);
			}
		}
		
		
		return cashUpDatas;
		
		
	}
	
	public ArrayList<PaymentData> paymentData(int tableNumber){
		
		
		ArrayList<PaymentData> paymentDatas = new ArrayList<>();
		Connection conn = null;
		Statement mySt = null;
		ResultSet myRs = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "select * from orderlist natural join menu where orderState != 1 AND orderState != 4 AND table_no =" + tableNumber;
			mySt = conn.createStatement();
			myRs = mySt.executeQuery(sql);

			while (myRs.next()) {

				
				PaymentData paymentData = new PaymentData(myRs.getInt("table_no"), myRs.getString("menu_name"), myRs.getInt("quantity"),
						myRs.getInt("price"));
				paymentDatas.add(paymentData);
			}
			return paymentDatas;

		} catch (Exception e) {
			e.printStackTrace();
			return paymentDatas;

		} finally {

			close(conn, mySt, myRs);
		}
	}
	
	public ArrayList<PaymentData> paymentDataMerge(ArrayList<PaymentData> inputPaymentDatas){
		
		ArrayList<PaymentData> paymentDatas = new ArrayList<>();
		
		for(PaymentData a : inputPaymentDatas) {
			
			int resultIndex = paymentDatas.indexOf(a); 
			if(resultIndex>=0) {
				
				paymentDatas.get(resultIndex).setMenu_quantity(paymentDatas.get(resultIndex).getMenu_quantity()+a.getMenu_quantity());
				
			}else {
				
				paymentDatas.add(a);
			}
		}
		
		
		return paymentDatas;
		
		
	}

	public void payment(int tableNumber) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement mySt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "update  orderlist SET orderState = 4 where table_no =? AND (orderState = 2 OR orderState = 3);";
			mySt =  conn.prepareStatement(sql);
			
			mySt.setInt(1, tableNumber);
			mySt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			

		} finally {

			close(conn, mySt, null);
		}
	}

	public void paymentDelete(int tableNumber) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement mySt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "delete from orderlist where table_no =? AND orderState = 1;";
			mySt =  conn.prepareStatement(sql);
			
			mySt.setInt(1, tableNumber);
			mySt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("gg");

		} finally {

			close(conn, mySt, null);
		}
	}
	
	public void paymentDelete(int tableNumber,String[] menuNames,String[] orderDateTimes) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement mySt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "delete from orderlist where table_no =? AND orderState = 1;";
			mySt =  conn.prepareStatement(sql);
			
			mySt.setInt(1, tableNumber);
			mySt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("gg");

		} finally {

			close(conn, mySt, null);
		}
	}
	
	public void addMenu(MenuData menu) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement mySt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "insert into menu VALUES (?,?,?)";
			mySt =  conn.prepareStatement(sql);
			
			mySt.setString(1, menu.getMenuName());
			mySt.setInt(2, menu.getPrice());
			mySt.setInt(3, menu.getCategoryId());
			mySt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			

		} finally {

			close(conn, mySt, null);
		}
	}

	// 해당 테이블에 해당시간에 주문한 메뉴들을 삭제
	public void deleteOrders(int tableNumber, ArrayList<String> deleteMenus, ArrayList<String> deleteTimes) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement mySt = null;
		try {
			conn = dataSource.getConnection();
			StringBuffer sql = new StringBuffer("delete from orderlist where table_no = ? AND menu_name = ? AND orderTime=?; ");
			mySt = conn.prepareStatement(sql.toString());

			for (int i = 0; i < deleteMenus.size(); i++) {
				mySt.setInt(1, tableNumber);
				mySt.setString(2,deleteMenus.get(i));
				mySt.setString(3,deleteTimes.get(i));
				mySt.addBatch();
				mySt.clearParameters();
			}
			mySt.executeBatch();
		} catch (SQLException e) {
			System.out.println("deleteOrders 함수 실행중 에러발생");
			e.printStackTrace();
		} finally {

			close(conn, mySt, null);
		}
	}

	public void updateMenu(String menuName, int menuCost) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement mySt = null;
		try {
			System.out.println("메뉴명 :"+menuName+", 메뉴 가격 :"+menuCost);
			conn = dataSource.getConnection();
			StringBuffer sql = new StringBuffer("UPDATE menu SET price = ? where menu_name =?; ");
			mySt = conn.prepareStatement(sql.toString());
			mySt.setInt(1,menuCost);
			mySt.setString(2, menuName);
			mySt.execute();
		} catch (SQLException e) {

			e.printStackTrace();
			
			
		} finally {

			close(conn, mySt, null);
		}
	}

	

	

}
