package cards;

import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class KingMudface extends HeroCard implements Card{
    private @Getter @Setter int mana;
    private @Getter @Setter String description;
    private @Getter @Setter ArrayList<String> colors;
    private @Getter @Setter String name;

    public KingMudface(CardInput cardInput) {
        super(cardInput);
    }

    @Override
    public void attack() {

    }

    @Override
    public void specialAttack() {

    }
}
