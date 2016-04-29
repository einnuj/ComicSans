package model.metadata.fields;

/**
 * A Field Class that represents a User's bookmarking
 * Created by einnuj on 4/7/2016.
 */
public class Bookmark extends AbstractField {

    // Like Favorite and such, there are no fields/methods necessary.
    private int chapterNumber;
    private int pageNumber;

    public Bookmark(){}

    public Bookmark(String comicTarget, String userOrigin, int cNum, int pNum) {
        super(comicTarget, userOrigin);
        this.chapterNumber = cNum;
        this.pageNumber = pNum;
    }

    public void setChapterNumber(int n){
        chapterNumber = n;
    }

    public void setPageNumber(int n){
        pageNumber = n;
    }

    public int getChapterNumber(){
        return chapterNumber;
    }

    public int getPageNumber(){
        return pageNumber;
    }
}
