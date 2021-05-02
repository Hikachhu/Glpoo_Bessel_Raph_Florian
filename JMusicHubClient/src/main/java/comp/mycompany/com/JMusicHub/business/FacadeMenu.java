package comp.mycompany.com.JMusicHub.business;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

import java.util.ArrayList;
import java.util.Scanner;
import org.apache.log4j.Logger;
import java.io.*;

/**
 * La facade à pour objectif de simplification la manipulation des différentes listes en plancant leurs manipulation dans des méthodes
 * Ainsi nous pouvons manipuler la facade de différentes manière sans s'occupant de manipuler les différentes structures de stockage
 */
public class FacadeMenu{

  /**
   * Couleurs disponibles pour l'affichage
   */
  final String RESET = "\u001B[0m";
  final String BLACK = "\u001B[30m";
  final String RED = "\u001B[31m";
  final String GREEN = "\u001B[32m";
  final String YELLOW = "\u001B[33m";
  final String BLUE = "\u001B[34m";
  final String PURPLE = "\u001B[35m";
  final String CYAN = "\u001B[36m";
  final String WHITE = "\u001B[37m";

  /**
   * Factory permettant de créer des objets de stockages de différents types, ainsi leurs creations est cachée est plus flexible
   */
  FactoryOfStockageVolatile FactoryStockageVolatile = new FactoryOfStockageVolatile();
  FactoryOfStockageMaster   FactoryStockageMaster   = new FactoryOfStockageMaster();

  /**
   * Generation Objets de stockages à l'aide des factorys
   */
  StockageVolatile          ListeChanson            = FactoryStockageVolatile.Generate("Chanson");
  StockageVolatile          ListeLivreAudio         = FactoryStockageVolatile.Generate("LivreAudio");
  StockageMaster            ListeAlbum              = FactoryStockageMaster.Generate("Album");
  StockageMaster            ListePlaylist           = FactoryStockageMaster.Generate("Playlist");

  /**
   * Objet utile à la manipulation des différentes listes de stockage
   */
  final static Logger logger = Logger.getLogger(FacadeMenu.class);
  Scanner clavier = new Scanner(System.in);
  char c='h';
  int ChansonNumber;
  InterfaceClient client = new Client();
  Mutable ChoixClient = MutableInt.getInstance();
  InterfaceAudio Player = new PlaySound();

  /**
   * Mise à jour des différentes listes depuis le server
   */

  public void ReceptionClient(){
     InterfaceClient client  = new Client();
    try{
      /**
       * Reception de la liste des Chanson
       */
      ListeChanson=client.RecupChansonVolatile();
      logger.info("Liste chanson = "+ListeChanson);
      /**
       * Reception de la liste des LivresAudios
       */
      ListeLivreAudio=client.RecupLivreAudioVolatile();
      logger.info("Liste LivreAudio = "+ListeLivreAudio);
      /**
       * Receptions de la liste des Albums
       */
      ListeAlbum=client.RecupAlbumVolatile();
      logger.info("ListeAlbum = "+ListeAlbum);
      /**
       * Reception de la liste des playlist
       */
      ListePlaylist=client.RecupPlaylistVolatile();
      logger.info("ListePlaylist = "+ListePlaylist);
    }catch (Exception e) {
      logger.error("Echec recupération des fichiers",e);
    }
  }

  /**
   * Ajout d'une Chanson dans un album
   */
  public void AjoutMusiqueDansAlbum(){
    logger.info("Ajout d'une musique dans un album");
    int AlbumNumber;
    System.out.println("Liste des albums et leurs contenus:\n " +ListeAlbum);
    System.out.println("Listes des chansons disponibles: \n"    +ListeChanson);

    /**
     * Selection de l'album où nous voulons ajouter une musique
     */
    do {
      System.out.println("Selectionnez l'album où ajouter des musiques");
      AlbumNumber=clavier.nextInt();
    } while (!(AlbumNumber>=0 && AlbumNumber<ListeAlbum.getEnsemble().size()));

    /**
     * Selection d'une chanson que nous voulons ajouter dans l'album
     */
    do {
      System.out.println("Selectionnez un id à ajouter entre 1 et "+ListeChanson.getEnsemble().size());
      ChansonNumber=clavier.nextInt();
    } while (!(ChansonNumber>=0 && ChansonNumber<ListeChanson.getEnsemble().size()));

    /**
     * Envoi des informations relative au numero de l'album et la musique à ajouter
     */
    try{
      ChoixClient.setValue(AlbumNumber);
      client.EnvoiChoix(ChoixClient);
      ChoixClient.setValue(ChansonNumber);
      client.EnvoiChoix(ChoixClient);
      logger.info("Envoi de "+AlbumNumber+" et "+ChansonNumber);
    }catch (Exception e) {
      logger.error("Erreur dans l'envoi des informations pour l'ajout de musique à un album",e);
    }
  }

