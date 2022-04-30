
<html>
<head>
    <title>Users detail</title>
</head>
<body>
    <c:url value="/user/edit/createAccount" var="addUserURL" />
    <c:url value="/user/edit/editAccount/" var="editUserURL" />
     <c:url value="/user/edit/deleteAccount/" var="deleteUserURL" />
     <c:url value="/user/view/" var="viewUserURL"/>
    
     <security:authorize access="hasAnyRole('LECTURER')">
         <a href="${addUserURL}">addUser</a>           
      </security:authorize>
    
    <c:choose>
        <c:when test="${empty users}">
            <p>no users in this course</p>
        </c:when>
        <c:otherwise>
              <h3>List of Users</h3>              
            <ul>
                <c:forEach var="user" items="${users}">
                    <li><p><a href="${viewUserURL}${user.username}">${user.fullName}</a>&nbsp;${user.role}&nbsp;${user.phoneNumber}&nbsp;${user.address}</p>         
                         <security:authorize access="hasAnyRole('LECTURER')">
                            <a href="${editUserURL}${user.username}">editUser</a>
                            <a href="${deleteUserURL}${user.username}">deleteUser</a>
                         </security:authorize>
                </c:forEach>
            </ul>
        </c:otherwise>
    </c:choose>
</body>
</html>
