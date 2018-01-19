# Instructions

## Technologies
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
│   Instructions.md    
│
└───videogenapp
│   │   file011.txt
│   │
│   └───subfolder1
│       │   file111.txt
│       │   file112.txt
│       │   ...
│   
└───videogentransformations
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

## Authors
June Benvegnu-Sallou, Ramadan Soumaila
