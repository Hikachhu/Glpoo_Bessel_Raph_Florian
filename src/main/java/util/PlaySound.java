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


public class PlaySound {
  private final int BUFFER_SIZE = 115384;
  private File soundFile;
  private AudioInputStream audioStream;
  private AudioFormat audioFormat;
  private SourceDataLine sourceLine;
  private MutableInt ReadBytes;

  public void Lecture(String filename){
    int c;
    Scanner clavier = new Scanner(System.in);
    int PositionActuel=0;
    int PositionActuel2=0;
    long longueur;
    long actuel;
    String strFilename = "Data/"+filename;
    System.out.println(Instant.now() );
      try {
          soundFile = new File(strFilename);
      } catch (Exception e) {
          e.printStackTrace();
          System.exit(1);
      }

      try {
          audioStream = AudioSystem.getAudioInputStream(soundFile);
      } catch (Exception e){
          e.printStackTrace();
          System.exit(1);
      }


      audioFormat = audioStream.getFormat();

      DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
      try {
          sourceLine = (SourceDataLine) AudioSystem.getLine(info);
          sourceLine.open(audioFormat);
      } catch (LineUnavailableException e) {
          e.printStackTrace();
          System.exit(1);
      } catch (Exception e) {
          e.printStackTrace();
          System.exit(1);
      }

      sourceLine.start();
      int nBytesRead = 0;
      int tour=1;

      int frameSize=audioFormat.getFrameSize();
      float frameRate=audioFormat.getSampleRate();
      long totalFrames = audioStream.getFrameLength();

      double totalSeconds = (double) totalFrames / audioFormat.getSampleRate();

      System.out.println(totalSeconds);
      System.out.println("frameSize="+frameSize+" totalFrames="+totalFrames+" sampleRate="+frameRate);

      int pourcentage1=0,pourcentage2=0;

      byte[] abData = new byte[frameSize];
      ReadBytes=new MutableInt(1,frameSize);
      KeyThread R1 = new KeyThread(ReadBytes);
      audioStream.mark(frameSize);
      R1.start();
      System.out.println((double)totalFrames/(double)frameRate);
      int tailleLecture=frameSize;
      audioStream.mark((int)totalFrames*4);
      for(tour=1;tour<totalFrames;) {


        if(ReadBytes.getValue()==0){
          tailleLecture=0;
        }else if(ReadBytes.getValue()==1){
          tailleLecture=ReadBytes.getMax();
        }else if(ReadBytes.getValue()==2){
          try{
            audioStream.skip((int)(frameRate*30*ReadBytes.getMax()));
            tour+=(30*nBytesRead/frameSize);
          }catch (Exception e) {
            System.out.println(e.getMessage());
          }
            ReadBytes.setValue(1);
            System.out.println("Sortie");
        }else if(ReadBytes.getValue()==3){
            System.out.println("Recule de 30secs");
            System.out.println(audioStream.markSupported());
            try{
              audioStream.reset();
              audioStream.mark((int)totalFrames*4);
              audioStream.skip((int)(tour-(nBytesRead/frameSize)*30));
              tour-=(int)(nBytesRead/frameSize)*30;
            }catch (Exception e) {
              System.out.println(e.getMessage());
            }
            ReadBytes.setValue(1);
        }else if(ReadBytes.getValue()==4){
          tour=(int)totalFrames;
        }
        try {
            nBytesRead = audioStream.read(abData, 0, tailleLecture);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (nBytesRead >= 0) {
            @SuppressWarnings("unused")
            int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
        }
        tour+=nBytesRead/frameSize;
        pourcentage1=(int)((float)tour/(float)totalFrames*100);
        if(pourcentage1!=pourcentage2){
          pourcentage2=(int)((float)tour/(float)totalFrames*100);
          System.out.println(pourcentage1+	" actual "+tour+" total "+totalFrames);
        }
      }

      sourceLine.drain();
      sourceLine.close();


  //    ProgressBar pb = new ProgressBar("Test", (int)audioClip.getMicrosecondLength());
/*
      do {
        longueur=audioClip.getMicrosecondLength();
        actuel=audioClip.getMicrosecondPosition();
        PositionActuel=(int)audioClip.getMicrosecondPosition() / 1000000;

        pb.setExtraMessage("Downloading...");

        if(PositionActuel!=PositionActuel2){
          PositionActuel2=(int)audioClip.getMicrosecondPosition() / 1000000;
          pb.stepTo((int)audioClip.getMicrosecondPosition());
        }
        if(audioClip.getMicrosecondLength()<audioClip.getMicrosecondPosition()){
          System.out.println("Depasse");
        }
      } while (longueur>actuel);

      audioClip.close();
      */
      System.out.println("Fini "+(Instant.now() ));
  //    R1.interrupt();


  }

  public void LecturePlaylist(Playlist PlaylistJouer){
    for (Stockage Ajouer : PlaylistJouer.getEnsemble()) {
      Lecture(Ajouer.getContenu());
    }
  }

  public void LectureAlbum(Album AlbumJouer){
    for (Stockage Ajouer : AlbumJouer.getEnsemble()) {
      Lecture(Ajouer.getContenu());
    }
  }
}
