import java.awt.Point;

public class PirateShip implements Observer{
	Point ShipLocation; 
	Point currentLocation;	
	Point otherPirateShip;
	int mapSize;
	//Random rand = new Random();
	int pirateNum;
	boolean[][] OceanGrid;
	public PirateShip(int num, int size, Point loc, boolean[][] Grid){
		pirateNum = num;
		mapSize = size;
		currentLocation = loc.getLocation();
		OceanGrid = Grid;
	}
  
	@Override
	public void update (Ship ship){
		
	  if(ship instanceof Ship){
		  ShipLocation = ((Ship)ship).getShipLocation();
		  movePirateShip();
	  }
	}
	public void SetOtherPirateShip(Point location) {
		otherPirateShip = location.getLocation();
	}
	public Point getShipLocation(){
		return currentLocation;
		}
	  
	public void movePirateShip(){
			 Point NextLocation = currentLocation.getLocation();
				 if ((NextLocation.x - ShipLocation.x < 0) && (NextLocation.x+1<mapSize))
					 NextLocation.x++;
				 else
					 if(NextLocation.x-1>=0)NextLocation.x--;
				 
				 if ((NextLocation.y - ShipLocation.y < 0) && (NextLocation.y+1<mapSize))
					 NextLocation.y++;
				 else
					 if(NextLocation.y-1>=0) NextLocation.y--;
			 if(!OceanGrid[NextLocation.x][NextLocation.y] ) currentLocation = NextLocation.getLocation();
			 //&& NextLocation.getLocation()!= otherPirateShip.getLocation()
			 //System.out.println("PirateShip #"+ pirateNum +":" + currentLocation.x + " " + currentLocation.y);
		 }
}
