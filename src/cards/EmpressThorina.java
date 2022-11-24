package cards;

import fileio.CardInput;
import game.structure.Game;

public class EmpressThorina extends HeroCard {

    public EmpressThorina(final CardInput cardInput) {
        super(cardInput);
    }

    /**
     * Low Blow ability - kills the card with the biggest health on the row.
     * @param table Game table where the cards are
     * @param affectedRow the row that will be affected by ability
     */
    @Override
    public void attack(final Card[][] table, final int affectedRow) {
        //Low Blow
        int cardsOnRow = Game.CARDS_ON_ROW;
        int maxValue = -1, index = 0;
        for (int i = cardsOnRow - 1; i >= 0; --i) {
            if (table[affectedRow][i] != null) {
                if (table[affectedRow][i].getHealth() >= maxValue) {
                    maxValue = table[affectedRow][i].getHealth();
                    index = i;
                }
            }
        }
        //kill the card
        for (int i = index; i < cardsOnRow - 1; ++i) {
            table[affectedRow][i] = table[affectedRow][i + 1];
        }
        //erase the last card from the row
        table[affectedRow][cardsOnRow - 1] = null;

        this.setMadeMove(true);
    }

    @Override
    public void specialAttack(final Card cardAttacked) { }
}
