<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Resultado de Envio</title>
        <link rel="stylesheet" href="login.css">
    </head>
    <body>

        <div class="login-page">
            <div class="form" >

                <h1>
                    ${requestScope.message}
                </h1>

                <div class="login-form" >

                    <br>
                    <button onclick="history.go(-1)">Caixa Envio</button>
                    <br>
                    <br>
                    <button onclick="history.go(-2)">Menu</button>
                    <br>
                    <br>
                    <a class="not-active" href="index.html"> <button>Sair</button> </a>

                </div>
            </div>
        </div>
    </body>
</html>