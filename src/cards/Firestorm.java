package cards;

import fileio.CardInput;
import game.structure.Game;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Firestorm extends EnvironmentCard {

    private @Getter @Setter int mana;
    private @Getter @Setter String description;
    private @Getter @Setter ArrayList<String> colors;
    private @Getter @Setter String name;

    public Firestorm(final CardInput cardInput) {
        super(cardInput);
    }

    public Firestorm(final Card cardToCopy) {
        super(cardToCopy);
    }

    /**
     * Decreases the health of all cards ob an attacked row by one
     * @param table Game table, where the cards are
     * @param affectedRow the row that will be attacked
     */
    @Override
    public void attack(final Card[][] table, final int affectedRow) {
        // -1 health to all minions
        int elementsOnRow = Game.CARDS_ON_ROW;

        for (int i = 0; i < elementsOnRow; i++) {
            if (table[affectedRow][i] != null) {
                table[affectedRow][i].getDamage(1);

                if (table[affectedRow][i].isDead()) {
                    //remove from row
                    for (int j = i; j < elementsOnRow - 1; j++) {
                        table[affectedRow][j] = table[affectedRow][j + 1];
                    }
                    //delete last element from the row
                    table[affectedRow][elementsOnRow - 1] = null;
                    //get back when eliminate one element
                    i--;
                }
            }
        }
    }

    @Override
    public void specialAttack(final Card cardAttacked) { }
}
