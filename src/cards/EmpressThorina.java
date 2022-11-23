package cards;

import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

public class EmpressThorina extends HeroCard {

    public EmpressThorina(CardInput cardInput) {
        super(cardInput);
    }

    @Override
    public void attack(Card[][] table, int affectedRow) {
        System.out.printf("ENTERED GENERAL EMPRESS THORINA" + "\n");
        //Low Blow
        int cardsOnRow = 5;
        int maxValue = -1, index = 0;
        for(int i = cardsOnRow - 1; i >= 0; --i) {
            if (table[affectedRow][i] != null) {
                if (table[affectedRow][i].getHealth() >= maxValue) {
                    maxValue = table[affectedRow][i].getHealth();
                    index = i;
                }
            }
        }

        //kill the card
        for(int i = index; i < cardsOnRow - 1; ++i) {
            table[affectedRow][i] = table[affectedRow][i + 1];
        }
        //erase the last card from the row
        table[affectedRow][cardsOnRow - 1] = null;

        this.setMadeMove(true);
    }


    @Override
    public void specialAttack(Card cardAttacked) {

    }
}
