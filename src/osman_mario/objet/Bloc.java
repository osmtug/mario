package osman_mario.objet;

import osman_mario.jeu.Scene;

public class Bloc extends Objet{
	
	
	public Bloc(float x ,float y) {
		super(x, y, Scene.BLOCSIZE, Scene.BLOCSIZE, "/image/bloc.png");
		this.animable = true;
	}
	
	

}
