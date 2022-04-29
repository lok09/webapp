<html>
    <head>
        <title>Edit Lecture Title</title>
    </head>
    <body>
        <c:url value="/" var="mainURL" />
        <a href="${mainURL}">MAIN PAGE</a> <br>
        <h1>Edit Lecture Title</h1>
        <form:form method="POST" modelAttribute="lecture">
            <form:label path="lectureTitle">Lecture Title: </form:label>
            <form:input type="text" value="${lecture.lectureTitle}" path="lectureTitle"/><br>
            <input type="submit" value="Update">
        </form:form>

    </body>
</html>
