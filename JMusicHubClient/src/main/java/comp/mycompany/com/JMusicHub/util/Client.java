package comp.mycompany.com.JMusicHub.util;
import org.apache.log4j.Logger;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;

import org.w3c.dom.*;
import java.io.IOException;
import java.io.File;
import java.util.UUID;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.*;

/**
 * Permet d'envoyer différentes informations au serveur
 */
public class Client implements InterfaceClient{
  final static Logger logger = Logger.getLogger(Client.class);

  private static DataOutputStream dataOutputStream = null;
  private static DataInputStream dataInputStream = null;
  //IP Local
  public final static String SERVER = "127.0.0.1";

  /**
   * Reception d'un fichier depuis le serveur
   * @param  tempDir   Localité où le fichier doit être stocké
   * @param  fileName  Nom du fichier sous laquelle il sera enregistrer
   */
    public void receiveFile(String tempDir, String fileName) throws Exception{
      int bytesRead;
      int current = 0;
      FileOutputStream fos = null;
      BufferedOutputStream bos = null;
      Socket sock = null;
      try {
        //Ouverture de la Socket
        sock = new Socket(SERVER, 9876);
        System.out.println("Connecting...");
        logger.info("Apres sock");

        //Ouverture du Fichier où nous ecrirons le contenu lu par la socket
        File fichier = new File(tempDir+fileName);
        logger.info("File is "+tempDir+fileName);

        //Input des informations lu depuis la socket
        InputStream is = sock.getInputStream();
        logger.info("post creation is");
        fos = new FileOutputStream(fichier);
        logger.info("post creation fos");
        bos = new BufferedOutputStream(fos);
        logger.info("post creation bos");
        System.out.println("Receive...");
        byte[] buffer = new byte[8192];
        int count;

        //Ecriture dans le fichier de toutes les informations lus par la socket
        while ((count = is.read(buffer)) > 0)
        {
          bos.write(buffer, 0, count);
          current += count;
        }
        bos.flush();
        logger.info("File " + fileName+ " downloaded (" + current + " bytes read)");
      }
      //Fermeture des flux
      finally {
        if (fos != null) fos.close();
        if (bos != null) bos.close();
        if (sock != null) sock.close();
        logger.info("close all");
      }
    }

    /**
     * Reception d'un fichier depuis le serveur pour le stocker sur le client
     * @param  FichierAEnvoyer        Nom du fichier dont nous voulons l'obtention
     * @return                        Renvoi du fichier que nous recupérons du serveur
     */
    public File RecupFile(String FichierAEnvoyer)throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
      //get the localhost IP address, if server is running on some other IP, you need to use that
      InetAddress host = InetAddress.getLocalHost();
      Socket socket = null;
      ObjectOutputStream oos = null;
      ObjectInputStream ois = null;

