<html>
<head>
    <title>Edit Poll Comment</title>
</head>
<body>
<h1>Edit Poll Comment</h1>
<form:form method="POST" modelAttribute="pollComment">
    <form:label path="content">Comment Content: </form:label>
    <form:textarea path="content" value="${pollComment.content}"/><br>
    <input type="submit" name="add" value="Add">

</form:form>

</body>
</html>
