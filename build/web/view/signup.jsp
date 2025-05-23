

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <title>Inscription - Client</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            :root {
                --background: #121212;
                --card-bg: #1e1e1e;
                --primary-color: #e50914;
                --text-color: #ffffff;
                --input-bg: #2c2c2c;
                --border-radius: 10px;
            }

            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            }

            body {
                background-color: var(--background);
                color: var(--text-color);
                height: 100vh;
                display: flex;
                align-items: center;
                justify-content: center;
                padding: 20px;
            }

            .signup-container {
                background-color: var(--card-bg);
                padding: 40px;
                border-radius: var(--border-radius);
                box-shadow: 0 0 20px rgba(229, 9, 20, 0.2);
                width: 100%;
                max-width: 400px;
            }

            h2 {
                text-align: center;
                margin-bottom: 30px;
                color: var(--primary-color);
                font-size: 26px;
            }

            .form-group {
                margin-bottom: 20px;
            }

            label {
                display: block;
                margin-bottom: 8px;
                font-weight: 600;
            }

            input[type="text"],
            input[type="password"],
            input[type="email"] {
                width: 100%;
                padding: 12px;
                border: 1px solid #444;
                background-color: var(--input-bg);
                color: var(--text-color);
                border-radius: var(--border-radius);
                font-size: 16px;
                transition: border-color 0.3s;
            }

            input[type="text"]:focus,
            input[type="password"]:focus,
            input[type="email"]:focus {
                border-color: var(--primary-color);
                outline: none;
            }

            .btn-signup {
                width: 100%;
                padding: 14px;
                background-color: var(--primary-color);
                color: white;
                border: none;
                border-radius: var(--border-radius);
                font-size: 16px;
                font-weight: bold;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            .btn-signup:hover {
                background-color: #b20710;
            }

            .error-message {
                color: #ff4f4f;
                background-color: #330000;
                padding: 10px;
                border-radius: var(--border-radius);
                margin-bottom: 20px;
                text-align: center;
                font-size: 14px;
            }

            @media (max-width: 500px) {
                .signup-container {
                    padding: 25px;
                }
            }
        </style>
    </head>
    <body>
        <div class="signup-container">
            <h2>🎬 Inscription</h2>

            <% if (request.getAttribute("error") != null) {%>
            <div class="error-message"><%= request.getAttribute("error")%></div>
            <% }%>

            <form action="${pageContext.request.contextPath}/SignupController" method="POST">
                <div class="form-group">
                    <label for="cin">Numéro CIN :</label>
                    <input type="text" id="cin" name="cin" required>
                </div>

                <div class="form-group">
                    <label for="nom">Nom :</label>
                    <input type="text" id="nom" name="nom" required>
                </div>

                <div class="form-group">
                    <label for="prenom">Prénom :</label>
                    <input type="text" id="prenom" name="prenom" required>
                </div>

                <div class="form-group">
                    <label for="email">Adresse e-mail :</label>
                    <input type="email" id="email" name="email" required>
                </div>

                <div class="form-group">
                    <label for="motDePasse">Mot de passe :</label>
                    <input type="password" id="motDePasse" name="motDePasse" required>
                </div>

                <button type="submit" class="btn-signup">🎟️ S'inscrire</button>
            </form>
        </div>
    </body>
</html>




