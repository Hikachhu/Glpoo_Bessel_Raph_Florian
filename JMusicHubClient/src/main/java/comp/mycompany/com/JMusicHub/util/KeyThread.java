package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.Robot;
import org.apache.log4j.Logger;
import java.util.Scanner;


/**
 * Thread utiliser pour modifier la valeur d'une classe qui sera mise à jour par des entrées utilisateurs
 */
class KeyThread implements Runnable {
   final static Logger logger = Logger.getLogger(KeyThread.class);
   private Thread t;
   Scanner scanner = new Scanner(System.in);
   Mutable ReadBytes;

   KeyThread(Mutable ReadBytes) {
     /**
      * Synchronisation de la classe permettant de faire transmettre les informations entre le Thread et la fonction appellante
      */
      this.ReadBytes=ReadBytes;
      System.out.println("Creation du thread");
   }

   /**
    * Execution du Thread
    */
   public void run() {
     try{
        System.out.println("Lecture des entrées utilisateurs activé");
        System.out.println("Commandes disponibles:\np pour mettre en pause\nr pour reprendre la musique\ne pour accélérer de 30 secs\nz pour reculer de 30 secs\nq pour quitter");
        int c;
        do{
          c=scanner.next().charAt(0);
          /**
           * Conversion de l'entrée utilisateur en une valeur placé dans la transmission avec la fonction appellante
           * @param c Entrée utilisateur
           */
          switch (c) {
            case 'p':
              System.out.println("Pause");
              ReadBytes.setValue(0);
              break;
            case 'r':
              System.out.println("Reprise");
              ReadBytes.setValue(1);
              break;
            case 'e':
              System.out.println("Avancé de 30secs");
              ReadBytes.setValue(2);
              break;
            case 'z':
              System.out.println("Recule de 30secs");
              ReadBytes.setValue(3);
              break;
            case 'q':
              ReadBytes.setValue(4);
              break;
          }
        }while(c!='q'&&c!='m');
        /**
         * Positionnement de la variable mutable pour ne pas interférer avec le reste du programme
         */
        ReadBytes.setValue(-2);
        //Arret du thread
        t.interrupt();
        if(c=='q'){
          logger.info("Sortie programme apres ecoute de musique");
          System.out.println("Sortie du programme au revoir");
          System.exit(0);
        }

        logger.info("Retour menu apres écoute musique");
      }catch (Exception e) {
        logger.error("Error in thread : ",e);
      }
   }

   public void start () {
      logger.info("Debut Thread");
      if (t == null) {
         t = new Thread (this);
         t.start ();
      }
   }
}