  public void SuppressionAlbum(){
    ListeAlbum.Suppression();
  }

  /**
   * Affichage des différentes listes disponibles
   */
  public void Display(){

    logger.info("Affichage liste musique");
    System.out.println(RED+"Chanson :"+RESET);
    System.out.println(ListeChanson);

    System.out.println(RED+"\nAlbum :"+RESET);
    System.out.println(ListeAlbum);

    System.out.println(RED+"\nPlaylist :"+RESET);
    System.out.println(ListePlaylist);

    System.out.println(RED+"\nLivre audio:"+RESET);
    System.out.println(ListeLivreAudio);
  }

  /**
   * Affichage des informations permettant d'utiliser la Facade
   */
  public void Help(){
    System.out.println(PURPLE+"Commandes disponibles"+RESET);
    System.out.println("« d » : Affiche toutes les listes");
    System.out.println("« a » : rajout d’un nouvel album");
    System.out.println("« + » : rajout d’une chanson existante à un album");
    System.out.println("« _ » : suppression d’un Album");
    System.out.println("« p » : creation d’une nouvelle playlist à partir de chansons et livres audio existants");
    System.out.println("« - » : suppression d’une Playlist");
    System.out.println("« s » : sauvegarde des playlists, des albums, des chansons et des livres audios dans les fichiers xml respectifs");
    System.out.println("« h » : aide avec les détails des commandes précédentes");
    System.out.println("« j » : Mode Player");
    System.out.println("« m » : Synchronisation avec le serveur");
    System.out.println("« q » : quitte le programme");
  }

  /**
   * Creation d'une playlist à partir de la liste des LivresAudios et des chansons
   */
  public void CreationPlaylist(){
    ListePlaylist.addUser(ListeLivreAudio,ListeChanson);
  }

  /**
   * Creation d'un nouvel album
   */
  public void AjoutAlbum(){
    ListeAlbum.addUser();
  }

  /**
   * Suppression d'une playlist
   */
  public void SuppressionPlaylist(){
    if (ListePlaylist.getEnsemble().size()!=0)ListePlaylist.Suppression();
  }

  /**
   * Lecture parmis les Stockages disponibles
   * @param tempDir dossier où se trouvent les chanson
   */
  public void LectureAudio(String tempDir){
    String Ajouer="";
    StockageVolatile AjouerPlaylist,AjouterAlbum;
    Adapter adapter = new Adapter();
    int NumeroId;

    System.out.println(PURPLE+"Quel type de fichier voulez-vous jouer ?"+RESET);
    System.out.println("« c » : Chanson");
    System.out.println("« l » : Livre audio");
    System.out.println("« a » : Album");
    System.out.println("« p » : Playlist");
    /**
     * Selection du type de Stockage utilisé pour l'écoute
     */
    c = clavier.next().charAt(0);
    try{
      ChoixClient.setValue(adapter.Conversion(c,'c','l','a','p'));
    }catch (Exception e) {
      System.out.println("ERROR "+e.getMessage());
    }
    try {
      client.EnvoiChoix(ChoixClient);
    }catch (Exception e) {

    }

    /**
     * Execution de la Methode utile pour lire une specifique selon le type de Liste souhaité
     */
    switch (c) {
      //Ecoute de chanson
      case 'c':
        System.out.println(RED+"Choissisez un id parmis les chansons disponibles:"+RESET);
        System.out.println(ListeChanson);
        ConfirmationChoixLecture(tempDir,ListeChanson);
        break;

      //Ecoute de livre audio
      case 'l':
        System.out.println(RED+"\nChoissisez un id parmis les Livres audios:"+RESET);
        System.out.println(ListeLivreAudio.Tri(0));

        ConfirmationChoixLecture(tempDir,ListeLivreAudio);
        break;

        //Ecoute d'album
      case 'a':
        System.out.println(RED+"\nChoissisez un id parmis les Albums:"+RESET);
        System.out.println(ListeAlbum.Tri(0));
        ConfirmationChoixLecture(tempDir,ListeAlbum);
        break;

        //Ecoute de playlist
      case 'p':
        System.out.println(RED+"\nChoissisez un id parmis les Playlists:"+RESET);
        System.out.println(ListePlaylist.Tri(0));
        ConfirmationChoixLecture(tempDir,ListePlaylist);
      }
  }

