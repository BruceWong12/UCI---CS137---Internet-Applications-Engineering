package com.best_duck;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


@WebServlet(name = "Checkout", value = "/checkout")
public class Checkout extends HttpServlet {
    PrintWriter output;
    HttpServletResponse response;
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        renderPage(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        renderPage(req, res);
    }
    public void renderPage(HttpServletRequest req, HttpServletResponse res) throws IOException{
        response = res;
        response.setContentType("text/html;charset=UTF-8");
        p("");
        p("<!doctype html>");
        p("<html lang=\"en\">");
        p("<header>\n" +
                "    <link href=\"./css/style.css\" rel=\"stylesheet\">\n" +
                "    <link href=\"./css/shoppingCart.css\" rel=\"stylesheet\">\n" +
                "    <link href=\"./css/product_category.css\" rel =\"stylesheet\">\n" +
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
        // Get session object
        HttpSession session = req.getSession();

        // Get the cart
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");

        //Order Summary
        Float subtotal = (float) 0;
        for(Map.Entry<String, Integer> entry: cart.entrySet()) {
            String prodID = entry.getKey();
            Integer qty = entry.getValue();
            Map<String, Object> product = Database.getProduct(prodID);
            float price = (float) product.get("price");
            System.out.println(prodID);
            // add to subtotal
            subtotal+= (qty * price);
        }
        p("<!-- Main -->\n" +
                "<section>\n" +
                "<div class=\"main\">\n" +
                "");

        p("<p><a href=\"shoppingcart\"><i class=\"fas fa-arrow-left\"></i> Back to Shopping Cart page</a></p>\n" + "<br>\n"
                + "");

        p("<!-- Shopping Cart Detail Card -->\n" +
                "<div class=\"w3-card-4 shoppingcartcard\" style=\"width:48%; float:left;\">\n" +
                "<center><strong><h2>Order Summary</h2></strong></center>\n" +
                "<p>SUBTOTAL<span class=\"alignright\" style=\"\">$"+subtotal+"</span></p>\n" +
                "<p>DISCOUNT<span class=\"alignright\" style=\"\">$0.00</span></p>\n" +
                "<p>ESTIMATED TOTAL<span class=\"alignright\" style=\"\">$"+subtotal+"</span></p>\n" +
                "<div class=\"container\" align=\"left\">\n" +
	          	/*"<form action = \"checkout\" align=\"left\">\n" +
	          		"<button id=\"checkoutbutton\" type=\"submit\" ><i class=\"fas fa-arrow-right\"></i> PROCEED TO CHECKOUT</button>\n" +
	          	"</form>\n" +*/
                "<br>\n" +
                "<center><strong><h2>Your Shopping Cart</h2></strong></center>\n" +
                "");

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
            String productName = (String) product.get("name");
            String productprice = Float.toString( (float) product.get("price") );

            //Retrieve the main image. This is the first image in the array.
            String imageLinks = (String) product.get("image");
            String[] imageLinksArray = imageLinks.split(",");
            String mainImage = imageLinksArray[0];

      			/*p("<div class=\"productrow\">\n" +
      			"<p>product id: "+prodID+", quantity: "+qty+"</p>\n" +
      			"</div>\n");*/
            p("<tr>\n" +
                    "<td><center><img src=\""+mainImage+"\" alt=\"product image\"></center></td>\n" +
                    "<td>"+productName+"</td>\n" +
                    "<td>"+qtyString+"</td>\n" +
                    "<td>"+productprice+"</td>\n" +
                    "</tr>\n" +
                    "");
        }


        p("</table>");

        p("</div>\n" +
                "</div>\n" +
                "<!-- end of product detail card div -->\n" +
                "");



        p("<!-- Order Form Card -->");
        p("<div class=\"w3-card-4 orderform-card\" style=\"width:48%; float:right;boarder:1px\">\n" +
                "<form action=\"processing\" method=\"post\" name=\"orderform\" onsubmit=\"\">\n" +
                "<div class=\"col-50\">\n" +
                "<strong><h2>Order Form</h2></strong>\n" +
                "<h4>Shipping Information</h4>\n" +

                "<div class=\"row\">\n" +
                "<div class=\"col-50\">\n" +
                "<label for=\"cname\">First Name</label>\n" +
                "<input type=\"text\" id=\"fname\" name=\"firstname\" placeholder=\"John\" required>\n" +
                "</div>\n" +

                "<div class=\"col-50\">\n" +
                "<label for=\"cname\">Last Name</label>\n" +
                "<input type=\"text\" id=\"lname\" name=\"lastname\" placeholder=\"Doe\" required>\n" +
                "</div>\n" +
                "</div>\n" +

                "<label for=\"adr\">Address</label>\n" +
                "<input type=\"text\" id=\"adr\" name=\"address\" placeholder=\"542 W. 15th Street\" required>\n" +

                "<label for=\"city\">City</label>\n" +
                "<input type=\"text\" id=\"city\" name=\"city\" placeholder=\"New York\" required>\n"
                + "");

        p("<div class=\"row\">\n" +
                "<div class=\"col-50\">\n" +
                "<label for=\"state\">State</label>\n" +
                "<select class=\"select-css\" name=\"state\">\n" +
                "<option value=\"AL\">Alabama</option>\n" +
                "<option value=\"AK\">Alaska</option>\n" +
                "<option value=\"AZ\">Arizona</option>\n" +
                "<option value=\"AR\">Arkansas</option>\n" +
                "<option value=\"CA\">California</option>\n" +
                "<option value=\"CO\">Colorado</option>\n" +
                "<option value=\"CT\">Connecticut</option>\n" +
                "<option value=\"DE\">Delaware</option>\n" +
                "<option value=\"DC\">District Of Columbia</option>\n" +
                "<option value=\"FL\">Florida</option>\n" +
                "<option value=\"GA\">Georgia</option>\n" +
                "<option value=\"HI\">Hawaii</option>\n" +
                "<option value=\"ID\">Idaho</option>\n" +
                "<option value=\"IL\">Illinois</option>\n" +
                "<option value=\"IN\">Indiana</option>\n" +
                "<option value=\"IA\">Iowa</option>\n" +
                "<option value=\"KS\">Kansas</option>\n" +
                "<option value=\"KY\">Kentucky</option>\n" +
                "<option value=\"LA\">Louisiana</option>\n" +
                "<option value=\"ME\">Maine</option>\n" +
                "<option value=\"MD\">Maryland</option>\n" +
                "<option value=\"MA\">Massachusetts</option>\n" +
                "<option value=\"MI\">Michigan</option>\n" +
                "<option value=\"MN\">Minnesota</option>\n" +
                "<option value=\"MS\">Mississippi</option>\n" +
                "<option value=\"MO\">Missouri</option>\n" +
                "<option value=\"MT\">Montana</option>\n" +
                "<option value=\"NE\">Nebraska</option>\n" +
                "<option value=\"NV\">Nevada</option>\n" +
                "<option value=\"NH\">New Hampshire</option>\n" +
                "<option value=\"NJ\">New Jersey</option>\n" +
                "<option value=\"NM\">New Mexico</option>\n" +
                "<option value=\"NY\">New York</option>\n" +
                "<option value=\"NC\">North Carolina</option>\n" +
                "<option value=\"ND\">North Dakota</option>\n" +
                "<option value=\"OH\">Ohio</option>\n" +
                "<option value=\"OK\">Oklahoma</option>\n" +
                "<option value=\"OR\">Oregon</option>\n" +
                "<option value=\"PA\">Pennsylvania</option>\n" +
                "<option value=\"RI\">Rhode Island</option>\n" +
                "<option value=\"SC\">South Carolina</option>\n" +
                "<option value=\"SD\">South Dakota</option>\n" +
                "<option value=\"TN\">Tennessee</option>\n" +
                "<option value=\"TX\">Texas</option>\n" +
                "<option value=\"UT\">Utah</option>\n" +
                "<option value=\"VT\">Vermont</option>\n" +
                "<option value=\"VA\">Virginia</option>\n" +
                "<option value=\"WA\">Washington</option>\n" +
                "<option value=\"WV\">West Virginia</option>\n" +
                "<option value=\"WI\">Wisconsin</option>\n" +
                "<option value=\"WY\">Wyoming</option>\n" +
                "</select>\n" +
                "</div>\n" +
                "");

        p("<div class=\"col-50\">\r\n" +
                "<label for=\"zip\">Zip</label>\r\n" +
                "<input type=\"text\" id=\"zip\" name=\"zip\" placeholder=\"10001\" required>\r\n" +
                "</div>\n" +
                "</div>" +
                "");

        p("<label for=\"Shipping Method\">Shipping Method:</label>\n" +

                "<select class=\"select-css\" id=\"Shipping Method\" name=\"shippingmethod\">\n" +
                "<option value=\"overnight\">Overnight</option>\n" +
                "<option value=\"2-day\">2-Days Expedited</option>\n" +
                "<option value=\"6-day\">6-Days Ground</option>\n" +
                "</select>" );

        p("<h4>Billing Information</h4>\n" +

                "<label for=\"ccnum\">Credit card number</label>\n" +
                "<input type=\"text\" id=\"ccnum\" name=\"cardnumber\" placeholder=\"1111-2222-3333-4444\" required>\n" +

                "<label for=\"expmonth\">Exp Month</label>\n" +
                "<input type=\"text\" id=\"expmonth\" name=\"expmonth\" placeholder=\"September\" required>\n" +

                "<div class=\"row\">\n" +
                "<div class=\"col-50\">\n" +
                "<label for=\"expyear\">Exp Year</label>\n" +
                "<!--<input type=\"text\" id=\"expyear\" name=\"expyear\" placeholder=\"2018\" pattern=\"[2020-2100]\">-->\n" +
                "<input type=\"text\" id=\"expyear\" name=\"expyear\" placeholder=\"2018\" min=\"2018\" max=\"2100\" required>\n" +
                "</div>\n" +

                "<div class=\"col-50\">\n" +
                "<label for=\"cvv\">CVV</label>\n" +
                "<input type=\"text\" id=\"cvv\" name=\"cvv\" placeholder=\"352\" pattern=\"[0-9]{3}\" required>\n" +
                "</div>\n" +
                "</div>\n" );

        p("<h4>Receipt/Tracking</h4>\n" +

                "<label for=\"phone\">Phone Number</label>\n" +
                "<input type=\"tel\" id=\"phone\" name=\"phone\" placeholder=\"1-555-666-7777\" pattern=\"[0-9]{1,}-[0-9]{3}-[0-9]{3}-[0-9]{4}\" required>\n" +

                "<label for=\"email\">Email</label>\n" +
                "<input type=\"text\" id=\"email\" name=\"email\" placeholder=\"john@example.com\" required>\n" +

                //"<input type=\"submit\" id=\"submitbtn\" value=\"Submit\" />\n" +
                "<button id=\"submitbtn\" type=\"submit\" value=\"Submit\" >SUBMIT</button>\n" +
                "</div>\n" +
                "</form>\n" +

                "</div>\n" +
                "<!-- end of order form card div -->" );


        p("<br>\n" +

                "</div>\n" +
                "<!-- end of main div -->\n" +
                "</section>\n" +
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
                "</div> \n" );
        p("</body>");
        p("</html>");

        //clean up
        output = null;
        response = null;
    }
    private void p(String message) throws IOException{
        if(output!=null) {
            output.println(message);
        } else {
            output = response.getWriter();
        }
    }
}
