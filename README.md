# Projet Île Interdite
Projet LDD2 IM POGL


    Ce README doit contenir les instructions de lancement et :
        Les parties du sujet que vous avez trait ́ees.
        Vos choix d’architecture. Les choix d’architecture font partie de ce que nous allons ́evaluer :
            donnez un peu de d ́etail sur l’organisation de vos classes et le partage des rˆoles. 
            N’h ́esitez pas `a utiliser massivement les diff ́erentes techniques vues en cours et en TP.
        Les probl`emes qui sont pr ́esents et que vous n’avez pas pu ́eliminer.
        Les morceaux de code ́ecrits `a plusieurs ou emprunt ́es ailleurs. Sans cette information, cela s’appelle du plagiat, 
            et est consid ́er ́e comme une fraude par le r ́eglement de l’uni- versit ́e.
            On fera une exception pour ce qui est issu des notes de cours ou des corrections de TP.
    Il faut un diagramme de classe pour le projet
    Il faut des tests pour les methodes
    Les methodes doivent contenir de la documentation :
        Descritpion des parametres
        Description des retours
        Description de la methode

## Les parties traitées :
    Notre application est une version simplifiée du jeu "l'ile interdite" et prend en charge les fonctionnalités suivantes :
    - Le jeu peut se jouer de 1 à 4 joueurs, parametre saisi par l'utilisateur
    - Le jeu gere son initialisation en disposant les joueurs et les artefacts sur les zones disponibles de manière aléatoire.
    Un héliport est aussi placé aléatoirement sur le plateau
    - Le jeu gere les trois actions definies dans le jeu : "Se deplacer", "Recuperer un artefact" et "Assecher une zone"
    - Le jeu gere les évènements d'inondations des zones (lors de la fin d'un tour ou pour l'évènement "montée des eaux" 
    declenché lors d'une recherche de clé)
    - Le jeu gere la recherche de clé et la fin d'un tour d'un joueur (l'évènement s'effectue automatiquement si le 
    joueur a réalisé trois actions parmi les précédentes mais peut être enclenché par l'utilisateur s'il ne souhaite pas 
    utiliser toutes ses actions)
    - Le jeu gere l'élimination des joueurs et affiche un GAME OVER si l'un des joueur se retrouve éliminé
    - Le jeu gere le "rattrapage" d'un joueur : ci celui-ci se trouve sur une case submergee, il se positionne sur 
    l'une des cases adjacentes si certaines sont disponibles. Il est éliminé si aucune case n'est disponible (GAME OVER)
    - Le jeu gere l'affichage de l'inventaire de l'équipe afin que les utilisateurs puissent connaitre le nombre 
    d'actions encore disponibles, le joueur à qui c'est le tour, les clés qu'ils ont récupérés ainsi que les artefacts 
    récupérés
    - L'équipe peut à tout moment arreter la partie pour changer les parametres/ relancer une nouvelle partie/ quitter.
    
 ## Les parties non traitées (pour le moment) :
    - Declencher la "defaite" (GAME OVER) quand un artefact toujours pas récupéré n'est 
    plus disponible, quand l'héliport se retrouve submergé. Plus difficile : quand un artefact n'est plus accessible, quand 
    l'héliport n'est plus accessible pour un joueur.
    - Mettre en place les joueurs "spéciaux" qui ont des actions spécifiques.
    - Declencher la victoire lorsque les joueurs retournent à l'héliport et qu'ils ont récupérés tous les artefacts
    - 
    
## Architecture :
    Les fichiers sources sont disposés selon le modele de Conway (Modeles-Vue-Controleur):
    -Le dossier "Modeles" contient tous les objets correspondant au probleme et qui contiennent les données relatives à celui-ci :
      * La classe Coord        : représentation des coordonnées d'une grille
      * La classe Joueur       : représentation d'un joueur 
      * La classe Zone         : représentation d'une zone de la grille. Elle "extends 
      * La classe Ile          : représentation de l'ile. Elle "extends" une Grille de l'interface graphique
      * L'enumeration Artefact : représentaion des artefacts présent sur une zone (Eau, Feu, Terre, Air, Vide, Heliport)
      * L'enumeration Etat     : représentation de l'état d'une zone (Normale, Inondee, Submergee)
      
    -Le dossier "Vue" contient les fichiers utilisés pour la mise en place de l'interface graphique :
    
    
    
Problèmes :
    Aucun ?
    
## Inspiration :

MODELES : Aucune inspiration en particulier, utilisation des notions du cours pour créer les objets qui correspondent au problème

VUE et CONTROLEUR : (Interface graphique et gestion des évènements)
    L'interface grpahique a été construite après plusieurs recherches sur Internet (pour comprendre le fonctionnement de certains objets swing comme les JPanel et leurs Layout, les JButton pour creer des boutons, les JLabel pour mettre du texte, les ActionListener pour gere des évènements ou encore les Graphics/paintComponent pour dessiner sur un composant). Elle s'inspire en partie aussi des modeles vus en TD.
    
    
    
    
    
