package com.best_duck;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CategoryServlet", urlPatterns = "/products",description = "category")
public class CategoryServlet extends HttpServlet {
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

        String category= req.getParameter("category");

        p("");
        p("<!doctype html>");
        p("<html lang=\"en\">");
        //Head
        p("<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <link href=\"./css/style.css\" rel=\"stylesheet\">\n" +
                "    <link href=\"./css/products.css\" rel=\"stylesheet\">\n" +
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
        //body
        p("<body>");
        //start of table
        p("<div class=\"main-container\">\n" +
                "    <div class=\"main\">\n" +
                "        <a href=\"products.html\"><button>Back to Products Page</button></a>\n" +
                "\n" +
                "        <!-- Product Table -->\n" +
                "        <div class=\"product-table\">\n" +
                "            <table>\n" +
                "                <caption><h1>"+category+"</h1></caption>\n" +
                "                <thead>\n" +
                "                <tr>\n" +
                "                    <!--<th>Table Header</th>-->\n" +
                "                </tr>\n" +
                "                </thead>\n" +
                "                <tfoot>\n" +
                "                <tr>\n" +
                "                    <!--<td colspan=\"3\">Copyright &copy; 2018 Example Organization</td>-->\n" +
                "                </tr>\n" +
                "                </tfoot>\n" +
                "                <tbody>\n");



            ArrayList<Map<String, Object>> productList = com.best_duck.Database.getAllProductsByCategory(category);
            int i=0;
            for(Map<String, Object> product : productList) {
                if( (i%3)==0 ) {
                    p("<tr>");
                }
                p("<td>\n"
                        // dynamically generate the cards
                        + "<div class=\"productcategory-card\">\n"
                        + "<a href=\"details?sku="+product.get("sku")+"\">\n"
                        + "<img src=\""+ product.get("image")
                        + "\" alt=\"" +
                        product.get("name") +
                        "\" style=\"width:100%\">\n"
                        + "</a>\n"
                        + "<div class=\"productcategory-card-container\">\n"
                        + "<h4><b>"
                        + product.get("name")
                        + "</b></h4>\n"
                        + "<p>Producer: " +
                        product.get("producer") +
                        "</p>\n"
                        + "<p>Price: " +
                        product.get("price") +
                        "</p>\n"
                        + "<p>Currently In Stock: " +
                        product.get("stock") +
                        "</p>\n"
                        + "</div>\n"
                        + "</div>\n"
                        + "</td>\n");
            if( (i%3)==2 ) {
                p("</tr>");
            }
            i++;
        }





        //end of table
        p("                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </div> <!-- end of \"product-table\" div tag -->\n" +
                "    </div> <!-- end of \"main\" div tag -->\n" +
                "</div>");
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
