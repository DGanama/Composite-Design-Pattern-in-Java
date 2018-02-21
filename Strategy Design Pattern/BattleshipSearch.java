import java.io.File;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BattleshipSearch {
	char bsMatrix[][];
	Strategy searchStrategy;
	List<String> games = new ArrayList<String>();
	public BattleshipSearch() {
		searchStrategy = new OptimizedSearch();
	}
	public void setStrategy(Strategy strategy){
		searchStrategy = strategy;	
		}
	public void readFileInput(String fileName) {
		try {	
			Scanner sc = new Scanner(new File(fileName));
			while(sc.hasNextLine()) {
				games.add(sc.nextLine());}
			sc.close();
			}
		catch (Exception e) {e.printStackTrace();}
	}
	public void StartGame(String game, int gameNumber) {
		System.out.println("Game "+gameNumber+": ");
		System.out.println("");
		int pairCount=0;
		StringTokenizer tokenizer = new StringTokenizer(game,"()");
		while(tokenizer.hasMoreTokens()) {
			
			String pair = tokenizer.nextToken();
			String[] cord = pair.split(",");
			int x = Integer.parseInt(cord[0]);
			int y = Integer.parseInt(cord[1]);
			if(pairCount<5) {
			bsMatrix[y][x] = 'c';
			pairCount++;}
			else if(pairCount>=5) {
				bsMatrix[y][x] = 's';
				pairCount++;}
		}
	}
	public void Search() {
		searchStrategy.Search(bsMatrix);
		System.out.println("Strategy: " +searchStrategy.getName());
		System.out.println("Number of Cells Searched: " + searchStrategy.getStats());
		System.out.println(searchStrategy.getString());
		System.out.println();
		}
	public void resetMatrix() {
		for (int j = 0; j<bsMatrix.length; j++){
		     for (int i = 0; i<bsMatrix.length; i++){
		    	 if(bsMatrix[j][i]=='s' || bsMatrix[j][i]=='c') {
		    		 bsMatrix[j][i]='o';}
		     }
		 }
	}
	public static void main(String[] args) {
		BattleshipSearch BS = new BattleshipSearch();
		BS.readFileInput("src/input.txt");
		BS.bsMatrix = new char[25][25];
		for(int k=0; k<25; k++)
			  for(int j=0; j<25; j++) 
				  BS.bsMatrix[k][j] = 'o';
		for (int i = 0; i<BS.games.size(); i++) {
			BS.StartGame(BS.games.get(i),i);
			BS.setStrategy(new HorizontalSearch());
			BS.Search();
			BS.setStrategy(new RandomSearch());
			BS.Search();
			BS.setStrategy(new OptimizedSearch());
			BS.Search();
			BS.resetMatrix();
		}
	}
}
