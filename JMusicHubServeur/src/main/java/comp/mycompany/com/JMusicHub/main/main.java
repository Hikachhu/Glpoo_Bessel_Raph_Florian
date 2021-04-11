package comp.mycompany.com.JMusicHub.main;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import java.io.*;
import org.apache.log4j.Logger;


import com.sun.jna.*;
import com.sun.jna.platform.win32.WinDef.*;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import java.util.Scanner;

class main{

  final static Logger logger = Logger.getLogger(main.class);
  public static void main(String[] args) {

    if(System.getProperty("os.name").startsWith("Windows")){
        // Set output mode to handle virtual terminal sequences
        Function GetStdHandleFunc = Function.getFunction("kernel32", "GetStdHandle");
        DWORD STD_OUTPUT_HANDLE = new DWORD(-11);
        HANDLE hOut = (HANDLE)GetStdHandleFunc.invoke(HANDLE.class, new Object[]{STD_OUTPUT_HANDLE});

        DWORDByReference p_dwMode = new DWORDByReference(new DWORD(0));
        Function GetConsoleModeFunc = Function.getFunction("kernel32", "GetConsoleMode");
        GetConsoleModeFunc.invoke(BOOL.class, new Object[]{hOut, p_dwMode});

        int ENABLE_VIRTUAL_TERMINAL_PROCESSING = 4;
        DWORD dwMode = p_dwMode.getValue();
        dwMode.setValue(dwMode.intValue() | ENABLE_VIRTUAL_TERMINAL_PROCESSING);
        Function SetConsoleModeFunc = Function.getFunction("kernel32", "SetConsoleMode");
        SetConsoleModeFunc.invoke(BOOL.class, new Object[]{hOut, dwMode});
    }

    logger.info("Debut du programme");

    //Couleurs
    final String RESET = "\u001B[0m";
    final String BLACK = "\u001B[30m";
    final String RED = "\u001B[31m";
    final String GREEN = "\u001B[32m";
    final String YELLOW = "\u001B[33m";
    final String BLUE = "\u001B[34m";
    final String PURPLE = "\u001B[35m";
    final String CYAN = "\u001B[36m";
    final String WHITE = "\u001B[37m";

    char c='h';
    Scanner clavier = new Scanner(System.in);
    int ChansonNumber;


    //Declaration des listes pour stocker les informations
    ChansonVolatile     ListeChanson    = new ChansonVolatile();
    LivreAudioVolatile  ListeLivreAudio = new LivreAudioVolatile();
    AlbumVolatile       ListeAlbum      = new AlbumVolatile();
    PlaylistVolatile    ListePlaylist   = new PlaylistVolatile();
    PlaySound           Player         = new PlaySound();

    //Lecture des informations
    WriteChansonVolatile    FichierChansons   = new WriteChansonVolatile();
    WriteAlbumVolatile      FichierAlbums     = new WriteAlbumVolatile();
    WriteLivreAudioVolatile FichierLivreAudio = new WriteLivreAudioVolatile();
    WritePlaylistVolatile   FichierPlaylist   = new WritePlaylistVolatile();

    ListeChanson      = FichierChansons.readXML("files/Element.xml");
    ListeAlbum        = FichierAlbums.readXML("files/Albums.xml");
    ListeLivreAudio   = FichierLivreAudio.readXML("files/Element.xml");
    ListePlaylist     = FichierPlaylist.readXML("files/Playlist.xml");

    Serveur serveur = new Serveur();
    MutableInt ChoixClient=new MutableInt(0,0);
    try{
      serveur.EnvoiChansonVolatile(ListeChanson);
      serveur.EnvoiLivreAudioVolatile(ListeLivreAudio);
      serveur.EnvoiAlbumVolatile(ListeAlbum);
      serveur.EnvoiPlaylistVolatile(ListePlaylist);
    }catch (Exception e) {
      System.out.println(e.getMessage());
    }
    //Boucle principale pour le menu
    do{
      switch (ChoixClient.getValue()) {
        case 1:
          System.out.println(RED+"Chanson disponibles:"+RESET);
          System.out.println(ListeChanson);

          System.out.println(RED+"\nAlbum par date de sortie :"+RESET);
          System.out.println(ListeAlbum.Trie());

          System.out.println(RED+"\nAlbum par genre:"+RESET);
          System.out.println(ListeAlbum.TrieChanson());

          System.out.println(RED+"\nPlaylist trie par noms:"+RESET);
          System.out.println(ListePlaylist.Trie());

          System.out.println(RED+"\nLivre audio:"+RESET);
          System.out.println(ListeLivreAudio);
          break;

        case 2:
          //Ajout d'une musique dans la list des chansons disponibles
          ListeChanson.addUser();
          break;

        case 3:
          //Ajout d'un album dans la liste des album dispo
          logger.info("Avant addUser de ListeAlbum");
          ListeAlbum.addUser();
          break;

        case 4:
          try{
            int AlbumNumber;
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

          break;

        case 5:
          ListeLivreAudio.addUser();
          break;

        case 6:
          ListePlaylist.addContenu(ListeLivreAudio,ListeChanson);
          break;

        case 7:

          if (ListePlaylist.getEnsemble().size()!=0)ListePlaylist.suppression();
          break;

        case 8:
          FichierChansons.writeXML("files/Element.xml",ListeChanson);
          FichierAlbums.writeXML("files/Albums.xml",ListeAlbum);
          FichierLivreAudio.writeXML("files/Element.xml",ListeLivreAudio);
          FichierPlaylist.writeXML("files/Playlist.xml",ListePlaylist);

          break;

        case 9:
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
          break;
      }
      System.out.println("___________________________MENU PRINCIPALE___________________________");
      System.out.println("Entrez une commande");
      System.out.println("h pour obtenir la liste des commandes disponibles");
      try{
        ChoixClient.setValue(0);
        serveur.ChoixUser(ChoixClient);
        System.out.println("recu "+c);
      }catch (Exception e) {
        System.out.println("Sorti du programme car : "+e.getMessage());
        System.exit(0);
      }
      if(ChoixClient.getValue()==10){
        String Ajouer="";
        Playlist AjouerPlaylist;
        Album AjouterAlbum;
        int NumeroId;
        c=' ';
        System.out.println(PURPLE+"Quel type de fichier voulez-vous jouer ?"+RESET);
        System.out.println("« c » : Chanson");
        System.out.println("« l » : Livre audio");
        System.out.println("« a » : Album");
        System.out.println("« p » : Playlist");
  //      do{

        try{
          ChoixClient.setValue(0);
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
              ChoixClient.setValue(0);
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
              ChoixClient.setValue(0);
              serveur.ChoixUser(ChoixClient);
              System.out.println("recu "+c);
            }catch (Exception e) {
              System.out.println("Sorti du programme car : "+e.getMessage());
              System.exit(0);
            }

            if(ChoixClient.getValue()==1){
              System.out.println("Lecture de "+Ajouer);
              try{
                serveur.sendFile("Data/"+Ajouer);
              }catch (Exception e) {

              }
            }else{
              System.out.println("Anulation lectue de "+Ajouer);
            }
            break;

            //Lecture distante de LivreAudio
          case 2:
            System.out.println(RED+"\nChoissisez un id parmis les Livres audios:"+RESET);
            System.out.println(ListeLivreAudio.Trie());
            try{
              ChoixClient.setValue(0);
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
              ChoixClient.setValue(0);
              serveur.ChoixUser(ChoixClient);
              System.out.println("recu "+c);
            }catch (Exception e) {
              System.out.println("Sorti du programme car : "+e.getMessage());
              System.exit(0);
            }

            if(ChoixClient.getValue()==1){
              System.out.println("Lecture de "+Ajouer);
              try{
                serveur.sendFile("Data/"+Ajouer);
              }catch (Exception e) {

              }
            }else{
              System.out.println("Anulation lectue de "+Ajouer);
            }
            break;

            //Lecture distante d'album
          case 3:
            System.out.println(RED+"\nChoissisez un id parmis les Albums:"+RESET);
            System.out.println(ListeAlbum.Trie());
            try{
              ChoixClient.setValue(0);
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
              ChoixClient.setValue(0);
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
            System.out.println(ListePlaylist.Trie());
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


    }while (ChoixClient.getValue()!=11);

  }
}
