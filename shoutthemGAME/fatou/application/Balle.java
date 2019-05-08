package application;

import java.util.Random;

import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;

public class Balle extends Circle {

    private int vitessey = 5;
    private int vitessex = 0;
    private double x;

    private double y;

    public Balle(double xx, double yy) {
        this.setRadius(5);
        x = xx;
        y = yy;
        this.setTranslateX(x);
        this.setTranslateY(y);

    }

    public int getVitessey() {
        return vitessey;
    }

    public void setVitessey(int vitesse) {
        this.vitessey = vitesse;
    }

    public int getVitessex() {
        return vitessex;
    }

    public void setVitessex(int vitesse) {
        this.vitessex = vitesse;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean moveballe() {
		//setCenterX(getCenterX()-vitessex);
        //setCenterY(getVitessey()-vitessey);
        y -= vitessey;
        x -= vitessex;
        this.setTranslateY(y);
        this.setTranslateX(x);
		//System.out.println("ffff"+getCenterY() );

        if (y - getRadius() < 0 || y + getRadius() > ((Region) getParent()).getHeight()) {
			//setVitessey(-getVitessey());
            //setVitessex(randomVitesse()*5);

            return true;

        }
        if (x - getRadius() < 0 || x + getRadius() > ((Region) getParent()).getWidth()) {
			//setVitessey(randomVitesse()*5);
            //setVitessex(-getVitessex());
            return true;
        }
        return false;

    }

    int randomVitesse() {
        Random random = new Random(System.nanoTime());
        if (random.nextInt(2) == 1) {
            return -1;
        } else {
            return 1;
        }
    }

}
