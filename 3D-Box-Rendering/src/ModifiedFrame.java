import javax.swing.JFrame;

public class ModifiedFrame extends JFrame {
	
	public ModifiedFrame(int width, int height) {
		this.setSize(width, height);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
