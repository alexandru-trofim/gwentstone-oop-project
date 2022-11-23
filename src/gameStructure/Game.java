package gameStructure;

import cards.Card;
import cards.EnvironmentCard;
import com.fasterxml.jackson.databind.node.ArrayNode;
import debug.Debug;
import fileio.Coordinates;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game {
    private @Getter @Setter Player playerOne;
    private @Getter @Setter Player playerTwo;
    private @Getter @Setter ArrayList<GameSession> games;

    private @Getter @Setter Card[][]  gameTable;
    private @Getter @Setter int playerToMove;
    private @Getter @Setter int currentRound;

    public Game(Player playerOne, Player playerTwo, ArrayList<GameSession> games) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.games = games;
    }


    //aici implementez functiile legate de executarea actiunilor;
    public void resetCardMadeMove() {
        int numberOfRows = 4;
        int numberOfColumns = 5;

        for (int i = 0; i < numberOfRows; ++i) {
            for (int j = 0; j < numberOfRows; ++j) {
                if (gameTable[i][j] != null) {
                    gameTable[i][j].setMadeMove(false);
                }
            }
        }
    }

    public void makeCardsUnfrozen() {
        int numberOfRows = 4;
        int numberOfColumns = 5;
        int start, end;
        if (playerToMove == 1) {
            start = 2;
            end = 3;
        } else {
            start = 0;
            end = 1;
        }
        for (int i = start; i <= end; ++i) {
            for (int j = 0; j < numberOfColumns; ++j) {
                if (gameTable[i][j] != null) {
                    gameTable[i][j].setFrozen(false);
                }
            }
        }
    }
    public void endPlayerTurn() {
        makeCardsUnfrozen();
        //if the current player ended his turn and so did the other player
        //then we end the round
        if((playerToMove == 1 && playerTwo.getMadeMove() == 1) ||
                (playerToMove == 2 && playerOne.getMadeMove() == 1)) {
            //end round


            playerOne.getCardFromDeck();
            playerTwo.getCardFromDeck();

            playerOne.setMadeMove(0);
            playerTwo.setMadeMove(0);

            //set All cards property Made move to false
            resetCardMadeMove();

            currentRound += 1;

            int addMana;
            if (currentRound <= 10)
                addMana = currentRound;
            else
                addMana = 10;

            playerOne.setMana(playerOne.getMana() + addMana);
            playerTwo.setMana(playerTwo.getMana() + addMana);

            if (playerToMove == 1)
                playerToMove = 2;
            else
                playerToMove = 1;

        } else {
            if (playerToMove == 1) {
                playerOne.setMadeMove(1);
                playerToMove = 2;
            } else {
                playerTwo.setMadeMove(1);
                playerToMove = 1;
            }
        }

    }



    public void loadCurrentSession(GameSession session) {
        GameStart gameStart = session.getGameStart();
        //initialize new hands for each hand
        this.playerOne.getNewHand();
        this.playerTwo.getNewHand();

        this.playerOne.initializeActiveDeck(gameStart.getPlayerOneDeckId());
        this.playerTwo.initializeActiveDeck(gameStart.getPlayerTwoDeckId());

        //shuffle the decks
        Random rand1 = new Random(gameStart.getShuffleSeed());
        Collections.shuffle(this.getPlayerOne().getActiveDeck().getCards(),rand1);

        Random rand2 = new Random(gameStart.getShuffleSeed());
        Collections.shuffle(this.getPlayerTwo().getActiveDeck().getCards(),rand2);

        //initialize player Heroes
        this.playerOne.setPlayerHero(gameStart.getPlayerOneHero());
        this.playerTwo.setPlayerHero(gameStart.getPlayerTwoHero());

        //prepare the game table
        this.gameTable = new Card[4][5];

        //set Player to movetest05_use_environment_card_invalid.jsonf
        this.playerToMove = gameStart.getStartingPlayer();

        //get one card from deck for each player
        this.playerOne.getCardFromDeck();
        this.playerTwo.getCardFromDeck();


        //set mana for players
        this.playerOne.setMana(1);
        this.playerTwo.setMana(1);

        //set current round
        this.currentRound = 1;

    }

    public void executeActions(GameSession session, ArrayNode output) {
        ArrayList<Action> actions = session.getActions();

        for(Action currentAction: actions) {
            System.out.printf(currentAction.getCommand() + "\n");
            //check which action we have and execute it
            switch (currentAction.getCommand()) {
                case "getPlayerDeck":
                    if (currentAction.getPlayerIdx() == 1)
                        Debug.getPlayerDeck(playerOne.getActiveDeck(), output, 1);
                    else
                        Debug.getPlayerDeck(playerTwo.getActiveDeck(), output, 2);
                    break;
                case "getPlayerHero":
                    if (currentAction.getPlayerIdx() == 1)
                        Debug.getPlayerHero(playerOne.getPlayerHero(), output, 1);
                    else
                        Debug.getPlayerHero(playerTwo.getPlayerHero(), output, 2);
                    break;
                case "getPlayerTurn":
                    Debug.getPlayerTurn(playerToMove, output);
                    break;
                case "getPlayerMana":
                    if (currentAction.getPlayerIdx() == 1)
                        Debug.getPlayerMana(playerOne.getMana(), output, 1);
                    else
                        Debug.getPlayerMana(playerTwo.getMana(), output, 2);
                    break;
                case "getCardsOnTable":
                    Debug.getCardsOnTable(gameTable,output);
                    break;
                case "endPlayerTurn":
                    endPlayerTurn();
                    break;
                case "placeCard":
                    if (playerToMove == 1)
                        currentAction.placeCard(gameTable, playerOne,
                            playerToMove, currentAction.getHandId(), output);
                    else
                        currentAction.placeCard(gameTable, playerTwo,
                                playerToMove, currentAction.getHandId(), output);
                    break;
                case "getCardsInHand":
                    if(currentAction.getPlayerIdx() == 1)
                        Debug.getCardsInHand(playerOne.getPlayerHand(), output, 1);
                    else
                        Debug.getCardsInHand(playerTwo.getPlayerHand(), output, 2);
                    break;
                case "getEnvironmentCardsInHand":
                    if(currentAction.getPlayerIdx() == 1)
                        Debug.getEnvironmentCardsInHand(playerOne.getPlayerHand(), output, 1);
                    else
                        Debug.getEnvironmentCardsInHand(playerTwo.getPlayerHand(), output, 2);
                    break;
                case "getCardAtPosition":
                    Debug.getCardAtPosition(currentAction.getX(), currentAction.getY(),
                            gameTable, output);
                    break;
                case "useEnvironmentCard":
                    if (playerToMove == 1) {
                        currentAction.useEnvironmentCard(currentAction.getHandId(),
                                currentAction.getAffectedRow(),
                                1,
                                gameTable,
                                playerOne,
                                output);
                    } else {
                        currentAction.useEnvironmentCard(currentAction.getHandId(),
                                currentAction.getAffectedRow(),
                                2,
                                gameTable,
                                playerTwo,
                                output);
                    }
                    break;
                case "getFrozenCardsOnTable":
                    Debug.getFrozenCardsOnTable(gameTable, output);
                    break;
                case "cardUsesAttack":
                    currentAction.cardUsesAttack(currentAction.getCardAttacker(),
                                                currentAction.getCardAttacked(),
                                                gameTable,
                                                playerToMove,
                                                output);
                    break;
                case "cardUsesAbility":
                    currentAction.cardUsesAbility(currentAction.getCardAttacker(),
                            currentAction.getCardAttacked(),
                            gameTable,
                            playerToMove,
                            output);
                    break;



            }
        }
    }


}
