<%--
  Created by IntelliJ IDEA.
  User: lilsh
  Date: 4/14/2016
  Time: 10:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mock</title>
</head>
<body>
    <div id="jsonBlock">
        <div id="userJson">
            <a href="#" onclick="mockUser()">Click Here To Get USER</a>
        </div>
        <div id="comicJson">
            <a href="#" onclick="mockComic()">Click Here To Create COMIC</a>
        </div>
        <div id="editUserJson">
            <a href="#" onclick="mockEditUser()">Click Here To Edit USER!</a>
        </div>
        <div id="addChapter">
            <a href="#" onclick="mockCreateChapterAndPages()">Click Here To Generate CHAPTER/PAGES!</a>
        </div>
        <div id="getComicDiv">
            <a href="#" onclick="mockGetComic()">Click Here to GET Comic</a>
        </div>
        <div id="bookmarkDiv">
            <a href="#" onclick="addBookmark()">Click to Add Bookmark!</a>
            <a href="#" onclick="removeBookmark()">Click to Remove Bookmark!</a>
        </div>
        <div id="commentDiv">
            <a href="#" onclick="addComment()">Click to Add Comment!</a>
        </div>
        <div id="favoriteDiv">
            <a href="#" onclick="addFavorite()">Click to Add Favorite!</a>
            <a href="#" onclick="removeFavorite()">Click to Remove Favorite!</a>
        </div>
        <div id="likeDiv">
            <a href="#" onclick="addLike()">Click to Add Like!</a>
            <a href="#" onclick="removeLike()">Click to Remove Like!</a>
        </div>
        <div id="ratingDiv">
            <a href="#" onclick="addRating()">Click to Add Rating!</a>
        </div>
        <div id="checkLike">
            <a href="#" onclick="checkLike()">Click to check like!</a>
            <a href="#" onclick="checkFavorite()">Click to check fave!</a>
            <a href="#" onclick="numLikes()">get num likes!</a>
            <a href="#" onclick="numFavorites()">get num favess!</a>

        </div>
        <div id="currentUserObject">
            <p>Nothing's Here!</p>
        </div>
    </div>
</body>
</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="/scripts/mockingbird.js"></script>
<script src="/scripts/social.js"></script>
