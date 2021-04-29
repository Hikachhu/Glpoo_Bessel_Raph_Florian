package comp.mycompany.com.JMusicHub.business;
import org.apache.log4j.Logger;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

import java.util.*;
import java.io.Serializable;

public class PlaylistVolatile extends StockageMaster implements Serializable{
  // public ArrayList<Playlist> Ensemble = new ArrayList<Playlist>();


 public void addUser(){
   FactoryOfStockageVolatile FactoryStockageVolatile = new FactoryOfStockageVolatile();
   StockageVolatile livreaudiovolatile = FactoryStockageVolatile.Generate("Chanson");
   StockageVolatile chansonvolatile= FactoryStockageVolatile.Generate("LivreAudio");
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
   System.out.println(chansonvolatile);

   System.out.println("Livre audio:");
   System.out.println(livreaudiovolatile);

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
         if(number>=0&&number<=(chansonvolatile.getEnsemble()).size()) nouvelle.add(chansonvolatile.get(number));
         break;

       case 2:
         try{
           serveur.ChoixUser(ChoixClient);
           logger.info("Bonne reception du choix ID du client");
         }catch (Exception e) {
           logger.error("Echec reception d'id",e);
         }
         number=ChoixClient.getValue();
         if(number>=0&&number<=livreaudiovolatile.getEnsemble().size()) nouvelle.add(livreaudiovolatile.get(number));
         else System.out.println("Id inconnu");
         break;
     }
     //Sorti de la boucle si le choix est 3 pour le client
   } while (c!=3);
   Ensemble.add(nouvelle);
   logger.info("Ajout de la playlist créé dans la liste des playlist");
 }

 /**
 * suppression d'une playlist grâce à un client
 */
 public void suppression(){
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
   Ensemble.remove(Number-1);
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

}
