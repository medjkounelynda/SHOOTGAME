/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package application;

import static java.lang.Thread.sleep;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Pane;

/**
 *
 * @author netbook
 */
public class ThreadNaissance extends Thread{
 Demon demon;

/**
 *
 * @author netbook
 */

   
   public ThreadNaissance(Demon d1){
      demon=d1;
   }
   public void run(){
       
       try {
           System.out.println("je suis fffff");
           
         
           sleep(1000);
             Random r = new Random(System.nanoTime());
             int d= r.nextInt();
             if(d%2!=0){
                 demon.setType(Sexe.femelle);
                 demon.setIcone("/media/demonFemelle.png");
             }else{
                 demon.setType(Sexe.male);
                 demon.setIcone("/media/demonMale.png");
            }
        
       } catch (InterruptedException ex) {
           Logger.getLogger(ThreadBebe.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       
   
    
   }

    
}
