package cards;

import fileio.CardInput;
import game.structure.Game;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Winterfell extends EnvironmentCard {
    private @Getter @Setter int mana;
    private @Getter @Setter String description;
    private @Getter @Setter ArrayList<String> colors;
    private @Getter @Setter String name;

    public Winterfell(final CardInput cardInput) {
        super(cardInput);
    }

    public Winterfell(final Card cardToCopy) {
        super(cardToCopy);
    }

    /**
     * Makes all the cards on the row frozen for a turn.
     * @param table Game table
     * @param affectedRow the row that will be attacked
     */
    @Override
    public void attack(final Card[][] table, final int affectedRow) {
        int elementsOnRow = Game.CARDS_ON_ROW;
        for (int i = 0; i < elementsOnRow; i++) {
            if (table[affectedRow][i] != null) {
                table[affectedRow][i].setFrozen(true);
            }
        }
    }

    @Override
    public void specialAttack(final Card cardAttacked) { }
}
