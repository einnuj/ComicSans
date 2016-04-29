/**
 * Created by einnuj.
 */

function authenticate() {
    var currentURL = window.location.href;
    var response;

    $.ajax({
        url: "/AuthServlet",
        type: "get",
        data: {"currentURL" : currentURL},
        success: function(responseText) {
            $("#logLink").attr("href", responseText.url);
            if (responseText.loggedIn) {
                $("#logLink").text("Log Out");
            }
            else {
                $("#logLink").text("Log In");
            }
            $("#logLink").show();
        }
    });
}

function fakeAjax() {
    $("#ajaxDiv > a").text("You just got AJAX'D");
}

$(document).ready(authenticate());