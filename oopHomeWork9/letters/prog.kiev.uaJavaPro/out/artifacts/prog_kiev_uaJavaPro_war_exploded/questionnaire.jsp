<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Questionnaire</title>
</head>
<body>
<% String login = (String)session.getAttribute("user_login"); %>
<% if (login == null) { %>
<%      response.sendRedirect("index.jsp"); } %>

<form action="/singleStatistic" method = "POST">
    <p><b>Do you like red cats?</b></p>
    <p><input type="radio" name="question1" value="Yes" checked>Yes<Br>
        <input type="radio" name="question1" value="No">No<Br>
    <p><b>Do you like white rats?</b></p>
    <p><input type="radio" checked name="question2" value="Yes" checked>Yes<Br>
        <input type="radio" name="question2" value="No">No<Br>
    <p><input type="submit"></p>
</form>

<h4>You are logged in as: <%= login %></h4>
<br>Click this link to <a href="/login?a=exit">logout</a>
</body>
</html>