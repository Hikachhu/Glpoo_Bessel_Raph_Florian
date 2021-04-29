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
    Client client = new Client();
    MutableInt mutableInt = MutableInt.getInstance();
      Scanner clavier = new Scanner(System.in);

    try{
      System.out.println("Entrez le Titre:");
      String Titre=clavier.nextLine();
      try{
        client.EnvoiString(Titre);
      }catch (Exception e) {

      }
      System.out.println("Entrez la Duree:");
      int Duree=clavier.nextInt();

      mutableInt.setValue(Duree);
      try{
        client.EnvoiChoix(mutableInt);
      }catch (Exception e) {

      }
      clavier.nextLine();

      System.out.println("Entrez l'Artiste :");
      String Artiste=clavier.nextLine();

      try{
        client.EnvoiString(Artiste);
      }catch (Exception e) {

      }

      int DateSortie;
      do {
        System.out.println("Entrez la Date de sortie:");
        DateSortie=clavier.nextInt();
      } while (!(DateSortie>0&&DateSortie<=2021));
      mutableInt.setValue(DateSortie);
      try{
        client.EnvoiChoix(mutableInt);
      }catch (Exception e) {

      }

    }catch (Exception e) {
      System.out.println("Erreur dans les entrées utilisateurs");
    }

  }

    /**
     * Fonction pour ajouter une chanson à un album dont nous connaissons le numéro
     * @param AlbumNumber Numero de l'album dans l'ArrayList
     * @param Aajouter    Chanson à ajouter
    //  */
    // public void add(int AlbumNumber,Stockage Aajouter){
    //   StockageVolatile nouveau=Ensemble.get(AlbumNumber);
    //   nouveau.add(Aajouter);
    // }

    /**
     * Tri un album parmis l'ArrayList des Albums
     * @param AlbumNumber Numero de l'Album à Trier
     */
    public void TriMusique(int AlbumNumber){
      (Ensemble.get(AlbumNumber)).Tri(0);
    }

    /**
     * Ajoute un Album dans l'ArrayList d'album
     * @param nouveau Album a ajouter
    //  */
    // public void add(Album nouveau){
    //   Ensemble.add(nouveau);
    // }
    //
    // /**
    //  * Creer un String contenant l'ensemble des chansons avec les informations sur l'album au début
    //  * @return Renvoi la chaine de caractere
    //  */
    // public String toString(){
    //   String s="";
    //   for (StockageVolatile Courant : Ensemble ) {
    //     s+=(Courant+"\n");
    //   }
    //   return s;
    // }
    //
    // /**
    //  * Accesseur de la l'ArrayList
    //  * @return ArrayList d'Album
    //  */
    // public ArrayList<StockageVolatile> getEnsemble(){
    //   return Ensemble;
    // }
    //
    // /**
    //  * Accesseur d'un album parmis l'ArrayList d'albums
    //  * @param  number Numero de l'Album à récupérer
    //  * @return        Renvoi l'album selectionné
    //  */
    // public StockageVolatile get(int number){
    //   return Ensemble.get(number);
    // }

    /**
     * Trie les albums selon leurs dates de sortie
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


    public String toString(){
      String s="";
      for (StockageVolatile Courant : Ensemble ) {
        s+=(Courant+"\n");
      }
      return s;
    }
}
