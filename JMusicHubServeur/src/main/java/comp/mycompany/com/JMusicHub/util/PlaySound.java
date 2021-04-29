package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

import java.io.*;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.IOException;

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
 * Classe permettant d'envoyer toutes les musiques à jouer sur le client
 */
public class PlaySound {
  private File soundFile;
  final Logger logger = Logger.getLogger(PlaySound.class);

  /**
   * Envoi toutes les musiques d'une playlist un par un
   * @param PlaylistJouer Playlist à envoyer au client
   */
  public void LecturePlaylist(StockageVolatile PlaylistJouer){
    Serveur serveur = new Serveur();
    for (Stockage Ajouer : PlaylistJouer.getEnsemble()) {
      System.out.println("Lecture de "+Ajouer);
      try{
        serveur.sendFile("Data/"+Ajouer.getType()+"/"+Ajouer.getContenu());
        logger.info("Envoi réussi du fichier"+"Data/"+Ajouer.getType()+"/"+Ajouer.getContenu());
      }catch (Exception e) {
        logger.error("Echec envoi de "+"Data/"+Ajouer.getType()+"/"+Ajouer.getContenu(),e);
      }
    }
  }

  /**
   * Envoi toutes les musiques d'une playlist un par un
   * @param AlbumJouer Album à envoyer au client
   */
  public void LectureAlbum(StockageVolatile AlbumJouer){
    Serveur serveur = new Serveur();
    System.out.println(AlbumJouer.getEnsemble());
    for (Stockage Ajouer : AlbumJouer.getEnsemble()) {
      System.out.println(Ajouer);
      System.out.println("Lecture du fichier "+Ajouer.getContenu());
      try{
        serveur.sendFile("Data/"+Ajouer.getType()+"/"+Ajouer.getContenu());
        logger.info("Envoi réussi du fichier"+"Data/"+Ajouer.getContenu());
      }catch (Exception e) {
        logger.error("Echec envoi de "+"Data/"+Ajouer.getType()+"/"+Ajouer.getContenu(),e);
      }
    }
  }
}
