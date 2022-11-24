package cards;

import fileio.CardInput;

public class TheRipper extends SpecialMinionCard {

    public TheRipper(final CardInput cardInput) {
        super(cardInput);
    }
    public TheRipper(final Card cardToCopy) {
        super(cardToCopy);
    }

    /**
     * Weak Knees: Decreases the health of the attacked card by 2.
     * @param cardAttacked card that will be attacked.
     */
    @Override
    public void specialAttack(final Card cardAttacked) {
        int oldAttackDamage = cardAttacked.getAttackDamage();
            if (oldAttackDamage < 2) {
                cardAttacked.setAttackDamage(0);
            } else {
                cardAttacked.setAttackDamage(oldAttackDamage - 2);
            }

        this.setMadeMove(true);
    }
}
