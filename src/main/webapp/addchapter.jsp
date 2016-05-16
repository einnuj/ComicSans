<%--
  Created by IntelliJ IDEA.
  User: maggie
  Date: 4/21/16
  Time: 4:19 PM
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


    <title>Add Chapter</title>
</head>
<body>
    <%-- Side navbar --%>
    <c:set var="mainNav" value="mainNav.html"/>
    <jsp:include page="${mainNav}"></jsp:include>

    <div class="info-container">
        <h1>Upload Chapter</h1>
        <div class="create-content">
            <ul>
                <li>
                    <form id="submitComicForm" name="submitForm">
                        <label for="chapterNumber" id="labelForChapter">Chapter Title:</label>
                        <input type="text" name="chapter" id="chapterNumber"/> <br>
                        <br><br>
                        Upload pages:
                        <input id ="fileSubmit" type="file" name="comicPage[]" multiple>
                        <br>
                        <a class="btn btn-lg btn-primary" role="button" href="preview.jsp">Preview</a>
                        <input class="btn btn-lg btn-primary" onclick="submitChapter()" type="button" value="Submit">
                    </form>
                </li>
            </ul>
        </div>
    </div>
</body>
</html>

<script src="scripts/submit.js"></script>