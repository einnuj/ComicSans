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
        if (Object.keys(allComicsAsMap).length <= 5) {
            return allComicsAsMap;
        }
        if (!(Math.random() < 0.5)) {
            delete allComicsAsMap[key];
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
            var imgSrc = retrieveImage(allComics[key].metadata.coverImage);
    
            if (imgSrc == null || typeof imgSrc == 'undefined' || imgSrc == '') {
                imgSrc = "images/covers/DoofusCover.png";
            }
    
            var html = "<li><a onclick='passBySession(" + allComics[key].id + "," + 0 + ")' role='button'><img src=" + imgSrc + "></a></li>";
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

    $("#user-favorites").show();

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

    $("#user-likes").show();

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


$(document).ready(main());
$(document).ready(loadCovers());

