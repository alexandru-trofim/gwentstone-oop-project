

# GwentStone 


<div align="center"><img src="https://media.giphy.com/media/wZiD4vvJ1yIjKleNnK/giphy.gif" width="500px"></div>

## Overview

This project is a simplistic implementation of a collectible card game such as
HearthStone or Gwent. The realisation of this project helped me a lot in understanding
and consolidating the basic conceps of OOP design and Java fundamentals.

The game is played by two players. Every player has more deck of cards, and for each game
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

    
![alt text](./readme_img/Screenshot%202022-11-24%20at%2022.13.45.jpg)  




