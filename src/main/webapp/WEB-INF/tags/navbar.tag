<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="nav block form tag" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<c:choose>
    <c:when test="${sessionScope.get('is_authorized') == 'true'}">
        <nav class="navbar navbar-expand navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/"><h4>Coach Journal</h4></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/profile">Profile</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/groups">Groups</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/schedule">Schedule</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/my_achievements">Achievements</a>
                        </li>
<%--                        <li class="nav-item">--%>
<%--                            <a class="nav-link active" href="${pageContext.request.contextPath}/logout">Log out</a>--%>
<%--                        </li>--%>
                    </ul>
                </div>
            </div>
        </nav>
    </c:when>
    <c:otherwise>
        <nav class="navbar navbar-expand navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/"><h4>Coach Journal</h4></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav1">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/registration">Registration</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="${pageContext.request.contextPath}/authorization">Log in</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </c:otherwise>
</c:choose>