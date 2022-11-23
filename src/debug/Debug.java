package debug;

import cards.Card;
import cards.HeroCard;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.Coordinates;
import gameStructure.Deck;
import gameStructure.Game;
import gameStructure.Player;

import java.awt.image.AreaAveragingScaleFilter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Debug {

    public static void getPlayerDeck(Deck playerDeck, ArrayNode output, int playerId) {
        ObjectNode commandOutput = output.objectNode();
        commandOutput.put("command", "getPlayerDeck");
        commandOutput.put("playerIdx",playerId);

        ArrayNode deckOutput= commandOutput.putArray("output");
        for(Card card: playerDeck.getCards()) {
            ObjectNode cardOutput = deckOutput.objectNode();

            card.convertCardToJson(cardOutput);
            deckOutput.add(cardOutput);

        }

        output.add(commandOutput);

    }

    public static void getPlayerHero(Card heroCard, ArrayNode output, int playerId) {
        ObjectNode commandOutput = output.objectNode();

        commandOutput.put("command", "getPlayerHero");
        commandOutput.put("playerIdx",playerId);

        ObjectNode hero = commandOutput.putObject("output");
        heroCard.convertCardToJson(hero);

       output.add(commandOutput);
    }

    public static void getPlayerTurn(int playerToMove, ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();

        commandOutput.put("command", "getPlayerTurn");
        commandOutput.put("output", playerToMove);

        output.add(commandOutput);
    }

    public static void getCardsInHand(ArrayList<Card> playerHand,ArrayNode output, int playerId) {
        ObjectNode commandOutput = output.objectNode();

        commandOutput.put("command", "getCardsInHand");
        commandOutput.put("playerIdx", playerId);

        ArrayNode handOutput= commandOutput.putArray("output");

        for(Card card: playerHand) {
            ObjectNode cardOutput = handOutput.objectNode();

            card.convertCardToJson(cardOutput);
            handOutput.add(cardOutput);
        }

        output.add(commandOutput);
    }

    public static void getPlayerMana(int mana,ArrayNode output, int playerId) {
       ObjectNode commandOutput = output.objectNode();

       commandOutput.put("command", "getPlayerMana");
       commandOutput.put("playerIdx", playerId);
       commandOutput.put("output", mana);

        output.add(commandOutput);
    }

    public static void getCardsOnTable(Card[][] table, ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();
        commandOutput.put("command", "getCardsOnTable");

        ArrayNode deckOutput = commandOutput.putArray("output");
        for(int i = 0; i < 4; i++) {
            ArrayNode row = commandOutput.arrayNode();
            for (int j = 0; j < 5; j++) {
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

    public static void environmentCardError(int handIdx, ArrayNode output) {
       ObjectNode commandOutput = output.objectNode();
       commandOutput.put("command", "placeCard");
       commandOutput.put("handIdx", handIdx);
       commandOutput.put("error", "Cannot place environment card on table.");

       output.add(commandOutput);
    }
    public static void notEnoughManaError(int handIdx, ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();
        commandOutput.put("command", "placeCard");
        commandOutput.put("handIdx", handIdx);
        commandOutput.put("error", "Not enough mana to place card on table.");
        output.add(commandOutput);
    }
    public static void notEnoughSpaceError(int handIdx, ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();
        commandOutput.put("command", "placeCard");
        commandOutput.put("handIdx", handIdx);
        commandOutput.put("error", "Cannot place card on table since row is full.");
        output.add(commandOutput);
    }

    public static void getEnvironmentCardsInHand(ArrayList<Card> playerHand, ArrayNode output, int playerId) {
        ObjectNode commandOutput = output.objectNode();
        commandOutput.put("command", "getEnvironmentCardsInHand");
        commandOutput.put("playerIdx", playerId);

            ArrayNode handOutput= commandOutput.putArray("output");

            for(Card card: playerHand) {
                //check if is Environment type
                if( card.getName().equals("Firestorm") || card.getName().equals("Winterfell") ||
                        card.getName().equals("Heart Hound")) {
                    ObjectNode cardOutput = handOutput.objectNode();
                    card.convertCardToJson(cardOutput);
                    handOutput.add(cardOutput);
                }
            }
            output.add(commandOutput);
    }

    public static void getCardAtPosition(int x, int y, Card[][] table, ArrayNode output) {
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

    public static void notEnvironmentCard(int handId, int affectedRow, ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();
        commandOutput.put("command", "useEnvironmentCard");
        commandOutput.put("handIdx", handId);
        commandOutput.put("affectedRow", affectedRow);
        commandOutput.put("error", "Chosen card is not of type environment.");

        output.add(commandOutput);

    }

    public static void notEnoughManaEnvironment(int handId, int affectedRow, ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();
        commandOutput.put("command", "useEnvironmentCard");
        commandOutput.put("handIdx", handId);
        commandOutput.put("affectedRow", affectedRow);
        commandOutput.put("error", "Not enough mana to use environment card.");

        output.add(commandOutput);

    }

    public static void environmentAttacksOwnRow(int handId, int affectedRow, ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();
        commandOutput.put("command", "useEnvironmentCard");
        commandOutput.put("handIdx", handId);
        commandOutput.put("affectedRow", affectedRow);
        commandOutput.put("error", "Chosen row does not belong to the enemy.");

        output.add(commandOutput);

    }

    public static void cannotStealCardFullRow(int handId, int affectedRow, ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();
        commandOutput.put("command", "useEnvironmentCard");
        commandOutput.put("handIdx", handId);
        commandOutput.put("affectedRow", affectedRow);
        commandOutput.put("error", "Cannot steal enemy card since the player's row is full.");

        output.add(commandOutput);

    }

    public static void getRightCommandName(String commandName, ObjectNode commandOutput) {
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

        }
    }

    public static void getFrozenCardsOnTable(Card[][] table, ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();
        commandOutput.put("command", "getFrozenCardsOnTable");

        ArrayNode deckOutput = commandOutput.putArray("output");
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (table[i][j] != null && table[i][j].isFrozen()) {
                    ObjectNode cardOutput = deckOutput.objectNode();

                    table[i][j].convertCardToJson(cardOutput);
                    deckOutput.add(cardOutput);
                }
            }
        }
        output.add(commandOutput);
    }

    public static void cardIsNotATank (Coordinates cardAttacker, Coordinates cardAttacked,
                                       String commandName,  ArrayNode output) {
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
    public static void cardIsNotEnemy(Coordinates cardAttacker, Coordinates cardAttacked,
                                      String commandName, ArrayNode output) {
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
    public static void cardIsFrozen(Coordinates cardAttacker, Coordinates cardAttacked,
                                      String commandName, ArrayNode output) {
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
    public static void cardAlreadyAttacked(Coordinates cardAttacker, Coordinates cardAttacked,
                                    String commandName, ArrayNode output) {
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



    public static void cardIsEnemySpecial(Coordinates cardAttacker, Coordinates cardAttacked,
                                             ArrayNode output) {
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

    public static void gameEnd(int currentPlayer, ArrayNode output) {

        ObjectNode commandOutput = output.objectNode();
        if (currentPlayer == 1) {
            commandOutput.put("gameEnded", "Player one killed the enemy hero.");
        } else {
            commandOutput.put("gameEnded", "Player two killed the enemy hero.");
        }

        output.add(commandOutput);
    }

    public static void notEnoughManaHero(int affectedRow, ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();

        commandOutput.put("command", "useHeroAbility");
        commandOutput.put("affectedRow", affectedRow);
        commandOutput.put("error","Not enough mana to use hero's ability.");

        output.add(commandOutput);
    }
    public static void heroAlreadyAttacked(int affectedRow, ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();

        commandOutput.put("command", "useHeroAbility");
        commandOutput.put("affectedRow", affectedRow);
        commandOutput.put("error","Hero has already attacked this turn.");

        output.add(commandOutput);
    }
    public static void rowNotEnemyHero(int affectedRow, ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();

        commandOutput.put("command", "useHeroAbility");
        commandOutput.put("affectedRow", affectedRow);
        commandOutput.put("error", "Selected row does not belong to the enemy.");

        output.add(commandOutput);
    }
    public static void rowNotAllyHero(int affectedRow, ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();

        commandOutput.put("command", "useHeroAbility");
        commandOutput.put("affectedRow", affectedRow);
        commandOutput.put("error", "Selected row does not belong to the current player.");

        output.add(commandOutput);
    }
    public static void getPlayerOneWins(ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();

        commandOutput.put("command", "getPlayerOneWins");
        commandOutput.put("output", Game.getPlayerOneWins());

        output.add(commandOutput);
    }

    public static void getPlayerTwoWins(ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();

        commandOutput.put("command", "getPlayerTwoWins");
        commandOutput.put("output", Game.getPlayerTwoWins());

        output.add(commandOutput);
    }

    public static void getTotalGamesPlayed(ArrayNode output) {
        ObjectNode commandOutput = output.objectNode();

        commandOutput.put("command", "getTotalGamesPlayed");
        commandOutput.put("output", Game.getTotalGamesPlayed());

        output.add(commandOutput);
    }
    public static void printTable() {
        Card[][] table = Game.getGameTable();
        for(int i = 3; i >= 0; i--) {
            System.out.printf("row " + i + " :");
            for( int j = 0;  j < 5; j++) {
                if(table[i][j] != null) {

                    System.out.printf(table[i][j].getName() + "   ");
                } else {
                    System.out.printf("NULL   ");
                }
            }
            System.out.printf("\n");
        }
    }
}
