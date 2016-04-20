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

$(document).ready(mockObjectify());