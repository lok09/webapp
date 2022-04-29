<html>
    <head>
        <title>Add Lecture</title>
    </head>
    <body>
        <c:url value="/" var="mainURL" />
        <a href="${mainURL}">MAIN PAGE</a> <br>
        <h1>Add Lecture</h1>
        <form:form method="POST" modelAttribute="lecture">
            <form:label path="lectureTitle">Lecture Title: </form:label>
            <form:input type="text" path="lectureTitle"/><br>
            <input type="submit" name="add" value="Add">

        </form:form>

    </body>
</html>
