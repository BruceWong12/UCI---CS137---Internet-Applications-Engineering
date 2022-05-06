package com.best_duck;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

@WebServlet(name = "ProductsServlet", urlPatterns = "/categories")
public class ProductsServlet extends HttpServlet {
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
        p("<h1 style=\"font-family: ui-rounded;font-size: 40px\">Categories</h1>\n" +
                "\n" +
                "<!--Product Categories -->\n" +
                "\n" +
                "<main-container>\n" +
                "    <div class=\"basic-grid\">\n" +
                "        <div class=\"card\"><!--1-->\n" +
                "            <a href=\"products?category=Storage\">\n" +
                "                <img class= \"product-img\" src=\"img/storage.jpg\" alt=\"storage\">\n" +
                "                <h2 style=\"alignment-baseline: baseline;alignment: center\">Storage</h2>\n" +
                "            </a>\n" +
                "        </div>\n" +
                "        <div class=\"card\"><!--2-->\n" +
                "            <a href=\"products?category=GPUs\">\n" +
                "                <img class= \"product-img\" src=\"img/graphicCard.jpg\" alt=\"Video Graphics Cards\">\n" +
                "                <h2 style=\"alignment-baseline: baseline;alignment: center\">Video Graphics Cards</h2>\n" +
                "            </a>\n" +
                "        </div>\n" +
                "        <div class=\"card\"><!--3-->\n" +
                "            <a href=\"products?category=Processors\">\n" +
                "                <img class= \"product-img\" src=\"img/cpu.jpg\" alt=\"CPUs & Processors\">\n" +
                "                <h2 style=\"alignment-baseline: baseline;alignment: center\">CPUs & Processors</h2>\n" +
                "            </a>\n" +
                "        </div>\n" +
                "        <div class=\"card\"><!--4-->\n" +
                "            <a href=\"products?category=RAMs\">\n" +
                "                <img class= \"product-img\" src=\"img/RAM.jpeg\" alt=\"Memory (RAM)\">\n" +
                "                <h2 style=\"alignment-baseline: baseline;alignment: center\">Memory (RAM)</h2>\n" +
                "            </a>\n" +
                "        </div>\n" +
                "        <div class=\"card\"><!--5-->\n" +
                "            <a href=\"products?category=Motherboards\">\n" +
                "                <img class= \"product-img\" src=\"img/motherboard.jpeg\" alt=\"Accessories\">\n" +
                "                <h2 >Motherboards</h2>\n" +
                "            </a>\n" +
                "        </div>\n" +
                "        <div class=\"card\"><!--6-->\n" +
                "            <a href=\"products?category=Cases\">\n" +
                "                <img class=\"product-img\" src=\"img/computercases.jpeg\" alt=\"Computer Cases\">\n" +
                "                <h2>Computer Cases</h2>\n" +
                "            </a>\n" +
                "        </div>\n" +
                "        <div class=\"card\"><!--7-->\n" +
                "            <a href=\"products?category=Power_Supplies\">\n" +
                "                <img class=\"product-img\" src=\"img/powerSupply.jpg\" alt=\"Power Supplies\">\n" +
                "                <h2>Power Supplies</h2>\n" +
                "            </a>\n" +
                "        </div>\n" +
                "        <div class=\"card\"><!--8-->\n" +
                "            <a href=\"products?category=Coolings\">\n" +
                "                <img class=\"product-img\" src=\"img/fans.jpg\" alt=\"Fans, Heatsinks & Cooling\">\n" +
                "                <h2>Fans & Cooling</h2>\n" +
                "            </a>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</main-container>");
        RequestDispatcher rd = req.getRequestDispatcher("/orderlist");
        try {
            rd.include(req, res); //this inlcudes another servlet
        } catch (ServletException e) {
            System.out.println("include failed");
            throw new RuntimeException(e);
        }
        System.out.println("After include");

        //end of table


        p("</body>\n" +
                "<footer>\n" +
                "    <p>BestDuck Web Design, Copyright &copy; 2022</p>\n" +
                "</footer>\n" +
                "</html>");
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
