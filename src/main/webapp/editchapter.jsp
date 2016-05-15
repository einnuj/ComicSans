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


    <title>Edit Chapter</title>
</head>
<body>
<%-- Side navbar --%>
<c:set var="mainNav" value="mainNav.html"/>
<jsp:include page="${mainNav}"></jsp:include>

<div class="info-container">
    <div class="comic-info">
        <div class="edit-image">
            <img id="cover-thumbnail" src="images/covers/CoConutCover.png" />
        </div>
        <div class="chapter-info-descr">
            <h1>Chapter 01</h1>
            <button class="btn-primary">Add Pages<span class="glyphicon glyphicon-plus-sign" aria-hidden="true" onclick=""></span></button>
            <button class="btn-primary">Delete Chapter<span class="glyphicon glyphicon-trash" aria-hidden="true" onclick=""></span></button>
        </div>
    </div>
    <%-- Comic Listing --%>
    <div class="page-listing">
        <div class="page-block">
            <h3>Page 01</h3>
            <img src="images/mockpages/1.png" width="25%" height="25%"/><br>
            <button class="btn-primary">Delete <span class="glyphicon glyphicon-trash" aria-hidden="true" onclick=""></span></button>
        </div>
        <div class="page-block">
            <h3>Page 02</h3>
            <img src="images/mockpages/2.png" width="25%" height="25%"/><br>
            <button class="btn-primary">Delete <span class="glyphicon glyphicon-trash" aria-hidden="true" onclick=""></span></button>
        </div>
    </div>
</div>
</body>
</html>

<%-- SCRIPTS --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="scripts/summary.js"></script>