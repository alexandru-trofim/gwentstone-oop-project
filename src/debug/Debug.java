package debug;

import cards.Card;
import cards.HeroCard;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import gameStructure.Deck;

import java.awt.image.AreaAveragingScaleFilter;

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
}
