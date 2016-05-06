package model.comics;

import com.googlecode.objectify.annotation.Subclass;

/**
 * A class that represents a single page in a comic.
 * Created by einnuj on 4/7/2016.
 */
@Subclass
public class ComicPage extends ComicMedia {
    private String imgURL;
    private int pageNumber;
    private int chapterNumber;

    ComicPage(){}

    // TODO: evaluate purpose of this constructor
    public ComicPage(String name, String imgURL, int pageNumber, int chapterNumber) {
        super(name);

        this.imgURL = imgURL;
        this.pageNumber = pageNumber;
        this.chapterNumber = chapterNumber;
    }

    public ComicPage(String name, String imgURL) {
        super(name);

        this.imgURL = imgURL;
    }

    /* Getters */

    public String getImgURL() {
        return imgURL;
    }
    public int getPageNumber(){
        return pageNumber;
    }
    public int getChapterNumber(){
        return chapterNumber;
    }
    public void setChapterNumber(int chapterNumber){
        this.chapterNumber = chapterNumber;
    }
    public void setPageNumber(int pageNumber){
        this.pageNumber=pageNumber;
    }
    public void setImgURL(String imgURL){
        this.imgURL=imgURL;
    }

}
