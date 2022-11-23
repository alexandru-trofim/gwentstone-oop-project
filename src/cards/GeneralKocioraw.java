package cards;

import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class GeneralKocioraw extends HeroCard {

    public GeneralKocioraw(CardInput cardInput) {
        super(cardInput);
    }

    @Override
    public void attack(Card[][] table, int affectedRow) {
        System.out.printf("ENTERED GENERAL KOCI" + "\n");
        //Earth Born
        int cardsOnRow = 5;
        for(int i = 0; i < cardsOnRow; ++i) {
            Card card = table[affectedRow][i];
            if (card != null) {
                card.setAttackDamage(card.getAttackDamage() + 1);
            }
        }

        this.setMadeMove(true);
    }


    @Override
    public void specialAttack(Card cardAttacked) {

    }
}