  /**
   * Permet de demander et vérifier les informations fournis par un utilisateurs sur la lecture d'une liste de données
   * @param tempDir    Localisation des fichiers à lire
   * @param AConfirmer Liste parmis laquel l'utilisateur doit faire une selection
   */
  public void ConfirmationChoixLecture(String tempDir,StockageMaster AConfirmer){
    StockageVolatile AjouterAlbum;
    int NumeroId;
    NumeroId=clavier.nextInt();
    /**
     * Demande et envoi du numero de la liste à jouer
     */
    try {
      ChoixClient.setValue(NumeroId);
      client.EnvoiChoix(ChoixClient);
      logger.info("Envoi de la valeur "+NumeroId);
    }catch (Exception e) {
      logger.info("Erreur dans l'envoi de la valeur "+ChoixClient.getValue(),e);
    }
    AjouterAlbum=AConfirmer.get(NumeroId);
    System.out.println("Vous voulez jouer "+AjouterAlbum+" ? (y/n)");
    c=clavier.next().charAt(0);
    /**
     * Modification de l'information puis envoi de celle ci au serveur
     */
    switch(c){
      case 'y':
        ChoixClient.setValue(1);
        break;
      default:
        ChoixClient.setValue(2);
        break;
      }
      try {
        client.EnvoiChoix(ChoixClient);
      }catch (Exception e) {
        logger.error("Erreur dans l'envoi du choix au serveur",e);
      }
    /**
     * Lecture d'une liste de plusieurs fichiers audios
     */
    if(c=='y'){
      System.out.println("Lecture de "+AjouterAlbum);
      Player.LectureListe(AjouterAlbum,tempDir);
      logger.info("Lecture de "+AjouterAlbum);

    }else{
      System.out.println("Anulation lecture de "+AConfirmer);
      logger.info("Annulation lecture");
    }
  }

  /**
   * Permet de demander et vérifier les informations fournis par un utilisateurs sur la lecture d'une donnée
   * @param tempDir    Localisation du fichier à lire
   * @param AConfirmer Liste parmis laquel l'utilisateur doit faire une selection
   */
  public void ConfirmationChoixLecture(String tempDir,StockageVolatile ListeChanson){
    String Ajouer="";
    int NumeroId=clavier.nextInt();
    /**
     * Demande et envoi du numero de la liste à jouer
     */
    try {
      ChoixClient.setValue(NumeroId);
      client.EnvoiChoix(ChoixClient);
    }catch (Exception e) {
      logger.error("Erreur dans l'envoi du choix au serveur",e);
    }
    Ajouer=ListeChanson.get(NumeroId).getContenu();
    System.out.println("Vous voulez jouer "+Ajouer+" ? (y/n)");
    /**
     * Recuperation  du choix de selection d'un utilisateur et l'envoi au serveur
     */
    c=clavier.next().charAt(0);
    switch(c){
      case 'y':
        ChoixClient.setValue(1);
        break;
      default:
        ChoixClient.setValue(2);
        break;
      }
      try {
        client.EnvoiChoix(ChoixClient);
      }catch (Exception e) {
        logger.error("Erreur dans l'envoi du choix au server");
      }
      /**
       * Si le client valide son choix alors nous allons lire le fichier en le recupérant
       */
      if(c=='y'){
        System.out.println("Lecture de "+Ajouer);
        /**
         * Recuperation du fichier que nous lisons
         */
        try{
          client.receiveFile(tempDir, Ajouer);
        }catch (Exception e) {
          logger.error("Erreur dans la reception du fichier "+Ajouer,e);
        }
        /**
         * Lecture du fichier
         * @param tempDir+Ajouer Localisation du fichier à lire
         */
        Player.Lecture(tempDir+Ajouer);
      }else{
        System.out.println("Anulation lectue de "+Ajouer);
      }
  }
}
