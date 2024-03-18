package osman_mario.jeu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Clavier implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Main.scene.rightpressed = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			Main.scene.leftpressed = true;
		}else if(e.getKeyCode() == KeyEvent.VK_C) {
			Main.scene.cpressed = true;
			Main.scene.getMario().courir();
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			Main.scene.spacepressed = true;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(Main.scene.getMario().getTransfo() >0 && !Main.scene.getMario().isAnimation()) {
				if(!Main.scene.downpressed)
					Main.scene.getMario().setY(Main.scene.getMario().getPosY() + 14);
				Main.scene.getMario().setHauteur(30);
				Main.scene.downpressed = true;
			}
		}else if(e.getKeyCode() == KeyEvent.VK_G) {
			if(Main.scene.getMario().getTransfo() >0) {
				Main.scene.getMario().setAnimation(true);
				Main.scene.getMario().setInvicible(true);
				Main.scene.getMario().setTransfo(0);
			}else {
				Main.scene.getMario().setTransfo(-1);
				Main.scene.getMario().setAnimation(true);
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			Main.scene.leftpressed = false;
		}else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Main.scene.rightpressed = false;
		}else if(e.getKeyCode() == KeyEvent.VK_C) {
			Main.scene.cpressed = false;
			Main.scene.getMario().arretCourir();
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			Main.scene.spacepressed = false;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			Main.scene.downpressed = false;
			if(Main.scene.getMario().getTransfo() >0) {
				Main.scene.getMario().setY(Main.scene.getMario().getPosY() - 14);
				Main.scene.getMario().setHauteur(44);
			}
		}
		
	}

}
