<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>My profile</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="<c:url value="/js/script.js"/>"></script>
<%--        <script >--%>
<%--        document.addEventListener('DOMContentLoaded', function() {--%>

<%--            button = document.getElementById('logOut');--%>

<%--            function confirmLogOut() {--%>
<%--                if (confirm('Are you sure you want to log out?')){--%>
<%--                    window.location.replace('http://localhost:8080/my-website/logout');--%>
<%--                }--%>
<%--                else {--%>
<%--                    window.location.replace('http://localhost:8080/my-website/profile');--%>
<%--                }--%>
<%--            }--%>
<%--            if(button) {--%>
<%--                button.addEventListener('click', confirmLogOut);--%>
<%--            }--%>
<%--        });</script>--%>
</head>
<t:navbar/>
<div class="container" style="display: flex;
    flex-direction:row;
    justify-content: space-between;
   ">

    <div class="data-form" style=" border-radius: 20px; font-size: large;
    padding: 40px;
    background: #A9A9A9;
    display: flex;
    flex-direction: column;
    width: 500px;
    margin-left: auto;
    margin-right: auto;
    margin-top: 30px;">
        <h3 style="text-align: center">My profile</h3>
        <br>
        <div class="field" style="display: flex;
    flex-direction: row;
    justify-content: space-between;">
            Username: <p>${sessionScope.get("username")}</p>
        </div>
        <div class="field" style="display: flex;
    flex-direction: row;
    justify-content: space-between;">
            Email: <p>${requestScope.get("email")}</p>
        </div>

        <div class="field" style="display: flex;
    flex-direction: row;
    justify-content: space-between;">
            Country: <p>${requestScope.get("country")}</p>
        </div>

        <div class="field" style="display: flex;
    flex-direction: row;
    justify-content: space-between;">
            Gender: <p>${requestScope.get("gender")}</p>
        </div>

        <div class="field" style="display: flex;
    flex-direction: row;
    justify-content: space-between;">
            Count of groups: <p> ${requestScope.get("countOfGroups")}</p>
        </div>
        <br>
        <a><button type="submit" id="logOut" class="btn btn-dark" >Log out</button></a>
    </div>

    <div class="personal-data" style="border-radius: 20px; font-size: large;
    padding: 40px;
    background: #A9A9A9;
    display: flex;
    flex-direction: column;
    width: 500px;
    margin-left: auto;
    margin-right: auto;
    margin-top: 30px;">
        <h3 style="text-align: center">My stats</h3>
        <br>
        <div class="field" style="display: flex;
    flex-direction: row;
    justify-content: space-between;">
            Category: <p>${requestScope.get("category")}</p>
        </div>

        <div class="field" style="display: flex;
    flex-direction: row;
    justify-content: space-between;">
            Work Experience: <p>${requestScope.get("exp")}</p>
        </div>

        <div class="field" style="display: flex;
    flex-direction: row;
    justify-content: space-between;">
            Class: <p>${requestScope.get("class")}</p>
        </div>

        <div class="data-button">
            <a href="${pageContext.request.contextPath}/stats" class="btn btn-dark">Change stats</a>
        </div>
    </div>
</div>

</body>
</html>
