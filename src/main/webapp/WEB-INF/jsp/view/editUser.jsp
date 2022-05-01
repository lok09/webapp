<html>
<head>
    <title>Edit User</title>
</head>
<body>
<h1>Edit User</h1>
<form:form method="POST" modelAttribute="user">
    <form:label path="username">Username: </form:label>
    <form:label path="username">${user.username}</form:label><br>
    <form:label path="password">password: </form:label>
    <form:input type="password" path="password" value=""/><br>
    <form:label path="role">role: </form:label>
    <form:radiobutton path="role" label="Lecture" value="ROLE_LECTURER"/><br>
    <form:radiobutton path="role" label="Student" value="ROLE_STUDENT"/><br>
    <form:label path="username">Full name: </form:label>
    <form:input type="text" path="fullName" value="${user.fullName}"/><br>
    <form:label path="phoneNumber">Phone Number: </form:label>
    <form:input type="text" path="phoneNumber" value="${user.phoneNumber}"/><br>
    <form:label path="address">Address: </form:label>
    <form:input type="text" path="address" value="${user.address}"/><br>
    <input type="submit" value="edit">

</form:form>

</body>
</html>
