<%-- 
    Document   : receber
    Created on : 09/07/2021, 19:53:27
    Author     : adail
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            
            out.println(email);
            out.println(senha);
            
            %>
    </body>
</html>
