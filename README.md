# Projet IDM 2017-2018
*Générateur de vidéos - M2 ILA*

**Nathan Wanono - Mark Hervagault**


## SUM UP : 

Ce projet consiste à proposer une solution logicielle pour déployer un générateur de vidéos à partir d'une spécification textuelle (VideoGen). 
Les 3 parties de ce projet sont :

* **Le générateur** qui permet de prendre un fichier .videogen spécifiant le déroulement d'une vidéo et de la générer
* **Le site web** interface Homme-Machine permettant de visualiser des vidéos générées en fonction de la spécification du .videogen
* **Une étude empirique** étude sur la taille des vidéos

## Comment lancer le générateur : 

Dans un premier temps, il faut spécifier la génération en suivant l'exemple du template.videogen présent dans le dossier /WebApp/public

Ensuite il faut installer le serveur et le lancer:
* Allez dans /WebApp
* npm install
* npm start

Une fois que le serveur est lancé, l'application est disponible sur localhost:3000


## Fonctionnement du générateur :

Le générateur va suivre la spécification textuelle (template.videogen) pour générer une vidéo unique.
Une suite de vidéos sera générée et ffmepg se chargera de concaténer cette suite et de produire une vidéo au format mp4.

## Ajout de vidéos :

Il est possible d'ajouter autant de vidéos que vous le souhaitez dans le dossier /WebApp/public/OriginVideo/
FFmpeg est exigeant sur le format des vidéos, privilégiez ces caractéristiques:
encapsulation : MP4/MOV
Codec vidéo : H-264 1008kbit/s 25ips
Resolution 1280x720 ou 864x480
Codec Audio : MPEG 4 Audio (AAC) 128 kbit/s  2 canaux 44100hz
