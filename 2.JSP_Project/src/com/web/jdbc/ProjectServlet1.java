package com.web.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ProjectServlet1
 */
@WebServlet("/ProjectServlet1")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 50
)
public class ProjectServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;


    @Resource(name = "jdbc/JSP_Project")
	private DataSource dataSource;
    
    private DataUtil dataUtil;
    
    public void init() throws ServletException {
	
		dataUtil = new DataUtil(dataSource);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String command = "initial";
		if(request.getParameter("command")!=null) {
			command=request.getParameter("command");
		}
		
		switch(command) {
		
		case "initial" : 
			tableList(request,response);
			break;
		case "movePage" : 
			movePage(request,response);
			break;
		case "KitchenData" :
			KitchenData(request,response);
			break;
		case "CounterData" :
			CounterData(request,response);
			break;
		case "updateState" :
			UpdateState(request,response);
			break;
		case "TableAdd" :
			TableAdd(request,response);
			break;
		case "TableDelete" :
			TableDelete(request,response);
			break;
		case "TableMove" :
			TableMove(request,response);
			break;
		case "TableChange" :
			TableChange(request,response);
			break;
		case "Order" :
			Order(request,response);
			break;
		case "OrderList" :
			OrederList(request,response);
			break;
		case "PopUp_AddMenu_Move" :
			PopUp_AddMenu_Move(request,response);
			break;
		case "PopUp_DeleteMenu_Move" :
			PopUp_DeleteMenu_Move(request,response);
			break;
		case "PopUp_UpdateMenu_Move" :
			PopUp_UpdateMenu_Move(request,response);
			break;
		case "PopUp_UpdateMenuPage_Move" :
			PopUp_UpdateMenuPage_Move(request,response);
			break;
		case "PopUp_SoldOutMenu_Move" :
			PopUp_SoldOutMenu_Move(request,response);
			break;
		case "MenuDelete" :
			MenuDelete(request,response);
			break;
		case "PayMentPage" :
			PayMentPage(request,response);
			break;
		case "Payment" :
			Payment(request,response);
			break;
		case "PopUp_CashUp":
			PopUp_cashUp(request,response);
			break;
		case "PopUp_BetweenDateCashUp" :
			PopUp_BetweenDateCashUp(request,response);
			break;
		case "OrderDelete" :
			OrderDelete(request,response);
			break;
		case "MenuAdd" :
			MenuAdd(request,response);
			break;
		case "MenuUpdate" :
			MenuUpdate(request,response);
			break;
		}
		
		
		
	}


	private void OrderDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] deleteMenuNames = request.getParameterValues("deleteMenus");
		int tableNumber= Integer.parseInt(request.getParameter("tableNumber"));
		ArrayList<String> deleteTimes = new ArrayList<>();
		ArrayList<String> deleteMenus = new ArrayList<>();
		for(String a : deleteMenuNames) {
			deleteMenus.add(a.split(",")[0]);
			deleteTimes.add(a.split(",")[1]);
		}
		
		dataUtil.deleteOrders(tableNumber,deleteMenus,deleteTimes);
		request.setAttribute("move", "yes");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/PopUp_Exit.jsp");
		dispatcher.forward(request, response);
	}
	private void PopUp_BetweenDateCashUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		ArrayList<CashUpData> cashUpData = dataUtil.cashUpData(startDate,endDate);
		ArrayList<CashUpData> cashUpDatasDataMerge = dataUtil.cashUpDataMerge(cashUpData);
		Collections.sort(cashUpDatasDataMerge,Collections.reverseOrder());
		
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("cashUpDatas", cashUpDatasDataMerge);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/PopUp_CashUp.jsp");
		dispatcher.forward(request, response);
	}
	
	private void PopUp_cashUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String nowFM = format.format(now);
        
		ArrayList<CashUpData> cashUpData = dataUtil.cashUpData(nowFM);
		ArrayList<CashUpData> cashUpDatasDataMerge = dataUtil.cashUpDataMerge(cashUpData);
		Collections.sort(cashUpDatasDataMerge,Collections.reverseOrder());
		
		request.setAttribute("cashUpDatas", cashUpDatasDataMerge);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/PopUp_CashUp.jsp");
		dispatcher.forward(request, response);
	}
	private void Payment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int tableNumber = Integer.parseInt(request.getParameter("tableNumber"));
		
		dataUtil.payment(tableNumber);
		dataUtil.paymentDelete(tableNumber);
		
		response.sendRedirect("/PopUp_DeletePage.jsp");
	}
	private void PayMentPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//tdIndex='+tdIndex+'&trIndex='+trIndex
		
		int tableNumber = Integer.parseInt(request.getParameter("tableNumber"));
		System.out.println("테이블 번호  :"+tableNumber);
		ArrayList<PaymentData> paymentData = dataUtil.paymentData(tableNumber);
		ArrayList<PaymentData> paymentDataMerge = dataUtil.paymentDataMerge(paymentData);
	
		request.setAttribute("Orders", paymentDataMerge);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/PopUp_PaymentPage.jsp");
		dispatcher.forward(request, response);
		
		
	}
	private void MenuDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] deleteMenuNames = request.getParameterValues("deleteMenuName");
		for(String i : deleteMenuNames) System.out.println(i);
		dataUtil.deleteMenus(deleteMenuNames);
		
		
		response.sendRedirect("/PopUp_DeletePage.jsp");
	}
	private void PopUp_SoldOutMenu_Move(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<CategoryData> categories = dataUtil.categoryDatas();
		request.setAttribute("MenuCategory", categories);
		List<List<MenuData>> menus = dataUtil.menuDatas();
		request.setAttribute("Menus", menus);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/PopUp_SoldOut.jsp");
		dispatcher.forward(request, response);
	}
	private void PopUp_UpdateMenuPage_Move(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String menuName = request.getParameter("updateMenuName");
		System.out.println("1: "+menuName);
		MenuData menudata = dataUtil.menuData(menuName);
		System.out.println("2: "+menudata);
		request.setAttribute("UpdateMenu", menudata);
		List<CategoryData> categories = dataUtil.categoryDatas();
		request.setAttribute("MenuCategory", categories);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/PopUp_UpdateMenuPage.jsp");
		dispatcher.forward(request, response);
	}
	private void PopUp_UpdateMenu_Move(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<CategoryData> categories = dataUtil.categoryDatas();
		request.setAttribute("MenuCategory", categories);
		List<List<MenuData>> menus = dataUtil.menuDatas();
		request.setAttribute("Menus", menus);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/PopUp_Update.jsp");
		dispatcher.forward(request, response);
	}
	private void PopUp_DeleteMenu_Move(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<CategoryData> categories = dataUtil.categoryDatas();
		request.setAttribute("MenuCategory", categories);
		List<List<MenuData>> menus = dataUtil.menuDatas();
		request.setAttribute("Menus", menus);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/PopUp_Delete.jsp");
		dispatcher.forward(request, response);
	}
	private void PopUp_AddMenu_Move(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<CategoryData> categories = dataUtil.categoryDatas();
		request.setAttribute("MenuCategory", categories);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/PopUp_Add.jsp");
		dispatcher.forward(request, response);
	}
	private void OrederList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int tableNumber = Integer.parseInt(request.getParameter("tableNum"));
		
		ArrayList<Order> orders = dataUtil.orderList(tableNumber);
		request.setAttribute("TableNumber", tableNumber);
		request.setAttribute("OrderList", orders);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/orderListConfirmPage.jsp");
		dispatcher.forward(request, response);
		
	}
	private void Order(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int tableNumber = Integer.parseInt(request.getParameter("tableNum"));
		String orderMenuNames = request.getParameter("orderMenuNames");
		String orderMenuQuantity = request.getParameter("orderMenuQuantity");
		
		String[] menuNames = orderMenuNames.split(",");
		String[] menuQuantity = orderMenuQuantity.split(",");
		
		
		dataUtil.order(tableNumber,menuNames,menuQuantity);
	
		response.sendRedirect("ProjectServlet1?command=movePage&location=Table"+tableNumber);
		
	}
	private void TableChange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int trIndex = Integer.parseInt(request.getParameter("trIndex"));
		int tdIndex = Integer.parseInt(request.getParameter("tdIndex")); 
		int tableNum = Integer.parseInt(request.getParameter("tableNum"));
		
		CustomerTable newNumberTable = new CustomerTable(tableNum ,trIndex,tdIndex);
		
		dataUtil.changeTableNumber(newNumberTable);
		response.sendRedirect("ProjectServlet1?command=CounterData");
	}
	private void TableMove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int trIndex = Integer.parseInt(request.getParameter("trIndex"));
		int tdIndex = Integer.parseInt(request.getParameter("tdIndex")); 
		CustomerTable newLocationTable = new CustomerTable(trIndex,tdIndex);
		
		int btrIndex = Integer.parseInt(request.getParameter("btrIndex"));
		int btdIndex = Integer.parseInt(request.getParameter("btdIndex")); 
		CustomerTable originalLocationTable = new CustomerTable(btrIndex,btdIndex);
		
		dataUtil.changeTableLocation(originalLocationTable,newLocationTable);
		response.sendRedirect("ProjectServlet1?command=CounterData");
		
	}
	private void TableDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int trIndex = Integer.parseInt(request.getParameter("trIndex"));
		int tdIndex = Integer.parseInt(request.getParameter("tdIndex"));
		CustomerTable newTable = new CustomerTable(trIndex,tdIndex);

		dataUtil.deleteTable(newTable);
		response.sendRedirect("ProjectServlet1?command=CounterData");
	}
	private void TableAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// tableNum='+tableNumber+'&tdIndex='+tdIndex+'&trIndex='+trIndex;
		int tableNo = Integer.parseInt(request.getParameter("tableNum"));
		int trIndex = Integer.parseInt(request.getParameter("trIndex"));
		int tdIndex = Integer.parseInt(request.getParameter("tdIndex"));
		CustomerTable newTable = new CustomerTable(tableNo,trIndex,tdIndex);
		
		dataUtil.addTable(newTable);
		response.sendRedirect("ProjectServlet1?command=CounterData");
	}
	private void UpdateState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int orderNumber = Integer.parseInt(request.getParameter("OrderNumber"));
		dataUtil.updateState(orderNumber);
		
		response.sendRedirect("ProjectServlet1?command=KitchenData");
	}
	private void CounterData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int[][] tableLocation = dataUtil.TableData();
		request.setAttribute("Location", tableLocation);
		ArrayList<Order> Order = dataUtil.OrderData();
		ArrayList<Order> OrderMerge = dataUtil.OrderDataMerge(Order);
		request.setAttribute("Order", OrderMerge);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("ProjectServlet1?command=movePage&location=Counter");
		dispatcher.forward(request, response);
	}
	private void KitchenData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList<Order> Order1 = dataUtil.OrderData(1);
		ArrayList<Order> Order2 = dataUtil.OrderData(2);
		
		request.setAttribute("orderConfirm", Order1);
		request.setAttribute("beingCooked", Order2);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("ProjectServlet1?command=movePage&location=Kitchen");
		dispatcher.forward(request, response);
		
	}
	private void tableList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String tableList = dataUtil.tableListString();
			request.setAttribute("table", tableList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		RequestDispatcher dispatcher = request.getRequestDispatcher("/LoginPage.jsp");
		dispatcher.forward(request, response);
	}
	private void movePage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String href="/";
		String location = request.getParameter("location");
		if(!location.contains("Table")) {
			href+=location+"Page.jsp";
			System.out.println("1 :" +href);
		}else {
			List<List<MenuData>> menus = dataUtil.menuDatas();
			request.setAttribute("menuDatas", menus);
			int idx = location.indexOf("e")+1; 
			System.out.print("location: "+location +", idx : "+idx);
			href+="TablePage.jsp?tableNumber="+location.substring(idx);			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(href);
		dispatcher.forward(request, response);
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*
	 * protected void doPost(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { // TODO Auto-generated
	 * method stub String command = "initial";
	 * if(request.getParameter("command")!=null) {
	 * command=request.getParameter("command"); }
	 * 
	 * switch(command) { case "MenuAdd" : MenuAdd(request,response); break; case
	 * "MenuUpdate" : MenuUpdate(request,response); break; } }
	 */
	private void MenuUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//MenuName MenuCost
		request.setCharacterEncoding("UTF-8");
		String MenuName = request.getParameter("MenuName");
		int MenuCost = Integer.parseInt(request.getParameter("MenuCost"));
		
		dataUtil.updateMenu(MenuName,MenuCost);
		request.setAttribute("move", "yes"); 
		RequestDispatcher dispatcher = request.getRequestDispatcher("/PopUp_Exit.jsp");
		dispatcher.forward(request, response);
	}

	private void MenuAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// img 폴더의 실제 경로를 얻기위해 application 정의 해두기
//		ServletContext application = request.getServletContext();

		// 입력받은 메뉴명 메뉴 가격 메뉴카테고리
        String menuName = request.getParameter("MenuName");
        int menuCost = Integer.parseInt(request.getParameter("MenuCost"));
        int menuCategoryNum = Integer.parseInt(request.getParameter("MenuCategory"));
        MenuData menu = new MenuData(menuCategoryNum,menuCost,menuName);
        dataUtil.addMenu(menu);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/PopUp_DeletePage.jsp");
		dispatcher.forward(request, response);
        
        // input file의 name 을 
//        Part part = request.getPart("fileName");
//        String fileName = getFilename(part);
//        if (!fileName.isEmpty()) {
//        	System.out.println("C:\\Users\\12gks\\JSP\\JSP_Project\\WebContent\\img\\"+menuCategoryNum);
//            part.write("C:\\Users\\12gks\\JSP\\JSP_Project\\WebContent\\img\\"+menuCategoryNum);
// 
//        }
	}
		
//	private String getFilename(Part part) {
//        String contentDisp = part.getHeader("content-disposition");
//        System.out.println(contentDisp);
//        String[] split = contentDisp.split(";");
//        for (int i = 0; i < split.length; i++) {
//            String temp = split[i];
//            if (temp.trim().startsWith("filename")) {
//            	 System.out.println(temp.substring(temp.indexOf("=") + 2, temp.length() - 1));
//                return temp.substring(temp.indexOf("=") + 2, temp.length() - 1);
//            }
//        }
//        return "";
//    }
	
	

}
