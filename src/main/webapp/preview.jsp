<%--
  Created by IntelliJ IDEA.
  User: maggie
  Date: 4/15/16
  Time: 2:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="read.jsp">
    <jsp:param name="title" value="Preview"/>
</jsp:include>
<div class="preview-options">
    <a class="btn btn-lg btn-primary" role="button" href="publish.jsp">Exit Preview</a>
    <a class="btn btn-lg btn-primary" role="button">Submit</a>
</div>
