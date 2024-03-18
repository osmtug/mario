package osman_mario.objet;

import java.awt.Image;

import javax.swing.ImageIcon;

public class PieceBloc extends Objet{

	public PieceBloc(float x, float y){
		super(x, y, 10, 20);
	}
	
	public Image getImage() {
		
		int n = 20;
		this.setCpt(this.getCpt() + 1);
		int cpt = this.getCpt() / n;
		ImageIcon ico = new ImageIcon(getClass().getResource("/image/piece"+cpt+".png"));
		this.img = ico.getImage();
		if(this.getCpt() >= (6 * n)-1) {this.setCpt(0);}
		return(this.img);
	}

	//renvoit true si l'animation de la pièce est fini
	public boolean estFini() {
		if(this.y == this.yAbsolue) {
			return true;
		}
		return false;
	}

}
