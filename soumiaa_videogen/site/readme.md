Pour ouvrir le site : 
cd Generator-Website
 mvn spring-boot:run

ou bien ouvrir le projet dans eclipse et lancer la class app

port 8080

----
en cas de soucis de dépendances : 
 mvn install:install-file -Dfile=/home/aminesoumiaa/workspace/idm_project/libs/Generator.jar -DgroupId=fr.ila.idm -DartifactId=genvideo-gen -Dversion=1.0 -Dpackaging=jar

mvn install:install-file -Dfile=/home/aminesoumiaa/workspace/idm_project/libs/Model.jar -DgroupId=fr.ila.idm -DartifactId=genvideo-model -Dversion=1.0 -Dpackaging=jar
