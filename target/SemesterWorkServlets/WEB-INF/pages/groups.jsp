<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<html>
<head>
    <title>My groups</title>
</head>
<body>
<t:navbar/>
<div style="text-align: center; padding-top: 20px"><h3>My groups</h3></div>
<center>
<div style="border-radius: 30px; padding-left: 30px; padding-top: 20px;
    font-size: 20px;
    width: 40%;">
    <c:forEach var = "group" items="${requestScope.get('groups')}" varStatus="counter">
        <t:group num="${counter.count}" name="${group.name}" count="${group.countOfMembers}"/>
    </c:forEach>
</div>
</center>

<div style="text-align: center; padding-top: 20px; padding-bottom: 20px;"><h3>Group's Settings</h3></div>

<div class="group-settings" style="column-count: 3;
    display: flex;
    column-width: 400px;
    padding-left: 320px;
    column-gap: 30px;">
    <form action="groups" method="POST" style="padding-left: 20px; padding-top: 20px; justify-content: center" >
            <b>Add new group:</b>
            <div class="mb-3" style="width: 170px;">
                <label for="inputName">Name of group:</label>
                <input class="form-control" type="text" id="inputName" name="addGroup">
            </div>
            <div class="mb-3" style="width: 150px;">
                <label for="inputCount">Count of members:</label>
                <input class="form-control" type="number" id="inputCount" name="addCount" min="1">
            </div>

        <div class="data-button">
            <input type="submit" value="Add" name="color-button" class="btn btn-dark"/>
        </div>
    </form>

    <form action="groups" method="POST" style="padding-left: 20px; padding-top: 20px; justify-content: center" >
        <b>Delete group:</b>
            <div class="mb-3" style="width: 170px;">
                <label for="inputDeleteName">Name of group:</label>
                <input class="form-control" type="text" id="inputDeleteName" name="deleteName">
            </div>

        <div class="data-button">
            <input type="submit" value="Delete" name="color-button" class="btn btn-dark"/>
        </div>
    </form>

    <form action="groups" method="POST" style="padding-left: 20px; padding-top: 20px; justify-content: center" >
        <b>Update group:</b>

        <div class="mb-3" style="width: 170px;">
            <label for="inputUpdateName">Name of group:</label>
            <input class="form-control" type="text" id="inputUpdateName" name="name">
        </div>
        <div class="mb-3" style="width: 170px;">
            <label for="inputNewCount">New count of members:</label>
            <input class="form-control" type="number" id="inputNewCount" name="newCount" min="1">
        </div>

        <div class="data-button">
            <input type="submit" value="Update" name="color-button" class="btn btn-dark"/>
        </div>
    </form>
</div>
</body>
</html>
