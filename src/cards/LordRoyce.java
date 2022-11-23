package cards;

import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class LordRoyce extends HeroCard {

    public LordRoyce(CardInput cardInput) {
        super(cardInput);
    }

    @Override
    public void attack(Card[][] table, int affectedRow) {
        System.out.printf("ENTERED LORD ROYCE" + "\n");
        //Sub-zero
        int cardsOnRow = 5;
        int maxValue = -1, index = 0;
        for(int i = cardsOnRow - 1; i >= 0; --i) {
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
    public void specialAttack(Card cardAttacked) {
    }
}
