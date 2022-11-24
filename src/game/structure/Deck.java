package game.structure;

import cards.Card;
import cards.Disciple;
import cards.Firestorm;
import cards.HeartHound;
import cards.MinionCard;
import cards.Miraj;
import cards.TheCursedOne;
import cards.TheRipper;
import cards.Winterfell;
import fileio.CardInput;
import lombok.Setter;
import lombok.Getter;

import java.util.ArrayList;

public class Deck {
    private @Getter @Setter int nrCardsInDeck;
    private @Getter @Setter ArrayList<Card> cards;

    public Card getRightCard(final CardInput card) {
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

    public Card copyCard(final Card card) {
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

    public Deck(final ArrayList<CardInput> cardsToAdd) {
        this.nrCardsInDeck = cardsToAdd.size();
        this.cards = new ArrayList<Card>();

        for (CardInput iter: cardsToAdd) {
            Card cardToAdd = getRightCard(iter);
            this.cards.add(cardToAdd);
        }
    }
    public Deck(final Deck deckToCopy) {
        this.nrCardsInDeck = deckToCopy.getNrCardsInDeck();
        this.cards = new ArrayList<Card>();

        for (Card cardToAdd: deckToCopy.getCards()) {
            Card newCard = copyCard(cardToAdd);
            this.cards.add(newCard);
        }
    }
}
