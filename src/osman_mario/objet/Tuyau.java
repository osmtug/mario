package osman_mario.objet;


import osman_mario.jeu.Scene;

public class Tuyau extends Objet{
	
	
	public Tuyau(float x ,float y, String s) {
		super(x, y, Scene.BLOCSIZE*2, Scene.BLOCSIZE*2, "/image/"+s+".png");
	}

}
