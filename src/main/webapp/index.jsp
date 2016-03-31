<%--
  Created by IntelliJ IDEA.
  User: Junnie
  Date: 3/30/2016
  Time: 11:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%-- FAVICON --%>
    <%-- STYLESHEETS --%>

    <title>Comic-Sans</title>
</head>
<body>

    <div id="authDiv">
        <a href="#" onclick="authenticate()">Click here for AuthServlet!</a>
    </div>

    <div id="profileDiv">
        <a href="profile/profile.jsp">Click here for Profile!</a>
    </div>

    <div id="logDiv">
        <a id="logLink" href=""></a>
    </div>


</body>
</html>

<%-- SCRIPTS --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="scripts/authentication.js"></script>
