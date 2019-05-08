package application;

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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Pistolero extends Circle {
	private DoubleProperty vitesse = new SimpleDoubleProperty();

	private double movex = 0;
	private double movey = 0;

	private double X;
	private double Y;
	private double rotate = 0;
	private int nb_balle_autorise = 0;

	public IntegerProperty nb_balle_restante = new SimpleIntegerProperty();

	public boolean tire = false;

	private LinkedList<Balle> list_balle = new LinkedList<Balle>();

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
		nb_balle_restante = new SimpleIntegerProperty(nb_balle_autorise);
	}

	public LinkedList<Balle> getList_balle() {
		return list_balle;
	}

	public void setList_balle(LinkedList<Balle> list_balle) {
		this.list_balle = list_balle;
	}

	public Pistolero(double x, double y, int nb_balle, URL urlImage) {
		this.setRadius(30);
		//this.setHeight(50);

		X = x / 2;
		Y = y / 1.5;

		System.out.println(X);

		this.setCenterX(X);
		this.setCenterY(Y);
		nb_balle_autorise = nb_balle;

		this.getStyleClass().add("pistolero");

		URL url = getClass().getResource("/media/player.png");
		Image cat = new Image(url.toString());
		// cat.widthProperty().s
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

		if (X + this.getRadius() <= ((Region) getParent()).getWidth() && X >= 0 && Y >= 0
				&& Y + this.getRadius() <= ((Region) getParent()).getHeight()) {

			this.setCenterX(X);
			this.setCenterY(Y);
			this.setRotate(rotate);
		} else {
			X = this.getCenterX();

			Y = this.getCenterY();

		}
	}

	public void shoot(LinkedList<Obstacle> p,LinkedList<Demon> d) {

		// for (int i = nb_balle_tire; i < list_balle.size(); i++) {
		// System.out.println("nbballe"+nb_balle_autorise);

		if (tire && nb_balle_autorise == 0) {
			
			Balle nouvelleBalle=new Balle(this.getx() + this.getRadius() / 2, this.gety());
			for (int j = 0; j < p.size(); j++) {
				
				Intersect.collision(nouvelleBalle, p.get(j));

			}
		
				

			
			this.addBalle(nouvelleBalle);

			((Pane) getParent()).getChildren().add(list_balle.getLast());

			tire = false;
			
			
				

			
			
		} else if (tire && nb_balle_autorise > 0 && nb_balle_restante.intValue() > 0) {
			
			Balle nouvelleBalle=new Balle(this.getx() + this.getRadius() / 2, this.gety());
			this.addBalle(nouvelleBalle);
			
			for (int j = 0; j < p.size(); j++) {
				Intersect.collision(nouvelleBalle, p.get(j));

			}
			
			

			((Pane) getParent()).getChildren().add(list_balle.getLast());
			nb_balle_restante.set(nb_balle_restante.intValue() - 1);
			;
			 System.out.println("nouvelle valeur"+nb_balle_restante.intValue());

			tire = false;
			
		} else

			for (int i = 0; i < list_balle.size(); i++) {
				
			
				if(list_balle.get(i).moveballe()) {
					
					((Pane) getParent()).getChildren().remove(list_balle.get(i));
				
					list_balle.remove(list_balle.get(i));
				}

			}

		//System.out.println("liste de balle" + list_balle.size());

	}

	public void addBalle(Balle b) {
		list_balle.add(b);

	}

	public void removeballe(Balle b) {
		list_balle.remove(b);

	}

}
