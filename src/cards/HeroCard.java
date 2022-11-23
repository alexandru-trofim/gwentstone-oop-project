package cards;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.CardInput;

public abstract class HeroCard extends EnvironmentCard{

    public HeroCard(CardInput cardInput) {
        super(cardInput);
        this.setMana(cardInput.getMana());
        this.setFrozen(false);
        this.setHealth(30);
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
