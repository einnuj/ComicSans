<%--
  Created by IntelliJ IDEA.
  User: lilsh
  Date: 4/14/2016
  Time: 10:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mock</title>
</head>
<body>
    <div id="jsonBlock">
        <div id="userJson">
            <a href="#" onclick="mockUser()">Click Here To Get USER</a>
        </div>
        <div id="comicJson">
            <a href="#" onclick="mockComic()">Click Here To Get COMIC</a>
        </div>
        <div id="editUserJson">
            <a href="#" onclick="mockEditUser()">Click Here To Edit USER!</a>
        </div>
    </div>
</body>
</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="/scripts/mockingbird.js"></script>