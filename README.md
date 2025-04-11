# 🎬 Application de Gestion de Cinéma

## 📚 Sommaire
- [📌 Contexte](#-contexte)
- [❓ Problématique](#-problématique)
- [🎯 Objectifs](#-objectifs)
- [🧩 Diagramme de Classe](#-diagramme-de-classe)
- [📸 Captures d'Écran](#-captures-décran)

---

## 📌 Contexte

Ce projet est une application web développée en **Java (JSP/Servlets + Hibernate)** permettant de gérer les activités d’un cinéma.  
Les utilisateurs peuvent consulter les films à l’affiche, réserver leurs places pour une séance, et accéder à l’historique de leurs réservations.  
Les administrateurs ont accès à des statistiques visuelles afin d’analyser les tendances (par genre de film).

---

## ❓ Problématique

Dans un contexte de digitalisation croissante, les cinémas ont besoin d’un système :

- 💡 Centralisé pour la gestion des films, séances et réservations.
- ⚡ Fluide pour permettre la recherche rapide de séances.
- 🧠 Intelligent avec un affichage instantané des places disponibles (AJAX).
- 📊 Interactif pour suivre les préférences du public grâce à des statistiques.

---

## 🎯 Objectifs

- ✅ Développer une plateforme intuitive de réservation de films.
- 👥 Gérer les comptes utilisateurs (clients et administrateurs).
- 🎥 Permettre de :
  - Consulter les films à l’affiche.
  - Réserver des séances facilement.
  - Afficher les réservations personnelles.
- 📈 Intégrer **Chart.js** pour visualiser les réservations par genre.
- 🔄 Utiliser **AJAX** pour la mise à jour dynamique des places disponibles.

---

## 🧩 Diagramme de Classe

📎 *Diagramme UML représentant les entités principales de l’application :*

![Diagramme de classe](images/diagramme%20de%20classe%20Cinema.png)

---

## 📸 Captures d'Écran

### 🛠️ Création automatique de la base avec Hibernate

Hibernate permet de générer automatiquement les tables à partir des entités Java :

![Création de la base de données](images/creation%201.png)
![Création de la base de données](images/creation%202.png)
![Création de la base de données](images/creation%203.png)

---


### 📥 Insertion des Données (Hibernate)

Insertion de films, séances et réservations via les entités Hibernate (extrait du fichier `Test.java`) :

![Insertion des données](images/insertion%201.png)

---

### 🔍 Le filtrage 

![Filtrage](images/filtrage%201.png)
![Filtrage](images/filtrage%202.png)

---

