import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.Button;
//import javafx.scene.text.*;


public class OceanExplorer extends Application{
	final private int scallingFactor = 50;  
	final private int mapSize = 10;
	ImageView shipImageview, CapturedImageView;
	Image CapturedImage, PirateIslandImage, pirateShipImage, IslandImage, shipImage;
	List<ImageView> PirateShipImageView = new ArrayList<ImageView>(); 	//Keeping Track of PirateShip ImageViews for Movement
	List<PirateShip> PirateShips  = new ArrayList<PirateShip>();		 	//Keeping Track of PirateShips
	//Text ScoreBoard;
	Button button;
	Scene scene;
	Pane root;
	//int score;
	OceanMap oceanMap;
	Ship ship;
	Random rand = new Random();
	boolean[][] OceanGrid;
	boolean foundTarget = false;
	public void drawMap() {
		for(int x = 0; x < mapSize; x++){ 
			for(int y = 0; y < mapSize; y++){
				Rectangle rect = new Rectangle(x*scallingFactor,y*scallingFactor,scallingFactor,scallingFactor);
				rect.setStroke(Color.BLACK); //Black Outline
				rect.setFill(Color.PALETURQUOISE); //Water Color
				root.getChildren().add(rect);
			}
		}
	}
	public void AddIslandImages(Image image, Point loc) {
		//Can add Island or PirateIsland Images
		ImageView imageView =  new ImageView(image); 
		imageView.setX(loc.x * scallingFactor);
		imageView.setY(loc.y * scallingFactor);
		root.getChildren().add(imageView);
	}
	
	public void AddIslands() {
		//Adds Islands and their images
		List<Point> Islands = oceanMap.getIslands();
		//IslandImage = new Image("island.jpg",scallingFactor,scallingFactor,true,true);
		for(Point i : Islands) {
			AddIslandImages(IslandImage, i.getLocation() );
		}
	}
	/*public void ChangeScoreBoard() {
		String txt = Integer.toString(score);
		ScoreBoard.setText(txt);
		
	}*/
	public void AddPirateIslands() {
		//Adds PirateIslands, Pirate Ships, and their Images. Keeps track of PirateShips
		List<Point> PirateIslands = oceanMap.getPirateIslands();
		ImageView pirateShipIV;
		for(Point i : PirateIslands) {
			AddIslandImages(PirateIslandImage, i );
			pirateShipIV = new ImageView(pirateShipImage);
			pirateShipIV.setX(i.x * scallingFactor);
			pirateShipIV.setY(i.y * scallingFactor);
			PirateShipImageView.add(pirateShipIV);
			PirateShips.add( new PirateShip(PirateShips.size()+1, mapSize, i.getLocation(), OceanGrid));
		}
	}
	public void AddPirateShip() {
		for (ImageView i: PirateShipImageView)
		root.getChildren().add(i);
	}
	public void InitImages() {
		PirateIslandImage = new Image("pirateIsland.png",scallingFactor,scallingFactor,true,true);
		pirateShipImage = new Image("pirateShip.png",scallingFactor,scallingFactor,true,true);
		IslandImage = new Image("island.jpg",scallingFactor,scallingFactor,true,true);
		shipImage = new Image("ship.png",scallingFactor,scallingFactor,true,true);
		CapturedImage = new Image("win.gif",scallingFactor, scallingFactor, false, false);
	}
	
