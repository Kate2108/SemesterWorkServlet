<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</head>
<body class="reg_body">
<form action="authorization" method="POST" >
    <div class="position-absolute top-50 start-50 translate-middle">

        <div class="w-100 p-3">
            <label for="InputEmail" class="form-label">Email</label>
            <input type="email" class="form-control" id="InputEmail" name="email" required minlength="8" maxlength="35" placeholder="example@gmail.com" pattern="(\w+[\\.-]?\w+)+@(\w+[\\.-]?\w+)+[\\.]?[a-z]{2,4}"/>
        </div>

        <div class="w-100 p-3">
        <label for="inputPassword" class="form-label">Password</label>
        <input type="password" id="inputPassword" class="form-control" name="password" minlength="8" maxlength="30" required>
        </div>
        <div class="data-button" style="text-align: center">
            <input type="submit" value="Submit" name="color-button" class="btn btn-dark"/>
        </div>

        <br>
        <c:if test="${requestScope.get('is_authorized') == 'false'}">
            <h4 class = 'wrong-data' style="color: red">${requestScope.get('failedDescription')}</h4>
        </c:if>
    </div>
</form>

</body>
</html>
