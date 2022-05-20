<%--
    Created by IntelliJ IDEA.
    User: 11720
    Date: 2022/5/9
    Time: 9:47
  --%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>categories</title>
    <link href="css/style.css" rel="stylesheet">
    <link href="css/products.css" rel="stylesheet">
    <link href="css/product_category.css" rel="stylesheet">
</head>
<body>
<header>
    <div class="topNav">
        <a href="index.html">Home</a>
        <a class="active" href="categories">Products</a>
        <a href="team.html">Team</a>
        <a href="about.html">About</a>
        <div class="topNav-right">
            <a href="shoppingcart"><i class="fas fa-shopping-cart"></i>Shopping Cart</a>
        </div>
    </div>
</header>
<h1 style="font-family: ui-rounded;font-size: 40px">Categories</h1>
<main-container>
    <div class="basic-grid">
        <div class="card"><!--1-->
            <a href="products?category=Storage">
                <img class="product-img" src="img/storage.jpg" alt="storage">
                <h2 style="alignment-baseline: baseline;alignment: center">Storage</h2>
            </a>
        </div>
        <div class="card"><!--2-->
            <a href="products?category=GPUs">
                <img class="product-img" src="img/graphicCard.jpg" alt="Video Graphics Cards">
                <h2 style="alignment-baseline: baseline;alignment: center">Video Graphics Cards</h2>
            </a>
        </div>
        <div class="card"><!--3-->
            <a href="products?category=Processors">
                <img class="product-img" src="img/cpu.jpg" alt="CPUs & Processors">
                <h2 style="alignment-baseline: baseline;alignment: center">CPUs & Processors</h2>
            </a>
        </div>
        <div class="card"><!--4-->
            <a href="products?category=RAMs">
                <img class="product-img" src="img/RAM.jpeg" alt="Memory (RAM)">
                <h2 style="alignment-baseline: baseline;alignment: center">Memory (RAM)</h2>
            </a>
        </div>
        <div class="card"><!--5-->
            <a href="products?category=Motherboards">
                <img class="product-img" src="img/motherboard.jpeg" alt="Accessories">
                <h2>Motherboards</h2>
            </a>
        </div>
        <div class="card"><!--6-->
            <a href="products?category=Cases">
                <img class="product-img" src="img/computercases.jpeg" alt="Computer Cases">
                <h2>Computer Cases</h2>
            </a>
        </div>
        <div class="card"><!--7-->
            <a href="products?category=Power_Supplies">
                <img class="product-img" src="img/powerSupply.jpg" alt="Power Supplies">
                <h2>Power Supplies</h2>
            </a>
        </div>
        <div class="card"><!--8-->
            <a href="products?category=Coolings">
                <img class="product-img" src="img/fans.jpg" alt="Fans, Heatsinks & Cooling">
                <h2>Fans & Cooling</h2>
            </a>
        </div>
    </div>
    <div class="main">
        <div class="product-table">
            <table>
                <caption><h1>Orders</h1></caption>
                <tbody id="order_tbody">

                </tbody>
            </table>
        </div>
    </div>
</main-container>
<footer>
    <p>BestDuck Web Design, Copyright &copy; 2022</p>
</footer>
</body>
<script src="jquery-3.6.0-min.js"></script>
<script type=text/JavaScript>
    const baseUrl = "<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"%>";
    const xhr = new XMLHttpRequest();
    xhr.open('GET', baseUrl + 'webapi/orderlist');
    xhr.send();
    xhr.onreadystatechange = function () {
        // 判断 （服务端返回了所有结果）
        if (xhr.readyState === 4) {
            //判断响应码状态 200 404 403 401 500
            if (xhr.status >= 200 && xhr.status < 300) {
                let data = JSON.parse(xhr.response);
                let list = [];
                data.forEach(order => {
                    const xhr2 = new XMLHttpRequest()
                    xhr2.open('GET', baseUrl + 'webapi/getproducts/' + order.sku);
                    xhr2.send();
                    xhr2.onreadystatechange = function () {
                        if (xhr2.readyState === 4) {
                            //判断响应码状态 200 404 403 401 500
                            if (xhr2.status >= 200 && xhr2.status < 300) {
                                let pd = JSON.parse(xhr2.response)
                                let imagelink = pd.image;
                                let name = pd.name;
                                list.push("<td>\n"
                                    // dynamically generate the cards
                                    + "<div class=\"productcategory-card\">\n"
                                    + "<a href=\"details?sku=" + order.sku + "\">\n"
                                    + "<img src=\"" + imagelink +
                                    "\" alt=\"" +
                                    name +
                                    "\" style=\"width:100%\">\n"
                                    + "</a>\n"
                                    + "<div class=\"productcategory-card-container\">\n"
                                    + "<h4><b>"
                                    + name
                                    + "</b></h4>\n"
                                    + "<p>First name:" +
                                    order.first_name +
                                    "</p>\n"
                                    + "<p>"
                                    + "<p>OrderID:" +
                                    order.order_num +
                                    "</p>\n"
                                    + "<p>"
                                    + "<p>Address:" +
                                    order.address +
                                    "</p>\n"
                                    + "<p>"
                                    + "<p>City:" +
                                    order.city +
                                    "</p>\n"
                                    + "<p>State:" +
                                    order.state +
                                    "</p>\n"
                                    + "<p>Quantity: " +
                                    ////"Currently In Stock: 10" +
                                    order.quantity +
                                    "</p>\n"
                                    + "</div>\n"
                                    + "</div>\n"
                                    + "</td>\n")
                            }
                        }
                        $("#order_tbody").html(list.join(""))
                    }
                });

            } else {

            }
        }
    }
</script>
</html>