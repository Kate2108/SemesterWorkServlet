<%@tag description="user form tag" pageEncoding="UTF-8"%>
<%@attribute name="id" required="true"%>
<%@attribute name="email" required="true"%>
<%@attribute name="country" required="true"%>

<div class="list-users" style="display: flex;
    flex-direction: row;
    justify-content: space-between;
    font-size: medium;
    border-radius: 20px;" >
        <p>${id}</p> <p>${email}</p> <p>${country}</p>
</div>