function generateOrderDetail(){
    let firstName = document.getElementById("firstName").value;
    let lastName = document.getElementById("lastName").value;
    let address = document.getElementById("address").value;
    let city = document.getElementById("city").value;
    let state = document.getElementById("state").value;
    let zip = document.getElementById("zip").value;
    let shippingMethod = document.getElementById("shippingMethod").value;
    let sku = document.getElementById("SKU").value;
    let quantity = document.getElementById("qty").value;
    let ccnum = document.getElementById("ccnum").value;
    let expMonth = document.getElementById("expMonth").value;
    let expYear = document.getElementById("expYear").value;
    let cvv = document.getElementById("cvv").value;
    let phoneNum = document.getElementById("phone").value;
    let email = document.getElementById("email").value;
    let infoSummary = "Name: "+firstName+ " "+ lastName+"%0D"+
        "Address: "+address+ " "+ city+", "+state+ " "+ zip+"%0D"+
        "Shipping Method: "+ shippingMethod+"%0D"+
        "SKU: "+ sku+"%0D"+"Quantity: "+ quantity+ "%0D"+
        "Email: "+email+"%0D"+
        "Phone Number: "+ phoneNum;
    window.open('mailto:test@example.com?subject=Order Information&body='+infoSummary);
}