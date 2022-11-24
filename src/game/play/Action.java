package game.play;

import cards.Card;
import cards.EnvironmentCard;
import cards.HeroCard;
import com.fasterxml.jackson.databind.node.ArrayNode;
import debug.Debug;
import fileio.ActionsInput;
import fileio.Coordinates;
import game.structure.Game;
import game.structure.Player;
import lombok.Getter;
import lombok.Setter;

public class Action {
    private static final int MAX_ROW_ID = 3;
    private @Getter @Setter String command;
    private @Getter @Setter int handId;
    private @Getter @Setter Coordinates cardAttacker;
    private @Getter @Setter Coordinates cardAttacked;
    private @Getter @Setter int affectedRow;
    private @Getter @Setter int playerIdx;
    private @Getter @Setter int x;
    private @Getter @Setter int y;

    public Action(final ActionsInput actionsInput) {
        this.command = actionsInput.getCommand();
        this.handId = actionsInput.getHandIdx();
        this.affectedRow = actionsInput.getAffectedRow();
        this.playerIdx = actionsInput.getPlayerIdx();
        this.x = actionsInput.getX();
        this.y = actionsInput.getY();

        if (actionsInput.getCardAttacker() != null) {
            this.cardAttacker = new Coordinates();
            this.cardAttacker.setX(actionsInput.getCardAttacker().getX());
            this.cardAttacker.setY(actionsInput.getCardAttacker().getY());
        }
        if (actionsInput.getCardAttacked() != null) {
            this.cardAttacked = new Coordinates();
            this.cardAttacked.setX(actionsInput.getCardAttacked().getX());
            this.cardAttacked.setY(actionsInput.getCardAttacked().getY());
        }

    }

    /**
     * Places a card on the game table and removes it from the players hand
     * @param gameTable game table where the card is placed
     * @param player player which places the card
     * @param playerId the ID of the player that places the card
     * @param output output ArrayNode
     */
    public void placeCard(final Card[][] gameTable, final Player player,
                          final int playerId, final ArrayNode output) {
        int frontRow, backRow, rowToAdd;
        //get front and back rows of the player placing the card
        if (playerId == 1) {
            frontRow = 2;
            backRow = MAX_ROW_ID;
        } else {
            frontRow = 1;
            backRow = 0;
        }
        if (player.getPlayerHand().size() <= handId) {
            System.out.printf("ERROR!!! OUT OF BOUND");
            return;
        }
        String cardToPlaceName = player.getPlayerHand().get(handId).getName();
        Card cardToPlace = player.getPlayerHand().get(handId);

        if (cardToPlaceName.equals("The Ripper")  || cardToPlaceName.equals("Miraj")
                || cardToPlaceName.equals("Warden") || cardToPlaceName.equals("Goliath")) {
           rowToAdd = frontRow;
        } else {
            rowToAdd = backRow;
        }

        //Handling errors
        if (cardToPlace.getMana() > player.getMana()) {
            Debug.notEnoughManaError(handId, output);
            return;
        }
        if (cardToPlaceName.equals("Firestorm")  || cardToPlaceName.equals("Winterfell")
                || cardToPlaceName.equals("Heart Hound")) {
            Debug.environmentCardError(handId, output);
            return;
        }
        if (gameTable[rowToAdd][Game.CARDS_ON_ROW - 1] != null) {
            Debug.notEnoughSpaceError(handId, output);
            return;
        }

        int positionOnTable = 0;
        for (int i = 0; i < Game.CARDS_ON_ROW; ++i) {
           if (gameTable[rowToAdd][i] == null) {
               positionOnTable = i;
               break;
           }
        }
        //adding the card to the table and decreasing player mana
        player.setMana(player.getMana() - cardToPlace.getMana());
        gameTable[rowToAdd][positionOnTable] = player.getPlayerHand().get(handId);
        player.getPlayerHand().remove(handId);
    }

