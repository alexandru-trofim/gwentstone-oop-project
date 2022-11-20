package cards;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class HeroCard extends Card{

    public HeroCard(CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName(cardInput.getName());
        this.setFrozen(false);
        this.setHealth(30);
    }


    @Override
    public void attack() {

    }

    @Override
    public void specialAttack() {

    }

    public void convertCardToJson(ObjectNode cardOutput) {
        cardOutput.put("mana", this.getMana());
        cardOutput.put("description", this.getDescription());
        ArrayNode colorsOutput = cardOutput.putArray("colors");
        for(String color: this.getColors()) {
            colorsOutput.add(color);
        }
        cardOutput.put("name", this.getName());
        cardOutput.put("health", this.getHealth());
    }

}
