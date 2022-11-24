package cards;

import fileio.CardInput;
import game.structure.Game;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

public class HeartHound extends EnvironmentCard {
    private @Getter @Setter int mana;
    private @Getter @Setter String description;
    private @Getter @Setter ArrayList<String> colors;
    private @Getter @Setter String name;

    public HeartHound(final CardInput cardInput) {
        super(cardInput);
    }

    public HeartHound(final Card cardToCopy) {
        super(cardToCopy);
    }

    /**
     * Steals the minion with the max health on the enemy row given as a
     * parameter and puts it to the player's row that used this ability
     * @param table Game table
     * @param affectedRow the row that will be attacked
     */
    @Override
    public void attack(final Card[][] table, final int affectedRow) {
        //when we enter this function we already checked if
        //the enemy row isn't full, thus we can safely add the card
        int cardsOnRow = Game.CARDS_ON_ROW;

        //formula for finding the opposite row
        int rowToInsert = affectedRow - 3;
        if (rowToInsert < 0) {
            rowToInsert = -rowToInsert;
        }

        //find the card with the most health
        int maxId = 0, maxHealth = -1;

        for (int i = 0; i < cardsOnRow; ++i) {
            if (table[affectedRow][i] != null) {
                if (table[affectedRow][i].getHealth() > maxHealth) {
                    maxHealth = table[affectedRow][i].getHealth();
                    maxId = i;
                }
            }
        }
        //now put the max id card from affectedRow to rowToInsert
        //find the column to insert
        int columnToInsert = 0;
        for (int i = 0; i < cardsOnRow; ++i) {
           if (table[rowToInsert][i] == null) {
               columnToInsert = i;
               break;
           }
        }
        //steal card to our raw
        table[rowToInsert][columnToInsert] = table[affectedRow][maxId];

        //now delete the card
        //remove from row
        for (int i = maxId; i < cardsOnRow - 1; i++) {
            table[affectedRow][i] = table[affectedRow][i + 1];
        }
        //delete last element from the row
        table[affectedRow][cardsOnRow - 1] = null;
    }

    @Override
    public void specialAttack(final Card cardAttacked) { }
}
