package game.structure;

import cards.Card;
import com.fasterxml.jackson.databind.node.ArrayNode;
import debug.Debug;
import game.play.Action;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game {

    private static final int MAX_MANA = 10;
    private @Getter @Setter Player playerOne;
    private @Getter @Setter Player playerTwo;
    private @Getter @Setter ArrayList<GameSession> games;

    private @Getter @Setter Card[][]  gameTable;
    private @Getter @Setter int playerToMove;
    private @Getter @Setter int currentRound;

    private @Getter @Setter static int totalGamesPlayed;
    private @Getter @Setter static int playerOneWins;
    private @Getter @Setter static int playerTwoWins;
    public static final int CARDS_ON_ROW = 5;
    public static final int NR_OF_ROWS = 4;

    public Game(final Player playerOne, final Player playerTwo,
                final ArrayList<GameSession> games) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.games = games;
        totalGamesPlayed = 0;
        playerOneWins = 0;
        playerTwoWins = 0;
    }

    /**
     * At the end of a round resets the property MadeMove of
     * all the cards on the table
     */
    public void resetCardMadeMove() {

        for (int i = 0; i < NR_OF_ROWS; ++i) {
            for (int j = 0; j < CARDS_ON_ROW; ++j) {
                if (gameTable[i][j] != null) {
                    gameTable[i][j].setMadeMove(false);
                }
            }
        }
    }

    /**
     * At the end of the players move makes all his cards unfrozen
     */
    public void makeCardsUnfrozen() {
        int start, end;
        if (playerToMove == 1) {
            start = 2;
            end = 3;
        } else {
            start = 0;
            end = 1;
        }
        for (int i = start; i <= end; ++i) {
            for (int j = 0; j < CARDS_ON_ROW; ++j) {
                if (gameTable[i][j] != null) {
                    gameTable[i][j].setFrozen(false);
                }
            }
        }
    }

    /**
     * Ends the player turn by changing the current player. If both
     * players ended their turn it resets the required properties.
     */
    public void endPlayerTurn() {
        makeCardsUnfrozen();
        //if the current player ended his turn and so did the other player
        //then we end the round
        if ((playerToMove == 1 && playerTwo.getMadeMove() == 1)
                || (playerToMove == 2 && playerOne.getMadeMove() == 1)) {
            //end round
            playerOne.getCardFromDeck();
            playerTwo.getCardFromDeck();

            playerOne.setMadeMove(0);
            playerTwo.setMadeMove(0);

            //set All cards property Made move to false
            resetCardMadeMove();

            //reset Player heroes MadeMove property
            playerOne.getPlayerHero().setMadeMove(false);
            playerTwo.getPlayerHero().setMadeMove(false);

            currentRound += 1;

            int addMana;
            if (currentRound <= MAX_MANA) {
                addMana = currentRound;
            } else {
                addMana = MAX_MANA;
            }

            playerOne.setMana(playerOne.getMana() + addMana);
            playerTwo.setMana(playerTwo.getMana() + addMana);

            if (playerToMove == 1) {
                playerToMove = 2;
            } else {
                playerToMove = 1;
            }

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

    /**
     * Gets the data from the GameSession object to initialize
     * a new session. It resets and initializes all the objects
     * and properties needed to play a game.
     * @param session current session to be loaded
     */
    public void loadCurrentSession(final GameSession session) {
        GameStart gameStart = session.getGameStart();
        //initialize new hands for each hand
        this.playerOne.getNewHand();
        this.playerTwo.getNewHand();

        this.playerOne.initializeActiveDeck(gameStart.getPlayerOneDeckId());
        this.playerTwo.initializeActiveDeck(gameStart.getPlayerTwoDeckId());

        //shuffle the decks
        Random rand1 = new Random(gameStart.getShuffleSeed());
        Collections.shuffle(this.getPlayerOne().getActiveDeck().getCards(), rand1);

        Random rand2 = new Random(gameStart.getShuffleSeed());
        Collections.shuffle(this.getPlayerTwo().getActiveDeck().getCards(), rand2);

        //initialize player Heroes
        this.playerOne.setPlayerHero(gameStart.getPlayerOneHero());
        this.playerTwo.setPlayerHero(gameStart.getPlayerTwoHero());

        //prepare the game table
        this.gameTable = new Card[NR_OF_ROWS][CARDS_ON_ROW];

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

    /**
     * Parses all the commands given in session, executes them and adds
     * the output to de output ArrayNode
     * @param session the GameSession object from where we get the commands
     * @param output ouptut ArrayNode where we add the output
     */
    public void executeActions(final GameSession session, final ArrayNode output) {
        ArrayList<Action> actions = session.getActions();

        for (Action currentAction: actions) {
            //check which action we have and execute it
            switch (currentAction.getCommand()) {
                case "getPlayerDeck":
                    if (currentAction.getPlayerIdx() == 1) {
                        Debug.getPlayerDeck(playerOne.getActiveDeck(), output, 1);
                    } else {
                        Debug.getPlayerDeck(playerTwo.getActiveDeck(), output, 2);
                    }
                    break;
                case "getPlayerHero":
                    if (currentAction.getPlayerIdx() == 1) {
                        Debug.getPlayerHero(playerOne.getPlayerHero(), output, 1);
                    } else {
                        Debug.getPlayerHero(playerTwo.getPlayerHero(), output, 2);
                    }
                    break;
                case "getPlayerTurn":
                    Debug.getPlayerTurn(playerToMove, output);
                    break;
                case "getPlayerMana":
                    if (currentAction.getPlayerIdx() == 1) {
                        Debug.getPlayerMana(playerOne.getMana(), output, 1);
                    } else {
                        Debug.getPlayerMana(playerTwo.getMana(), output, 2);
                    }
                    break;
                case "getCardsOnTable":
                    Debug.getCardsOnTable(gameTable, output);
                    break;
                case "endPlayerTurn":
                    endPlayerTurn();
                    break;
                case "placeCard":
                    if (playerToMove == 1) {
                        currentAction.placeCard(gameTable, playerOne, playerToMove, output);
                    } else {
                        currentAction.placeCard(gameTable, playerTwo, playerToMove, output);
                    }
                    break;
                case "getCardsInHand":
                    if (currentAction.getPlayerIdx() == 1) {
                        Debug.getCardsInHand(playerOne.getPlayerHand(), output, 1);
                    } else {
                        Debug.getCardsInHand(playerTwo.getPlayerHand(), output, 2);
                    }
                    break;
                case "getEnvironmentCardsInHand":
                    if (currentAction.getPlayerIdx() == 1) {
                        Debug.getEnvironmentCardsInHand(playerOne.getPlayerHand(), output, 1);
                    } else {
                        Debug.getEnvironmentCardsInHand(playerTwo.getPlayerHand(), output, 2);
                    }
                    break;
                case "getCardAtPosition":
                    Debug.getCardAtPosition(currentAction.getX(), currentAction.getY(),
                            gameTable, output);
                    break;
                case "useEnvironmentCard":
                    if (playerToMove == 1) {
                        currentAction.useEnvironmentCard(1, gameTable, playerOne, output);
                    } else {
                        currentAction.useEnvironmentCard(2,
                                gameTable,
                                playerTwo,
                                output);
                    }
                    break;
                case "getFrozenCardsOnTable":
                    Debug.getFrozenCardsOnTable(gameTable, output);
                    break;
                case "cardUsesAttack":
                    currentAction.cardUsesAttack(gameTable, playerToMove, output);
                    break;
                case "cardUsesAbility":
                    currentAction.cardUsesAbility(gameTable, playerToMove, output);
                    break;
                case "useAttackHero":
                    Card hero;
                    if (playerToMove == 1) {
                        hero = playerTwo.getPlayerHero();
                    } else {
                        hero = playerOne.getPlayerHero();
                    }
                    currentAction.useAtatckHero(gameTable, playerToMove, hero, output);
                    break;
                case "useHeroAbility":
                    Player player = null;
                    if (playerToMove == 1) {
                        player = playerOne;
                    } else {
                        player = playerTwo;
                    }
                    currentAction.useHeroAbility(gameTable,
                            player, playerToMove, output);
                    break;
                case "getTotalGamesPlayed":
                    Debug.getTotalGamesPlayed(output);
                    break;
                case "getPlayerOneWins":
                    Debug.getPlayerOneWins(output);
                    break;
                case "getPlayerTwoWins":
                    Debug.getPlayerTwoWins(output);
                    break;
                default:
                    System.out.printf("!!!ERROR!!! Unrecognized command" + "\n");
            }
        }
    }

}
