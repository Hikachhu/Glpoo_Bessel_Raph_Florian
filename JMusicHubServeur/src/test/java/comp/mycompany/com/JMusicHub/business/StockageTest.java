package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import org.junit.jupiter.api.*;

public class StockageTest{

  @Test
  public void TestType(){
    Stockage test = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    Assertions.assertEquals("Chansons",test.getType());
  }

  @Test
  public void TestContenu(){
    Stockage test = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    Assertions.assertEquals("Contenu.wav",test.getContenu());
  }

}
