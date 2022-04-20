<html>
<head>
    <title>Upload Material</title>
</head>
<body>
<h3>Upload Material</h3>
<form:form method="POST" enctype="multipart/form-data" modelAttribute="material">
<b>Attachments</b><br />
<input type="file" name="attachments" multiple="multiple" /><br /><br />
<input type="submit" value="Submit"/>
</form:form>
</body>
</html>
