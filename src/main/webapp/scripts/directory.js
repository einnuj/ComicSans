/**
 * directory JS scripts for sorting/filtering comics
 * Created by Junnie on 5/5/2016.
 */

function sortComics(filter) {
    var dataName;

    switch (filter) {
        case 'alph':
            dataName = "name";
            break;
        case 'auth':
            dataName = "author";
            break;
        default:
            console.log("SOMETHING ASPLODED, CAP'N!");
    }

    $(".comic-listing").sort(function (a, b) {
        return $(a).data(dataName).localeCompare($(b).data(dataName));
    }).map(function () {
        return $(this).closest('.comicBlock');
    }).each(function (_, comicBlock) {
        $(comicBlock).parent().append(comicBlock);
    });
}

function getAllComicsAsMap() {
    var allComicsMap = {};

    // Iterate through all elements st class=comic-listing
    $(".comic-listing").each(function(comicDiv) {
        // Add to the Map: key = id, value = genre
        allComicsMap[$(this).attr("id")] = $(this).data("genre");
    });
    return allComicsMap;
}

function getAllComicsAsArray() {
    var allComicsArray = new Array();

    $(".comic-listing").each(function(comicDiv) {
        // Add to the Map: key = id, value = genre
        allComicsArray.push({id: $(this).attr("id"), name: $(this).data("name")});
    });
    return allComicsArray;
}

function filterComics(filter) {
    var comicsMap = getAllComicsAsMap();
    var filterUpper = filter.toUpperCase();
    
    for (var key in comicsMap) {
        if (!comicsMap.hasOwnProperty(key)) {
            continue;
        }
        if (filterUpper === "FAVE" && user) {
            if (!user.metadata.favorites[key]) {
                $("#" + key).hide();
            }
            else {
                $("#" + key).show();
            }
        }
        else if (filterUpper != comicsMap[key]) {
            $("#" + key).hide();
        }
        else {
            $("#" + key).show();
        }
    }
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

$(document).ready(loadCovers());