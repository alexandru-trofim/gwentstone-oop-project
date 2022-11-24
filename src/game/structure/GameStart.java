package game.structure;

import cards.Card;
import cards.EmpressThorina;
import cards.GeneralKocioraw;
import cards.KingMudface;
import cards.LordRoyce;
import fileio.CardInput;
import fileio.StartGameInput;
import lombok.Getter;
import lombok.Setter;

public class GameStart {
    private @Getter @Setter int playerOneDeckId;
    private @Getter @Setter int playerTwoDeckId;
    private @Getter @Setter int shuffleSeed;
    private @Getter @Setter Card playerOneHero;
    private @Getter @Setter Card playerTwoHero;
    private @Getter @Setter int startingPlayer;

    /**
     * Gets a CardInput object as a parameter and creates
     * an object that extends Card depending on it's name
     * @param card input information of a card
     * @return returns a Card object
     */
    public Card getRightHeroCard(final CardInput card) {

        return switch (card.getName()) {
            case "King Mudface" -> new KingMudface(card);
            case "Lord Royce" -> new LordRoyce(card);
            case "Empress Thorina" -> new EmpressThorina(card);
            case "General Kocioraw" -> new GeneralKocioraw(card);
            default -> null;
        };
    }

    public GameStart(final StartGameInput startGame) {
        this.playerOneDeckId = startGame.getPlayerOneDeckIdx();
        this.playerTwoDeckId = startGame.getPlayerTwoDeckIdx();
        this.shuffleSeed = startGame.getShuffleSeed();
        this.playerOneHero = getRightHeroCard(startGame.getPlayerOneHero());
        this.playerTwoHero = getRightHeroCard(startGame.getPlayerTwoHero());
        this.startingPlayer = startGame.getStartingPlayer();
        }
}
