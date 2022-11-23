package cards;

import fileio.CardInput;

abstract class SpecialMinionCard extends MinionCard{


    public SpecialMinionCard(Card cardToCopy) {
        super(cardToCopy);
    }

    public SpecialMinionCard(CardInput cardInput) {
        super(cardInput);
    }

    




}
