package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import org.apache.log4j.Logger;

import java.io.*;
import org.apache.log4j.Logger;

import java.lang.ClassNotFoundException;
import java.net.*;

/**
 *
 *  Class pour envoyer toutes les informations au Client
 *  Description détaillée pour la 1ère méthode, les autres méthodes ont des explications
 * moins détaillées pour ce qui est répétitif
 *
 */
public class Serveur implements XMLFiles{
    private static ServerSocket server;
    //Port fixer
    private static int port = 9876;
    //Declaration du logger
    final static Logger logger = Logger.getLogger(Serveur.class);

    /**
     * Méthode d'envoi d'un fichier
     * @param  path        path du fichier à envoyer
     * @throws Exception
     * @throws IOException
     */
    public void sendFile(String path) throws Exception,IOException {

      //Ensemble des classes utile à l'envoi du fichier
      FileInputStream fis = null;
      BufferedInputStream bis = null;
      OutputStream os = null;
      ServerSocket servsock = null;
      Socket sock = null;

      //Test de l'ouverture du serveur sur un port
      try {
         servsock = new ServerSocket(port);
         System.out.println("Attente de connexion du client");
         try {
           //Acceptation de la connexion pour le serveur
           sock = servsock.accept();
           System.out.println("Connexion établie : " + sock);
           logger.info("Connexion établie");

           // Creation du pointeur sur le fichier à envoyer
           File myFile = new File (path);
           logger.info("post ouverture myFile");

           //Tableau utile à l'envoi des informations (en fonction de la taille du fichier)
           byte [] mybytearray  = new byte [(int)myFile.length()];
           logger.info("post ouverture mybytearray "+mybytearray.length );

           //Ouverture du buffer et des flux sur le fichier à envoyer
           fis = new FileInputStream(myFile);
           logger.info("fis");
           bis = new BufferedInputStream(fis);
           logger.info("bis");

           //Lecture du fichier pour mettre le contenu du fichier dans le tableau mybytearray
           bis.read(mybytearray,0,mybytearray.length);
           logger.info("bis read");

           //Creation de la socket
           os = sock.getOutputStream();
           logger.info("os  ");
           System.out.println("Sending " + path + "(" + mybytearray.length + " bytes)");

           //Envoi des information dans la socket
           os.write(mybytearray,0,mybytearray.length);
           logger.info("post os write");
           os.flush();
           System.out.println("Done.");
           //A ce niveau les informations du fichiers ont été théoriquement envoyée
         }
         finally {

           //Fermeture des flux de sortie et sur le fichier
           if (bis != null) bis.close();
           if (os != null) os.close();
           if (sock!=null) sock.close();
         }
      }
      finally {

        //Fermeture du serveur
       if (servsock != null) servsock.close();
      }
    }

    /**
     * Fonction envoyant la liste des chansons disponible sur le Serveur
     * @param  ListeChanson           Liste des chansons issu du XML et destinée à être envoyéel
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void EnvoiChansonVolatile(StockageVolatile ListeChanson)throws IOException, ClassNotFoundException{
      server = new ServerSocket(port);
      System.out.println("Waiting for the client request");
      Socket socket = server.accept();
      ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
      char ChoixMenu = (char) ois.readObject();
      System.out.println("Message Received: " + ChoixMenu);

      ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
      oos.writeObject(ListeChanson);
	  // System.out.println(ListeChanson);
	  oos.flush();
      oos.close();
      ois.close();
      socket.close();
      System.out.println("Shutting down Socket server!!\n");
      server.close();
    }

    /**
     * Méthode envoyant les livres audios
     * @param  ListeLivreAudio         Liste des livres à envoyer
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void EnvoiLivreAudioVolatile(StockageVolatile ListeLivreAudio)throws IOException, ClassNotFoundException{
      server = new ServerSocket(port);
      System.out.println("Waiting for the client request");
      Socket socket = server.accept();
      ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
      char ChoixMenu = (char) ois.readObject();
      System.out.println("Message Received: " + ChoixMenu);
      ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
      oos.writeObject(ListeLivreAudio);
      ois.close();
      oos.close();
      socket.close();
      System.out.println("Shutting down Socket server!!\n");
      server.close();
    }

    /**
     * Méthode envoyant la liste des albums
     * @param  ListeAlbum             Liste albums à envoyer
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void EnvoiAlbumVolatile(StockageMaster ListeAlbum)throws IOException, ClassNotFoundException{
      server = new ServerSocket(port);
      System.out.println("Waiting for the client request");
      Socket socket = server.accept();
      ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
      char ChoixMenu = (char) ois.readObject();
      System.out.println("Message Received: " + ChoixMenu);
      ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
      oos.writeObject(ListeAlbum);
      ois.close();
      oos.close();
      socket.close();
      System.out.println("Shutting down Socket server!!\n");
      server.close();
    }

    /**
     * Méthode d'envoi des playlists
     * @param  ListePlaylist          Liste à envoyer
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void EnvoiPlaylistVolatile(StockageMaster ListePlaylist)throws IOException, ClassNotFoundException{
      server = new ServerSocket(port);
      System.out.println("Waiting for the client request");
      Socket socket = server.accept();
      ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
      char ChoixMenu = (char) ois.readObject();
      System.out.println("Message Received: " + ChoixMenu);
      ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
      oos.writeObject(ListePlaylist);
      ois.close();
      oos.close();
      socket.close();
      System.out.println("Shutting down Socket server!!\n");
      server.close();
    }

    /**
     * Méthode recevant le choix d'un utilisateur par le biais d'un int stockée dans la classe MutableInt
     * @param  ChoixClient            Instance de la classe MutableInt pour conserver la valeur dans le main
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void ChoixUser(MutableInt ChoixClient) throws IOException, ClassNotFoundException{
      server = new ServerSocket(port);
      System.out.println("Waiting for the client request");
      Socket socket = server.accept();
      ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

      //Choix met la valeur de ChoixClient à la valeur lu par le serveur
      ChoixClient.setValue((int) ois.readObject());

      System.out.println("Message Received: " + ChoixClient.getValue());
      ois.close();
      socket.close();
      server.close();
    }

    /**
     * Méthode recevant un String depuis le client et le renvoi
     * @return Renvoi le string lu
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public String ReceptionString() throws IOException, ClassNotFoundException{
      String Recu;
      server = new ServerSocket(port);
      System.out.println("Waiting for the client request");
      Socket socket = server.accept();
      ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

      //Recu recoi la valeur lu par le serveur
      Recu =(String) ois.readObject();
      System.out.println("Message Received: " + Recu);
      ois.close();
      socket.close();
      server.close();
      return Recu;
    }
}
