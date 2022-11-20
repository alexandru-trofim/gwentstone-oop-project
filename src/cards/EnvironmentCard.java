package cards;

import fileio.CardInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class EnvironmentCard implements Card{
    private @Getter @Setter int mana;
    private @Getter @Setter String description;
    private @Getter @Setter ArrayList<String> colors;
    private @Getter @Setter String name;

    public  EnvironmentCard(CardInput cardInput) {
        this.mana = cardInput.getMana();
        this.description = cardInput.getDescription();
        this.name = cardInput.getName();

        this.colors = new ArrayList<String>();
        for(String color: cardInput.getColors()) {
            this.colors.add(color);
        }
    }

    public EnvironmentCard(EnvironmentCard cardToCopy) {
        this.mana = cardToCopy.mana;
        this.description = cardToCopy.description;
        this.colors = cardToCopy.colors;
        this.name = cardToCopy.name;
    }
    @Override
    public void attack() {

    }

    @Override
    public void specialAttack() {

    }
}
