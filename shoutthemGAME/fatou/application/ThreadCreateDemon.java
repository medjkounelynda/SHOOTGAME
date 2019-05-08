/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package application;

import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Pane;

/**
 *
 * @author netbook
 */
public class ThreadCreateDemon  extends Thread{
    LinkedList<Obstacle> liste_obstacle ;
    LinkedList<Demon> demons ;
    Pane sceneGame;
    Pistolero postelero;
    int nbDemon;
    public ThreadCreateDemon(LinkedList<Obstacle> lo ,LinkedList<Demon> de ,Pane scen,Pistolero post,int nbDem){
        this.liste_obstacle=lo;
        this.demons = de;
        this.postelero= post;
        this.nbDemon=nbDem;
        sceneGame = scen;
        
    }
    
public void run(){
    double px, py,vx,vy;
    Random random;
    for (int i = 0; i < nbDemon; i++) {
        
        random = new Random(System.nanoTime());
           // Demon d = Demon.createDemon(liste_obstacle, demons, postelero, sceneGame);
        px =  (sceneGame.getPrefWidth() - Demon.radius * 2) + Demon.radius;
            py = (sceneGame.getPrefHeight() - Demon.radius * 2) + Demon.radius;
            vx = 8 * (random.nextDouble() - 0.5);
            vy = 8 * (random.nextDouble() - 0.5);
        Demon d= new Demon(px, py,vx,vy);
            demons.add(d);
            sceneGame.getChildren().add(d);
            d.move();
             System.out.println("px " + px + " py " + py + " vx" + vx + " vy " + vy);
        try {
            sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadCreateDemon.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
}
    
}
