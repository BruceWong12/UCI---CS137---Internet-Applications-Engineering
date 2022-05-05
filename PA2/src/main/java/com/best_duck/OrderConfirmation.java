package com.best_duck;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(name = "OrderConfirmation", value = "/order_confirmation")
public class OrderConfirmation extends HttpServlet {
	PrintWriter output;
	HttpServletResponse response;
	
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		renderPage(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		renderPage(req,res);
	}
	private void renderPage(HttpServletRequest req, HttpServletResponse res) throws IOException {
		System.out.println("Shabi professor");
		response = res;
		response.setContentType("text/html;charset=UTF-8");
		
		
		// Get session object
		HttpSession session = req.getSession();
		
		// Get the cart
		Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
		
		// get new order record id
		int orderrecordid = (int) session.getAttribute("orderrecordid");
		
		//Get order
		Map<String, Object> order = Database.getOrder(orderrecordid);
		
		int OrderID = (int) order.get("OrderID");
		Date OrderDate = (Date) order.get("OrderDate");
		String OrderProductName = (String) order.get("OrderProductName");
		float OrderPriceQuantity = (float) order.get("OrderPriceQuantity");
		float OrderDiscount = (float) order.get("OrderDiscount");
		float OrderSubtotalAfterDiscount = (float) order.get("OrderSubtotalAfterDiscount");
		float OrderShippingPrice = (float) order.get("OrderShippingPrice");
		float OrderSubtotalAfterShipping=(float) order.get("OrderSubtotalAfterShipping");
		float OrderTaxRate =(float) order.get("OrderTaxRate");
		float OrderAmountTaxed =(float) order.get("OrderAmountTaxed");
		float OrderSubtotalAfterTax =(float) order.get("OrderSubtotalAfterTax");
		float OrderTotalPrice = (float) order.get("OrderTotalPrice");
		String FirstName = (String) order.get("FirstName");
		String LastName = (String) order.get("LastName");
		String Address = (String) order.get("Address");
		String City = (String) order.get("City");
		String State = (String) order.get("State");
		int Zip = (int) order.get("Zip");
		String ShippingMethod = (String) order.get("ShippingMethod");
		String ProductID = (String) order.get("ProductID");
		String ProductList = (String) order.get("ProductList");
		int Quantity= (int) order.get("Quantity");
		String QuantityList= (String) order.get("QuantityList");
		String CreditCardNumber= (String) order.get("CreditCardNumber");
		String ExpMonth= (String) order.get("ExpMonth");
		int ExpYear=(int) order.get("ExpYear");
		int CVV= (int) order.get("CVV");
		String PhoneNumber=(String) order.get("PhoneNumber");
		String Email=(String) order.get("Email");
		
		
		
		// Render the Order Confirmation Page
		p("");
		p("<!doctype html>\n" +
		"<html lang=\"en\">\n" +
		  "<head>\n" +
		    "<meta charset=\"utf-8\">\n" +
		    "<meta name=\"author\" content=\"\">\n" +
		    "<meta name=\"description\" content=\"\">\n" +

		    "<title>Products</title>\n" +

		    "<!-- external css file -->\n" +
		    "<script src=\"https://use.fontawesome.com/releases/v5.11.1/js/all.js\"></script>\n" +
		    "<link href=\"./css/style.css\" rel=\"stylesheet\">\n" +
		    "<link href=\"./css/orderconfirmation.css\" rel=\"stylesheet\">\n" +


		    "<!-- JavaScript -->\n" +
		    "<!--<script type=\"text/javascript\">-->\n" +
		    "<script>\n" +
		      "$(document).ready(function () {\n" +
		        "/* Go to Top Button */\n" +
		        "var elmClass = '.gotopbtn'; // Adjust this accordingly.\n" +

		        "//Check to see if the window is top if not then display button\n" +
		        "$(window).scroll(function () {\n" +
		          "if ($(this).scrollTop() > 300) { // 300px from top\n" +
		            "$(elmClass).fadeIn();\n" +
		          "} else {\n" +
		            "$(elmClass).fadeOut();\n" +
		          "}\n" +
		        "});\n" +

		        "//Click event to scroll to top\n" +
		        "$(elmClass).click(function () {\n" +
		          "$('html, body').animate({ scrollTop: 0 }, 800);\n" +
		          "return false;\n" +
		        "});\n" +
		        "/*----------------------------*/\n" +
		      "});\n" +
		      "function openSearch() {\n" +
		        "document.getElementById(\"myOverlay\").style.display = \"block\";\n" +
		      "}\n" +

		      "function closeSearch() {\n" +
		        "document.getElementById(\"myOverla\").style.display = \"none\";\n" +
		      "}\n" +
		    "</script>\n" +

		  "</head>");
		
		  //BODY TAG
		  p("<body>");
		  
		  p("<!-- Overlay for Search button --> \n" +
			  "<div id=\"myOverlay\" class=\"overlay\">\n" +
			    "<span class=\"closebtn\" onclick=\"closeSearch()\" title=\"Close Overlay\">×</span>\n" +
			    "<div class=\"overlay-content\">\n" +
			      "<form action=\"#\">\n" +
			        "<input type=\"text\" placeholder=\"Search..\" name=\"search\">\n" +
			        "<button type=\"submit\"><i class=\"fa fa-search\"></i></button>\n" +
			      "</form>\n" +
			    "</div>\n" +
			  "</div>\n" +
	
	
			  "<div class=\"main-container\">\n" +
			    "<!--<p>hello world!</p>-->\n" +
	
			    "<!-- Nav -->\n" +
			    "<!-- https://www.w3schools.com/howto/howto_css_topnav_right.asp-->\n" +
			    "<header>\n" +
			      "<div class=\"topnav\">\n" +
			        "<!-- Left-aligned links -->\n" +
			        "<!--<a id=\"topnav-logo\" href=\"index.html\"><img src=\"img/logo_placeholder.png\" alt=\"Logo\"></a>-->\n" +
			        "<a href=\"./index.html\">Home</a>\n" +
			        "<a class=\"active\" href=\"./products.html\">Products</a>\n" +
	
			        "<!-- https://www.w3schools.com/howto/howto_css_subnav.asp -->\n" +
			        "<!--<a href=\"#about\">About</a>-->\n" +
			        "<div class=\"subnav\">\n" +
			          "<button class=\"subnavbtn\">About <i class=\"fa fa-caret-down\"></i></button>\n" +
			          "<div class=\"subnav-content\">\n" +
			            "<a href=\"./company.html\">Company</a>\n" +
			            "<a href=\"./team.html\">Team</a>\n" +
			            "<!-- <a href=\"#careers\">Careers</a> -->\n" +
			            "<a href=\"./contact.html\">Contact</a>\n" +
			          "</div>\n" +
			        "</div>\n" +
	
			        "<!-- Right-aligned links-->\n" +
			        "<div class=\"topnav-right\">\n" +
			          "<!--<a href=\"#search\">Search</a>-->\n" +
			          "<button class=\"searchBtn\" onClick=\"openSearch()\" style=\"float:left;\"><i class=\"fas fa-search\"></i> Search</button>\n" +
			          "<a href=\"shoppingcart\"><i class=\"fas fa-shopping-cart\"></i> Shopping Cart</a>\n" +
			        "</div>\n" +
	
			      "</div>\n" +
			    "</header>\n" +
			    "");


		  	//MAIN CONTAINER
		    p("<div class=\"main-container\">\n" +



		      "<!-- Main -->\n" +
		      "<section>\n" +
		        "<div class=\"main\">\n" +


		          "<div class=\"redbox\">\n" +

		            "<div class=\"orderconfirmation\" >\n" +
		              "<div class=\"sectionheading\"><p>ORDER CONFIRMATION</p></div>\n" +


		              "<p><span style=\"font-size: 30px;\"><i class=\"fas fa-cart-arrow-down\"></i></span></p>\n" +
		              "<p>\n" +
		                ""+FirstName +" " + LastName +", thank you for your order!\n" +
		              "</p>\n" +
		              "<p>We've recieved your order and will contact you as soon as your package is shipped.\n" +
		                "You can find your purchase information below.</p>\n" +
		            "</div>\n" +

		            "<div class=\"ordersummary\">\n" +
		              "<div class=\"sectionheading\"><p>Order Summary</p></div>\n" +
		              "<p>"+ OrderDate +"</p> <!-- Date of Purchase -->\n" +
		              "");
		              p("<div class=\"productimageandsummary-container\">\n" +
		                /*"<div class=\"productimage\">\n" +
		                  "<?php\n" +
		                    "//<img class= \"product-img\" src=\"../img/products/ps4.jpeg\" alt=\"PS4\">\n" +
		                    "echo \"<img class='product-img' src='\".$mainImagePath.\"' alt='product image'>"\n" +
		                  "?>\n" +
		                "</div>\n" +*/ //TODO
		                  
		                "<div class=\"productimage\">\n" +
		              "");
		              
		              		// Create product cart table
			                p("<table>");
				 			
				 			p("<tr>\n" +
				 				"<th></th>\n" +
				 				"<th>PRODUCT</th>\n" +
				 				"<th>QTY</th>\n" +
				 				"<th>PRICE</th>\n" +
				 			"</tr>\n" +
				 			"");
		              
		              		for(Map.Entry<String, Integer> entry: cart.entrySet()) {
		              			String prodID = entry.getKey();
				      			Integer qty = entry.getValue();
				      			String qtyString = Integer.toString(qty);
				      			Map<String, Object> product = Database.getProduct(prodID);
				      			String productname = (String) product.get("ProductName");
				      			float price = (float) product.get("Price");
				      			String productprice = Float.toString( price );
				      			
		              			//Retrieve the main image. This is the first image in the array.
				    			String imageLinks = (String) product.get("ImageLinks"); 
				    			String[] imageLinksArray = imageLinks.split(",");
				    			String mainImage = imageLinksArray[0];
				    			
		              			p("<tr>\n" +
		              					"<td><center><img src=\""+mainImage+"\" alt=\"product image\"></center></td>\n" +
		    		      				"<td>"+productname+"</td>\n" +
		    		      				"<td>"+qtyString+"</td>\n" +			
		    		      				"<td>$"+productprice+"</td>\n" +
    		      				"</tr>\n" +
        		      			"");		
		              		}
		              		p("</table>");
		              	p("</div>");
		                
		                p("<div class=\"productsummary\">\n" +
		                  "<p style=\"border-top: 1px solid lightgrey; padding-top: 5px;\">\n" +
		                    "<span style=\"font-weight: bold;\">Product Name</span>\n" +
		                    "<span class=\"alignright\" style=\"font-weight: bold;\">See product table to the right.<span>\n" +
		                  "</p>\n" +
		                  "<p>Product ID <span class=\"alignright\">"+ProductList+"</span></p>\n" +
		                  //"<p>Product Price <span class=\"alignright\"><?php echo "$".$product['Price'].""; ?></span></p>\n" +
		                  //"<p>Quantity <span class=\"alignright\"><?php echo "".$order['Quantity'].""; ?></span></p>\n" +
		                  //"<p>Product Price X Quantity <span class=\"alignright\"><?php echo "$".$order['OrderPriceQuantity'].""; ?></span></p>\n" +
		                  "<p>Order Subtotal <span class=\"alignright\">$"+OrderPriceQuantity+"</p>\n" +
		                  "<p>Discount <span class=\"alignright\">$"+OrderDiscount+"</span></p>\n" +
		                  "<p>Price After Discount <span class=\"alignright\">$"+OrderSubtotalAfterDiscount+"</p>\n" +


		                "</div>\n" +
		              "</div>\n" +
		              "");
		              
		            p("</div>\n" +

		            "<div class=\"ordertotal\">\n" +
		             "<div class=\"sectionheading\"><p>Order Total</p></div>\n" +
		              "<p style=\"border-top: 1px solid lightgrey; padding-top: 5px;\">\n" +
		                "Subtotal price <span class=\"alignright\">$"+OrderPriceQuantity+"</span></p>\n" +
		              "<p>Discount <span class=\"alignright\" style=\"color: green;\">(0% discount rate) -$0.00</span></p>\n" +
		              "<p>Shipping price <span class=\"alignright\" style=\"color: red;\" >"+"("+ShippingMethod+") +$"+OrderShippingPrice+"</span></p>\n" +
		              "<P>Tax <span class=\"alignright\" style=\"color: red;\">"+"("+OrderTaxRate+"% tax rate) +$"+OrderAmountTaxed+"</span></p>\n" +
		              "<p style=\"border-top: 1px solid lightgrey; padding-top: 5px;\">\n" +
		                "<span style=\"font-weight: bold;\">Total price: </span>\n" +
		                "<span class=\"alignright\" style=\"font-weight: bold; color: blue;\">$"+OrderTotalPrice+"</span>\n" +
		              "</p>\n" +
		            "</div>\n" +

		            "<div class=\"billingandshipping\">\n" +
		              "<div class=\"sectionheading\"><p>Billing and Shipping</p></div>\n" +


		              "<div class=\"billingandshipping-container\">\n" +
		                "<div class=\"billingandshipping-box\">\n" +
		                  "<p><span style=\"font-weight: bold;\">Billing</span></p>\n" +
		                  "<p>"+FirstName+" "+LastName+"</p>\n" +
		                  "<p>"+Address+"</p>\n" +
		                  "<p>"+City+","+State+"</p>\n" +
		                  "<p>"+Zip+"</p>\n" +
		                  "<p>USA</p>\n" +
		                "</div>\n" +
		                "<div class=\"billingandshipping-box\">\n" +
		                  "<p><span style=\"font-weight: bold;\">Shipping</span></p>\n" +
		                  "<p>"+FirstName+" "+LastName+"</p>\n" +
		                  "<p>"+Address+"</p>\n" +
		                  "<p>"+City+","+State+"</p>\n" +
		                  "<p>"+Zip+"</p>\n" +
		                  "<p>USA</p>\n" +
		                "</div>\n" +
		                "<div class=\"billingandshipping-box\">\n" +
		                  "<p><span style=\"font-weight: bold;\">Payment method</span></p>\n" +
		                  "<p>Credit Card</p>\n" +
		                "</div>\n" +
		                "<div class=\"billingandshipping-box\">\n" +
		                  "<p><span style=\"font-weight: bold;\">Shipping method</span></p>\n" +
		                  "<p>"+ShippingMethod+"</p>\n" +
		                "</div>\n" +
		              "</div>\n" +


		            "</div>\n" +
		          "</div> <!--end of redbox class -->\n" +

		        "</div>\n" +
		      "</section>\n" +
		      "</div> <!--end of main container class -->\n" +
		      "");
		      
		      
		      //FOOTER TAG
			 p("<!-- Footer --> \n" +
			    "<!-- \n" +
			      "<div class=\"footer\"> \n" +
			        "<p>Footer</p> \n" +
			      "</div> --> \n" +
			    "<footer> \n" +
			      "<p>Acme Web Design, Copyright &copy; 2020</p> \n" +
			    "</footer> \n" +
			  "</div> \n" +

			  "<!-- go to top button --> \n" +
			  "<a class=\"gotopbtn\" href=\"#\"><span class=\"fas fa-caret-square-up\"></span></a> \n" +
			  "");

		    p("<!-- go to top button -->\n"+
		    "<a class=\"gotopbtn\" href=\"#\"><span class=\"fas fa-caret-square-up\"></span></a>\n"+
		    "");
		  p("</body>");
		  p("</html>");
		
		
		//clean up
		output = null;
		response = null;
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
	
}
