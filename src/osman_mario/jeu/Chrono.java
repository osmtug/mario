package osman_mario.jeu;

public class Chrono implements Runnable{

	private final int PAUSE = 5; //temp d'attente entre deux tour de boucle
	
	@Override
	public void run() {
		while(true) {
			
			Main.scene.repaint();
			try {
				Thread.sleep(PAUSE);
			} catch (InterruptedException e) {}
		}
	}
}
