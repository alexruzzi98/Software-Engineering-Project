package it.polimi.ingsw.model.cards;

import it.polimi.ingsw.model.map.Building;
import it.polimi.ingsw.model.map.Directions;
import it.polimi.ingsw.model.map.GameMap;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PanTest {

    Player player1, player2;
    Card cardPan;
    GameMap gameMap;
    List<Directions> directions;

    @BeforeEach
    void setup() {
        player1 = new Player("GoodPlayer");
        player2 = new Player("BadPlayer");
        cardPan = CardLoader.loadCards().get("pan");
        player1.setPower(cardPan);
        gameMap = new GameMap();
        gameMap.getMap().get(13).setMovement(player1, player1.getWorkers().get(0));
        player1.getWorkers().get(0).setBoardPosition(gameMap.getMap().get(13));
        gameMap.getMap().get(4).setMovement(player1, player1.getWorkers().get(1));
        player1.getWorkers().get(1).setBoardPosition(gameMap.getMap().get(4));
        gameMap.getMap().get(21).setMovement(player2, player2.getWorkers().get(0));
        player2.getWorkers().get(0).setBoardPosition(gameMap.getMap().get(21));
        gameMap.getMap().get(18).setMovement(player2, player2.getWorkers().get(1));
        player2.getWorkers().get(1).setBoardPosition(gameMap.getMap().get(18));
        player1.selectCurrentWorker(gameMap, "worker1");

        gameMap.getMap().get(23).setBuilding(Building.LVL1);
        gameMap.getMap().get(23).addBuildingLevel();
        gameMap.getMap().get(23).setBuilding(Building.LVL2);
        gameMap.getMap().get(23).addBuildingLevel();
        gameMap.getMap().get(23).setBuilding(Building.LVL3);
        gameMap.getMap().get(23).addBuildingLevel();
        gameMap.getMap().get(14).setBuilding(Building.LVL1);
        gameMap.getMap().get(14).addBuildingLevel();
        gameMap.getMap().get(14).setBuilding(Building.LVL2);
        gameMap.getMap().get(14).addBuildingLevel();

        directions = player1.findWorkerMove(gameMap, player1.getWorkers().get(0));
    }

    @Test
    void checkVictory() {
        assertThrows(NullPointerException.class , () -> cardPan.checkVictory(null, player1));
        assertThrows(NullPointerException.class , () -> cardPan.checkVictory(gameMap, null));

        assertEquals(Response.MOVED,player1.executeWorkerMove(gameMap, Directions.NORD));
        assertEquals(Response.NOTWIN,cardPan.checkVictory(gameMap, player1));
        player1.getCurrentWorker().setBoardPosition(gameMap.getMap().get(14));
        assertEquals(Response.MOVED,player1.executeWorkerMove(gameMap, Directions.EST));
        assertEquals(Response.WIN,cardPan.checkVictory(gameMap, player1));
        assertEquals( Response.MOVED,player1.executeWorkerMove(gameMap, Directions.OVEST));
        assertEquals(Response.NOTWIN,cardPan.checkVictory(gameMap, player1));
        assertEquals(Response.MOVED,player1.executeWorkerMove(gameMap, Directions.SUD));
        assertEquals(Response.WIN,cardPan.checkVictory(gameMap, player1));
    }
}