    /**
     * Uses an environment card and attacks an enemy row
     * @param playerId the ID of the player that uses the environment card
     * @param table game table where the environment card attacks
     * @param player the player that attacks
     * @param output output ArrayNode
     */
    public void useEnvironmentCard(final int playerId,
                                   final Card[][] table, final Player player,
                                   final ArrayNode output) {
        if (player.getPlayerHand().size() < handId) {
            System.out.printf("!!!OutOfBound!!!" + "\n");
            return;
        }

        Card currentCard = player.getPlayerHand().get(handId);

        if (!currentCard.getName().equals("Firestorm")
                && !currentCard.getName().equals("Winterfell")
                && !currentCard.getName().equals("Heart Hound")) {
            Debug.notEnvironmentCard(handId, affectedRow, output);
            return;
        }
        if (currentCard.getMana() > player.getMana()) {
            Debug.notEnoughManaEnvironment(handId, affectedRow, output);
            return;
        }
        if ((playerId == 1 && (affectedRow == 2 || affectedRow == MAX_ROW_ID))
                || (playerIdx == 2 && (affectedRow == 0 || affectedRow == 1))) {
            Debug.environmentAttacksOwnRow(handId, affectedRow, output);
            return;
        }

        //check if rowToInsert is full, if yes throw error
        int cardsOnRow = Game.CARDS_ON_ROW;
        int rowToInsert = affectedRow - MAX_ROW_ID;
        if (rowToInsert < 0) {
            rowToInsert = -rowToInsert;
        }
        //check if the row is full when trying to steal with Heart Hound
        if (currentCard.getName().equals("Heart Hound")
                && table[rowToInsert][cardsOnRow - 1] != null) {
            Debug.cannotStealCardFullRow(handId, affectedRow, output);
            return;
        }

        //use environment card
        final EnvironmentCard castedCard = (EnvironmentCard) currentCard;
        castedCard.attack(table, affectedRow);

        player.setMana(player.getMana() - currentCard.getMana());

        //delete environment card from hand
        player.getPlayerHand().remove(handId);

    }

