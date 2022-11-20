package cards;

import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class TheRipper extends SpecialMinionCard implements Card{

    private @Getter @Setter int mana;
    private @Getter @Setter int attackDamage;
    private @Getter @Setter String description;
    private @Getter @Setter ArrayList<String> colors;
    private @Getter @Setter String name;
    private @Getter @Setter boolean isTank;
    private @Getter @Setter boolean isFrozen;


    public TheRipper(int mana, int health, int attackDamage, String description, ArrayList<String> colors, String name) {
        super(mana, health, attackDamage, description, colors, name);
    }
    public TheRipper(CardInput cardInput) {
        super(cardInput);
    }
    public TheRipper(TheRipper cardToCopy) {
        super(cardToCopy);
    }
    @Override
    public void attack() {

    }

    @Override
    public void specialAttack() {

    }
}
