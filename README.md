# Projet Île Interdite
Projet LDD2 IM POGL 
 - Alexandre LORET 
 - Lorenzo BRUCATO

## Lancement : 
    Executer le main() situé dans la classe controleur

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
    
## Architecture :
    Les fichiers sources sont disposés selon le modele de Conway (Modeles-Vue-Controleur)
    Le main() qui lance l'application est situé dans la classe controleur.
    
    -Le dossier "Modeles" contient tous les objets correspondant au probleme et qui contiennent les données relatives à celui-ci :
      * La classe Coord                 :     représentation des coordonnées d'une grille
      * La classe Joueur                : représentation d'un joueur 
      * La classe Zone (extends JPanel) : représentation d'une zone de la grille 
      * La classe Ile (extends Grille)  : représentation de l'ile
      * L'enumeration Artefact          : représentaion des artefacts présent sur une zone (Eau, Feu, Terre, Air, Vide, Heliport)
      * L'enumeration Etat              : représentation de l'état d'une zone (Normale, Inondee, Submergee)
      
    -Le dossier "Vue" contient les fichiers utilisés pour la mise en place de l'interface graphique :
      * La classe Fenetre (extends JFrame) : la fenetre de l'interface graphique, composés de boutons, de panneaux, ...
      * La classe Bouton (extends JButton) : un Bouton, permet à l'utilisateur de déclencher une évènement 
      * La classe Panneau (extends JPanel) : un Panneau avec un FlowLayout, pour placer des boutons et du texte à la suite
      * La classe Grille  (extends JPanel) : un Panneau avec un GridLayout, pour disposer des éléments en grille
      * La classe Texte   (extends JLabel) : un élément de texte, pour afficher une information
      
    -Le dossier "Controleur" qui gere les évènements déclenchés par l'utilisateur :
      * La classe Controleur (extends ActionListener) : modifie les données du modele et met à jour la fenetre selon l'évènement
      * L'enumeration ChangeParam : pour determiner le parametre a modifier (nombre de joueurs/nombre d'artefacts/...)
      
    -Le dossier "Images" qui contient divers images utilisées par l'interface graphique
      * (haut.png, bas.png, droite.png, gauche.png, assecheCentre.png, assecheHaut.png, assecheBas.png, assecheDroite.png,
      assecheGauche.png)
    
## Diagramme de classe :



## Les problemes non résolus :



## Inspiration :

    L'interface grpahique a été construite après plusieurs recherches sur Internet (pour comprendre le fonctionnement
de certains objets swing comme les JPanel et leurs Layout, les JButton pour creer des boutons, les JLabel pour mettre 
du texte, les ActionListener pour gere des évènements ou encore les Graphics/paintComponent pour dessiner sur un 
composant). Elle s'inspire en partie aussi des modeles vus en TD.
    
    
    
    
    
