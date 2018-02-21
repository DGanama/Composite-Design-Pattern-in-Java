import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public interface GardenComponent {
	public void move(double x, double y);
	public boolean ContainsPoint(Point2D point);
	public Point2D getCurrentCenter();
	public void changeColor(Color newColor);
}
