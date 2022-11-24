package cards;

import fileio.CardInput;
import game.structure.Game;


public class LordRoyce extends HeroCard {

    public LordRoyce(final CardInput cardInput) {
        super(cardInput);
    }

    /**
     * Sub-Zero: Makes the card with the max health on the attacked row frozen.
     * @param table Game table
     * @param affectedRow the row that will be attacked
     */
    @Override
    public void attack(final Card[][] table, final int affectedRow) {
        //Sub-zero
        int cardsOnRow = Game.CARDS_ON_ROW;
        int maxValue = -1, index = 0;

        for (int i = cardsOnRow - 1; i >= 0; --i) {
            if (table[affectedRow][i] != null) {
                if (table[affectedRow][i].getAttackDamage() >= maxValue) {
                    maxValue = table[affectedRow][i].getAttackDamage();
                    index = i;
                }
            }
        }
        if (table[affectedRow][index] != null) {
            table[affectedRow][index].setFrozen(true);
        }

        this.setMadeMove(true);
    }

    @Override
    public void specialAttack(final Card cardAttacked) { }
}
