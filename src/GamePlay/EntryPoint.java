package GamePlay;

import cards.Card;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.GameInput;
import fileio.Input;
import players.Game;
import players.GameSession;
import players.Player;

import java.util.ArrayList;

public class EntryPoint {



    public static void StartGame(Input inputData, ArrayNode output) {

        Player playerOne = new Player(inputData.getPlayerOneDecks());
        Player playerTwo = new Player(inputData.getPlayerTwoDecks());
        ArrayList<GameSession> gameSessions = new ArrayList<>();

        for(GameInput inputGame: inputData.getGames()) {
            GameSession gameSessionToAdd = new GameSession(inputGame);
            gameSessions.add(gameSessionToAdd);

        }

        Game game = new Game(playerOne, playerTwo, gameSessions);

        //a cicle that plays all the games
        //fac o functie pentru play game
        for(GameSession session: game.getGames()) {
            //ObjectNode a = new ObjectNode();
        }


    }
}
