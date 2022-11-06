<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Schedule</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<t:navbar/>
<div style="text-align: center; padding-top: 20px"><h3>My schedule</h3></div>

<center>
<div style="width: 40%; padding-top: 10px; font-size: 20px;">
    <c:forEach var = "sch" items="${sessionScope.get('schedule')}">
        <t:schedule day="${sch.day}" group="${sch.group}" hs="${sch.hourStart}" ms="${sch.minuteStart}" he="${sch.hourEnd}" me="${sch.minuteEnd}"/>
    </c:forEach>
</div>
</center>
<div style="text-align: center; padding-top: 20px; padding-bottom: 20px;"><h3>Schedule's Settings</h3></div>

<c:if test="${requestScope.get('done') == 'false'}">
    <h5 class = 'wrong-data' style="color: red; text-align: center">${requestScope.get('failedDescription')}</h5>
</c:if>

<div class="schedule-settings" style="column-count: 2;
    display: flex;
    column-width: 500px;
    padding-left: 300px;
    column-gap: 60px;">
    <form action="schedule" method="POST" style="padding-left: 20px; padding-top: 20px; justify-content: center" >
        <b>Add new training:</b>
        <br>
            <label for="inputDay">Day of week:</label>
            <select class="form-select" name="day" aria-label="Day" required id="inputDay" style="width: 200px;">
                <option></option>
                <option>Monday</option>
                <option>Tuesday</option>
                <option>Wednesday</option>
                <option>Thursday</option>
                <option>Friday</option>
                <option>Saturday</option>
                <option>Sunday</option>
            </select>

        <div class="mb-3" style="width: 200px;">
            <label for="inputName">Name of group:</label>
            <input class="form-control" type="text" id="inputName" name="groupName" aria-describedby="helpBlock">
        </div>
        <div id="helpBlock" class="form-text">
            You can add training only for existing <a href="${pageContext.request.contextPath}/groups">groups</a>
        </div>
        <div class="mb-3" style="width: 200px;">
            <label for="inputHours">Start hour:</label>
            <input class="form-control" type="number" id="inputHours" name="hourS" min="6" max="22">
        </div>
        <div class="mb-3" style="width: 200px;">
            <label for="inputMinutes">Start minute:</label>
            <input class="form-control" type="number" id="inputMinutes" name="minuteS" min="0" max="59">
        </div>
        <div class="mb-3" style="width: 200px;">
            <label for="inputHours1">End hour:</label>
            <input class="form-control" type="number" id="inputHours1" name="hourE" min="6" max="22">
        </div>
        <div class="mb-3" style="width: 200px;">
            <label for="inputMinutes1">End minute:</label>
            <input class="form-control" type="number" id="inputMinutes1" name="minuteE" min="00" max="59">
        </div>

        <div class="data-button">
            <input type="submit" value="Add" name="color-button" class="btn btn-dark"/>
        </div>
    </form>

    <form action="schedule" method="POST" style="padding-left: 20px; padding-top: 20px; justify-content: center" >
        <b>Delete training:</b>
        <br>
        <label for="inputDDay">Day of week:</label>
        <select class="form-select" name="Dday" aria-label="Day" required id="inputDDay" style="width: 200px;">
            <option></option>
            <option>Monday</option>
            <option>Tuesday</option>
            <option>Wednesday</option>
            <option>Thursday</option>
            <option>Friday</option>
            <option>Saturday</option>
            <option>Sunday</option>
        </select>

        <div class="mb-3" style="width: 200px;">
            <label for="inputName">Name of group:</label>
            <input class="form-control" type="text" id="inputDName" name="groupDName" aria-describedby="helpDBlock">
        </div>
        <div id="helpDBlock" class="form-text">
            You can delete training only for existing <a href="${pageContext.request.contextPath}/groups">groups</a>
        </div>
        <br>
        <div class="data-button">
            <input type="submit" value="Delete" name="color-button" class="btn btn-dark"/>
        </div>
    </form>
</div>
</body>
</html>
