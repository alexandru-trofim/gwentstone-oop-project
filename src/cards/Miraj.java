package cards;

import fileio.CardInput;

public class Miraj extends SpecialMinionCard {



    public Miraj(CardInput cardInput) {
        super(cardInput);
    }
    public Miraj(Card cardToCopy) {
        super(cardToCopy);
    }



    @Override
    public void specialAttack(Card cardAttacked) {
        System.out.printf("SPECIAL ATTACK :" + this.getName()  + " CardATTACKED "+cardAttacked.getName() +" Health:" +
                cardAttacked.getHealth() + "  Attack " + cardAttacked.getAttackDamage());

        int aux = this.getHealth();
        this.setHealth(cardAttacked.getHealth());
        cardAttacked.setHealth(aux);

        this.setMadeMove(true);

        System.out.printf("SPECIAL ATTACK :" + this.getName()  + " CardATTACKED "+cardAttacked.getName() +" Health:" +
                cardAttacked.getHealth() + "  Attack " + cardAttacked.getAttackDamage());
    }
}
