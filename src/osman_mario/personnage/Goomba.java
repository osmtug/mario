package osman_mario.personnage;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Goomba extends Personnage{
	
	private Image goombaImg;
	private ImageIcon icogoomba;
	private boolean dead = false;
	private int cptDeath = 0;

	public Goomba(int x, int y) {
		super(x, y, 22, 22);
		
		this.icogoomba = new ImageIcon(getClass().getResource("/image/mob/goomba.png"));
		this.goombaImg = this.icogoomba.getImage();
		this.veloX = -0.3f;
	}
	
	public void deplacementEcran() {
		if(this.veloY <1.5 && this.isEnlaire()) {
			this.veloY += 0.04f;
    	}
    	this.setEnlaire(true);
    	this.x += this.veloX;
    	this.y += this.veloY;
	}

	public Image getImage() {
		if(this.dead) {
			ImageIcon ico = new ImageIcon(getClass().getResource("/image/mob/goombamarronsdead.png"));
			return ico.getImage();
		}
		int n = 30;
		this.cpt += 1;
		int cpt = this.cpt / n;
		ImageIcon ico = new ImageIcon(getClass().getResource("/image/mob/goombamarrons"+cpt+".png"));
		this.goombaImg = ico.getImage();
		if(this.cpt >= (2 * n)-1) {this.cpt = 0;}
		return(this.goombaImg);
	}
	
	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}
	
	public String getMob() {
		return "goomba";
	}

	public int getCptDeath() {
		return cptDeath;
	}

	public void incCptDeath() {
		this.cptDeath++;
	}

}
