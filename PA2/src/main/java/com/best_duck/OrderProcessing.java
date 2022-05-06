package com.best_duck;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

import java.util.Date;
import java.util.UUID;


@WebServlet(name = "orderProcessing", urlPatterns = "/processing", description = "processing")
public class OrderProcessing extends HttpServlet {

	private static float TAX_RATE = 0.0725f; //7.25%. Now using tax rates from state table
	private static float DISCOUNT_RATE = 0.0f; //0%
	
	PrintWriter output;
	HttpServletResponse response;
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		response = res;
		response.setContentType("text/html;charset=UTF-8");
		
		
		// Get session object
		HttpSession session = req.getSession();
		
		// Get the cart
		Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
		
		// if the session is new, the cart won't exist
		if(cart==null) {
			cart = new HashMap<String, Integer>();
			session.setAttribute("cart", cart);
		}
		
		
		
		//Print parameters passed by post method
  		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String address = req.getParameter("address");
		String city = req.getParameter("city");
		String stateCode = req.getParameter("state");
		String state = getStateName(stateCode); //Converts State Code to State Name exp. CA -> California
		String zip = req.getParameter("zip");
		String shippingmethod = req.getParameter("shippingmethod");
		String cardnumber = req.getParameter("cardnumber");
		String expmonth = req.getParameter("expmonth");
		String expyear = req.getParameter("expyear");
		String cvv = req.getParameter("cvv");
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
  		
		//convert strings to int
		int zipInt = Integer.parseInt(zip);
		int expyearInt = Integer.parseInt(expyear);
		int expMonthInt = Integer.parseInt(expmonth);
		int cvvInt = Integer.parseInt(cvv);
		
		p(" All Parameters from session object");
		p("firstname: " + firstname + "\n" +
		  "lastname: " + lastname + "\n" +
		  "address: " + address + "\n" +
		  "city: " + city + "\n" +
		  "state" + state + "\n" +
		  "zip" + zip + "\n" +
		  "shippingmethod" + shippingmethod + "\n" +
		  "cardnumber" + cardnumber + "\n" +
		  "expmonth" + expmonth + "\n" +
		  "expyear" + expyear + "\n" +
		  "cvv" + cvv + "\n" +
		  "phone" + phone + "\n" +
		  "email" + email + "\n" +
		  "");
		
		
		/*
		 * 
		 * 		ORDER PROCESSING
		 */
		

		
		
		//Order Summary
		Float subtotal = (float) 0;
		ArrayList<String> productIDList = new ArrayList<String>();
		//int productCount = 0;
		//ArrayList<String> quantityList;
		//int quantityCount = 0;
		
		//Print items in shopping cart. Use HTTP session's cart attribute
		p("Products and Quantity ordered: \n");
		String sku = null;
		String order_id = getUUID();
		//System.out.println(order_id.length());
		String user_id = "0";
		//Get today's date
		Date todaysDate = new Date();


		for(Entry<String, Integer> entry: cart.entrySet()) {
  			sku = entry.getKey();
  			Integer qty = entry.getValue();
  			String qtyString = Integer.toString(qty);
  			Map<String, Object> product = Database.getProduct(sku);
  			String productname = (String) product.get("name");
  			float price = (float) product.get("price");
  			String productprice = Float.toString( price );
  			
  			
  			p("product:" + sku + " productname: " + productname + " qty: " + qtyString + "\n");

			productIDList.add(sku);
			System.out.println("----Set order----");
			System.out.println(order_id);
			System.out.println(Integer.parseInt(sku));
			System.out.println(firstname);
			System.out.println(lastname);
			System.out.println(address);
			System.out.println(city);
			System.out.println(state);
			System.out.println(zip);
			System.out.println(shippingmethod);
			System.out.println(qty);
			System.out.println(cardnumber);
			System.out.println(expMonthInt);
			System.out.println(expyearInt);
			System.out.println(cvvInt);
			System.out.println(phone);
			System.out.println(email);
			System.out.println(user_id);
			System.out.println(todaysDate);

			System.out.println("--- --- ---");

			Database.setOrder(
					order_id,
					Integer.parseInt(sku),
					firstname,
					lastname,
					address,
					city,
					state,
					zip,
					shippingmethod,
					qty,
					cardnumber,
					expMonthInt,
					expyearInt,
					cvvInt,
					phone,
					email,
					user_id,
					todaysDate
			); // user id: need input
		
  		}
  		p("subtotal: " + subtotal + "\n");
  		
  		
  		// Calculate discounts, subtotals, totals
  		
