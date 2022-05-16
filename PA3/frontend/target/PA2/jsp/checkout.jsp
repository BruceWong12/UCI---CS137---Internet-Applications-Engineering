<%@ page import="java.util.Map" %>
<%--<%@ page import="com.best_duck.servlet.Database" %>--%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="java.util.prefs.AbstractPreferences" %>
<%@ page import="static com.best_duck.config.HttpConfig.http" %><%--
    Created by IntelliJ IDEA.
    User: 11720
    Date: 2022/5/9
    Time: 16:47
  --%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath %>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>checkout</title>
    <link href="css/style.css" rel="stylesheet">
    <link href="css/shoppingCart.css" rel="stylesheet">
    <link href="css/product_category.css" rel="stylesheet">
</head>
<style>
    .list {
        z-index: 2;
        position: absolute;
        background-color: #ffffff;
    }

    .list ul {
        width: 120px;
        clear: both;
        border: 1px solid #A9A9A9;
        display: none;
        list-style: none;
        margin: 0px;
    }

    .list ul li {
        padding-left: 8px;
    }

    .list ul li:hover {
        background-color: #CCC;
    }
</style>
<body>
<header>
    <div class="topNav">
        <a class="active" href="index.html">Home</a>
        <a href="jsp/products.jsp">Products</a>
        <a href="team.html">Team</a>
        <a href="about.html">About</a>
        <div class="topNav-right">
            <a href="shoppingcart"><i class="fas fa-shopping-cart"></i> Shopping Cart</a>
        </div>
    </div>
</header>
<section>
    <div class="main">
        <p><a href="shoppingcart"><i class="fas fa-arrow-left"></i> Back to Shopping Cart page</a></p><br>
        <div class="w3-card-4 shoppingcartcard" style="width:48%; float:left;">
            <center><strong><h2>Order Summary</h2></strong></center>
            <p>SUBTOTAL<span class="alignright">
                    $<%
                Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
                Float subtotal = (float) 0;
                for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                    String prodID = entry.getKey();
                    Integer qty = entry.getValue();
