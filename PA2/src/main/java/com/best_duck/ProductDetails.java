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
                "<style>"+
                ".rate {\n" +
                "    float: ;\n" +
                "    height: 46px;\n" +
                "    padding: 0 8px;\n" +
                "}\n" +
                ".rate:not(:checked) > input {\n" +
                "    position:absolute;\n" +
                "    top:-9999px;\n" +
                "}\n" +
                ".rate:not(:checked) > label {\n" +
                "    float:right;\n" +
                "    width:1em;\n" +
                "    overflow:hidden;\n" +
                "    white-space:nowrap;\n" +
                "    cursor:pointer;\n" +
                "    font-size:30px;\n" +
                "    color:#ccc;\n" +
                "}\n" +
                ".rate:not(:checked) > label:before {\n" +
                "    content: '★ ';\n" +
                "}\n" +
                ".rate > input:checked ~ label {\n" +
                "    color: #ffc700;    \n" +
                "}\n" +
                ".rate:not(:checked) > label:hover,\n" +
                ".rate:not(:checked) > label:hover ~ label {\n" +
                "    color: #deb217;  \n" +
                "}\n" +
                ".rate > input:checked + label:hover,\n" +
                ".rate > input:checked + label:hover ~ label,\n" +
                ".rate > input:checked ~ label:hover,\n" +
                ".rate > input:checked ~ label:hover ~ label,\n" +
                ".rate > label:hover ~ input:checked ~ label {\n" +
                "    color: #c59b08;\n" +
                "}</style>"+
                "<script src = ../../js/product_detail.js></script>\n" +
                "<header>\n" +
                "<div class=\"topNav\">\n" +
                "<a href=\"index.html\">Home</a>\n" +
                "<a class=\"active\" href = \"categories\">Products</a>\n" +
                "<a href=\"team.html\">Team</a>\n" +
                "<a href=\"about.html\">About</a>\n" +
                "<!-- Right-aligned links-->\n" +
                "<div class=\"topNav-right\">\n" +
                "<a href =\"shoppingcart\"><i class=\"fas fa-shopping-cart\"></i> Shopping Cart</a>\n" +
                "</div>\n" +
                "</div>\n" +
                "</header>\n");
        p("</header>\n");
        p("<body>\n");


        p("<!-- product detail-->\n" +
                "<div class=\"product_detail-card\" >\n" +
                "<img src=\"" + imagelink + "\">\n" +
                "<h4><b>\n" + name + "</b></h4>\n" +
                " <div class=\"rate\">\n" +
                "    <input type=\"radio\" id=\"star5\" name=\"rate\" value=\"5\" />\n" +
                "    <label for=\"star5\" title=\"text\">5 stars</label>\n" +
                "    <input type=\"radio\" id=\"star4\" name=\"rate\" value=\"4\" />\n" +
                "    <label for=\"star4\" title=\"text\">4 stars</label>\n" +
                "    <input type=\"radio\" id=\"star3\" name=\"rate\" value=\"3\" />\n" +
                "    <label for=\"star3\" title=\"text\">3 stars</label>\n" +
                "    <input type=\"radio\" id=\"star2\" name=\"rate\" value=\"2\" />\n" +
                "    <label for=\"star2\" title=\"text\">2 stars</label>\n" +
                "    <input type=\"radio\" id=\"star1\" name=\"rate\" value=\"1\" />\n" +
                "    <label for=\"star1\" title=\"text\">1 star</label>\n" +
                "  </div>"+
                "<tr>"+
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
                                    "<input type=\"number\" id=\"qty\" name=\"qty\" placeholder=\"1\" min=\"1\" max=\"5\" required>\n" +
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
