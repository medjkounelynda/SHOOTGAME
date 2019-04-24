/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import application.Demon;
import application.Intersect;
import application.Obstacle;
import application.Pistolero;
import java.net.URL;
import java.util.LinkedList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author netbook
 */
public class Controller implements Initializable {
    @FXML
	private Pane sceneGame;
	
	 @FXML
	    Slider slider;
	 
	 private int nbBalle=0;
	 private int nbDemon=5;
	 private int nbObstacle = 5;
	 LinkedList<Obstacle> liste_obstacle = new LinkedList<Obstacle>();
	 private Demon[] demons= new Demon[nbDemon]; 
	 
	 private KeyCode up;
	 private KeyCode down;
	 private KeyCode right;
	 private KeyCode left;
	 private KeyCode tire;
	 
	 
         
	 private IntegerProperty nbballe_restante=new SimpleIntegerProperty();
	 private IntegerProperty ndemon_restant=new SimpleIntegerProperty();
	 private URL urlImagePist;
	 
	
	 public int getNbObstacle() {
			return nbObstacle;
		}

		public void setNbObstacle(int nbObstacle) {
			this.nbObstacle = nbObstacle;
		}
	
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

	Pistolero postelero;

	@FXML
	void Direction(KeyEvent e) {
		//System.out.println("lw;q;q"+down.getName());
		if (e.getCode() == up) {
			postelero.setMovey(-postelero.getVitesse().doubleValue());
//System.out.println("lw;q;q"+up.getName());
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Random random = new Random(System.nanoTime());
        postelero=new Pistolero(sceneGame.getPrefWidth(), sceneGame.getPrefHeight(),nbBalle,urlImagePist);
		
		
		sceneGame.getChildren().add(postelero);
		
		postelero.getVitesse().bind(slider.valueProperty()
                .multiply(1 / 0.3));
		
		// -----------------------------------------------ajout
				// obstacle----------------------------------------------
				creationObstacle();
				
				
		
		
		double y=0;
		double xx=0;
	
		for (int i = 0; i < nbDemon; i++) {
			
                    double px = random.nextDouble() * (sceneGame.getPrefWidth() - Demon.radius * 2)
                    + Demon.radius;
            double py = random.nextDouble() * (sceneGame.getPrefHeight() - Demon.radius * 2)
                    + Demon.radius;
            double vx = 8 * (random.nextDouble() - 0.5);
            double vy = 8 * (random.nextDouble() - 0.5);

            
                        demons[i]= new  Demon(px, py, vx,vy);
                        demons[i].rateProperty().bind(slider.valueProperty()
                            .multiply(1 / 0.3));
		
			//Demon c=new Demon(x, y);
			sceneGame.getChildren().add(demons[i]);
                       
			
		}
                 for(int i=0;i<nbDemon; i++) {
                           demons[i].move();
                }
		
		
		
	
		
		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {

				postelero.move();
				postelero.shoot();
				nbballe_restante.set(postelero.nb_balle_restante.intValue());
                                /* for (Demon d: demons) {
                                    d.move();
                                }*/
				//System.out.println(nbballe_restante);
				
				Intersect.collision(postelero.getList_balle(), liste_obstacle);
				
				
				

			}
		};

		timer.start();

	}
	
	
       
    void creationObstacle() {
		boolean creation = true;
		for (int i = 0; i < nbObstacle; i++) {

			Obstacle obstacle_game = new Obstacle(Intersect.randomPosition(sceneGame.getPrefWidth()),
					Intersect.randomPosition(sceneGame.getPrefHeight()));
			if (!obstacle_game.getBoundsInParent().intersects(postelero.getBoundsInParent())) {
				//System.out.println("obstacle pistelero");
				for (int j = 0; j < liste_obstacle.size(); j++) {
					if (liste_obstacle.get(j).getBoundsInParent().intersects(obstacle_game.getBoundsInParent())) {
						j = liste_obstacle.size();
						i = i - 1;
						//System.out.println("change" + creation + i);
						creation = false;
					} else {
						//System.out.println("intersection dobstacle");
						creation = true;
					}

				}

			} else {
				i = i - 1;
				// System.out.println("change pistolero"+creation+i);
				creation = false;
			}

			if (creation) {
				liste_obstacle.add(obstacle_game);
				sceneGame.getChildren().add(obstacle_game);
				

			}

		}
	}
  
}
