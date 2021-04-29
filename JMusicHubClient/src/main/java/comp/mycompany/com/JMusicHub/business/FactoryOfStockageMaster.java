package comp.mycompany.com.JMusicHub.business;
import org.apache.log4j.Logger;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

public class FactoryOfStockageMaster{
  public StockageMaster Generate(String Choix){
    switch(Choix){
      case "Album":
        return new AlbumVolatile();
      case "Playlist":
        return new PlaylistVolatile();
      default:
        return null;
    }
  }
}
