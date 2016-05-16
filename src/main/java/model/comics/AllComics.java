package model.comics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cherrinkim on 4/28/16.
 */
public class AllComics {
    HashMap<Long, WebComic> comics;

    public AllComics() {
        comics = new HashMap<>();
    }

    /* Getters */
    public WebComic getComic(Long comicTarget){
        return comics.get(comicTarget);
    }

    public HashMap<Long, WebComic> getComics() {
        return comics;
    }

    public List<WebComic> getComicsAsList() {
        return new ArrayList<WebComic>(comics.values());
    }

    /* Methods */

    public void addComic(Long comicTarget, WebComic myComic){
        comics.put(comicTarget, myComic);
    }

    public void deleteComic(Long comicTarget){
        comics.remove(comicTarget);
    }
}
