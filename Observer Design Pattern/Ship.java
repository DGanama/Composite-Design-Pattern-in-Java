import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

public class Ship implements Subject{
	int range;
	//int score = 0;
	Point currentLocation;
	int unitSize; //scaling factor
	boolean[][] oceanMap;
	List<Observer> observers = new LinkedList<Observer>();
	public Ship(int MapSize) {
		currentLocation = new Point(0,0);
		range = MapSize;
	}

	public Point getShipLocation(){
		return currentLocation;
		}
	public void moveSouth() {
		if((currentLocation.y+1<range) && !oceanMap[currentLocation.x][currentLocation.y+1]) currentLocation.y++;
	}
	public void moveNorth() {
		if((currentLocation.y-1>=0) && !oceanMap[currentLocation.x][currentLocation.y-1]) currentLocation.y--;
	}
	public void moveEast() {
		if((currentLocation.x+1<range) && !oceanMap[currentLocation.x+1][currentLocation.y]) currentLocation.x++;
	}
	public void moveWest() {
		if((currentLocation.x-1>=0) && !oceanMap[currentLocation.x-1][currentLocation.y]) currentLocation.x--;
	}
	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
		
	}

	@Override
	public void removeObserver(Observer o) {
		if(observers.contains(o))
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for (Observer shipObserver: observers)
			shipObserver.update(this);
	}

}
