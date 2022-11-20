package players;

import cards.*;
import fileio.CardInput;
import fileio.DecksInput;
import lombok.Setter;
import lombok.Getter;

import java.util.ArrayList;

public class Deck {
    private @Getter @Setter int nrCardsInDeck;
    private @Getter @Setter ArrayList<Card> cards;

    public Card getRightCard(CardInput card) {
        return switch (card.getName()) {
            case "The Ripper" -> new TheRipper(card);
            case "Miraj" -> new Miraj(card);
            case "The Cursed One" -> new TheCursedOne(card);
            case "Disciple" -> new Disciple(card);
            case "Firestorm" -> new Firestorm(card);
            case "Winterfell" -> new Winterfell(card);
            case "Heart Hound" -> new HeartHound(card);
            default -> new MinionCard(card);
        };
    }

    public Card copyCard(MinionCard card) {
        return switch (card.getName()) {
            case "The Ripper" -> new TheRipper(card);
            case "Miraj" -> new Miraj(card);
            case "The Cursed One" -> new TheCursedOne(card);
            case "Disciple" -> new Disciple(card);
            case "Firestorm" -> new Firestorm(card);
            case "Winterfell" -> new Winterfell(card);
            case "Heart Hound" -> new HeartHound(card);
            default -> new MinionCard(card);
        };

    }

    public Deck(ArrayList<CardInput> cardsToAdd) {

        this.nrCardsInDeck = cardsToAdd.size();

        this.cards = new ArrayList<Card>();

        for(CardInput iter: cardsToAdd) {
            Card cardToAdd = getRightCard(iter);
            this.cards.add(cardToAdd);
        }

    }
    public Deck(Deck deckToCopy) {
        this.nrCardsInDeck = deckToCopy.getNrCardsInDeck();
        this.cards = new ArrayList<Card>();

        for(Card cardToAdd: deckToCopy.getCards()) {
            Card newCard =
        }
    }

}
