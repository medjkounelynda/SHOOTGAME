package application;

import java.awt.Rectangle;
import java.net.URL;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class demon extends javafx.scene.shape.Rectangle{
	private double x;
	private double y;
	private DoubleProperty vitesse = new SimpleDoubleProperty();
	public enum Langage {
		  male,femelle;
		}
	
	private Langage type;
	
	public demon(double xx, double yy){
		
		this.setWidth(50);
		this.setHeight(50);
		URL url = getClass().getResource("/media/shooter2.png");
		Image cat = new Image(url.toString());
		//cat.widthProperty().s
		this.setFill(new ImagePattern(cat));
		//System.out.println("width"+xx+yy);
		x = xx;
		 y= yy;
		
		

		this.setTranslateX(x);
		this.setTranslateY(y);
		
	}
	
	
	public DoubleProperty getVitesse() {
		return vitesse;
	}

	public void setVitesse(DoubleProperty vitesse) {
		this.vitesse = vitesse;
	}
	
	void move() {
		
		
	}

}
