
# VideoGEN
Notre programme principale est le main de la class Main.java, nous avons pu implémenter les fonctionnalités demandées, le programme lui meme contient des examples de commands sous forme de commentaires. Exemple: 
```java 
case "GENERATE_CSV_FILE": // example of input: GENERATE_CSV_FILE example2 
```

# Organisation
- Le dossier 'variante' contient un exemple de variante obtenue par notre generateur
- Le dossier generatedVideos comme son nom l'indique contient les videos generés qu'ils soit intermédiaires ou finales
- Le dossier videos contient les vidéos utilisées pour les tests
- Le dossier textFiles contient les fichiers playLists générés (txt) les fichiers CSV, les pages html et tout autre fichier text généré
- Le package transformation contient les differentes classes permettant la transformation de notre grammaire
- Le package util contients les fonctionalités nessaisaires pour les transforamtions (ex: filtres)
- La class Main.java est notre programme principale
- La classe Test.xtend permet le tests de certaines fonctionalités


# Utilisation

Un problème de compilation nous a empêché de lancer le programme depuis un terminal, donc nous passons par eclipse pour l’exécution.
'run Main.java', pour les arguments nous utilisant un Scanner(System.in)
pour une utilisation plus libre voici quelques instructions:

## Validation

Pour  valider un fichier '.videogen' on passe par le main ou directement en appelant validator(videoGen)  de la classe Utils

## Clean

Il est important de supprimer (ou déplacer) les fichiers générés avant de relancer la generation, donc la fonction cleanTestFiles() de la class Utils fait le nettoyage

## Generation de fichier text playlist

Les playlists sont generer par la fonction generatePlayList(videoGen) de la class Ffmpeg.java

## Concatination des vidéos d'une playlist

La concatination se fait avec la fonction concat_and_play() de la classe Utils, qui comme son nom l'indique lance la video resultante aussi (require vlc)

## Export vers Gif

Pour exporter une video vers gif, il y a la fonction videoToGif() de la class VideoToGif.xtend, il est possible aussi de créer pour une spécification, une gif par video choisie, avec la fonction modelToGifs() de la meme classe


## Generer la playList de la plus long duree
Pour cela on utilise la transformation toLongestPlayList() de la classe LongestVariant.java

## Generer la page html avec les vignettes
La transformation toHtml() de la classe Html.java permet assembler une page html avec les recommandations du TP

## Generation du CSV
Pour l'analyse sur les tailles de toutes les variantes possibles pour un model, la fonction getAllVariants() de la classe Etude.java permet de creer le fichier CSV contenant les informations nécessaires
