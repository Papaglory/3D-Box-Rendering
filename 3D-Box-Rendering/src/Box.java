import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

// Class for drawing a 3D box
public class Box extends JPanel{
	
	
	// List of actual 3D positions and
	// List of projected positions
	// onto the canvas
	
	/*
	 * All vectors operate between -1 and 1
	 * before drawing, the vectors will be
	 * translated into screen size units
	 */
	
	// 8 vertices
	private List<Vector> vertices = new ArrayList<>();
	// This is the projected position, z is set to 0
	private List<Vector> projectedVertices = new ArrayList<>();
	
	private final Camera camera;
	
	// Constructor
	public Box(Camera camera) {
		this.camera = camera;

		// Default sizes / position
		Vector v1 = new Vector(-0.3, 0.3, -0.3, 1);
		Vector v2 = new Vector(0.3, 0.3, -0.3, 1);
		Vector v3 = new Vector(-0.3, 0.3, 0.3, 1);
		Vector v4 = new Vector(0.3, 0.3, 0.3, 1);		
		Vector v5 = new Vector(-0.3, -0.3, -0.3, 1);
		Vector v6 = new Vector(0.3, -0.3, -0.3, 1);
		Vector v7 = new Vector(-0.3, -0.3, 0.3, 1);
		Vector v8 = new Vector(0.3, -0.3, 0.3, 1);
		
		vertices.add(v1);
		vertices.add(v2);
		vertices.add(v3);
		vertices.add(v4);
		vertices.add(v5);
		vertices.add(v6);
		vertices.add(v7);
		vertices.add(v8);
	}
	
	// Project the box onto the canvas
	public void projectBox() {
		projectedVertices.clear();
		// Project the 3D vertices onto the z-axis
	}
	
	
	/**
	 * Used to rotate the box around the y-axis
	 * @param theta
	 *			Amount of degrees (rad?) to rotate the box
	 */
	public void RotateYAxis(double theta) {
		List<Vector> matrixVectors = new ArrayList<>();
		Vector a1 = new Vector(Math.cos(theta),0,-Math.sin(theta), 0);
		Vector a2 = new Vector(0,1,0,0);
		Vector a3 = new Vector(Math.sin(theta),0,Math.cos(theta), 0);
		Vector a4 = new Vector(0,0,0,1);
		matrixVectors.add(a1);
		matrixVectors.add(a2);
		matrixVectors.add(a3);
		matrixVectors.add(a4);
		Matrix m = new Matrix(matrixVectors);
		// Using matrix multiplication to calculate all vectors at the same time
		Matrix n = new Matrix(vertices);		
		Matrix calculatedMatrix = m.matrixMultiplication(n);
		// Put the projected vectors (as matrix) into projectedVertices
		vertices = calculatedMatrix.getMatrix();
	}
	
	/**
	 * Used to rotate the box around the x-axis
	 * @param theta
	 *			Amount of degrees (rad?) to rotate the box
	 */
	public void RotateXAxis(double theta) {
		List<Vector> matrixVectors = new ArrayList<>();
		Vector a1 = new Vector(1,0,0, 0);
		Vector a2 = new Vector(0,Math.cos(theta),Math.sin(theta),0);
		Vector a3 = new Vector(0,-Math.sin(theta),Math.cos(theta), 0);
		Vector a4 = new Vector(0,0,0,1);
		matrixVectors.add(a1);
		matrixVectors.add(a2);
		matrixVectors.add(a3);
		matrixVectors.add(a4);
		Matrix m = new Matrix(matrixVectors);
		// Using matrix multiplication to calculate all vectors at the same time
		Matrix n = new Matrix(vertices);		
		Matrix calculatedMatrix = m.matrixMultiplication(n);
		// Put the projected vectors (as matrix) into projectedVertices
		vertices = calculatedMatrix.getMatrix();
	}
	
