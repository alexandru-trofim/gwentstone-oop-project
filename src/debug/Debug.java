package debug;

import cards.Card;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.Coordinates;
import game.structure.Deck;
import game.structure.Game;

import java.util.ArrayList;

public class Debug {

    /**
     * Outputs the player deck given as a parameter
     * @param playerDeck the deck that will be printed
     * @param output output ArrayNode
     * @param playerId current player ID
     */
    public static void getPlayerDeck(final Deck playerDeck, final ArrayNode output,
                                     final int playerId) {
        ObjectNode commandOutput = output.objectNode();
        commandOutput.put("command", "getPlayerDeck");
        commandOutput.put("playerIdx", playerId);

        ArrayNode deckOutput = commandOutput.putArray("output");
        for (Card card: playerDeck.getCards()) {
            ObjectNode cardOutput = deckOutput.objectNode();

            card.convertCardToJson(cardOutput);
            deckOutput.add(cardOutput);

        }

        output.add(commandOutput);

    }

    /**
     * Outputs the player hero given as a parameter.
     * @param heroCard hero card
     * @param output output ArrayNode
     * @param playerId current player ID
     */
    public static void getPlayerHero(final Card heroCard, final ArrayNode output,
                                     final int playerId) {
        ObjectNode commandOutput = output.objectNode();

        commandOutput.put("command", "getPlayerHero");
        commandOutput.put("playerIdx", playerId);

        ObjectNode hero = commandOutput.putObject("output");
        heroCard.convertCardToJson(hero);

       output.add(commandOutput);
    }

    /**
     * Outputs the player ID of the player that has the turn.
     * @param playerToMove player ID of the current player
     * @param output output ArrayNode
     */
    public static void getPlayerTurn(final int playerToMove, final ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();

        commandOutput.put("command", "getPlayerTurn");
        commandOutput.put("output", playerToMove);

        output.add(commandOutput);
    }

    /**
     * Outputs the cards in the hand of the player that has the turn.
     * @param playerHand player hand
     * @param output output ArrayNode
     * @param playerId the ID of the player that has the turn
     */
    public static void getCardsInHand(final ArrayList<Card> playerHand, final ArrayNode output,
                                      final int playerId) {
        ObjectNode commandOutput = output.objectNode();

        commandOutput.put("command", "getCardsInHand");
        commandOutput.put("playerIdx", playerId);

        ArrayNode handOutput = commandOutput.putArray("output");

        for (Card card: playerHand) {
            ObjectNode cardOutput = handOutput.objectNode();

            card.convertCardToJson(cardOutput);
            handOutput.add(cardOutput);
        }

        output.add(commandOutput);
    }

    /**
     * Outputs the mana of the player given as a parameter
     * @param mana mana that will be outputed
     * @param output output ArrayNode
     * @param playerId the ID of the player
     */
    public static void getPlayerMana(final int mana, final ArrayNode output, final  int playerId) {
       ObjectNode commandOutput = output.objectNode();

       commandOutput.put("command", "getPlayerMana");
       commandOutput.put("playerIdx", playerId);
       commandOutput.put("output", mana);

        output.add(commandOutput);
    }

    /**
     * Outputs all the card on the table
     * @param table Game table
     * @param output output ArrayNode
     */
    public static void getCardsOnTable(final Card[][] table, final ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();
        commandOutput.put("command", "getCardsOnTable");

        ArrayNode deckOutput = commandOutput.putArray("output");
        for (int i = 0; i < Game.NR_OF_ROWS; i++) {
            ArrayNode row = commandOutput.arrayNode();
            for (int j = 0; j < Game.CARDS_ON_ROW; j++) {
                if (table[i][j] != null) {
                    ObjectNode cardOutput = row.objectNode();

                    table[i][j].convertCardToJson(cardOutput);
                    row.add(cardOutput);
                }
            }
            deckOutput.add(row);
        }
        output.add(commandOutput);
    }

    /**
     * Outputs error when a player want to use an environment card but the card
     * chosen is not envirnoment
     * @param handIdx the id of the card in hand
     * @param output output ArrayNode
     */
    public static void environmentCardError(final int handIdx, final ArrayNode output) {
       ObjectNode commandOutput = output.objectNode();
       commandOutput.put("command", "placeCard");
       commandOutput.put("handIdx", handIdx);
       commandOutput.put("error", "Cannot place environment card on table.");

       output.add(commandOutput);
    }

