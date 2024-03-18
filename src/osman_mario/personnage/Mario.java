package osman_mario.personnage;

import java.awt.Image;

import javax.swing.ImageIcon;

import osman_mario.jeu.Main;
import osman_mario.objet.Objet;
import osman_mario.objet.PieceBloc;

public class Mario extends Personnage{
	private Image mario;
	private boolean invicible = false, animation = false;
	private int cptanime=0;
	private long debutinvincible ;
	private ImageIcon icoMario;
	private int transfo = 0;
	private float acceleration = 0.007f;
	private float veloMax = 1.2f;
	private int cptSaut;
	private int piece=0, score=0;

	

	public Mario(int x, int y) {
		super(x, y, 22, 22);
		
		icoMario = new ImageIcon(getClass().getResource("/image/petit/mariostayright.png"));
		this.mario = this.icoMario.getImage();
	}
	
	public Image getMario() {return this.mario;}
	
	public Image marcheMario(String nom, int frequence, int nbimg) {
		String str = "/image/";
		ImageIcon ico;
		Image img;
		int cpt;
		this.cpt++;
		cpt = this.cpt / frequence;
		if(this.cpt == (nbimg * frequence)-1) {this.cpt =0;}
		
		
		if(this.isInvicible() && cpt%2==0 && this.getTransfo() == 0) {
			if(System.currentTimeMillis() - this.getDebutinvincible() > 3000) {
				this.invicible = false;
				this.debutinvincible = 0;
			}
			ico = new ImageIcon(getClass().getResource("/image/petit/rien.png"));
			img = ico.getImage();
			return img;
			
		}

		if(this.hauteur == 22) {
			str = str+"petit/";
			if(this.getTransfo() == -1) {
				ico = new ImageIcon(getClass().getResource(str+"death.png"));
				img = ico.getImage();
				return img;
			}
		}else if(this.getTransfo() !=2) {
			str = str + "grand/";
			if(Main.scene.downpressed) {
				if(this.versDroite) {
					ico = new ImageIcon(getClass().getResource(str+"mariocrounchright.png"));
					img = ico.getImage();
					return img;
				}else {
					ico = new ImageIcon(getClass().getResource(str+"mariocrounchleft.png"));
					img = ico.getImage();
					return img;
				}
			}
		}else{
			str = str + "feu/";
			if(Main.scene.downpressed) {
				if(this.versDroite) {
					ico = new ImageIcon(getClass().getResource(str+"mariocrounchright.png"));
					img = ico.getImage();
					return img;
				}else {
					ico = new ImageIcon(getClass().getResource(str+"mariocrounchleft.png"));
					img = ico.getImage();
					return img;
				}
			}
		}
		
		
		if(this.isEnlaire()) {
			if(this.versDroite) {
				ico = new ImageIcon(getClass().getResource(str+"mariojumpright.png"));
				img = ico.getImage();
				return img;
			}else {
				ico = new ImageIcon(getClass().getResource(str+"mariojumpleft.png"));
				img = ico.getImage();
				return img;
			}
		}else if(Main.scene.rightpressed && this.veloX<0.0f) {
			ico = new ImageIcon(getClass().getResource(str+"marioturnright.png"));
			img = ico.getImage();
			return img;
		}else if ( Main.scene.leftpressed && this.veloX>0.0f) {
			ico = new ImageIcon(getClass().getResource(str+"marioturnleft.png"));
			img = ico.getImage();
			return img;
		}else if(!(this.marche) || this.isAnimation()) {
			if(this.versDroite) {str= str + nom + "stayright.png";}
			else {str= str + nom + "stayleft.png";}
		}else {
			if(this.versDroite) {str= str + nom + "walkright"+cpt+".png";}
			else {str= str + nom + "walkleft"+cpt+".png";}
			
		}
		ico = new ImageIcon(getClass().getResource(str));
		img = ico.getImage();
		return img;
	}
	
