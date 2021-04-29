package comp.mycompany.com.JMusicHub.business;
import org.apache.log4j.Logger;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

public class FactoryOfStockageVolatile{
  public StockageVolatile Generate(String Choix){
    switch(Choix){
      case "Chanson":
        return new ChansonVolatile();
      case "LivreAudio":
        return new LivreAudioVolatile();
      default:
        return null;
    }
  }
}
