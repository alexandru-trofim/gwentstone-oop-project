package players;

import fileio.ActionsInput;
import fileio.GameInput;

import java.util.ArrayList;

public class GameSession {
    GameStart gameStart;
    ArrayList<Action> actions;

    public GameSession(GameInput gameInput) {
       this.gameStart = new GameStart(gameInput.getStartGame());
       this.actions = new ArrayList<>();

       for(ActionsInput actionInput: gameInput.getActions()) {
           Action actionToAdd = new Action(actionInput);
           this.actions.add(actionToAdd);
       }
    }
}
