<html>
<head>
  <title>Add Poll</title>
</head>
<body>
<h1>Add Poll</h1>
<form:form method="POST" modelAttribute="poll">
  <form:label path="question">Poll Question: </form:label>
  <form:input type="text" path="question"/><br>
  <form:label path="optionA">Option A: </form:label>
  <form:input type="text" path="optionA"/>
  <form:label path="optionB">Option B: </form:label>
  <form:input type="text" path="optionB"/>
  <form:label path="optionC">Option C: </form:label>
  <form:input type="text" path="optionC"/>
  <form:label path="optionD">Option D: </form:label>
  <form:input type="text" path="optionD"/>
  <form:label path="answer">Answer: </form:label>
  <form:input type="text" path="answer"/>
  <input type="submit" value="Add">
</form:form>

</body>
</html>
