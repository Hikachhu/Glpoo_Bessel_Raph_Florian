package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import org.junit.jupiter.api.*;

public class PlaylistVolatileTest{
  @Test
  public void Test1(){
    PlaylistVolatile VolatileTest = new PlaylistVolatile();
    StockageVolatile test1 = new Playlist("Nom",1);
    Chanson ChansonTest1 = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    test1.add(ChansonTest1);
    VolatileTest.add(test1);
    StockageVolatile test2 = new Playlist("Nom2",2);
    Chanson ChansonTest2 = new Chanson("Titre2",180,1,"Artiste2","Contenu2.wav",1);
    test2.add(ChansonTest2);
    VolatileTest.add(test2);
    Assertions.assertEquals("Nom: Nom ID: 1\n\t\tTitre =Titre | Duree =3m0s | ID= 1 | Artiste = Artiste | Contenu = Contenu.wav | genre = Classique\n\nNom: Nom2 ID: 2\n\t\tTitre =Titre2 | Duree =3m0s | ID= 1 | Artiste = Artiste2 | Contenu = Contenu2.wav | genre = Classique\n\n",VolatileTest.toString());
  }
}
