<%@tag description="schedule form tag" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="day" required="true"%>
<%@attribute name="hs" required="true"%>
<%@attribute name="ms" required="true"%>
<%@attribute name="he" required="true"%>
<%@attribute name="me" required="true"%>
<%@attribute name="group" required="true"%>

<c:if test="${ms < 10}">
    <c:if test="${me < 10}">
        <div class="list-groups" style="display: flex;
    flex-direction: row;
    justify-content: space-between;
    font-size: medium;
    border-radius: 20px;" >
            <p>${day}</p> <p>${hs}:0${ms} - ${he}:0${me}</p> <p>${group}</p>
        </div>
    </c:if>
    <c:if test="${me >= 10}">
        <div class="list-groups" style="display: flex;
    flex-direction: row;
    justify-content: space-between;
    font-size: medium;
    border-radius: 20px;" >
            <p>${day}</p> <p>${hs}:0${ms} - ${he}:${me}</p> <p>${group}</p>
        </div>
    </c:if>
</c:if>
<c:if test="${ms >= 10}">
    <c:if test="${me < 10}">
        <div class="list-groups" style="display: flex;
    flex-direction: row;
    justify-content: space-between;
    font-size: medium;
    border-radius: 20px;" >
            <p>${day}</p> <p>${hs}:${ms} - ${he}:0${me}</p> <p>${group}</p>
        </div>
    </c:if>
    <c:if test="${me >= 10}">
        <div class="list-groups" style="display: flex;
    flex-direction: row;
    justify-content: space-between;
    font-size: medium;
    border-radius: 20px;" >
            <p>${day}</p> <p>${hs}:${ms} - ${he}:${me}</p> <p>${group}</p>
        </div>
    </c:if>
</c:if>
