<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Welcome to Coach Journal Website</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</head>
<body>
<t:navbar/>
<div class="m-3 m-lg-3" >
    <h5>
        This site provides:
    </h5>
    <ul>
        <li>
            Your profile with professional information
        </li>
        <li>
            Lists of your study groups
        </li>
        <li>
            You can get some achievements here!
        </li>
    </ul>
    <c:if test="${sessionScope.get('is_registered') == 'true' && (sessionScope.get('is_authorized') == null || sessionScope.get('is_authorized') == 'false')}">
    <h5 style="color: green">Please log in to use all functions of website.</h5>
    </c:if>
</div>

</body>
</html>
