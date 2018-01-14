# IDM Project
ISTIC RENNES - FR    
Quentin Dubois    
2017-2018

# Introduction

Ce projet est un générateur de vidéo basé sur la librairie XTEND (DSL).   

Technologie utilisées : 
-	NodeJS  (Application web / UI)
-	JAVA  pour la partie génération de vidéo aléatoire
-  FFMPEG pour la concaténation de vidéo  


Le projet est contenu dans le dossier DuboisQ, le reste des dossiers sont les fichiers necessaire a XTEND ainsi qu'a sa configuration.

**DuboisQ/WebAp**    
Application Web contenant serveur NodeJS, Runnable Jar (provenant de XtendProject), vidéos sources.          
    
**DuboisQ/XtendProject**    
Projet Xtend pour la prise en charge des .VideoGen

# Fonctionnalitées

A partir d'une page web, il est possible d'appuyer sur un bouton "générer" qui envoie une requête HTTP au server nodeJS.     
A la reception de cette requête, le serveur appelle IDM.jar    
IDM.jar ce base sur un fichier .videogen ainsi qu'un dossier de video source afin de réaliser un fichier texte contenant la référence des vidéos a utilisé dans cette nouvelle génération.     
Ce fichier texte va permettre d'utiliser FFMPEG, pour qu'il concataine tout les vidéo référencé dans ce fichier text.    
Une fois la vidéo crée, nodeJS repond avec le chemin associé a cette vidéo.     
Le browser n'a plus qu'a récupérer cette ressource et la jouer.     
     
   
un système de vérification est réaliser par nodeJS afin de supprimer les vidéo qui date de plus de X minutes permettant de garder un dossier relativement léger.

# Installation

clone le repo
cd DuboisQ/Webapp
npm start

# Utilisation


# Conclusion


