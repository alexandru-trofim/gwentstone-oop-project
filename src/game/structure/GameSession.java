package game.structure;

import fileio.ActionsInput;
import fileio.GameInput;
import game.play.Action;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class GameSession {
    private @Getter @Setter GameStart gameStart;
    private @Getter @Setter ArrayList<Action> actions;

    public GameSession(final GameInput gameInput) {
       this.gameStart = new GameStart(gameInput.getStartGame());
       this.actions = new ArrayList<>();

       for (ActionsInput actionInput: gameInput.getActions()) {
           Action actionToAdd = new Action(actionInput);
           this.actions.add(actionToAdd);
       }
    }
}
