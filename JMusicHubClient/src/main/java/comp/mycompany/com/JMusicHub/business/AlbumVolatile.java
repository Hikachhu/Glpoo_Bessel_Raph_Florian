package comp.mycompany.com.JMusicHub.business;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Scanner;
import org.apache.log4j.Logger;

/**
 * Permet de Stocker une liste d'album via une liste de StockageVolatile
 */
public class AlbumVolatile implements StockageMaster, Serializable{
  public ArrayList<StockageVolatile> Ensemble = new ArrayList<StockageVolatile>();

  /**
   * Ajout d'un Album dans la list des albums en envoyant les informations à un server
   */
   public void addUser(StockageVolatile ...Utile){
     InterfaceClient client  = new Client();
    Mutable mutableInt = MutableInt.getInstance();
    Scanner clavier = new Scanner(System.in);
    final Logger logger = Logger.getLogger(AlbumVolatile.class);

    try{

      /**
       * Envoi du titre au serveur
       */

      System.out.println("Entrez le Titre:");
      String Titre=clavier.nextLine();
      try{
        client.EnvoiString(Titre);
        logger.info("Envoi du titre = "+Titre);
      }catch (Exception e) {
        logger.error("Erreur dans l'envoi du titre ",e);
      }

      /**
       * Envoi de la Duree
       */
      System.out.println("Entrez la Duree:");
      int Duree=clavier.nextInt();

      mutableInt.setValue(Duree);
      try{
        client.EnvoiChoix(mutableInt);
        logger.info("Envoi du Duree = "+Duree);
      }catch (Exception e) {
        logger.error("Erreur dans l'envoi de la durée ",e);
      }
      clavier.nextLine();
      /**
       * Envoi de l'Artiste
       */

      System.out.println("Entrez l'Artiste :");
      String Artiste=clavier.nextLine();

      try{
        client.EnvoiString(Artiste);
        logger.info("Envoi de l'Artiste = "+Artiste);
      }catch (Exception e) {
        logger.error("Erreur dans l'envoi de l'Artiste ",e);
      }

      /**
       * Envoi d'un date de sortie
       */
      int DateSortie;
      do {
        System.out.println("Entrez la Date de sortie:");
        DateSortie=clavier.nextInt();
      } while (!(DateSortie>0&&DateSortie<=2021));
      mutableInt.setValue(DateSortie);
      try{
        client.EnvoiChoix(mutableInt);
        logger.info("Envoi de la Date de sortie = "+DateSortie);
      }catch (Exception e) {
        logger.error("Erreur dans l'envoi de la date de sortie ",e);
      }

    }catch (Exception e) {
      System.out.println("Erreur dans les entrées utilisateurs");
      logger.error("Erreur dans l'envoi des informations ou leur créations",e);
    }

  }
    /**
     * Trie les albums selon un parametre
     * @return Renvoi un string contenant les albums triée et leurs chansons
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
         default:
           return "ERROR";
         }

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

  public void Suppression(){
       InterfaceClient client  = new Client();
      Mutable ChoixClient =  MutableInt.getInstance();
    int Number;
    Scanner clavier = new Scanner(System.in);
    do{
      System.out.println("Choisissez la playlist à supprimer");
      Number=clavier.nextInt();
    }while (Number>Ensemble.size()&&Number<1);
    try{
      ChoixClient.setValue(Number);
      client.EnvoiChoix(ChoixClient);
    }catch (Exception e) {

    }
  }
       /**
       * Ajout d'une liste dans la liste des listes
       * @param nouveau [description]
       */
       public void add(StockageVolatile nouveau){
       Ensemble.add(nouveau);
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


    public String toString(){
      String s="";
      for (StockageVolatile Courant : Ensemble ) {
        s+=(Courant+"\n");
      }
      return s;
    }
}
