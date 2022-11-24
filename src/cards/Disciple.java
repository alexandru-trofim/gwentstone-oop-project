package cards;

import fileio.CardInput;

public class Disciple extends SpecialMinionCard {


    public Disciple(final CardInput cardInput) {
        super(cardInput);
    }

    public Disciple(final Card cardToCopy) {
        super(cardToCopy);
    }

    /**
     * God's plan ability - increases the life of an ally card by two
     * @param cardAttacked card that will be attacked.
     */
    @Override
    public void specialAttack(final Card cardAttacked) {
        cardAttacked.setHealth(cardAttacked.getHealth() + 2);

        this.setMadeMove(true);
    }
}
