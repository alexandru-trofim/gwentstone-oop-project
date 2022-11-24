

# GwentStone 


<div align="center"><img src="https://media.giphy.com/media/wZiD4vvJ1yIjKleNnK/giphy.gif" width="500px"></div>

## Overview

This project is a simplistic implementation of a collectible card game such as
HearthStone or Gwent. The realisation of this project helped me a lot in understanding
and consolidating the basic conceps of OOP design and Java fundamentals.

The game is played by two players. Every player has more decks of cards, and for each game
a player chooses a deck. Also, every player has a Hero. When the Hero of a player dies the 
game ends. The cards are placed on a game table where all the action takes place.

The game works like a back-end application. It takes the input as a JSON file and outputs the
result also in a JSON file.
## Project Structure

* src/
  * checker/ - checker files
  * fileio/ - contains classes used to read data from the json files
  * main/
    * Main - the Main class runs the checker on the implementation.
      Run Main to test the implementation from the IDE or from command line.
* input/ - contains the tests in JSON format
* ref/ - contains all reference output for the tests in JSON format
* cards/ - contains all the classes that implement the game cards
* debug/ - contains a class that has functions for debugging and printing the output
* game/ - contains the classes that implement the gameplay
  * play - contains the classes responsible for game actions
  * structure - contains the classes responsible for the structure of the game 
    and for parsing the commands and loading new games

## The organisation of classes   
## cards package

![alt text](./readme_img/Screenshot%202022-11-24%20at%2022.13.45.jpg)  

cards is the package where I used the most the inheritance and incapsulation concepts
### Card
Is an abstract class that has all the basic functionality of a card. All other classes inherit
the card properties because every other class in this package is basically a card. I made it 
abstract because we have no need to have Card objects.

### Minion
Is a class that extends the Card class. It is not an abstract class because we need to
have instances of the Minion class for basic cards like Sentinel, Berserker, Goliath and Warden.

### Special Minion Cards 
The classes Disciple, Miraj, TheRipper and TheCursedOne inherit all the properties and 
the abilities of the SpecialMinionCard but implement their own special attack
### Environment Cards 
This is an abstract class that extends the class Card and is needed to define the environment type
cards that has a different attack ability. The classes HeartHound, Winterfell and Firestorm extend
this class.
### Hero Cards
HeroCard class extend the EnvironmentCard Class and is a base for Hero Card classes which 
implement their own abilities.

## game package
The game package contains two other packages. 

### Deck 
The deck class basically keeps an array of cards.
### Player
This class implements the player with all the necessary attributes like Decks, Player Hand, 
Player Hero, mana and an internal property madeMove that helps the game keep track if the 
player made his move on the current round.
### GameStart
This class contains all the required info to start a game like the heroes of the players, 
the starting player 



