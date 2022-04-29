<html>
    <head>
        <title>Registry</title>
    </head>
    <body>
        <c:url value="/" var="mainURL" />
        <a href="${mainURL}">MAIN PAGE</a> <br>
        <h1>Registry</h1>
        <form:form method="POST" modelAttribute="user">
            <form:label path="username">Username: </form:label>
            <form:input type="text" path="username"/><br>
            <form:label path="password">password: </form:label>
            <form:input type="password" path="password"/><br>
            <form:label path="username">Full name: </form:label>
            <form:input type="text" path="fullName"/><br>
            <form:label path="phoneNumber">Phone Number: </form:label>
            <form:input type="text" path="phoneNumber"/><br>
            <form:label path="address">Address: </form:label>
            <form:input type="text" path="address"/><br>
            <input type="submit" value="Create">

        </form:form>

    </body>
</html>