    /**
     * Outputs error when not enough mana to use a card
     * @param handIdx the id of the card in hand
     * @param output output ArrayNode
     */
    public static void notEnoughManaError(final int handIdx, final ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();
        commandOutput.put("command", "placeCard");
        commandOutput.put("handIdx", handIdx);
        commandOutput.put("error", "Not enough mana to place card on table.");
        output.add(commandOutput);
    }

    /**
     * Outputs error when not enough space to place a card
     * @param handIdx the id of the card in hand
     * @param output output ArrayNode
     */
    public static void notEnoughSpaceError(final int handIdx, final  ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();
        commandOutput.put("command", "placeCard");
        commandOutput.put("handIdx", handIdx);
        commandOutput.put("error", "Cannot place card on table since row is full.");
        output.add(commandOutput);
    }

    /**
     * Outputs all the environment cards of a player hand given as a parameter
     * @param playerHand the hand of the active player
     * @param output outputArraylist
     * @param playerId the ID of the player
     */
    public static void getEnvironmentCardsInHand(final ArrayList<Card> playerHand,
                                                 final ArrayNode output, final int playerId) {
        ObjectNode commandOutput = output.objectNode();
        commandOutput.put("command", "getEnvironmentCardsInHand");
        commandOutput.put("playerIdx", playerId);

            ArrayNode handOutput = commandOutput.putArray("output");

            for (Card card: playerHand) {
                //check if is Environment type
                if (card.getName().equals("Firestorm") || card.getName().equals("Winterfell")
                        || card.getName().equals("Heart Hound")) {
                    ObjectNode cardOutput = handOutput.objectNode();
                    card.convertCardToJson(cardOutput);
                    handOutput.add(cardOutput);
                }
            }
            output.add(commandOutput);
    }

    /**
     * Outputs the card on that table that is on the positon given as input.
     * @param x x coordinate
     * @param y y coordinate
     * @param table Game table
     * @param output output ArrayNode
     */
    public static void getCardAtPosition(final int x, final int y, final Card[][] table,
                                         final ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();
        commandOutput.put("command", "getCardAtPosition");
        commandOutput.put("x", x);
        commandOutput.put("y", y);


        if (table[x][y] != null) {
            ObjectNode cardOutput = commandOutput.putObject("output");
            table[x][y].convertCardToJson(cardOutput);
        } else {
            commandOutput.put("output", "No card available at that position.");
        }
        output.add(commandOutput);
    }

    /**
     *Outputs error when a player want to use an environment card but the card.
     * @param handIdx the id of the card in hand
     * @param affectedRow the row affected by the environment card
     * @param output output ArrayNode
     */
    public static void notEnvironmentCard(final int handIdx, final int affectedRow,
                                          final ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();
        commandOutput.put("command", "useEnvironmentCard");
        commandOutput.put("handIdx", handIdx);
        commandOutput.put("affectedRow", affectedRow);
        commandOutput.put("error", "Chosen card is not of type environment.");

        output.add(commandOutput);
    }

    /**
     * Outputs error when a player tries to use an environment
     * card but hasn't enough mana.
     * @param handId the ID of the card in hand
     * @param affectedRow the attacked row by the envirnoment card
     * @param output output ArrayNode
     */
    public static void notEnoughManaEnvironment(final int handId, final int affectedRow,
                                                final ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();
        commandOutput.put("command", "useEnvironmentCard");
        commandOutput.put("handIdx", handId);
        commandOutput.put("affectedRow", affectedRow);
        commandOutput.put("error", "Not enough mana to use environment card.");

        output.add(commandOutput);

    }

    /**
     * Outputs an errror when the player tries to attack his own
     * row with an environment card.
     * @param handId the ID of the card in hand
     * @param affectedRow the row that is attacked by the environment card
     * @param output output ArrayNode
     */
    public static void environmentAttacksOwnRow(final int handId, final int affectedRow,
                                                final ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();
        commandOutput.put("command", "useEnvironmentCard");
        commandOutput.put("handIdx", handId);
        commandOutput.put("affectedRow", affectedRow);
        commandOutput.put("error", "Chosen row does not belong to the enemy.");

        output.add(commandOutput);
    }

    /**
     * Outputs an error when a player tries to steal a card from enemy.
     * but has a full row
     * @param handId the ID of the
     * @param affectedRow the row attacked bu the environment card
     * @param output output ArrayNode
     */
    public static void cannotStealCardFullRow(final int handId, final int affectedRow,
                                              final ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();
        commandOutput.put("command", "useEnvironmentCard");
        commandOutput.put("handIdx", handId);
        commandOutput.put("affectedRow", affectedRow);
        commandOutput.put("error", "Cannot steal enemy card since the player's row is full.");

        output.add(commandOutput);
    }

