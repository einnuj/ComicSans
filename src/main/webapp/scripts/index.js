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
            //var imgSrc = getRandomCoverArt();

            var imgSrc = allComics[key].metadata.coverImage;

            if (imgSrc == null || imgSrc == "")
                imgSrc = "images/covers/DoenutCover.png";
            else {
                imgSrc = retrieveImage(imgSrc);
                if (imgSrc == null || imgSrc == "")
                    imgSrc = "images/covers/DoenutCover.png";
            }


        var html = "<li><a onclick='passBySession(" + allComics[key].id + "," + 1 + ")' role='button'><img src=" + imgSrc + "></a></li>"
        targetDiv.append(html);
    }
}

function retrieveImage(img_key) {
    var path = "";
    $.ajax({
        url: "/upload",
        type: "GET",
        async: false,
        data: {"action": "GET COVER", "blob_key": img_key},
        success: function (responseText) {
            path = responseText;
        },
    });
    return path;
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