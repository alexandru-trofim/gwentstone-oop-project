package cards;

import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Miraj extends SpecialMinionCard implements Card{
    private @Getter @Setter int mana;
    private @Getter @Setter int attackDamage;
    private @Getter @Setter String description;
    private @Getter @Setter ArrayList<String> colors;
    private @Getter @Setter String name;
    private @Getter @Setter boolean isTank;
    private @Getter @Setter boolean isFrozen;

    public Miraj(int mana, int health, int attackDamage, String description, ArrayList<String> colors, String name) {
        super(mana, health, attackDamage, description, colors, name);
    }

    public Miraj(CardInput cardInput) {
        super(cardInput);
    }
    public Miraj(Miraj cardToCopy) {
        super(cardToCopy);
    }

    @Override
    public void attack() {

    }

    @Override
    public void specialAttack() {

    }
}
