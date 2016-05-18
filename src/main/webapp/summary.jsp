<%--
  Created by IntelliJ IDEA.
  User: maggie
  Date: 4/7/16
  Time: 11:48 AM
  To change this template use File | Settings | File Templates.

  *** THE CLASS AUTHOR PRIV IS FOR EDIT CONTROLS ONLY VISIBLE TO THE AUTHOR ***

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%-- Meta tags --%>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <%-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags --%>
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="">

    <%-- Bootstrap CSS core  --%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <%-- Stylesheets --%>
    <link href="css/summary.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">


    <title>Summary</title>
</head>
<body>
    <%-- Side navbar --%>
    <c:set var="mainNav" value="mainNav.html"/>
    <jsp:include page="${mainNav}"></jsp:include>

    <div class="info-container">
        <div class="comic-info">
            <img id="cover-thumbnail" src="" width="200px" height="310px"/>
            <div class="comic-info-descr">
                <h1 id="title-header"></h1>
                <span class="AUTHOR_PRIV"><span class="glyphicon glyphicon-pencil" aria-hidden="true" onclick="editComicTitle()"></span></span>
                <div id="edit-title">
                    New Title: <input id="title-text" type="text" value="" />
                </div>

                <h5 id="author-header"></h5>

                <div class="comic-summary">
                    <p id="summary-paragraph">
                    </p>
                    <span class="AUTHOR_PRIV"><span class="glyphicon glyphicon-pencil" aria-hidden="true" onclick="editComicSummary()"></span></span>
                </div>
                <div id="edit-summary">
                    <textarea id="summary-text-area" value=""></textarea>
                </div>
                <br>
                <br>
                <br>
                <p id="fav-field">Favorites: </p>
                <p id="like-field">Likes :</p>
                <div class="social-buttons">
                    <a id="fav-btn" class="btn btn-lg btn-primary" role="button" onclick="socialButton('FAV')">Favorite <span class="glyphicon glyphicon-star" aria-hidden="true"></span></a>
                    <a id="lik-btn" class="btn btn-lg btn-primary" role="button" onclick="socialButton('LIK')">Like <span class="glyphicon glyphicon-heart" aria-hidden="true"></span></a>
                    <a class="btn btn-lg btn-primary" role="button" onclick="readIssue(0);">Read Now <span class="glyphicon glyphicon-book" aria-hidden="true"></span></a>
                </div>
            </div>
        </div>
        <div class="chapter-listing">
            <div class="row">
                <div class="col-md-6"><h3>Issues</h3></div>
            </div>
            <ul id="issue-list"></ul>
            <span class="AUTHOR_PRIV"><a class="btn btn-lg btn-primary" role="button" href="addchapter.jsp">Issue <span class="glyphicon glyphicon-plus" aria-hidden="true"></span></a></span>
        </div>
        <br><br>
        <%-- COMMENT THREAD STUFFS --%>
        <h3 id="comment-header">Leave a comment:</h3>
        <textarea id="comment-input"></textarea><br><br>
        <a id="comment-submit" class="btn btn-lg btn-primary" role="button" onclick="appendComment()">Submit &nbsp;&nbsp;<span class="glyphicon glyphicon-edit"></span></a>
        <ul id="comment-thread">
        </ul>
    </div>
</body>
</html>

<%-- SCRIPTS --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="scripts/summary.js"></script>
<script src="scripts/social.js"></script>