//                    Map<String, Object> product = Database.getProduct(prodID);
                    JSONObject product =
                            JSON.parseObject(
                                    http.sync("/getproducts/" + prodID)
                                            .bodyType("application/json")
                                            .get()
                                            .getBody()
                                            .toString());
                    float price = (float) product.getFloat("price");
                    System.out.println(prodID);
                    // add to subtotal
                    subtotal += (qty * price);
                }
                session.setAttribute("subtotal", subtotal);
                out.println(subtotal);
            %>
                </span></p>
            <p>DISCOUNT<span class="alignright" style="">$0.00</span></p>
            <p>ESTIMATED TOTAL<span class="alignright" id="ESTIMATEDTOTAL">
                    $<%
                out.println(session.getAttribute("subtotal"));
            %>
                </span></p>
            <div class="container" align="left">
                <br>
                <center><strong><h2>Your Shopping Cart</h2></strong></center>
                <table>
                    <tr>
                        <th></th>
                        <th>PRODUCT</th>
                        <th>QTY</th>
                        <th>PRICE</th>
                    </tr>
                    <%
                        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                            String prodID = entry.getKey();
                            Integer qty = entry.getValue();
                            String qtyString = Integer.toString(qty);
                            JSONObject product =
                                    JSON.parseObject(
                                            http.sync("/getproducts/" + prodID)
                                                    .bodyType("application/json")
                                                    .get()
                                                    .getBody()
                                                    .toString());
                            String productName = (String) product.get("name");
                            String productprice =  product.getString("price");

                            //Retrieve the main image. This is the first image in the array.
                            String imageLinks = (String) product.get("image");
                            String[] imageLinksArray = imageLinks.split(",");
                            String mainImage = imageLinksArray[0];

                            out.println("<tr>\n" +
                                    "<td><center><img src=\"" + mainImage + "\" alt=\"product image\"></center></td>\n" +
                                    "<td>" + productName + "</td>\n" +
                                    "<td>" + qtyString + "</td>\n" +
                                    "<td>" + productprice + "</td>\n" +
                                    "</tr>\n" +
                                    "");
                        }
                    %>
                </table>

            </div>
        </div>
        <div class="w3-card-4 orderform-card" style="width:48%; float:right;boarder:1px">
            <form action="processing" method="post" name="orderform" onsubmit="">
                <div class="col-50">
                    <strong><h2>Order Form</h2></strong>
                    <h4>Shipping Information</h4>
                    <div class="row">
                        <div class="col-50">
                            <label for="fname">First Name</label>
                            <input type="text" id="fname" name="firstname" placeholder="John" required>
                        </div>
                        <div class="col-50">
                            <label for="lname">Last Name</label>
                            <input type="text" id="lname" name="lastname" placeholder="Doe" required>
                        </div>
                    </div>
                    <label for="adr">Address</label>
                    <input type="text" id="adr" name="address" placeholder="542 W. 15th Street" required>

                    <label for="city">City</label>
                    <input type="text" id="city" name="city" placeholder="New York" required>
                    <div class="row">
                        <div class="col-50">
                            <label for="state">State</label>
                            <select id="state" class="select-css" name="state">
                                <option value="AL">Alabama</option>
                                <option value="AK">Alaska</option>
                                <option value="AZ">Arizona</option>
                                <option value="AR">Arkansas</option>
                                <option value="CA">California</option>
                                <option value="CO">Colorado</option>
                                <option value="CT">Connecticut</option>
                                <option value="DE">Delaware</option>
                                <option value="DC">District Of Columbia</option>
                                <option value="FL">Florida</option>
                                <option value="GA">Georgia</option>
                                <option value="HI">Hawaii</option>
                                <option value="ID">Idaho</option>
                                <option value="IL">Illinois</option>
                                <option value="IN">Indiana</option>
                                <option value="IA">Iowa</option>
                                <option value="KS">Kansas</option>
                                <option value="KY">Kentucky</option>
                                <option value="LA">Louisiana</option>
                                <option value="ME">Maine</option>
                                <option value="MD">Maryland</option>
                                <option value="MA">Massachusetts</option>
                                <option value="MI">Michigan</option>
                                <option value="MN">Minnesota</option>
                                <option value="MS">Mississippi</option>
                                <option value="MO">Missouri</option>
                                <option value="MT">Montana</option>
                                <option value="NE">Nebraska</option>
                                <option value="NV">Nevada</option>
                                <option value="NH">New Hampshire</option>
                                <option value="NJ">New Jersey</option>
                                <option value="NM">New Mexico</option>
                                <option value="NY">New York</option>
                                <option value="NC">North Carolina</option>
                                <option value="ND">North Dakota</option>
                                <option value="OH">Ohio</option>
                                <option value="OK">Oklahoma</option>
                                <option value="OR">Oregon</option>
                                <option value="PA">Pennsylvania</option>
                                <option value="RI">Rhode Island</option>
                                <option value="SC">South Carolina</option>
                                <option value="SD">South Dakota</option>
                                <option value="TN">Tennessee</option>
                                <option value="TX">Texas</option>
                                <option value="UT">Utah</option>
                                <option value="VT">Vermont</option>
                                <option value="VA">Virginia</option>
                                <option value="WA">Washington</option>
                                <option value="WV">West Virginia</option>
                                <option value="WI">Wisconsin</option>
                                <option value="WY">Wyoming</option>
                            </select>
                        </div>
                        <div class="col-50">
                            <label for="taxregionname">Tax Region Name</label>
                            <input type="text" id="taxregionname" name="taxregionname" placeholder="" required>
                            <div class="list">
                                <ul id="ul"></ul>
                            </div>
                        </div>
                        <div class="col-50">
                            <label for="zip">Zip</label>
                            <input type="text" id="zip" name="zip" placeholder="10001" required>
                        </div>
                    </div>
                    <label for="Shipping Method">Shipping Method:</label>

                    <select class="select-css" id="Shipping Method" name="shippingmethod">
                        <option value="overnight">Overnight</option>
                        <option value="2-day">2-Days Expedited</option>
                        <option value="6-day">6-Days Ground</option>
                    </select>
                    <h4>Billing Information</h4>
                    <label for="ccnum">Credit card number</label>
                    <input type="text" id="ccnum" name="cardnumber" placeholder="1111-2222-3333-4444" required>
                    <label for="expmonth">Exp Month</label>
                    <input type="text" id="expmonth" name="expmonth" placeholder="September" required>
                    <div class="row">
                        <div class="col-50">
                            <label for="expyear">Exp Year</label>
                            <input type="text" id="expyear" name="expyear" placeholder="2018" min="2018" max="2100"
                                   required>
                        </div>
                        <div class="col-50">
                            <label for="cvv">CVV</label>
                            <input type="text" id="cvv" name="cvv" placeholder="352" pattern="[0-9]{3}" required>
                        </div>
                    </div>
                    <h4>Receipt/Tracking</h4>
                    <label for="phone">Phone Number</label>
                    <input type="tel" id="phone" name="phone" placeholder="1-555-666-7777"
                           pattern="[0-9]{1,}-[0-9]{3}-[0-9]{3}-[0-9]{4}" required>
                    <label for="email">Email</label>
                    <input type="text" id="email" name="email" placeholder="john@example.com" required>
                    <button id="submitbtn" type="submit" value="Submit">SUBMIT</button>
                </div>
            </form>

        </div>
        <br>
    </div>
