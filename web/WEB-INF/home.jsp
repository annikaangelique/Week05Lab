<%-- 
    Document   : home
    Created on : 20-Feb-2021, 1:33:36 PM
    Author     : alexz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <h1>Home Page</h1>
        <h2>Hello ${user.username}</h2>
        <a href="login?logout">Log out</a>
    </body>
</html>
