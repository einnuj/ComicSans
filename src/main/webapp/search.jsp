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
    <link href="css/tooltips.css" rel="stylesheet">
    <link href="css/homePage.css" rel="stylesheet">

    <title>Search Results</title>
</head>
<body>
    <%-- Side navbar --%>
    <c:set var="dirNav" value="dirNav.html"/>
    <jsp:include page="${dirNav}"></jsp:include>

    <div class="info-container">
        <h1>Search Results</h1>

        <div id="search-comics" class="section-sliders">
            <h2>Comics</h2>
            <ul>
            </ul>
        </div>

        <div id="search-comments" class="section-sliders">
            <h2>Comments In Comics</h2>
            <ul>
            </ul>
        </div>

        <div id="search-users" class="section-sliders">
            <h2>Users</h2>
            <ul>
            </ul>
        </div>
    </div>

</body>
</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="scripts/session.js"></script>
<script src="scripts/index.js"></script>
