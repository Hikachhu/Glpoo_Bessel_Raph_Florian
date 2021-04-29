package comp.mycompany.com.JMusicHub.business;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class AlbumVolatile extends StockageMaster implements Serializable{
   // public ArrayList<StockageVolatile> Ensemble = new ArrayList<StockageVolatile>();

  /**
   * Ajout d'un Album dans la list des albums
   */
   public void addUser(){
     final Logger logger = Logger.getLogger(AlbumVolatile.class);
     Serveur server = new Serveur();
     MutableInt mutableInt = MutableInt.getInstance();
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

    public void TriMusique(int AlbumNumber){
      (Ensemble.get(AlbumNumber)).Tri(0);
    }

    public String Tri(int Choix){
      ArrayList<StockageVolatile> init= new ArrayList<StockageVolatile>(Ensemble);
      String s="";
      switch(Choix){
        case 0:
          ArrayList<Album> Trier= new ArrayList<Album>();
          for (StockageVolatile Actuel :init ) {
            Trier.add((Album)Actuel);
          }
          Trier.sort((p1, p2) -> Integer.valueOf(p1.getDateSortie()).compareTo(p2.getDateSortie()));
          for (Album album : Trier) {
            s+="\t"+album+"\n";
          }
          return s;
        case 1:
          for (StockageVolatile album: init){
            ((Album)album).TriAlbum();
          }
          for (StockageVolatile album : init) {
            s+="\t"+album+"\n";
          }
          return s;
        default:
          return "ERROR";
        }

    }

    public String toString(){
      String s="";
      for (StockageVolatile Courant : Ensemble ) {
        s+=(Courant+"\n");
      }
      return s;
    }

    /**
     *  Trie chaque chanson des albums en fonctions de leurs genres
     * @return Renvoi un string contenant les albums et les chansons triée
     */
}
