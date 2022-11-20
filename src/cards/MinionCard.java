package cards;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;

public class MinionCard extends Card {

    public  MinionCard() {

    }

    public MinionCard(CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setHealth(cardInput.getHealth());
        this.setAttackDamage(cardInput.getAttackDamage());
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName(cardInput.getName());
        this.setFrozen(false);

        if (this.getName().equals("Goliath")  || this.getName().equals("Warden")) {
            this.setTank(true);
        } else {
            this.setTank(false);
        }
    }

    public MinionCard(Card cardToCopy) {
        this.setMana(cardToCopy.getMana());
        this.setHealth(cardToCopy.getHealth());
        this.setAttackDamage(cardToCopy.getAttackDamage());
        this.setDescription(cardToCopy.getDescription());
        this.setColors(cardToCopy.getColors());
        this.setName(cardToCopy.getName());
        this.setTank(cardToCopy.isTank());
        this.setFrozen(cardToCopy.isFrozen());
    }

    @Override
    public void attack() {

    }

    @Override
    public void specialAttack() {

    }


}
