/**
 * directory JS scripts for sorting/filtering comics
 * Created by Junnie on 5/5/2016.
 */

function sortComics(filter) {
    
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

function filterComics() {
    var comicsMap = getAllComicsAsMap();
    var filter = $('.dropdown-menu :selected').text();
    
    for (var key in comicsMap) {
        if (!comicsMap.hasOwnProperty(key)) {
            continue;
        }
        if (filter != comicsMap[key]) {
            $("#" + key).hide();
        }
    }
}

function filterComic(genre, filter) {
    return filter === genre;
}