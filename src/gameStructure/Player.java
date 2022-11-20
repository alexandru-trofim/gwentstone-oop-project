package gameStructure;

import cards.Card;
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
    private @Getter @Setter Card playerHero;
    private @Getter @Setter ArrayList<Card> playerHand;

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

    public void initializeActiveDeck(int deckId) {
        this.ActiveDeck =  new Deck(this.decks.get(deckId));
    }

    public void getCardFromDeck() {
        if (getActiveDeck().getCards().size() > 0) {
            //add first card from activeDeck
            playerHand.add(getActiveDeck().getCards().get(0));
            //remove it from deck
            getActiveDeck().getCards().remove(0);
            //decrement the number of card in deck
            getActiveDeck().setNrCardsInDeck(getActiveDeck().getNrCardsInDeck() - 1);
        }
    }

    public void getNewHand() {
        this.playerHand = new ArrayList<>();
    }


}
