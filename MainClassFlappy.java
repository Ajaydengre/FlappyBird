package flppy;
import javax.swing.JFrame;



public class MainClassFlappy {
public static void main(String[] args){
		
		JFrame frame=new JFrame("FlappyBird");
		frame.setContentPane(new GamePlayF());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		frame.setSize(800,600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
	}
	

}
