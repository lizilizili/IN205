

# ReadMe

### Author: Qingyao Wang
## Exécution
```cd ./bataille-navale ```
```mvn clean install exec:java```


## Exercice 1: Affichage du « board »


### Classe Board

La classe « board » est créé dans cette exercice, dont il y a :
-	 les attributs
-- un nom de String (privé)
--	un tableau 2D de char pour représenter les navires (privé)
--	un tableau 2D de boolean pour représenter les frappes (privé)
-	les constructeurs
--	```public Board(String name, int size);```
--	```public Board(String name);``` (size=10)
-	et sont méthodes
--	```public int getSize() ;```
--	```public String getName(); ```(Encapsulation)
--	```public void print();```

### Résultat d’un affichage

Voici un exemple de print() d’un board de taille=10 ;

![](https://ftp.bmp.ovh/imgs/2021/03/aa0fda4c899b3333.png)

## Exercice 2 : Création des classes de navires
### Classe AbstractShip
La classe « AbstractShip » est créé dans cette exercice, dont il y a :
-	les attributs
--	un label de char (privé)
--	un nom de String (privé)
--	un size de int(prive)
--	une direction de enum Directions(privé)
-	les constructeurs
--	```public AbstractShip(String name, char label, int size, Directions d);```
--	```public AbstractShip(String name, char label, int size);```(direction=Directions.EAST)
-	et sont méthodes
--	```public char getLabel() ;``` (Encapsulation)
--	```public String getName() ;``` (Encapsulation)
--	```public int getLength() ;``` (Encapsulation)
--	```public Directions getDirection() ;``` (Encapsulation)
--	```public void setDirection(Directions direction) ;``` (Encapsulation)

### Classe filles
Il y a 4 calsses filles de la classe « AbstractShip », elles sont :
-	classe « Destroyer» (length=2,label=’D’)
-	classe « Submarine» (length=3,label=’S’)
-	classe « Battleship» (length=4,label=’B’)
-	classe « Carrier» (length=5,label=’C’)

### Représentation des directions

On a ```enum Directions { NORTH,SOUTH,WEST,EAST }```, pour mieux présenter les directions, on utilise un méthode pour convertir un direction a un vecteur correspondant ```{(1,0),(-1,0),(0,-1),(0,1)}```
on rajouter dans le classe « AbstractShip » :
-	public int[] dir2vertor() ;

### Sous-package
Pour mieux récupérer les fichiers, on utilise les sous package pour repartir les ficher, par exemple, on mettre tous les fichiers ce cet exercice dans un dossier « ships »
et pour les autres fichiers il faut

```import ensta.ships.* ;```
  
## Exercice 3 : Placement des navires

### Interface « IBoard »
Pour utiliser l’interface « IBoard » il faut rajouter dans la classe « Board » les méthodes :
-	```int getSize();```
-	```void putShip(AbstractShip ship, int x, int y);```
-	```boolean hasShip(int x, int y);```
-	```void setHit(boolean hit, int x, int y);```
-	```Boolean getHit(int x, int y);```
-	```Hit sendHit(int x, int y);```

### Cas le navire sort la grille
dans la fonction putShip, si le navires sort de la grille on utilise
```throw new IllegalArgumentException("Cas le navire sort de la grille! ")```
### Cas deux navires se chevauchent
dans la fonction putShip, si this.hasShip(position) est vrai on utilise
		```throw new IllegalArgumentException("Deux navires chevauchent! ");```

### Implémentation  dans TestBoard
On utilise 
	```try {
			board.putShip(ship,position);
	}catch (Exception e) {
			System.out.println(e);
	}```

### Résultat du Placement des navires
![](https://ftp.bmp.ovh/imgs/2021/03/052234c446ed5bb6.png)
## Exercice 4 : Entrées utilisateur
On utilise le classe InputHelper pour récupérer les entrées, et comme les directions sont présentées en lettre, on utilise un ```switch…case``` pour les convertir a un variable de ```enum Directions``` 

## Exercice 5 : État des navires et des frappes

### Classe ShipState
Pour présenter l’état de chaque navire, on crée la classe ShipState dont il y a :
-	les attributs
--	un navire de AbstractShip
--	un etat « truck » de boolean
-	les méthodes
--	void addStrike();
--	boolean isStruck();
--	String toString(); (le label du navire) ;
--	boolean isSunk();
--	AbstractShip getShip();

Pour utiliser cette classe on doit faire des modifications des autres classes
-	Dans AbstractShip, on cree un attribut entier strikeCount ainsi qu'une méthode addStrike() permettant de manipuler le nombre de frappes que le navire a reçu au total, et  la méthode isSunk();
-	Dans Board on change le tableau de Character ships en un tableau de ShipState.

### Cas frappe sur une position déjà frappée
Dans la fonction void setHit(hit,position) ; si ```this.getHit(position) !=null```, on utilise :
	```throw new IllegalArgumentException("Cette position est deja touche!");```

### Résultat des frappes
![](https://ftp.bmp.ovh/imgs/2021/03/2f90bb738651489a.png)

## Exercice 6 : Envoyer des frappes

### Méthode sendHit
Pour réaliser une réaction d’une frappe qui vient de l’adversaire, on rajoute dans la classe IBoard et la classe Board une méthode Hit sendHit(int x, int y);
-	Dans le cas où il n’y pas de touche on met ```return (Hit.MISS)```
-	Dans le cas où il y a une touche mais pas sunk, on met ```return (Hit.STIKE)```
-	Dans le cas ou il y a un sunk d’une navire on retourne le nom de navire

### Cas frappe sur une position déjà frappe
Dans la fonction ```void sendHit(position) ;``` on appelle ```this.ships[position].addStrike() ;```


### Résultat des renvoie de frappes

![](https://ftp.bmp.ovh/imgs/2021/03/18d73eb44e464ad6.png)

## Exercice 7 : Intelligence artificielle

### classe BattleShipAI
Pour réaliser un putship() automatique, on fait pour chaque navire : 
```    
           do {
            	// use Random to pick a random x, y & orientation
            	x=rnd.nextInt(size-1);
            	y=rnd.nextInt(size-1);
            	o=orientations[rnd.nextInt(3)];
            	s.setDirection(o);  	
            	
            } while(!canPutShip(s, y, x));          
            board.putShip(s, x, y);
```
### Résultat de AI
	le résultat s’affiche quand on fait un test.

## Exercice 8 : Place au jeu
### Le jeu est planifié comme :
-	Afficher le nom du joueur 1 ainsi que son Board ;
-	Saisir les coordonnées de la frappe ;
-	Ré-afficher le Board ;
-	Afficher les coordonnées du hit et son résultat ;
-	Recevoir la frappe de l’adversaire et réafficher le Board ;
-	Afficher les coordonnées du hit et son résultat.

### Résultats du jeu
voir en utiliser:
	```cd ./bataille-navale ```
```mvn clean install exec:java```

