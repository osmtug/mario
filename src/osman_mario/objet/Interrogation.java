package osman_mario.objet;

import osman_mario.jeu.Scene;

public class Interrogation extends Objet{
	
	
	
	public Interrogation(float x ,float y) {
		super(x, y, Scene.BLOCSIZE, Scene.BLOCSIZE, "/image/bloc_vide.png");
	}
	
	public Interrogation(float x ,float y, int i) {
		super(x, y, Scene.BLOCSIZE, Scene.BLOCSIZE, "/image/bloc_vide.png", 0, i);
		this.animable = true;
	}
	
	public Interrogation(float x ,float y, String s) {
		super(x, y, Scene.BLOCSIZE, Scene.BLOCSIZE, "/image/bloc_vide.png", 0, s);
		this.animable = true;
	}

}
