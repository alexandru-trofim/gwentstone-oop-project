package cards;

import fileio.CardInput;
import game.structure.Game;


public class KingMudface extends HeroCard {

    public KingMudface(final CardInput cardInput) {
        super(cardInput);
    }

    /**
     * Earth born: increases the damage of all the cards on the row by 1.
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
              card.setHealth(card.getHealth() + 1);
          }
        }
        this.setMadeMove(true);
    }

    @Override
    public void specialAttack(final Card cardAttacked) { }
}
