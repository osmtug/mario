package osman_mario.objet;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

import osman_mario.jeu.Main;

public abstract class Objet {

	protected  int largeur, hauteur;
	protected  float x, y, yAbsolue;
	public  Image img;
	private  int cpt;
	protected String str;
	private  boolean anime = false;
	protected boolean animable = false;
	protected  int piece = -1;
	protected  Color color;
	protected  String item = "";
	
	public Objet(float x, float y, int largeur, int hauteur, Color color) {
		this.setPosX(x);
		this.y = y;
		this.yAbsolue = this.y;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.setCpt(-1);
		this.setColor(color);
	}
	
	public Objet(float x, float y, int largeur, int hauteur, String str) {
		this.setPosX(x);
		this.y = y;
		this.yAbsolue = this.y;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.setCpt(-1);
		this.str = str;
		ImageIcon icobloc = new ImageIcon(getClass().getResource(str));
		this.img = icobloc.getImage();
	}
	
	//constructeur de base
	public Objet(float x, float y, int largeur, int hauteur) {
		this.setPosX(x);
		this.y = y;
		this.yAbsolue = this.y;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.setCpt(-1);
	}
	
	//constructeur pour les blocs interrogations avec des pièces
	public Objet(float x, float y, int largeur, int hauteur, String str, int cpt, int item) {
		this.setPosX(x);
		this.y = y;
		this.yAbsolue = this.y;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.setCpt(cpt);
		this.str = str;
		this.setPiece(item);
		ImageIcon icobloc = new ImageIcon(getClass().getResource(str));
		this.img = icobloc.getImage();
	}
	
	//constructeur pour les blocs interrogations avec un champignon
	public Objet(float x, float y, int largeur, int hauteur, String str, int cpt, String s) {
		this.setPosX(x);
		this.y = y;
		this.yAbsolue = this.y;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.cpt = cpt;
		this.str = str;
		this.item = s;
		ImageIcon icobloc = new ImageIcon(getClass().getResource(str));
		this.img = icobloc.getImage();
	}
	
	//deplacement pour faire glisser les objet vers la gauche de l'écran pour simuler la caméra qui suit mario 
	public void deplacement() {
		this.x -= Main.scene.getMario().getVeloX();
	}
	
	
	//renvoit l'image du bloc en faisant les animations si il y en a
	public Image getImage() {
	if(this.cpt!=-1) {
		int n = 40;
		this.cpt++;
		int cpt = this.cpt / n;
		ImageIcon ico = new ImageIcon(getClass().getResource(str.substring(0,str.length()-8)+"interro"+cpt+".png"));
		this.img = ico.getImage();
		if(this.cpt >= (4 * n)-1) {this.cpt = 0;}
	}
		return(this.img);
	}
	
	//animation d'un objet qui monte en hauteur puis qui redécend a sa place de base (doit déja être monter légèrement)
	public void animation(int hauteur, float vitesse) {
		if(this.anime || this.y != this.yAbsolue) {
			if(this.y > this.yAbsolue) {
				this.anime = false;
				this.y = this.yAbsolue;
				
			} else if(this.y > this.yAbsolue-hauteur && this.anime){
				this.y -= vitesse;
			} else {
				this.y +=vitesse;
				this.anime = false;
			}
		}
	}
	
	//*******getter & setter*********

	public int getPiece() {
		return piece;
	}

	public void setPiece(int piece) {
		this.piece = piece;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public boolean isAnimable() {
		return animable;
	}

	public int getLargeur() {
		return largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public float getPosX() {
		return x;
	}

	public void setPosX(float x) {
		this.x = x;
	}
	
	public float getPosY() {
		return y;
	}

	public int getCpt() {
		return cpt;
	}

	public void setCpt(int cpt) {
		this.cpt = cpt;
	}

	public boolean isAnime() {
		return anime;
	}

	public void setAnime(boolean anime) {
		this.anime = anime;
	}
	
	public void setAnimable(boolean animable) {
		this.animable = animable;
	}

	public void setPosY(float y) {
		this.y = y;
	}

}
