<%@tag description="user form tag" pageEncoding="UTF-8"%>
<%@attribute name="name" required="true"%>
<%@attribute name="desсription" required="true"%>
<%@attribute name="rvalue" required="true"%>
<%@attribute name="cvalue" required="true"%>

<div class="list-users" style="border-radius: 20px; font-size: large;
    padding: 30px;
    background: #A9A9A9;
    display: flex;
    flex-direction: column;
    width: 550px;
    height: 90px;
    margin-left: auto;
    margin-right: auto;
    margin-top: 10px;" >
    <div style="font-size: 18px">
        <b class="ach-name">${name}</b>
    </div>
    <div class="field" style="display: flex;
    flex-direction: row;
    justify-content: space-between; font-size: 16px">
         <p>${desсription}</p> <p>${cvalue}/${rvalue}</p>
    </div>
</div>