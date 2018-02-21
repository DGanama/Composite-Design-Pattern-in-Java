import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class FlowerBedComposite implements GardenComponent{
	List<GardenComponent> FlowerBedComponents = new ArrayList<GardenComponent>();
	Point2D position;
	Color color;
	boolean Moveable;
	Rectangle rectangle;
	int height, width, lineSize;
	
	public FlowerBedComposite(Point2D pos, int h, int w, int lS) {
		lineSize=lS;
		position=pos;
		color = setRandomColor();
		height=h; 
		width=w; 
		rectangle = new Rectangle();
		rectangle.setHeight(height); 
		rectangle.setWidth(width); 
		rectangle.setX(position.getX());
		rectangle.setY(position.getY()); 
		rectangle.setStroke(color); 
		rectangle.setFill(Color.TRANSPARENT);
		rectangle.setStrokeWidth(lineSize);
	}
	@Override
	public boolean ContainsPoint(Point2D point) {
		return (rectangle.contains(point));
	}
	@Override
	public Point2D getCurrentCenter() {
		return position;
	}
	public Rectangle getRectangle() {
		return rectangle;
	}
	
	public void addComponent(GardenComponent newComponent) {
		if(!FlowerBedComponents.contains(newComponent) ) 
			FlowerBedComponents.add(newComponent);
			newComponent.changeColor(color);
	}
	@Override
	public void changeColor(Color newColor) {
		color=newColor;
		rectangle.setStroke(newColor);
	}
	public void removeComponent(GardenComponent newComponent) {
		FlowerBedComponents.remove(newComponent);
		newComponent.changeColor(Color.BLACK);
		
	}
	public void UpdateComponents(GardenComponent draggedComponent) {
		if(!rectangle.contains(draggedComponent.getCurrentCenter())) removeComponent(draggedComponent);
	}
	@Override
	public void move(double x, double y) {
		for (GardenComponent i: FlowerBedComponents) i.move(x,y);
		rectangle.setY(y=rectangle.getY()+y);
		rectangle.setX(x=rectangle.getX()+x);
		position = new Point2D(x,y);
	}
	public Color setRandomColor() {
		Random random = new Random();
	    int r = random.nextInt(255);
	    int g = random.nextInt(255);
	    int b = random.nextInt(255);
	    return Color.rgb(r, g, b);
	   }
}
