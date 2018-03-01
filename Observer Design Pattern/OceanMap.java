import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class OceanMap {
	boolean[][] theMap;
	Point currentLocation;
	Random rand = new Random();
	int mapSize = 0;
	List<Point> Islands = new ArrayList<Point>();
	List<Point> PirateIslands = new ArrayList<Point>();
	public OceanMap(int size) {
		theMap = new boolean[size][size];
		mapSize = size;
		currentLocation = new Point(0,0);
		AddIslands();
		AddPirateIslands();
	}
	public void AddIslands() {
		Point Island=new Point();
		for(int i = 0; i < mapSize; i++) {
			Island.x = rand.nextInt(mapSize);
			Island.y = rand.nextInt(mapSize);
			if(Island.x == 0 && Island.y == 0) {
				Island.x = rand.nextInt(mapSize);
				Island.y = rand.nextInt(mapSize);
			}
			theMap[Island.x][Island.y] = true;
			Islands.add(Island.getLocation());
		}
	}
	public List<Point> getIslands(){
		return Islands;
	}
	public List<Point> getPirateIslands(){
		return PirateIslands;
	}
	public void AddPirateIslands() {
		Point PirateIsland=new Point();
		for(int i = 0; i < 2; i++) {
			PirateIsland.x = rand.nextInt(mapSize-2)+1;
			PirateIsland.y = rand.nextInt(mapSize-2)+1;
			theMap[PirateIsland.x][PirateIsland.y] = true;
			PirateIslands.add(PirateIsland.getLocation());
		}
	}
	
	public boolean[][] getMap(){
		return theMap; 
	}
	public Point getShipLocation(){
		return currentLocation;
	}
}
