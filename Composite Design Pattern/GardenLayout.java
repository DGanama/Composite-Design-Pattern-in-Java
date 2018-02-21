import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Point2D;

public class GardenLayout extends Application{
	Pane root;
	Scene scene;
	Point2D lastPosition;
	boolean dragging = false;
	GardenComponent currentComponent = null;
	List<GardenComponent> components = new ArrayList<GardenComponent>();
	//FlowerLeafNode flower;
	@Override
	public void start(Stage Garden) {
		root = new AnchorPane();
		scene = new Scene(root, 500, 500);
		scene.setOnMouseDragged(mouseHandler); 
		scene.setOnMouseReleased(mouseHandler);
		scene.setOnMousePressed(mouseHandler);	
		Garden.setTitle("My Garden");
		Garden.setScene(scene);
		Garden.show();;
	}
	public GardenComponent getCurrentShape(Point2D clickLocation) {
		for(GardenComponent component: components) 
			if(component.ContainsPoint(clickLocation)) {
				if(component instanceof FlowerLeafNode) return component;
				else {
					for(GardenComponent innerComponent: ((FlowerBedComposite)component).FlowerBedComponents)
						if(innerComponent.ContainsPoint(clickLocation)) return innerComponent;
					return component;
				}
			}
		return null;}
	
	public void createComponent(Point2D clickPoint, MouseButton bt) {
		switch (bt) {
		case PRIMARY:
			FlowerLeafNode circ = new FlowerLeafNode(clickPoint,Color.BLACK, true); 			
			components.add(0, circ); 
			root.getChildren().add(circ.getCircle());
		break;
		case SECONDARY:
			FlowerBedComposite rect = new FlowerBedComposite(clickPoint, 150, 150, 10); 	
			components.add(rect); 	
			root.getChildren().add(rect.getRectangle());
		break;
		default:
			break;
		}
	}
	public void updateComponents(GardenComponent movedComponent) {
		for (GardenComponent component: components) 
			if(component instanceof FlowerBedComposite)
					((FlowerBedComposite)component).UpdateComponents(movedComponent);
			
	}
	EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				Point2D clickPoint = new Point2D(mouseEvent.getX(), mouseEvent.getY());
				System.out.println(clickPoint.getX() + " " + clickPoint.getY());
				String eventName = mouseEvent.getEventType().getName();
				if(!dragging) currentComponent = getCurrentShape(clickPoint);
				switch(eventName){
					case("MOUSE_RELEASED"): 
						if(currentComponent!=null && currentComponent instanceof FlowerLeafNode) {
							updateComponents(currentComponent);
							for(GardenComponent component: components) {
								if(component instanceof FlowerBedComposite && component.ContainsPoint(clickPoint)) {  
									((FlowerBedComposite)component).addComponent(currentComponent);
									break;
								}
							}
								//else if (component instanceof FlowerBedComposite) updateComponents(component, currentComponent);
						}	
						if(currentComponent == null)		
							createComponent(clickPoint, mouseEvent.getButton());
							/*if(mouseEvent.getButton()==MouseButton.PRIMARY) { 			
								FlowerLeafNode circ = new FlowerLeafNode(clickPoint,Color.BLACK, true); 			
								components.add(circ); 
								root.getChildren().add(circ.getCircle());} 
							else if(mouseEvent.getButton()==MouseButton.SECONDARY) { 
								FlowerBedComposite rect = new FlowerBedComposite(clickPoint, 150, 150, 10); 	
								components.add(rect); 	
								root.getChildren().add(rect.getRectangle());}*/
						else currentComponent = null;
						dragging=false;
					break;
					case("MOUSE_DRAGGED"):
						dragging=true;
						if(currentComponent != null && lastPosition!=null) {
								System.out.print("Dragging");
								double delateX = clickPoint.getX()-lastPosition.getX();
								double delateY = clickPoint.getY()-lastPosition.getY();
								currentComponent.move(delateX, delateY);
								//updateComponents(currentComponent);
						}
					break;	
				}
				lastPosition = clickPoint;
			}
		};
	
	public static void main(String[] args) {
		launch(args);
	}
}
