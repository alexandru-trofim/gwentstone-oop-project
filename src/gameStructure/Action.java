package gameStructure;

import cards.Card;
import cards.EnvironmentCard;
import com.fasterxml.jackson.databind.node.ArrayNode;
import debug.Debug;
import fileio.ActionsInput;
import fileio.Coordinates;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.ArrayList;

public class Action {
    private @Getter @Setter String command;
    private @Getter @Setter int handId;
    private @Getter @Setter Coordinates cardAttacker;
    private @Getter @Setter Coordinates cardAttacked;
    private @Getter @Setter int affectedRow;
    private @Getter @Setter int playerIdx;
    private @Getter @Setter int x;
    private @Getter @Setter int y;

    public Action(ActionsInput actionsInput) {
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

    public void placeCard(Card[][] gameTable, Player player, int playerIdx, int handIndex,
                          ArrayNode output) {
        //player.getPlayerHand().get(handIndex);
        int frontRow, backRow, rowToAdd;
        if(playerIdx == 1) {
            frontRow = 2;
            backRow = 3;
        } else {
            frontRow = 1;
            backRow = 0;
        }
        if(player.getPlayerHand().size() <= handIndex) {
            System.out.printf("ERROR!!! OUT OF BOUND");
            return;
        }
        String cardToPlaceName = player.getPlayerHand().get(handIndex).getName();
        Card cardToPlace = player.getPlayerHand().get(handIndex);

        if (cardToPlaceName.equals("The Ripper")  || cardToPlaceName.equals("Miraj")  ||
        cardToPlaceName.equals("Warden") || cardToPlaceName.equals("Goliath")) {
           rowToAdd = frontRow;
        } else {
            rowToAdd = backRow;
        }

        //Handling errors
        if (cardToPlace.getMana() > player.getMana()) {
            Debug.notEnoughManaError(handIndex, output);
            return;
        }
        if (cardToPlaceName.equals("Firestorm")  || cardToPlaceName.equals("Winterfell") ||
                cardToPlaceName.equals("Heart Hound")) {
            Debug.environmentCardError(handIndex, output);
            return;
        }
        if (gameTable[rowToAdd][4] != null) {
            Debug.notEnoughSpaceError(handIndex, output);
            return;
        }

        int positionOnTable = 0;
        for(int i = 0; i < 5; ++i) {
           if(gameTable[rowToAdd][i] == null) {
               positionOnTable = i;
               break;
           }
        }
        player.setMana(player.getMana() - cardToPlace.getMana());
        gameTable[rowToAdd][positionOnTable] = player.getPlayerHand().get(handIndex);
        player.getPlayerHand().remove(handIndex);
    }

    public void useEnvironmentCard(int handId, int affectedRow,  int playerIdx,
                                   Card[][] table,  Player player, ArrayNode output) {
        if (player.getPlayerHand().size() < handId) {
            System.out.printf("!!!OutOfBound!!!" + "\n");
            return;
        }

        Card currentCard = player.getPlayerHand().get(handId);

        System.out.printf("ENVIRONMENT CARD: " + currentCard.getName() + " affectedRow " + affectedRow + "\n");
        if (!currentCard.getName().equals("Firestorm") &&
                !currentCard.getName().equals("Winterfell") &&
                !currentCard.getName().equals("Heart Hound")) {
            Debug.notEnvironmentCard(handId, affectedRow, output);
            return;
        }
        if (currentCard.getMana() > player.getMana()) {
            Debug.notEnoughManaEnvironment(handId, affectedRow, output);
            return;
        }
        if ((playerIdx == 1 && (affectedRow == 2 || affectedRow == 3)) ||
                (playerIdx == 2 && (affectedRow == 0 || affectedRow == 1))) {
            Debug.environmentAttacksOwnRow(handId, affectedRow, output);
            return;
        }

        //check if rowToInsert is full, if yes throw error
        int cardsOnRow = 5;
        int rowToInsert = 0;
        switch (affectedRow){
            case 0 -> rowToInsert = 3;
            case 1 -> rowToInsert = 2;
            case 2 -> rowToInsert = 1;
            case 3 -> rowToInsert = 0;
        }
        //check if the row is full when trying to steal with Heart Hound
        if (currentCard.getName().equals("Heart Hound") && table[rowToInsert][cardsOnRow - 1] != null) {
            Debug.cannotStealCardFullRow(handId, affectedRow, output);
            return;
        }

        //use environment card
        EnvironmentCard castedCard = (EnvironmentCard) currentCard;
        castedCard.attack(table, affectedRow);

        player.setMana(player.getMana() - currentCard.getMana());

        //delete environment card from hand
        player.getPlayerHand().remove(handId);



    }
    public boolean checkForTanks(Card[][] table, int frontRow, int backRow) {
        int numberOfColumns = 5;
        for(int i = 0; i < numberOfColumns; ++i) {
            if (table[frontRow][i] != null) {
                if (table[frontRow][i].isTank())
                    return true;
            }
            if (table[backRow][i] != null) {
                if (table[backRow][i].isTank())
                    return true;
            }
        }
        return false;
    }

    public void killCardAtPosition(Card[][] table, int x, int y) {
        int cardsOnRow = 5;
        for(int i = y; i < cardsOnRow - 1; ++i) {
           table[x][i] = table[x][i + 1];
        }

        //erase last element on the row
        table[x][cardsOnRow - 1] = null;

    }

    public void cardUsesAttack(Coordinates cardAttackerCoord, Coordinates cardAttackedCoord,
                               Card[][] table, int playerIdx, ArrayNode output) {

        int frontRow;
        int backRow;
        Card cardAttacked = table[cardAttackedCoord.getX()][cardAttackedCoord.getY()];
        Card cardAttacker = table[cardAttackerCoord.getX()][cardAttackerCoord.getY()];
        if (cardAttacked == null || cardAttacker == null) {
            System.out.printf("ATTACKER/ATTACKED null" + "\n");
            return;
        }
        System.out.printf("card Attacker coord: x: " + cardAttackerCoord.getX() + " y: " + cardAttackerCoord.getY() + "\n");
        System.out.printf("Attacker is Frozen " + cardAttacker.isFrozen() + "\n");

        //front and back rows of enemy
        if (playerIdx == 2) {
            frontRow = 2;
            backRow = 3;
        } else {
            frontRow = 1;
            backRow = 0;
        }

        if (cardAttacker.isMadeMove()) {
            Debug.cardAlreadyAttacked(cardAttackerCoord, cardAttackedCoord, output);
            return;
        }
        if (cardAttackedCoord.getX() != frontRow &&
                cardAttackedCoord.getX() != backRow) {
            Debug.cardIsNotEnemy(cardAttackerCoord, cardAttackedCoord, output);
            return;
        }

        if (cardAttacker.isFrozen() == true) {
            Debug.cardIsFrozen(cardAttackerCoord, cardAttackedCoord, output);
            return;
        }


        if (checkForTanks(table, frontRow, backRow) && !cardAttacked.isTank()) {
            Debug.cardIsNotATank(cardAttackerCoord, cardAttackedCoord, output);
            return;
        }


        cardAttacker.attack(cardAttacked);

        if (cardAttacked.isDead()) {
            killCardAtPosition(table, cardAttackedCoord.getX(), cardAttackedCoord.getY());
        }

    }

    public void cardUsesAbility(Coordinates cardAttackerCoord, Coordinates cardAttackedCoord,
                                Card[][] table, int playerIdx, ArrayNode output) {
        int frontRow;
        int backRow;
        Card cardAttacked = table[cardAttackedCoord.getX()][cardAttackedCoord.getY()];
        Card cardAttacker = table[cardAttackerCoord.getX()][cardAttackerCoord.getY()];

        if (cardAttacked == null || cardAttacker == null) {
            System.out.printf("ATTACKER/ATTACKED null" + "\n");
            return;
        }
        System.out.printf("card Attacker coord: x:AAAAAAA " + cardAttackerCoord.getX() + " y: " + cardAttackerCoord.getY() + "\n");
        System.out.printf("Attacker is Frozen " + cardAttacker.isFrozen() + "\n");

        //front and back rows of enemy
        if (playerIdx == 2) {
            frontRow = 2;
            backRow = 3;
        } else {
            frontRow = 1;
            backRow = 0;
        }

        if (cardAttacker.isMadeMove()) {
            Debug.cardAlreadyAttackedSpecial(cardAttackerCoord, cardAttackedCoord,
                                                                            output);
            return;
        }

        if (cardAttacker.getName().equals("Disciple")) {
            if (cardAttackedCoord.getX() == frontRow ||
                    cardAttackedCoord.getX() == backRow) {
                Debug.cardIsEnemySpecial(cardAttackerCoord, cardAttackedCoord,
                        output);
                return;
            }
        }

        if (cardAttacker.getName().equals("The Ripper") ||
                cardAttacker.getName().equals("Miraj") ||
                cardAttacker.getName().equals("The Cursed One")) {

            if (cardAttackedCoord.getX() != frontRow &&
                    cardAttackedCoord.getX() != backRow) {
                Debug.cardIsNotEnemySpecial(cardAttackerCoord, cardAttackedCoord,
                        output);
                return;
            }

        }

        if (cardAttacker.isFrozen()) {
            Debug.cardIsFrozenSpecial(cardAttackerCoord, cardAttackedCoord,
                    output);
            return;
        }


        if (checkForTanks(table, frontRow, backRow) && !cardAttacked.isTank()) {
            Debug.cardIsNotATankSpecial(cardAttackerCoord, cardAttackedCoord,
                    output);
            return;
        }


        cardAttacker.specialAttack(cardAttacked);

        if (cardAttacked.isDead()) {
            killCardAtPosition(table, cardAttackedCoord.getX(), cardAttackedCoord.getY());
        }


    }



}
