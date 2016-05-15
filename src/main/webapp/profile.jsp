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
            <img src="images/usericon.png" />
            <div class="comic-info-descr">
                <h1>Maggie</h1>
                <h4>Comics created: 3</h4>
                <h4>Comics read: 13</h4>
                <h4>Comments posted: 35</h4>
                <p>Bio:
                    <br>I have been reading comics since I was a child. I love comics with a passion.
            </div>
        </div>
        <h2>Creator of: </h2>
        <div class="comic-listing">
            <a href="summary.jsp"><img src="images/covers/CoConutCover.png" /></a>
            <h3>CoConut</h3>
        </div>
        <div class="comic-listing">
            <img src="images/covers/DoenutCover.png" />
            <h3>Doenut</h3>
        </div>
        <div class="comic-listing">
            <img src="images/covers/DoofusCover.png" />
            <h3>Doofus</h3>
        </div>
    </div>
</body>
</html>

<%-- SCRIPTS --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>