	public void restartGame() throws Exception {
		if( root.getChildren().removeAll(root.getChildren()) ) {
			oceanMap = new OceanMap(mapSize);
			OceanGrid = oceanMap.getMap(); 
			drawMap();
			PirateShipImageView = new ArrayList<ImageView>();
			PirateShips  = new ArrayList<PirateShip>();
			rand = new Random();
			foundTarget = false;
			AddIslands();
			AddPirateIslands();
			AddPirateShip();
			setShip();
			root.getChildren().add(button);
			startGame();
		}
		else throw new RuntimeException("Children Weren't Removed!");
	}
	public void setShip() {
		ship = new Ship( mapSize );
		for(PirateShip i: PirateShips)ship.registerObserver(i);
		ship.oceanMap = OceanGrid;
		shipImageview = new ImageView(shipImage);
		shipImageview.setX(oceanMap.getShipLocation().x * scallingFactor);
		shipImageview.setY(oceanMap.getShipLocation().y * scallingFactor);
		root.getChildren().add(shipImageview);
	}
	@Override
	public void start(Stage oceanStage) {
		root = new Pane();		
		scene = new Scene(root,mapSize*scallingFactor,mapSize*scallingFactor+50);	
		oceanStage.setTitle("ColumbusGame");
		oceanStage.setScene(scene);
		oceanStage.show();	
		oceanMap = new OceanMap(mapSize);
		OceanGrid = oceanMap.getMap();
		InitImages();
		drawMap();
		AddIslands();
		AddPirateIslands();
		AddPirateShip();
		button = new Button("Reset");
		button.setScaleX(2);
		button.setScaleY(2);
		button.setLayoutX(mapSize*scallingFactor/2-scallingFactor/2);
		button.setLayoutY(mapSize*scallingFactor+12);
		button.setOnAction(new EventHandler<ActionEvent>() {
		@Override
	    	public void handle(ActionEvent ke) {
		 		try {
					restartGame();
				} catch (Exception e) {
					e.printStackTrace();
				}
		 	}
		});
		root.getChildren().add(button);
		setShip();
		CapturedImageView = new ImageView(CapturedImage);
		/*score = 0;
		ScoreBoard = new Text();
		ScoreBoard.setText("0");
		ScoreBoard.setX(20);
		ScoreBoard.setY(mapSize*scallingFactor + 25 );
		root.getChildren().add(ScoreBoard);*/
		startGame();
		
	}
	protected void setCapturedImage(){
    	if(root.getChildren().contains(shipImageview)){
			root.getChildren().remove(shipImageview);
			CapturedImageView.setX(ship.getShipLocation().x*scallingFactor);
			CapturedImageView.setY(ship.getShipLocation().y*scallingFactor);
			root.getChildren().add(CapturedImageView);		
		}
    }
	private void startGame(){
	    scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
	    	@Override
	    	public void handle(KeyEvent ke) {
	    		if(!foundTarget) {
	    			switch(ke.getCode()){
			    		case RIGHT:
			    		ship.moveEast();						
			    		break;
			    		case LEFT:
			    		ship.moveWest();
			    		break;
			    		case DOWN:
			    		ship.moveSouth();
			    		break;
			    		case UP:
			    		ship.moveNorth();
			    		break;
			    	    default:
			    		break;	
	    			}
	    		shipImageview.setX(ship.getShipLocation().x*scallingFactor);
	    		shipImageview.setY(ship.getShipLocation().y*scallingFactor);
	    		ship.notifyObservers();
	    		int j = 0;
	    		PirateShip PShip;
	    		for (ImageView i: PirateShipImageView) {
	    			PShip = PirateShips.get(j++);
	    			i.setX(PShip.getShipLocation().x*scallingFactor);
		    		i.setY(PShip.getShipLocation().y*scallingFactor);
		    		if(ship.getShipLocation().equals( PShip.getShipLocation())){	
			      		  setCapturedImage();
			      		  foundTarget = true;
			      		}
	    		}
//	    		pirateShipImageview1.setX(PShip1.getShipLocation().x*scallingFactor);
//	    		pirateShipImageview1.setY(PShip1.getShipLocation().y*scallingFactor);
//	    		pirateShipImageview2.setX(PShip2.getShipLocation().x*scallingFactor);
//	    		pirateShipImageview2.setY(PShip2.getShipLocation().y*scallingFactor);
	    		
	    		}
	    	}
	        });
	    }    

	public static void main(String[] args) {
		launch(args);
	}
}
