package cards;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public abstract class Card {

    private @Getter @Setter int mana;
    private @Getter @Setter int health;
    private @Getter @Setter int attackDamage;
    private @Getter @Setter String description;
    private @Getter @Setter ArrayList<String> colors;
    private @Getter @Setter String name;
    private @Getter @Setter boolean isTank;
    private @Getter @Setter boolean isFrozen;

    public abstract void attack();
    public abstract void specialAttack();
}
