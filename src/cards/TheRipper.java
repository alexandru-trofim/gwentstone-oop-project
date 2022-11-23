package cards;

import fileio.CardInput;

public class TheRipper extends SpecialMinionCard {

    public TheRipper(CardInput cardInput) {
        super(cardInput);
    }
    public TheRipper(Card cardToCopy) {
        super(cardToCopy);
    }


    @Override
    public void specialAttack(Card cardAttacked) {
        System.out.printf("SPECIAL ATTACK :" + this.getName()  + " CardATTACKED "+cardAttacked.getName() +" Health:" +
                cardAttacked.getHealth() + "  Attack " + cardAttacked.getAttackDamage());
        int oldAttackDamage = cardAttacked.getAttackDamage();
            if (oldAttackDamage < 2)
                cardAttacked.setAttackDamage(0);
            else
                cardAttacked.setAttackDamage(oldAttackDamage - 2);


        this.setMadeMove(true);

        System.out.printf("SPECIAL ATTACK :" + this.getName()  + " CardATTACKED "+cardAttacked.getName() +" Health:" +
                cardAttacked.getHealth() + "  Attack " + cardAttacked.getAttackDamage());
    }
}
