package cards;

import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Miraj extends SpecialMinionCard {



    public Miraj(CardInput cardInput) {
        super(cardInput);
    }
    public Miraj(Card cardToCopy) {
        super(cardToCopy);
    }

    @Override
    public void attack() {

    }

    @Override
    public void specialAttack() {

    }
}
