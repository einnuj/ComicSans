/**
 * directory JS scripts for sorting/filtering comics
 * Created by Junnie on 5/5/2016.
 */

function sortComics(filter) {
    
}

function listAllComics() {
    // Select all Comics
    var allComicsMap = {};
    $(".comic-listing").each(function(comicDiv) {
        allComicsMap[$(this).attr("id")] = $(this).data("genre");
    });
    return allComicsMap;
}

function filterComics() {
    var comicsMap = listAllComics();
    
    console.log(comicsMap);
}

function filterComic(comic) {
    var filter = $('.dropdown-menu :selected').text();

    return filter === comic.metadata.genre;
}