      //establish socket connection to server
      socket = new Socket(host.getHostName(), 9876);
      //write to socket using ObjectOutputStream
      oos = new ObjectOutputStream(socket.getOutputStream());
      System.out.println("Envoi de "+FichierAEnvoyer);
      oos.writeObject(FichierAEnvoyer);
      //read the server response message
      ois = new ObjectInputStream(socket.getInputStream());
      File Recuperer = (File) ois.readObject();
      //close resources
      ois.close();
      oos.close();
      try{
        Thread.sleep(100);
      }catch (Exception e) {
        System.out.println(e.getMessage());
      }
      return Recuperer;
    }

    /**
     * Recupération des Chansons disponibles sur le serveur
     * @return Renvoi de cette liste de chanson
     */
    public ChansonVolatile RecupChansonVolatile()throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
      ChansonVolatile  ListeChanson = new ChansonVolatile();
      //get the localhost IP address, if server is running on some other IP, you need to use that
      InetAddress host = InetAddress.getLocalHost();
      Socket socket = null;
      ObjectOutputStream oos = null;
      ObjectInputStream ois = null;

      //establish socket connection to server
      socket = new Socket(host.getHostName(), 9876);
      //write to socket using ObjectOutputStream
      oos = new ObjectOutputStream(socket.getOutputStream());
      System.out.println("Envoi de d");
      oos.writeObject('d');
      //read the server response message
      ois = new ObjectInputStream(socket.getInputStream());
      ListeChanson = (ChansonVolatile) ois.readObject();
      ois.close();
      oos.close();
      try{
        Thread.sleep(100);
      }catch (Exception e) {
        System.out.println(e.getMessage());
      }
      return ListeChanson;
    }



    /**
     * Recupération des LivreAudio disponibles sur le serveur
     * @return Renvoi de cette liste de chanson
     */
    public LivreAudioVolatile RecupLivreAudioVolatile()throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
      LivreAudioVolatile  ListeLivreAudio = new LivreAudioVolatile();
      //get the localhost IP address, if server is running on some other IP, you need to use that
      InetAddress host = InetAddress.getLocalHost();
      Socket socket = null;
      ObjectOutputStream oos = null;
      ObjectInputStream ois = null;

      //establish socket connection to server
      socket = new Socket(host.getHostName(), 9876);
      //write to socket using ObjectOutputStream
      oos = new ObjectOutputStream(socket.getOutputStream());
      System.out.println("Envoi de d");
      oos.writeObject('d');
      //read the server response message
      ois = new ObjectInputStream(socket.getInputStream());
      ListeLivreAudio = (LivreAudioVolatile) ois.readObject();
      //close resources
      ois.close();
      oos.close();
      try{
        Thread.sleep(100);
      }catch (Exception e) {
        System.out.println(e.getMessage());
      }
      return ListeLivreAudio;
    }



    /**
     * Recupération des Albums disponibles sur le serveur
     * @return Renvoi de cette liste de chanson
     */
    public AlbumVolatile RecupAlbumVolatile()throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
      AlbumVolatile       ListeAlbum      = new AlbumVolatile();
      //get the localhost IP address, if server is running on some other IP, you need to use that
      InetAddress host = InetAddress.getLocalHost();
      Socket socket = null;
      ObjectOutputStream oos = null;
      ObjectInputStream ois = null;

      //establish socket connection to server
      socket = new Socket(host.getHostName(), 9876);
      //write to socket using ObjectOutputStream
      oos = new ObjectOutputStream(socket.getOutputStream());
      System.out.println("Envoi de d");
      oos.writeObject('d');
      //read the server response message
      ois = new ObjectInputStream(socket.getInputStream());
      ListeAlbum = (AlbumVolatile) ois.readObject();
      //close resources
      ois.close();
      oos.close();
      try{
        Thread.sleep(100);
      }catch (Exception e) {
        System.out.println(e.getMessage());
      }
      return ListeAlbum;
    }



    /**
     * Recupération des Playlists disponibles sur le serveur
     * @return Renvoi de cette liste de chanson
     */
    public PlaylistVolatile RecupPlaylistVolatile()throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
    PlaylistVolatile    ListePlaylist   = new PlaylistVolatile();
      //get the localhost IP address, if server is running on some other IP, you need to use that
      InetAddress host = InetAddress.getLocalHost();
      Socket socket = null;
      ObjectOutputStream oos = null;
      ObjectInputStream ois = null;

      //establish socket connection to server
      socket = new Socket(host.getHostName(), 9876);
      //write to socket using ObjectOutputStream
      oos = new ObjectOutputStream(socket.getOutputStream());
      System.out.println("Envoi de d");
      oos.writeObject('d');
      //read the server response message
      ois = new ObjectInputStream(socket.getInputStream());
      ListePlaylist = (PlaylistVolatile) ois.readObject();
      //close resources
      ois.close();
      oos.close();
      try{
        Thread.sleep(100);
      }catch (Exception e) {
        System.out.println(e.getMessage());
      }
      return ListePlaylist;
    }

    /**
     * Envoi d'un int au serveur
     * @param  Choix                  Objet contenant la valeur à envoyer
     */
    public void EnvoiChoix(Mutable Choix) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
      InetAddress host = InetAddress.getLocalHost();
      Socket socket = null;
      ObjectOutputStream oos = null;

      //establish socket connection to server
      socket = new Socket(host.getHostName(), 9876);
      //write to socket using ObjectOutputStream
      oos = new ObjectOutputStream(socket.getOutputStream());
      //write to socket using ObjectOutputStream
      oos.writeObject(Choix.getValue());
      //close resources
      oos.close();
      try{
        Thread.sleep(100);
      }catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }

    /**
     * Envoi d'un String au server
     * @param  Aenvoyer               String à envoyer au serveur
     */
    public void EnvoiString(String Aenvoyer) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
      InetAddress host = InetAddress.getLocalHost();
      Socket socket = null;
      ObjectOutputStream oos = null;

      //establish socket connection to server
      socket = new Socket(host.getHostName(), 9876);
      //write to socket using ObjectOutputStream
      oos = new ObjectOutputStream(socket.getOutputStream());
      //write to socket using ObjectOutputStream
      oos.writeObject(Aenvoyer);
      //close resources
      oos.close();
      try{
        Thread.sleep(100);
      }catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
}
