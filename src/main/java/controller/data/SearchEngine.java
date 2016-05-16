package controller.data;

import model.comics.WebComic;
import model.users.User;
import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;

import java.util.*;

public class SearchEngine {

    public static List<Map> search(List<WebComic> comicsList, List<User> userList, String query) {
        // Returns a Map<id, binToDecNumber> where the binToDecNumber is a decimal number, but when converted to binary, represents which of the following had search hits:
        // Comic Name, Chapter Names, Page Names, Author Name, Bio, Comments

        searchComics(comicsList, query);

        // Returns a Map<id, binToDecNumber> where the binToDecNumber is a decimal number, but when converted to binary, represents which of the following had search hits:
        // User Name, User Bio

        /*
        Call searchUsers Function Here
         */

        return null;
    }

    private static Map<Long, Integer> searchComics(List<WebComic> comicsList, String query) {
        Map<Long, Integer> hits = new HashMap<Long, Integer>();

        for (WebComic comic : comicsList) {
            
        }

        // The Collection<Emit> that comes as the result of the Trie parse contains the keyword, and the start/end index of where it was found.
        Trie trie = Trie.builder()
                .caseInsensitive()
                .addKeyword("casing")
                .build();
        Collection<Emit> emits = trie.parseText("CaSiNg");

        return hits;
    }
}
