<%--
  Created by IntelliJ IDEA.
  User: maggie
  Date: 4/21/16
  Time: 4:19 PM
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


    <title>Add Chapter</title>
</head>
<body>
    <%-- Side navbar --%>
    <c:set var="mainNav" value="mainNav.html"/>
    <jsp:include page="${mainNav}"></jsp:include>

    <div class="info-container">
        <h1>Upload Issue</h1>
        <div class="create-content">
            <ul>
                <li>

                    <form id="submitChapterForm" name="submitForm" action="<%= blobstoreService.createUploadUrl("/upload") %>" method="post" enctype="multipart/form-data">
                        <label for="issueTitle" id="labelForChapter">Issue Title:</label>
                        <input type="text" name="issueTitle" id="issueTitle"/> <br>
                        <br><br>
                        Choose image:
                        <input id ="fileSubmit" type="file" name="comicPages">
                        <br>
                        <input class="btn btn-lg btn-primary" onclick="submitIssue()" type="button" value="Submit">

                    </form>
                </li>
            </ul>
        </div>
    </div>
</body>
</html>

<script src="scripts/submit.js"></script>