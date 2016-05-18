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


    <title>Profile</title>
</head>
<body>
    <%-- Side navbar --%>
    <c:set var="mainNav" value="mainNav.html"/>
    <jsp:include page="${mainNav}"></jsp:include>

    <div class="info-container">
        <div class="comic-info">
            <img src="images/defaultLogo.png" />
            <div class="comic-info-descr">
                <h1 id="name-header"></h1>
                <h4 id="created-header">Comics created: </h4>
                <h4 id="comments-header">Comments posted: </h4>
            </div>
        </div>

        <div class="section-sliders" id="user-created">
            <h3>Creator Of</h3>
            <ul>
            </ul>
        </div>
        <div class="section-sliders" id="user-favorites">
            <h3>Favorites</h3>
            <ul>
            </ul>
        </div>
        <div class="section-sliders" id="user-likes">
            <h3>Likes</h3>
            <ul>
            </ul>
        </div>
    </div>
</body>
</html>

<%-- SCRIPTS --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="scripts/profile.js"></script>

