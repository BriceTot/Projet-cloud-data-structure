<%--
  Created by IntelliJ IDEA.
  User: brice
  Date: 07/12/2022
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML PUBliC "-//W3C/DTD HTML 4.0 Transitional//EN">
<html>
<META HTTP-EQUIV = "Pragma" CONTENT="no-cache">
<link type="text/css" rel="stylesheet" href="css/style.css">
<title>Logout Page </title>
<body>
<label>Welcome ${username}</label>
<h2>Logout</h2>
<FORM METHOD=POST ACTION="Logout" NAME="logout">
  <p>
    <BR>
    <BR>
    <font size="2"><strong> Click this button to log out: </strong></font>
    <input type="submit" name="logout" value="Logout">
  </p>
</form>
</body>
</html>
