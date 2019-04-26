package application;

import java.util.LinkedList;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class Intersect {

	public static void collision(Balle list_balle, Obstacle p) {

		Timeline tl = new Timeline();
		tl.setCycleCount(Animation.INDEFINITE);
		KeyFrame moveBall = new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				Bounds bound_obstacle = p.getBoundsInParent();
				if (list_balle.getBoundsInParent().intersects(bound_obstacle.getMinX(), bound_obstacle.getMinY(),
						bound_obstacle.getMinZ(), bound_obstacle.getWidth(), bound_obstacle.getHeight(),
						bound_obstacle.getDepth())) {

					System.out.println("colision");

					list_balle.setVitessey(-list_balle.getVitessey());
					list_balle.setVitessex(list_balle.randomVitesse() * 5);
					// list_balle.get(i).setCenterX(list_balle.get(i).getCenterX()+);
				}

			}
		});
		tl.getKeyFrames().add(moveBall);
		tl.play();
	}

	public static void collision(Demon deamon, Obstacle p) {

		/*
		 * AnimationTimer timer = new AnimationTimer() { long collision = 0;
		 * 
		 * @Override public void handle(long now) {
		 * 
		 * // System.out.println(now);
		 * 
		 * Bounds bound_obstacle = p.getBoundsInParent();
		 * 
		 * 
		 * 
		 * if (deamon.getBoundsInParent().intersects(bound_obstacle.getMinX(),
		 * bound_obstacle.getMinY(), bound_obstacle.getMinZ(),
		 * bound_obstacle.getWidth(), bound_obstacle.getHeight(),
		 * bound_obstacle.getDepth())) { if (collision == 0 ||(now-collision ) / 10000 >
		 * 10) { System.out.println("collision" + ( now-collision) / 10000);
		 * collision=1; Random random = new Random(System.nanoTime()); int t =
		 * random.nextInt(3);
		 * 
		 * if (t == 0) { deamon.setVy(-deamon.getVy());
		 * 
		 * deamon.setVx(deamon.getVx()); } else if (t == 1) {
		 * deamon.setVy(-deamon.getVy());
		 * 
		 * deamon.setVx(-deamon.getVx());
		 * 
		 * } else { deamon.setVy(deamon.getVy());
		 * 
		 * deamon.setVx(-deamon.getVx()); } collision = System.nanoTime();
		 * //System.out.println("collision" + (collision - now) / 10000);
		 * 
		 * }
		 * 
		 * 
		 * // timer.wait(100);
		 * 
		 * }
		 * 
		 * } };
		 * 
		 * timer.start();
		 */

		Timeline tl = new Timeline();
		tl.setCycleCount(Animation.INDEFINITE);
		KeyFrame intersectDeamonOB = new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				Bounds bound_obstacle = p.getBoundsInParent();

				if (deamon.getBoundsInParent().intersects(bound_obstacle.getMinX(), bound_obstacle.getMinY(),
						bound_obstacle.getMinZ(), bound_obstacle.getWidth(), bound_obstacle.getHeight(),
						bound_obstacle.getDepth())) {

					Random random = new Random(System.nanoTime());
					int t = random.nextInt(3);

					if (t == 0) {
						deamon.setVy(-deamon.getVy());

						deamon.setVx(deamon.getVx());
					} else if (t == 1) {
						deamon.setVy(-deamon.getVy());

						deamon.setVx(-deamon.getVx());

					} else {
						deamon.setVy(deamon.getVy());

						deamon.setVx(-deamon.getVx());
					}

				}

			}

		});
		tl.getKeyFrames().add(intersectDeamonOB);
		tl.play();

	}

	public static void collision(LinkedList<Balle> list_balle, LinkedList<Demon> p) {
		 //boolean colision=false;
		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				for (int i = 0; i < list_balle.size(); i++) {
					
					for (int j = 0; j < p.size(); j++) {
						
						Bounds bound_obstacle = p.get(j).getBoundsInParent();
						if (list_balle.get(i).getBoundsInParent().intersects(bound_obstacle.getMinX(), bound_obstacle.getMinY(),
								bound_obstacle.getMinZ(), bound_obstacle.getWidth(), bound_obstacle.getHeight(),
								bound_obstacle.getDepth())) {

							
							list_balle.remove(i);
							p.remove(j);
							System.out.println("reste"+p.size());

							// list_balle.get(i).setCenterX(list_balle.get(i).getCenterX()+);
						}
						
						
					}
					
				}

				

			}
		};

		timer.start();
	}

	public static double randomPosition(double positionMax) {
		Random random = new Random(System.nanoTime());
		double t = Math.floor(Math.random() * ((positionMax - 60) - 60 + 1)) + 60;

		return t;
	}

}
