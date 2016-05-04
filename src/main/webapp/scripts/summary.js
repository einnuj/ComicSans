/**
 * Created by Guacamole on 4/14/16.
 */

//$(document).ready(mockObjectify());

var editTitle = false;
var editSummary = false;
var currentUser;
var currentComic = "4644337115725824";

// Get data from a mock comic (datastore) to populate the summary page
getComic();
getCurrentUser();

function editComicSummary(comic) {
    $("#edit-summary").toggle();
    if (editSummary == false) {
        editSummary = true;
    }
    else {
        if ($("#summary-text-area").val() != "")
            $("#summary-paragraph").html($("#summary-text-area").val());
        editSummary = false;
    }

}
function editComicTitle() {
    if (editTitle == true) {
        editTitle = false;
        if ($("#title-text").val() != "")  // if it has, reflect the change in the title header
            $("#title-header").html($("#title-text").val());
    } // else just change boolean flag
    else editTitle = true;

    $("#edit-title").toggle(); // toggle visibility of text box
}
// FOR EDITING THE COVER PAGE IMAGE
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            //localStorage.setItem("cover", e.target.result);
            $('#cover-thumbnail')
                .attr('src', e.target.result)
                .width(150)
                .height(200);
        };

        reader.readAsDataURL(input.files[0]);
    }
}

function getCurrentUser() {
    $.ajax({
        url: "/UserServlet",
        type: "get",
        success: function(responseText) {
            $("#userJson > a").text(responseText);
            getUserHelper(responseText);
        },
    });
}

// If the current user is the author, then toggle off edit controls
function getUserHelper(response) {
    currentUser = response;
    if (currentUser == ""){
        $(".AUTHOR_PRIV").toggle();
        $(".comic-info-descr").css("margin-left", "+=65");
    }
    else if (currentUser.metadata.name != author) {
        $(".AUTHOR_PRIV").toggle();
        $(".comic-info-descr").css("margin-left", "+=65");
    }
}

function getComic() {
    $.ajax({
        url: "/ComicServlet",
        type: "get",
        data: {"id": "4644337115725824"},
        success: function(responseText) {
            $("#comicJson > a").text(responseText);
            waitForAjaxComic(responseText);
        }
    })
}

function waitForAjaxComic(obj) {
    // Get data for each field from JSON object
    currentComic = obj;
    name = obj.name;
    author = obj.metadata.author;
    biography = obj.metadata.bio;
    //cover = obj.metadata.displayPicture; ---- Needs to be added to JSON

    // Set data for the title
    $("#title-header").html(name);
    $("#title-text").html(name);

    // Set the author
    $("#author-header").html("Author: " + author)

    // Set data for the summary
    $("#summary-paragraph").html(biography);
    $("#summary-text-area").html(biography);

    // Set data for favorites
    $("#fav-field").html("Favorites: " + currentComic.metadata.favorites);

    // Set the cover image
    var number = localStorage.getItem("ComicNumberSelected");
    if (number == 1)
        $("#cover-thumbnail").attr("src", "images/covers/CoConutCover.png");
    else if (number == 2)
        $("#cover-thumbnail").attr("src", "images/covers/DoenutCover.png");
    else
        $("#cover-thumbnail").attr("src", "images/covers/DoofusCover.png");
}

function socialButton(type) {
    switch (type) {
        case "SUB":

            if (true) { // user is not subscribed yet
                subscribe(currentComic.id);
                $("#sub-btn").text("UnSubscribe");
            }
            else {
                unsubscribe(currentComic.id); // user is already subscribed
                $("#sub-btn").html("Subscribe");
            }
            break;

        case "FAV":
            if ($("#fav-btn").text().trim() == "Favorite") { // If the user doesn't already like the comic, then allow a like
                favorite(currentComic.id);
                currentComic.metadata.favorites++;
                $("#fav-field").html("Favorites: " + currentComic.metadata.favorites);
                $("#fav-btn").html("UnFavorite" + "<span class=\"glyphicon glyphicon-star\" aria-hidden=\"true\"></span>");
            }
            else {
                unfavorite(currentComic.id);
                currentComic.metadata.favorites--;
                $("#fav-field").html("Favorites: " + currentComic.metadata.favorites);
                $("#fav-btn").html("Favorite" + "<span class=\"glyphicon glyphicon-star\" aria-hidden=\"true\"></span>");
            }
            break;

        case "LIK":
            if (true) {
                like(currentComic.id);
                currentComic.metadata.likes++;
                $("#lik-btn").html("UnLike");
            }
            else {
                unlike(currentComic.id);
                currentComic.likes--;
                $("#lik-btn").html("Like");
            }
            break;

        default:
            console.log("Something's off...");
            break;
    }
}

