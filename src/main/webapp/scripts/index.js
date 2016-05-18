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

$(document).ready(main());