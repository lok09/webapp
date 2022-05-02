<html>
    <head>
        <title>Add Poll Comment</title>
    </head>
    <body>
        <c:url value="/" var="mainURL" />
        <a href="${mainURL}">MAIN PAGE</a> <br>
        <h1>Add Poll Comment</h1>
        <form:form method="POST" modelAttribute="pollComment">
            <form:label path="content">Comment Content: </form:label>
            <form:textarea path="content"/><br>
            <input type="submit" name="add" value="Add">
        </form:form>

    </body>
</html>