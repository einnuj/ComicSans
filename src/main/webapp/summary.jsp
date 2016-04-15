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
    <link href="css/main.css" rel="stylesheet">

    <title>Summary</title>
</head>
<body>
    <%-- Side navbar --%>
    <c:set var="sideNav" value="sideNav.html"/>
    <jsp:include page="${sideNav}"></jsp:include>

    <div class="info-container">
        <div class="comic-info">
            <img src="images/covers/CoConutCover.png" />
            <span class="glyphicon glyphicon-pencil" aria-hidden="true" onclick="changeCoverImage()"></span>
            <div class="comic-info-descr">
                <h1 id="title-header">CoConut</h1>
                <button type="button" onclick="editComicTitle()"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button>
                <div id="edit-title">
                    <input id="title-text" type="text" value="" />
                </div>

                <h5>Author: John Smith</h5>
                <span class="glyphicon glyphicon-pencil" aria-hidden="true" onclick="editComicSummary()"></span>
                <h4>Follow the adventures of CoConut, the most relatable fruit in the world.</h4>
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
                    <a class="btn btn-lg btn-primary" role="button">Subscribe</a>
                    <a class="btn btn-lg btn-primary" role="button">Favorite</a>
                    <a class="btn btn-lg btn-primary" role="button">Like</a>
                </div>
            </div>
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