	/**
	 * Used to rotate the box around the z-axis
	 * @param theta
	 *			Amount of degrees (rad?) to rotate the box
	 */
	public void RotateZAxis(double theta) {
		List<Vector> matrixVectors = new ArrayList<>();
		Vector a1 = new Vector(Math.cos(theta),Math.sin(theta),0, 0);
		Vector a2 = new Vector(-Math.sin(theta),Math.cos(theta),0,0);
		Vector a3 = new Vector(0,0,1, 0);
		Vector a4 = new Vector(0,0,0,1);
		matrixVectors.add(a1);
		matrixVectors.add(a2);
		matrixVectors.add(a3);
		matrixVectors.add(a4);
		Matrix m = new Matrix(matrixVectors);
		// Using matrix multiplication to calculate all vectors at the same time
		Matrix n = new Matrix(vertices);		
		Matrix calculatedMatrix = m.matrixMultiplication(n);
		// Put the projected vectors (as matrix) into projectedVertices
		vertices = calculatedMatrix.getMatrix();
	}
	
	public void calculateIntoScreen() {
		// Screen width and size is 900 pixels
		/*
		 * For every vector, change every coordinate value
		 * into screen size and put the new values into a new vector
		 * and then put the vectors into a vertices list
		 */
		// Z value should be 0 after projection
		List<Vector> screenVertices = new ArrayList<>();
		for (Vector v : projectedVertices) {
			// Get x pixel position
			double xValue = v.getValue(0);
			// Turn into pixel position
			int xPixel = (int)(xValue * 1100 /(2) + 1100/2);
			// Get y pixel position
			double yValue = v.getValue(1);
			// Turn into pixel position
			int yPixel = (int)(-yValue * 1100 /(2) + 1100/2);
			// Get z pixel position
			double zValue = v.getValue(2);
			// Turn into pixel position
			int zPixel = (int)(zValue * 1100 /(2));
			Vector newVector = new Vector(xPixel, yPixel, zPixel, 1);
			screenVertices.add(newVector);
		}
		projectedVertices = screenVertices;
	}
	
	public void calculateProjection() {
		double d = camera.getValue(2);
		List<Vector> matrixVectors = new ArrayList<>();
		Vector a1 = new Vector(1,0,0,0);
		Vector a2 = new Vector(0,1,0,0);
		Vector a3 = new Vector(0,0,0,-1/d);
		Vector a4 = new Vector(0,0,0,1);
		matrixVectors.add(a1);
		matrixVectors.add(a2);
		matrixVectors.add(a3);
		matrixVectors.add(a4);		
		Matrix m = new Matrix(matrixVectors);
		// Using matrix multiplication to calculate all vectors at the same time
		Matrix n = new Matrix(vertices);		
		Matrix calculatedMatrix = m.matrixMultiplication(n);
		// Need to divide the first three components by the fourth component
		List<Vector> columns = calculatedMatrix.getMatrix();
		List<Vector> correctVectors = new ArrayList<>();
		for (int i = 0; i < calculatedMatrix.getWidth(); i++) {
			// Value to divide the coordinates with
			double divisor = columns.get(i).getValue(3);
			List<Double> vals = new ArrayList<>();
			for (int j = 0; j < calculatedMatrix.getDim(); j++) {
				double value = columns.get(i).getValue(j);
				value /= divisor;
				vals.add(value);
			}
			Vector correct = new Vector(vals);
			correctVectors.add(correct);
		}
		// Put the projected vectors (as matrix) into projectedVertices
		projectedVertices = correctVectors;
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		calculateProjection();
		calculateIntoScreen();
		g.setColor(Color.GREEN);
		this.setBackground(Color.BLACK);

		// This will create a line between every vertex in the box
		for (int i = 0; i < projectedVertices.size(); i++) {
			g.setColor(Color.RED);
			g.drawOval((int)projectedVertices.get(i).getValue(0)-5, (int)projectedVertices.get(i).getValue(1)-5,
					10, 10);
			for (int j = 0; j < projectedVertices.size(); j++) {
				// Return if it's the same vector. No need to draw
				if (i == j) continue;
				g.setColor(Color.GREEN);
				Vector v1 = projectedVertices.get(i);
				Vector v2 = projectedVertices.get(j);
				// x and y plane
				g.drawLine((int)v1.getValue(0), (int)v1.getValue(1),
						(int)v2.getValue(0), (int)v2.getValue(1));
			}
		}
	}
}
