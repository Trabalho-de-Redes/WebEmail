<%@page import="javax.mail.Multipart"%>
<%@page import="java.util.Date"%>
<%@page import="javax.mail.Message"%>
<%@page import="util.receber"%>
<%@page import="util.Email"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="login_1.css" class="style">
        <title>Caixa de Entrada</title>
    </head>
    <body>

        <div class="form" >

            <h1>Caixa de Entrada </h1>
            <hr>
            <%
                receber msgs = new receber();
                String email = request.getParameter("email");
                String senha = request.getParameter("senha");
                Message[] messages = msgs.doit(msgs.retornaPop(email), email,
                        senha);

                if (messages.equals(null)) {
            %> 
            <h3>Caixa de Mensagens Vazia</h3>
            <hr>
            <%
            } else {

                for (int i = 0; i < messages.length; ++i) {
            %>
            <p><%out.println("MENSSAGEM #" + (i + 1) + ":");%></p>
            <%
                Message msg = messages[i];
                String from = "unknown";
                if (msg.getReplyTo().length >= 1) {
                    from = msg.getReplyTo()[0].toString();

                } else if (msg.getFrom().length >= 1) {
                    from = msg.getFrom()[0].toString();
                }
                String subject = msg.getSubject();
                String dataEnvio = msg.getSentDate().toString();
                Object mult = msg.getContent();
                String text = "";
                if (mult instanceof Multipart) {
                    text = "Mensagem contém varias partes, verificar no seu provedor";
                } else {
                    text = msg.getContent().toString();
                }

            %>
            <p> <% out.println("De: " + from); %></p> 
            <p> <% out.println("Assunto: " + subject); %> </p> 
            <p> <% out.println("Mensagem: " + text);%> </p>
            <p> <% out.println("Data de Envio: " + dataEnvio);%> </p>
            <hr>

            <%
                    }
                }%>

            <div class="login-form" >
                <br>
                <button onclick="history.go(-1)">Menu</button>
                <br>
                <br>
                <a class="not-active" href="index.html"> <button>Sair</button></a>
            </div>
        </div>
    </body>   
</html>
