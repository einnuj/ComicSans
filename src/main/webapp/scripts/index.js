function main() {
    $.ajax({
        url: "/ComicServlet",
        type: "GET",
        success: function(response) {
            var allComics = getTopSuggestions(response);
            addComicsIntoHTML(allComics);
        }

    })
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
        var html = "<li><a href='summary.js'><img src=" + imgSrc + "/></a></li>"
        targetDiv.append(html);
    }
}

function getRandomCoverArt() {
    switch(Math.floor(Math.random() * (3))) {
        case 0:
            return "images/covers/CoConutCover.png";
        case 1:
            return "images/covers/DoenutCover.png";
        case 2:
            return "images/covers/DoofusCover.png";
    }
}

$(document).ready(main());