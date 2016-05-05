/**
 * directory JS scripts for sorting/filtering comics
 * Created by Junnie on 5/5/2016.
 */

function sortComics(filter) {
    
}

function filterComics() {
    var filteredComics = "${allComics.getComicsAsList}";
    filteredComics = filteredComics.filter(filterComic);
}

function filterComic(comic) {
    var filter = $('.dropdown-menu :selected').text();

    return filter === comic.metadata.genre;
}