    /**
     * A helper function that puts the command name to the output.
     * @param commandName the name of the command
     * @param commandOutput output
     */
    private static void getRightCommandName(final String commandName,
                                           final ObjectNode commandOutput) {
        switch (commandName) {
            case "cardUsesAttack":
                commandOutput.put("command", "cardUsesAttack");
                break;
            case "cardUsesAbility":
                commandOutput.put("command", "cardUsesAbility");
                break;
            case "useAttackHero":
                commandOutput.put("command", "useAttackHero");
                break;
            default:
                break;

        }
    }

    /**
     * Output all the frozen cards on the table.
     * @param table Game table
     * @param output output ArrayNode
     */
    public static void getFrozenCardsOnTable(final Card[][] table, final ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();
        commandOutput.put("command", "getFrozenCardsOnTable");

        ArrayNode deckOutput = commandOutput.putArray("output");
        for (int i = 0; i < Game.NR_OF_ROWS; i++) {
            for (int j = 0; j < Game.CARDS_ON_ROW; j++) {
                if (table[i][j] != null && table[i][j].isFrozen()) {
                    ObjectNode cardOutput = deckOutput.objectNode();

                    table[i][j].convertCardToJson(cardOutput);
                    deckOutput.add(cardOutput);
                }
            }
        }
        output.add(commandOutput);
    }

    /**
     * Outputs an error when a player attacks a card or a hero and
     * there are tanks on the table that he should attack first.
     * @param cardAttacker coordinates of the attacker card
     * @param cardAttacked coordinates of the attacked card
     * @param commandName command name
     * @param output output ArrayNode
     */
    public static void cardIsNotATank(final Coordinates cardAttacker,
                                       final Coordinates cardAttacked,
                                       final String commandName,  final ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();
        getRightCommandName(commandName, commandOutput);

        ObjectNode attacker = commandOutput.putObject("cardAttacker");
        attacker.put("x", cardAttacker.getX());
        attacker.put("y", cardAttacker.getY());

        if (!commandName.equals("useAttackHero")) {
            ObjectNode attacked = commandOutput.putObject("cardAttacked");
            attacked.put("x", cardAttacked.getX());
            attacked.put("y", cardAttacked.getY());
        }
        commandOutput.put("error", "Attacked card is not of type 'Tank'.");

        output.add(commandOutput);
    }

    /**
     * Outputs an error when a player attacks an ally.
     * @param cardAttacker coordinates of the attacker card
     * @param cardAttacked coordinates of the attacked card
     * @param commandName command name
     * @param output output ArrayNode
     */
    public static void cardIsNotEnemy(final Coordinates cardAttacker,
                                      final Coordinates cardAttacked,
                                      final String commandName, final ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();
        getRightCommandName(commandName, commandOutput);

        ObjectNode attacker = commandOutput.putObject("cardAttacker");
        attacker.put("x", cardAttacker.getX());
        attacker.put("y", cardAttacker.getY());

        if (!commandName.equals("useAttackHero")) {
            ObjectNode attacked = commandOutput.putObject("cardAttacked");
            attacked.put("x", cardAttacked.getX());
            attacked.put("y", cardAttacked.getY());
        }

        commandOutput.put("error", "Attacked card does not belong to the enemy.");

        output.add(commandOutput);
    }

    /**
     * Outputs an error when a player attacks with a frozen card.
     * @param cardAttacker coordinates of the attacker card
     * @param cardAttacked coordinates of the attacked card
     * @param commandName command name
     * @param output output ArrayNode
     */
    public static void cardIsFrozen(final Coordinates cardAttacker, final Coordinates cardAttacked,
                                     final String commandName, final ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();
        getRightCommandName(commandName, commandOutput);

        ObjectNode attacker = commandOutput.putObject("cardAttacker");
        attacker.put("x", cardAttacker.getX());
        attacker.put("y", cardAttacker.getY());

        if (!commandName.equals("useAttackHero")) {
            ObjectNode attacked = commandOutput.putObject("cardAttacked");
            attacked.put("x", cardAttacked.getX());
            attacked.put("y", cardAttacked.getY());
        }

        commandOutput.put("error", "Attacker card is frozen.");
        output.add(commandOutput);
    }

