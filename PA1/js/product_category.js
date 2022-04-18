$(document).ready(function () {
    /* Go to Top Button */
    var elmClass = '.gotopbtn'; // Adjust this accordingly.

    //Check to see if the window is top if not then display button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) { // 300px from top
            $(elmClass).fadeIn();
        } else {
            $(elmClass).fadeOut();
        }
    });

    //Click event to scroll to top
    $(elmClass).click(function () {
        $('html, body').animate({scrollTop: 0}, 800);
        return false;
    });
    /*----------------------------*/
});

export function openSearch() {
    document.getElementById("myOverlay").style.display = "block";
}

export function closeSearch() {
    document.getElementById("myOverlay").style.display = "none";
}
