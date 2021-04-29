package comp.mycompany.com.JMusicHub.util;
import org.apache.log4j.Logger;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


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

public class Client {
  final static Logger logger = Logger.getLogger(Client.class);

  private static DataOutputStream dataOutputStream = null;
  private static DataInputStream dataInputStream = null;
  public final static String SERVER = "127.0.0.1";

    public void receiveFile(String tempDir, String fileName) throws Exception{
      int bytesRead;
      int current = 0;
      FileOutputStream fos = null;
      BufferedOutputStream bos = null;
      Socket sock = null;
      try {
        sock = new Socket(SERVER, 9876);
        System.out.println("Connecting...");
        logger.info("Apres sock");
        File fichier = new File(tempDir+fileName);
        logger.info("File is "+tempDir+fileName);
        InputStream is = sock.getInputStream();
        logger.info("post creation is");
        fos = new FileOutputStream(fichier);
        logger.info("post creation fos");
        bos = new BufferedOutputStream(fos);
        logger.info("post creation bos");
        System.out.println("Receive...");
        byte[] buffer = new byte[8192]; // or 4096, or more
        int count;
        while ((count = is.read(buffer)) > 0)
        {
          logger.info("Read " + count + " "+current);
          bos.write(buffer, 0, count);
          current += count;
        }
        bos.flush();
        logger.info("File " + fileName+ " downloaded (" + current + " bytes read)");
      }
      finally {
        if (fos != null) fos.close();
        if (bos != null) bos.close();
        if (sock != null) sock.close();
        logger.info("close all");
      }
    }


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
	  //Object o = ois.readObject();
	  //System.out.println(ListeChanson);
	  //System.out.println("=*");
	  //System.out.println(o.getClass());
	  //System.out.println( (ChansonVolatile) o);
      //close resources
      ois.close();
      oos.close();
      try{
        Thread.sleep(100);
      }catch (Exception e) {
        System.out.println(e.getMessage());
      }
      return ListeChanson;
    }

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

    public void EnvoiChoix(MutableInt Choix) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
      InetAddress host = InetAddress.getLocalHost();
      Socket socket = null;
      ObjectOutputStream oos = null;

      //establish socket connection to server
      socket = new Socket(host.getHostName(), 9876);
      //write to socket using ObjectOutputStream
      oos = new ObjectOutputStream(socket.getOutputStream());
      //write to socket using ObjectOutputStream
      System.out.println("Envoi de "+Choix.getValue());
      oos.writeObject(Choix.getValue());
      //close resources
      oos.close();
      try{
        Thread.sleep(100);
      }catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }

    public void EnvoiString(String Aenvoyer) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
      InetAddress host = InetAddress.getLocalHost();
      Socket socket = null;
      ObjectOutputStream oos = null;

      //establish socket connection to server
      socket = new Socket(host.getHostName(), 9876);
      //write to socket using ObjectOutputStream
      System.out.println("Envoi de "+Aenvoyer);
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