    /**
     * Chcks if there are tank cards on enemy rows
     * @param table game table
     * @param frontRow front row index of the enemy
     * @param backRow back row index of the enemy
     * @return returns true if there are tanks on the lines of the
     * enemy and false otherwise
     */
    public boolean checkForTanks(final Card[][] table, final int frontRow, final int backRow) {
        int numberOfColumns = Game.CARDS_ON_ROW;
        for (int i = 0; i < numberOfColumns; ++i) {
            if (table[frontRow][i] != null) {
                if (table[frontRow][i].isTank()) {
                    return true;
                }
            }
            if (table[backRow][i] != null) {
                if (table[backRow][i].isTank()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Kills a card at a given position.
     * @param table game table
     * @param xPos x position of the card
     * @param yPos y position of the card
     */
    public void killCardAtPosition(final Card[][] table, final  int xPos, final int yPos) {
        int cardsOnRow = Game.CARDS_ON_ROW;
        for (int i = yPos; i < cardsOnRow - 1; ++i) {
           table[xPos][i] = table[xPos][i + 1];
        }
        //erase last element on the row
        table[x][cardsOnRow - 1] = null;
    }

    /**
     * Uses the attack ability of the card.
     * @param table game table
     * @param playerId the ID of the player which card attacks
     * @param output output ArrayNode
     */
    public void cardUsesAttack(final Card[][] table, final int playerId, final ArrayNode output) {
        int frontRow;
        int backRow;
        Card attacked = table[cardAttacked.getX()][cardAttacked.getY()];
        Card attacker = table[cardAttacker.getX()][cardAttacker.getY()];

        if (attacked == null || attacker == null) {
            System.out.printf("ATTACKER/ATTACKED null" + "\n");
            return;
        }
        //front and back rows of enemy
        if (playerId == 2) {
            frontRow = 2;
            backRow = MAX_ROW_ID;
        } else {
            frontRow = 1;
            backRow = 0;
        }

        if (attacker.isMadeMove()) {
            Debug.cardAlreadyAttacked(cardAttacker, cardAttacked,
                                    "cardUsesAttack", output);
            return;
        }
        if (cardAttacked.getX() != frontRow
                && cardAttacked.getX() != backRow) {
            Debug.cardIsNotEnemy(cardAttacker, cardAttacked,
                    "cardUsesAttack", output);
            return;
        }

        if (attacker.isFrozen()) {
            Debug.cardIsFrozen(cardAttacker, cardAttacked,
                   "cardUsesAttack", output);
            return;
        }

        if (checkForTanks(table, frontRow, backRow) && !attacked.isTank()) {
            Debug.cardIsNotATank(cardAttacker, cardAttacked,
                    "cardUsesAttack", output);
            return;
        }

        attacker.attack(attacked);

        if (attacked.isDead()) {
            killCardAtPosition(table, cardAttacked.getX(), cardAttacked.getY());
        }
    }

    /**
     * Uses the special ability of the card.
     * @param table game table
     * @param playerId the ID of the player that attacks
     * @param output outputArrayNode
     */
    public void cardUsesAbility(final Card[][] table, final int playerId, final ArrayNode output) {
        int frontRow;
        int backRow;
        Card attacked = table[cardAttacked.getX()][cardAttacked.getY()];
        Card attacker = table[cardAttacker.getX()][cardAttacker.getY()];

        //front and back rows of enemy
        if (playerId == 2) {
            frontRow = 2;
            backRow = MAX_ROW_ID;
        } else {
            frontRow = 1;
            backRow = 0;
        }

        if (attacker.isMadeMove()) {
            Debug.cardAlreadyAttacked(cardAttacker, cardAttacked,
                                    "cardUsesAbility", output);
            return;
        }

        if (attacker.getName().equals("Disciple")) {
            if (cardAttacked.getX() == frontRow
                    || cardAttacked.getX() == backRow) {
                Debug.cardIsEnemySpecial(cardAttacker, cardAttacked,
                        output);
                return;
            }
        }

        if (attacker.getName().equals("The Ripper")
                || attacker.getName().equals("Miraj")
                || attacker.getName().equals("The Cursed One")) {

            if (cardAttacked.getX() != frontRow
                    && cardAttacked.getX() != backRow) {
                Debug.cardIsNotEnemy(cardAttacker, cardAttacked,
                        "cardUsesAbility", output);
                return;
            }
        }

        if (attacker.isFrozen()) {
            Debug.cardIsFrozen(cardAttacker, cardAttacked,
                    "cardUsesAbility", output);
            return;
        }


        if (checkForTanks(table, frontRow, backRow) && !attacked.isTank()
            && !attacker.getName().equals("Disciple")) {

            Debug.cardIsNotATank(cardAttacker, cardAttacked,
                    "cardUsesAbility", output);
            return;
        }


        attacker.specialAttack(attacked);

        if (attacked.isDead()) {
            killCardAtPosition(table, cardAttacked.getX(), cardAttacked.getY());
        }


    }

    /**
     * Attacks with a card the hero of the enemy .
     * @param table game table
     * @param playerId the ID of the user that attacks
     * @param hero the hero card of the enemy
     * @param output output ArrayNode
     */
    public void useAtatckHero(final Card[][] table, final int playerId,
                              final Card hero, final ArrayNode output) {

        Card attacker = table[cardAttacker.getX()][cardAttacker.getY()];

        int frontRow, backRow;
        //front and back rows of enemy
        if (playerId == 2) {
            frontRow = 2;
            backRow = MAX_ROW_ID;
        } else {
            frontRow = 1;
            backRow = 0;
        }

        Coordinates thereIsNoAttacked = new Coordinates();

        if (attacker.isFrozen()) {
            Debug.cardIsFrozen(cardAttacker, thereIsNoAttacked,
                    "useAttackHero", output);
            return;
        }
        if (attacker.isMadeMove()) {
            Debug.cardAlreadyAttacked(cardAttacker, thereIsNoAttacked,
                    "useAttackHero", output);
            return;
        }
        if (checkForTanks(table, frontRow, backRow)) {
            Debug.cardIsNotATank(cardAttacker, thereIsNoAttacked,
                    "useAttackHero", output);
            return;
        }

        attacker.attack(hero);
        if (hero.isDead()) {
            Debug.gameEnd(playerId, output);
            Game.setTotalGamesPlayed(Game.getTotalGamesPlayed() + 1);

            if (playerId == 1) {
                Game.setPlayerOneWins(Game.getPlayerOneWins() + 1);
            } else {
                Game.setPlayerTwoWins(Game.getPlayerTwoWins() + 1);
            }
        }
    }

    /**
     * Uses the special ability of the hero.
     * @param table game table
     * @param player player object of the player that attacks
     * @param playerId the ID of the player that uses the hero ability
     * @param output output ArrayNode
     */
    public void useHeroAbility(final Card[][] table, final Player player,
                               final int playerId, final ArrayNode output) {
        int frontRow, backRow;
        Card hero = player.getPlayerHero();
        //front and back rows of enemy
        if (playerId == 2) {
            frontRow = 2;
            backRow = MAX_ROW_ID;
        } else {
            frontRow = 1;
            backRow = 0;
        }

        if (player.getMana() < hero.getMana()) {
            Debug.notEnoughManaHero(affectedRow, output);
            return;
        }

        if (hero.isMadeMove()) {
            Debug.heroAlreadyAttacked(affectedRow, output);
            return;
        }

        if (hero.getName().equals("Empress Thorina")
                || hero.getName().equals("Lord Royce")) {
            if (affectedRow != backRow && affectedRow != frontRow) {
                Debug.rowNotEnemyHero(affectedRow, output);
                return;
            }
        }

        if (hero.getName().equals("General Kocioraw")
                || hero.getName().equals("King Mudface")) {
            if (affectedRow == backRow || affectedRow == frontRow) {
                Debug.rowNotAllyHero(affectedRow, output);
                return;
            }
        }
        //use environment card
        HeroCard castedCard = (HeroCard) hero;
        castedCard.attack(table, affectedRow);

        player.setMana(player.getMana() - hero.getMana());
    }
}
