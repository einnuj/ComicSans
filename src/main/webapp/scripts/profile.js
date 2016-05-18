// Get the user whose information will fill the page
var currentUser = getUser(sessionStorage.getItem("user_to_profile"));

initializePage();

function getUser(strID) {
    var user = "";
    $.ajax({
        url: "/UserServlet",
        type: "get",
        data: {"uID": strID},
        async: false,
        success: function (responseText) {
            $("#userJson > a").text(responseText);
            user = responseText;
        }
    });
    return user;
}

function initializePage() {
    var meta = currentUser.metadata;

    $("#name-header").html(meta.name);
    $("#created-header").html("Comics created: " + Object.keys(meta.comicsCreatedMap).length);
    $("#comments-header").html("Comments posted: " + meta.numComments);
}
function retrieveImage(img_key) {
    var path = "";
    $.ajax({
        url: "/upload",
        type: "GET",
        async: false,
        data: {"action": "GET IMAGE", "blob_key": img_key},
        success: function (responseText) {
            path = responseText;
        }
    });
    return path;
}

function loadCovers() {
    $(".comic-listing").each(function() {

        // get the image key
        var cover_key = $(this).data("cover");

        // get the actual image tag from the page
        the_image = $(this).find("img");

        // get the image url via ajax
        if (cover_key == ""  || cover_key == null) {
            // no cover for the comic, default to Doenut
            the_image.attr("src", "images/covers/DoenutCover.png");
        } else {
            img_path = retrieveImage(cover_key);

            if (img_path == "")
            // blob wasn't retrieved
                the_image.attr("src", "images/covers/Doofus.png");
            else
                the_image.attr("src", img_path); // blob was retrieved =)
        }

    });
    //jQuery(this).find("img");
}

function loadCreatedComics() {
    var comicsCreated = currentUser.metadata.comicsCreatedMap;
    var imageKey;

    for(var key in comicsCreated) {
        if(!comicsCreated.hasOwnProperty(key)){
            continue;
        }
        imageKey = retrieveImage(comicsCreated[key].metadata.coverImage);
        $(".comics-created").append('<div class="comic-listing"><img src=' + imageKey + '><h3>' + comicsCreated[key].name + '</h3></div>' );
    }
}

function main() {
    $.ajax({
        url: "/ComicServlet",
        type: "GET",
        data: {"index" : true},
        success: function(response) {
            generateFavorites(response);
            generateLikes(response);
            generateCreated(response);
            
        }
    });
}

function generateCreated(allComics){
    if (currentUser == null || typeof currentUser == 'undefined' || currentUser == '') {
        return;
    }
    var allComicsAsMap = allComics;
    var userCreated = currentUser.metadata.comicsCreatedMap;
    var targetDiv = $("#user-created > ul");
    var imageKey;

    if(currentUser != null){
        $("#user-created").show();
    }
    for(var key in userCreated) {
        if(!userCreated.hasOwnProperty(key)){
            continue;
        }
        imageKey = retrieveImage(allComicsAsMap[key].metadata.coverImage);

        if (imageKey == null || typeof imageKey == 'undefined' || imageKey == '') {
            imageKey = "images/covers/DoofusCover.png";
        }

        var html = "<li><a onclick='passBySession(" + allComicsAsMap[key].id + "," + 0 + ")' role='button'><img src=" + imageKey + "></a></li>";
        targetDiv.append(html);
    }
}

function generateFavorites(allComics){
    if (currentUser == null || typeof currentUser == 'undefined' || currentUser == '') {
        return;
    }
    var allComicsAsMap = allComics;
    var userFavorites = currentUser.metadata.favorites;
    var targetDiv = $("#user-favorites > ul");
    var imageKey;

    if(currentUser != null){
        $("#user-favorites").show();
    }
    for(var key in userFavorites) {
        if(!userFavorites.hasOwnProperty(key)) {
            continue;
        }
        imageKey = retrieveImage(allComicsAsMap[key].metadata.coverImage);

        if (imageKey == null || typeof imageKey == 'undefined' || imageKey == '') {
            imageKey = "images/covers/DoofusCover.png";
        }

        var html = "<li><a onclick='passBySession(" + allComicsAsMap[key].id + "," + 0 + ")' role='button'><img src=" + imageKey + "></a></li>";
        targetDiv.append(html);
    }
}

function generateLikes(allComics){
    if (currentUser == null || typeof currentUser == 'undefined' || currentUser == '') {
        return;
    }
    var allComicsAsMap = allComics;
    var userLikes = currentUser.metadata.likes;
    var targetDiv = $("#user-likes > ul");
    var imageKey;

    if(currentUser != null){
        $("#user-likes").show();
    }
    for(var key in userLikes) {
        if(!userLikes.hasOwnProperty(key)){
            continue;
        }
        imageKey = retrieveImage(allComicsAsMap[key].metadata.coverImage);

        if (imageKey == null || typeof imageKey == 'undefined' || imageKey == '') {
            imageKey = "images/covers/DoofusCover.png";
        }

        var html = "<li><a onclick='passBySession(" + allComicsAsMap[key].id + "," + 0 + ")' role='button'><img src=" + imageKey + "></a></li>";
        targetDiv.append(html);
    }
}

function passBySession(id, image) {
    sessionStorage.setItem('id_to_load', id);
    sessionStorage.setItem("ComicNumberSelected", image);
    window.location.assign("summary.jsp");
}

$(document).ready(main());
$(document).ready(loadCovers());
$(document).ready(loadCreatedComics());


