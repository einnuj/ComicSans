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
        url: "/ComicServlet",
        type: "post",
        data: {"action" : "CREATE COMIC", "name" : "CAPTAIN PLAN IT", "description" : "dotally rad comik"},
        success: function(responseText) {
            $("#comicJson > a").text(responseText);

            comicId = responseText.id;
            $("#getComicDiv > a").show();
        }
    })
}

function mockCreateChapterAndPages() {
    $.ajax({
        url: "/ComicServlet",
        type: "post",
        data: {"action" : "CREATE CHAPTER", "id" : comicId, "name" : "Napter Chame", "files" : "file1"},
        success: function() {
            console.log("SUCCESS");
        }
    })
}

function mockGetComic() {
    $.ajax({
        url:"/ComicServlet",
        type: "get",
        data: {"id" : comicId},
        success: function(responseText) {
            $("#getComicDiv > a").text(responseText);
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

function hideGetComic() {
    $("#getComicDiv > a").hide();
}

$(document).ready(hideGetComic());