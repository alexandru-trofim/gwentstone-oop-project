package gameStructure;

import fileio.ActionsInput;
import fileio.Coordinates;
import lombok.Getter;
import lombok.Setter;

public class Action {
    private @Getter @Setter String command;
    private @Getter @Setter int handId;
    private @Getter @Setter Coordinates cardAttacker;
    private @Getter @Setter Coordinates cardAttacked;
    private @Getter @Setter int affectedRow;
    private @Getter @Setter int playerIdx;
    private @Getter @Setter int x;
    private @Getter @Setter int y;

    public Action(ActionsInput actionsInput) {
        this.command = actionsInput.getCommand();
        this.handId = actionsInput.getHandIdx();
        //this.cardAttacker.setX(actionsInput.getCardAttacker().getX());
        //this.cardAttacker.setY(actionsInput.getCardAttacker().getY());
        //this.cardAttacked.setX(actionsInput.getCardAttacked().getX());
        //this.cardAttacked.setY(actionsInput.getCardAttacked().getY());
        this.affectedRow = actionsInput.getAffectedRow();
        this.playerIdx = actionsInput.getPlayerIdx();
        this.x = actionsInput.getX();
        this.y = actionsInput.getY();

    }
}
