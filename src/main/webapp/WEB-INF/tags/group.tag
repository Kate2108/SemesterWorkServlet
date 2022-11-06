<%@tag description="group form tag" pageEncoding="UTF-8"%>
<%@attribute name="num" required="true"%>
<%@attribute name="name" required="true"%>
<%@attribute name="count" required="true"%>

<div class="list-groups" style="display: flex;
    flex-direction: row;
    justify-content: space-between;
    font-size: medium;
    border-radius: 20px;" >
    <p>${num}</p> <p>${name}</p> <p>${count}</p>
</div>