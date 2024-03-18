package osman_mario.objet;

import java.awt.Color;

import osman_mario.jeu.Scene;
import osman_mario.personnage.Personnage;

public class Vide extends Objet{
	
	
	public Vide(float x ,float y, Color color) {
		super(x, y, Scene.BLOCSIZE, Scene.BLOCSIZE, color);
	}
	
	public boolean dansVide(Personnage p) {
		if(p.getPosX() > this.getPosX()  && p.getPosX()+p.getLargeur() < this.getPosX()+this.getLargeur() ) {
			return true;
		}
		return false;
	}

}
