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


```mermaid
graph LR
A((Browser)) -- 1 --> B((NodeJS))
B --2--> D((JAVA))
D --3--> E((FFMPEG))
E--4--> D
D --5--> B
B --6--> A
```

Le projet est contenu dans le dossier DuboisQ, le reste des dossiers sont les fichiers necessaire a XTEND ainsi qu'a sa configuration.

**DuboisQ/WebAp**    
Application Web contenant serveur NodeJS, Runnable Jar (provenant de XtendProject), vidéos sources.          
    
**DuboisQ/XtendProject**    
Projet Xtend pour la prise en charge des .VideoGen

# Installation

clone le repo
cd DuboisQ/Webapp
npm start

# Utilisation


# Conclusion


