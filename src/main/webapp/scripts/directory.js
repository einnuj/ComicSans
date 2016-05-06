/**
 * directory JS scripts for sorting/filtering comics
 * Created by Junnie on 5/5/2016.
 */

function sortComics(filter) {

    switch (filter) {
        case 'alph':
            $(".comic-listing").sort(function (a, b) {
                return $(a).data("name").localeCompare($(b).data("name"));
            }).map(function () {
                return $(this).closest('.comicBlock');
            }).each(function (_, comicBlock) {
                $(comicBlock).parent().append(comicBlock);
            });
            break;
        default:
            console.log("SOMETHING ASPLODED, CAP'N!");
    }
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

function isLoggedIn() {
    $.ajax({
        url: "/UserServlet",
        type: "get",
        async: false,
        success: function(responseText) {
            console.log(responseText);
            user = responseText;
            return responseText;
        },
        error: function(_) {
            return false;
        }
    })
}

$(document).ready(isLoggedIn());