For all these instructions make sure to have a recent ffmpeg command configured in your path or the Videogen will not work correctly.


## How to run VideoGen IDE:
- The compiler and all assets required for Generating an Eclipse IDE can be found in the directory antoineC.

- However there is no runnable jar to test the Videogen Grammar IDE the only way is to build an Eclipse Project from antoineC folder (make sure to exclude antoineC/VideoGenWeb/* folder from build path.

- The compiler can be found in antoineC/VideoGenWeb/lib/videogen_compiler.jar and can be called from two ways:

```java -jar videogen_compiler.jar <INFOS|COMPILE> <videogen filename>```

The first argument for videogen compiler is to define what to do: Either generate a variant of video, or generate a csv that list all variantes with their durations and sizes.



## How to run VideoGenWeb server:

maven can't run it because VideoGenWeb server required a local jar as a dependency, the videogen compiler jar file.

There is no known way to start the server than in dev mode with the help of an IDE like IntelliJ.

The web server is very young and doesn't have other features than extracting generated video files or generated variantes informations files.


## Other informations
The project has suffer from a bad start at developping it in a Windows Environment and finish the development in a Linux OS. The problem was that FFMPEG commands are slightly different from Windows to Linux.

During the development of the compiler, almost all the development was using only the generated metamodel API, but when we have to deal with probability for each videoseq, we were obliged to use the Ecore API to check if the probability of a mediaseq was set to 0 or was not set at all. 
In fact the generated metamodel API doesn't have the information to differentiate probability set to 0 and probability not set at all.


## Rapport empirique sur la taille des vidéos:

- Trop peu d'essai ont était réalisé afin de construire un rapport complet.    
- Ce compilateur utilise FFMPEG pour assembler les vidéos, FFMPEG nécéssite que tout les morceaux composants la vidéos finale possède les mêmes caractéristiques en terme d'encodage et de résolutions notamment. En règle général si les morceaux de vidéos sont de résolutions différentes, on peut dire que la vidéo généré sera plus petites que la somme des tailles des vidéos qui la compose.
- A savoir que les images sont transformer en vidéo mp4 de 3 secondes. 
