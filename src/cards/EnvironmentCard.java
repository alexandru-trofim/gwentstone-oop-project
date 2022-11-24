package cards;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.CardInput;

public abstract class EnvironmentCard extends Card {
    //it has just mana, description, colors, name

    public  EnvironmentCard(final CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName(cardInput.getName());
    }

    public EnvironmentCard(final Card cardToCopy) {
        this.setMana(cardToCopy.getMana());
        this.setDescription(cardToCopy.getDescription());
        this.setColors(cardToCopy.getColors());
        this.setName(cardToCopy.getName());
    }

    /**
     * Attack function of an Environment card that attack a whole row
     * on the table.
     * @param table Game table
     * @param affectedRow the row that will be attacked
     */
    public abstract void attack(Card[][] table, int affectedRow);

    /**
     * Converts an Environment card and adds it to an ObjectNode given
     * as a parameter.
     * @param cardOutput ObjectNode where the converted card
     *                   will be added.
     */
    @Override
    public void convertCardToJson(final ObjectNode cardOutput) {
        cardOutput.put("mana", this.getMana());
        cardOutput.put("description", this.getDescription());
        ArrayNode colorsOutput = cardOutput.putArray("colors");

        for (String color: this.getColors()) {
            colorsOutput.add(color);
        }
        cardOutput.put("name", this.getName());
    }

}
