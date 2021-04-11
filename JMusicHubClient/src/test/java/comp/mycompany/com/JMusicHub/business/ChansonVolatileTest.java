package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import org.junit.jupiter.api.*;

public class ChansonVolatileTest{
  @Test
  public void Test1(){
    Chanson ChansonTest = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    ChansonVolatile VolatileTest = new ChansonVolatile();
    VolatileTest.add(ChansonTest);
    Assertions.assertEquals("\tTitre =Titre | Duree =3m0s | ID= 1 | Artiste = Artiste | Contenu = Contenu.wav | genre = Classique\n",VolatileTest.toString());

  }
  public void Test2(){
    Chanson ChansonTest = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    ChansonVolatile VolatileTest = new ChansonVolatile();
    VolatileTest.add(ChansonTest);
    Assertions.assertEquals("Titre =Titre | Duree =3m0s | ID= 1 | Artiste = Artiste | Contenu = Contenu.wav | genre = Classique",VolatileTest.get(0).toString());

  }
}
