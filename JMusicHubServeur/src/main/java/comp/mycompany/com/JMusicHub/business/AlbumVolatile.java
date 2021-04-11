package comp.mycompany.com.JMusicHub.business;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Scanner;
import org.apache.log4j.Logger;

/**
 * Classe contenant une liste des albums disponibles
 */
public class AlbumVolatile implements Serializable{
  public ArrayList<Album> Ensemble = new ArrayList<Album>();

  /**
   * Ajoute un album à la liste des albums
   */
  public void addUser(){
    final Logger logger = Logger.getLogger(AlbumVolatile.class);
    Serveur server = new Serveur();
    MutableInt mutableInt = new MutableInt(0,0);
    Scanner clavier = new Scanner(System.in);

    try{
      System.out.println("Attende de la reception du Titre, Duree, Artiste et DateSortie ");
      String Titre;
      int Duree;
      String Artiste;
      int DateSortie;

      //reception du Titre grace à la classe serveur
      Titre = server.ReceptionString();
      logger.info("Recuperation Titre");

      //Reception de la Duree
      server.ChoixUser(mutableInt);
      Duree=mutableInt.getValue();
      logger.info("Recuperation Duree");

      //Reception de l'Artiste
      Artiste = server.ReceptionString();
      logger.info("Recuperation Artiste");

      //Recetion de la DateSortie
      server.ChoixUser(mutableInt);
      DateSortie=mutableInt.getValue();
      logger.info("Recuperation Date de sortie");

      //Creation d'un album en fonction des informations recupérer du serveur
      Album nouveau = new Album(Titre,Duree,Ensemble.size(),Artiste,DateSortie);
      Ensemble.add(nouveau);

    }catch (Exception e) {
      System.out.println("Erreur dans les entrées utilisateurs");
    }

  }

  /**
   * Fonction pour ajouter une chanson à un album dont nous connaissons le numéro
   * @param AlbumNumber Numero de l'album dans l'ArrayList
   * @param Aajouter    Chanson à ajouter
   */
  public void add(int AlbumNumber,Chanson Aajouter){
    Album nouveau=Ensemble.get(AlbumNumber);
    nouveau.add(Aajouter);
  }

  /**
   * Tri un album parmis l'ArrayList des Albums
   * @param AlbumNumber Numero de l'Album à Trier
   */
  public void TriMusique(int AlbumNumber){
    (Ensemble.get(AlbumNumber)).Tri();
  }

  /**
   * Ajoute un Album dans l'ArrayList d'album
   * @param nouveau Album a ajouter
   */
  public void add(Album nouveau){
    Ensemble.add(nouveau);
  }

  /**
   * Creer un String contenant l'ensemble des chansons avec les informations sur l'album au début
   * @return Renvoi la chaine de caractere
   */
  public String toString(){
    String s="";
    for (Album Courant : Ensemble ) {
      s+=(Courant+"\n");
    }
    return s;
  }

  /**
   * Accesseur de la l'ArrayList
   * @return ArrayList d'Album
   */
  public ArrayList<Album> getEnsemble(){
    return Ensemble;
  }

  /**
   * Accesseur d'un album parmis l'ArrayList d'albums
   * @param  number Numero de l'Album à récupérer
   * @return        Renvoi l'album selectionné
   */
  public Album get(int number){
    return Ensemble.get(number);
  }

  /**
   * Trie les albums selon leurs dates de sortie
   * @return Renvoi un string contenant les albums triée et leurs chansons
   */
  public String Trie(){
    ArrayList<Album> Trier= new ArrayList<Album>(Ensemble);
    Trier.sort((p1, p2) -> Integer.valueOf(p1.getDateSortie()).compareTo(p2.getDateSortie()));
    String s="";
    for (Album album : Trier) {
      s+="\t"+album+"\n";
    }
    return s;
  }

  /**
   *  Trie chaque chanson des albums en fonctions de leurs genres
   * @return Renvoi un string contenant les albums et les chansons triée
   */
  public String TrieChanson(){
    ArrayList<Album> Trier= new ArrayList<Album>(Ensemble);
    for (Album album: Trier){
      album.Tri();
    }
    String s="";
    for (Album album : Trier) {
      s+="\t"+album+"\n";
    }
    return s;
  }

}
