import java.awt.Point;
import java.util.List;
public interface Strategy {
	public void Search(char Matrix[][]);
	public void LookAround(int left);
	public void AddCoordinate(char typ, Point temp);
	public String getString();
	public Point getCoords();
	public int getStats();
	public List<Point> getCarrier();
	public List<Point> getSubmarine();
	public String getName();
}
