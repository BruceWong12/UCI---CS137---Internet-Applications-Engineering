package best_duck;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(name = "cpuCatServlet", urlPatterns = "/cpu")
public class cpuCatServlet extends HttpServlet {
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
                "            <a href=\"../../index.html\">Home</a>\n" +
                "            <!-- https://www.w3schools.com/howto/howto_css_subnav.asp -->\n" +
                "            <a class=\"active\" href = \"../../products.html\">Products</a>\n" +
                "            <a href=\"../../team.html\">Team</a>\n" +
                "            <a href=\"../../about.html\">Contact</a>\n" +
                "\n" +
                "            <!-- Right-aligned links-->\n" +
                "            <div class=\"topNav-right\">\n" +
                "                <a><i class=\"fas fa-shopping-cart\"></i>Shopping Cart</a>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </header>\n" +
                "</head>");
        //body
        p("<body>\n" +
                "\n" +
                "<!-- Computer Cases Categories -->\n" +
                "<div class=\"main-container\">\n" +
                "    <div class=\"main\">\n" +
                "        <a href=\"../../products.html\"><button>Back to Products Page</button></a>\n" +
                "\n" +
                "        <!-- Product Table -->\n" +
                "        <div class=\"product-table\">\n" +
                "            <table>\n" +
                "                <caption><h1>CPUs & Processors</h1></caption>\n" +
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
                "                <tbody>\n" +
                "\n" +
                "                <!-- First Row -->\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <div class=\"productcategory-card\">\n" +
                "                            <a href=\"cpus_&_processors_category_detail_Core_i7.html\">\n" +
                "                                <img src=\"./img/products/cpus_&_processors/Core_i7.jpg\" alt=\"Core_i7\" style=\"width:100%\">\n" +
                "                            </a>\n" +
                "                            <div class=\"productcategory-card-container\">\n" +
                "                                <h4><b>Core i7-12700K Desktop Processor 12 (8P+4E) Cores up to 5.0 GHz</b></h4>\n" +
                "                                <p>Producer: Intel</p>\n" +
                "                                <p>Price: $59.9</p>\n" +
                "                                <p>Currently In Stock: 10</p>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </td>\n" +
                "\n" +
                "                    <td>\n" +
                "                        <div class=\"productcategory-card\">\n" +
                "                            <a href=\"cpus_&_processors_category_detail_Ryzen_9.html\">\n" +
                "                                <img src=\"./img/products/cpus_&_processors/Ryzen_9.jpg\" alt=\"Ryzen_9\" style=\"max-width:100%\">\n" +
                "                            </a>\n" +
                "                            <div class=\"productcategory-card-container\">\n" +
                "                                <h4><b>Ryzen 9 5900X 4th Gen 12-core, 24-threads Unlocked Desktop Processor</b></h4>\n" +
                "                                <p>Producer: AMD</p>\n" +
                "                                <p>Price: $59.9</p>\n" +
                "                                <p>Currently in Stock: 5</p>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </div> <!-- end of \"product-table\" div tag -->\n" +
                "    </div> <!-- end of \"main\" div tag -->\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "<footer>\n" +
                "    <p>BestDuck Web Design, Copyright &copy; 2022</p>\n" +
                "</footer>\n" +
                "\n" +
                "</body>\n" +
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
