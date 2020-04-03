package it.polimi.ingsw.model.Map;

import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Player.Worker;
import it.polimi.ingsw.utils.ConstantsContainer;
import javafx.util.Pair;

import java.sql.SQLClientInfoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameMap {



    private ArrayList<Square> gameMap;
    private HashMap<Worker, Square> workersPosition;
    private Square [][]linkToCoordinates = new Square[25][25]; // mettere come costanti
    private ArrayList<Square> modifiedSquare = new ArrayList<>();

    public GameMap() {
        this.gameMap = MapLoader.loadMap();
        for(Square square: gameMap){
            Integer[] coordinates =  square.getCoordinates();
            linkToCoordinates[coordinates[0]][coordinates[1]] = square;
        }
    }

    //
    //Function to obtain the number of tile from coordinates
    //

    public Square getTileFromCoordinates(Integer[] coordinates){
        return linkToCoordinates[coordinates[0]][coordinates[1]];
    }

    //
    //function to find all the reachable square moving from a specific square
    //

    public ArrayList<Directions> reachableSquares(Worker worker){
          if(worker == null)
              throw new NullPointerException("null worker");
          int level_position = worker.getBoardPosition().getBuildingLevel();
          HashMap<Directions,Integer> canAccess = worker.getBoardPosition().getCanAccess();
          ArrayList<Directions> reachableSquares = new ArrayList<>();

          for(Directions dir: Directions.values()){
              int squareTile  =canAccess.get(dir);
              if(squareTile > 0 && squareTile <= 25) { //mettere come costanti
                  Square possibleSquare = gameMap.get(squareTile- 1);
                  if(!possibleSquare.hasPlayer() && (possibleSquare.getBuildingLevel() >= 0 && possibleSquare.getBuildingLevel() <= level_position +1 && !worker.getBoardPosition().equals(possibleSquare) )
                          && possibleSquare.getBuilding() != Building.DOME ){
                      reachableSquares.add(dir);
                  }
              }
          }

return reachableSquares;
    }

    //
    //function that change the position of the worker
    //

    public void moveWorkerTo(Player player, Directions direction){
        if(player == null || direction == null)
            throw new NullPointerException("null player or direction");
        Worker currentWorker = player.getCurrentWorker();
        currentWorker.setPreviousBoardPosition(currentWorker.getBoardPosition());
        currentWorker.getPreviousBoardPosition().setHasPlayer(false);
        currentWorker.setBoardPosition( gameMap.get(currentWorker.getBoardPosition().getCanAccess().get(direction) -1));
        currentWorker.getBoardPosition().setHasPlayer(true);
        currentWorker.getBoardPosition().setPlayer(player);
        currentWorker.getBoardPosition().setWorker(currentWorker);

    }

    //
    //function that change the position of the worker
    //

    public ArrayList<Directions> buildableSquare(Worker worker){
        if(worker == null)
            throw new NullPointerException("null worker");
        ArrayList<Directions> buildableSquare = new ArrayList<>();
        HashMap<Directions,Integer> canAccess = worker.getBoardPosition().getCanAccess();

        for(Directions dir: Directions.values()){
              int squareTile = canAccess.get(dir);
              if(squareTile > 0 && squareTile <= 25){
                  Square possibleBuild = gameMap.get(squareTile -1);
                  if(!possibleBuild.getBuilding().equals(Building.DOME) && !possibleBuild.hasPlayer() && !worker.getBoardPosition().equals(possibleBuild)){
                      buildableSquare.add(dir);
                  }
              }
        }

        return buildableSquare;
    }

    //
    //function that build in the position selected,with the type of building selected
    //

    public boolean buildInSquare(Worker worker, Directions direction, Building building){
        if(worker == null || direction == null || building == null){
            throw new NullPointerException("null worker or building or direction");
        }
        Square buildingSquare = gameMap.get(worker.getBoardPosition().getCanAccess().get(direction) -1);
        if(building.equals(Building.mapNext(buildingSquare.getBuilding()))){
            worker.setPreviousBuildPosition(buildingSquare);
            buildingSquare.setBuilding(building);
            buildingSquare.addBuildingLevel();

            return true;
        }
        return false;

    }

    //
    //function that return the positions of both player's workers
    //

    public ArrayList<Square> workersSquares(Player actualPlayer){
        if(actualPlayer == null)
            throw new NullPointerException("player null");
        ArrayList<Square> workerSquare = new ArrayList<>();

        for(Worker worker : actualPlayer.getWorkers()){
            workerSquare.add(worker.getBoardPosition());

        }
        return workerSquare;
    }

    public ArrayList<Square> getGameMap(){ return gameMap;}


    //
    //function that check if a square is in the perimeter
    //

    public  boolean isInPerimeter(Integer tile){
        if(tile == null)
            throw new NullPointerException("tile null");

        return tile <= ConstantsContainer.PERIMETERPOSITION;
    }
}
