<%--
  Created by IntelliJ IDEA.
  User: maggie
  Date: 4/5/16
  Time: 8:32 PM
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

    <title>Comic Directory</title>
</head>
<body>
    <%-- Side navbar --%>
    <c:set var="sideNav" value="sideNav.html"/>
    <jsp:include page="${sideNav}"></jsp:include>

    <div class="info-container">
        <h1>All Comics</h1>

        <%-- Filter Dropdown --%>
        <div class="dropdown">
            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                <h4>Filter By <span class="caret"></span></h4>

            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                <li><a href="">Action</a></li>
                <li><a href="">Comedy</a></li>
                <li><a href="">Drama</a></li>
                <li><a href="">Fantasy</a></li>
                <li><a href="">Gaming</a> </li>
                <li><a href="">Horror</a> </li>
                <li><a href="">Romance</a></li>
                <li><a href="">Sports</a> </li>
                <li><a href="">Thriller</a> </li>
            </ul>
        </div>

        <%-- Comic Listing --%>
        <c:forEach var="comic" items="${allComics.comicsAsList}">
            <div class="comic-listing">
                <a href="summary.jsp"><img src="images/covers/CoConutCover.png"></a>
                <h3><c:out value="${comic.name}"/></h3>
                <h5><c:out value="${comic.metadata.author}"/></h5>
            </div>
        </c:forEach>
        <div class="comic-listing">
            <a href="summary.jsp"><img src="images/covers/CoConutCover.png"></a>
            <h3>CoConut</h3>
            <h5>John Smith</h5>
        </div>
        <div class="comic-listing">
            <img src="images/covers/DoenutCover.png" />
            <h3>Doenut</h3>
            <h5>Jane Doe</h5>
        </div>
        <div class="comic-listing">
            <img src="images/covers/DoofusCover.png" />
            <h3>Doofus</h3>
            <h5>Ian McKellen</h5>
        </div>
    </div>

</body>
</html>

<%-- SCRIPTS --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>