console.log("fuck everything");
// Get the user whose information will fill the page
var currentUser = getUser(sessionStorage.getItem("user_to_profile"));

initializePage();



function getUser(strID) {
    var user = "";
    $.ajax({
        url: "/UserServlet",
        type: "get",
        data: {"uID": strID},
        async: false,
        success: function (responseText) {
            $("#userJson > a").text(responseText);
            user = responseText;
        },
    });
    return user;
}

function initializePage() {
    var meta = currentUser.metadata;

    $("#name-header").html(meta.name);
    $("#comments-header").html("Comments posted: " + meta.numComments);
}