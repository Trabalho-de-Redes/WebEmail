
<%@page import="java.util.Date"%>
<%@page import="javax.mail.Message"%>
<%@page import="model.receber"%>
<%@page import="model.Email"%>
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
                
                Message[] messages = msgs.doit(msgs.retornaPop(email) ,email, 
                       request.getParameter("senha"));

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
              


            %> <p> <% out.println("Assunto: " + subject); %> </p> 
            <p> <% out.println("Mensagem: ");%> </p>
            <p> <% out.println("De: " + from); %></p> 
            <hr>

            <%

                        // you may want to replace the spaces with "_"
                        // the files will be saved into the TEMP directory
                    }
                }%>
        </div>


    </body>   
</html>
