package cards;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.CardInput;

public abstract class HeroCard extends EnvironmentCard {
    private static final int HERO_HEALTH = 30;

    public HeroCard(final CardInput cardInput) {
        super(cardInput);
        this.setMana(cardInput.getMana());
        this.setFrozen(false);
        this.setHealth(HERO_HEALTH);
    }

    /**
     * Converts a hero card to a JSON object and adds it
     * to ObjectNode given as parameter.
     * @param cardOutput ObjectNode where the converted card
     *                   will be added.
     */
    public void convertCardToJson(final ObjectNode cardOutput) {
        cardOutput.put("mana", this.getMana());
        cardOutput.put("description", this.getDescription());
        ArrayNode colorsOutput = cardOutput.putArray("colors");

        for (String color: this.getColors()) {
            colorsOutput.add(color);
        }
        cardOutput.put("name", this.getName());
        cardOutput.put("health", this.getHealth());
    }
}
