package application;

import java.net.URL;

import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Obstacle extends Circle {
	
	private double xobs;
	private double yobs;
	Circle hearth;
	
	public Obstacle(double x, double y) {
		setRadius(40);
		URL url = getClass().getResource("/media/rock.png");
		Image cat = new Image(url.toString());
		//cat.widthProperty().s
		this.setFill(new ImagePattern(cat));
		this.setStroke(Color.ALICEBLUE);
		
		
		xobs = x;
		 yobs= y;
		 
		  hearth=new Circle();
		 hearth.setRadius(this.getRadius()-10);
		 
		
		

		this.setTranslateX(xobs);
		this.setTranslateY(yobs);
	}
	

}
