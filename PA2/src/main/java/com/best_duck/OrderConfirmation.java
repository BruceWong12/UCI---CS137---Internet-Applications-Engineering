package com.best_duck;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
		System.out.println("in confirmation page");
		response = res;
		response.setContentType("text/html;charset=UTF-8");
		
		
		// Get session object
		HttpSession session = req.getSession();
		
		// Get the cart
		Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
		
		// get new order record id
		String orderrecordid = (String) session.getAttribute("orderrecordid");
		
		//Get order
		ArrayList<Map<String, Object>> orderproducts = Database.getOrderProducts(orderrecordid);
		//Map<String, Object> order = Database.getOrderProducts(orderrecordid);
		//Get shared information
		Map<String, Object> orderInfo = Database.getOrderInfo(orderrecordid);

		String OrderID = (String) orderInfo.get("order_num");
		String FirstName = (String) orderInfo.get("first_name");
		String LastName = (String) orderInfo.get("last_name");
		String Address = (String) orderInfo.get("address");
		String City = (String) orderInfo.get("city");
		String State = (String) orderInfo.get("state");
		String Zip = (String) orderInfo.get("zip");
		String ShippingMethod = (String) orderInfo.get("shipping_method");
		String CreditCardNumber= (String) orderInfo.get("credit_num");
		int ExpMonth= (int) orderInfo.get("exp_mon");
		int ExpYear=(int) orderInfo.get("exp_year");
		int CVV = (int) orderInfo.get("cvv");
		String PhoneNumber = (String) orderInfo.get("phone_num");
		String Email = (String) orderInfo.get("email");
		/*float OrderTotalPrice = (float) orderInfo.get("price");
		String FirstName = (String) orderInfo.get("firstName");
		String LastName = (String) orderInfo.get("lastName");
		String Address = (String) orderInfo.get("Address");
		String City = (String) orderInfo.get("City");
		String State = (String) orderInfo.get("State");
		int Zip = (int) orderInfo.get("Zip");
		String ShippingMethod = (String) orderInfo.get("ShippingMethod");
		String ProductID = (String) orderInfo.get("ProductID");
		String ProductList = (String) orderInfo.get("ProductList");
		int Quantity= (int) orderInfo.get("Quantity");
		String QuantityList= (String) orderInfo.get("QuantityList");
		String CreditCardNumber= (String) orderInfo.get("CreditCardNumber");
		String ExpMonth= (String) orderInfo.get("ExpMonth");
		int ExpYear=(int) orderInfo.get("ExpYear");
		int CVV= (int) orderInfo.get("CVV");
		String PhoneNumber=(String) orderInfo.get("PhoneNumber");
		String Email=(String) orderInfo.get("Email");*/
		
		
		
		// Render the Order Confirmation Page
		p("");
		p("<!doctype html>\n" +
		"<html lang=\"en\">\n");
		p("<header>\n" +
				"    <link href=\"./css/style.css\" rel=\"stylesheet\">\n" +
				"    <link href=\"./css/shoppingCart.css\" rel=\"stylesheet\">\n" +
				"    <link href=\"./css/product_category.css\" rel =\"stylesheet\">\n" +
						"<link href=\"./css/orderconfirmation.css\" rel =\"stylesheet\">\n"+
				"        <div class=\"topNav\">\n" +
				"            <!-- Left-aligned links -->\n" +
				"            <a class=\"active\" href=\"index.html\">Home</a>\n" +
				"            <a href = \"categories\">Products</a>\n" +
				"            <a href=\"team.html\">Team</a>\n" +
				"            <a href=\"about.html\">About</a>\n" +
				"\n" +
				"            <!-- Right-aligned links-->\n" +
				"            <div class=\"topNav-right\">\n" +
				"                <a href =\"shoppingcart\"><i class=\"fas fa-shopping-cart\"></i> Shopping Cart</a>\n" +
				"            </div>\n" +
				"        </div>\n" +
				"    </header>");


		//BODY TAG
		  p("<body>");

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
		              //"<p>"+ OrderDate +"</p> <!-- Date of Purchase -->\n" +
		              "");
		              p("<div class=\"productimageandsummary-container\">\n" +
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
				      			String productname = (String) product.get("name");
				      			float price = (float) product.get("price");
				      			String productprice = Float.toString( price );

		              			//Retrieve the main image. This is the first image in the array.
				    			String imageLink = (String) product.get("image");

		              			p("<tr>\n" +
		              					"<td><center><img src=\""+imageLink+"\" alt=\"product image\"></center></td>\n" +
		    		      				"<td>"+productname+"</td>\n" +
		    		      				"<td>"+qtyString+"</td>\n" +
		    		      				"<td>$"+productprice+"</td>\n" +
    		      				"</tr>\n" +
        		      			"");
		              		}
		              		p("</table>");
		              	p("</div>");
		                
//		                p(
//								"<div class=\"productsummary\">\n" +
//		                  "<p style=\"border-top: 1px solid lightgrey; padding-top: 5px;\">\n" +
//		                    "<span style=\"font-weight: bold;\">Product Name</span>\n" +
//		                    "<span class=\"alignright\" style=\"font-weight: bold;\">See product table to the right.<span>\n" +
//		                  "</p>\n" +
//		                  //"<p>Product ID <span class=\"alignright\">"+ProductList+"</span></p>\n" +
//		                  //"<p>Product Price <span class=\"alignright\"><?php echo "$".$product['Price'].""; ?></span></p>\n" +
//		                  //"<p>Quantity <span class=\"alignright\"><?php echo "".$order['Quantity'].""; ?></span></p>\n" +
//		                  //"<p>Product Price X Quantity <span class=\"alignright\"><?php echo "$".$order['OrderPriceQuantity'].""; ?></span></p>\n" +
//		                  //"<p>Order Subtotal <span class=\"alignright\">$"+OrderPriceQuantity+"</p>\n" +
//		                  //"<p>Discount <span class=\"alignright\">$"+OrderDiscount+"</span></p>\n" +
//		                  //"<p>Price After Discount <span class=\"alignright\">$"+OrderSubtotalAfterDiscount+"</p>\n" +
//
//
//		                "</div>\n" +
//		              "</div>\n" +
//		              "");
		              
		            p("</div>\n" +

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
			      "<p>BestDuck Web Design, Copyright &copy; 2020</p> \n" +
			    "</footer> \n" +
			  "</div> \n");

		  p("</body>");
		  p("</html>");
		cart.clear();
		
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
