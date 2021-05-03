package comp.mycompany.com.JMusicHub.business;
import org.apache.log4j.Logger;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

import java.util.*;
import java.io.Serializable;

public class PlaylistVolatile implements StockageMaster, Serializable{

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
    try{
      Ensemble.remove(Number);
    }catch (Exception e) {
      logger.error("Suppression d'un numero qui n'existe pas");
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

  // public ArrayList<Playlist> Ensemble = new ArrayList<Playlist>();
 public void addUser(StockageVolatile ...Utile){
   final Logger logger = Logger.getLogger(PlaylistVolatile.class);
   Serveur serveur = new Serveur();
   MutableInt ChoixClient =  MutableInt.getInstance();
   int number, c;
   Scanner clavier = new Scanner(System.in);
   System.out.println("___________________________________________________");
   System.out.println("Titre de la playlist:");
   String Titre ="";
   try{
     Titre= serveur.ReceptionString();
     logger.info("Bonne reception du titre de la playlist: "+Titre);
   }catch (Exception e) {
     logger.error("Erreur de la reception du titre de la playlist",e);
   }

   //Creation de la playlist
   Playlist nouvelle = new Playlist(Titre,Ensemble.size()+1);

   //Affichage des listes disponibles
   System.out.println("Contenu que vous pouvez ajouter:");
   System.out.println("Chanson:");
   System.out.println(Utile[1]);

   System.out.println("Livre audio:");
   System.out.println(Utile[0]);

   do {
     System.out.println("c ajouter chanson\nl ajouter livreaudio\nEntrez une commande:");
     try{
       serveur.ChoixUser(ChoixClient);
       logger.info("Bonne reception du choix du type de stockage du client");
     }catch (Exception e) {
       logger.error("Erreur de la reception du choix du client ",e);
     }
     c=ChoixClient.getValue();
     System.out.println("selectionnez l'id à ajouter:");
     switch (c) {
       case 1:
         try{
           serveur.ChoixUser(ChoixClient);
           logger.info("Bonne reception du choix ID du client");
         }catch (Exception e) {
           logger.error("Echec reception d'id",e);
         }
         number=ChoixClient.getValue();
         if(number>=0&&number<=(Utile[1].getEnsemble()).size()) nouvelle.add(Utile[1].get(number));
         break;

       case 2:
         try{
           serveur.ChoixUser(ChoixClient);
           logger.info("Bonne reception du choix ID du client");
         }catch (Exception e) {
           logger.error("Echec reception d'id",e);
         }
         number=ChoixClient.getValue();
         if(number>=0&&number<=Utile[0].getEnsemble().size())
            Utile[0].add(Utile[0].get(number));
         else
            System.out.println("Id inconnu");
         break;
     }
     //Sorti de la boucle si le choix est 3 pour le client
   } while (c!=3);
   Ensemble.add(nouvelle);
   logger.info("Ajout de :"+nouvelle);
   logger.info("Ajout de la playlist créé dans la liste des playlist");
 }

  public String Tri(int Choix){
    if(Choix==0){
      ArrayList<StockageVolatile> Init= new ArrayList<StockageVolatile>(Ensemble);
      ArrayList<Playlist> Trier= new ArrayList<Playlist>();
      for (StockageVolatile Actuel : Init) {
        Trier.add((Playlist)Actuel);
      }
      Trier.sort((p1, p2) -> p1.getTitre().compareTo(p2.getTitre()));
      String s="";
      for (Playlist playlist : Trier) {
        s+="\t"+playlist+"\n";
      }
      return s;
    }else{
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
