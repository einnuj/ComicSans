package controller.data;

import model.comics.ComicChapter;
import model.comics.ComicPage;
import model.comics.WebComic;
import model.metadata.ComicMetadata;
import model.metadata.UserMetadata;
import model.metadata.fields.Comment;
import model.users.User;
import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;

import java.util.*;

public class SearchEngine {

    public static List<Map> search(List<WebComic> comicsList, List<User> userList, String keyword) {

        Map<Long, String> comicResults = searchComics(comicsList, keyword);
        Map<Long, List> commentResults = searchComments(comicsList, keyword);
        Map<Long, String> userResults = searchUsers(userList, keyword);

        return null;
    }

    /**
     * Searches through a List of WebComics for a keyword and generates a Map<WebComic id, String binary> result.
     * @param comicsList - the List of WebComics to search through
     * @param keyword - the keyword to search for
     * @return - a Map with key being Long WebComic id, String binary such that the binary represents which fields contained the keyword, 1 meaning a hit:
     *      [0]: WebComic's Name
     *      [1]: WebComic's Chapters' Names
     *      [2]: WebComic's Chapters' Names
     *      [3]: WebComic's Author's Name
     *      [4]: WebComic's Bio
     */
    private static Map<Long, String> searchComics(List<WebComic> comicsList, String keyword) {
        Map<Long, String> hits = new HashMap<Long, String>();

        // Build an Aho-Corasick based Trie that discounts text-overlaps and case-ness with a single keyword: the search keyword.
        Trie trie = Trie.builder().removeOverlaps().caseInsensitive().addKeyword(keyword).build();

        for (WebComic comic : comicsList) {
            char[] comicResults = searchComic(comic, trie);

            if (comicResults != null) {
                hits.put(comic.getId(), new String(comicResults));
            }
        }

        return hits;
    }

    /**
     * Searches through all of a Comics' text fields via a given Trie
     * @param comic - the target WebComic
     * @param trie - the Trie that implements the search algorithm
     * @return a char[] that represents a binary number representing which fields had hits; null if no hits
     */
    private static char[] searchComic(WebComic comic, Trie trie) {
        char[] results = new char[]{'0', '0', '0', '0', '0'};
        boolean atLeastOneHit = false;

        List<StringBuilder> collapsedNames = collapseChapterAndPageNames(comic);
        ComicMetadata metadata = comic.getMetadata();

        Emit searchComicName = trie.firstMatch(comic.getName());
        Emit searchChapterNames = trie.firstMatch(collapsedNames.get(0));
        Emit searchPageNames = trie.firstMatch(collapsedNames.get(1));
        Emit searchAuthorName = trie.firstMatch(metadata.getAuthor());
        Emit searchBio = trie.firstMatch(metadata.getBio());

        if (searchComicName != null) {
            results[0] = '1';
            atLeastOneHit = true;
        }
        if (searchChapterNames != null) {
            results[1] = '1';
            atLeastOneHit = true;
        }
        if (searchPageNames != null) {
            results[2] = '1';
            atLeastOneHit = true;
        }
        if (searchAuthorName != null) {
            results[3] = '1';
            atLeastOneHit = true;
        }
        if (searchBio != null) {
            results[4] = '1';
            atLeastOneHit = true;
        }

        return (atLeastOneHit) ? results : null;
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

    /**
     * Takes a list of WebComics and a String keyword and searches through all Comments for the keyword sans overlap and case-ness
     * @param comicsList - a List of WebComics to search through
     * @param keyword - a String keyword to look for
     * @return a Map of Long, the Comic ID, and a List of Comments that contain the keyword, or some equivalent representation
     */
    private static Map<Long, List> searchComments(List<WebComic> comicsList, String keyword) {
        Map<Long, List> hits = new HashMap<Long, List>();
        Trie trie = Trie.builder().removeOverlaps().caseInsensitive().addKeyword(keyword).build();

        for (WebComic comic : comicsList) {
            List<Integer> commentsHit = searchComments(comic, trie);

            if (commentsHit != null && !commentsHit.isEmpty()) {
                hits.put(comic.getId(), commentsHit);
            }
        }

        return hits;
    }

    /**
     * Takes a WebComic and Trie and returns a List of Integers for any comments that contain the search keyword.
     * @param comic - the target WebComic to search through Comments for the containing keyword
     * @param trie - the Trie structure for searching
     * @return a List of Integers that represent the index of the Comment that contains the keyword in the Comic Metadata
     */
    private static List<Integer> searchComments(WebComic comic, Trie trie) {
        List<Comment> commentList = comic.getMetadata().getCommentList();
        List<Integer> hitIndices = new ArrayList<Integer>();

        for (int i = 0; i < commentList.size(); i++) {
            Comment currentComment = commentList.get(i);

            Emit searchComment = trie.firstMatch(currentComment.getDescription());

            if (searchComment != null) {
                hitIndices.add(i);
            }
        }

        return hitIndices;
    }

    /**
     * Given a List of Users and a String keyword, searches through all Users' text fields for the keyword
     * @param userList - a List of all Users to search through
     * @param keyword - the keyword to search for
     * @return a Map of Long representing the User's id, and a String, representing the binary representation of which fields have hits, 1 meaning a hit:
     *      [0]: User's Name
     *      [1]: User's Bio
     */
    private static Map<Long, String> searchUsers(List<User> userList, String keyword) {
        Map<Long, String> hits = new HashMap<Long, String>();
        Trie trie = Trie.builder().removeOverlaps().caseInsensitive().addKeyword(keyword).build();

        for (User user : userList) {
            char[] userResults = searchUser(user, trie);

            if (userResults != null) {
                hits.put(user.getId(), new String(userResults));
            }
        }

        return hits;
    }

    /**
     * Searches a User's name and bio using a given Trie
     * @param user - the User to search through
     * @param trie - the search-algo Trie to search with
     * @return a char[] representing whether there was a search hit on the fields in a User, or null if not hits
     */
    private static char[] searchUser(User user, Trie trie) {
        char[] results = new char[] {'0', '0'};
        UserMetadata metadata = user.getMetadata();
        boolean atLeastOneHit = false;

        Emit searchName = trie.firstMatch(metadata.getName());
        Emit searchBio = trie.firstMatch(metadata.getBio());

        if (searchName != null) {
            results[0] = '1';
            atLeastOneHit = true;
        }
        if (searchBio != null) {
            results[1] = '1';
            atLeastOneHit = true;
        }

        return (atLeastOneHit) ? results : null;
    }
}
