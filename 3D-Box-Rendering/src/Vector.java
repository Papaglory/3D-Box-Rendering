import java.util.ArrayList;
import java.util.List;

public class Vector {

	private int dim;
	
	private List<Double> position = new ArrayList<>();
	
	public Vector(List<Double> position) {
		if (position.isEmpty()) throw new ExceptionInInitializerError("vector is empty");
		dim = position.size();
		this.position = position;
	}
	
	//use this for creating simple 3d vectors
	public Vector(double x, double y, double z) {
		position.add(x);
		position.add(y);
		position.add(z);
		this.dim = position.size();
	}
	
	//use this for creating simple 4d vectors
	public Vector(double x, double y, double z, double a) {
		position.add(x);
		position.add(y);
		position.add(z);
		position.add(a);
		this.dim = position.size();
	}
	
	public void setPosition(List<Double> newPos) {
		if (newPos.size() != dim) {
			throw new IllegalArgumentException("new position must have the same dimension size");
		}
		this.position = newPos;
	}
	
	public double getValue(int i) {
		if (i < 0 || i >= dim) throw new IndexOutOfBoundsException("i: " + i);
		return position.get(i);
	}
	
	public int getDim() { return dim; }
	
	@Override
	public String toString() {
		return position.toString();
	}
}