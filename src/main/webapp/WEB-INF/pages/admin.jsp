<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<html>
<head>
    <title>Admin</title>
</head>
<body>
<form action="admin" method="POST" style="padding-left: 20px; padding-top: 20px;" >
    <div class="mb-3" style="width: 400px; justify-content: center">
        <label for="InputId" class="form-label">Delete user. Write down user's id.</label>
        <input type="number" class="form-control" id="InputId" name="deleteId" />
    </div>
    <br>
    <div class="mb-3" style="width: 400px; justify-content: center">
        <label for="InputCountry" class="form-label">Change country. Enter user's id and new country</label>
        <input type="number" class="form-control" id="InputCountry" name="countryId"/>
    </div>
    <select class="form-select" name="newCountry" aria-label="Country" id="selector" style="width: 400px; justify-content: center">
        <option>Armenia</option>
        <option>Belarus</option>
        <option>China</option>
        <option>France</option>
        <option>Kazakhstan</option>
        <option>Germany</option>
        <option>Russia</option>
        <option>Spain</option>
    </select>
    <br>
    <div class="data-button">
        <input type="submit" value="Submit" name="color-button" class="btn btn-dark"/>
    </div>
    <br>
</form>
<div style="border-radius: 30px; padding-left: 30px;
    font-size: 20px;
    width: 40%;">
    <c:forEach var = "user" items="${requestScope.get('users')}">
        <t:user id="${user.id}" email="${user.email}" country="${user.country}"/>
    </c:forEach>
</div>
</body>
</html>
