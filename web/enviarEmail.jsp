<%@page import="model.Email"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Enviar Email</title>
    <link rel="stylesheet" href="login.css">
</head>
<body>
    <%
        Email email = new Email();
        String userEmail = request.getParameter("email");
        String senha = request.getParameter("senha");
        email.setEmailFrom(userEmail);
        email.setSenha(senha);
        email.setServerEmail(email.retornaSMTP(userEmail));
       
     

      
        %>
    
    <div class="login-page">
        <div class="form">
            <form class="login-form" action="enviado.jsp" method="post">
            <h1>Envio de E-mail</h1>
            
            <input id="server" type="text" name="mailServer" readonly="readonly" value="<% out.write(email.getServerEmail()); %>"/>
            <input id="from" type="text" readonly="readonly" name="email"  value="<% out.write(email.getEmailFrom()); %>"/>
            <input id="to" type="text"  name="to" placeholder="To"/>
            <input id="cc" type="text" name="cc" placeholder="Cc"/>
            <input id="bcc" type="text" name="bcc" placeholder="Bcc"/>
            <input id="subj" type="text" name="subject" placeholder="Subject"/>
            <textarea  name="mensagem"  id="msg" cols="30" rows="10" placeholder="Mensagem"></textarea>
            <input id="arq" name="arq" type="file">

            <input name="senha" hidden readonly="readonly" value="<% out.write(email.getSenha());%>">
            <button>Enviar</button>
          </form>
        </div>
      </div>
    

</body>
</html>