  		//Order Subtotal
  		float workingTotal = subtotal;
  		float ordersubtotalprice = workingTotal;
  		float orderpricequantity = ordersubtotalprice;

  		// Total
  		float ordertotalprice = orderpricequantity;
		
  		
		// Save order to database
  		String orderproductname = "See ProductList for complete product list";
  		String orderproductid = "See ProductList for complete product list";
  		int orderquantity = 0; //will not use this field "Quantity" in order table, will use QuantityList instead to record string list of quantities
  		String newrecordID = order_id;

		p("record: " + newrecordID + " created!");

  		/**/
  		
  		// Set orderrecord session attribute
  		session.setAttribute("orderrecordid", newrecordID);
		// Get session object
		// Get the cart
		cart.clear();

		output = null;
		response = null;
		
		
		//Send to next servlet
		//Call a servlet from a servlet using RequestDispatcher
		//RequestDispatcher rd = req.getRequestDispatcher("sq");
				//rd.forward(req, res); //this forwards the req and res objects to the sq servlet
		System.out.println("Before forward");
		RequestDispatcher rd = req.getRequestDispatcher("order_confirmation");
		rd.forward(req, res); //this forwards the req and res objects to the order confirmation servlet
		//clean up

	}
	
	/**
	 * Helper Method
	 * @param message
	 * @throws IOException
	 */
	private void p(String message) throws IOException{
		if(output!=null) {
			output.println(message);
		} else {
			output = response.getWriter();
		}
	}
	
	private String getStateName(String stateCode) {
		Map<String, String> states = new HashMap<String, String>();
		states.put("Alabama","AL");
		states.put("Alaska","AK");
		states.put("Alberta","AB");
		states.put("American Samoa","AS");
		states.put("Arizona","AZ");
		states.put("Arkansas","AR");
		states.put("Armed Forces (AE)","AE");
		states.put("Armed Forces Americas","AA");
		states.put("Armed Forces Pacific","AP");
		states.put("British Columbia","BC");
		states.put("California","CA");
		states.put("Colorado","CO");
		states.put("Connecticut","CT");
		states.put("Delaware","DE");
		states.put("District Of Columbia","DC");
		states.put("Florida","FL");
		states.put("Georgia","GA");
		states.put("Guam","GU");
		states.put("Hawaii","HI");
		states.put("Idaho","ID");
		states.put("Illinois","IL");
		states.put("Indiana","IN");
		states.put("Iowa","IA");
		states.put("Kansas","KS");
		states.put("Kentucky","KY");
		states.put("Louisiana","LA");
		states.put("Maine","ME");
		states.put("Manitoba","MB");
		states.put("Maryland","MD");
		states.put("Massachusetts","MA");
		states.put("Michigan","MI");
		states.put("Minnesota","MN");
		states.put("Mississippi","MS");
		states.put("Missouri","MO");
		states.put("Montana","MT");
		states.put("Nebraska","NE");
		states.put("Nevada","NV");
		states.put("New Brunswick","NB");
		states.put("New Hampshire","NH");
		states.put("New Jersey","NJ");
		states.put("New Mexico","NM");
		states.put("New York","NY");
		states.put("Newfoundland","NF");
		states.put("North Carolina","NC");
		states.put("North Dakota","ND");
		states.put("Northwest Territories","NT");
		states.put("Nova Scotia","NS");
		states.put("Nunavut","NU");
		states.put("Ohio","OH");
		states.put("Oklahoma","OK");
		states.put("Ontario","ON");
		states.put("Oregon","OR");
		states.put("Pennsylvania","PA");
		states.put("Prince Edward Island","PE");
		states.put("Puerto Rico","PR");
		states.put("Quebec","QC");
		states.put("Rhode Island","RI");
		states.put("Saskatchewan","SK");
		states.put("South Carolina","SC");
		states.put("South Dakota","SD");
		states.put("Tennessee","TN");
		states.put("Texas","TX");
		states.put("Utah","UT");
		states.put("Vermont","VT");
		states.put("Virgin Islands","VI");
		states.put("Virginia","VA");
		states.put("Washington","WA");
		states.put("West Virginia","WV");
		states.put("Wisconsin","WI");
		states.put("Wyoming","WY");
		states.put("Yukon Territory","YT");
		
		String statename = "";
		statename = getKeyByValue(states,stateCode);
		return statename;
	}
	
	
	private static <T, E> T getKeyByValue(Map<T, E> map, E value) {
	    for (Entry<T, E> entry : map.entrySet()) {
	        if (Objects.equals(value, entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}

	private static String getUUID(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
}
