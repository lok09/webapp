<html>
    <head>
        <title>Edit Lecture Comment</title>
    </head>

    <body>
        <c:url value="/" var="mainURL" />
        <a href="${mainURL}">MAIN PAGE</a> <br>
        <h1>Edit Lecture Comment</h1>
        <form:form method="POST" modelAttribute="lectureComment">
            <form:label path="content">Lecture Comment: </form:label><br>
            <form:textarea type="text" value="${lectureComment.content}" path="content"/><br>
            <input type="submit" value="Update">

        </form:form>

    </body>
</html>
