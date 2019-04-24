/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package application;

/**
 *
 * @author netbook
 */
import java.net.URL;
import java.util.LinkedList;
import java.util.regex.Pattern;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Pistolero extends Rectangle {
	 private DoubleProperty vitesse = new SimpleDoubleProperty();
	 

	private double movex = 0;
	private double movey = 0;

	
	private double X;
	private double Y;
	private double rotate=0;
	private int nb_balle_autorise=0;
	
     public IntegerProperty nb_balle_restante=new SimpleIntegerProperty();
    
	public boolean tire=false;
	
	public IntegerProperty getNb_balle_restante() {
		return nb_balle_restante;
	}

	public void setNb_balle_restante(int nb_balle_restante) {
		this.nb_balle_restante = new SimpleIntegerProperty(nb_balle_restante);
	}


	
	public int getNb_balle_autorise() {
		return nb_balle_autorise;
	}

	public void setNb_balle_autorise(int nb_balle_autorise) {
		this.nb_balle_autorise = nb_balle_autorise;
		nb_balle_restante=new SimpleIntegerProperty(nb_balle_autorise);
	}

	
	
	
private LinkedList<Balle> list_balle= new LinkedList<Balle>();

	

	public Pistolero(double x, double y, int nb_balle,URL urlImage) {
		this.setWidth(50);
		this.setHeight(50);
		
		X = x / 2;
		Y = y / 1.5;
		
		System.out.println(X);

		this.setTranslateX(X);
		this.setTranslateY(Y);
		nb_balle_autorise=nb_balle;
		
		this.getStyleClass().add("Pistolero");

		URL url = getClass().getResource("/media/player.png");
		Image cat = new Image(url.toString());
		//cat.widthProperty().s
		this.setFill(new ImagePattern(cat));

	}
	
	public DoubleProperty getVitesse() {
		return vitesse;
	}

	public void setVitesse(DoubleProperty vitesse) {
		this.vitesse = vitesse;
	}
	public double getrotate() {
		return rotate;
	}

	public void setrotate(double rotate) {
		this.rotate = rotate;
	}

	public double getMovex() {
		return movex;
	}

	public void setMovex(double movex) {
		this.movex = movex;
	}

	public double getMovey() {
		return movey;
	}

	public void setMovey(double movey) {
		this.movey = movey;
	}

	public double getx() {
		return X;
	}

	public void setx(double x) {
		X = x;
	}

	public double gety() {
		return Y;
	}

	public void sety(double y) {
		Y = y;
	}

	public void move() {

		X += movex;
		Y += movey;
		

		if (X + this.getWidth() <= ((Region) getParent()).getWidth() && X >= 0 && Y >= 0
				&& Y + this.getHeight() <= ((Region) getParent()).getHeight()) {

			this.setTranslateX(X);
			this.setTranslateY(Y);
			this.setRotate(rotate);
		} else {
			X = this.getTranslateX();

			Y = this.getTranslateY();

		}
	}
	
	
	
	public void shoot() {
		
		//for (int i = nb_balle_tire; i < list_balle.size(); i++) {
		//System.out.println("nbballe"+nb_balle_autorise);
		
		if(tire && nb_balle_autorise==0) {
			
		this.addBalle(new Balle(this.getx()+this.getWidth()/2,this.gety()));
		
			((Pane) getParent()).getChildren().add(list_balle.getLast());
			
			tire=false;
			}else if(tire && nb_balle_autorise>0 && nb_balle_restante.intValue()>0) {
				
				this.addBalle(new Balle(this.getx()+this.getWidth()/2,this.gety()));
				
				((Pane) getParent()).getChildren().add(list_balle.getLast());
				nb_balle_restante.set(nb_balle_restante.intValue()-1);;
				//System.out.println("nouvelle valeur"+nb_balle_restante.intValue());
				
				tire=false;
			}else 
		
		
		
	
		
		 for (Balle p : list_balle) {
             p.moveballe();
            
         }
	
	}
	
	
	
	
	public void addBalle(Balle b) {
		list_balle.add(b);
		
	}
	
	public void removeballe(Balle b) {
		list_balle.remove(b);
		
	}

}

