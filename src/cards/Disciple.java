package cards;

import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Disciple extends SpecialMinionCard {


    public Disciple(CardInput cardInput) {
        super(cardInput);
    }

    public Disciple(Card cardToCopy) {
        super(cardToCopy);
    }

    @Override
    public void attack() {

    }

    @Override
    public void specialAttack() {

    }
}
