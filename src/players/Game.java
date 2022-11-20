package players;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Game {
    private @Getter @Setter Player playerOne;
    private @Getter @Setter Player playerTwo;
    private @Getter @Setter ArrayList<GameSession> games;

    public Game(Player playerOne, Player playerTwo, ArrayList<GameSession> games) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.games = games;
    }


    //aici implementez functiile legate de executarea actiunilor;
}
