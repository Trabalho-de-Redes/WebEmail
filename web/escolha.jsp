<%-- 
    Document   : escolha
    Created on : 13/07/2021, 23:32:11
    Author     : adail
--%>

<%@page import="model.Email"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" href="login.css" class="style">
    </head>
    <body>
        
            <%
        Email email = new Email();
        String userEmail = request.getParameter("email");
        String senha = request.getParameter("senha");
        email.setEmailFrom(userEmail);
        email.setSenha(senha);
  
      
      
        %>
        
        <div class="login-page">
        <div class="form" >
            <form class="login-form" action="enviarEmail.jsp" method="post">
            <h1>Menu WebMail</h1>
            <input name="email" type="hidden" placeholder="Email" value="<% out.write(email.getEmailFrom());%>">
            <input name ="senha" type="hidden" placeholder="Senha"value="<% out.write(email.getSenha());%>">
            <button>Caixa de Envio</button>
          </form>
            <br>
            <form class="login-form" action="caixa_email.jsp" method="post">
                <input name="email" type="hidden" placeholder="Email" value="<% out.write(email.getEmailFrom());%>">
            <input name ="senha" type="hidden" placeholder="Senha"value="<% out.write(email.getSenha());%>">
                  <button>Caixa de Entrada</button>
            </form>
            
        </div>
      </div>
    </body>
</html>
