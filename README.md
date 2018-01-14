# IDM Project
ISTIC RENNES - FR        
Quentin Dubois       
2017-2018      

# Site de démonstration

Un projet qui m'a rendu chèvre :D       
Funny Goat Generator      
http://51.15.202.124:3000

# Introduction

Ce projet est un générateur de vidéo basé sur la librairie XTEND (DSL).   

Technologie utilisées :
-	NodeJS  (Application web / UI)
-	JAVA  pour la partie génération de vidéo aléatoire
- FFMPEG pour la concaténation de vidéo  


Le projet est contenu dans le dossier DuboisQ, le reste des dossiers sont les fichiers necessaire a XTEND ainsi qu'a sa configuration.

**DuboisQ/WebAp**    
Application Web contenant serveur NodeJS, Runnable Jar (provenant de XtendProject), vidéos sources.          

**DuboisQ/XtendProject**    
Projet Xtend pour la prise en charge des .VideoGen

# Fonctionnalitées

A partir d'une page web, il est possible d'appuyer sur un bouton "generate" qui envoie une requête HTTP au serveur nodeJS.     
A la réception de cette requête, le serveur appelle IDM.jar    
IDM.jar ce base sur un fichier .videogen ainsi qu'un dossier de vidéo source afin de réaliser un fichier texte contenant la référence des vidéos a utiliser dans cette nouvelle génération.     
Ce fichier texte va permettre d'utiliser FFMPEG, pour qu'il "merge" toutes les vidéos référencées dans ce fichier texte.    
Une fois la vidéo crée, nodeJS répond avec le chemin associé a cette vidéo.     
Le browser n'a plus qu'a récupérer cette ressource et la jouer.     


un système de vérification est réalisé par nodeJS afin de supprimer les vidéos qui date de plus de X minutes, ce permettant de garder un dossier de vidéo généré relativement léger.

# Installation

Dépendances:
`sudo apt-get update`
`sudo apt-get install git nodejs-legacy npm default-jre`

Installation:
`git clone https://github.com/DuboisQ/video_generator_idm_2017.git`
`cd video_generator_idm_2017/DuboisQ/WebApp`
`npm install`

# Utilisation

Démarrage du serveur:
`npm start`

Accès au serveur localhost:3000

modification des vidéo utilisées:
- Ajouter des vidéos au dossier WebApp/public/OriginVideo
- Modifier template.videogen (WebApp/public)

#Autre

La concaténation de vidéo via FFMPEF (et la commande utilisé dans le JAR) requiert d'utiliser des vidéos similaire en terme de structure.
Je vous recommande donc d'utiliser des vidéos ayants:
- Même format conteneur (ISO MP4/M4A)
- Même résolution (720p)
- Même codec vidéo (H.264 25/30 fps)
- Même codec audio (MPEG-4 AAC)

# Conclusion
