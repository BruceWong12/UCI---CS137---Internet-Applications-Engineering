package com.best_duck;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "productDetails", urlPatterns = "/details", description = "details")
public class ProductDetails extends HttpServlet {
    PrintWriter output;
    HttpServletResponse response;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession(true);
        LinkedList<String> viewed = (LinkedList<String>) session.getAttribute("viewed");
        if (viewed == null) {
            session.setAttribute("viewed", new LinkedList<String>());
            viewed = (LinkedList<String>) session.getAttribute("viewed");
        }
        String id = req.getParameter("productID");
//		String n = "PS4";
        if (viewed.size() >= 5 && !viewed.contains(id)) {
            viewed.poll();
            viewed.add(id);
        } else if (!viewed.contains(id))
            viewed.add(id);

        renderPage(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        renderPage(req, res);
    }

    private void renderPage(HttpServletRequest req, HttpServletResponse res) throws IOException {
        response = res;
        response.setContentType("text/html;charset=UTF-8");

        //MAIN SECTION

        String sku = req.getParameter("sku");

        Map<String, Object> product = Database.getProduct(sku);
        String name = (String) product.get("name");
        String imagelink = (String) product.get("image");
        String producer = (String) product.get("producer");
        Float price = (Float) product.get("price");
        Integer stock = (Integer) product.get("stock");
        String description = (String) product.get("description");

        p("");
        p("<!doctype html>\n");
        p("<html lang=\"en\">\n");

        //HEAD TAG
        p("<head>\n");
        p("<meta charset=\"utf-8\"> \n" +
                "<title>Detail</title>\n" +
                "<link href=\"./css/style.css\" rel=\"stylesheet\">\n" +
                "<link href=\"./css/product_details.css\" rel=\"products stylesheet\">\n" +
                "<link href=\"https://www.w3schools.com/w3css/4/w3.css\" rel=\"stylesheet\">\n" +
                "<script src = ../../js/product_detail.js></script>\n" +
                "<header>\n" +
                "<div class=\"topNav\">\n" +
                "<a href=\"index.html\">Home</a>\n" +
                "<a class=\"active\" href = \"../../products.html\">Products</a>\n" +
                "<a href=\"team.html\">Team</a>\n" +
                "<a href=\"about.html\">About</a>\n" +
                "<!-- Right-aligned links-->\n" +
                "<div class=\"topNav-right\">\n" +
                "<a><i class=\"fas fa-shopping-cart\"></i> Shopping Cart</a>\n" +
                "</div>\n" +
                "</div>\n" +
                "</header>\n");
        p("</header>\n");
        p("<body>\n");


        p("<!-- product detail-->\n" +
                "<div class=\"product_detail-card\" >\n" +
                "<img src=\"" + imagelink + "\">\n" +
                "<h4><b>\n" + name + "</b></h4>\n" +
                "<p>SKU:" + sku + "</p>\n" +
                "<p>Producer: " + producer + "</p>\n" +
                "<p>Price:" + price + "</p>\n" +
                "<p>Currently In Stock: " + stock + "</p>\n" +
                "<p>Description:" + description +
                "</p>\n" +
                //"<input type=\"submit\" value=\"Add to Cart\"/>" +
                "<!-- Order Form Card -->\n" +
                "<div class=\"w3-card-4 orderform-card\" style=\"width:48%; float:center;\">\n" +
                    "<div class=\"container\" align=\"left\">\n" +
                        "<form action=\"shoppingcart\" align=\"left\">\n" +
                            "<input type=\"hidden\" id=\"sku\" name=\"sku\" value=\"" + product.get("sku") + "\">\n" +
                            "<label align=\"left\">Quantity (5 Max)\n" +
                                    "<input type=\"number\" id=\"qty\" name=\"qty\" placeholder=\"1\" min=\"0\" max=\"5\" required>\n" +
                            "</label>\n" +
                            "<button id=\"add-to-cartbutton\" type=\"submit\"><i class=\"fas fa-cart-plus\"></i> ADD TO CART</button>\n" +
                        "</form>\n" +
                    "</div>\n" +
                "</div>\n" +
                "<!-- end of order form card div -->\n" +
                "</div>\n");


        p("</body>\n");
        p("</html>\n");


        //clean up
        output = null;
        response = null;

    }

    /**
     * Helper Method
     *
     * @param message
     * @throws IOException
     */
    private void p(String message) throws IOException {
        if (output != null) {
            output.println(message);
        } else {
            output = response.getWriter();
        }
    }

}
