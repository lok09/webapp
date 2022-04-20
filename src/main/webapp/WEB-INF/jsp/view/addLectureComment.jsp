<html>
<head>
  <title>Add Comment</title>
</head>
<body>
<h1>Add Lecture</h1>
<form:form method="POST" modelAttribute="lectureComment">
  <form:label path="content">Comment Content: </form:label>
  <form:textarea path="content"/><br>
  <input type="submit" name="add" value="Add">

</form:form>

</body>
</html>
