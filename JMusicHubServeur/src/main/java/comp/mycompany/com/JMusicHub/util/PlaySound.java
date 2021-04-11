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


public class PlaySound {
  private final int BUFFER_SIZE = 115384;
  private File soundFile;
  private AudioInputStream audioStream;
  private AudioFormat audioFormat;
  private SourceDataLine sourceLine;
  private MutableInt ReadBytes;
  final static Logger logger = Logger.getLogger(PlaySound.class);
  

  public void LecturePlaylist(Playlist PlaylistJouer){
    Serveur serveur = new Serveur();

    for (Stockage Ajouer : PlaylistJouer.getEnsemble()) {
      System.out.println("Lecture de "+Ajouer);
      try{
        serveur.sendFile("Data/"+Ajouer.getContenu());
      }catch (Exception e) {

      }
    }
  }

  public void LectureAlbum(Album AlbumJouer){
    Serveur serveur = new Serveur();
    System.out.println(AlbumJouer.getEnsemble());
    for (Stockage Ajouer : AlbumJouer.getEnsemble()) {
      System.out.println(Ajouer);
      System.out.println("Lecture du fichier "+Ajouer.getContenu());
      try{
        serveur.sendFile("Data/"+Ajouer.getContenu());
        System.out.println("Fini "+Ajouer.getContenu());
      }catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
