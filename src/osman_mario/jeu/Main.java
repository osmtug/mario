package osman_mario.jeu;

import javax.swing.JFrame;

public class Main {

	public static Scene scene;
	
	public static void main(String[] args) {
		JFrame window = new JFrame("mario bros");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(700,409);
		window.setLocationRelativeTo(null);
		window.setAlwaysOnTop(true);
		
		scene = new Scene();
		
		window.setContentPane(scene);
		window.setVisible(true);

	}

}
