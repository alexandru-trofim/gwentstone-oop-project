package cards;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Winterfell implements Card{
    private @Getter @Setter int mana;
    private @Getter @Setter String description;
    private @Getter @Setter ArrayList<String> colors;
    private @Getter @Setter String name;

    @Override
    public void attack() {

    }

    @Override
    public void specialAttack() {

    }
}
