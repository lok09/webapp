
<html>
    <head>
        <title>Lecture detail</title>
    </head>
    <body>
        <c:url value="/logout" var="logoutURL" />
        <a href="${logoutURL}">Logout</a> <br>
        <c:url value="/" var="mainURL" />
        <a href="${mainURL}">MAIN PAGE</a> <br>
        <c:url value="/lecture/addComment/" var="addCommentURL" />
        <c:url value="/lecture/material/" var="downloadURL" />
        <c:url value="/lecture/edit/uploadMaterial/" var="uploadURL"/>
        <c:url value="/lecture/edit/deleteMaterial/" var="deleteMaterialURL"/>
        <c:url value="/lecture/edit/editLecture/" var="editLectureURL"/>
        <c:choose>
            <c:when test="${empty lecture}">
                <p>There is no such lectures at this course</p>
            </c:when>
            <c:otherwise>
                <p>Lecture Title: ${lecture.lectureTitle}</p>
                <security:authorize access="hasAnyRole('LECTURER')">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${editLectureURL}${lecture.lectureID}">Edit Title</a>
                </security:authorize>
                <br>

                <c:if test="${empty lecture.materials}">
                    <p>There are no material for this lecture</p><br>
                </c:if>
                <c:if test="${empty lecture.comments}">
                    <p>There are no comment for this lecture</p><br>
                </c:if>
                <c:if test="${not empty lecture.materials}">
                    <h3>List of Materials</h3>
                    <ul>
                        <c:forEach var="material" items="${lecture.materials}">
                            <li><a href="${downloadURL}${material.materialID}">${material.materialName}</a>
                                <p>material uploaded at <fmt:formatDate value="${material.date}" pattern="dd-MM-yyyy HH:mm"/>&nbsp;<security:authorize access="hasAnyRole('LECTURER')">
                                        <a href="${deleteMaterialURL}${material.materialID}">Delete Material</a>
                                    </security:authorize></p></li>
                                </c:forEach>
                    </ul><br>
                </c:if>
                <security:authorize access="hasAnyRole('LECTURER')">
                    <a href="${uploadURL}${lecture.lectureID}">Add Material</a><br>
                </security:authorize>
                <c:if test="${not empty lecture.comments}">
                    <h3>List of Comments</h3>
                    <ul>
                        <c:forEach var="comments" items="${lecture.comments}">
                            <li>
                                <p>${comments.content}
                                    <security:authorize access="hasAnyRole('LECTURER') or principal.username=='${comments.user.username}'">
                                        [<a href="<c:url value="/lecture/${lecture.lectureID}/editComment/${comments.commentID}" />">Edit</a>]
                                    </security:authorize>
                                    <security:authorize access="hasAnyRole('LECTURER')">

                                        [<a href="<c:url value="/lecture/${lecture.lectureID}/deleteComment/${comments.commentID}" />">Delete</a>]
                                    </security:authorize>
                                </p>
                                <p>lectureComment written by ${comments.user.fullName} at <fmt:formatDate value="${comments.date}" pattern="dd-MM-yyyy HH:mm"/> </p></li>
                            </c:forEach>
                    </ul>
                </c:if>
                <a href="${addCommentURL}${lecture.lectureID}">Add Comment</a>
            </c:otherwise>
        </c:choose>
    </body>
</html>
