package model.metadata;

import model.metadata.fields.Comment;
import model.metadata.fields.Favorite;
import model.metadata.fields.Like;

import java.util.List;

/**
 * A class that represents the Metadata of a Comic.
 * Created by einnuj on 4/7/2016.
 */
public class ComicMetadata {
    private List<Like> likeList;
    private List<Favorite> favoriteList;
    private List<Comment> commentList;
    private GenreEnum genre;
    private String description;

    // This should be a User
    private String author;
}
