package controller.data;

import model.comics.WebComic;
import model.users.User;

import java.util.List;
import java.util.Map;

public class SearchEngine {

    public static List<Map> search(List<WebComic> comicsList, List<User> userList) {
        // Returns a Map<id, binToDecNumber> where the binToDecNumber is a decimal number, but when converted to binary, represents which of the following had search hits:
        // Comic Name, Chapter Names, Page Names, Author Name, Bio, Comments

        /*
        Call searchComics Function Here
         */

        // Returns a Map<id, binToDecNumber> where the binToDecNumber is a decimal number, but when converted to binary, represents which of the following had search hits:
        // User Name, User Bio

        /*
        Call searchUsers Function Here
         */

        return null;
    }
}
