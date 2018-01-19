# VideoGen2
Yet another variant of a configurable generator of generator of video variants

# Ingénierie des modèles

## Générateur de vidéo

L'application est un générateur de vidéo permettant de prendre en entrée des vidéos, images ou GIF puis les concatène suivant certaines règles que l'utilisateur appliquera.

## Technologies

La grammaire ainsi que son modèle utilise xtext dans son environnement Eclipse.  

Le reste de l'application est écrite en Java.  

Le traitement et l'édition des vidéos se fait via FFMPEG.

## Architecture

Les règles permettant définir le traitement des vidéos en entrées sont définites au travers une grammaire xtext (VideoGen.xtext).  

Cette grammaire à l'aide d'Xtext permet de produire un modèle servant de base pour l'ensemble des objets Java sur les quelles l'application se base.  

L'utilisateur en entrée fournit un fichier .videogen et des vidéos, ficher qui sera parsé puis convertit en POJO qui permettra d'effectué les traitements à l'aide FFMPEG.

## Aperçu de l'étude
Par manque de temps, nous n'avons pu mener l'étude à terme. Nous avons tout de même pu constater quelques information notables.
Sur trois variantes générées a partir d'un même .videogen nous constatons que :

	1.La taille calculée ainsi que la taille réelle ne sont pas les même et en présentent pas de relation dans notre cas
	2.Appliquer un filtre negate (video jouée a l'envers) ne semble pas modifier la taille de la video générée.

## Autres
Par manque de temps nous n'avons pas packagé notre générateur ni de site web.
Nous ne fournisson pas non plus de quoi participer aux concours.
Un screencast d'execution est présent.
L'historique des commits n'est pas représentatif de la repartition réel du travail au sein du binome

