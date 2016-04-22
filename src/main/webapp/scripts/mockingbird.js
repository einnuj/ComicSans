/**
 * Created by lilsh on 4/14/2016.
 */

function mockUser() {
    $.ajax({
        url: "/UserServlet",
        type: "get",
        success: function(responseText) {
            $("#userJson > a").text(responseText);
        }
    })
}

function mockComic() {
    $.ajax({
        url: "/ComicServlet",
        type: "get",
        data: {"name" : "NEW NAME", "description" : "NEW BIO"},
        success: function(responseText) {
            $("#comicJson > a").text(responseText);

            console.log(responseText.id);
            console.log(responseText.metadata.name);
        }
    })
}

function mockObjectify() {
    $.ajax({
        url: "/InitServlet",
        type: "get",
        success: function(responseText) {
            $("#objectify > a").text(responseText);
        }
        })}

function mockEditUser() {
    $.ajax({
        url: "/UserServlet",
        type: "post",
        data: {"name" : "Junnie", "description" : "BRAND NEW"},
        success: function(responseText) {
            $("#editUserJson > a").text(responseText);
        }
    })
}