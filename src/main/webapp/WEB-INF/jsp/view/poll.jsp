<html>
    <head>
        <title>Poll detail</title>
    </head>
    <body>
<<<<<<< HEAD
        <c:url value="/poll/submit/${poll.pollID}/" var="submitURL"/>
        <c:url value="/poll/addComment/" var="addCommentURL"/>
        <c:url value="/poll/edit/editPoll/" var="editPollURL"/>
        <c:url value="/poll/edit/editPollComment/" var="editPollCommentURL"/>
=======
        <c:url value="/" var="mainURL" />
        <a href="${mainURL}">MAIN PAGE</a> <br>
        <c:url value="/poll/submit/${poll.pollID}/" var="submitURL"/>
        <c:url value="/poll/addComment/" var="addCommentURL"/>
>>>>>>> main
        <c:choose>
            <c:when test="${empty poll}">
                This poll does not exist
            </c:when>
            <c:otherwise>
                <p>Question: ${poll.question}</p><br>
<<<<<<< HEAD
                <security:authorize access="hasAnyRole('LECTURER')">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${editPollURL}${poll.pollID}">Edit Poll</a>
                </security:authorize>
                <p>Answer: ${poll.answer}</p><br>
                <p><a href="${submitURL+="A"}">A: ${poll.optionA}</a>${optionA}</p>
                <p><a href="${submitURL+="B"}">B: ${poll.optionB}</a>${optionB}</p>
                <p><a href="${submitURL+="C"}">C: ${poll.optionC}</a>${optionC}</p>
                <p><a href="${submitURL+="D"}">D: ${poll.optionD}</a>${optionD}</p>
=======
                <p>Answer: </p><br>
                <p><a href="${submitURL+="A"}">A: ${poll.optionA}</a></p>
                <p><a href="${submitURL+="B"}">B: ${poll.optionB}</a></p>
                <p><a href="${submitURL+="C"}">C: ${poll.optionC}</a></p>
                <p><a href="${submitURL+="D"}">D: ${poll.optionD}</a></p>
>>>>>>> main
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
                            <c:forEach var="comments" items="${poll.pollComments}">
                                <li><p>${comments.content}</p>
<<<<<<< HEAD
                                    <p>Comment written by ${comments.user.fullName} at <fmt:formatDate value="${comments.date}" pattern="dd-MM-yyyy HH:mm"/> <a href="${editPollCommentURL}${comments.commentID}">Edit Poll</a></p></li>
=======
                                    <p>Comment written by ${comments.user.fullName} at <fmt:formatDate value="${comments.date}" pattern="dd-MM-yyyy HH:mm"/> </p></li>
>>>>>>> main
                                </c:forEach>
                        </ul>
                    </c:otherwise>
                </c:choose>
                <a href="${addCommentURL}${poll.pollID}">Add Comment</a>
            </c:otherwise>
        </c:choose>


    </body>
</html>
