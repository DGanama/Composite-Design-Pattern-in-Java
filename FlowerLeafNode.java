import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
public class FlowerLeafNode implements GardenComponent{
	Point2D position;
	Color color;
	boolean Moveable;
	Circle circle;
	FlowerLeafNode(Point2D pos, Color col, boolean moveable){
		position = pos;
		color = col;
		Moveable = moveable;
		circle = new Circle();
		circle.setCenterX(position.getX());
		circle.setCenterY(position.getY());
		circle.setRadius(5);
		circle.setFill(color);
		circle.setStroke(color);
		circle.setStrokeWidth(1);
		
	}
	@Override
	public boolean ContainsPoint(Point2D point) {
		return (circle.contains(point));
	}
	@Override
	public Point2D getCurrentCenter() {
		return position;
	}
	@Override
	public void changeColor(Color newColor) {
		color=newColor;
		circle.setStroke(newColor);
		circle.setFill(newColor);
	}
	public Circle getCircle() {
		return circle;
	}
	
	
	@Override
	public void move(double x, double y) {
		circle.setCenterY(y=circle.getCenterY()+y);
		circle.setCenterX(x=circle.getCenterX()+x);
		position = new Point2D(x,y);
	}
}
