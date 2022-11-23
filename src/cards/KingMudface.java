package cards;

import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class KingMudface extends HeroCard {

    public KingMudface(CardInput cardInput) {
        super(cardInput);
    }

    @Override
    public void attack(Card[][] table, int affectedRow) {

        System.out.printf("ENTERED King MUDFACE" + "\n");
        //Earth Born
        int cardsOnRow = 5;
        for(int i = 0; i < cardsOnRow; ++i) {
          Card card = table[affectedRow][i];
          if (card != null) {
              card.setHealth(card.getHealth() + 1);
          }
        }

        this.setMadeMove(true);
    }


    @Override
    public void specialAttack(Card cardAttacked) {

    }
}
