# Instructions

## Technologies
- Docker
### Web-side
- JHipster

### Server-side
- Java / Xtend

### Tests
- JUnit 4 

## Architecture
The Web-side of the application is contained inside the videogenapp folder whereas the server-side part is inside the videogentransformations folder.
```
jbsrs
│   README.md
│   Instructions.md    
│
└───videogenapp
│  
└───videogentransformations

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
└───data/input
    │   file021.txt
    │   file022.txt
```
### Server-side
```
videogentransformations
│   pom.xml
│   build.properties  
│
└───src
│   │   sample1.videogen
│   │   ...
│   │
│   └───subfolder1
│       │   file111.txt
│       │   file112.txt
│       │   ...
│   
└───samples
|   │   sample1.videogen
|   │   ...
|
└───output
|   │   
|   └───filtered
|   |
|   └───gifs
|   |
|   └───playlists
|   |
|   └───resizes
|   |
|   └───thumbs
|
└───empiricalStudy
|   │   ...
|
```

## Deployment
Instructions to deploy the project

## Features
- Concatenate videos into a variant from a specification file
- Apply a filter to a video (via the specification file)
- Export variants as gifs
- Take into account the probabilities of alternative medias
- Take into account the durations of alternative medias
- Create a csv file with the informations of the variants

## Usage
```
jar -java xxx.jar command

command :

generer_playlist AbsoluteOutputFolder videogenspecificationfile
exporter_gifs AbsoluteOutputFolder videogenspecificationfile witdh heigth
tailles_variantes AbsoluteOutputFolder videogenspecificationfile (gif/video) ? gif_width gif_heigth
durees_variantes AbsoluteOutputFolder videogenspecificationfile 

```
/ : means that there is a choice to make.
(): means that it needs to be put with only the related option.

## Authors
June Benvegnu-Sallou, Ramadan Soumaila
