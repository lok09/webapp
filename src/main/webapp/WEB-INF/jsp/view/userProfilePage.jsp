
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
    <c:choose>
        <c:when test="${empty user}">
            <p>no such user exists</p>
        </c:when>
             <c:otherwise>
                 <h3>Information of this user</h3>                     
                 <table>
                   <tr>
                      <th>${user.username}</th>
                      <th>${user.role}</th>
                      <th>${user.fullName}</th>
                      <th>${user.phoneNumber}</th>
                      <th>${user.address}</th>
                   </tr>
                  </table>       
                   
                    <h3>the&nbsp;${user.fullName}&nbsp;pollResults</h3>     
                      <c:if test="${empty user.pollResults}">
                          <p>No pollResults</p>
                      </c:if>
                          
                   <c:if test="${not empty user.pollResults}">
                      <ul>
                        <c:forEach var="pollresult" items="${user.pollResults}">
                            <li>${pollresult.poll.question}</li>
                            <li>${pollresult.option}</li>
                            <li>${pollresult.date}</li>
                        </c:forEach>
                      </ul>
                   </c:if>
                          
                     <h3>the&nbsp;${user.fullName}&nbsp;lectureComments</h3>   
                     <c:if test="${empty user.lectureComments}">
                          <p>No lectureComments</p>
                      </c:if>
                          
                        <c:if test="${not empty user.lectureComments}">
                      <ul>
                        <c:forEach var="lectureComment" items="${user.lectureComments}">
                            <li>${lectureComment.lecture.lectureTitle}</li>
                            <li>${lectureComment.content}</li>
                            <li>${lectureComment.date}</li>
                        </c:forEach>
                      </ul>
                   </c:if>
                   
             </c:otherwise>                       
                                                    
    </c:choose>
</body>
</html>
