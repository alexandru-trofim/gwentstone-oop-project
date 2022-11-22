package cards;

import debug.Debug;
import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

public class HeartHound extends EnvironmentCard {
    private @Getter @Setter int mana;
    private @Getter @Setter String description;
    private @Getter @Setter ArrayList<String> colors;
    private @Getter @Setter String name;

    public HeartHound(CardInput cardInput) {
        super(cardInput);
    }

    public HeartHound(Card cardToCopy) {
        super(cardToCopy);
    }

    @Override
    public void attack(Card[][] table, int affectedRow) {
        //when we enter this function we already checked if
        //the enemy row isn't full, thus we cand safely add the card
        int cardsOnRow = 5;
        int rowToInsert = 0;
        switch (affectedRow){
            case 0 -> rowToInsert = 3;
            case 1 -> rowToInsert = 2;
            case 2 -> rowToInsert = 1;
            case 3 -> rowToInsert = 0;
        }
        //find the card with the most health
        int maxId = 0, maxHealth = -1;

        for(int i = 0; i < cardsOnRow; ++i) {
            if(table[affectedRow][i] != null) {
                if(table[affectedRow][i].getHealth() > maxHealth) {
                    maxHealth = table[affectedRow][i].getHealth();
                    maxId = i;
                }
            }
        }
        //now put the max id card from affectedRow to rowToInsert
        //find the column to insert
        int columnToInsert = 0;
        for(int i = 0; i < cardsOnRow; ++i) {
           if(table[rowToInsert][i] == null) {
               columnToInsert = i;
               break;
           }
        }
        //steal card to our raw
        table[rowToInsert][columnToInsert] = table[affectedRow][maxId];

        //now delete the card
        //remove from row
        for(int i = maxId; i < cardsOnRow - 1; i++) {
            table[affectedRow][i] = table[affectedRow][i+1];
        }
        //delete last element from the row
        table[affectedRow][cardsOnRow - 1] = null;
    }


    @Override
    public void attack() {

    }

    @Override
    public void specialAttack() {

    }
}
