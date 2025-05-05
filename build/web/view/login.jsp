<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Connexion - Application Cinéma</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">

    </head>
    <body>

        <header class="main-header">
            <div class="logo">
                <h1>Cinématy</h1>
            </div>
            <nav>
                <a href="view/login.jsp">Connexion</a>
            </nav>
            <nav>
                <a href="view/signup.jsp">Inscription</a>
            </nav>
        </header>

        <!-- Formulaire de connexion -->
        <div class="login-container">
            <h2>Connexion</h2>
            <form class="login-form" method="POST" action="/Cinema/login">
                <div class="form-group">
                    <label for="email">Email :</label>
                    <input type="email" name="email" id="email" required>
                </div>

                <div class="form-group">
                    <label for="motDePasse">Mot de passe :</label>
                    <input type="password" name="motDePasse" id="motDePasse" required>
                </div>

                <input type="submit" value="Se connecter" class="btn-submit">

                <% if (request.getAttribute("error") != null) {%>
                <div class="error"><%= request.getAttribute("error")%></div>
                <% }%>

                <p style="text-align: center; margin-top: 15px;">
                    Vous n'avez pas de compte ?
                    <a href="signup.jsp" style="color: #795548;">Créer un compte</a>
                </p>
            </form>
        </div>

    </body>
</html>
