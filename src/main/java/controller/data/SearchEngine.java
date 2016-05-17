package controller.data;

import model.comics.ComicChapter;
import model.comics.ComicPage;
import model.comics.WebComic;
import model.metadata.ComicMetadata;
import model.users.User;
import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;

import java.util.*;

public class SearchEngine {

    public static List<Map> search(List<WebComic> comicsList, List<User> userList, String query) {
        // Returns a Map<id, binToDecNumber> where the binToDecNumber is a decimal number, but when converted to binary, represents which of the following had search hits:
        // Comic Name, Chapter Names, Page Names, Author Name, Bio, Comments

        Map<Long, String> comicResults = searchComics(comicsList, query);

        // Returns a Map<id, binToDecNumber> where the binToDecNumber is a decimal number, but when converted to binary, represents which of the following had search hits:
        // User Name, User Bio

        /*
        Call searchUsers Function Here
         */

        return null;
    }

    /**
     * Searches through a List of WebComics for a keyword and generates a Map<WebComic id, String binary> result.
     * @param comicsList - the List of WebComics to search through
     * @param query - the keyword to search for
     * @return - a Map with key being Long WebComic id, String binary such that the binary represents which fields contained the keyword:
     *          [0]: WebComic's Name
     *          [1]: WebComic's Chapters' Names
     *          [2]: WebComic's Chapters' Names
     *          [3]: WebComic's Author's Name
     *          [4]: WebComic's Bio
     */
    private static Map<Long, String> searchComics(List<WebComic> comicsList, String query) {
        Map<Long, String> hits = new HashMap<Long, String>();

        for (WebComic comic : comicsList) {
            // Build an Aho-Corasick based Trie that discounts text-overlaps and case-ness with a single keyword: the search query.
            Trie trie = Trie.builder().removeOverlaps().caseInsensitive().addKeyword(query).build();

            char[] comicResults = searchComic(comic, trie);

            hits.put(comic.getId(), new String(comicResults));
        }

        return hits;
    }

    /**
     * Searches through all of a Comics' text fields via a given Trie
     * @param comic - the target WebComic
     * @param trie - the Trie that implements the search algorithm
     * @return a char[] that represents a binary number representing which fields had hits
     */
    private static char[] searchComic(WebComic comic, Trie trie) {
        char[] results = new char[]{'0', '0', '0', '0', '0'};

        List<StringBuilder> collapsedNames = collapseChapterAndPageNames(comic);
        ComicMetadata metadata = comic.getMetadata();

        Emit searchComicName = trie.firstMatch(comic.getName());
        Emit searchChapterNames = trie.firstMatch(collapsedNames.get(0));
        Emit searchPageNames = trie.firstMatch(collapsedNames.get(1));
        Emit searchAuthorName = trie.firstMatch(metadata.getAuthor());
        Emit searchBio = trie.firstMatch(metadata.getBio());

        if (searchComicName != null) {
            results[0] = '1';
        }
        if (searchChapterNames != null) {
            results[1] = '1';
        }
        if (searchPageNames != null) {
            results[2] = '1';
        }
        if (searchAuthorName != null) {
            results[3] = '1';
        }
        if (searchBio != null) {
            results[4] = '1';
        }

        return results;
    }

    /**
     * Takes a WebComic and condenses the name of the Comic, the Comics' Chapters, and the Chapters' Pages, into one.
     * @param comic - the target WebComic
     * @return a List of StringBuilder: [0] is the collapsed Chapter names, and [1] is the collapsed Page names.
     */
    private static List<StringBuilder> collapseChapterAndPageNames(WebComic comic) {
        ArrayList<StringBuilder> results = new ArrayList<StringBuilder>();
        StringBuilder collapsedChapterNames = new StringBuilder();
        StringBuilder collapsedPageNames = new StringBuilder();

        for (ComicChapter chapter : comic.getChildMediaList()) {
            collapsedChapterNames.append(chapter.getName());
            for (ComicPage page : chapter.getChildMediaList()) {
                collapsedPageNames.append(page.getName());
            }
        }

        results.add(collapsedChapterNames);
        results.add(collapsedPageNames);

        return results;
    }
}
