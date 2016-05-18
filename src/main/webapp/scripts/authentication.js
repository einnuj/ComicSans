function authenticate() {
    var currentURL = window.location.href;

    $.ajax({
        url: "/AuthServlet",
        type: "get",
        data: {"currentURL" : currentURL},
        success: function(responseText) {
            var logLinkDiv = $("#logLink");
            logLinkDiv.attr("href", responseText.url);
            if (responseText.loggedIn) {
                logLinkDiv.text("Log Out");
            }
            else {
                logLinkDiv.text("Log In");
            }
            logLinkDiv.show();
        }
    });
}

$(document).ready(authenticate());