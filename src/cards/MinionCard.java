package cards;

import java.util.ArrayList;

import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;

public class MinionCard extends Card {

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
        if (this.name.equals("Goliath") || this.name.equals("Warden")) {
            this.isTank = true;
        } else {
            this.isTank = false;
        }
    }

    public MinionCard(MinionCard cardToCopy) {
        this.mana = cardToCopy.mana;
        this.health = cardToCopy.health;
        this.attackDamage = cardToCopy.attackDamage;
        this.description = cardToCopy.description;
        this.colors = cardToCopy.colors;
        this.name = cardToCopy.name;
        this.isTank = cardToCopy.isTank;
        this.isFrozen = cardToCopy.isFrozen;
    }

    public  MinionCard() {

    }

    public MinionCard(CardInput cardInput) {
        this.mana = cardInput.getMana();
        this.attackDamage = cardInput.getAttackDamage();
        this.health = cardInput.getHealth();
        this.description = cardInput.getDescription();
        this.name = cardInput.getName();

        this.colors = new ArrayList<String>();
        for(String color: cardInput.getColors()) {
            this.colors.add(color);
        }

        this.isFrozen = false;
        if (this.name == "Goliath" || this.name == "Warden") {
            this.isTank = true;
        } else {
            this.isTank = false;
        }
    }

    @Override
    public void attack() {

    }

    @Override
    public void specialAttack() {

    }
}
