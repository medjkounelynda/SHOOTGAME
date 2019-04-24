package application;

import java.util.LinkedList;
import java.util.Random;

import javafx.geometry.Bounds;

public class Intersect {
	public static void collision(Pistolero joueur, Obstacle p) {

	}

	public static void collision(LinkedList<Balle> list_balle,LinkedList<Obstacle> p) {
		for (int i = 0; i < list_balle.size(); i++) {
			
			for (int j = 0; j < p.size(); j++) {
				Bounds bound_obstacle = p.get(j).getBoundsInParent();
				if (list_balle.get(i).getBoundsInParent().intersects(bound_obstacle.getMinX() + 20,
						bound_obstacle.getMinY() + 10, bound_obstacle.getMinZ(), bound_obstacle.getWidth() - 30,
						bound_obstacle.getHeight() - 25, bound_obstacle.getDepth())) {
					//list_balle.remove(i);
					j=p.size();
					
					list_balle.get(i).setVitessey(-list_balle.get(i).getVitessey());
					list_balle.get(i).setVitessex(list_balle.get(i).randomVitesse()*5);
					
					
				}
			}
			
			
			
			

		}

	}
	
	
	public static double randomPosition(double positionMax ) {
		 Random random = new Random(System.nanoTime());
		 double t=Math.floor(Math.random() * ((positionMax-60) - 60 + 1)) + 60;
		/* for (int i = 0; i < positionInterdite.size(); i++) {
			 if (positionInterdite.get(i)==t) {
				  t=Math.floor(Math.random() * (positionMax-60 - positionMax-60 + 1)) + positionMax-60;
				  i=0;
			}*/
			
		//}
		 
		// System.out.println("loiiiiiiii"+t);
		// System.out.println("max"+(positionMax-60));
		// System.out.println("min"+t);
		return t;
	}

}
