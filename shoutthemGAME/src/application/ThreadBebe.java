/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package application;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Pane;

/**
 *
 * @author netbook
 */
public class ThreadBebe extends Thread {
   LinkedList<Demon> demons ;
   Pane pane;
   Demon d1;
   Demon d2;
   
   public ThreadBebe(Demon d1, Demon d2){
       this.d1=d1 ;
       this.d2=d2;
   }
   public void run(){
       double vx = d1.getVx();
      double vy = d2.getVy();
       
       try {
           System.out.println("je suis fffff");
           
         /* d1.setRate(0);
          d2.setRate(0);
           d1.setIcone("/media/feu.png");
           d2.setIcone("/media/feu.png");*/
           sleep(100);
           /*d1.setIcone("/media/demonFemelle.png");
           d2.setIcone("/media/demonFemelle.png");
           d1.setRate(1);
          d2.setRate(1);
        d1.setVx(vx);
        d2.setVy(vy);*/
        
       } catch (InterruptedException ex) {
           Logger.getLogger(ThreadBebe.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       
   }
    
}
