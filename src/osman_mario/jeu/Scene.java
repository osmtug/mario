package osman_mario.jeu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import osman_mario.objet.Bloc;
import osman_mario.objet.Interrogation;
import osman_mario.objet.Objet;
import osman_mario.objet.PieceBloc;
import osman_mario.objet.Tuyau;
import osman_mario.objet.Vide;
import osman_mario.personnage.Champignon;
import osman_mario.personnage.Goomba;
import osman_mario.personnage.Mario;
import osman_mario.personnage.Personnage;

@SuppressWarnings("serial")
public class Scene extends JPanel{
	
	public final static int BLOCSIZE = 25;

	private ImageIcon icoFond;
	private Image fond1;
	
	private float xFond1;

	//position de l'ecran sur le niveau
	private float posx;
	
	//booléen pour savoir si on appuie sur une touche
	public boolean rightpressed, leftpressed, cpressed, spacepressed, downpressed;
	
	
	private Mario mario;
	private List<Vide> niveau1vide = new ArrayList<Vide>();
	private List<Float> placevide = new ArrayList<Float>();
	public List<Champignon> allchampi = new ArrayList<Champignon>();
	public List<PieceBloc> allPieceBloc = new ArrayList<PieceBloc>();
	public List<Personnage> allMob = new ArrayList<Personnage>();
	public List<Goomba> allGoomba = new ArrayList<Goomba>();
	
	private List<Objet> niveau1 = new ArrayList<Objet>();
	private List<Objet> allniveau1 = new ArrayList<Objet>();
	private boolean dead = false;
	private boolean detruire = false;
	private Font font;
	
	
	
	
	public Scene() {
		super();
		
		//création du niveau 1
		niv1();
		
		this.posx = -1;
		//
		icoFond = new ImageIcon(getClass().getResource("/image/fond1.png"));
		this.fond1 = this.icoFond.getImage();
		
		mario = new Mario(50, 245);
		
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(new Clavier());
		
		Thread chronoEcran = new Thread(new Chrono());
		chronoEcran.start();
		
		
	}
	
	public void niv1() {
		try {
			InputStream inputStream = getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf");
		    font = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(12f);
		    font = font.deriveFont(Font.BOLD);
		} catch (FontFormatException | IOException e) {
		    e.printStackTrace();
		}
		
		allniveau1 = new ArrayList<Objet>();
		niveau1vide = new ArrayList<Vide>();
		placevide = new ArrayList<Float>();
		
		allchampi = new ArrayList<Champignon>();
		allMob = new ArrayList<Personnage>();
		allGoomba = new ArrayList<Goomba>();
		allPieceBloc = new ArrayList<PieceBloc>();
		niveau1 = new ArrayList<Objet>();
		
		this.xFond1 = 0;
		
		//remplissage de tous les objets du niveau
		allniveau1.add(new Bloc(500, 225));
		allniveau1.add(new Bloc(550, 225));
		allniveau1.add(new Tuyau(700, 275,"tuyauverthaut"));
		allniveau1.add(new Bloc(600, 225));
		allniveau1.add(new Interrogation(525, 225,"power"));
		allniveau1.add(new Interrogation(575, 225,1));
		allniveau1.add(new Interrogation(400, 225,1));
		allniveau1.add(new Interrogation(550, 125,5));
		allniveau1.add(new Tuyau(950, 250,"tuyauverthaut"));
		allniveau1.add(new Tuyau(950, 275,"corpvertvertical"));
		allniveau1.add(new Tuyau(1150, 225,"tuyauverthaut"));
		allniveau1.add(new Tuyau(1150, 250,"corpvertvertical"));
		allniveau1.add(new Tuyau(1150, 275,"corpvertvertical"));
		allniveau1.add(new Tuyau(1425, 225,"tuyauverthaut"));
		allniveau1.add(new Tuyau(1425, 250,"corpvertvertical"));
		allniveau1.add(new Tuyau(1425, 275,"corpvertvertical"));
		allniveau1.add(new Bloc(1950, 225));
		allniveau1.add(new Bloc(2000, 225));
		allniveau1.add(new Bloc(2025, 125));
		allniveau1.add(new Bloc(2050, 125));
		allniveau1.add(new Bloc(2075, 125));
		allniveau1.add(new Bloc(2100, 125));
		
		//remplissage de tous les trous dans le sol du niveau
		niveau1vide.add(new Vide(1727, 325, new Color(92, 148, 252)));
		niveau1vide.add(new Vide(1727, 350, new Color(92, 148, 252)));
		niveau1vide.add(new Vide(1752, 350, new Color(92, 148, 252)));
		niveau1vide.add(new Vide(1752, 325, new Color(92, 148, 252)));
	
		placevide.add((float) 1724);
		placevide.add((float) 1780);
		
		//remplissage de tous les mobs du niveau 
		allMob.add(new Goomba(804,303));
		allMob.add(new Goomba(771,303));
		
		allniveau1.add(new Interrogation(1500, 225,"power"));
		
		
	}
	
