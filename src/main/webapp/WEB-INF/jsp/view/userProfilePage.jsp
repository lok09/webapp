
<html>
    <head>
        <title>Users Profile Page</title>
    </head>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
    <body>
        <c:url value="/logout" var="logoutURL" />
        <a href="${logoutURL}">Logout</a> <br>
        <c:url value="/" var="mainURL" />
        <a href="${mainURL}">MAIN PAGE</a> <br>
        <c:choose>
            <c:when test="${empty user}">
                <p>no such user exists</p>
            </c:when>
            <c:otherwise>
                <h3>Information of this user</h3>                     
                <table>
                    <tr>
                        <th>username</th>
                        <th>role</th>
                        <th>full name</th>
                        <th>phone number</th>
                        <th>address</th>
                    </tr>
                    <tr>
                        <td>${user.username}</td>
                        <td>${user.role}</td>
                        <td>${user.fullName}</td>
                        <td>${user.phoneNumber}</td>
                        <td>${user.address}</td>
                    </tr>
                </table>       

                <h3>List of poll results</h3>     
                <c:if test="${empty user.pollResults}">
                    <p>No poll results</p>
                </c:if>

                <c:if test="${not empty user.pollResults}">
                    <ul>
                        <c:forEach var="pollresult" items="${user.pollResults}">
                            <li>Question: ${pollresult.poll.question}<br>
                                Answer: ${pollresult.option}<br>
                                Answer at ${pollresult.date}</li>
                            </c:forEach>
                    </ul>
                </c:if>

                <h3>List of lectureComments</h3>   
                <c:if test="${empty user.lectureComments}">
                    <p>No lecture comments</p>
                </c:if>

                <c:if test="${not empty user.lectureComments}">
                    <ul>
                        <c:forEach var="lectureComment" items="${user.lectureComments}">
                            <li>Title: ${lectureComment.lecture.lectureTitle}<br>
                                ${lectureComment.content}<br>
                                comment at ${lectureComment.date}</li>
                            </c:forEach>
                    </ul>
                </c:if>

                <h3>List of lectureComments</h3>   
                <c:if test="${empty user.pollComments}">
                    <p>No poll comments</p>
                </c:if>

                <c:if test="${not empty user.pollComments}">
                    <ul>
                        <c:forEach var="pollComment" items="${user.pollComments}">
                            <li>Question: ${pollComment.poll.question}<br>
                                ${pollComment.content}<br>
                                comment at ${pollComment.date}</li>
                            </c:forEach>
                    </ul>
                </c:if>

            </c:otherwise>                       

        </c:choose>
    </body>
</html>
