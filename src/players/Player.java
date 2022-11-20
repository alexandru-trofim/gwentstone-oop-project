package players;

import fileio.DecksInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Player {
    private @Getter @Setter int nrCardsInDeck;
    private @Getter @Setter int nrDecks;
    private @Getter @Setter ArrayList<Deck> decks;
    private @Getter @Setter int mana;
    private @Getter @Setter Deck ActiveDeck;

    public Player(DecksInput decksInput) {
        this.nrCardsInDeck = decksInput.getNrCardsInDeck();
        this.nrDecks = decksInput.getNrDecks();
        this.mana = 0;
        this.decks = new ArrayList<Deck>();

        //iterate through Arraylist<ArrayList<CardInput>> to create all decks
        for(int i = 0; i < this.nrDecks; ++i) {
           Deck deckToAdd = new Deck(decksInput.getDecks().get(i));
           this.decks.add(deckToAdd);
        }
    }




}
