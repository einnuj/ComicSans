var currentUser = getUser(sessionStorage.getItem("user_to_profile"));

function getUser(strID) {
    var user = "";
    $.ajax({
        url: "/UserServlet",
        type: "get",
        data: {"uID": strID},
        async: false,
        success: function (responseText) {
            user = responseText;
        }
    });
    return user;
}

function main() {
    $.ajax({
        url: "/ComicServlet",
        type: "GET",
        data: {"index" : true},
        success: function(response) {
            generateFavorites(response);
            generateLikes(response);
            var allComics = getTopSuggestions(response);
            addComicsIntoHTML(allComics);
        }
    });
}

function getTopSuggestions(allComics) {
    var allComicsAsMap = allComics;

    for (var key in allComicsAsMap) {
        if (!allComicsAsMap.hasOwnProperty(key)) {
            continue;
        }
        else if (!(Math.random() < 0.5)) {
            delete allComicsAsMap[key];
        }
        if (allComicsAsMap.length <= 5) {
            return allComicsAsMap;
        }
    }
    return allComicsAsMap;
}

function addComicsIntoHTML(allComics) {
    var targetDiv = $("#top-suggestions > ul");
        for (var key in allComics) {
        if (!allComics.hasOwnProperty(key)) {
            continue;
        }
        var imgSrc = getRandomCoverArt();
        var html = "<li><a onclick='passBySession(" + allComics[key].id + "," + imgSrc[0] + ")' role='button'><img src=" + imgSrc[1] + "/></a></li>"
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
        if(!userFavorites.hasOwnProperty(key)){
            continue;
        }
        imageKey = retrieveImage(allComicsAsMap[key].metadata.coverImage);
        targetDiv.append('<div class="comic-listing"><img src=' + imageKey + '></div>' );
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
        targetDiv.append('<div class="comic-listing"><img src=' + imageKey + '></div>' );
    }
}
$(document).ready(main());
$(document).ready(loadCovers());

