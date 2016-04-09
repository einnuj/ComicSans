package model.comics;

/**
 * A class that represents a single page in a comic.
 * Created by einnuj on 4/7/2016.
 */
public class ComicPage extends ComicMedia {
    private String imgURL;

    public ComicPage(String name, String imgURL) {
        super(name);
        this.imgURL = imgURL;
    }

    public String getImgURL() {
        return imgURL;
    }
}