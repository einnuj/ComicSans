/**
 * Created by einnuj.
 */

function authenticate() {
    var currentURL = window.location.href;

    $.ajax({
        url: "/AuthServlet",
        type: "get",
        data: {"currentURL" : currentURL},
        success: function(responseText) {
            $("#logLink").attr("href", responseText);
            $("#logLink").show();
        }
    });
}

function fakeAjax() {
    $("#ajaxDiv > a").text("You just got AJAX'D");
}

$(document).ready(authenticate());