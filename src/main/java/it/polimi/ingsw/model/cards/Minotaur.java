package it.polimi.ingsw.model.cards;

import it.polimi.ingsw.model.map.Building;
import it.polimi.ingsw.model.map.Directions;
import it.polimi.ingsw.model.map.GameMap;
import it.polimi.ingsw.model.map.Square;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Worker;
import it.polimi.ingsw.model.Response;
import it.polimi.ingsw.utils.ConstantsContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class that extends Card that build the Minotaur card
 * @author Luigi Scibona, Alessandro Ruzzi, Edoardo Piantoni
 * @version 1.0
 * @since 2020/06/27
 */

public class Minotaur extends Card {

    /**
     * Public constructor
     * @param name Name of the card
     * @param description Description of the power of the card
     * @param isPlayableIn3 Boolean saying if the card is playable in 3 Players
     * @param type Type of the card
     * @param subType Subtype of the card
     */

    public Minotaur(String name, String description, boolean isPlayableIn3, CardType type, CardSubType subType) {
        super(name, description, isPlayableIn3, type, subType);
    }

    @Override
    public List<Directions> findWorkerMove(GameMap gameMap, Worker worker) {
        if (gameMap == null ||worker == null)
            throw new NullPointerException("null gameMap or worker");

        int levelPosition = worker.getBoardPosition().getBuildingLevel();
        Map<Directions, Integer> canAccess = worker.getBoardPosition().getCanAccess();
        List<Directions> reachableSquares = new ArrayList<>();

        for (Directions dir : Directions.values()) {
            int squareTile = canAccess.get(dir);
            if (squareTile > ConstantsContainer.MINMAPPOSITION && squareTile <= ConstantsContainer.MAXMAPPOSITION) { //rivedere questo if
                Square possibleSquare = gameMap.getMap().get(squareTile - 1);
                if ((possibleSquare.getBuildingLevel() >= 0 && possibleSquare.getBuildingLevel() <= levelPosition + 1)
                        && possibleSquare.getBuilding() != Building.DOME) {
                    if (possibleSquare.hasPlayer() && !possibleSquare.getPlayer().getNickName().equals(worker.getBoardPosition().getPlayer().getNickName())) {
                        if(canPush(gameMap, possibleSquare, dir))
                            reachableSquares.add(dir);
                    }
                    else if(!possibleSquare.hasPlayer())
                    {
                        reachableSquares.add(dir);
                    }
                }
            }
        }
        return reachableSquares;
    }

    @Override
    public Response executeWorkerMove(GameMap gameMap, Directions directions, Player player) {
        if(gameMap == null || player == null || directions == null)
            throw new NullPointerException("null gameMap or player or direction");

        Worker currentWorker = player.getCurrentWorker();
        Square nextSquare = gameMap.getMap().get(currentWorker.getBoardPosition().getCanAccess().get(directions) - 1);
        gameMap.clearModifiedSquare();

        if(nextSquare.hasPlayer()){
            push(gameMap, nextSquare, directions);
        }
        gameMap.getModifiedSquare().add(0, currentWorker.getBoardPosition());
        currentWorker.setPreviousBoardPosition(currentWorker.getBoardPosition());
        currentWorker.getPreviousBoardPosition().setHasPlayer(false);
        currentWorker.setBoardPosition(nextSquare);
        currentWorker.getBoardPosition().setHasPlayer(true);
        currentWorker.getBoardPosition().setPlayer(player);
        currentWorker.getBoardPosition().setWorker(currentWorker);
        gameMap.getModifiedSquare().add(1, nextSquare);

        return Response.MOVED;
    }

    /**
     * Method that says if the minotaur can push in the directions provided
     * @param gameMap Map of the game
     * @param possibleSquare Square where the possible pushed enemy worker would go
     * @param directions Direction where can move the Worker of minotaur
     * @return Boolean that says if he can push
     */

    private boolean canPush(GameMap gameMap, Square possibleSquare, Directions directions) {
        if(gameMap == null || possibleSquare == null || directions == null)
            throw new NullPointerException("null gameMap or square or direction");

        int pushingTile = possibleSquare.getCanAccess().get(directions);
        if (pushingTile != 0){
            Square pushingSquare = gameMap.getMap().get(pushingTile - 1);
            return !pushingSquare.hasPlayer() && pushingSquare.getBuildingLevel() != 4 && !pushingSquare.getBuilding().equals(Building.DOME);
        }
        return  false;
    }

    /**
     * Method that move the Worker in the direction provided and push the enemy worker
     * @param gameMap Map of the game
     * @param actualSquare Square where the Worker of minotaur want to move
     * @param directions Direction provided
     */

    public void push(GameMap gameMap, Square actualSquare, Directions directions) {
        if(gameMap == null || actualSquare == null || directions == null)
            throw new NullPointerException("null gameMap or square or direction");

        Worker pushedWorker = actualSquare.getWorker();
        Player pushedPlayer = actualSquare.getPlayer();


        pushedWorker.setPreviousBoardPosition(actualSquare);
        pushedWorker.getPreviousBoardPosition().setHasPlayer(false);
        pushedWorker.setBoardPosition(gameMap.getMap().get(actualSquare.getCanAccess().get(directions) - 1));
        pushedWorker.getBoardPosition().setHasPlayer(true);
        pushedWorker.getBoardPosition().setPlayer(pushedPlayer);
        pushedWorker.getBoardPosition().setWorker(pushedWorker);
        gameMap.addModifiedSquare(pushedWorker.getBoardPosition());
    }
}