	public boolean collisionDessus(Objet o) {
		if(this.x + this.largeur > o.getPosX()+8 && this.x < o.getPosX() + o.getLargeur()-8 && this.y < o.getPosY() + o.getHauteur() && this.y > o.getPosY() + o.getHauteur() -5 && this.veloY < 0) {
			this.y = o.getPosY() + o.getHauteur();
			if(o.isAnimable()) {
				o.setPosY(o.getPosY()-0.1f);
				o.setAnime(true);
				if(o.getPiece() != -1) {
					o.setPiece(o.getPiece() - 1);
					this.addPiece();
					PieceBloc p = new PieceBloc(o.getPosX()+7, o.getPosY()-3);
					p.setAnime(true);
					p.setPosY(p.getPosY()-1);
					Main.scene.allPieceBloc.add(p);
					if(o.getPiece() == 0) {
						o.setPiece(-1);
						o.setAnimable(false);
						o.setCpt(-1);
						ImageIcon ico = new ImageIcon(getClass().getResource("/image/bloc_vide.png"));
						o.img = ico.getImage();
					}
				}else if(o.getItem() =="power") {
					o.setAnimable(false);
					o.setCpt(-1);
					ImageIcon ico = new ImageIcon(getClass().getResource("/image/bloc_vide.png"));
					o.img = ico.getImage();
					Main.scene.allchampi.add(new Champignon(o.getPosX()+1, o.getPosY()-3, "rouge"));
				}else if(this.getTransfo()!=0){
					Main.scene.setDetruire(true);
				}
			}
			return true;
		}
		return false;
	}
	
	public void ralenti() {
		if(this.veloX<0.05f && this.veloX>-0.05f) {
			this.veloX = 0.0f;
		} else if(this.veloX > 0) {
			this.veloX -= 0.007f;
		}else {
			this.veloX += 0.007f;
		}
	}
	
	public void deplacementDroit() {
		if(!isEnlaire()) {
			this.versDroite =true;
			if(veloX<this.veloMax) {
				veloX += this.acceleration;
				if (this.veloX < 0) {
					veloX += 0.005f;
				}
			}
		}else {
			if(veloX<this.veloMax) {
				veloX += 0.007;
				if (this.veloX < 0) {
					veloX += 0.005f;
				}
			}
		}
	}
	
	public void deplacementGauche() {
		if(!isEnlaire()) {
			this.versDroite =false;
			if(veloX >-this.veloMax) {
				veloX -= this.acceleration;
				if(this.veloX > 0) {
					veloX -= 0.005f;
				}
			}
		}else {
			if(veloX >-this.veloMax) {
				veloX -= 0.007;
				if(this.veloX > 0) {
					veloX -= 0.005f;
				}
			}
		}
	}
	
	public void gravite() {
		this.cptSaut++;
		if(getVeloY()<2 && (!Main.scene.spacepressed || this.cptSaut > 53)) {
			this.veloY += 0.04f;
			this.setCptSaut(1000);
		}
	}
	
	public void saut() {
		this.veloY = -1.5f;
		setCptSaut(0);
	}
	
	//*********getters & setters**********

	public int getCptanime() {
		return cptanime;
	}

	public void setCptanime(int cptanime) {
		this.cptanime = cptanime;
	}
	
	public float getVeloX() {
		return this.veloX;
	}

	public boolean isAnimation() {
		return animation;
	}

	public void setAnimation(boolean animation) {
		this.animation = animation;
	}

	public int getTransfo() {
		return transfo;
	}

	public void setTransfo(int transfo) {
		this.transfo = transfo;
	}

	public boolean isInvicible() {
		return invicible;
	}

	public void setInvicible(boolean invicible) {
		this.invicible = invicible;
	}

	public void setY(float y) {
		this.y = y;
		
	}

	public int getCptSaut() {
		return cptSaut;
	}

	public void setCptSaut(int cptSaut) {
		this.cptSaut = cptSaut;
	}

	public long getDebutinvincible() {
		return debutinvincible;
	}

	public void setDebutinvincible(long debutinvincible) {
		this.debutinvincible = debutinvincible;
	}
	
	public int getPiece() {
		return piece;
	}

	public int getScore() {
		return score;
	}

	public void courir() {
		this.veloMax = 1.7f;
		this.acceleration = 0.013f;
	}

	public void arretCourir() {
		this.veloMax = 1.2f;
		this.acceleration = 0.007f;
	}
	
	

	public void marcher() {
		if(this.veloX != 0.0f) {
			this.marche = true;
	    	if(!Main.scene.cpressed) {
	    		if(this.veloX>1.2f) {
	    			this.veloX -=0.01f;
	    		}else if(this.veloX<-1.2f) {
	    			this.veloX +=0.01f;
	    		}
	    	}
	    }else {
	    	this.marche = false;
	    }
		
	}

	

	public boolean estMort() {
		if(this.y > 500) {
	  		return true;
		}
		return false;
	}
	
	public void addPiece() {
		this.piece++;
		this.score += 200;
	}

	public void degat() {
		this.transfo--;
		this.debutinvincible = System.currentTimeMillis();
		this.animation = true;
		this.invicible = true;
		
	}

	public void addScore(int n) {
		this.score += n;
		
	}
	
	

}
