package game.play;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.GameInput;
import fileio.Input;
import game.structure.Game;
import game.structure.GameSession;
import game.structure.Player;

import java.util.ArrayList;

public class EntryPoint {

    /**
     * <p>Runs the game by taking all the input data from the Input object and puts
     * it into the objects needed to run the game. Initializes the players, creates
     * an array of game sessions and puts them into a Game Object. <p/>
     * Then iterates through all the game sessions, initialize every session and
     * runs all the commands.
     * @param inputData the object that stores all the input data
     * @param output output object that stores all the output 
     */
    public static void startGame(final Input inputData, final ArrayNode output) {
        //take info from InputeData and put it in our objects
        Player playerOne = new Player(inputData.getPlayerOneDecks());
        Player playerTwo = new Player(inputData.getPlayerTwoDecks());
        ArrayList<GameSession> gameSessions = new ArrayList<>();
        //fill gameSessions array
        for (GameInput inputGame: inputData.getGames()) {
            GameSession gameSessionToAdd = new GameSession(inputGame);
            gameSessions.add(gameSessionToAdd);
        }
        //game is set up
        Game game = new Game(playerOne, playerTwo, gameSessions);

        //a cicle that plays all the games
        //fac o functie pentru play game
        for (GameSession session: game.getGames()) {
            //load a game session load game start and game actions
            // then set active deck, starting player and all stuff

            //current session loaded
            game.loadCurrentSession(session);

            //launch actions
            game.executeActions(session, output);
        }


    }
}
