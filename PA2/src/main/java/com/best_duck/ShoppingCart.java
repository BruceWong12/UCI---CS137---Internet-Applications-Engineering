package com.best_duck;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "shoppingCart", urlPatterns = "/shoppingcart",description = "shoppingcart")
public class ShoppingCart extends HttpServlet {
    PrintWriter output;
    HttpServletResponse response;
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        renderPage(req,res);
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        renderPage(req,res);
    }
    private void renderPage(HttpServletRequest req, HttpServletResponse res) throws IOException {
        response = res;
        response.setContentType("text/html;charset=UTF-8");
        p("");
        p("<!doctype html>");
        p("<html lang=\"en\">");

        //HEAD TAG
        p("<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <link href=\"./css/style.css\" rel=\"stylesheet\">\n" +
            "    <link href=\"./css/shoppingCart.css\" rel=\"stylesheet\">\n" +
            "    <link href=\"./css/product_category.css\" rel =\"stylesheet\">\n" +
            "\n" +
            "    <header>\n" +
            "        <div class=\"topNav\">\n" +
            "            <!-- Left-aligned links -->\n" +
            "            <a href=\"./index.html\">Home</a>\n" +
            "            <!-- https://www.w3schools.com/howto/howto_css_subnav.asp -->\n" +
            "            <a class=\"active\" href = \"categories\">Products</a>\n" +
            "            <a href=\"./team.html\">Team</a>\n" +
            "            <a href=\"./about.html\">Contact</a>\n" +
            "\n" +
            "            <!-- Right-aligned links-->\n" +
            "            <div class=\"topNav-right\">\n" +
            "                <a href =\"shoppingcart\"><i class=\"fas fa-shopping-cart\"></i>Shopping Cart</a>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </header>\n" +
            "</head>");

        //BODY TAG
        p("<body>");

        //MAIN SECTION

        // Get session object
        HttpSession session = req.getSession();

        // Get the cart
        Map<String, Integer> cart = (Map<String, Integer>)session.getAttribute("cart");

        // Get viewed items
        LinkedList<String> viewed = (LinkedList<String>)session.getAttribute("viewed");

        // if the session is new, the cart won't exist
        if(cart==null) {
            cart = new HashMap<String, Integer>();
            session.setAttribute("cart", cart);
        }

        // retrieve form data
        String productSKU;
        Integer quantity;


        // add to the session object
        if(req.getParameter("sku")!= null && req.getParameter("qty")!=null) {
            //System.out.println("get parameter");
            productSKU = req.getParameter("sku");
            quantity = Integer.parseInt( req.getParameter("qty") );
            cart.put(productSKU, quantity);
        }


        p("<!-- Main -->\n" +
                "<section>\n" +
                "<div class=\"main\">\n" +
                "");

        p("<p><a href=\"products.html\"><i class=\"fas fa-arrow-left\"></i> Continue Shopping</a></p>\n" +
                "<br>\n" +
                "");

        p("<!-- Shopping Cart Detail Card -->\n" +
                "<div class=\"w3-card-4 shoppingcartcard\" style=\"width:48%; float:left;\">\n" +
                "<center><strong><h2>Your Shopping Cart</h2></strong></center>\n" +
                "");

        p("<table>");

        p("<tr>\n" +
                "<th></th>\n" +
                "<th>PRODUCT</th>\n" +
                "<th>QTY</th>\n" +
                "<th>PRICE</th>\n" +
                "<th></th>\n" +
                "<th></th>\n" +
                "</tr>\n" +
                "");

        //Order Summary
        Float subtotal = (float) 0;
        for(Map.Entry<String, Integer> entry: cart.entrySet()) {
            //System.out.println("view cart");
            String prodID = entry.getKey();
            Integer qty = entry.getValue();
            String qtyString = Integer.toString(qty);
            Map<String, Object> product = Database.getProduct(prodID);
            String productname = (String) product.get("name");
            float price = (float) product.get("price");
            price = ( float )(Math.round(price* 100 )/ 100 );
            String productprice = Float.toString( price );


            // add to subtotal
            subtotal+= (qty * price);

            //Retrieve the main image. This is the first image in the array.
            String imageLink = (String) product.get("image");

		      			/*p("<div class=\"productrow\">\n" +
		      			"<p>product id: "+prodID+", quantity: "+qty+"</p>\n" +
		      			"</div>\n");*/
            p("<tr>\n" +
                    "<td><center><img src=\""+imageLink+"\" alt=\"product image\"></center></td>\n" +
                    "<td>"+productname+"</td>\n" +
                    "<td>"+qtyString+"</td>\n" +
                    "<td>"+productprice+"</td>\n" +
                    "<td><button id=\"button_edit\" class=\"smallbtn\" onclick=\"editItem()\"><i class=\"fas fa-edit\"></i>edit</button></td>\n" +
                    "<td><button id=\"button_trash\" class=\"smallbtn\" onclick=\"deleteItem()\"><i class=\"fas fa-trash-alt\"></i>delete</button></td>\n" +



                    //Modal Windows
                    "<div id=\"editmodal\" class=\"modal\">\n" +
                    "<div class=\"modal-content\">\n" +
                    "<span id=\"editmodal-close\" class=\"modal-close\">X</span>\n" +
                    "<p>Edit Quantity</p>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "<div id=\"trashmodal\" class=\"modal\">\n" +
                    "<div class=\"modal-content\">\n" +
                    "<span id=\"trashmodal-close\" class=\"modal-close\">X</span>\n" +
                    "<p>Are you sure you want to delete item?</p>\n" +
                    "</div>\n" +
                    "</div>\n" +

                    //Button Script
                    "<script>\n" +
                    "var editmodal = document.getElementById(\"editmodal\");\n" +
                    "var trashmodal= document.getElementById(\"trashmodal\");\n" +
                    "var closeeditmodalbtn = document.getElementById(\"editmodal-close\");\n"+
                    "var closetrashmodalbtn = document.getElementById(\"trashmodal-close\")\n" +

                    "//open edit item modal\n" +
                    "function editItem() {\n" +
                    "editmodal.style.display = \"block\";\n" +
                    "}\n" +
                    "//open trash item modal\n" +
                    "function deleteItem() {\n" +
                    "trashmodal.style.display = \"block\";\n" +
                    "}\n" +

                    "//close modal when x is clicked\n" +
                    "closeeditmodalbtn.onclick = function(){\n" +
                    "editmodal.style.display = \"none\";\n" +
                    "}\n" +

                    "closetrashmodalbtn.onclick = function(){\n" +
                    "trashmodal.style.display = \"none\";\n" +
                    "}\n" +
                    "//close modal when anywhere outside is clicked\n" +
                    "window.onclick = function(event) {\n" +
                    "if((event.target == editmodal) || (event.target == trashmodal) ) {\n" +
                    "editmodal.style.display = \"none\";\n" +
                    "trashmodal.style.display = \"none\";\n" +
                    "}\n" +
                    "}\n" +
                    "</script>\n" +

                    "</tr>\n" +
                    "");
        }


        p("</table>");

        p("</div>\n" +
                "<!-- end of product detail card div -->\n" +
                "");

        p("<!-- Order Summary Card -->\n" +
                "<div class=\"w3-card-4 ordersummarycard\" style=\"width:48%; float:left;\">\n" +
                "<center><strong><h2>Order Summary</h2></strong></center>\n" +
                "<p>SUBTOTAL<span class=\"alignright\" style=\"\">$"+subtotal+"</span></p>\n" +
                "<p>DISCOUNT<span class=\"alignright\" style=\"\">$0.00</span></p>\n" +
                "<p>ESTIMATED TOTAL<span class=\"alignright\" style=\"\">$"+subtotal+"</span></p>\n" +
                "<div class=\"container\" align=\"left\">\n" +
                "<form action = \"checkout\" align=\"left\">\n" +
                "<button id=\"checkoutbutton\" type=\"submit\" ><i class=\"fas fa-arrow-right\"></i> PROCEED TO CHECKOUT</button>\n" +
                "</form>\n" +
                "<br>\n" +
                "");

        p("</div>\n" +
                "</div>\n" +
                "<!-- end of order form card div -->\n" +
                "");


        p("</div>\n" +
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
                "</div> \n"+

//                "<!-- go to top button --> \n" +
//                "<a class=\"gotopbtn\" href=\"#\"><span class=\"fas fa-caret-square-up\"></span></a> \n" +
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
