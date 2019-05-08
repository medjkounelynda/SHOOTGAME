package application;

import java.util.LinkedList;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class Intersect {

    public static void collision(Balle list_balle, Obstacle p) {

        Timeline tl = new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame moveBall = new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {
            int i = 0;

            public void handle(ActionEvent event) {
                Bounds bound_obstacle = p.getBoundsInParent();
                if (list_balle.getBoundsInParent().intersects(bound_obstacle.getMinX(), bound_obstacle.getMinY(),
                        bound_obstacle.getMinZ(), bound_obstacle.getWidth(), bound_obstacle.getHeight(),
                        bound_obstacle.getDepth()) && i == 0) {

                    System.out.println("colision" + i++);

                    list_balle.setVitessey(-list_balle.getVitessey());
                    list_balle.setVitessex(list_balle.randomVitesse() * 5);
                    i++;
                    // list_balle.get(i).setCenterX(list_balle.get(i).getCenterX()+);
                } else {
                    i = 0;
                    // System.out.println("pas colision");
                }

            }
        });
        tl.getKeyFrames().add(moveBall);
        tl.play();
    }

    public static void collision(Demon deamon, Obstacle p) {

        Timeline tl = new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame intersectDeamonOB = new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {
            int i = 0;

            public void handle(ActionEvent event) {
                Bounds bound_obstacle = p.getBoundsInParent();

                double dx = deamon.getCenterX() - p.getCenterX();
                double dy = deamon.getCenterY() - p.getCenterY();
                double distance = Math.sqrt(dx * dx + dy * dy);
                double minDist = deamon.getRadius() + p.getRadius();

                if (distance < minDist) {
                    //System.out.println("collision de deamon ");
                    Random random = new Random(System.nanoTime());
                    int t = random.nextInt(3);
                    i++;
                    if (t == 0) {
                        deamon.setVy(-deamon.getVy());

                        deamon.setVx(deamon.getVx());

                    } else if (t == 1) {
                        deamon.setVy(-deamon.getVy());

                        deamon.setVx(-deamon.getVx());

                    } else {
                        i = 0;
                        deamon.setVy(deamon.getVy());

                        deamon.setVx(-deamon.getVx());

                    }
                    deamon.setCenterX(deamon.getCenterX() + (minDist - distance));

                } else {
                    // System.out.println("distance du
                    // obstacle"+p.getCenterX()+"yy"+p.getCenterY());
                    //	System.out.println("pas de collisionde deamon" + minDist + "  " + distance + "demon est x"
                    //	+ deamon.getCenterX() + " y " + deamon.getCenterY());
                }

            }

        });
        tl.getKeyFrames().add(intersectDeamonOB);
        tl.play();

    }

    public static boolean collision(Pistolero joueur, Demon p, Pane panel) {

        double dx = p.getCenterX() - p.getCenterX();
        double dy = joueur.getCenterY() - joueur.getCenterY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        double minDist = joueur.getRadius() + p.getRadius();

        if (distance < minDist) {
            //System.out.println("collision de deamon  joueur");
            Random random = new Random(System.nanoTime());
            return true;
        } else {
            return false;
        }

    }

    public static int collision(LinkedList<Balle> list_balle, LinkedList<Demon> p, Pane panel) {
        // boolean colision=false;

        for (int i = 0; i < list_balle.size(); i++) {

            for (int j = 0; j < p.size(); j++) {

                Bounds bound_obstacle = p.get(j).getBoundsInParent();
                if (list_balle.get(i).getBoundsInParent().intersects(bound_obstacle.getMinX(), bound_obstacle.getMinY(),
                        bound_obstacle.getMinZ(), bound_obstacle.getWidth(), bound_obstacle.getHeight(),
                        bound_obstacle.getDepth())) {

                    panel.getChildren().remove(list_balle.get(i));
                    list_balle.remove(i);
                    panel.getChildren().remove(p.get(j));
                    p.remove(j);
                    return j;
					// System.out.println("reste"+p.size());

                    // list_balle.get(i).setCenterX(list_balle.get(i).getCenterX()+);
                }

            }

        }

        return 0;

    }

    public static double randomPosition(double positionMax) {
        Random random = new Random(System.nanoTime());
        double t = Math.floor(Math.random() * ((positionMax - 60) - 60 + 1)) + 60;

        return t;
    }

    public static void collisionDemon( IntegerProperty td,LinkedList<Obstacle> lo,LinkedList<Demon> demons, Pane panel, Pistolero pis) {
        Timeline tl = new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame moveBall = new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {
            int i = 0;

            public void handle(ActionEvent event) {
                for (int i = 0; i < demons.size(); i++) {
                    for (int j = i + 1; j < demons.size(); j++) {
                        
                        Bounds boundi = demons.get(i).getBoundsInParent();
                        if (demons.get(i).getBoundsInParent().intersects(demons.get(j).getBoundsInParent())
                                ) {
                            if (demons.get(i).isMale(demons.get(j))) {
                                System.out.println("je suis dans collision de male");
                                ThreadBebe b = new ThreadBebe(demons.get(i),demons.get(j));
                                    b.start();
                                    panel.getChildren().remove(demons.get(j));
                                    demons.remove(demons.get(j));
                                    System.out.println("je suis dans collision de male");
                                
                            }else if(demons.get(i).isCouple(demons.get(j))){
                                 System.out.println("je suis dans collision de couple");
                                    if(demons.size()<10){
                                    Demon d= demons.get(i).naissance(demons.get(j));
                                     ThreadNaissance tn = new ThreadNaissance(d);
                                    tn.start();
                                 td.set(td.get()+1);
                                    demons.add(d);
                                    panel.getChildren().add(d);
                                    
                                    
                                   
                                //d.move();
                                    } 
                            }

                        }
                    }
                }
            }
        }
            );
        tl.getKeyFrames ()

            .add(moveBall);
        tl.play ();
        }
        /* if (d1.getBoundsInParent().intersects(d2.getBoundsInParent())){
         System.out.println("collision fatou");
        
         d1.setIcone("/media/feu.png");
         d2.setIcone("/media/feu.png");

         if ((d1.getType ()== Sexe.male && d2.getType() == Sexe.femelle) || (d1.getType() == Sexe.femelle && d2.getType() == Sexe.male)) {
         Timeline tl = new Timeline(new KeyFrame(Duration.seconds(1), e -> naissance(this, d, ld, panel)));
         tl.play();

         } else {
         if (this.type == Sexe.male && d.type == Sexe.male) {
                
         Timeline tl = new Timeline(new KeyFrame(Duration.seconds(1), e -> saigner(this, d, ld, panel)));
         tl.play();
         //tuer un demon
         }else{
         Timeline tl = new Timeline(new KeyFrame(Duration.seconds(1), e -> retabOrdre(this, d)));
         tl.play();
                
         }
         }
         }
         }

         private void saigner(Demon d1, Demon d2, LinkedList<Demon> ld, Pane panel) {
         d1.setIcone("/media/demonMale.png");
         panel.getChildren().remove(d2);
         ld.remove(d2);

         }*/

    private static void retabOrdre(Demon d1, Demon d2) {

        d1.setIcone("/media/demonfemelle.png");

        d2.setIcone("/media/demonFemelle.png");

    }
    
     /*public  static void naissance(Demon d1, Demon d2, LinkedList<Demon> ld, Pane panel){
         Demon d = 
     
     
     Demon nouv = new Demon(d1.getCenterX(), d1.getCenterY(), d1.getVx(), d1.getVy());
     nouv.move();
     nouv.collisionDemon(d2, ld, panel);
     nouv.setIcone("/media/demonBebe.png");
     ld.add(nouv);
     panel.getChildren().add(nouv);
       
        
     }   */
public   static void  dddd( LinkedList<Obstacle> lo ,LinkedList<Demon> de ,Pane scen,Pistolero post,int nbDem){
    ThreadCreateDemon tcd = new ThreadCreateDemon(lo,de, scen,post, nbDem);
    tcd.start();
}
}
