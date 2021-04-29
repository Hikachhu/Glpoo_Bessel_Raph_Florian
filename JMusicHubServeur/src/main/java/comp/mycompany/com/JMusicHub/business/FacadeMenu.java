package comp.mycompany.com.JMusicHub.business;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Scanner;
import org.apache.log4j.Logger;

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
  Serveur serveur = new Serveur();
  MutableInt ChoixClient= MutableInt.getInstance();

  FactoryOfStockageVolatile FactoryStockageVolatile = new FactoryOfStockageVolatile();
  FactoryOfStockageMaster   FactoryStockageMaster = new FactoryOfStockageMaster();

  StockageVolatile          ListeChanson    = FactoryStockageVolatile.Generate("Chanson");
  StockageVolatile          ListeLivreAudio = FactoryStockageVolatile.Generate("LivreAudio");
  StockageMaster            ListeAlbum      = FactoryStockageMaster.Generate("Album");
  StockageMaster            ListePlaylist   = FactoryStockageMaster.Generate("Playlist");

  WriteChansonVolatile    FichierChansons   = new WriteChansonVolatile();
  WriteAlbumVolatile      FichierAlbums     = new WriteAlbumVolatile();
  WriteLivreAudioVolatile FichierLivreAudio = new WriteLivreAudioVolatile();
  WritePlaylistVolatile   FichierPlaylist   = new WritePlaylistVolatile();


  public void SelectionMenu(int Selection){
    ListeAlbum        = FichierAlbums.readXML("files/Albums.xml");
    ListePlaylist     = FichierPlaylist.readXML("files/Playlist.xml");
    ListeChanson      = AutomaticUpdateXML.GetListFileChanson();
    ListeLivreAudio   = AutomaticUpdateXML.GetListFileLivresAudios();
    switch (Selection) {
      case 1:
        Display();
        break;
      case 2:
        //Ajout d'une musique dans la list des chansons disponibles
        AjoutChanson();
        break;
      case 3:
        //Ajout d'un album dans la liste des album dispo
        AjoutAlbum();
        break;
      case 4:
        AjoutMusiqueDansAlbum();
        break;
      case 5:
        AjoutLivreAudio();
        break;
      case 6:
        CreationPlaylist();
        break;
      case 7:
        SuppressionPlaylist();
        break;
      case 8:
        Enregistrement();
        break;
      case 9:
        Help();
        break;
      case 10:
        LectureAudio();
        break;
      case 12:
        EnvoiClient();
    }
  }

  public void EnvoiClient(){
    XMLFiles EnvoyeurFichier = new Serveur();
    try{
      EnvoyeurFichier.EnvoiChansonVolatile(ListeChanson);
      EnvoyeurFichier.EnvoiLivreAudioVolatile(ListeLivreAudio);
      EnvoyeurFichier.EnvoiAlbumVolatile(ListeAlbum);
      EnvoyeurFichier.EnvoiPlaylistVolatile(ListePlaylist);
    }catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void Display(){
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

  public void Enregistrement(){

    WriteChansonVolatile    FichierChansons   = new WriteChansonVolatile();
    WriteAlbumVolatile      FichierAlbums     = new WriteAlbumVolatile();
    WriteLivreAudioVolatile FichierLivreAudio = new WriteLivreAudioVolatile();
    WritePlaylistVolatile   FichierPlaylist   = new WritePlaylistVolatile();
    FichierChansons.writeXML("files/Element.xml",ListeChanson);
    FichierAlbums.writeXML("files/Albums.xml",ListeAlbum);
    FichierLivreAudio.writeXML("files/Element.xml",ListeLivreAudio);
    FichierPlaylist.writeXML("files/Playlist.xml",ListePlaylist);
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

  public void LectureAudio(){
    PlaySound                 Player          = new PlaySound();
    String Ajouer="";
    StockageVolatile AjouerPlaylist,AjouterAlbum;
    int NumeroId;
    char c=' ';
    System.out.println(PURPLE+"Quel type de fichier voulez-vous jouer ?"+RESET);
    System.out.println("« c » : Chanson");
    System.out.println("« l » : Livre audio");
    System.out.println("« a » : Album");
    System.out.println("« p » : Playlist");
    try{
      serveur.ChoixUser(ChoixClient);
      System.out.println("recu "+c);
    }catch (Exception e) {
      System.out.println("Sorti du programme car : "+e.getMessage());
      System.exit(0);
    }
    switch (ChoixClient.getValue()) {

      //Lecture distante de chanson
      case 1:
        System.out.println(RED+"Choissisez un id parmis les chansons disponibles:"+RESET);
        System.out.println(ListeChanson);
        try{
          serveur.ChoixUser(ChoixClient);
          System.out.println("recu "+c);
        }catch (Exception e) {
          System.out.println("Sorti du programme car : "+e.getMessage());
          System.exit(0);
        }
        NumeroId=ChoixClient.getValue();
        Ajouer=ListeChanson.get(NumeroId).getContenu();
        System.out.println("Vous voulez jouer "+Ajouer+" ? (y/n)");

        try{
          serveur.ChoixUser(ChoixClient);
          System.out.println("recu "+c);
        }catch (Exception e) {
          System.out.println("Sorti du programme car : "+e.getMessage());
          System.exit(0);
        }

        if(ChoixClient.getValue()==1){
          System.out.println("Lecture de "+Ajouer);
          try{
            serveur.sendFile("Data/"+ListeChanson.get(NumeroId).getType()+"/"+Ajouer);
          }catch (Exception e) {

          }
        }else{
          System.out.println("Anulation lectue de "+Ajouer);
        }
        break;

        //Lecture distante de LivreAudio
      case 2:
        System.out.println(RED+"\nChoissisez un id parmis les Livres audios:"+RESET);
        System.out.println(ListeLivreAudio.Tri(0));
        try{
          serveur.ChoixUser(ChoixClient);
          System.out.println("recu "+c);
        }catch (Exception e) {
          System.out.println("Sorti du programme car : "+e.getMessage());
          System.exit(0);
        }
        NumeroId=ChoixClient.getValue();
        Ajouer=ListeLivreAudio.get(NumeroId).getContenu();
        System.out.println("Vous voulez jouer "+Ajouer+" ? (y/n)");


        try{
          serveur.ChoixUser(ChoixClient);
          System.out.println("recu "+c);
        }catch (Exception e) {
          System.out.println("Sorti du programme car : "+e.getMessage());
          System.exit(0);
        }

        if(ChoixClient.getValue()==1){
          System.out.println("Lecture de "+Ajouer);
          try{
            serveur.sendFile("Data/"+ListeChanson.get(NumeroId).getType()+"/"+Ajouer);
          }catch (Exception e) {

          }
        }else{
          System.out.println("Anulation lectue de "+Ajouer);
        }
        break;

        //Lecture distante d'album
      case 3:
        System.out.println(RED+"\nChoissisez un id parmis les Albums:"+RESET);
        System.out.println(ListeAlbum.Tri(0));
        try{
          serveur.ChoixUser(ChoixClient);
          System.out.println("recu "+c);
        }catch (Exception e) {
          System.out.println("Sorti du programme car : "+e.getMessage());
          System.exit(0);
        }
        NumeroId=ChoixClient.getValue();
        AjouterAlbum=ListeAlbum.get(NumeroId);
        System.out.println("Vous voulez jouer "+AjouterAlbum+" ? (y/n)");


        try{
          serveur.ChoixUser(ChoixClient);
        }catch (Exception e) {
          System.out.println("Sorti du programme car : "+e.getMessage());
          System.exit(0);
        }

        if(ChoixClient.getValue()==1){
          System.out.println("Lecture de "+AjouterAlbum);
          Player.LectureAlbum(AjouterAlbum);

        }else{
          System.out.println("Anulation lectue de "+AjouterAlbum);
        }
        break;

        //Lecture distante de playlist
      case 4:
        System.out.println(RED+"\nChoissisez un id parmis les Playlists:"+RESET);
        System.out.println(ListePlaylist.Tri(1));
        try{
          ChoixClient.setValue(0);
          serveur.ChoixUser(ChoixClient);
          System.out.println("recu "+c);
        }catch (Exception e) {
          System.out.println("Sorti du programme car : "+e.getMessage());
          System.exit(0);
        }
        NumeroId=ChoixClient.getValue();
        AjouerPlaylist=ListePlaylist.get(NumeroId-1);
        System.out.println("Vous voulez jouer "+AjouerPlaylist+" ? (y/n)");


        try{
          ChoixClient.setValue(0);
          serveur.ChoixUser(ChoixClient);
        }catch (Exception e) {
          System.out.println("Sorti du programme car : "+e.getMessage());
          System.exit(0);
        }

        if(ChoixClient.getValue()==1){
          System.out.println("Lecture de "+AjouerPlaylist);
          Player.LecturePlaylist(AjouerPlaylist);

        }else{
          System.out.println("Anulation lectue de "+AjouerPlaylist);
        }

        break;
      }
      c='h';
    }

  public void AjoutMusiqueDansAlbum(){
    int AlbumNumber,ChansonNumber;
    try{
      ChoixClient.setValue(0);
      serveur.ChoixUser(ChoixClient);
      AlbumNumber=ChoixClient.getValue();
      serveur.ChoixUser(ChoixClient);
      ChansonNumber=ChoixClient.getValue();
      ListeAlbum.add(AlbumNumber,(ListeChanson.get(ChansonNumber-1)));
    }catch (Exception e) {
      System.out.println("Sorti du programme car : "+e.getMessage());
      System.exit(0);
    }
  }
  public void UpdateListe(){
    ListeChanson      = AutomaticUpdateXML.GetListFileChanson();
    ListeLivreAudio   = AutomaticUpdateXML.GetListFileLivresAudios();
  }
}