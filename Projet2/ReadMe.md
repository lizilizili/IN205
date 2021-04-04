

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
Et puis j’ai créé les trois Implémentations correspondantes :
- EmpruntDaoImpl.java
- LivreDaoImpl.java
- MembreDaoImpl.java

### Date et LocaoDate 

Dans le SQL, on utilise le getDate(String) pour récupérer les dateEmprunts et les dateRetours
Il faut utiliser la fonction : toLocalDate() pourque les données correspondent bien le type de date dans la classe Emprunt

### Résultat d'un testDao

Voici un exemple de la fonctionnement dans dao.

![](https://ftp.bmp.ovh/imgs/2021/04/fe6cc219b0b37409.png)


  
## Exercice 4 :  Manipulation des données par les services

### Service
De même J‘ai mis les trois ficher d’interface des services dans le dossier « librarymanager\service».
Et puis j’ai créé les trois Implémentations correspondantes :
- EmpruntServiceImpl.java
- LivreServiceImpl.java
- MembreServiceImpl.java

### Résultat d'un testService

Voici un exemple pour les nouvelles fonctionnements.
![](https://ftp.bmp.ovh/imgs/2021/04/a998f58f2767716f.png)
![](https://ftp.bmp.ovh/imgs/2021/04/1cc320ceac5c6d48.png)

## Exercice 5 : Interface utilisateur

### Ce que j’ai réalisé
-	L’affichage la liste des livres/membres/emprunts-all/emprunt-courent
-	Le saut aux détails des livres/membres/emprunts
-	Le compte des nombres des livres/membres/emprunts
-	Un emprunt d’un livre disponible par un membre possible

### Ce que j’ai essayé

-	L’ajoute d’un livre (Même s’il n’y a rien rempli comme paramètre, un livre est créé automatiquement)
-	L’ajoute d’un membre (Même problème)
### Ce que je n’ai pas d’idée
-	Une option préremplie.


