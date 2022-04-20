<html>
<head>
  <title>Add Lecture</title>
</head>
<body>
<h1>Add Lecture</h1>
<form:form method="POST" modelAttribute="lecture">
  <form:label path="lectureTitle">Lecture Title: </form:label>
  <form:input type="text" value="${lecture.lectureTitle}" path="lectureTitle"/><br>
  <input type="submit" value="Update">

</form:form>

</body>
</html>
