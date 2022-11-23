package cards;

import fileio.CardInput;

public class Disciple extends SpecialMinionCard {


    public Disciple(CardInput cardInput) {
        super(cardInput);
    }

    public Disciple(Card cardToCopy) {
        super(cardToCopy);
    }



    @Override
    public void specialAttack(Card cardAttacked) {
        System.out.printf("SPECIAL ATTACK :" + this.getName()  + " CardATTACKED "+cardAttacked.getName() +" Health:" +
                cardAttacked.getHealth() + "  Attack " + cardAttacked.getAttackDamage());

        cardAttacked.setHealth(cardAttacked.getHealth() + 2);

        this.setMadeMove(true);

        System.out.printf("SPECIAL ATTACK :" + this.getName()  + " CardATTACKED "+cardAttacked.getName() +" Health:" +
                cardAttacked.getHealth() + "  Attack " + cardAttacked.getAttackDamage());
    }
}
