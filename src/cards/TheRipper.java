package cards;

import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class TheRipper extends SpecialMinionCard {

    public TheRipper(CardInput cardInput) {
        super(cardInput);
    }
    public TheRipper(Card cardToCopy) {
        super(cardToCopy);
    }
    @Override
    public void attack() {

    }

    @Override
    public void specialAttack() {

    }
}
