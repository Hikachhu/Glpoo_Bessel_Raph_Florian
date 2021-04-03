package comp.mycompany.com.JMusicHub.main;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import java.io.*;
import org.apache.log4j.Logger;


import java.util.Scanner;

class main{

  final static Logger logger = Logger.getLogger(main.class);
  public static void main(String[] args) {

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
    CopyFileStream      Copieur         = new CopyFileStream();
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

    //Boucle principale pour le menu
    do{
      switch (c) {
        case 'd':
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
          //Ajout d'un album dans la liste des album dispo
          ListeAlbum.addUser();
          break;

        case '+':
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

          ListeAlbum.add(AlbumNumber,(ListeChanson.get(ChansonNumber-1)));

          break;

        case 'l':
          ListeLivreAudio.addUser();
          break;

        case 'p':
          ListePlaylist.addContenu(ListeLivreAudio,ListeChanson);
          break;

        case '-':

          if (ListePlaylist.getEnsemble().size()!=0)ListePlaylist.suppression();
          break;

        case 's':
          FichierChansons.writeXML("files/Element.xml",ListeChanson);
          FichierAlbums.writeXML("files/Albums.xml",ListeAlbum);
          FichierLivreAudio.writeXML("files/Element.xml",ListeLivreAudio);
          FichierPlaylist.writeXML("files/Playlist.xml",ListePlaylist);

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
      System.out.println("___________________________________________________");
      System.out.println("Entrez une commande:");
      c = clavier.next().charAt(0);


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
        do{
        c = clavier.next().charAt(0);
        switch (c) {
          case 'c':
            System.out.println(RED+"Choissisez un id parmis les chansons disponibles:"+RESET);
            System.out.println(ListeChanson);
            NumeroId=clavier.nextInt();
            Ajouer=ListeChanson.get(NumeroId).getContenu();
            System.out.println("Vous voulez jouer "+Ajouer+" ? (y/n)");
            c=clavier.next().charAt(0);
            if(c=='y'){
              System.out.println("Lecture de "+Ajouer);
              Player.Lecture(Ajouer);

            }else{
              System.out.println("Anulation lectue de "+Ajouer);
            }
            break;
          case 'l':
            System.out.println(RED+"\nChoissisez un id parmis les Livres audios:"+RESET);
            System.out.println(ListeLivreAudio.Trie());
            NumeroId=clavier.nextInt();
            Ajouer=ListeLivreAudio.get(NumeroId).getContenu();
            System.out.println("Vous voulez jouer "+Ajouer+" ? (y/n)");
            c=clavier.next().charAt(0);
            if(c=='y'){
              System.out.println("Lecture de "+Ajouer);
              Player.Lecture(Ajouer);

            }else{
              System.out.println("Anulation lectue de "+Ajouer);
            }
            break;
          case 'a':
            System.out.println(RED+"\nChoissisez un id parmis les Albums:"+RESET);
            System.out.println(ListeAlbum.Trie());
            NumeroId=clavier.nextInt();

            AjouterAlbum=ListeAlbum.get(NumeroId);
            System.out.println("Vous voulez jouer "+AjouterAlbum+" ? (y/n)");
            c=clavier.next().charAt(0);
            if(c=='y'){
              System.out.println("Lecture de "+Ajouer);
              Player.LectureAlbum(AjouterAlbum);

            }else{
              System.out.println("Anulation lectue de "+Ajouer);
            }
            break;
          case 'p':
            System.out.println(RED+"\nChoissisez un id parmis les Playlists:"+RESET);
            System.out.println(ListePlaylist.Trie());
            NumeroId=clavier.nextInt();

            AjouerPlaylist=ListePlaylist.get(NumeroId-1);
            System.out.println("Vous voulez jouer "+AjouerPlaylist+" ? (y/n)");
            c=clavier.next().charAt(0);
            if(c=='y'){
              System.out.println("Lecture de "+Ajouer);
              Player.LecturePlaylist(AjouerPlaylist);

            }else{
              System.out.println("Anulation lectue de "+Ajouer);
            }

            break;
          }
        }
        while(c!='q');
      }


    }while (c!='q');

  }
}
