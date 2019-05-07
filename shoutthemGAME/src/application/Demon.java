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
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Demon extends Circle {

    public static int radius = 40;

    DoubleProperty vx = new SimpleDoubleProperty();
    DoubleProperty vy = new SimpleDoubleProperty();
    DoubleProperty rate = new SimpleDoubleProperty();

    private Sexe type;

    public Demon(double x, double y, double vx, double vy) {
      
            rate.set(1);
            Random r = new Random(System.nanoTime());
            
            URL url;
            int rr = r.nextInt(10);
            // System.out.println("random = "+rr);
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
            
            Image cat = new Image(url.toString());
            //cat.widthProperty().s
            this.setFill(new ImagePattern(cat));
        

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

        Timeline tl = new Timeline();
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

                    }
                });
        tl.getKeyFrames().add(moveBall);
        tl.play();

    }

    public Sexe getType() {
        return type;
    }

    public static double randomDemon(Pane pane) {
        Random random = new Random(System.nanoTime());
       return random.nextDouble() * (pane.getPrefWidth() - Demon.radius * 2);
    }

    public void collisionDemon(Demon d, LinkedList<Demon> ld, Pane panel) {
        if(this.intersects(d.getBoundsInLocal())){
            System.out.println("je suis fatou");
        
        this.setIcone("/media/feu.png");
        d.setIcone("/media/feu.png");

        if ((this.type == Sexe.male && d.type == Sexe.femelle) || (this.type == Sexe.femelle && d.type == Sexe.male)) {
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

    }
    private void retabOrdre(Demon d1, Demon d2){
        
            d1.setIcone("/media/demonfemelle.png");
       
             d2.setIcone("/media/demonFemelle.png");
        
    }
    private void naissance(Demon d1, Demon d2, LinkedList<Demon> ld, Pane panel){
         String s1 = d1.getType()==Sexe.male ? "/media/demonMale.png":"/media/demonFemelle.png";
        String s2 = d2.getType()==Sexe.male ? "/media/demonMale.png":"/media/demonFemelle.png";
        System.out.println(s1+" s2: "+s2);
        d1.setIcone(s1);
        d2.setIcone(s2);
        Demon nouv = new Demon(d1.getCenterX(), d1.getCenterY(), d1.getVx(), d1.getVy());
        nouv.move();
        nouv.collisionDemon(d2, ld, panel);
        nouv.setIcone("/media/demonBebe.png");
        ld.add(nouv);
        panel.getChildren().add(nouv);
       
        
       
        
    }
    

    public void setIcone(String chemin) {
        URL url = getClass().getResource(chemin);
        Image cat = new Image(url.toString());
        this.setFill(new ImagePattern(cat));
    }
}
