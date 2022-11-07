<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Welcome to Coach Journal Website</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<t:navbar/>
<div class="m-3 m-lg-3" >
    <h1 style="text-align: center;">Welcome to Coach Journal Website!</h1>
    <br>
    <h4 style="padding-left: 10px"> This site provides:</h4>
    <br>
    <ul style="font-size: 18px">
        <li>
            Your profile with professional information
        </li>
        <li>
            Lists of your study groups (add, delete and update functions available)
        </li>
        <li>
            Your personal schedule of trainings with your groups
        </li>
        <li>
            You also can get some achievements here!
        </li>
    </ul>
    <br>
    <c:if test="${sessionScope.get('is_registered') == 'true' && (sessionScope.get('is_authorized') == null || sessionScope.get('is_authorized') == 'false')}">
    <h5 style="color: green; text-align: center">Please log in to use all functions of website.</h5>
    </c:if>
</div>

</body>
</html>
