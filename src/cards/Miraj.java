package cards;

import fileio.CardInput;

public class Miraj extends SpecialMinionCard {

    public Miraj(final CardInput cardInput) {
        super(cardInput);
    }
    public Miraj(final Card cardToCopy) {
        super(cardToCopy);
    }

    /**
     * Skyjack: swaps the health between the card that used the attack
     * and the card that was attacked.
     * @param cardAttacked card that will be attacked.
     */
    @Override
    public void specialAttack(final Card cardAttacked) {
        int aux = this.getHealth();
        this.setHealth(cardAttacked.getHealth());
        cardAttacked.setHealth(aux);

        this.setMadeMove(true);
    }
}
