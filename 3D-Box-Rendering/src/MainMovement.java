import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class MainMovement {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1100, 1100);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Initialize camera position
        List<Double> cameraPos = new ArrayList<>();
        cameraPos.add(0.0);
        cameraPos.add(0.0);
        cameraPos.add(1.0);
        Camera camera = new Camera(cameraPos);

		// Rotation speed
		double speed = 1.5;
        
        // Create box with camera
        Box box = new Box(camera);
        frame.add(box);
        
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Check for key input
                int key = e.getKeyCode();
                // Rotate the box depending on input
                switch (key) {
                    case KeyEvent.VK_W:
                        box.RotateXAxis(Math.toRadians(speed)); // Rotate up
                        break;
                    case KeyEvent.VK_S:
                        box.RotateXAxis(Math.toRadians(-speed)); // Rotate down
                        break;
                    case KeyEvent.VK_A:
                        box.RotateYAxis(Math.toRadians(speed)); // Rotate left
                        break;
                    case KeyEvent.VK_D:
                        box.RotateYAxis(Math.toRadians(-speed)); // Rotate right
                        break;
                    case KeyEvent.VK_UP:
                        box.RotateXAxis(Math.toRadians(speed)); // Rotate up (arrow key)
                        break;
                    case KeyEvent.VK_DOWN:
                        box.RotateXAxis(Math.toRadians(-speed)); // Rotate down (arrow key)
                        break;
                    case KeyEvent.VK_LEFT:
                        box.RotateYAxis(Math.toRadians(speed)); // Rotate left (arrow key)
                        break;
                    case KeyEvent.VK_RIGHT:
                        box.RotateYAxis(Math.toRadians(-speed)); // Rotate right (arrow key)
                        break;
                }
                frame.repaint(); // Repaint the frame after rotation
            }

            @Override
            public void keyTyped(KeyEvent e) {
                // Not needed for this implementation
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Not needed for this implementation
            }
        });

        frame.setFocusable(true); // Ensure frame has focus to receive key events
    }
}
