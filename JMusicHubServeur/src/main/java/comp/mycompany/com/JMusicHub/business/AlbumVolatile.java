package comp.mycompany.com.JMusicHub.business;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import java.io.Serializable;

import java.util.ArrayList;
import org.apache.log4j.Logger;

public class AlbumVolatile implements StockageMaster, Serializable{

  public ArrayList<StockageVolatile> Ensemble = new ArrayList<StockageVolatile>();

  /**
  * Ajout d'une liste dans la liste des listes
  * @param nouveau [description]
  */
  public void add(StockageVolatile nouveau){
  Ensemble.add(nouveau);
  }

  /**
   * Suppression d'une liste dans la liste disponibles
   */
   public void Suppression(){
    final Logger logger = Logger.getLogger(PlaylistVolatile.class);
    int Number;
    Serveur serveur = new Serveur();
    MutableInt ChoixClient = MutableInt.getInstance();
    try{
      serveur.ChoixUser(ChoixClient);
      logger.info("Reception du choix client pour la suppression d'une playlist");
    }catch (Exception e) {
      logger.error("Erreur dans la reception d'un choix client pour supprimer la playlist",e);
    }
    Number=ChoixClient.getValue();
    Ensemble.remove(Number);
  }

  /**
 * Ajout d'un element dans une des listes
 * @param ListeNumber Numero de la liste dans laquelle ajouter un element
   * @param Aajouter    Element à ajouter
   */
  public void add(int ListeNumber,Stockage Aajouter){
   StockageVolatile nouveau=Ensemble.get(ListeNumber);
   nouveau.add(Aajouter);
  }

  /**
   * Ajout d'un Album dans la list des albums
   */
   public void addUser(StockageVolatile ...Utile){
     final Logger logger = Logger.getLogger(AlbumVolatile.class);
     Serveur server = new Serveur();
     MutableInt mutableInt = MutableInt.getInstance();

     try{
       System.out.println("Attende de la reception du Titre, Duree, Artiste et DateSortie du client ");
       String Titre;
       int Duree;
       String Artiste;
       int DateSortie;

       //reception du Titre grace à la classe serveur
       Titre = server.ReceptionString();
       logger.info("Recuperation Titre "+Titre);

       //Reception de la Duree
       server.ChoixUser(mutableInt);
       Duree=mutableInt.getValue();
       logger.info("Recuperation Duree "+Duree);

       //Reception de l'Artiste
       Artiste = server.ReceptionString();
       logger.info("Recuperation Artiste "+Artiste);

       //Recetion de la DateSortie
       server.ChoixUser(mutableInt);
       DateSortie=mutableInt.getValue();
       logger.info("Recuperation Date de sortie "+DateSortie);

       //Creation d'un album en fonction des informations recupérer du serveur
       Album nouveau = new Album(Titre,Duree,Ensemble.size(),Artiste,DateSortie);
       logger.info("Creation de l'album "+nouveau);
       Ensemble.add(nouveau);
       logger.info("Nouvel ensemble d'album ="+Ensemble);
     }catch (Exception e) {
       logger.info("Erreur dans les entrées utilisateurs",e);
     }
   }

    /**
     * Tri le contenu des albums de différentes manières
     * @param  Choix Choix du systeme de tri
     * @return       Renvoi la liste triée
     */
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

          /**
           * Si le choix voulu n'est pas parmis ceux disponible
           */
        default:
          return "ERROR";
        }
    }

    /**
     * Accesseur d'un album parmis l'ArrayList d'albums
     * @param  number Numero de l'Album à récupérer
     * @return        Renvoi l'album selectionné
     */
    public StockageVolatile get(int number){
      return Ensemble.get(number);
    }

    /**
     * Accesseur de la l'ArrayList
     * @return ArrayList d'Album
     */
    public ArrayList<StockageVolatile> getEnsemble(){
      return Ensemble;
    }

    /**
     * Creer un String contenant l'ensemble des chansons avec les informations sur l'album au début
     * @return Renvoi la chaine de caractere
     */
    public String toString(){
      String s="";
      for (StockageVolatile Courant : Ensemble ) {
        s+=(Courant+"\n");
      }
      return s;
    }
}
