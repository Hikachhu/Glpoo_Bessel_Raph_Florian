package comp.mycompany.com.JMusicHub.business;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class FacadeMenu{

  //Liste des couleurs disponible
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
  final static Logger logger = Logger.getLogger(FacadeMenu.class);

  //Liste des factorys disponible
  FactoryOfStockageVolatile FactoryStockageVolatile = new FactoryOfStockageVolatile();
  FactoryOfStockageMaster   FactoryStockageMaster = new FactoryOfStockageMaster();

  //Liste des objet de stockages d'informations
  private static StockageVolatile          ListeChanson    ;
  private static StockageVolatile          ListeLivreAudio ;
  private static StockageMaster            ListeAlbum      ;
  private static StockageMaster            ListePlaylist   ;

  WriteChansonVolatile    FichierChansons   ;
  WriteAlbumVolatile      FichierAlbums     ;
  WriteLivreAudioVolatile FichierLivreAudio ;
  WritePlaylistVolatile   FichierPlaylist   ;

  //Initalisation de toutes les classes utiles
  public FacadeMenu(){
    FichierChansons   = new WriteChansonVolatile();
    FichierAlbums     = new WriteAlbumVolatile();
    FichierLivreAudio = new WriteLivreAudioVolatile();
    FichierPlaylist   = new WritePlaylistVolatile();
    ListeChanson      = FactoryStockageVolatile.Generate("Chanson");
    ListeLivreAudio   = FactoryStockageVolatile.Generate("LivreAudio");
    ListeAlbum        = FactoryStockageMaster.Generate("Album");
    ListePlaylist     = FactoryStockageMaster.Generate("Playlist");
    ListePlaylist     = FichierPlaylist.readXML("files/Playlist.xml");
    ListeAlbum        = FichierAlbums.readXML("files/Albums.xml");
  }

  //Selection d'une méthode à utiliser selon un paramètre d'entrée
  public void SelectionMenu(int Selection){
    //A chaque execution il y a une actualisation des listes
    ListeChanson      = AutomaticUpdateXML.GetListFileChanson();
    ListeLivreAudio   = AutomaticUpdateXML.GetListFileLivresAudios();
    switch (Selection) {
      case 1:
        Display();
        break;
      case 2:
        //Ajout d'un album dans la liste des album dispo
        AjoutAlbum();
        break;
      case 3:
        AjoutMusiqueDansAlbum();
        break;
      case 4:
        SuppressionAlbum();
        break;
      case 5:
        CreationPlaylist();
        break;
      case 6:
        SuppressionPlaylist();
        break;
      case 7:
        Enregistrement();
        break;
      case 8:
        Help();
        break;
      case 9:
        LectureAudio();
        break;
      case 10:
        EnvoiClient();
    }
  }

  public void SuppressionAlbum(){
    ListeAlbum.Suppression();
  }

  /**
    * Envoi des listes à un client
   */
  public void EnvoiClient(){
    InterfaceServeur EnvoyeurFichier = new Serveur();
    try{
      EnvoyeurFichier.EnvoiChansonVolatile(ListeChanson);
      EnvoyeurFichier.EnvoiLivreAudioVolatile(ListeLivreAudio);
      EnvoyeurFichier.EnvoiAlbumVolatile(ListeAlbum);
      EnvoyeurFichier.EnvoiPlaylistVolatile(ListePlaylist);
    }catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Affichage des listess disponible
   */
  public void Display(){
    System.out.println(RED+"Chanson disponibles:"+RESET);
    System.out.println(ListeChanson);

    System.out.println(RED+"\nAlbum disponible :"+RESET);
    System.out.println(ListeAlbum);

    System.out.println(RED+"\nAlbum par genre:"+RESET);
    System.out.println(ListeAlbum.Tri(1));

    System.out.println(RED+"\nPlaylist trie par noms:"+RESET);
    System.out.println(ListePlaylist.Tri(0));

    System.out.println(RED+"\nLivre audio:"+RESET);
    System.out.println(ListeLivreAudio);
  }

  /**
   * Creation d'une playlist à partir des listes deja existante
   */
  public void CreationPlaylist(){
    ListePlaylist.addUser(ListeLivreAudio,ListeChanson);
  }

  /**
   * Ajout d'un Album
   */
  public void AjoutAlbum(){
    ListeAlbum.addUser();
    System.out.println(ListeAlbum);
  }

  /**
   * Suppression d'une Playlist
   */
  public void SuppressionPlaylist(){
    if (ListePlaylist.getEnsemble().size()!=0)ListePlaylist.Suppression();
  }

  /**
   * Sauvegarde de toutes les listes dans des fichiers XML
   */
  public void Enregistrement(){
    WriteChansonVolatile    FichierChansons   = new WriteChansonVolatile();
    WriteLivreAudioVolatile FichierLivreAudio = new WriteLivreAudioVolatile();
    WriteAlbumVolatile      FichierAlbums     = new WriteAlbumVolatile();
    WritePlaylistVolatile   FichierPlaylist   = new WritePlaylistVolatile();
    FichierChansons.writeXML("files/Element.xml",ListeChanson);
    FichierAlbums.writeXML("files/Albums.xml",ListeAlbum);
    FichierLivreAudio.writeXML("files/Element.xml",ListeLivreAudio);
    FichierPlaylist.writeXML("files/Playlist.xml",ListePlaylist);
  }

  /**
   * Affiche les informations relative aux commandes disponible
   */
  public void Help(){
    System.out.println(PURPLE+"Commandes disponibles"+RESET);
    System.out.println("« j » : Mode Player");
    System.out.println("« d » : Affiche toutes les listes");
    System.out.println("« a » : rajout d’un nouvel album");
    System.out.println("« + » : rajout d’une chanson existante à un album");
    System.out.println("« p » : creation d’une nouvelle playlist à partir de chansons et livres audio existants");
    System.out.println("« - » : suppression d’une Playlist");
    System.out.println("« _ » : suppression d’un Album");
    System.out.println("« s » : sauvegarde des playlists, des albums, des chansons et des livres audios dans les fichiers xml respectifs");
    System.out.println("« h » : aide avec les détails des commandes précédentes");
    System.out.println("« m » : Synchronisation avec le serveur");
    System.out.println("« q » : quitte le programme");
  }

  /**
   * Lecture d'un fichier audio
   */
  public void LectureAudio(){
    PlaySound                 Player          = new PlaySound();
    String Ajouer="";
    StockageVolatile AjouerPlaylist,AjouterAlbum;
    int NumeroId;
    char c=' ';
    /**
     * Selection du type de fichier à lire
     */
    System.out.println(PURPLE+"Quel type de fichier voulez-vous jouer ?"+RESET);
    System.out.println("« c » : Chanson");
    System.out.println("« l » : Livre audio");
    System.out.println("« a » : Album");
    System.out.println("« p » : Playlist");
    try{
      //Reception du choix du client
      serveur.ChoixUser(ChoixClient);
      System.out.println("recu "+c);
    }catch (Exception e) {
      System.out.println("Sorti du programme car : "+e.getMessage());
      System.exit(0);
    }

    //En fonction du choix client nous nous mettons à l'écoute de diverses manière
    switch (ChoixClient.getValue()) {

      //Lecture distante de chanson
      case 1:
        System.out.println(RED+"Choissisez un id parmis les chansons disponibles:"+RESET);
        System.out.println(ListeChanson);
        try{
          //Reception du numéro de la chanson à jouer
          serveur.ChoixUser(ChoixClient);
          System.out.println("recu "+c);
        }catch (Exception e) {
          System.out.println("Sorti du programme car : "+e.getMessage());
          System.exit(0);
        }
        NumeroId=ChoixClient.getValue();
        Ajouer=ListeChanson.get(NumeroId).getContenu();
        System.out.println("Vous voulez jouer "+Ajouer+" ? (y/n)");

        //Reception de la validation de l'écoute d'une chanson
        try{
          serveur.ChoixUser(ChoixClient);
          System.out.println("recu "+c);
        }catch (Exception e) {
          System.out.println("Sorti du programme car : "+e.getMessage());
          System.exit(0);
        }

        //Lecture d'une chanson, envoi du fichier pour que le client le lise
        if(ChoixClient.getValue()==1){
          System.out.println("Lecture de "+Ajouer);
          try{
            serveur.sendFile("Data/"+ListeChanson.get(NumeroId).getType()+"/"+Ajouer);
          }catch (Exception e) {
            logger.error("Echec de l'envoi du fichier "+Ajouer);
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


  /**
   * Ajout d'une musique dans un album
   */
  public void AjoutMusiqueDansAlbum(){
    int AlbumNumber,ChansonNumber;
    try{
      ChoixClient.setValue(0);
      serveur.ChoixUser(ChoixClient);
      AlbumNumber=ChoixClient.getValue();
      serveur.ChoixUser(ChoixClient);
      ChansonNumber=ChoixClient.getValue();
      ListeAlbum.add(AlbumNumber,(ListeChanson.get(ChansonNumber)));
    }catch (Exception e) {
      System.out.println("Sorti du programme car : "+e.getMessage());
      System.exit(0);
    }
  }

  //Mise à jour des listes des chansons et livres audios directement dans le dossier
  public void UpdateListe(){
    ListeChanson      = AutomaticUpdateXML.GetListFileChanson();
    ListeLivreAudio   = AutomaticUpdateXML.GetListFileLivresAudios();
  }
}
