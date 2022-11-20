package cards;

import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
abstract class SpecialMinionCard extends MinionCard{



    public SpecialMinionCard(Card cardToCopy) {
        super(cardToCopy);
    }

    public SpecialMinionCard(CardInput cardInput) {
        super(cardInput);
    }

    @Override
    public void attack() {

    }

    @Override
    public void specialAttack() {

    }
}
