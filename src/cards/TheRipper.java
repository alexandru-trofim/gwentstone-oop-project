package cards;

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

    @Override
    public void attack() {

    }

    @Override
    public void specialAttack() {

    }
}
