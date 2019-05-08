/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

/**
 * 
 * 
 *
 * @author netbook
 */
import java.net.URL;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Demon extends Circle {

    public static int radius = 40;

    DoubleProperty vx = new SimpleDoubleProperty();
    DoubleProperty vy = new SimpleDoubleProperty();
    DoubleProperty rate = new SimpleDoubleProperty();
   public boolean bebe;
   ImageView image;
    private Sexe type;

    public Demon(double x, double y, double vx, double vy) {
      
            rate.set(1);
            Random r = new Random(System.nanoTime());
            bebe = false;
            URL url;
            int rr = r.nextInt(10);
            this.setStroke(Color.BLUE);
            // System.out.println("random = "+rr);
           
            this.type = Sexe.femelle;
           if (rr % 2 == 0) {
                this.type = Sexe.femelle;
                url = getClass().getResource("/media/demonFemelle.png");
            } else {
                this.type = Sexe.male;
                url = getClass().getResource("/media/demonMale.png");
            }
            this.vx.set(vx);
            this.vy.set(vy);
            
            
            this.setRadius(radius);
            
            this.setCenterX(x);
            this.setCenterY(x);
            
            Image cat = new Image(url.toString(),false);
            //cat.widthProperty().s
            this.setFill(new ImagePattern(cat));
           
            
            

    }
    
    public Demon(double x, double y, double vx, double vy, Sexe s) {
      
            rate.set(1);
            Random r = new Random(System.nanoTime());
            bebe = true;
            URL url;
            int rr = r.nextInt(10);
            this.setStroke(Color.BLUE);
            // System.out.println("random = "+rr);
           
            this.type = s;
           if (this.type ==Sexe.femelle) {
               
                url = getClass().getResource("/media/demonFemelle.png");
            } else if (this.type ==Sexe.male) {
               
                url = getClass().getResource("/media/demonMale.png");
            }else{
               
                url = getClass().getResource("/media/demonBebe.png");
            }
           
            this.vx.set(vx);
            this.vy.set(vy);
            
            
            this.setRadius(radius);
            
            this.setCenterX(x);
            this.setCenterY(x);
            
            Image cat = new Image(url.toString(),false);
            //cat.widthProperty().s
            this.setFill(new ImagePattern(cat));
           
            
            

    }
    public Sexe getType(){
        return type;
    }
    public void setType(Sexe s){
        this.type= s;
    }
    public DoubleProperty vxProperty() {
        return vx;
    }

    public DoubleProperty vyProperty() {
        return vy;
    }

    public DoubleProperty rateProperty() {
        return rate;
    }

    public Double getVx() {
        return vx.getValue();
    }

    public Double getVy() {
        return vy.getValue();
    }

    public Double getRate() {
        return rate.getValue();
    }

    public void setVx(double vitesse) {
        this.vx.set(vitesse);
    }

    public void setVy(double v) {
        this.vy.set(v);
    }

    public void setRate(double v) {
        this.rate.set(v);
    }
    /* la méthode move() depace la particule */

    public void move() {

      /* Timeline tl = new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame moveBall = new KeyFrame(Duration.seconds(0.1),
                new EventHandler<ActionEvent>() {

                    public void handle(ActionEvent event) {
                        
                        /* delta -- la valeur absolue de deplacement */
                        double delta = 2.7 * getRate();
                        /* deplacer le centre de la particule */
                        setCenterX(getCenterX() + getVx() * delta);
                        setCenterY(getCenterY() + getVy() * delta);

                        /* detecter les collision avec le bord
                         * et si collision modifier le vecteur de la vitesse */
                        if (getCenterX() - getRadius() < 0
                        && getVx() < 0) {
                            setVx(-getVx());
                        }
                        if (getCenterY() - getRadius() < 0
                        && getVy() < 0) {
                            setVy(-getVy());
                        }
                        if (getCenterX() + getRadius() > ((Region) getParent()).getWidth()
                        && getVx() > 0) {
                            setCenterX(((Region) getParent()).getWidth() - getRadius());
                            setVx(-getVx());
                        }
                        if (getCenterY() + getRadius() > ((Region) getParent()).getHeight()
                        && getVy() > 0) {
                            setCenterY(((Region) getParent()).getHeight() - getRadius());
                            setVy(-getVy());
                        }

                  /*  }
               });
        tl.getKeyFrames().add(moveBall);
        tl.play();*/

    }


    public static double randomDemon(Pane pane) {
        Random random = new Random(System.nanoTime());
       return random.nextDouble() * (pane.getPrefWidth() - Demon.radius * 2);
    }

    
    

    public void setIcone(String chemin) {
        URL url = getClass().getResource(chemin);
        Image cat = new Image(url.toString());
        this.setFill(new ImagePattern(cat));
    }
    
    
 public static Demon  createDemon(LinkedList<Obstacle> liste_obstacle ,LinkedList<Demon> demons , Pistolero postelero ,Pane sceneGame) {

        double px, py, vx, vy;

        Random random;
        Demon d=null;
        boolean creation = true;
         do{
            random = new Random(System.nanoTime());
            px = random.nextDouble() * (sceneGame.getPrefWidth() - Demon.radius * 2) + Demon.radius;
            py = random.nextDouble() * (sceneGame.getPrefHeight() - Demon.radius * 2) + Demon.radius;
            vx = 2 * (random.nextDouble() - 0.5);
            vy = 2 * (random.nextDouble() - 0.5);
            System.out.println("px " + px + " py " + py + " vx" + vx + " vy " + vy);
            Demon demon = new Demon(px, py, vx, vy);
            
            if (!demon.getBoundsInParent().intersects(postelero.getBoundsInParent())) {
                System.out.println(" pas obstacle pistelero");
                for (int j = 0; j < liste_obstacle.size(); j++) {
                    if (liste_obstacle.get(j).getBoundsInParent().intersects(demon.getBoundsInParent())) {
                        j = liste_obstacle.size();
                        System.out.println("change" + creation );
                        creation = false;
                    } else {
                        // System.out.println("intersection dobstacle");
                        creation = true;
                    }

                }//fin collision obstacle
                /*if(creation){
                    //collision demon
                    for (int j = 0; j < demons.size(); j++) {
                        if (demons.get(j).getBoundsInParent().intersects(demon.getBoundsInParent())) {
                            j = demons.size();
                             System.out.println("collision demon");
                            creation = false;
                        } else {
                            // System.out.println("intersection dobstacle");
                            creation = true;
                        }
                    
                    }
                }*/

            } else {
              //collision ave cpistolero
                // System.out.println("change pistolero"+creation+i);
                creation = false;
            }

            if (creation) {
                /*demons.add(demon);
                sceneGame.getChildren().add(demon);*/
                d= demon;

            }

        }while (d==null);
         //d.move();
         return d;
    }
 public boolean isMale( Demon d2){
     return (this.type ==Sexe.male && d2.type ==Sexe.male);
 }
public boolean  isCouple(Demon d){
    return this.type.equals(d.type);
           
}
public boolean isFemlelle( Demon d2){
     return (this.type ==Sexe.femelle && d2.type ==Sexe.femelle);
 }
public Demon naissance(Demon d){
    Demon d2= new Demon(d.getBoundsInParent().getMinX(),
            d.getBoundsInParent().getMinY(), (d.getVx()-this.getVx()), (d.getVy()-this.getVy()), Sexe.bebe);
    return d2;
}
}
