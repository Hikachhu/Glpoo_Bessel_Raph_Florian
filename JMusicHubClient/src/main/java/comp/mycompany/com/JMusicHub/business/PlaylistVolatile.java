package comp.mycompany.com.JMusicHub.business;
import org.apache.log4j.Logger;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

import java.util.*;
import java.io.Serializable;

/**
 * Permet de Stocker une liste des playlists via une liste de StockageVolatile
 */
public class PlaylistVolatile implements StockageMaster, Serializable{

  public ArrayList<StockageVolatile> Ensemble = new ArrayList<StockageVolatile>();
  public void addUser(StockageVolatile ...Utile){
    final Logger logger = Logger.getLogger(PlaylistVolatile.class);

    StockageVolatile livreaudiovolatile = new LivreAudioVolatile();
    StockageVolatile chansonvolatile= new ChansonVolatile();
     InterfaceClient client  = new Client();
    Mutable ChoixClient = MutableInt.getInstance();

    int number;
    char c;
    Scanner clavier = new Scanner(System.in);
    System.out.println("___________________________________________________");
    System.out.println("Titre de la playlist:");
    String Titre =clavier.nextLine();

    /**
     * Envoi du titre au serveur
     */
    try{
      client.EnvoiString(Titre);
    }catch (Exception e) {

    }
    System.out.println("Contenu que vous pouvez ajouter:");
    System.out.println("Chanson:");
    System.out.println(chansonvolatile);

    System.out.println("Livre audio:");
    System.out.println(livreaudiovolatile);

    /**
     * Ajout de tout les elements selectionnés au fur et a mesure
     */
    do {
      System.out.println("c ajouter chanson\nl ajouter livreaudio\nq terminer\nEntrez une commande:");
      c = clavier.next().charAt(0);
      try{
        switch(c){
          case 'c':
            ChoixClient.setValue(1);
            break;
          case 'l':
            ChoixClient.setValue(2);
            break;
          case 'q':
            ChoixClient.setValue(3);
            break;
          }
          //Envoi du choix de l'utilisateur au serveur
          client.EnvoiChoix(ChoixClient);
        }catch (Exception e) {
          logger.error("Echec dans l'envoi des informations au server",e);
        }
      System.out.println("selectionnez l'id à ajouter:");
      switch (c) {
        case 'c':
          number = clavier.nextInt();
          ChoixClient.setValue(number);
          try{
            client.EnvoiChoix(ChoixClient);
          }catch (Exception e) {
            logger.error("Echec envoi information au serveur",e);
          }
          break;

        case 'l':
          number = clavier.nextInt();
          ChoixClient.setValue(number);
          try{
            client.EnvoiChoix(ChoixClient);
          }catch (Exception e) {
            logger.error("Echec envoi information au serveur",e);
          }
          break;
      }
    } while (c!='q');
    logger.info("nouvelle liste des playlists = "+getEnsemble());
  }

  /**
   * Suppression d'une playlist parmis les playlist disponibles
   */
  public void Suppression(){
    final Logger logger = Logger.getLogger(PlaylistVolatile.class);
     InterfaceClient client  = new Client();
    Mutable ChoixClient = MutableInt.getInstance();
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
      logger.error("Echec envoi information au serveur",e);
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



    /**
    * Ajout d'une liste dans la liste des listes
    * @param nouveau Element a ajouter
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

  /**
   * Trie les albums selon un parametre
   * @return Renvoi un string contenant les albums triée et leurs chansons
   */
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
   * Creer un String contenant l'ensemble des chansons avec les informations sur la playlist au début
   * @return Renvoi la chaine de caractere
   */
  public String toString(){
    String s="";
    int i=0;
    for (StockageVolatile Courant : Ensemble ) {
      s+=("Numero :"+i+" | "+Courant+"\n");
      i++;
    }
    return s;
  }

}
