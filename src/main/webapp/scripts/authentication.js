/**
 * Created by einnuj.
 */

function authenticate() {
    $.get("/AuthServlet", function(responseText) {
        $("#logLink").text("Log(In/Out) Here!");
        $("#logLink").attr("href", responseText);
        $("#logLink").show();
    })
}