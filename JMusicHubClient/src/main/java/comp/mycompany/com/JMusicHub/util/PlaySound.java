package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

import java.io.*;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;
import java.time.Instant;
import org.w3c.dom.*;
import java.io.IOException;
import java.io.File;
import java.util.UUID;

import java.util.Scanner;
import me.tongfei.progressbar.*;
import org.apache.log4j.Logger;

/**
 * Classe permettant de lire les fichiers audios
 */
public class PlaySound implements InterfaceAudio {
  private final int BUFFER_SIZE = 115384;
  private File soundFile;
  private AudioInputStream audioStream;
  private AudioFormat audioFormat;
  private SourceDataLine sourceLine;
  private Mutable ReadBytes;
  final static Logger logger = Logger.getLogger(PlaySound.class);

  /**
   * Lis un fichier dont la localisation est passée en parametre
   * @param filename Position d'un fichier
   */
  public void Lecture(String strFilename) {
     InterfaceClient client  = new Client();
    Scanner clavier = new Scanner(System.in);
    int c,PositionActuel=0,PositionActuel2=0;
    long longueur,actuel;
    logger.info("Debut de la lecture de "+strFilename+" à "+Instant.now() );
    /**
     * Ouverture des flux afin de manipuler les bytes d'un fichier
     */
      try {
          soundFile= new File(strFilename);
          logger.info("Recupération fichier : "+strFilename);
      } catch (Exception e) {
          e.printStackTrace();
          logger.error("Impossible de récupérer le fichier distant: "+strFilename);
          System.exit(1);
      }

      try {
          audioStream = AudioSystem.getAudioInputStream(soundFile);
          logger.info("Ouverture audioStream de "+soundFile);
      }catch(Exception e){
          logger.error("Impossible d'ouvrir l'audioStream de "+strFilename);
          e.printStackTrace();
          System.exit(1);
      }


      audioFormat = audioStream.getFormat();
      DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
      try {
          sourceLine = (SourceDataLine) AudioSystem.getLine(info);
          sourceLine.open(audioFormat);
          logger.info("Ouverture de la sourceLine de "+strFilename);
      } catch (LineUnavailableException e) {
          e.printStackTrace();
          logger.error("Impossible d'ouvrir le sourceLine de  "+strFilename,e);
          System.exit(1);
      } catch (Exception e) {
          e.printStackTrace();
          logger.error("Impossible d'ouvrir le sourceLine de  "+strFilename,e);
          System.exit(1);
      }

      sourceLine.start();
      int nBytesRead = 0;
      int tour=1;
      int frameSize=audioFormat.getFrameSize();
      float frameRate=audioFormat.getSampleRate();
      long totalFrames = audioStream.getFrameLength();

      double totalSeconds = (double) totalFrames / audioFormat.getSampleRate();

      logger.info("frameSize="+frameSize+" totalFrames="+totalFrames+" sampleRate="+frameRate+ " totalSeconds= "+totalSeconds);

      int pourcentage1=0,pourcentage2=0;
      //Tableau servant à lire les informations depuis le fichier
      byte[] abData = new byte[frameSize];
      ReadBytes=MutableInt.getInstance();

      //Initalisation du Tread et démarrage de celui-ci
      KeyThread R1 = new KeyThread(ReadBytes);
      R1.start();

      //Positionnement du marquage
      audioStream.mark(frameSize);

      //Lecture de
      int tailleLecture=frameSize;
      audioStream.mark((int)totalFrames*4);
      logger.info("Debut musique");
      double s=0.0;
      int lu=0;
      ReadBytes.setValue(1);
      ReadBytes.setMax((int)frameSize);
      /**
       * Boncle de lecture principale de la musique
       * @param tour=1;tour<totalFrames; Tant que nous ne sommes pas à la fin de la musique, nous comptons en frame
       */
      for(tour=1;tour<totalFrames;) {
        /**
         * Les différents if permettent de modifier l'interaction avec la musique d'après l'objet ReadBytes
         */
        //Mise en pause de la musique et nous restons bloquer dans la boucle tant qu'il n'y a pas eu la valeur 1 sur l'objet mutable
        if(ReadBytes.getValue()==0){
          tailleLecture=0;
          logger.info("Mise en pause musique");
          ReadBytes.setValue(-1);
          while(lu!=1){
            try{
              Thread.sleep(10);
            }catch (Exception e) {
              System.out.println(e.getMessage());
            }
            lu=ReadBytes.getValue();
          }
          lu=0;
          logger.info("Reprise musique");
          //Reprise de la musique
        }else if(ReadBytes.getValue()==1){
          tailleLecture=ReadBytes.getMax();
          ReadBytes.setValue(-1);
          //Acceleration de 30 secs à l'aide d'un skip
        }else if(ReadBytes.getValue()==2){
          try{
            audioStream.skip((int)(frameRate*30*ReadBytes.getMax()));
            tour+=(30*frameRate);
            logger.info("Acceleration de 30secs de la musique");
            s+=30*frameRate;
          }catch (Exception e) {
            System.out.println(e.getMessage());
          }
            ReadBytes.setValue(1);
            //Recule de 30 sec en revenant en arrière puis skiper jusqua 30 secs avant la position actuel
        }else if(ReadBytes.getValue()==3){
            logger.info("Recule de 30secs de la musique/non realisé");
            System.out.println(audioStream.markSupported());
            try{
              audioStream.reset();
              audioStream.mark((int)totalFrames*4);
              tour-=(30*frameRate);
              audioStream.skip((int)(s*frameSize-frameRate*30*frameSize));
              System.out.println("s ="+s+" decalage ="+(s*frameSize-frameRate*30*frameSize)+"    ");
              s=(s-30*frameRate);

              System.out.println(tour);
            }catch (Exception e) {
              System.out.println(e.getMessage());
            }
            ReadBytes.setValue(1);
            //Arret de de la lecture en se positionnant à la fin des frames
        }else if(ReadBytes.getValue()==4){
          tour=(int)totalFrames;
        }
        //Lecture d'un paquet de bytes sur le flux du fichier audio
        try {
            nBytesRead = audioStream.read(abData, 0, tailleLecture);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Ecriture de ce flux dans la sortie audio
        if (nBytesRead >= 0) {
            @SuppressWarnings("unused")
            int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
        }
        //Incrémentation des valeur notamment le pourcentage d'avancé de la musique
        tour+=nBytesRead/frameSize;
        pourcentage1=(int)((float)tour/(float)totalFrames*100);
        if(pourcentage1!=pourcentage2){
          pourcentage2=(int)((float)tour/(float)totalFrames*100);
        }

        //Chaque seconde nous affichons l'évolution du % d'avancé dans l'écoute audio
        if(s%frameRate==0){
          System.out.print(pourcentage1+"% joué | avancée actuelle = "+(int)(s/frameRate)+"s\r");
          logger.info(pourcentage1+"% joué | avancée actuelle = "+(int)(s/frameRate)+"s");
        }
        s+=1;
      }
      logger.info("Fin musique "+strFilename);
      sourceLine.drain();
      sourceLine.close();
      try {
        audioStream.close();
      } catch (Exception e) {
          logger.error("Close audiostream "+ e.getMessage());
      }
      System.gc();
      if(soundFile.delete()){
          System.out.println("File deleted successfully");
      }else{
          System.out.println("Failed to delete the file");
      }


      logger.info("Fini "+(Instant.now() ));
      System.out.println("Appuyez sur q pour quitter le programme\nm pour revenir au menu principale ou lire l'audio suivant");
      while(lu!=-2){
        lu=ReadBytes.getValue();
        try{
          Thread.sleep(10);
        }catch (Exception e) {
          System.out.println(e.getMessage());

        }
      }
  }

  /**
   * Permet de lire un ensemble de musique stockée dans une liste
   * @param PlaylistJouer [description]
   * @param tempDir       [description]
   */
  public void LectureListe(StockageVolatile PlaylistJouer,String tempDir){
     InterfaceClient client  = new Client();
    //Pour chaque element d'une liste de valeur
    for (Stockage Ajouer : PlaylistJouer.getEnsemble()){
      System.out.println("Lecture de "+Ajouer);
      try{
        client.receiveFile(tempDir, Ajouer.getContenu());
      }catch (Exception e) {
        logger.error("Echec dans la reception d'un fichier");
      }
      //Lecture d'un fichier
      Lecture(tempDir+Ajouer.getContenu());
    }
  }
}
