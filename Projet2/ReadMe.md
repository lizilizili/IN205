

# ReadMe

### Author: Qingyao Wang
## Exécution

```mvn clean install exec:java```
```mvn clean install tomcat7.run```


## Exercice 2:  La représentation des données


### Modèle

J’ai créé trois classes pour réaliser ce projet:
- Emprunt.java
- Livre.java
- Membre.java

Et puis je les ai mis dans le dossier  « librarymanager\modele».



### Résultat d'un test

Voici un exemple de la création des modèles

![](https://ftp.bmp.ovh/imgs/2021/04/dde28cf14cacb420.png)

## Exercice 3 :  L’accès aux données
### Dao
J‘ai mis les trois ficher d’interface des dao dans le dossier « librarymanager\dao».
Et puis j’ai créé les trois Implémentation correspondants :
- EmpruntDaoImpl.java
- LivreDaoImpl.java
- MembreDaoImpl.java

### Résultat d'un testDao

Voici un exemple de la fonctionnement dans dao.

![](https://ftp.bmp.ovh/imgs/2021/04/fe6cc219b0b37409.png)


  
## Exercice 4 :  Manipulation des données par les services

### Service
De même J‘ai mis les trois ficher d’interface des dao dans le dossier « librarymanager\service».
Et puis j’ai créé les trois Implémentation correspondants :
- EmpruntServiceImpl.java
- LivreServiceImpl.java
- MembreServiceImpl.java

### Résultat d'un testService

Voici un exemple pour les nouvelles fonctionnements.
![](https://ftp.bmp.ovh/imgs/2021/04/a998f58f2767716f.png)
![](https://ftp.bmp.ovh/imgs/2021/04/1cc320ceac5c6d48.png)


