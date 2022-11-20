package cards;

import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Winterfell extends EnvironmentCard {
    private @Getter @Setter int mana;
    private @Getter @Setter String description;
    private @Getter @Setter ArrayList<String> colors;
    private @Getter @Setter String name;

    public Winterfell(CardInput cardInput) {
        super(cardInput);
    }


    public Winterfell(Card cardToCopy) {
        super(cardToCopy);
    }
    @Override
    public void attack() {

    }

    @Override
    public void specialAttack() {

    }
}
