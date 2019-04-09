package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.glass.ui.Timer;

import application.demon;
import application.pistolero;
import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;

public class Controller implements Initializable {

	@FXML
	private Pane sceneGame;
	
	 @FXML
	    Slider slider;
	 
	 private KeyCode up;
	 private KeyCode down;
	 private KeyCode right;
	 private KeyCode left;
	 private KeyCode tire;
	 
	 private int nbBalle=0;
	 private int nbDemon=20;
	 private IntegerProperty nbballe_restante=new SimpleIntegerProperty();
	 private IntegerProperty ndemon_restant=new SimpleIntegerProperty();
	 private URL urlImagePist;
	 
	
	
	
	public URL getUrlImagePist() {
		return urlImagePist;
	}

	public void setUrlImagePist(URL urlImagePist) {
		this.urlImagePist = urlImagePist;
	}

	public IntegerProperty getNbballe_restante() {
		return nbballe_restante;
	}

	public void setNbballe_restante(IntegerProperty nbballe_restante) {
		this.nbballe_restante = nbballe_restante;
	}

	public int getNbBalle() {
		
		return nbBalle;
	}

	public void setNbBalle(int nbballe) {
		
		this.nbBalle = nbballe;
		postelero.setNb_balle_autorise(nbBalle);
	}

	public KeyCode getUp() {
		return up;
	}

	public void setUp(KeyCode up) {
		this.up = up;
	}

	public KeyCode getDown() {
		return down;
	}

	public void setDown(KeyCode down) {
		System.out.println("je suis laaaaa");
		this.down = down;
	}

	public KeyCode getRight() {
		return right;
	}

	public void setRight(KeyCode right) {
		this.right = right;
	}

	public KeyCode getLeft() {
		return left;
	}

	public void setLeft(KeyCode left) {
		this.left = left;
	}

	public KeyCode getTire() {
		return tire;
	}

	public void setTire(KeyCode tire) {
		this.tire = tire;
	}

	pistolero postelero;

	@FXML
	void Direction(KeyEvent e) {
		System.out.println("lw;q;q"+down.getName());
		if (e.getCode() == up) {
			postelero.setMovey(-postelero.getVitesse().doubleValue());
System.out.println("lw;q;q"+up.getName());
		}
		if (e.getCode() == down) {
			postelero.setMovey(postelero.getVitesse().doubleValue());

		}
		if (e.getCode() == left) {
			postelero.setMovex(-postelero.getVitesse().doubleValue());

		}
		if (e.getCode() == right) {
			postelero.setMovex(postelero.getVitesse().doubleValue());

		}
		if (e.getCode() == tire) {
			//System.out.println(postelero.getx());
			
			//postelero.addBalle(new balle(postelero.getx(), postelero.gety()));
			
			postelero.tire=true;

		}
	}

	@FXML
	void Direction2(KeyEvent e) {

		if (e.getCode() == up) {
			postelero.setMovey(0);

		}
		if (e.getCode() == down) {
			postelero.setMovey(0);

		}
		if (e.getCode() == left) {
			postelero.setMovex(0);

		}
		if (e.getCode() == right) {
			postelero.setMovex(0);

		}
		if (e.getCode() == tire) {
			//System.out.println(postelero.getx());
			postelero.tire=false;
			

		}
	}

	@FXML
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		postelero=new pistolero(sceneGame.getPrefWidth(), sceneGame.getPrefHeight(),nbBalle,urlImagePist);
		
		
		sceneGame.getChildren().add(postelero);
		
		postelero.getVitesse().bind(slider.valueProperty()
                .multiply(1 / 0.3));
		
		double y=0;
		double xx=0;
		//nbballe_restante.bind(postelero.nb_balle_restante.asObject());
		for (int i = 0; i < nbDemon; i++) {
			System.out.println("hhhdjd");
			double x=(i*70)%(sceneGame.getPrefWidth()-50);
			
			
			System.out.println("merdeee"+x);
			if(xx>x) {
				xx=x;
				 y=(y+70)%(sceneGame.getPrefHeight()-50);
			}else {
				xx=x;
			}
			demon c=new demon(x, y);
			sceneGame.getChildren().add(c);
			
		}
		
		
		
	
		
		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {

				postelero.move();
				postelero.shoot();
				nbballe_restante.set(postelero.nb_balle_restante.intValue());
				//System.out.println(nbballe_restante);
				
				

			}
		};

		timer.start();

	}
	
	
	

}
