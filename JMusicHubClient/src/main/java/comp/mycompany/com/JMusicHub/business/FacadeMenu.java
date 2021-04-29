package comp.mycompany.com.JMusicHub.business;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

import java.util.ArrayList;
import java.util.Scanner;
import org.apache.log4j.Logger;
import java.io.*;

public class FacadeMenu{

  final String RESET = "\u001B[0m";
  final String BLACK = "\u001B[30m";
  final String RED = "\u001B[31m";
  final String GREEN = "\u001B[32m";
  final String YELLOW = "\u001B[33m";
  final String BLUE = "\u001B[34m";
  final String PURPLE = "\u001B[35m";
  final String CYAN = "\u001B[36m";
  final String WHITE = "\u001B[37m";

  final static Logger logger = Logger.getLogger(FacadeMenu.class);
  Scanner clavier = new Scanner(System.in);
  char c='h';
  int ChansonNumber;
  Client client = new Client();
  MutableInt ChoixClient = MutableInt.getInstance();
  PlaySound Player = new PlaySound();

  FactoryOfStockageVolatile FactoryStockageVolatile = new FactoryOfStockageVolatile();
  FactoryOfStockageMaster   FactoryStockageMaster   = new FactoryOfStockageMaster();
  StockageVolatile          ListeChanson            = FactoryStockageVolatile.Generate("Chanson");
  StockageVolatile          ListeLivreAudio         = FactoryStockageVolatile.Generate("LivreAudio");
  StockageMaster            ListeAlbum              = FactoryStockageMaster.Generate("Album");
  StockageMaster            ListePlaylist           = FactoryStockageMaster.Generate("Playlist");

  public void ReceptionClient(){
    Client client = new Client();
    try{
      ListeChanson=client.RecupChansonVolatile();
      logger.info("Liste chanson = "+ListeChanson);
      ListeLivreAudio=client.RecupLivreAudioVolatile();
      ListeAlbum=client.RecupAlbumVolatile();
      ListePlaylist=client.RecupPlaylistVolatile();
      logger.info("Reception des fichiers XML");
    }catch (Exception e) {
      logger.error("Echec recupération des fichiers",e);
    }
  }

  public void AjoutMusiqueDansAlbum(){
    logger.info("Ajout d'une musique dans un album");
    int AlbumNumber;
    System.out.println("Liste des albums et leurs contenus:\n " +ListeAlbum);
    System.out.println("Listes des chansons disponibles: \n"    +ListeChanson);

    do {
      System.out.println("Selectionnez l'album où ajouter des musiques");
      AlbumNumber=clavier.nextInt();
    } while (!(AlbumNumber>=0 && AlbumNumber<ListeAlbum.getEnsemble().size()));

    do {
      System.out.println("Selectionnez un id à ajouter");
      ChansonNumber=clavier.nextInt();
    } while (!(ChansonNumber>=0 && ChansonNumber<ListeChanson.getEnsemble().size()));

    try{
      logger.info("Envoi de "+AlbumNumber+" et "+ChansonNumber);
      ChoixClient.setValue(AlbumNumber);
      client.EnvoiChoix(ChoixClient);
      ChoixClient.setValue(ChansonNumber);
      client.EnvoiChoix(ChoixClient);
    }catch (Exception e) {
      logger.error("Erreur dans l'envoi des informations pour l'ajout de musique à un album",e);
    }
  }

  public void Display(){
    logger.info("Affichage liste musique");
    System.out.println(RED+"Chanson disponibles:"+RESET);
    System.out.println(ListeChanson);

    System.out.println(RED+"\nAlbum par date de sortie :"+RESET);
    System.out.println(ListeAlbum.Tri(0));

    System.out.println(RED+"\nAlbum par genre:"+RESET);
    System.out.println(ListeAlbum.Tri(1));

    System.out.println(RED+"\nPlaylist trie par noms:"+RESET);
    System.out.println(ListePlaylist.Tri(0));

    System.out.println(RED+"\nLivre audio:"+RESET);
    System.out.println(ListeLivreAudio);
  }

  public void Help(){
    System.out.println(PURPLE+"Commandes disponibles"+RESET);
    System.out.println("« j » : Mode Player");
    System.out.println("« d » : Affiche toutes les listes");
    System.out.println("« c » : rajout d’une nouvelle chanson");
    System.out.println("« a » : rajout d’un nouvel album");
    System.out.println("« + » : rajout d’une chanson existante à un album");
    System.out.println("« l » : rajout d’un nouveau livre audio");
    System.out.println("« p » : création d’une nouvelle playlist à partir de chansons et livres audio existants");
    System.out.println("« - » : suppression d’une playlist");
    System.out.println("« s » : sauvegarde des playlists, des albums, des chansons et des livres audios dans les fichiers xml respectifs");
    System.out.println("« h » : aide avec les détails des commandes précédentes");
    System.out.println("« q » : quitte le programme");


  }

  public void AjoutChanson(){
    ListeChanson.addUser();
  }

  public void AjoutLivreAudio(){
    ListeLivreAudio.addUser();
  }

  public void CreationPlaylist(){
    ListePlaylist.addUser();

  }

  public void AjoutAlbum(){
    ListeAlbum.addUser();
  }

  public void SuppressionPlaylist(){
    if (ListePlaylist.getEnsemble().size()!=0)ListePlaylist.suppression();
  }

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

  public void ConfirmationChoixLecture(String tempDir,StockageMaster AConfirmer){
    StockageVolatile AjouterAlbum;
    int NumeroId;
    NumeroId=clavier.nextInt();
    try {
      ChoixClient.setValue(NumeroId);
      client.EnvoiChoix(ChoixClient);
    }catch (Exception e) {

    }
    AjouterAlbum=AConfirmer.get(NumeroId);
    System.out.println("Vous voulez jouer "+AjouterAlbum+" ? (y/n)");
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

      }
    if(c=='y'){
      System.out.println("Lecture de "+AjouterAlbum);
      Player.LectureListe(AjouterAlbum,tempDir);

    }else{
      System.out.println("Anulation lectue de "+AConfirmer);
    }
  }

  public void ConfirmationChoixLecture(String tempDir,StockageVolatile ListeChanson){
    String Ajouer="";
    int NumeroId=clavier.nextInt();
    try {
      ChoixClient.setValue(NumeroId);
      client.EnvoiChoix(ChoixClient);
    }catch (Exception e) {

    }
    Ajouer=ListeChanson.get(NumeroId).getContenu();
    System.out.println("Vous voulez jouer "+Ajouer+" ? (y/n)");
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
      }
      if(c=='y'){
        System.out.println("Lecture de "+Ajouer);
        try{
          client.receiveFile(tempDir, Ajouer);
        }catch (Exception e) {
        }
        Player.Lecture(tempDir+Ajouer);
      }else{
        System.out.println("Anulation lectue de "+Ajouer);
      }
  }
}
