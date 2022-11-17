package cards;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

public class MinionCard implements Card {

    private @Getter @Setter int mana;
    private @Getter @Setter int health;
    private @Getter @Setter int attackDamage;
    private @Getter @Setter String description;
    private @Getter @Setter ArrayList<String> colors;
    private @Getter @Setter String name;
    private @Getter @Setter boolean isTank;
    private @Getter @Setter boolean isFrozen;

    public MinionCard(int mana, int health, int attackDamage, String description, ArrayList<String> colors, String name) {
        this.mana = mana;
        this.health = health;
        this.attackDamage = attackDamage;
        this.description = description;
        this.colors = colors;
        this.name = name;
        this.isFrozen = false;
        if (this.name == "Goliath" || this.name == "Warden") {
            this.isTank = true;
        }
    }

    @Override
    public void attack() {

    }

    @Override
    public void specialAttack() {

    }
}
