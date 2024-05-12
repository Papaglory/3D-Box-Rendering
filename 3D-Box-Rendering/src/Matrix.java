import java.util.ArrayList;
import java.util.List;

public class Matrix {

	private List<Vector> matrix = new ArrayList<>();
	
	private int width;
	private int height;
	
	public Matrix(List<Vector> vectors) {
		if (vectors.isEmpty()) throw new NegativeArraySizeException("list size must be greater  than 0");
		this.height = vectors.get(0).getDim();
		//make sure each vector have the same dim
		for(Vector v : vectors) {
			if (v.getDim() != height) throw new IllegalArgumentException("all vectors must have same dim");
		}
		this.width = vectors.size();
		//correct sizes, thus it's safe to set as matrix
		matrix = vectors;
	}
	
	/**
	 * Apply vector multiplication with this matrix object.
	 * @param v
	 * 		v vector with columns
	 * @return
	 * 		return a new vector that has been transformed by 'this.matrix'
	 */
	public Vector vectorMultiplication(Vector v) {
		if (v.getDim() != width) throw new IllegalArgumentException("v must be same width as matrix");
		
//		if (v.getDim() == width) System.out.println("correct sizes");
		List<Double> newVectorValues = new ArrayList<>();
		for (int i = 0; i < height; i++) {
			double sum = 0;
			for (int j = 0; j < width; j++) {
				//Going through left and down
				//(x,y,z) * (v1, v2, v3).getX();
				sum += v.getValue(j) * matrix.get(j).getValue(i);
			}
			newVectorValues.add(sum);
		}
		//check vector dimensions
		if (newVectorValues.size() != 4) {
//			throw new IllegalArgumentException("size is wrong");
		} else if (newVectorValues.size() == 3) System.out.println("size is correct");
		//create the new Vector
		Vector newVector = new Vector(newVectorValues);
		return newVector;
	}
	
	/**
	 * left-multiply this matrix with other
	 * @param other
	 * 			matrix to multiply with
	 * @return
	 * 			(this) * (other) AB
	 */
	public Matrix matrixMultiplication(Matrix other) {
		if (other.getDim() != width) throw new IllegalArgumentException("other must have dim == this.width");
		List<Vector> newMatrixVectors = new ArrayList<>();
		for (int i = 0; i < other.getWidth(); i++ ) {
			newMatrixVectors.add(vectorMultiplication(other.getVectorAt(i)));
			
		}
		if (newMatrixVectors.size() != other.getWidth()) System.out.println("wrong in matrixMultiplication");
		
		Matrix newMatrix = new Matrix(newMatrixVectors);
		return newMatrix;
	}
	
	/**
	 * retrieve a vector from the matrix
	 * @param i
	 * @return
	 * 			retrieve the vector at column i
	 */
	public Vector getVectorAt(int i) {
		return matrix.get(i);
	}
	
	/**
	 * @return the amount of columns in the matrix
	 */
	public int getWidth() {
		return this.width;
	}
	
	public List<Vector> getMatrix() {
		return matrix;
	}
	
	public int getDim() {
		return height;
	}
}