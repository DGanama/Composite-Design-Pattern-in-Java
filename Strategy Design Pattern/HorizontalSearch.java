import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class HorizontalSearch implements Strategy {
	
	int CellsVisited = 0;
	List<Point> sub = new ArrayList<Point>();
	List<Point> car = new ArrayList<Point>();
	char bsMatrix[][] = new char[25][25];
	Point currentposition = new Point(0,0);
	final int MAX_LENGTH = 25;
	boolean foundSub = false;
	boolean foundCar = false;
	
	public String getName() {
		return("Horizontal Search");}
	
	public void LookAround(int left) {
		char result = 'o';
		int dir = 0;
		Point temp = currentposition.getLocation();
		while(left>0) {
			result = 'o';
			switch (dir){
			case 0:
				if(temp.y+1<MAX_LENGTH) {
					temp.y++;
					result = bsMatrix[temp.x][temp.y];}
			break;
			case 1:
				if(temp.x+1<MAX_LENGTH) {
					temp.x++;
					result = bsMatrix[temp.x][temp.y];}
			break;
			case 2:
				if(temp.y-1>=0) {
					temp.y--;
					result = bsMatrix[temp.x][temp.y];}
			break;
			case 3:
				if(temp.x-1>=0) {
					temp.x--;
					result = bsMatrix[temp.x][temp.y];
				}
			break;
			}
			if(result=='c' || result =='s') {
				AddCoordinate(result, temp);
				left--;
			}
			else {
				dir++;
				temp = currentposition.getLocation();
			}
			if (dir>=4) break;
		}
	}
	public Point getCoords() {
		return currentposition;
	}
	public List<Point> getCarrier() {
		return car;
	}

	public List<Point> getSubmarine() {
		return sub;
	}
	public void AddCoordinate(char typ, Point coord) {
		if (typ=='c') car.add(coord.getLocation());
		else sub.add(coord.getLocation());
	}
	public String getString() {
		String fin = "Carrier Found at: " + "("+ car.get(0).x+","+car.get(0).y+")" + " And " + "("+ car.get(1).x+","+car.get(1).y+")" + 
				" Submarine Found at: " + "("+ sub.get(0).x+","+sub.get(0).y+")" + " And " + "("+ sub.get(1).x+","+sub.get(1).y+")";
		return fin;
	}
	public int getStats() {
		return CellsVisited;
	}
	public void Search(char[][] bs) {
		bsMatrix = bs.clone();
		for (int j = 0; j<MAX_LENGTH; j++) {
		     for(int i=0; i<MAX_LENGTH; i++) {
		    	 CellsVisited++;
		    	 currentposition = new Point(j,i).getLocation();
		    	 switch(bs[currentposition.x ][currentposition.y]) {
					case 'c':
						if(!foundCar) {
						//System.out.println(currentposition.toString());
						foundCar = true;
						AddCoordinate('c', currentposition);
						LookAround(4);}
					break;
					case 's':
						if(!foundSub) {
						//System.out.println(currentposition.toString());
						foundSub = true;
						AddCoordinate('s',currentposition);
						LookAround(2);
						}
					break;
					default:
					}
		    	
		    	 
		    		if(foundCar && foundSub) 
		    		return;
		    		
		     }
		}
	}
}
