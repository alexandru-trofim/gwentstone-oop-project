package game.structure;

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
    private @Getter @Setter Deck activeDeck;
    private @Getter @Setter Card playerHero;
    private @Getter @Setter ArrayList<Card> playerHand;
    private @Getter @Setter int madeMove;
    public Player(final DecksInput decksInput) {
        this.nrCardsInDeck = decksInput.getNrCardsInDeck();
        this.nrDecks = decksInput.getNrDecks();
        this.mana = 0;
        this.decks = new ArrayList<Deck>();
        this.madeMove = 0;
        //iterate through Arraylist<ArrayList<CardInput>> to create all decks
        for (int i = 0; i < this.nrDecks; ++i) {
           Deck deckToAdd = new Deck(decksInput.getDecks().get(i));
           this.decks.add(deckToAdd);
        }
    }

    /**
     * Initializes the player active deck by deep copying
     * a deck from the player's array of decks
     *
     * @param deckId the ID of the deck from the player's array of decks
     */
    public void initializeActiveDeck(final int deckId) {
        this.activeDeck = new Deck(this.decks.get(deckId));
    }

    /**
     * Puts a card from the active deck of the player in its hand at the end of the turn
     */
    public void getCardFromDeck() {
        if (getActiveDeck().getCards().size() > 0) {
            //add first card from activeDeck
            playerHand.add(getActiveDeck().getCards().get(0));
            //remove it from deck
            getActiveDeck().getCards().remove(0);
            //decrement the number of card in deck
            getActiveDeck().setNrCardsInDeck(getActiveDeck().getNrCardsInDeck() - 1);
        } else {
            System.out.printf("NOT ENOUGH CARDS \n");
        }

    }

    /**
     * Reinitializes the hand of the player when a new game begins
     */
    public void getNewHand() {
        this.playerHand = new ArrayList<>();
    }



}
