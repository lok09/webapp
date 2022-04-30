<html>
    <head>
        <title>Hello Spring MVC</title>
        <style>
            a {
                display:inline-block;
                padding:8px;
            }
        </style>
    </head>
    <body>
        <h1>Course Name: ${course}</h1>
        <c:url value="/logout" var="logoutURL" />
        <c:url value="/login" var="loginURL" />
        <c:url value="/lecturer/createAccount" var="createAccountURL" />
        <c:url value="/lecture/edit/addLecture" var="addLectureURL" />
        <c:url value="/lecture/view/" var="viewLectureURL" />
        <c:url value="/poll/edit/addPoll" var="addPollURL"/>
        <c:url value="/poll/view/" var="viewPollURL"/>
        <c:url value="/registry" var="registryURL" />
        <c:url value="/user/" var="usersURL" />


        <security:authorize access="isAuthenticated()">
            <a href="${logoutURL}">Logout</a> <br>
        </security:authorize>
        <security:authorize access="hasAnyRole('LECTURER')">
            <a href="${usersURL}">View All Users</a>
        </security:authorize>
        <security:authorize access="hasAnyRole('LECTURER')">
            <a href="${addLectureURL}">Add Lecture</a>
        </security:authorize>
        <security:authorize access="hasAnyRole('LECTURER')">
            &nbsp;<a href="${addPollURL}">Add Poll</a>
        </security:authorize>
        <security:authorize access="isAnonymous()">
            <a href="${loginURL}">Login</a><a href="${registryURL}">Registry</a>
        </security:authorize>
        <security:authorize access="hasRole('LECTURER')">
            <br><br><a href="${createAccountURL}">CreateAccount</a>
        </security:authorize>
        <h2>Lectures</h2>
        <c:choose>
            <c:when test="${empty lectures}">
                <p>There is no lectures in this course</p>
            </c:when>
            <c:otherwise>
                <ul>
                    <c:forEach var="lecture" items="${lectures}">
                        <li><a href="${viewLectureURL}${lecture.lectureID}">${lecture.lectureTitle}</a>&nbsp</li>
                        </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
        <h2>Polls</h2>
        <c:choose>
            <c:when test="${empty polls}">
                <p>There is no poll in this course</p>
            </c:when>
            <c:otherwise>
                <ul>
                    <c:forEach var="poll" items="${polls}">
                        <li><a href="${viewPollURL}${poll.pollID}">${poll.question}</a> </li>
                        </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>

    </body>
</html>
