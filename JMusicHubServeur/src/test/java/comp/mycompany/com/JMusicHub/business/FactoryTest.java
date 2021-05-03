package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import org.junit.jupiter.api.*;

public class FactoryTest{
  @Test
  public void FactoryVolatileTest1(){
     FactoryOfStockageVolatile FactoryStockageVolatile = new FactoryOfStockageVolatile();
     StockageVolatile creer = FactoryStockageVolatile.Generate("Chanson");
     Assertions.assertEquals("class comp.mycompany.com.JMusicHub.business.ChansonVolatile",creer.getClass().toString());
     FactoryOfStockageMaster   FactoryStockageMaster   = new FactoryOfStockageMaster();
  }
  @Test
  public void FactoryVolatileTest2(){
     FactoryOfStockageVolatile FactoryStockageVolatile = new FactoryOfStockageVolatile();
     StockageVolatile creer = FactoryStockageVolatile.Generate("LivreAudio");
     Assertions.assertEquals("class comp.mycompany.com.JMusicHub.business.LivreAudioVolatile",creer.getClass().toString());
     FactoryOfStockageMaster   FactoryStockageMaster   = new FactoryOfStockageMaster();
  }
  @Test
  public void FactoryMasterTest1(){
     FactoryOfStockageMaster   FactoryStockageMaster   = new FactoryOfStockageMaster();
     StockageMaster creer = FactoryStockageMaster.Generate("Album");
     Assertions.assertEquals("class comp.mycompany.com.JMusicHub.business.AlbumVolatile",creer.getClass().toString());
  }
  @Test
  public void FactoryMasterTest2(){
     FactoryOfStockageMaster   FactoryStockageMaster   = new FactoryOfStockageMaster();
     StockageMaster creer = FactoryStockageMaster.Generate("Playlist");
     Assertions.assertEquals("class comp.mycompany.com.JMusicHub.business.PlaylistVolatile",creer.getClass().toString());
  }
}
