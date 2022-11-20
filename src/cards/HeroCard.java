package cards;

import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class HeroCard implements Card{
    private @Getter @Setter int mana;
    private @Getter @Setter int health;
    private @Getter @Setter String description;
    private @Getter @Setter ArrayList<String> colors;
    private @Getter @Setter String name;

    public HeroCard(CardInput cardInput) {
        this.mana = cardInput.getMana();
        this.description = cardInput.getDescription();
        this.name = cardInput.getName();

        this.colors = new ArrayList<String>();
        for(String color: cardInput.getColors()) {
            this.colors.add(color);
        }

        this.health = 30;
    }


    @Override
    public void attack() {

    }

    @Override
    public void specialAttack() {

    }
}
