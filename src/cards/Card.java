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


    public abstract void attack();
    public abstract void specialAttack();

    public void convertCardToJson(ObjectNode cardOutput) {
        cardOutput.put("mana", this.getMana());
        cardOutput.put("attackDamage", this.getAttackDamage());
        cardOutput.put("health", this.getHealth());
        cardOutput.put("description", this.getDescription());
        ArrayNode colorsOutput = cardOutput.putArray("colors");
        for(String color: this.getColors()) {
            colorsOutput.add(color);
        }
        cardOutput.put("name", this.getName());
    }

    public void getDamage(int damage) {
        this.health -= damage;
    }

    public boolean isDead() {
        if (health <= 0)
            return true;
        else return false;
    }


}
