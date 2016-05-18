<%--
  Created by IntelliJ IDEA.
  User: Junnie
  Date: 3/30/2016
  Time: 11:39 PM
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
    <link href="css/homePage.css" rel="stylesheet">

    <title>Comic-Sans</title>
</head>
<body>

    <%-- Navbar --%>
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href=""><img src="images/logo.png" height="40" /></a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="directory.jsp">Directory</a></li>
                    <li><a href="publish.jsp">Publish</a></li>
                    <li><a onclick="userToProfile('');" >My Profile</a></li>
                    <li><a id="logLink" href="#">Log In</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="10000">
        <%-- Indicators --%>
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img class="first-slide" src="images/banners/CoConutBanner.png" alt="First slide">
                <div class="container">
                    <div class="carousel-caption">
                        <h1>CoConut</h1>
                        <h3>John Smith</h3>
                        <p>Follow the adventures of CoConut, the most relatable fruit in the world.</p>
                        <p><a class="btn btn-lg btn-primary" role="button">Subscribe</a>
                            <%-- IMPORTANT: CHANGE THE FIRST PARAMETER IN passBySession TO THE ID OF A COMIC IN YOUR DATASTORE --%>
                            <%-- REPEAT THIS FOR THE OTHER TWO INSTANCES BELOW. THEY DON'T HAVE TO BE THE SAME NUMBER AS LONG --%>
                            <%-- AS THEY MATCH COMICS IN YOUR LOCAL DATASTORE --%>
                            <a class="btn btn-lg btn-primary" onclick="passBySession(4644337115725824, 1)" role="button">Read Now</a></p>
                    </div>
                </div>
            </div>
            <div class="item">
                <img class="second-slide" src="images/banners/DoenutBanner.png" alt="Second slide">
                <div class="container">
                    <div class="carousel-caption">
                        <h1>Doenut</h1>
                        <h3>Jane Doe</h3>
                        <p>Doenut is a deer. But besides that, she's also insane. What crazy adventures
                            will she have?</p>
                        <p><a class="btn btn-lg btn-primary" role="button">Subscribe</a>
                            <a class="btn btn-lg btn-primary" onclick="passBySession(4644337115725824, 2)" role="button">Read Now</a></p>
                    </div>
                </div>
            </div>
            <div class="item">
                <img class="third-slide" src="images/banners/DoofusBanner.png" alt="Third slide">
                <div class="container">
                    <div class="carousel-caption">
                        <h1>Doofus</h1>
                        <h3>Ian McKellen</h3>
                        <p>Doofus contemplates life in the universe and why he's shaped like a potato.</p>
                        <p><a class="btn btn-lg btn-primary" role="button">Subscribe</a>
                            <a class="btn btn-lg btn-primary" onclick="passBySession(4644337115725824, 3)" role="button">Read Now</a></p>
                    </div>
                </div>
            </div>
        </div>
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    <%-- End of Carousel --%>
    <div id="top-suggestions" class="section-sliders">
        <%--<div class="section-title">Continue Reading</div>--%>
        <h2>Top Suggestions</h2>
        <ul>
        </ul>
    </div>
    <div class="section-sliders">
        <h2>Favorites</h2>
        <ul>
            <li>
                <a href="summary.jsp"><img src="images/covers/CoConutCover.png" /></a>
            </li>
            <li>
                <img src="images/covers/DoenutCover.png" />
            </li>
            <li>
                <img src="images/covers/DoofusCover.png" />
            </li>
        </ul>
    </div>
    <%--<div id="ajaxDiv">--%>
        <%--<a href="#" onclick="fakeAjax()">Click here for AJAX!</a>--%>
    <%--</div>--%>

    <%--<div id="profileDiv">--%>
        <%--<a href="profile/profile.jsp">Click here for Profile!</a>--%>
    <%--</div>--%>
    <%--<div id="logDiv">--%>
        <%--<a id="logLink" href=""></a>--%>
    <%--</div>--%>


</body>
</html>

<%-- SCRIPTS --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="scripts/session.js"></script>
<script src="scripts/authentication.js"></script>
<script src="scripts/index.js"></script>
<script src="scripts/random.js"></script>
<script src="scripts/profile.js"></script>