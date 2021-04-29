package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

import java.io.*;

public interface XMLFiles{
  public void EnvoiPlaylistVolatile(StockageMaster ListePlaylist)throws IOException, ClassNotFoundException;
  public void EnvoiAlbumVolatile(StockageMaster ListeAlbum)throws IOException, ClassNotFoundException;
  public void EnvoiLivreAudioVolatile(StockageVolatile ListeLivreAudio)throws IOException, ClassNotFoundException;
  public void EnvoiChansonVolatile(StockageVolatile ListeChanson)throws IOException, ClassNotFoundException;
}