    /**
     * Outputs an error when a player attacks with a card that already
     * made a move this turn.
     * @param cardAttacker coordinates of the attacker card
     * @param cardAttacked coordinates of the attacked card
     * @param commandName command name
     * @param output output ArrayNode
     */
    public static void cardAlreadyAttacked(final Coordinates cardAttacker,
                                           final Coordinates cardAttacked,
                                           final String commandName, final ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();
        getRightCommandName(commandName, commandOutput);

        ObjectNode attacker = commandOutput.putObject("cardAttacker");
        attacker.put("x", cardAttacker.getX());
        attacker.put("y", cardAttacker.getY());

        if (!commandName.equals("useAttackHero")) {
            ObjectNode attacked = commandOutput.putObject("cardAttacked");
            attacked.put("x", cardAttacked.getX());
            attacked.put("y", cardAttacked.getY());
        }
        commandOutput.put("error", "Attacker card has already attacked this turn.");
        output.add(commandOutput);
    }
    /**
     * Outputs an error when a player uses an ability that should
     * apply on allies on an enemy.
     * @param cardAttacker coordinates of the attacker card
     * @param cardAttacked coordinates of the attacked card
     * @param output output ArrayNode
     */
    public static void cardIsEnemySpecial(final Coordinates cardAttacker,
                                          final Coordinates cardAttacked,
                                          final ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();
        commandOutput.put("command", "cardUsesAbility");

        ObjectNode attacker = commandOutput.putObject("cardAttacker");
        attacker.put("x", cardAttacker.getX());
        attacker.put("y", cardAttacker.getY());

        ObjectNode attacked = commandOutput.putObject("cardAttacked");
        attacked.put("x", cardAttacked.getX());
        attacked.put("y", cardAttacked.getY());

        commandOutput.put("error", "Attacked card does not belong to the current player.");

        output.add(commandOutput);
    }

    /**
     * Outputs a message when one of the players wins
     * @param currentPlayer The player that has the turn
     * @param output output ArrayNode
     */
    public static void gameEnd(final int currentPlayer, final ArrayNode output) {

        ObjectNode commandOutput = output.objectNode();
        if (currentPlayer == 1) {
            commandOutput.put("gameEnded", "Player one killed the enemy hero.");
        } else {
            commandOutput.put("gameEnded", "Player two killed the enemy hero.");
        }

        output.add(commandOutput);
    }

    /**
     * Outputs a message when one of the players wins
     * @param affectedRow the row affected by hero attack
     * @param output output ArrayNode
     */
    public static void notEnoughManaHero(final int affectedRow, final ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();

        commandOutput.put("command", "useHeroAbility");
        commandOutput.put("affectedRow", affectedRow);
        commandOutput.put("error", "Not enough mana to use hero's ability.");

        output.add(commandOutput);
    }

    /**
     * Outputs an error when a player wants to attack but already made a move
     * @param affectedRow the row affected by hero attack
     * @param output output ArrayNode
     */
    public static void heroAlreadyAttacked(final int affectedRow, final ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();

        commandOutput.put("command", "useHeroAbility");
        commandOutput.put("affectedRow", affectedRow);
        commandOutput.put("error", "Hero has already attacked this turn.");

        output.add(commandOutput);
    }

    /**
     * Outputs an error when a player wants to attack an ally
     * @param affectedRow the row affected by hero attack
     * @param output output ArrayNode
     */
    public static void rowNotEnemyHero(final int affectedRow, final ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();

        commandOutput.put("command", "useHeroAbility");
        commandOutput.put("affectedRow", affectedRow);
        commandOutput.put("error", "Selected row does not belong to the enemy.");

        output.add(commandOutput);
    }

    /**
     * Outputs an error when a player wants to attack an enemy
     * @param affectedRow the row affected by hero attack
     * @param output output ArrayNode
     */
    public static void rowNotAllyHero(final int affectedRow, final ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();

        commandOutput.put("command", "useHeroAbility");
        commandOutput.put("affectedRow", affectedRow);
        commandOutput.put("error", "Selected row does not belong to the current player.");

        output.add(commandOutput);
    }
    /**
     * Outputs the total number of games that player one won
     * @param output output ArrayNode
     */
    public static void getPlayerOneWins(final ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();

        commandOutput.put("command", "getPlayerOneWins");
        commandOutput.put("output", Game.getPlayerOneWins());

        output.add(commandOutput);
    }

    /**
     * Outputs the total number of games that player two won
     * @param output output ArrayNode
     */
    public static void getPlayerTwoWins(final ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();

        commandOutput.put("command", "getPlayerTwoWins");
        commandOutput.put("output", Game.getPlayerTwoWins());

        output.add(commandOutput);
    }

    /**
     * Outputs the total number of games played
     * @param output output ArrayNode
     */
    public static void getTotalGamesPlayed(final ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();

        commandOutput.put("command", "getTotalGamesPlayed");
        commandOutput.put("output", Game.getTotalGamesPlayed());

        output.add(commandOutput);
    }

}