	//------------geters---------
	
	public Mario getMario() {return this.mario;}
	
	public boolean isDetruire() {
		return detruire;
	}
	
	//----------seters------

	public void setxFond1(int n) {this.xFond1 = n;}

	public void setDetruire(boolean detruire) {
		this.detruire = detruire;
	}
	
	//-----------methode---------
	
	public void deplacementFond() {
		//décalage des element du terrain vers la gauche quand mario dépasse la moitier de l'ecran pour donner l'illusion d'un suivie de caméra
		if(this.mario.getPosX() > 300 && this.mario.getVeloX() >0) {
			this.posx = (this.posx) + this.mario.getVeloX();
			this.xFond1 = this.xFond1 - this.mario.getVeloX();
			for(int i=0; i<niveau1.size(); i++) {
				niveau1.get(i).deplacement();
			}
			for(int i=0; i<niveau1vide.size(); i++) {
				niveau1vide.get(i).deplacement();
			}
			for(int i=0; i< placevide.size() ; i++) {
				placevide.set(i, placevide.get(i)-this.mario.getVeloX());
			}
			for (int i=0; i<this.allchampi.size(); i++) {
				this.allchampi.get(i).deplacement();
			}
			for (int i=0; i<this.allPieceBloc.size(); i++) {
				this.allPieceBloc.get(i).deplacement();
			}
			for(int i=0; i<this.allGoomba.size(); i++) {
				this.allGoomba.get(i).deplacement();
			}
		} else if(this.mario.getPosX() >= 0) {  //sinon déplacement de mario sur l'écran sur l'axe x
			this.mario.setPosX(this.mario.getPosX() + this.mario.getVeloX());
		}else {  //empecher mario de dépasser l'ecran du coté gauche
			this.mario.setPosX(0);
			if(this.mario.getVeloX() >0) {
				this.mario.setPosX(this.mario.getPosX() + this.mario.getVeloX());
			}
			mario.stopX();
		}
		//decalage du fond si mario s'éloigne trop pour donner un fond infinie
		if(this.xFond1 <= -1200) {
			this.xFond1 = this.xFond1 +1200;
		}
	}
	
	
	
	
	
	
	

	
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//calcule pour la taille du fond
		Graphics g2 = (Graphics2D)g;
		double scale = (double) getHeight() / fond1.getHeight(null);
	    int width = (int) (fond1.getWidth(null) * scale);
	    int height = getHeight();
	    
	    
	    //boucle de tout les elements du niveau 1 pour les afficher sur l'écran si ils sont proche de l'écran
	    for(int i=0; i<allniveau1.size(); i++) {
	    	if(allniveau1.get(i).getPosX()-this.posx < 900 ) {
	    		allniveau1.get(i).setPosX(allniveau1.get(i).getPosX()-this.posx);
	    		niveau1.add(allniveau1.get(i));
	    		allniveau1.remove(i);
	    	}
	    }
	    
	    
	    //boucle de tout les mobs du niveau 1 pour les afficher sur l'écran si ils sont proche de l'écran
	    for(int i=0; i<allMob.size(); i++) {
	    	if(allMob.get(i).getPosX()-this.posx < 700-i*33 ) {
	    		allMob.get(i).setPosX(allMob.get(i).getPosX()-this.posx);
	    		if(allMob.get(i).getMob() == "goomba")
	    		allGoomba.add((Goomba) allMob.get(i));
	    		allMob.remove(i);
	    	}
	    }
	    
