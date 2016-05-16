function passBySession(id, image) {
    sessionStorage.setItem('id_to_load', id);
    sessionStorage.setItem("ComicNumberSelected", image);
    window.location.assign("summary.jsp");
}

function userToProfile(strID) {
    var userID = "";
    $.ajax({
        url: "/UserServlet",
        type: "get",
        //data: {"uID": strID}, WITH THIS LINE COMMENTED OUT IT WILL ALWAYS GIVE THE USER LOGGED IN AT THE MOMENT
        async: false,
        success: function(responseText) {
            $("#userJson > a").text(responseText);
            userID = responseText.googleId;
        },
    });
    sessionStorage.setItem('user_to_profile', userID);
    window.location.assign("profile.jsp");
}