<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<html>
<head>
    <title>Change stats</title>
</head>
<body>
    <t:navbar/>
    <form action="stats" method="POST" style="padding-left: 20px; padding-top: 20px; justify-content: center" >

        <label for="selector1" class="form-label">Select new category</label>
        <select class="form-select" name="newCategory" aria-label="Category" id="selector1" style="width: 400px; justify-content: center">
            <option></option>
            <option>Second</option>
            <option>First</option>
            <option>Superior</option>
        </select>
        <br>

        <div class="mb-3" style="width: 400px; justify-content: center">
            <label for="InputExp" class="form-label">Select new work experience.</label>
            <input type="number" class="form-control" id="InputExp" name="exp" min="0"/>
        </div>
        <br>

        <label for="selector" class="form-label">Select new sport class</label>
        <select class="form-select" name="newClass" aria-label="Class" id="selector" style="width: 400px; justify-content: center">
            <option></option>
            <option>Candidate for master of Sports</option>
            <option>Master of Sports</option>
            <option>Master of Sports of international class</option>
        </select>
        <br>
        <div class="data-button">
            <input type="submit" value="Submit" name="color-button" class="btn btn-dark"/>
        </div>
    </form>
</body>
</html>
