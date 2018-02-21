import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

public class OptimizedSearch implements Strategy {
	int CellsVisited = 0;
	List<Point> sub = new ArrayList<Point>();
	List<Point> car = new ArrayList<Point>();
	char bsMatrix[][] = new char[25][25];
	//boolean Visited[25][25];
	Point currentposition = new Point(0,0);
	final int MAX_LENGTH = 25;
	boolean foundSub = false;
	boolean foundCar = false;
	public String getName() {
		return("Optimized Search");}
	public void Search(char Matrix[][]) {
		for(int i=0; i<MAX_LENGTH; i++) {
			//System.out.print("[");
			  for(int j=0; j<MAX_LENGTH; j++) {
			    bsMatrix[i][j]=Matrix[i][j];
			    if(Matrix[i][j]=='s')System.out.print("[sub at"+i+","+j+"]");
				}
			  //System.out.println("]");
		}
		int start = 0;
		//char peek = bsMatrix[0][0];
		//boolean shouldRun = true;
		while(!(foundCar && foundSub)){
			//System.out.println(currentposition.toString());
			if(currentposition.x >= 25 || currentposition.y  >= 25) break;
			CellsVisited++;
			switch(bsMatrix[currentposition.x ][currentposition.y ]) {
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
			if (currentposition.x + 3 >= 25) {
				start++;
				if(start>=3) start = 0;
				currentposition.x  = start;
				currentposition.y++;
			}
			else {
				currentposition.x  = currentposition.x  + 3;
			}
		}
	}
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
				temp.x = currentposition.x;
				temp.y = currentposition.y;
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
}
