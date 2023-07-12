![JavaUtils](images/javaORM.png)

# javaORM - Bibliothèque Java pour les structures de données
javaORM est une bibliothèque Java qui permet de créer de manière automatisée des classes de modèle pour faciliter l'accès aux données au sein d'une application (ORM).

## Structures

*Ce projet a été réalisé dans le cadre de l'apprentissage des concepts avancés de la programmation orientée objet en Java.*
## Fonctionnalités


## Utilisation


```bash
git clone https://github.com/saoudi-h/javaORM.git
cd javaORM
 
```

## Tests
Ce projet utilise JUnit pour les tests unitaires. Les tests sont situés dans le répertoire src/test/java. Vous pouvez exécuter les tests à l'aide de la commande suivante :

```bash
mvn test
```

## Javadoc
Ce projet a été documenter en français. Vous pouvez génèrer la doc avec la commande suivante :

```bash
mvn javadoc:javadoc
```
Le plugin Maven Javadoc générera la documentation dans le dossier `target/site/apidocs/`
## Génération du fichier JAR exécutable

Ce projet est configuré avec le plugin Maven maven-jar-plugin pour générer un fichier JAR exécutable. Vous pouvez générer le JAR en exécutant la commande suivante :
```bash
mvn verify
```
Le fichier JAR exécutable sera généré dans le répertoire target, situé a la racine du projet.
```bash
cd target
java -jar javaUtils-1.0-SNAPSHOT.jar
```

Utilisez le plugin exec-maven-plugin pour exécuter la classe principale du projet.
```shell
mvn exec:java

```
## Licence

Ce miniprojet est sous licence [MIT](https://opensource.org/licenses/MIT).

## Contributeurs

![Développé par Hakim Saoudi dans le cadre de sa formation de concepteur développeur d'application.
](images/hakimsaoudi_javaORM.png)