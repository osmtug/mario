package osman_mario.personnage;



import java.util.List;

import osman_mario.jeu.Main;
import osman_mario.objet.Objet;

public abstract class Personnage {
	protected int hauteur, largeur;
	protected int cpt;
	protected float x, veloY, y, veloX;
	protected boolean marche, versDroite;
	protected boolean enlaire = true;

	public Personnage(float x, float y, int hauteur, int largeur) {
		this.x = x;
		this.y =y;
		this.veloX = 0.0f;
		this.veloY = 0.0f;
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.cpt = 0;
		this.marche = false;
		this.versDroite = true;
	}
	
	
	public boolean collisionAvant(Objet o) {
		if (this.y +this.hauteur > o.getPosY()+2 && this.y < o.getPosY() + o.getHauteur() -2) {
			if ((this.x + this.largeur >= o.getPosX() && this.x + this.largeur <= o.getPosX() +10 && this.veloX >=0)) {
				this.setPosX(o.getPosX() - this.largeur);
				this.marche = false;
				return true;
			}
			if (this.x <= o.getPosX()+o.getLargeur() && this.x > o.getPosX()+o.getLargeur() -10 && this.veloX <=0) {
				this.x = o.getPosX() + o.getLargeur();
				this.marche = false;
				return true;
			}
		}
		return false;
	}

	

	
	public boolean collisionDessous(Objet o) {
		if(this.x + this.largeur > o.getPosX()+1 && this.x < o.getPosX() + o.getLargeur()-1 && this.y + this.hauteur >= o.getPosY() && this.y + this.hauteur < o.getPosY() + 3 && this.getVeloY() >= 0) {
			this.y =o.getPosY() - this.hauteur;
			return true;
		}
		return false;
	}
	
	public void deplacement() {
		this.x -= Main.scene.getMario().veloX;
	}
	
	public boolean estdedans(Objet o) {
		if(this.x+this.largeur > o.getPosX()+3 && this.x < o.getPosX()+o.getLargeur()-3 && this.y+this.hauteur > o.getPosY()+3 && this.y < o.getPosY()+o.getHauteur()-3)
			return true;
		return false;
	}
	
	public boolean estdedans(Personnage p) {
		if(this.x+this.largeur > p.x && this.x < p.x+p.largeur && this.y+this.hauteur > p.y+3 && this.y < p.y+p.hauteur)
			return true;
		return false;
	}
	
	public boolean collisionDessous(Personnage p) {
		if(this.x + this.largeur > p.x && this.x < p.x + p.largeur && this.y + this.hauteur >= p.y && this.y + this.hauteur < p.y + 3) {
			return true;
		}
		return false;
	}
	
	
	
	public void inverserSense() {
		this.veloX = -this.veloX;
		
	}

	public void stopY() {
		this.veloY = 0.0f;
	}
	
	public void stopX() {
		this.veloX = 0.0f;
	}
	
	public boolean surSolEnBas() {
		return this.y >= 325 - this.hauteur && this.y <= 325 - this.hauteur+3;
	}

	public void setSolEnBas() {
		this.y = 325 - this.hauteur;
	}
	
	public boolean dansVide(List<Float> placevide) {
		for(int i=0; i<placevide.size(); i+=2) {
	    	if(this.x > placevide.get(i)  && this.x+this.largeur < placevide.get(i+1) ) {
	    		return true;
	    	}
	    }
		return false;
		
	}
	
	


	public float getPosX() {
		return x;
	}


	public void setPosX(float x) {
		this.x = x;
	}


	public int getLargeur() {
		return largeur;
	}


	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}


	public boolean isEnlaire() {
		return enlaire;
	}


	public void setEnlaire(boolean enlaire) {
		this.enlaire = enlaire;
	}


	public float getPosY() {
		return y;
	}


	public int getHauteur() {
		return hauteur;
	}


	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}


	public float getVeloY() {
		return veloY;
	}


	public String getMob() {
		// TODO Auto-generated method stub
		return "";
	}
	
	
	
}