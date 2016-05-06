/**
 * directory JS scripts for sorting/filtering comics
 * Created by Junnie on 5/5/2016.
 */

function sortComics(filter) {
    var comicsMap = getAllComicsAsArray();

    switch (filter) {
        case 'alph':
            
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
        if (filterUpper != comicsMap[key]) {
            $("#" + key).hide();
        }
        else {
            $("#" + key).show();
        }
    }
}