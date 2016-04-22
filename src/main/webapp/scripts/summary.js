/**
 * Created by Guacamole on 4/14/16.
 */

//$(document).ready(mockObjectify());

var editTitle = false;
var editSummary = false;

// Get data from a mock comic to populate the summary page
//var JSONcomic = '{"name":"CoConut","childMediaList":null,"id":null,"metadata":{"bio":"Follow the adventures of CoConut, the most relatable fruit in the world.","name":"Comic Sam","displayPicture":"images/covers/CoConutCover.png","bookmarkList":[{"comicTarget":"Comic Sam","userOrigin":"Junnie","timeCreatedMillis":1461279320918},{"comicTarget":"Comic Sam","userOrigin":"Maggie","timeCreatedMillis":1461279320919},{"comicTarget":"Comic Sam","userOrigin":"Chaerin","timeCreatedMillis":1461279320919},{"comicTarget":"Comic Sam","userOrigin":"Barack Obama","timeCreatedMillis":1461279320919},{"comicTarget":"Comic Sam","userOrigin":"Alexander Hamilton","timeCreatedMillis":1461279320919},{"comicTarget":"Comic Sam","userOrigin":"Richard McKenna","timeCreatedMillis":1461279320919},{"comicTarget":"Comic Sam","userOrigin":"Jennifer Wong","timeCreatedMillis":1461279320919}],"commentList":[{"comicTarget":"Comic Sam","userOrigin":"Maggie","timeCreatedMillis":1461279320919,"description":"Great Comic!","lastEditedTimeMillis":0},{"comicTarget":"Comic Sam","userOrigin":"John Cena","timeCreatedMillis":1461279320919,"description":"Great Comic!","lastEditedTimeMillis":0},{"comicTarget":"Comic Sam","userOrigin":"Jennifer Wong","timeCreatedMillis":1461279320919,"description":"Great Comic!","lastEditedTimeMillis":0},{"comicTarget":"Comic Sam","userOrigin":"Son Goku","timeCreatedMillis":1461279320919,"description":"Great Comic!","lastEditedTimeMillis":0}],"favoriteList":[{"comicTarget":"Comic Sam","userOrigin":"Junnie","timeCreatedMillis":1461279320918},{"comicTarget":"Comic Sam","userOrigin":"Maggie","timeCreatedMillis":1461279320919},{"comicTarget":"Comic Sam","userOrigin":"John","timeCreatedMillis":1461279320919},{"comicTarget":"Comic Sam","userOrigin":"Chaerin","timeCreatedMillis":1461279320919},{"comicTarget":"Comic Sam","userOrigin":"Barack Obama","timeCreatedMillis":1461279320919},{"comicTarget":"Comic Sam","userOrigin":"Alexander Hamilton","timeCreatedMillis":1461279320919},{"comicTarget":"Comic Sam","userOrigin":"Richard McKenna","timeCreatedMillis":1461279320919}],"likeList":[{"comicTarget":"Comic Sam","userOrigin":"Junnie","timeCreatedMillis":1461279320919},{"comicTarget":"Comic Sam","userOrigin":"John","timeCreatedMillis":1461279320919},{"comicTarget":"Comic Sam","userOrigin":"Barack Obama","timeCreatedMillis":1461279320919},{"comicTarget":"Comic Sam","userOrigin":"John Cena","timeCreatedMillis":1461279320919},{"comicTarget":"Comic Sam","userOrigin":"Son Goku","timeCreatedMillis":1461279320919}],"author":"Maggie Lei","genre":"UNLISTED","ratingList":[{"comicTarget":"Comic Sam","userOrigin":"John","timeCreatedMillis":1461279320919,"rating":4},{"comicTarget":"Comic Sam","userOrigin":"Chaerin","timeCreatedMillis":1461279320919,"rating":4},{"comicTarget":"Comic Sam","userOrigin":"Alexander Hamilton","timeCreatedMillis":1461279320919,"rating":4},{"comicTarget":"Comic Sam","userOrigin":"John Cena","timeCreatedMillis":1461279320919,"rating":4},{"comicTarget":"Comic Sam","userOrigin":"Richard McKenna","timeCreatedMillis":1461279320919,"rating":4},{"comicTarget":"Comic Sam","userOrigin":"Jennifer Wong","timeCreatedMillis":1461279320919,"rating":4},{"comicTarget":"Comic Sam","userOrigin":"Son Goku","timeCreatedMillis":1461279320919,"rating":4}]}}';

//var name;
//var author;
//var biography;
//var cover;
//var JSONcomic = '';



mockComic();
//var obj = JSON.parse(JSONcomic);

//var mockUser = mockUser();
//var mockObjectify = mockObjectify();

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

// ------------------------------------- MOCK DATA (mockingbird.js)-----------------------------------------

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
            console.log("Inside " + responseText);
            JSONcomic = responseText;

            var obj = JSON.parse(JSONcomic);

            // Get data for each field from JSON object
            name = obj.name;
            author = obj.metadata.author;
            biography = obj.metadata.bio;
            //cover = obj.metadata.displayPicture; ---- Needs to be added to JSON string

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
    })
}
