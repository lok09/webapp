<html>
    <head>
        <title>Poll detail</title>
    </head>
    <body>
        <c:url value="/poll/submit/${poll.pollID}/" var="submitURL"/>
        <c:url value="/poll/addComment/" var="addCommentURL"/>
        <c:url value="/poll/edit/editPoll/" var="editPollURL"/>
        <c:url value="/poll/edit/editPollComment/" var="editPollCommentURL"/>
        
        <c:url value="/logout" var="logoutURL" />
        <a href="${logoutURL}">Logout</a> <br>
        <c:url value="/" var="mainURL" />
        <a href="${mainURL}">MAIN PAGE</a> <br>

        <c:choose>
            <c:when test="${empty poll}">
                This poll does not exist
            </c:when>
            <c:otherwise>
                <p>Question: ${poll.question}</p><br>
               
                <security:authorize access="hasAnyRole('LECTURER')">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${editPollURL}${poll.pollID}">Edit Poll</a>
                </security:authorize>
                <p>Answer: ${poll.answer}</p><br>
                <p><a href="${submitURL+="A"}">A: ${poll.optionA}</a>${optionA}</p>
                <p><a href="${submitURL+="B"}">B: ${poll.optionB}</a>${optionB}</p>
                <p><a href="${submitURL+="C"}">C: ${poll.optionC}</a>${optionC}</p>
                <p><a href="${submitURL+="D"}">D: ${poll.optionD}</a>${optionD}</p>

                <c:choose>
                    <c:when test="${not empty pollResult}">
                        <br>
                        <p>Your latest submission is ${pollResult.option} at ${pollResult.date}</p>
                    </c:when>
                    <c:otherwise>
                        <br>
                        <p>You didn't submit anything for this question yet</p>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${empty poll.pollComments}">
                        <br>
                        <p>There are no comment for this poll</p>
                        <br>
                        <h3>List of Comments</h3>
                    </c:when>
                    <c:otherwise>
                        <ul>
                            <c:forEach var="comment" items="${poll.pollComments}">
                                <li><p>${comment.content}</p>

                                    <p>Comment written by ${comment.user.fullName} at <fmt:formatDate value="${comment.date}" pattern="dd-MM-yyyy HH:mm"/><security:authorize access="hasAnyRole('LECTURER')"><a href="${editPollCommentURL}${comment.commentID}">Edit Comment</a></security:authorize> </p></li>

                                </c:forEach>
                        </ul>
                    </c:otherwise>
                </c:choose>
                <a href="${addCommentURL}${poll.pollID}">Add Comment</a>
            </c:otherwise>
        </c:choose>


    </body>
</html>
