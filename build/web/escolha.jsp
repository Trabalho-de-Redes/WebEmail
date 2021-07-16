
<%@page import="util.Email"%>
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
            String userEmail = request.getParameter("email");
            String senha = request.getParameter("senha");

        %>

        <div class="login-page">
            <div class="form" >
                <form class="login-form" action="enviarEmail.jsp" method="post">
                    <h1>Menu WebMail</h1>
                    <input name="email" hidden placeholder="Email" value="<% out.write(userEmail);%>">
                    <input name ="senha" hidden placeholder="Senha" value="<% out.write(senha);%>">
                    <button>Caixa de Envio</button>
                </form>
                <br>

                <form class="login-form" action="caixa_email.jsp" method="post">
                    <input name="email" hidden placeholder="Email" value="<% out.write(userEmail);%>">
                    <input name ="senha" hidden placeholder="Senha" value="<% out.write(senha);%>">
                    <button>Caixa de Entrada</button>
                </form>

            </div>
        </div>
    </body>
</html>
