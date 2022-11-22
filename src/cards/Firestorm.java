package cards;

import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Firestorm extends EnvironmentCard {

    private @Getter @Setter int mana;
    private @Getter @Setter String description;
    private @Getter @Setter ArrayList<String> colors;
    private @Getter @Setter String name;

    public Firestorm(CardInput cardInput) {
        super(cardInput);
    }

    public Firestorm(Card cardToCopy) {
        super(cardToCopy);
    }

    @Override
    public void attack(Card[][] table, int affectedRow) {
        // -1 health to all minions
        int elementsOnRow = 5;
        for(int i = 0; i < elementsOnRow; i++) {
            if(table[affectedRow][i] != null) {
                table[affectedRow][i].getDamage(1);

                if(table[affectedRow][i].isDead()) {
                    //remove from row
                    for(int j = i; j < elementsOnRow - 1; j++) {
                        table[affectedRow][j] = table[affectedRow][j+1];
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
    public void attack() {

    }

    @Override
    public void specialAttack() {

    }
}
