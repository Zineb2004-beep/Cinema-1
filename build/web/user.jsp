<%-- 
    Document   : user
    Created on : 19 avr. 2025, 01:18:46
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulaire Utilisateur - Cinéma</title>
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
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 30px;
        }

        .form-card {
            background-color: var(--card-bg);
            padding: 40px;
            border-radius: var(--border-radius);
            width: 100%;
            max-width: 500px;
            box-shadow: 0 0 20px rgba(229, 9, 20, 0.2);
        }

        h2 {
            margin-bottom: 30px;
            text-align: center;
            color: var(--primary-color);
            font-size: 28px;
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
        input[type="password"] {
            width: 100%;
            padding: 12px;
            border: 1px solid #444;
            background-color: var(--input-bg);
            color: var(--text-color);
            border-radius: var(--border-radius);
            transition: border-color 0.3s ease;
        }

        input[type="text"]:focus,
        input[type="password"]:focus {
            border-color: var(--primary-color);
            outline: none;
        }

        input[type="submit"] {
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

        input[type="submit"]:hover {
            background-color: #b20710;
        }

        @media (max-width: 600px) {
            .form-card {
                padding: 25px;
            }
        }
    </style>
</head>
<body>
    <div class="form-card">
        <h2>Formulaire Utilisateur 🎥</h2>
        <form method="POST" action="../UserController">
            <input type="hidden" name="id" value="<%= request.getParameter("id") != null ? request.getParameter("id") : "" %>" />

            <div class="form-group">
                <label for="nom">Nom :</label>
                <input type="text" id="nom" name="nom" value="<%= request.getParameter("nom") != null ? request.getParameter("nom") : "" %>" placeholder="Entrez votre nom" />
            </div>

            <div class="form-group">
                <label for="prenom">Prénom :</label>
                <input type="text" id="prenom" name="prenom" value="<%= request.getParameter("prenom") != null ? request.getParameter("prenom") : "" %>" placeholder="Entrez votre prénom" />
            </div>

            <div class="form-group">
                <label for="email">Email :</label>
                <input type="text" id="email" name="email" value="<%= request.getParameter("email") != null ? request.getParameter("email") : "" %>" placeholder="ex: nom@email.com" />
            </div>

            <div class="form-group">
                <label for="mdp">Mot de passe :</label>
                <input type="password" id="mdp" name="mdp" value="<%= request.getParameter("mdp") != null ? request.getParameter("mdp") : "" %>" placeholder="Choisissez un mot de passe" />
            </div>

            <input type="submit" value="🎬 Enregistrer l'utilisateur" />
        </form>
    </div>
</body>
</html>


