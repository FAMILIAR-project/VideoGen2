# Instructions

## Technologies
### Web-side
- JHipster

### Server-side
- Java / Xtend
- MySQL

### Tests
- JUnit 4 

## Architecture
The Web-side of the application is contained inside the videogenapp folder whereas the server-side part is inside the videogentransformations folder.
```
jbsrs
│   README.md
│   Instructions.md
|   videogen-transformations-1.0.0.jar
│
└───videogenapp : the web-site of the project
│  
└───videogentransformations : the server-side of the project

```

### Web
```
videogenapp
│   README.md
│   mvnw
|   pom.xml
|   ...
│
└───src
│   │
│   └───main
│   |   │
|   │   └───docker
|   │   |   │  Dockerfile
|   │   |   │  app.yml
|   │   |   │  mysql.yml
|   │   |   │  sonar.yml
|   │   |  
|   │   └───java/fr/istic/m2il/idm/videogenapp
|   │   |   │   Test.java
|   │   |   
|   │   └───resources
|   │   |   │   ...
|   │   |   
|   │   └───webapp
|   │   |   │   ...
|   │
│   └───test
│   
└───data/input : data needed for the generator to run in the browser
    └───images
    |
    └───videogen
    |
    └───videos
        └───alternative
        |
        └───mandatory
        |
        └───optional

```
### Server-side
```
videogentransformations
│   pom.xml
│   build.properties  
│
└───src/fr/istic/m2il/idm/videogentransformations : all the xtend code for the application to run
│   │   VideoGenAppRunTests.xtend : allows to run the commands on the server-side
│   │
│   └───configs
│   |   │   VideoGenCongifs.xtend
│   │
│   └───helper
│   |   │   CSVHelper.xtend
│   |   │   FFMPEGHelper.xtend
│   |   │   ProcessHelper.xtend
│   |   │   VideoGenCheckerHelper.xtend
│   |   │   VideoGenHelper.xtend
│   │
│   └───transformations 
│   |   │   VideoGenAnalysisTransformations.xtend
│   |   │   VideoGenPlayTransformations.xtend
│   │
│   └───utils
│       │   CommonUtils.xtend
│       │   VideoGenUtils.xtend
│   
└───samples : all the sample specifications
|   │   sample1.videogen
|   │   ...
|
└───empiricalStudy : all info about the empirical study
    |   Etude Empirique.pdf : the empirical study (material, method, results) 
    |   plot_correlation.png
    |   result_pearson.png
    |   script_R_empirique.R
    |
    └───analyses
    |   analysis_*.csv
    |   ...
    └───specs
    |   sample_*.videogen
    |   ...
    └───videos

```

## Deployment
Instructions to deploy the project

### Creating the database
```
> mysql -u root -p
mysql> CREATE DATABASE videogenapp;
mysql> CREATE USER 'videogenappadmin'@'localhost' IDENTIFIED BY 'videogenappadmin';
mysql> GRANT ALL ON videogenapp.* TO 'videogenappadmin'@'localhost';
mysql> exit
```
### Setting the DataBase informations (Only if database infos are different than those indicated in the Creation of database section)

```videogenapp/src/main/resources/config/application-dev.yml```
- Set User and PassWord properties
- Set database name in url property or create a database named videogenapp

```videogenapp/pom.xml```
- Set database User/PassWord properties in <username></username>,<password></password> properties (liquibase, lines 524 & 525)

### Running Application : 
## FrontEnd : In videogenapp folder
```
npm install
rm yarn.lock
rm yarn-error.log
yarn install
yarn start
```

## Backend: In videogenapp folder
- Run ```mvn install:install-file -Dfile=../videogen-transformations-1.0.0.jar -DgroupId=fr.istic.m2il.idm -DartifactId=videogen -Dversion=1.0.0 -Dpackaging=jar```
- Run ```mvn spring-boot:run```


    
## Features
- Concatenate videos into a variant from a specification file
- Apply a filter to a video (via the specification file)
- Export variants as gifs
- Take into account the probabilities of alternative medias
- Take into account the durations of alternative medias
- Create a csv file with the informations of the variants

## Usage
```
java -jar videogen-transformations-1.0.0.jar command

command :
generer_playlist AbsoluteOutputFolder videogenspecificationfile
exporter_gifs AbsoluteOutputFolder videogenspecificationfile witdh heigth
tailles_variantes AbsoluteOutputFolder videogenspecificationfile (gif/video) ? gif_width gif_heigth
durees_variantes AbsoluteOutputFolder videogenspecificationfile 

```
/ : means that there is a choice to make.
(): means that it needs to be put with only the related option.

## Contest
The specification file for the contest is located : ```videogenapp/data/input/videogen/contest.videogen```

### Infos
Le projet fonctionnait puis il y a eu un problème lors de l'essai avec Docker. Un problème avec le war et les chemins pour trouver les vidéos.

## Authors
June Benvegnu-Sallou, Ramadan Soumaila