	    //recharge du niveau en cas de décès
	    if(dead) {
	    	this.mario = new Mario(50, 245);
	    	this.posx = -1;
	    	this.dead = false;
	    	niv1();
	    }
	    
	    
	  if(!mario.isAnimation()) {
		  	//getstion des actions de mario en fonction des inputs
		    if(downpressed && !this.mario.isEnlaire()) {
		    	mario.ralenti();
		    	mario.ralenti();
		    }else if(rightpressed == leftpressed) {
		    	mario.ralenti();
		    }else if(rightpressed) {
		    	if(this.posx == -1) {
					this.posx = 0;
					this.setxFond1(0);
				}
				
		    	mario.deplacementDroit();
				
		    }else {
				
		    	mario.deplacementGauche();
		    }
		    
		    
		    
		    
		    mario.marcher();
		    
		    
		    if(mario.isEnlaire()) {
		    	mario.gravite();
		    }else {
		    	this.mario.stopY();;
		    	if(this.spacepressed) {
		    		mario.saut();
		    	}
		    }
		    
		    
		    //gestion de mario si il est trop bas (au niveau du sol)
		    if(this.mario.surSolEnBas()) {
		    	//on considère mario sur le sol si il est trop bas sur l'ecran
		    	this.mario.setEnlaire(false);
		    	
		    	
		    }else {
		    	
		    	this.mario.setEnlaire(true);
		    }
		    //considere mario en l'air si il est sur une case de vide meme si il est trop bas sur l'écran
		    for(int i=0; i<this.placevide.size(); i+=2) {
		    	if(this.dansVide(this.mario, placevide.get(i), placevide.get(i+1))) {
		    		this.mario.setEnlaire(true);
		    	}else if(placevide.get(i+1) <0){
		    		placevide.remove(i);
		    		placevide.remove(i);
		    	}
		    }
		    if(!mario.isEnlaire()) {
		    	this.mario.setSolEnBas();
		    }
	  }
	  
	  //tue mario si il est sorti trop bas de l'écran
	  dead = this.mario.estMort();
	    
	    
	    //boucle pour gerer les objet présent sur l'écran
	    for(int i=0; i<niveau1.size(); i++) {
	    	//calcule des collisions de mario(que si mario est en vie)
	    	if(this.mario.getTransfo() >=0 ) {
		    	if(this.mario.collisionDessous(niveau1.get(i))) {
		    		//on considère mario sur le sol si il a un objet en dessous
		    		this.mario.setEnlaire(false);
		    		this.mario.stopY();
		    		this.mario.setCptSaut(1000); //permet a mario de sauter dès qu'il a toucher le sol
		    	}else if(this.mario.collisionDessus(niveau1.get(i))) {
			    	this.mario.stopY();
			    	this.mario.setCptSaut(1000); //permet a mario de sauter dès qu'il a toucher le sol
	
			    }
	    	
			    if (this.mario.collisionAvant(niveau1.get(i))) {
			    	
			    	this.mario.stopX();
			    }
	    	}
	    	
		    
		    //calcule des collisions des champignons avec les objets
		    for(int j=0; j<this.allchampi.size(); j++) {
		    	//verifie si le champignon est dans un bloc
		    	if(allchampi.get(j).estdedans(niveau1.get(i))) {
		    		allchampi.get(j).dedans = true;
		    	}
		    	else {
		    		if(allchampi.get(j).collisionAvant(niveau1.get(i))) {
		    			allchampi.get(j).inverserSense();
		    		}else if(allchampi.get(j).collisionDessous(niveau1.get(i))) {
		    			allchampi.get(j).setEnlaire(false);
		    		}
		    		
		    		
		    	}
		    }
		    
		    //calcule des collisions des goombas avec les objets
		    for(int j=0; j<this.allGoomba.size(); j++) {
		    	if (this.allGoomba.get(j).collisionAvant(niveau1.get(i))) {
		    		allGoomba.get(j).inverserSense();
		    	}else {
		    		if(this.allGoomba.get(j).collisionDessous(niveau1.get(i))) {
		    			allchampi.get(j).setEnlaire(false);
		    		}
		    		
		    	}
		    }
		    
		    //animation des blocs si mario a tapé en dessous 
		    niveau1.get(i).animation(7, 0.5f);
		    
		    //destruction de l'objet si il est sortie de l'ecran ou si il a été cassé
		    if(niveau1.get(i).getPosX()<-100 || isDetruire()) {
		    	this.setDetruire(false);
		    	niveau1.remove(i);
		    }
	    }
	   
