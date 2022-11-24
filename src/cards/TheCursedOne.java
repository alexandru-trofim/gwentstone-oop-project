package cards;

import fileio.CardInput;

public class TheCursedOne extends SpecialMinionCard {

    public TheCursedOne(final CardInput cardInput) {
        super(cardInput);
    }

    public TheCursedOne(final Card cardToCopy) {
        super(cardToCopy);
    }

    /**
     * Shapeshift: swaps the health with the damage of the attacked card.
     * @param cardAttacked card that will be attacked.
     */
    @Override
    public void specialAttack(final Card cardAttacked) {
        int aux = cardAttacked.getAttackDamage();
        cardAttacked.setAttackDamage(cardAttacked.getHealth());
        cardAttacked.setHealth(aux);

        this.setMadeMove(true);
    }
}
