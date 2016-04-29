/**
 * Created by Guacamole on 4/14/16.
 */

//$(document).ready(mockObjectify());

var editTitle = false;
var editSummary = false;
var currentUser;
var currentComic;

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
        data: {"id": "5066549580791808"},
        success: function(responseText) {
            $("#comicJson > a").text(responseText);
            waitForAjaxComic(responseText);
        }
    })
}

<<<<<<< HEAD
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
            console.log(currentComic.metadata.name); 
            break;

        case "FAV":
            console.log("Favorited");
            break;

        case "LIK":
            console.log("Liked");
            break;

        default:
            console.log("Something's fucky...");
            break;
    }
}
function subscribe(){
    $.ajax({
        type: "POST",
        url: "/SocialServlet",
        data: {"action" : "SUBSCRIBE", "comicId" : "INSERT ID HERE"},
        success: function(result){
            console.log(result);
        },
        error: function(err){
            console.log(err);
        }
    });
}

function favorite(){
    $.ajax({
        type: "POST",
        url: "/SocialServlet",
        data: {"action" : "FAVORITE", "comicId" : "INSERT ID HERE"},
        success: function(result){
            console.log(result);
        },
        error: function(err){
            console.log(err);
        }
    });
}

function like(){
    $.ajax({
        type: "POST",
        url: "/SocialServlet",
        data: {"action" : "LIKE", "comicId" : "INSERT ID HERE"},
        success: function(result){
            console.log(result);
        },
        error: function(err){
            console.log(err);
        }
    });
}
=======
>>>>>>> 7a3af5a2b5d9105a6edd391fd06677e47d9d7e58