	    if(!mario.isAnimation()) {
	    	
		    this.deplacementFond();
		    
		    //deplacement de mario sur l'écran sur l'axe y
		    this.mario.setY(this.mario.getPosY() + this.mario.getVeloY());
		    
		    //boucle pour les actions des champignons sur l'écran
		    for(int i=0; i<this.allchampi.size(); i++) {
		    	
		    	//on verifie si le champigon est dans trou ou pas
		    	if(this.allchampi.get(i).surSolEnBas()) {
			    	if(!this.allchampi.get(i).dansVide(placevide)) {
			    		this.allchampi.get(i).setEnlaire(false);
			    		this.allchampi.get(i).setSolEnBas();
			    	}else {
			    		this.allchampi.get(i).setEnlaire(true);
			    	}
		    	}
		    	
		    	
		    	if(!allchampi.get(i).isEnlaire()) {
		    		allchampi.get(i).stopY();
		    	}
		    	
		    	this.allchampi.get(i).deplacementEcran();
		    	allchampi.get(i).dedans = false;
		    	
		    	//on fait disparaitre le champignon si mario le prend 
		    	if(allchampi.get(i).estdedans(mario)) {
		    		//on fait grandir mario si le champignon prit est rouge
		    		if(allchampi.get(i).getCouleur() == "rouge" && this.mario.getTransfo() == 0) {
		    			this.mario.setTransfo(1);
		    			this.mario.setInvicible(true);
		    			this.mario.setAnimation(true);
		    		}
		    		allchampi.remove(i);
		    	}else if(allchampi.get(i).getPosX()<-22 || allchampi.get(i).getPosY()>375) { //supprime le champignon si il sort de l'écran
			    	allchampi.remove(i);
			    }

		    }
		    
		  //boucle pour les actions des goombas sur l'écran
		    for(int i=0; i<this.allGoomba.size(); i++) {
		    	if(this.allGoomba.get(i).surSolEnBas()) {
			    	if(!this.allGoomba.get(i).dansVide(placevide)) {
			    		this.allGoomba.get(i).setEnlaire(false);
			    		this.allGoomba.get(i).setSolEnBas();
			    	}else {
			    		this.allGoomba.get(i).setEnlaire(true);
			    	}
		    	}
		    	
		    	
		    	if(!allGoomba.get(i).isEnlaire()) {
		    		allGoomba.get(i).stopY();
		    	}
		    	
		    	//calcule des collision entre les goombas
		    	for(int j=0; j<this.allGoomba.size(); j++) {
		    		if (this.allGoomba.get(j).estdedans(allGoomba.get(i))) {
			    		allGoomba.get(j).inverserSense();
			    		allGoomba.get(i).inverserSense();
			    	}
		    	}
		    	//on affiche un goomba écrasé pendant quelque temps si le goomba est mort
		    	if(this.allGoomba.get(i).isDead()) {
		    		this.allGoomba.get(i).incCptDeath();
		    		if(this.allGoomba.get(i).getCptDeath() == 100) {
		    			allGoomba.remove(i);
		    		}
		    	}else {
			    	this.allGoomba.get(i).deplacementEcran();
			    	
			    	//mario saute sur le goomba
			    	if(mario.collisionDessous(allGoomba.get(i))) {
			    		mario.saut();
			    		mario.addScore(100);
			    		mario.setCptSaut(10);  //fait sauter mario un peu moins haut si mario reste appuyer sur espace après avoir tué un goomba (pas nécéssaire)
			    		allGoomba.get(i).setDead(true);
			    	}else if(mario.estdedans(allGoomba.get(i))&& !mario.isInvicible()) {
			    		//fait prendre un dégat a mario si il s'est cogner au goomba
			    		mario.degat();
			    	}else if(allGoomba.get(i).getPosX()<-22 || allGoomba.get(i).getPosX()>900) { //on fait disparaitre les goombas trop loin de l'écran
				    	allGoomba.remove(i);
				    }
		    	}
		    }
		    
		  //boucle pour les actions des pieces qui sortent des blocs sur l'écran
		    for(int i=0; i<this.allPieceBloc.size(); i++) {
		    	this.allPieceBloc.get(i).animation(50, 1);
		    	if(allPieceBloc.get(i).estFini()) {
		    		this.allPieceBloc.remove(i);
		    	}
		    }
		    
		    
	    }else{
	    	//animation de mario :
	    	
	    	//on incrémente un compteur pour savoir on en est a où dans l'animation
	    	mario.setCptanime(mario.getCptanime() + 1);
	    	
	    	//si mario a prit un champignon
	    	if(this.mario.getTransfo() >=1) {
		    	if(mario.getCptanime()%40 == 0) { //on le fait rétrécir légèrement 
		    		mario.setHauteur(mario.getHauteur() - 6); //on modifie la hauteur a chaque foi =s que l'on midifie la taille pour qu'il reste sur le sol 
		    		mario.setY(mario.getPosY() + 6);
		    	}else if(mario.getCptanime()%20==0) {  // on le fait grandir (doit être supérieur au rétrécissement sinon il sera bloqué)
		    		mario.setHauteur(mario.getHauteur() + 14);
		    		mario.setY(mario.getPosY() - 14);
		    		if(mario.getHauteur()>44) { // si mario est trop grand on le met a la bonne taille et on arrête l'animation 
		    			mario.setY(mario.getPosY() + (mario.getHauteur()-44));
		    			mario.setHauteur(44);
		    			mario.setCptanime(0);
		    			mario.setInvicible(false);
		    			mario.setAnimation(false);
		    		}
		    	}
	    	}else if(this.mario.getTransfo() == 0){ //si mario s'est prit un dégat
	    		this.downpressed = false; // on l'empeche de rester accroupie car mario ne peut pas être acroupie en étant petit
	    		if(mario.getCptanime()%160==0) {  //on arrête au bout d'un certain temps
	    			mario.setAnimation(false);
	    			mario.setCptanime(0);
	    			
	    		}else if(mario.getCptanime()%80==0) { //a la moitié du temps on réduit la taille de mario
	    			mario.setY(mario.getPosY() + (mario.getHauteur()-22));
	    			mario.setHauteur(22);
	    			
	    		}
	    	}else { //si mario est mort 
	    		//on ne fait rien pendant quelque temps(jusqu'a que cptanime == 80
	    		if(this.mario.getCptanime()>80) {//une fois que mario a sauté on applique la gravite jusqu'a qu'il sort de l'écran
	    			mario.setEnlaire(true);
	    			mario.gravite();
	    			mario.setY(mario.getPosY() + mario.getVeloY());
	    		}else if(this.mario.getCptanime()==80) { //quand le temp est arrivé on fait sauté mario
	    			mario.saut();
	    		}
	    	}
	    }
	    
	    
	    
	    
	    
	    
	    //dessin du fond
		g2.drawImage(this.fond1, Float.valueOf(xFond1).intValue(), 0, width, height, null);
		g2.drawImage(this.fond1, Float.valueOf(xFond1).intValue()+width, 0, width, height, null);
		
