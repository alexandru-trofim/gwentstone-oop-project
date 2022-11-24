package cards;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
    private @Getter @Setter boolean madeMove;

    /**
     * Decreases the health of the Card given as a parameter
     * by the attack damage that calls that function.
     * @param cardToAttack card that will be attacked.
     */
    public void attack(final Card cardToAttack) {
       cardToAttack.health -= this.attackDamage;

       this.madeMove = true;
    }

    /**
     * Uses the special ability of a card on the card
     * given as parameter.
     * @param cardAttacked card that will be attacked.
     */
    public abstract void specialAttack(Card cardAttacked);

    /**
     * Converts a card to a JSON object and adds it
     * to ObjectNode given as parameter.
     * @param cardOutput ObjectNode where the converted card
     *                   will be added.
     */
    public void convertCardToJson(final ObjectNode cardOutput) {
        cardOutput.put("mana", this.getMana());
        cardOutput.put("attackDamage", this.getAttackDamage());
        cardOutput.put("health", this.getHealth());
        cardOutput.put("description", this.getDescription());
        ArrayNode colorsOutput = cardOutput.putArray("colors");

        for (String color: this.getColors()) {
            colorsOutput.add(color);
        }
        cardOutput.put("name", this.getName());
    }

    /**
     * Decreases the health of the card by the given input.
     * @param damage the abount by which the health will be decreased.
     */
    public void getDamage(final int damage) {
        this.health -= damage;
    }

    /**
     * Checks if a card is dead
     * @return true if health is less or equal to 0 or false otherwise
     */
    public boolean isDead() {
        return health <= 0;
    }
}
