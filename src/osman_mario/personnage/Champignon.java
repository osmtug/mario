package osman_mario.personnage;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Champignon extends Personnage{
	private Image champi;
	private ImageIcon icochampi;
	private String couleur;
	public boolean dedans = true;
		
	public Champignon(float x, float y, String str) {
		super(x, y, 22, 22);
			
		this.couleur = str;
		this.icochampi = new ImageIcon(getClass().getResource("/image/champi"+str+".png"));
		this.champi = this.icochampi.getImage();
		this.veloX = 0.6f;
	}

	public Image getImage() {return champi;}
	
	public String getCouleur() {return couleur;}

	public void deplacementEcran() {
		if(this.veloY<1.5 && this.enlaire) {
			this.veloY += 0.04f;
    	}
    	this.enlaire = true;
    	if(this.dedans) {
    		this.y -= 0.1f;
    	}else {
    		this.x += this.veloX;
    		this.y += this.veloY;
    	}
		
	}

	

	
	
	
	

}
