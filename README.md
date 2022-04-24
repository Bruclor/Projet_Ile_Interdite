# Projet Île Interdite
Projet LDD2 IM POGL 
 - Alexandre LORET 
 - Lorenzo BRUCATO

## Lancement : 
    Executer le main() situé dans la classe controleur

## Les parties traitées :

__Notre application est une version simplifiée du jeu "l'ile interdite" et prend en charge les fonctionnalités suivantes :__
    
    - Le jeu peut se jouer de 1 à 4 joueurs, parametre saisi par l'utilisateur
    
    - Le jeu place sur le plateau deux artefacts de chaque type par defaut, parametre qui peut être modifié par l'utilisateur
    
    - Le jeu gere son initialisation en disposant les joueurs et les artefacts sur les zones disponibles de manière aléatoire.
    Un héliport est aussi placé aléatoirement sur le plateau
    
    - Le jeu gere les trois actions definies dans le jeu : "Se deplacer", "Recuperer un artefact" et "Assecher une zone"
    
    - Le jeu gere les évènements d'inondations des zones (lors de la fin d'un tour ou pour l'évènement "montée des eaux" 
    declenché lors d'une recherche de clé/recuperation d'un objet)
    
    - Le jeu gere la recherche de clé/recuperation d'objet et la fin d'un tour d'un joueur.  
    
    - Le jeu gere la defaite. Il affiche un "GAME OVER" dans l'une des trois conditions suivantes : Un joueur se noie car il ne 
    peut plus se "rattraper"/ Un artefact toujours pas récupéré n'est plus disponible (tous ses exemplaires ont été submergés)
    / L'Héliport est submergé.
    
    - Le jeu gere le "rattrapage" d'un joueur : ci celui-ci se trouve sur une case submergee, il se positionne sur 
    l'une des cases adjacentes si certaines sont disponibles. Il est éliminé si aucune case n'est disponible (GAME OVER)
    
    - Le jeu gere la victoire. Il affiche "VICTOIRE" lorsque tous les joueurs sont réunis à l'héliport et que tous les artefacts 
    ont été récupérés. Il vérifie cette condition notamment apres le deplacement d'un joueur/l'utilisation d'un hélicoptere
    /le rattrapage d'un joueur.
    
    - Le jeu gere l'affichage de l'inventaire de l'équipe afin que les utilisateurs puissent connaitre le nombre 
    d'actions encore disponibles, le joueur à qui c'est le tour, les objets qu'ils ont récupérés ainsi que les artefacts 
    récupérés
    
    - L'équipe peut à tout moment arreter la partie pour changer les parametres/ relancer une nouvelle partie/ quitter.
    
    
__Le jeu prend egalement en charge certaines extensions du sujet :__
    
    - Le jeu prend en charge les actions speciales, parametre qui peut être activé par l'utilisateur
    
    - Le jeu demande l'utilisation d'une clé pour obtenir un artefact par défaut, parametre qui peut être modifié et être monté
    jusqu'à 4 (pour correspondre aux regles classiques)
    
    - L'action speciale "Utiliser un helico" ne compte pas comme une action. Il permet a un joueur de rejoindre 
    n'importe quelle case disponible quand un joueur utilise un objet "helico"
    
    - L'action speciale "Sac de sable" ne compte pas comme une action. Il permet a un joueur d'assecher n'importe 
    quelle case inondee quand un joueur utilise un objet "sable"
    
    - L'action speciale "Donner une clé" compte comme une action. Il permet à un joueur de donner une de ses clés 
    à un joueur situé sur sa case.
    
    - Le tirage des zones inondees à la fin d'un tour et le tirage d'un objet est une simulation de la pioche 
    d'un paquet de carte. La carte objet recupérée par un joueur en fin de tour est affichée.
    
    
    
 ## Les parties non traitées (pour le moment) :
    - La mise en place des personnages particuliers ("Navigateur", "Explorateur", ...)
    
## Architecture :
    Les fichiers sources sont disposés selon le modele de Conway (Modeles-Vue-Controleur)
    Le main() qui lance l'application est situé dans la classe controleur.
    
    -Le dossier "Modeles" contient tous les objets correspondant au probleme et qui contiennent les données relatives à celui-ci :
      * La classe Coord                 : représentation des coordonnées d'une grille
      * La classe Joueur                : représentation d'un joueur 
      * La classe Zone (extends JPanel) : représentation d'une zone de la grille 
      * La classe Ile (extends Grille)  : représentation de l'ile
      * La classe Paquet<T>             : classe générique representant un paquet de carte d'éléments T 
      * L'enumeration Artefact          : représentaion des artefacts présent sur une zone (Eau, Feu, Terre, Air, Vide, Heliport)
      * L'enumeration Etat              : représentation de l'état d'une zone (Normale, Inondee, Submergee)
      * L'enumeration Objet             : représentation des objets pouvant être récupérés par un joueur
      
    -Le dossier "Vue" contient les fichiers utilisés pour la mise en place de l'interface graphique :
      * La classe Fenetre (extends JFrame) : la fenetre de l'interface graphique, composés de boutons, de panneaux, ...
      * La classe Bouton (extends JButton) : un Bouton, permet à l'utilisateur de déclencher une évènement 
      * La classe Panneau (extends JPanel) : un Panneau avec un FlowLayout, pour placer des boutons et du texte à la suite
      * La classe Grille  (extends JPanel) : un Panneau avec un GridLayout, pour disposer des éléments en grille
      * La classe Texte   (extends JLabel) : un élément de texte, pour afficher une information
      
    -Le dossier "Controleur" qui gere les évènements déclenchés par l'utilisateur :
      * La classe Controleur (extends ActionListener) : modifie les données du modele et met à jour la fenetre selon l'évènement
      
    -Le dossier "Images" qui contient divers images utilisées par l'interface graphique
      * (haut.png, bas.png, droite.png, gauche.png, assecheCentre.png, assecheHaut.png, assecheBas.png, assecheDroite.png,
      assecheGauche.png, air.png, eau,.png, feu.png, terre.png)
    
## Diagramme de classe :


## Les problemes non résolus :
Trouver une façon plus "propre" pour gérer les évènements dans la classe controleur 
(au lieu d'utiliser un enchainement de tests de conditions)


## Inspiration :
    
L'interface graphique a été construite après plusieurs recherches sur Internet pour comprendre le fonctionnement de certains objets swing 
comme les JPanel et leurs Layout, les JButton pour creer des boutons, les JLabel pour mettre du texte, les ActionListener 
pour gerer des évènements ou encore les Graphics/paintComponent pour dessiner sur un 
composant. 
Elle s'inspire en partie aussi des modeles vus en TD. Certaines images (les images représentants les artefacts Air, Eau, Feu, Terre) 
ont été prises sur internet. Les autres ont été créés.
    
    
    
    
    
