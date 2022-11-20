package cards;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Setter @Getter
public class EnvironmentCard extends Card{
    //it has just mana, description, colors, name
    private @Getter @Setter int mana;

    public  EnvironmentCard(CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName(cardInput.getName());
    }

    public EnvironmentCard(Card cardToCopy) {
        this.setMana(cardToCopy.getMana());
        this.setDescription(cardToCopy.getDescription());
        this.setColors(cardToCopy.getColors());
        this.setName(cardToCopy.getName());
    }
    @Override
    public void attack() {

    }

    @Override
    public void specialAttack() {

    }

    @Override
    public void convertCardToJson(ObjectNode cardOutput) {
        cardOutput.put("mana", this.getMana());
        cardOutput.put("description", this.getDescription());
        ArrayNode colorsOutput = cardOutput.putArray("colors");
        for(String color: this.getColors()) {
            colorsOutput.add(color);
        }
        cardOutput.put("name", this.getName());
    }

}
