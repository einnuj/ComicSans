/**
 * Created by lilsh on 4/14/2016.
 */

function mockUser() {
    $.ajax({
        url: "/UserServlet",
        type: "get",
        success: function(responseText) {
            $("#userJson > a").text(responseText.metadata.name);
        }
    })
}

function mockComic() {
    $.ajax({
        url: "/ComicServlet.create",
        type: "get",
        success: function(responseText) {
            $("#comicJson > a").text(responseText);
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