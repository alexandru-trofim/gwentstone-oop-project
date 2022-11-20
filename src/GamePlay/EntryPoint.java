package GamePlay;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.GameInput;
import fileio.Input;
import gameStructure.Game;
import gameStructure.GameSession;
import gameStructure.Player;

import java.util.ArrayList;

public class EntryPoint {



    public static void startGame(Input inputData, ArrayNode output) {
        //take info from InputeData and put it in our objects
        Player playerOne = new Player(inputData.getPlayerOneDecks());
        Player playerTwo = new Player(inputData.getPlayerTwoDecks());
        ArrayList<GameSession> gameSessions = new ArrayList<>();
        //System.out.printf(String.valueOf(playerOne.getDecks().get(1).getCards().get(2).getName()));
        //fill gameSessions array
        for(GameInput inputGame: inputData.getGames()) {
            GameSession gameSessionToAdd = new GameSession(inputGame);
            gameSessions.add(gameSessionToAdd);

        }
        //game is set up
        Game game = new Game(playerOne, playerTwo, gameSessions);
        System.out.printf(game.getGames().get(0).getGameStart().getPlayerOneHero().getName());

        //a cicle that plays all the games
        //fac o functie pentru play game
        for(GameSession session: game.getGames()) {
            //ObjectNode a = new ObjectNode();
            //load a game session load game start and game actions
            // then set active deck, starting player and all stuff

            //current session loaded
            game.loadCurrentSession(session);

            //now we need to launch actions
            game.executeActions(session,output);
        }


    }
}
