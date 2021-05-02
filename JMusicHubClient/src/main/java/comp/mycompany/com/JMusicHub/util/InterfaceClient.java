package comp.mycompany.com.JMusicHub.util;
import org.apache.log4j.Logger;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

import java.io.*;
import java.net.*;

public interface InterfaceClient{
  public void               receiveFile(String tempDir, String fileName)  throws Exception;
  public File               RecupFile(String FichierAEnvoyer)             throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException;
  public ChansonVolatile    RecupChansonVolatile()                        throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException;
  public LivreAudioVolatile RecupLivreAudioVolatile()                     throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException;
  public AlbumVolatile      RecupAlbumVolatile()                          throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException;
  public PlaylistVolatile   RecupPlaylistVolatile()                       throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException;
  public void               EnvoiChoix(Mutable Choix)                  throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException;
  public void               EnvoiString(String Aenvoyer)                  throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException;
}
