package cards;

import fileio.CardInput;

public class MinionCard extends Card {

    public  MinionCard() {

    }

    public MinionCard(CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setHealth(cardInput.getHealth());
        this.setAttackDamage(cardInput.getAttackDamage());
        this.setDescription(cardInput.getDescription());
        this.setColors(cardInput.getColors());
        this.setName(cardInput.getName());
        this.setFrozen(false);
        this.setMadeMove(false);
        if (this.getName().equals("Goliath")  || this.getName().equals("Warden")) {
            this.setTank(true);
        } else {
            this.setTank(false);
        }
    }

    public MinionCard(Card cardToCopy) {
        this.setMana(cardToCopy.getMana());
        this.setHealth(cardToCopy.getHealth());
        this.setAttackDamage(cardToCopy.getAttackDamage());
        this.setDescription(cardToCopy.getDescription());
        this.setColors(cardToCopy.getColors());
        this.setName(cardToCopy.getName());
        this.setTank(cardToCopy.isTank());
        this.setFrozen(cardToCopy.isFrozen());
        this.setMadeMove(false);
    }


    @Override
    public void specialAttack(Card cardAttacked) {

    }






}
