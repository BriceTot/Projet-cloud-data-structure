<%--
  Created by IntelliJ IDEA.
  User: brice
  Date: 06/12/2022
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Web toDo List</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>
<body>
<label>Welcome ${username}</label>
<!-- ${TODO_LIST}-->
<div id="wrapper">
    <div id="header">
        <h2>ToDo List</h2>
    </div>
</div>
<form method="get" action="AddToDoServlet">
    <input type="submit" value="Add a ToDo" />
</form>
<div id="container">
    <div id="content">
        <table>
            <tr>
                <th>Description</th>
            </tr>
            <c:forEach var="tempToDo" items="${TODO_LIST }" >
                <c:url var="EditLink" value= "/EditToDoServlet">
                    <c:param name="toDoId" value="${tempToDo.id}"/>
                </c:url>
                <c:url var="DeleteLink" value= "/DeleteToDoServlet">
                    <c:param name="toDoId" value="${tempToDo.id}"/>
                </c:url>
                <tr>
                    <td> ${tempToDo.description}</td>
                    <td> <a href="${EditLink }"> Edit</a></td>
                    <td> <a href="${DeleteLink }"> Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<form action="Logout" method="get">
    <input type="submit" value="logout"/>
</form>
</body>
</html>
