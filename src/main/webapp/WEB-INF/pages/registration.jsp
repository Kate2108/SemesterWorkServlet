<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body class="reg_body">
    <form action="registration" method="POST" >
        <div class="m-5 m-lg-5">

            <div class="mb-3">
                <label for="InputUsername" class="form-label">Username</label>
                <input type="text" class="form-control" id="InputUsername" name="username" required minlength="2" maxlength="40"/>
                <p><span id="typeChars"></span></p>
                <p><span id="leftChars"></span></p>
            </div>

            <div class="mb-3">
                <label for="InputEmail" class="form-label">Email address</label>
                <input type="email" class="form-control" id="InputEmail" name="email" required minlength="8" maxlength="35" placeholder="example@gmail.com" pattern="(\w+[\\.-]?\w+)+@(\w+[\\.-]?\w+)+[\\.]?[a-z]{2,4}"/>
            </div>

            <label for="inputPassword" class="form-label">Password</label>
            <input type="password" id="inputPassword" class="form-control" aria-describedby="passwordHelpBlock" name="password" minlength="8" maxlength="30" required>
            <div id="passwordHelpBlock" class="form-text">
                Your password must consist of 8-30 characters, contain letters and numbers, and must not contain spaces, special characters or emojis.
            </div>
            <br>
            <div class="form-check">
                <input class="form-check-input" type="radio" value="male" id="male" name="gender">
                <label class="form-check-label" for="male">
                    Male
                </label>
            </div>

            <div class="form-check">
                <input class="form-check-input" type="radio" value="female" id="female" name="gender">
                <label class="form-check-label" for="female">
                    Female
                </label>
            </div>
            <br>
            <label for="selector" class="form-label">Country</label>
            <select class="form-select" name="country" aria-label="Country" required id="selector">
                <option></option>
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
            <label for="selector1" class="form-label">Type of sport</label>
            <select class="form-select" name="sport" aria-label="Sport" required id="selector1">
                <option></option>
                <option>Badminton</option>
                <option>Basketball</option>
                <option>Football</option>
                <option>Hockey</option>
                <option>Swimming</option>
                <option>Tennis</option>
                <option>Volleyball</option>
                <option>Another</option>
            </select>
            <br>
            <div class="data-button">
                <input type="submit" value="Submit" name="color-button" class="btn btn-dark"/>
            </div>
            <br>
        <c:if test="${requestScope.get('is_registered') == 'false'}">
            <h4 class = 'wrong-data' style="color: red">${requestScope.get('failedDescription')}</h4>
        </c:if>
        </div>
    </form>

</body>
</html>
