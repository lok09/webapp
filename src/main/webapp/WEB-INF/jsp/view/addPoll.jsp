<html>
    <head>
        <title>Add Poll</title>
        <style>label {
                width:150px;
                display: inline-block;
            }
            input[type="text"]{
                width: 200px;
            }</style>
    </head>
    <body>
        <c:url value="/" var="mainURL" />
        <a href="${mainURL}">MAIN PAGE</a> <br>
        <h1>Add Poll</h1>
        <form:form method="POST" modelAttribute="poll">
            <form:label path="question">Poll Question: </form:label>
            <form:input type="text" path="question"/><br/>
            <form:label path="optionA">Option A: </form:label>
            <form:input type="text" path="optionA"/><br/>
            <form:label path="optionB">Option B: </form:label>
            <form:input type="text" path="optionB"/><br/>
            <form:label path="optionC">Option C: </form:label>
            <form:input type="text" path="optionC"/><br/>
            <form:label path="optionD">Option D: </form:label>
            <form:input type="text" path="optionD"/><br/>
            <form:label path="answer">Answer: </form:label>
            <form:input type="text" path="answer"/><br/>
            <input type="submit" value="Add">
        </form:form>

    </body>
</html>
