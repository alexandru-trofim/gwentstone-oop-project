package cards;

import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
abstract class SpecialMinionCard extends MinionCard implements Card{

    public SpecialMinionCard(int mana, int health, int attackDamage, String description, ArrayList<String> colors, String name) {
        super(mana, health, attackDamage, description, colors, name);

    }

    public SpecialMinionCard(SpecialMinionCard cardToCopy) {
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
