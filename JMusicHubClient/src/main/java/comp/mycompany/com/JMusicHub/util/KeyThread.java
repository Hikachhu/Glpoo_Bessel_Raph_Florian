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



class KeyThread implements Runnable {
  final static Logger logger = Logger.getLogger(KeyThread.class);
   private Thread t;
   Scanner scanner = new Scanner(System.in);
   MutableInt ReadBytes;

   KeyThread(MutableInt ReadBytes) {
      this.ReadBytes=ReadBytes;
      System.out.println("Creating thread");
   }

   public void run() {
     try{
        System.out.println("Running "  );
        int c;
        do{
          c=scanner.next().charAt(0);;

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
        ReadBytes.setValue(-2);
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
      System.out.println("Starting");
      logger.info("Debut Thread");
      if (t == null) {
         t = new Thread (this);
         t.start ();
      }
   }


    public void interrupt() {
    }

}
