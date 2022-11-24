package cards;

import fileio.CardInput;

abstract class SpecialMinionCard extends MinionCard {

    SpecialMinionCard(final Card cardToCopy) {
        super(cardToCopy);
    }

    SpecialMinionCard(final CardInput cardInput) {
        super(cardInput);
    }
}
