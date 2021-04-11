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

    if(System.getProperty("os.name").startsWith("Windows"))
    {
      logger.info("Initialisation des couleurs");
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


    Client client = new Client();
    MutableInt ChoixClient = new MutableInt(0,0);
    String tempDir=System.getProperty("java.io.tmpdir");

    try{
      ListeChanson=client.RecupChansonVolatile();
      ListeLivreAudio=client.RecupLivreAudioVolatile();
      ListeAlbum=client.RecupAlbumVolatile();
      ListePlaylist=client.RecupPlaylistVolatile();
      logger.info("Reception des fichiers XML");
    }catch (Exception e) {
      logger.error("Echec recupération des fichiers",e);
    }

    //Boucle principale pour le menu
    do{
      c='h';
      switch (c) {
        case 'd':
        logger.info("Affichage liste musique");
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

        case 'c':
          //Ajout d'une musique dans la list des chansons disponibles
          ListeChanson.addUser();
          break;

        case 'a':
          logger.info("Ajout d'un album dans la liste des albums");
          //Ajout d'un album dans la liste des album dispo
          ListeAlbum.addUser();
          break;

        case '+':
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
          break;

        case 'l':
          ListeLivreAudio.addUser();
          break;

        case 'p':
          logger.info("Creation playlist");
          ListePlaylist.addContenu(ListeLivreAudio,ListeChanson);
          break;

        case '-':
          logger.info("Suppression playlist");
          if (ListePlaylist.getEnsemble().size()!=0)ListePlaylist.suppression();
          break;

        case 'h':
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
      c = clavier.next().charAt(0);
      try{
        switch(c){
          case 'd':
            ChoixClient.setValue(1);
            break;
          case 'c':
            ChoixClient.setValue(2);
            break;
          case 'a':
            ChoixClient.setValue(3);
            break;
          case '+':
            ChoixClient.setValue(4);
            break;
          case 'l':
            ChoixClient.setValue(5);
            break;
          case 'p':
            ChoixClient.setValue(6);
            break;
          case '-':
            ChoixClient.setValue(7);
            break;
          case 's':
            ChoixClient.setValue(8);
            break;
          case 'h':
            ChoixClient.setValue(9);
            break;
          case 'j':
            ChoixClient.setValue(10);
            break;
          case('q'):
            ChoixClient.setValue(11);
            break;
        }
        client.EnvoiChoix(ChoixClient);
      }catch (Exception e) {
        System.out.println(e.getMessage());
      }

      if(c=='j'){
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
        c = clavier.next().charAt(0);
        try{
          switch(c){
            case 'c':
              ChoixClient.setValue(1);
              break;
            case 'l':
              ChoixClient.setValue(2);
              break;
            case 'a':
              ChoixClient.setValue(3);
              break;
            case 'p':
              ChoixClient.setValue(4);
              break;
            }
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
            NumeroId=clavier.nextInt();
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

              try {client.EnvoiChoix(ChoixClient);
              }catch (Exception e) {
              }
              if(c=='y'){

                System.out.println("Lecture de "+Ajouer);

                try{client.receiveFile(tempDir, Ajouer);}catch (Exception e) {

                }

                Player.Lecture(tempDir+Ajouer);

              }else{
                System.out.println("Anulation lectue de "+Ajouer);
              }
              break;

          //Ecoute de livre audio
          case 'l':
            System.out.println(RED+"\nChoissisez un id parmis les Livres audios:"+RESET);
            System.out.println(ListeLivreAudio.Trie());
            NumeroId=clavier.nextInt();
            try {
              ChoixClient.setValue(NumeroId);
              client.EnvoiChoix(ChoixClient);
            }catch (Exception e) {

            }
            Ajouer=ListeLivreAudio.get(NumeroId).getContenu();
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
              try{client.receiveFile(tempDir, Ajouer);}catch (Exception e) {

              }
              Player.Lecture(tempDir+Ajouer);
            }else{
              System.out.println("Anulation lectue de "+Ajouer);
            }
            break;

            //Ecoute d'album
          case 'a':
            System.out.println(RED+"\nChoissisez un id parmis les Albums:"+RESET);
            System.out.println(ListeAlbum.Trie());
            NumeroId=clavier.nextInt();
            try {
              ChoixClient.setValue(NumeroId);
              client.EnvoiChoix(ChoixClient);
            }catch (Exception e) {

            }
            AjouterAlbum=ListeAlbum.get(NumeroId);
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
              System.out.println("Lecture de "+Ajouer);
              Player.LectureAlbum(AjouterAlbum,tempDir);

            }else{
              System.out.println("Anulation lectue de "+Ajouer);
            }
            break;

            //Ecoute de playlist
          case 'p':
            System.out.println(RED+"\nChoissisez un id parmis les Playlists:"+RESET);
            System.out.println(ListePlaylist.Trie());
            NumeroId=clavier.nextInt();
            try {
              ChoixClient.setValue(NumeroId);
              client.EnvoiChoix(ChoixClient);
            }catch (Exception e) {

            }

            AjouerPlaylist=ListePlaylist.get(NumeroId-1);
            System.out.println("Vous voulez jouer "+AjouerPlaylist+" ? (y/n)");
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
              Player.LecturePlaylist(AjouerPlaylist,tempDir);

            }else{
              System.out.println("Anulation lectue de "+Ajouer);
            }

            break;
          }
      }

    }while (c!='q');

  }
}
