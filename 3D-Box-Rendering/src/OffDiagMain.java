
import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

public class OffDiagMain {
	
	public static void main(String[] args) {
		JFrame frame = new ModifiedFrame(1100, 1100);
		//this is the only camera and box in the scene
		List<Double> cameraPos = new ArrayList<>();
		cameraPos.add(0.0);
		cameraPos.add(0.0);
		cameraPos.add(1.0);
		Camera camera = new Camera(cameraPos);
		Box box = new Box(camera);
		frame.add(box);
		double cap = 0.01;
		double counter = 0;
		//keep going until the program is closed
		while(true) {
			//check if it's time to create new frame
			if (counter < System.nanoTime() / 10000000) {
				counter = (System.nanoTime() / 10000000);
				counter += cap;
				box.RotateYAxis(Math.toRadians(0.4));
				box.RotateXAxis(Math.toRadians(0.2));
				box.RotateZAxis(Math.toRadians(0.2));
				frame.repaint();
			}
		}
	}
}