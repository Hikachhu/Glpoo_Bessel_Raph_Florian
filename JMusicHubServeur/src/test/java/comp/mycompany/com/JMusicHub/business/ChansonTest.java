package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import org.junit.jupiter.api.*;

public class ChansonTest{
  @Test
  public void Test1(){

    Chanson test = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    Assertions.assertEquals("Titre =Titre | Duree =3m0s | ID= 1 | Artiste = Artiste | Contenu = Contenu.wav | genre = Classique",test.toString());
  }

  @Test
  public void TestGenre(){
    Chanson test = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    Assertions.assertEquals(1,test.getGenreNumber());
  }

  @Test
  public void TestArtiste(){
    Chanson test = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    Assertions.assertEquals("Artiste",test.getArtiste());
  }

  @Test
  public void TestDureeSec(){
    Chanson test = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    Assertions.assertEquals(180,test.getDureeSec());
  }
}
