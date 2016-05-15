/**
 * Created by Guacamole on 4/14/16.
 */

//$(document).ready(mockObjectify());

var editTitle = false;
var editSummary = false;
var currentUser;
var comicId = sessionStorage.getItem("id_to_load");
var checkResult; // used for checking if someone is liked/favorite/subscribed (holds ajax result)

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
        // turn off commenting controls
        $("#comment-input").toggle();
        $("#comment-submit").toggle();
    }
    else if (currentUser.metadata.name != author) {
        $(".AUTHOR_PRIV").toggle();
        $(".comic-info-descr").css("margin-left", "+=65");
    }

    // * * * at this point the currentComic and currentUser are available for use ma nigga * * *


    //var testCase = isSubscribed(comicId); --- buggy? skip subscribe for now
    //console.log(testCase);

    /*  BUGGY
    checkResult = isSubscribed(comicId);
    // if the user is subscribed to the comic
    if (checkResult) {
        // set button text to unsubscribe
        $("#sub-btn").html("Unsubscribe" + "<span class=\"glyphicon glyphicon-plus\" aria-hidden=\"true\"></span>");
    } // else do nothing (text will be subscribe by default
    */

    checkResult = checkLike(comicId);
    // if the user liked the comic
    if (checkResult) {
        // set button text to unlike
        $("#lik-btn").html("Unlike" + "<span class=\"glyphicon glyphicon-heart\" aria-hidden=\"true\"></span>");
    } // else do nothing (text will be favorite by default

    checkResult = checkFavorite(comicId);
    // if the user favorited the comic
    if (checkResult) {
        // set button text to unfavorite
        $("#fav-btn").html("Unfavorite" + "<span class=\"glyphicon glyphicon-star\" aria-hidden=\"true\"></span>");
    } // else do nothing (text will be favorite by default
}

function getComic() {
    $.ajax({
        url: "/ComicServlet",
        type: "get",
        data: {"id": comicId},
        success: function(responseText) {
            $("#comicJson > a").text(responseText);
            getComicHelper(responseText);
        }
    })
}

function getComicHelper(obj) {
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
    var number = sessionStorage.getItem("ComicNumberSelected");
    if (number == 1)
        $("#cover-thumbnail").attr("src", "images/covers/CoConutCover.png");
    else if (number == 2)
        $("#cover-thumbnail").attr("src", "images/covers/DoenutCover.png");
    else
        $("#cover-thumbnail").attr("src", "images/covers/DoofusCover.png");

    // Fill out the comments section
    fillComments();
}

function socialButton(type) {
    if (currentUser == "")
        return;
    switch (type) {
        case "SUB":
            if ($("#sub-btn").text().trim() == "Subscribe") { // If the user doesn't already like the comic, then allow a like
                //subscribe(comicId);
                $("#sub-btn").html("Unsubscribe" + "<span class=\"glyphicon glyphicon-plus\" aria-hidden=\"true\"></span>");
            }
            else {
                //unsubscribe(comicId);
                $("#sub-btn").html("Subscribe" + "<span class=\"glyphicon glyphicon-plus\" aria-hidden=\"true\"></span>");
            }
            break;

        case "FAV":
            if ($("#fav-btn").text().trim() == "Favorite") { // If the user doesn't already like the comic, then allow a like
                favorite(comicId);
                currentComic.metadata.favorites++;
                $("#fav-field").html("Favorites: " + currentComic.metadata.favorites);
                $("#fav-btn").html("Unfavorite" + "<span class=\"glyphicon glyphicon-star\" aria-hidden=\"true\"></span>");
            }
            else {
                unfavorite(comicId);
                currentComic.metadata.favorites--;
                $("#fav-field").html("Favorites: " + currentComic.metadata.favorites);
                $("#fav-btn").html("Favorite" + "<span class=\"glyphicon glyphicon-star\" aria-hidden=\"true\"></span>");
            }
            break;

        case "LIK":
            if ($("#lik-btn").text().trim() == "Like") { // If the user doesn't already like the comic, then allow a like
                like(comicId);
                currentComic.metadata.likes++;
                $("#lik-btn").html("Unlike" + "<span class=\"glyphicon glyphicon-heart\" aria-hidden=\"true\"></span>");
            }
            else {
                unlike(comicId);
                currentComic.metadata.likes--;
                $("#lik-btn").html("Like" + "<span class=\"glyphicon glyphicon-heart\" aria-hidden=\"true\"></span>");
            }
            break;

        default:
            console.log("Something's off...");
            break;
    }
}

function appendComment() {
    var commentText = $("#comment-input").val();

    if (commentText == "")
        return;

    addComment(comicId, commentText);

    $.ajax({
        url: "/ComicServlet",
        type: "get",
        data: {"id": comicId},
        async: false,
        success: function(responseText) {
            $("#comicJson > a").text(responseText);
            currentComic = responseText;
        }
    })

    var timestamp = new Date();
    $("#comment-thread").prepend('<li class="user-comments">' + 'Posted by: ' + currentUser.name + ' - ON: ' + timestamp.toString() + '<br>' + commentText + '</li>');
    $("#comment-input").val('');
}
function fillComments() {
    var users = [];
    var descriptions = [];
    var time = [];
    getComments(comicId, users, time, descriptions);

    var comTime;
    for (i=0;i<users.length;i++) {
        comTime = new Date(time[i]);
        $("#comment-thread").prepend('<li class="user-comments">' + 'Posted by: ' + users[i] + ' - ON: ' + comTime.toString() + '<br>' + descriptions[i] + '</li>');
    }
}
