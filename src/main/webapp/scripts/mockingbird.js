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
            $("#comicJson > a").text(JSON.stringify(responseText));

            comicId = responseText.id;
            $("#getComicDiv > a").show();
        }
    })
}

function mockCreateChapterAndPages() {
    $.ajax({
        url: "/ComicServlet",
        type: "post",
        data: {"action" : "CREATE CHAPTER", "id" : 5682617542246400, "name" : "Napter Chame", "files" : "file1"},
        success: function() {
            console.log("SUCCESS");
        }
    })
}

function mockGetComic() {
    $.ajax({
        url:"/ComicServlet",
        type: "get",
        data: {"id" : 5682617542246400},
        success: function(responseText) {
            $("#getComicDiv > a").text(JSON.stringify(responseText));
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
            $("#editUserJson > a").text(JSON.stringify(responseText));
        }
    })
}

function addBookmark() {
    $.ajax({
        url: "/SocialServlet",
        type: "post",
        data: {"action" : "BOOKMARK", "comicId" : "5682617542246400"},
        success: function(responseText) {
            $("#currentUserObject > p").text(JSON.stringify(responseText));
        }
    })
}

function removeBookmark() {
    $.ajax({
        url: "/SocialServlet",
        type: "post",
        data: {"action" : "UNBOOKMARK", "comicId" : "5682617542246400"},
        success: function(responseText) {
            $("#currentUserObject > p").text(JSON.stringify(responseText));
        }
    })
}

function addComment() {
    $.ajax({
        url: "/SocialServlet",
        type: "post",
        data: {"action" : "COMMENT", "comicId" : "5682617542246400", "comment" : "Junnie's Awesome."},
        success: function(responseText) {
            $("#currentUserObject > p").text(JSON.stringify(responseText));
        }
    })
}

function addFavorite() {
    $.ajax({
        url: "/SocialServlet",
        type: "post",
        data: {"action" : "FAVORITE", "comicId" : "5682617542246400"},
        success: function(responseText) {
            $("#currentUserObject > p").text(JSON.stringify(responseText));
        }
    })
}

function removeFavorite() {
    $.ajax({
        url: "/SocialServlet",
        type: "post",
        data: {"action" : "UNFAVORITE", "comicId" : "5682617542246400"},
        success: function(responseText) {
            $("#currentUserObject > p").text(JSON.stringify(responseText));
        }
    })
}

function addLike() {
    $.ajax({
        url: "/SocialServlet",
        type: "post",
        data: {"action" : "LIKE", "comicId" : "5682617542246400"},
        success: function(responseText) {
            $("#currentUserObject > p").text(JSON.stringify(responseText));
        }
    })
}

function removeLike() {
    $.ajax({
        url: "/SocialServlet",
        type: "post",
        data: {"action" : "UNLIKE", "comicId" : "5682617542246400"},
        success: function(responseText) {
            $("#currentUserObject > p").text(JSON.stringify(responseText));
        }
    })
}

function addRating() {
    $.ajax({
        url: "/SocialServlet",
        type: "post",
        data: {"action" : "RATE", "comicId" : "5682617542246400", "rating" : "3"},
        success: function(responseText) {
            $("#currentUserObject > p").text(JSON.stringify(responseText));
        }
    })
}
function callTestUser() {
    $.ajax({
        url:"/ComicServlet",
        type: "get",
        data: {"id" : "5682617542246400"},
        success: function(responseText) {
            $("#currentUserObject > p").text(JSON.stringify(responseText));
        }
    })
}

function hideGetComic() {
    $("#getComicDiv > a").hide();
}

$(document).ready(hideGetComic());
$(document).ready(callTestUser());