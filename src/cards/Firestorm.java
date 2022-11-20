package cards;

import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Firestorm extends EnvironmentCard implements Card{

    private @Getter @Setter int mana;
    private @Getter @Setter String description;
    private @Getter @Setter ArrayList<String> colors;
    private @Getter @Setter String name;

    public Firestorm(CardInput cardInput) {
        super(cardInput);
    }

    public Firestorm(Firestorm cardToCopy) {
        super(cardToCopy);
    }

    @Override
    public void attack() {

    }

    @Override
    public void specialAttack() {

    }
}