		//dessin des troue vide 
		g2.setColor(this.niveau1vide.get(0).getColor());
		for(int i=0; i<this.niveau1vide.size(); i++) {
			g2.fillRect((int) this.niveau1vide.get(i).getPosX(), (int) this.niveau1vide.get(i).getPosY()-2, this.niveau1vide.get(i).getLargeur(), this.niveau1vide.get(i).getHauteur()+2);
		}
		
		//dessin des champignons
		for(int i=0; i<this.allchampi.size(); i++) {
			g2.drawImage(allchampi.get(i).getImage(), (int)allchampi.get(i).getPosX(), (int)allchampi.get(i).getPosY(), allchampi.get(i).getLargeur(), allchampi.get(i).getHauteur(), null);
	    }
		
		//dessin des pièces qui sortent des blocs
		for(int i=0; i<this.allPieceBloc.size(); i++) {
			g2.drawImage(allPieceBloc.get(i).getImage(), (int) this.allPieceBloc.get(i).getPosX(), (int) this.allPieceBloc.get(i).getPosY(), this.allPieceBloc.get(i).getLargeur(), this.allPieceBloc.get(i).getHauteur(), null);
		}
		
		//dessin des goombas
		for(int i=0; i<allGoomba.size(); i++) {
			g2.drawImage(allGoomba.get(i).getImage(), (int)allGoomba.get(i).getPosX(), (int)allGoomba.get(i).getPosY(), allGoomba.get(i).getLargeur(), allGoomba.get(i).getHauteur(), null);
		}
		
		//dessin des objet du niveau
		for(int i=0; i<niveau1.size(); i++) {
			g2.drawImage(niveau1.get(i).getImage(), (int)niveau1.get(i).getPosX(), (int)niveau1.get(i).getPosY(), niveau1.get(i).getLargeur(), niveau1.get(i).getHauteur(), null);
		}
		
		//dessin de mario
		g2.drawImage(this.mario.marcheMario("mario", 25, 4), (int) this.mario.getPosX(), (int) this.mario.getPosY(),mario.getLargeur(),mario.getHauteur(), null);
		
		//dessin des écritures en haut de l'écran
		g2.setFont(font);
	    g2.setColor(Color.WHITE);
		g2.drawString("Score : " + mario.getScore(), 20, 20);
		g2.drawString("Piece : " + mario.getPiece(), 190, 20);
		
	}

	private boolean dansVide(Personnage p, float i, float j) {
		if(p.getPosX() > i  && p.getPosX()+p.getLargeur() < j ) {
			return true;
		}
		return false;
	}

	
}
