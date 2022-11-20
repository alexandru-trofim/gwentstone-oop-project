package gameStructure;

import cards.Card;
import com.fasterxml.jackson.databind.node.ArrayNode;
import debug.Debug;
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

    public Game(Player playerOne, Player playerTwo, ArrayList<GameSession> games) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.games = games;
    }


    //aici implementez functiile legate de executarea actiunilor;
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

        //set Player to move
        this.playerToMove = gameStart.getStartingPlayer();

        //get one card from deck for each player
        this.playerOne.getCardFromDeck();
        this.playerTwo.getCardFromDeck();

    }

    public void executeActions(GameSession session, ArrayNode output) {
        ArrayList<Action> actions = session.getActions();

        for(Action currentAction: actions) {
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
            }
        }
    }
}
