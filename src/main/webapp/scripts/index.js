function main() {
    $.ajax({
        url: "/ComicServlet",
        type: "GET",
        data: {"index" : true},
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
        var html = "<li><a onclick='passBySession(" + allComics[key].id + "," + imgSrc[0] + ")' role='button'><img src=" + imgSrc[1] + "/></a></li>"
        targetDiv.append(html);
    }
}

function getRandomCoverArt() {
    switch(Math.floor(Math.random() * (3))) {
        case 0:
            return [0,"images/covers/CoConutCover.png"];
        case 1:
            return [1,"images/covers/DoenutCover.png"];
        case 2:
            return [2,"images/covers/DoofusCover.png"];
    }
}

$(document).ready(main());