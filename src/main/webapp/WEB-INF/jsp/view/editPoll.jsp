<html>
    <head>
        <title>Edit Poll</title>
    </head>
    <body>
        <h1>Edit Poll</h1>
        <form:form method="POST" modelAttribute="poll">
            <form:label path="question">Poll Question: </form:label>
            <form:input type="text" value="${poll.question}" path="question"/><br>
            <form:label path="optionA">Option A: </form:label>
            <form:input type="text" value="${poll.optionA}" path="optionA"/>
            <form:label path="optionB">Option B: </form:label>
            <form:input type="text" value="${poll.optionB}" path="optionB"/>
            <form:label path="optionC">Option C: </form:label>
            <form:input type="text" value="${poll.optionC}" path="optionC"/>
            <form:label path="optionD">Option D: </form:label>
            <form:input type="text" value="${poll.optionD}" path="optionD"/>
            <form:label path="answer">Answer: </form:label>
            <form:input type="text" value="${poll.answer}" path="answer"/>
            <input type="submit" value="update">
        </form:form>

    </body>
</html>