package com.best_duck;

import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database {
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/best_duck?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	//  Database credentials
	static final String USER = "root";
	static final String PASS = "LeoYoung981028";//"testdb124";
	
	/**
	 * openConnection
	 * @return
	 */
	private static Connection openConnection() {
		Connection conn = null;	
	    try {
	    	Class.forName(JDBC_DRIVER); //Register JDBC driver

		    System.out.println("Connecting to database..."); //Open a connection
		    conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	

	/**
	 * getProductsTest
	 * @param out
	 */
	public static void getProductsTest(PrintWriter out) {
		
		Connection conn = openConnection();
		
		Statement stmt = null;
	    System.out.println("Creating statement...");
	    try {
			stmt = conn.createStatement();
			String sql;
		    sql = "SELECT * FROM test.product;";
		    ResultSet rs = stmt.executeQuery(sql);
		    while(rs.next()){
		    	//Retrieve by column name
		    	String productName = rs.getString("ProductName");
		    	String productID = rs.getString("ProductID"); 
			
		    	System.out.print("product name: " + productName);
		    	System.out.println(", product id: " + productID); 
		    	
		    	//Display in browser
		    	out.print("product name: " + productName);
		    	out.println(", product id: " + productID); 
		    }  
		
		  	rs.close();
		  	stmt.close();
		  	conn.close();
		  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      

	}

	/**
	 * getAllProductsByCategory
	 * @param category
	 * @return
	 */
	public static ArrayList<Map<String, Object>> getAllProductsByCategory(String category) {
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        /*
        `product`  (
          `sku` int NULL DEFAULT NULL,
          `producer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
          `catagory` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
          `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
          `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
          `price` decimal(10, 2) NULL DEFAULT NULL,
          `stock` int NULL DEFAULT NULL,
          `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL
        ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

        */
		Connection conn = openConnection();

		//Statement stmt = null;
		System.out.println("Creating statement...");
		try {

			String sql;
			sql = "SELECT * FROM ecommerce.product WHERE productcategory=?";

			//prepare statement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, category); // fill the category

			//execute statement
			ResultSet rs = ps.executeQuery();

			//used to printing the results of ResultSet
			//and store ResultSet into an arraylist of hashmaps
			ResultSetMetaData rsmd = rs.getMetaData();
			System.out.println("querying " + sql + "(?= " + category + ")");
			int columnsNumber = rsmd.getColumnCount();

			while(rs.next()){
				//Retrieve by column name
				//String productName = rs.getString("ProductName");
				//String productID = rs.getString("ProductID");

				//System.out.print("product name: " + productName);
				//System.out.println(", product id: " + productID);

				Map<String, Object> row = new HashMap<String, Object>();

				//outuput result set values
				//for (int i = 1; i <= columnsNumber; i++) {
				//if (i > 1) System.out.print(",  ");
				//String columnValue = rs.getString(i);
				//System.out.print(columnValue + " " + rsmd.getColumnName(i));

				//row.put(rsmd.getColumnName(i),rs.getObject(i));
				//}
				//System.out.println("");

				row.put("sku",rs.getInt("sku"));
				row.put("producer",rs.getString("producer"));
				row.put("category",rs.getString("category"));
				row.put("name",rs.getString("name"));
				row.put("image",rs.getString("image"));
				row.put("price",rs.getFloat("price"));
				row.put("stock",rs.getInt("stock"));
				row.put("description",rs.getString("description"));
				list.add(row);
			}

			rs.close();
			//stmt.close();
			conn.close();



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}
	
	/**
	 * getProduct
	 * @param productID
	 * @return
	 */
	public static Map<String, Object> getProduct(String productID) {
		Map<String, Object> row = new HashMap<String, Object>();
		Connection conn = openConnection();
		
		//Statement stmt = null;
	    System.out.println("Creating statement...");
	    try {
			
			String sql;
		    sql = "SELECT * FROM ecommerce.product WHERE sku=?";
		    
		    //prepare statement
		    PreparedStatement ps = conn.prepareStatement(sql);
		    ps.setString(1, productID);
		    
		    //execute statement
		    ResultSet rs = ps.executeQuery();
		    
		    while(rs.next()){
		        row.put("ProductName",rs.getString("ProductName"));
		        row.put("Quantity",rs.getInt("Quantity"));
		        row.put("ProductID",rs.getString("ProductID"));
		        row.put("ProductCategory",rs.getString("ProductCategory"));
		        row.put("category",rs.getString("category"));
		        row.put("Platform",rs.getString("Platform"));
		        row.put("Price",rs.getFloat("Price"));
		        row.put("Description",rs.getString("Description"));
		        row.put("ImageLinks",rs.getString("ImageLinks"));
		    }  
		
		  	rs.close();
		  	//stmt.close();
		  	conn.close();
		  	

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return row;
	}
	
	/**
	 * getOrder
	 * @param orderID
	 * @return
	 */
	public static Map<String, Object> getOrder(int orderID) {
		Map<String, Object> row = new HashMap<String, Object>();
		Connection conn = openConnection();
		
		//Statement stmt = null;
	    System.out.println("Creating statement...");
	    try {
	    	
	    	String sql;
	    	sql = "SELECT * FROM ecommerce.order WHERE orderid=?";
	    	
	    	//prepare statement
		    PreparedStatement ps = conn.prepareStatement(sql);
	    	ps.setInt(1, orderID);
	    	
	    	//execute statement
	    	ResultSet rs = ps.executeQuery();
	    	
	    	while(rs.next()){
	    		row.put("OrderID", rs.getInt("OrderID"));
	    		row.put("OrderDate", rs.getDate("OrderDate"));
	    		row.put("OrderProductName", rs.getString("OrderProductName"));
	    		row.put("OrderPriceQuantity", rs.getFloat("OrderPriceQuantity"));
	    		row.put("OrderDiscount", rs.getFloat("OrderDiscount"));
	    		row.put("OrderSubtotalAfterDiscount", rs.getFloat("OrderSubtotalAfterDiscount"));
	    		row.put("OrderShippingPrice", rs.getFloat("OrderShippingPrice"));
	    		row.put("OrderSubtotalAfterShipping",rs.getFloat("OrderSubtotalAfterShipping"));
	    		row.put("OrderTaxRate",rs.getFloat("OrderTaxRate"));
	    		row.put("OrderAmountTaxed",rs.getFloat("OrderAmountTaxed"));
	    		row.put("OrderSubtotalAfterTax",rs.getFloat("OrderSubtotalAfterTax"));
	    		row.put("OrderTotalPrice", rs.getFloat("OrderTotalPrice"));
	    		row.put("FirstName", rs.getString("FirstName"));
	    		row.put("LastName", rs.getString("LastName"));
	    		row.put("Address", rs.getString("Address"));
	    		row.put("City", rs.getString("City"));
	    		row.put("State", rs.getString("State"));
	    		row.put("Zip", rs.getInt("Zip"));
	    		row.put("ShippingMethod", rs.getString("ShippingMethod"));
	    		row.put("ProductID", rs.getString("ProductID"));
	    		row.put("ProductList", rs.getString("ProductList"));
	    		row.put("Quantity", rs.getInt("Quantity"));
	    		row.put("QuantityList", rs.getString("QuantityList"));
	    		row.put("CreditCardNumber", rs.getString("CreditCardNumber"));
	    		row.put("ExpMonth", rs.getString("ExpMonth"));
	    		row.put("ExpYear",rs.getInt("ExpYear"));
	    		row.put("CVV", rs.getInt("CVV"));
	    		row.put("PhoneNumber",rs.getString("PhoneNumber"));
	    		row.put("Email",rs.getString("Email"));
	    	}  
			
		  	rs.close();
		  	//stmt.close();
		  	conn.close();
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return row;
	}
	
	
	
	
	public static int setOrder(
			java.util.Date todaysDate,
			String orderproductname,
			float orderpricequantity,
			float orderdiscount,
			float ordersubtotal_afterdiscount,
			float ordershippingprice,
			float ordersubtotal_aftershipping,
			float ordertaxrate,
			float orderamounttaxed,
			float ordersubtotal_aftertax,
			float ordertotalprice,
			String firstname,
			String lastname,
			String address,
			String city,
			String state,
			int zip,
			String shippingmethod,
			String orderproductid,
			String productIDList,
			int orderquantity,
			String quantityList,
			String cardnumber,
			String expmonth,
			int expyear,
			int cvv,
			String phone,
			String email
			) {
		
		// return value
		int recordID = 0;
		int numOfRowsUpdated = 0;
		
		// convert java date to mysql date
		Date mysqlTodaysDate = new Date( todaysDate.getTime() );
		
		Connection conn = openConnection();
		
		//Statement stmt = null;
	    System.out.println("Creating statement...");
	    try {
	    	String sql;
	    	sql = "INSERT INTO ecommerce.order (orderdate, orderproductname, orderpricequantity, orderdiscount, " + 
	    			                           "ordersubtotalafterdiscount, ordershippingprice, ordersubtotalaftershipping, " + 
	    			                           "ordertaxrate, orderamounttaxed, ordersubtotalaftertax, ordertotalprice, " + 
	    			                           "firstname, lastname, address, city, state, zip, shippingmethod, productid, " + 
	    			                           "productlist, quantity, quantitylist, creditcardnumber, expmonth, expyear, cvv, " + 
	    			                           "phonenumber, email)" + 
	    			                           " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    	//prepare statement
		    PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    ps.setDate(1, mysqlTodaysDate);
		    ps.setString(2, orderproductname);
		    ps.setDouble(3,orderpricequantity);
		    ps.setDouble(4,orderdiscount);
		    ps.setDouble(5,ordersubtotal_afterdiscount);
		    ps.setDouble(6,ordershippingprice);
		    ps.setDouble(7,ordersubtotal_aftershipping);
		    ps.setDouble(8,ordertaxrate);
		    ps.setDouble(9,orderamounttaxed);
		    ps.setDouble(10,ordersubtotal_aftertax);
		    ps.setDouble(11,ordertotalprice);
			ps.setString(12,firstname);
			ps.setString(13,lastname);
			ps.setString(14,address);
			ps.setString(15,city);
			ps.setString(16,state);
			ps.setInt(17,zip);
			ps.setString(18,shippingmethod);
			ps.setString(19,orderproductid);
			ps.setString(20,productIDList);
			ps.setInt(21,orderquantity);
			ps.setString(22,quantityList);
			ps.setString(23,cardnumber);
			ps.setString(24,expmonth);
			ps.setInt(25,expyear);
			ps.setInt(26,cvv);
			ps.setString(27,phone);
			ps.setString(28,email);
		    

		    //execute statement
			numOfRowsUpdated = ps.executeUpdate();
		    System.out.println(numOfRowsUpdated + " row(s) inserted in order table. ");
	    	
		    // return record id of newly inserted record
		    if(numOfRowsUpdated == 0) {
		    	throw new SQLException("Creating order record failed, no rows affected.");
		    }
		    try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
		    	if(generatedKeys.next()) {
		    		recordID = (int) generatedKeys.getLong(1);
		    	} else {
		    		throw new SQLException("Creating order record failed, no record ID obtained.");
		    	}
		    }
		    
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return recordID;
	}
	
	
	public static Map<String, Object> getTaxRate(String statename) {
		Map<String, Object> row = new HashMap<String, Object>();
		Connection conn = openConnection();
		
		//Statement stmt = null;
	    System.out.println("Creating statement...");
	    try {
	    	String sql;
	    	sql = "SELECT * FROM state WHERE state_name=?";
	    	
	    	//prepare statement
		    PreparedStatement ps = conn.prepareStatement(sql);
		    ps.setString(1, statename);
		    
		    //execute statement
		    ResultSet rs = ps.executeQuery();
	    	
	    	while(rs.next()){
	    		row.put("state_name",rs.getString("state_name"));
	    		row.put("tax", rs.getFloat("tax"));
	    	} 	
	    		
	    	rs.close();
		  	//stmt.close();
		  	conn.close();		
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return row;
	}
	
	/**
	 * getShippingPrice
	 * @param shippingmethod
	 * @return
	 */
	public static Map<String, Object> getShippingPrice(String shippingmethod) {
		Map<String, Object> row = new HashMap<String, Object>();
		Connection conn = openConnection();
		
		//Statement stmt = null;
	    System.out.println("Creating statement...");
	    try {
	    	
	    	String sql;
	    	sql = "SELECT * FROM shipping WHERE shipping_method=?";
	
	    	//prepare statement
		    PreparedStatement ps = conn.prepareStatement(sql);
	    	ps.setString(1, shippingmethod);
	    	
	    	//execute statement
	    	ResultSet rs = ps.executeQuery();
	    	
	    	while(rs.next()){
	    		row.put("shipping_method", rs.getString("shipping_method"));
	    		row.put("price", rs.getFloat("price"));
	    	} 
	    	
	    	rs.close();
		  	//stmt.close();
		  	conn.close();		
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return row;
	}
	
}
