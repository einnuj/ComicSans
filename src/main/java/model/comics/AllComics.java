package model.comics;

import java.util.HashMap;

/**
 * Created by cherrinkim on 4/28/16.
 */
public class AllComics {
    HashMap<String, WebComic> comics;

    public AllComics() {
        comics = new HashMap<>();
    }

    public void addComic(String comicTarget, WebComic myComic){
        comics.put(comicTarget, myComic);
    }

    public WebComic getComic(String comicTarget){
        return comics.get(comicTarget);
    }

    public void deleteComic(String comicTarget){
        comics.remove(comicTarget);
    }
}
