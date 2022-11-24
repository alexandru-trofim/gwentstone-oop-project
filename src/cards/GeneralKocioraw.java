package cards;

import fileio.CardInput;
import game.structure.Game;

public class GeneralKocioraw extends HeroCard {

    public GeneralKocioraw(final CardInput cardInput) {
        super(cardInput);
    }

    /**
     * Blood Thirst: increases attack of all cards on the attacked row by 1.
     * @param table Game table
     * @param affectedRow the row that will be attacked
     */
    @Override
    public void attack(final Card[][] table, final int affectedRow) {
        //Earth Born
        int cardsOnRow = Game.CARDS_ON_ROW;
        for (int i = 0; i < cardsOnRow; ++i) {
            Card card = table[affectedRow][i];
            if (card != null) {
                card.setAttackDamage(card.getAttackDamage() + 1);
            }
        }
        this.setMadeMove(true);
    }

    @Override
    public void specialAttack(final Card cardAttacked) { }
}
