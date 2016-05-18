<%--
  Created by IntelliJ IDEA.
  User: maggie
  Date: 4/7/16
  Time: 3:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>

<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>
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
    <link href="css/publish.css" rel="stylesheet">

    <title>Publish</title>
</head>
<body>
    <%-- Side navbar --%>
    <c:set var="mainNav" value="mainNav.html"/>
    <jsp:include page="${mainNav}"></jsp:include>

    <div class="info-container">
        <h1>Upload New Series</h1>
        <div class="create-content">
            <ul>
                <li>
                    <form id="submitComicForm" name="submitForm" action="<%= blobstoreService.createUploadUrl("/upload") %>" method="post" enctype="multipart/form-data">
                        <label for="titleid">Title:</label>
                        <br>
                        <input type="text" name="title" id="titleid"/>
                        <br><br>
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
                        </select><br><br>
                        <label for="comicSummary">Summary:</label>
                        <br>
                        <textarea type="input" name="summary" id="comicSummary"></textarea>
                        <br><br>
                        <h4>Upload a cover image:</h4>
                        <input id ="fileSubmit" type="file" name="comicCover">
                        <h5>Recommended Image Size: 200x310px</h5>
                        <br>
                        <input class="btn btn-lg btn-primary" onclick="submitComic()" type="button" value="Submit">

                    </form>
                </li>
            </ul>
        </div>
        <h1>Draw</h1>
        <div class="create-content">
            <p>
                <br>
            Comic Sans allows you to draw and submit your artwork all in one place! Prepare your drawing
            tablet and hands to create something awesome! <br><br>
            <a class="btn btn-lg btn-primary" role="button" href="whiteboard/index.jsp">Let's Draw!</a>
            </p>
        </div>
    </div>
</body>
</html>

<%-- SCRIPTS --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="scripts/submit.js"></script>