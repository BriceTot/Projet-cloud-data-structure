<%--
  Created by IntelliJ IDEA.
  User: brice
  Date: 07/12/2022
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<HTML>
<HEAD>
    <TITLE> AUTHENTIFICATION avec Formulaire JAAS </TITLE>
</HEAD>
<body>
<div align="center">
    <h1>Veuillez vous identifier</h1>
</div>
<form method="POST" action="j_security_check">
    <table align="center">
        <tr>
            <td>Login :</td>
            <td><input type="text" name="j_username" value=${usernameCookie}></td>
        </tr>
        <tr>
            <td>Mot de passe :</td>
            <td><input type="password" name="j_password" value=""></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Valider"></td>
            <td><input type="reset" value="Annuler"></td>
        </tr>
    </table>
</form>
</body>
</HTML>

