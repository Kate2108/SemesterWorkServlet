<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<html>
<head>
    <title>My Achievements</title>
</head>
<body>
<t:navbar/>
<div style="text-align: center; padding-top: 20px"><h3>My Achievements</h3></div>
<center>
    <div>
        <c:forEach var = "achievement" items="${requestScope.get('achievements')}">
            <t:achievement name="${achievement.name}" desсription="${achievement.description}" cvalue="${achievement.currentValue}" rvalue="${achievement.requiredValue}"/>
        </c:forEach>
    </div>
</center>
</body>
</html>
