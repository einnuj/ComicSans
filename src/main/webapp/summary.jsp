<%--
  Created by IntelliJ IDEA.
  User: maggie
  Date: 4/7/16
  Time: 11:48 AM
  To change this template use File | Settings | File Templates.
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
    <c:set var="sideNav" value="sideNav.html"/>
    <jsp:include page="${sideNav}"></jsp:include>

    <div class="info-container">
        <div class="comic-info">
            <div class="edit-image">
                <img id="cover-thumbnail" src="images/covers/CoConutCover.png" />
                <input id="edit-cover-picker" type="file" accept="image/*;" onchange="readURL(this)" />
            </div>
            <div class="comic-info-descr">
                <h1 id="title-header">CoConut</h1>
                <span class="glyphicon glyphicon-pencil" aria-hidden="true" onclick="editComicTitle()"></span>
                <div id="edit-title">
                    New Title: <input id="title-text" type="text" value="" />
                </div>

                <h5 id="author-header">Author: John Smith</h5>

                <div class="comic-summary">
                    <p id="summary-paragraph">
                    </p>
                    <span class="glyphicon glyphicon-pencil" aria-hidden="true" onclick="editComicSummary()"></span>
                </div>
                <div id="edit-summary">
                    This is an example of a comic summary.
                    <textarea id="summary-text-area" value=""></textarea>
                </div>
                <br>
                <br>
                <br>
                <p>Rating:
                    <span class="glyphicon glyphicon-star" aria-hidden="true"></span>
                    <span class="glyphicon glyphicon-star" aria-hidden="true"></span>
                    <span class="glyphicon glyphicon-star" aria-hidden="true"></span>
                    <span class="glyphicon glyphicon-star" aria-hidden="true"></span></p>
                <p>Favorites: 15</p>
                <p>Last updated: <em>3 days ago</em></p>
                <div class="social-buttons">
                    <a class="btn btn-lg btn-primary" onclick="subscribe()" role="button">Subscribe <span class="glyphicon glyphicon-plus" aria-hidden="true"></span></a>
                    <a class="btn btn-lg btn-primary" onclick="favorite()" role="button">Favorite <span class="glyphicon glyphicon-star" aria-hidden="true"></span></a>
                    <a class="btn btn-lg btn-primary" onclick="like()" role="button">Like <span class="glyphicon glyphicon-heart" aria-hidden="true"></span></a>
                    <a class="btn btn-lg btn-primary" role="button" href="read.jsp">Read Now <span class="glyphicon glyphicon-book" aria-hidden="true"></span></a>
                </div>
            </div>
        </div>
        <div class="chapter-listing">
            <div class="row">
                <div class="col-md-6"><h3>Chapters</h3></div>
                <div class="col-md-6"><h3>Date</h3></div>
            </div>
            <div class="row">
                <div class="col-md-6"><a href="editchapter.jsp"><span class="glyphicon glyphicon-pencil"></span></a><a href="read.jsp"> Chapter 01</a></div>
                <div class="col-md-6">Today</div>
            </div>
            <div class="row">
                <div class="col-md-6"><a href="editchapter.jsp"><span class="glyphicon glyphicon-pencil"></span></a><a href=""> Chapter 02</a></div>
                <div class="col-md-6">Today</div>
            </div>
            <div class="row">
                <div class="col-md-6"><a href="editchapter.jsp"><span class="glyphicon glyphicon-pencil"></span></a><a href=""> Chapter 03</a></div>
                <div class="col-md-6">Today</div>
            </div>
            <a class="btn btn-lg btn-primary" role="button" href="addchapter.jsp">Chapter <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span></a>
        </div>
        <div class="user-comments">
            <p>Probably the funniest thing I've read in a while! Easily my favorite!</p>
            <em>saucetrocity</em>
            <p>Posted: October 29, 2015</p>
        </div>
    </div>
</body>
</html>

<%-- SCRIPTS --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="scripts/summary.js"></script>
<script src="scripts/social.js"></script>