package cards;

import fileio.CardInput;

public class TheCursedOne extends SpecialMinionCard {




    public TheCursedOne(CardInput cardInput) {
        super(cardInput);
    }

    public TheCursedOne(Card cardToCopy) {
        super(cardToCopy);
    }


    @Override
    public void specialAttack(Card cardAttacked) {
        System.out.printf("SPECIAL ATTACK :" + this.getName()  + " CardATTACKED "+cardAttacked.getName() +" Health:" +
                cardAttacked.getHealth() + "  Attack " + cardAttacked.getAttackDamage());

        int aux = cardAttacked.getAttackDamage();
        cardAttacked.setAttackDamage(cardAttacked.getHealth());
        cardAttacked.setHealth(aux);

        System.out.printf("SPECIAL ATTACK :" + this.getName()  + " CardATTACKED "+cardAttacked.getName() +" Health:" +
                cardAttacked.getHealth() + "  Attack " + cardAttacked.getAttackDamage());

        this.setMadeMove(true);
    }
}