</section>
<footer><p>BestDuck Web Design, Copyright &copy; 2020</p></footer>
</body>
<script src="jquery-3.6.0-min.js"></script>
<script>
    const baseUrl = "<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"%>";
    let ul = document.getElementById("ul");
    let content_div = document.getElementById("taxregionname");

    //当用户离开输入字段时对其进行验证：
    content_div.onblur = function () {
        content_div.style.borderColor = "initial";
        ul.style.display = "none";
    }
    //当元素获得用户输入时运行的脚本
    content_div.oninput = function () {
        selectKeys();
    }
    content_div.onpropertychange = function () {
        selectKeys();
    }

    function selectKeys() {
        //var value=$("#content").val()		//Jquery写法
        let value = document.getElementById("taxregionname").value;
        if (value !== "") {
            const xhr = new XMLHttpRequest();
            xhr.open('GET', baseUrl + 'webapi/gettaxregionname/' + value);
            xhr.send();
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4) {
                    if (xhr.status >= 200 && xhr.status < 300) {
                        let dataList = JSON.parse(xhr.response);
                        ul.innerHTML = "";
                        for (let i = 0; i < dataList.length; i++) {
                            let li = document.createElement("li");
                            li.innerHTML = dataList[i];
                            li.onmousedown = function () {
                                document.getElementById("taxregionname").value = this.innerHTML;
                                const xhr2 = new XMLHttpRequest();
                                xhr2.open('GET', baseUrl+'webapi/getrate/' + this.innerHTML);
                                xhr2.send();
                                xhr2.onreadystatechange = function () {
                                    if (xhr2.readyState === 4) {
                                        if (xhr2.status >= 200 && xhr2.status < 300) {
                                            let rate = JSON.parse(xhr2.response)[0];
                                            let ptotal = "<%=session.getAttribute("subtotal")%>";
                                            let ESTIMATEDTOTAL = parseFloat(ptotal) * (1 + parseFloat(rate));
                                            document.getElementById("ESTIMATEDTOTAL").innerText = `$\${ptotal} * ( 1 + \${rate} ) = $\${ESTIMATEDTOTAL.toFixed(2)}`
                                        }
                                    }
                                }
                            }
                            ul.appendChild(li);
                            ul.style.display = "block";
                        }
                    }
                }
            }
        } else {
            ul.style.display = "none";
        }
    }
</script>
</html>