
<%@page import="model.Email"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmacao Envio</title>
        <link rel="stylesheet" href="login.css" class="style">
    </head>
    <body>
        <%
            Email email = new Email();

            //        String userEmail = request.getParameter("email");
            //        String senha = request.getParameter("senha");
            //        String to = request.getParameter("to");
            //        String subject = request.getParameter("subject");
            //        String mensagem = request.getParameter("mensagem");
            //        String mailServer = request.getParameter("mailServer");
            //        
            //        out.println("email: "+ userEmail);
            //        out.println("senha:"+ senha);
            //        out.println("msg:"+ mensagem);
            //        out.println("Server:"+ mailServer);
            //        out.println("sub: "+ subject);
            //        out.println("to : " +to);
            //        
            boolean result = email.enviarEmail(request.getParameter("mailServer"),
                    request.getParameter("email"),
                    request.getParameter("to"),
                    request.getParameter("cc"),
                    request.getParameter("subject"),
                    request.getParameter("mensagem"),
                    request.getParameter("senha"));

//            boolean result = true;
        %>

        <div class="login-page">
            <div class="form" >


                <h1>
                    <%                        if (result) {
                            out.write("O seu e-mail foi enviado com Sucesso!");
                        } else {
                            out.write("Ocorreu um erro!");
                        }

                    %>
                </h1>
                <div class="login-form">


                    <a  class="not-active" href="enviarEmail.jsp"><button>Caixa de Envio</button> </a> 
                    <br>
                    <br>
                    <a  class="not-active" href="index.html"> <button>Sair</button> </a>
                </div>
            </div>
        </div>
    </body>
</html>
