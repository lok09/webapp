<html>
    <head>
        <title>Poll detail</title>
    </head>
    <body>
        <c:url value="/" var="mainURL" />
        <a href="${mainURL}">MAIN PAGE</a> <br>
        <c:url value="/poll/submit/${poll.pollID}/" var="submitURL"/>
        <c:url value="/poll/addComment/" var="addCommentURL"/>
        <c:choose>
            <c:when test="${empty poll}">
                This poll does not exist
            </c:when>
            <c:otherwise>
                <p>Question: ${poll.question}</p><br>
                <p>Answer: </p><br>
                <p><a href="${submitURL+="A"}">A: ${poll.optionA}</a></p>
                <p><a href="${submitURL+="B"}">B: ${poll.optionB}</a></p>
                <p><a href="${submitURL+="C"}">C: ${poll.optionC}</a></p>
                <p><a href="${submitURL+="D"}">D: ${poll.optionD}</a></p>
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
                                    <p>Comment written by ${comments.user.fullName} at <fmt:formatDate value="${comments.date}" pattern="dd-MM-yyyy HH:mm"/> </p></li>
                                </c:forEach>
                        </ul>
                    </c:otherwise>
                </c:choose>
                <a href="${addCommentURL}${poll.pollID}">Add Comment</a>
            </c:otherwise>
        </c:choose>


    </body>
</html>
