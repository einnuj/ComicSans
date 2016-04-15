<%--
  Created by IntelliJ IDEA.
  User: maggie
  Date: 4/7/16
  Time: 3:07 PM
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

    <title>Publish</title>
</head>
<body>
    <%-- Side navbar --%>
    <c:set var="sideNav" value="sideNav.html"/>
    <jsp:include page="${sideNav}"></jsp:include>

    <div class="info-container">
        <h1>Upload</h1>
        <div class="create-content">
            <ul>

                <li>
                    <form id="submitComicForm" name="submitForm">
                        <label for="chapterNumber" id="labelForChapter">Chapter:</label>
                        <input type="text" name="chapter" id="chapterNumber"/> <br>
                        <label for="pageNumber" id="labelForPage">Page:</label>
                        <input type="text" name="page" id="pageNumber"/> <br>
                        <label for="genreName" id="labelForGenre">Genre:</label>
                        <select id="genreName">
                            <option>Action</option>
                            <option>Comedy</option>
                            <option>Drama</option>
                            <option>Fantasy</option>
                            <option>Gaming</option>
                            <option>Horror</option>
                            <option>Romance</option>
                            <option>Sports</option>
                            <option>Thriller</option>
                        </select>
                        <br>
                        <label for="titleid">Title:</label>
                        <br><br>
                        <input type="text" name="title" id="titleid"/> <br>
                        <input id ="fileSubmit" type="file" name="comicPage" accept="">
                        <br>
                        <a class="btn btn-lg btn-primary" role="button" href="preview.jsp">Preview</a>
                        <input class="btn btn-lg btn-primary" onclick="submitComicFunction()" type="button" value="Submit">
                    </form>
                </li>
            </ul>
        </div>
        <h1>Create</h1>
        <div class="create-content">
            <img src="images/APIplaceholder.png" />
            <form action="">
                <input class="btn btn-primary" type="submit" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
            </form>
        </div>
    </div>
</body>
</html>

<%-- SCRIPTS --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="scripts/submit.js"></script>