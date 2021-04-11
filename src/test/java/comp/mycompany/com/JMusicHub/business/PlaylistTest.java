package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import org.junit.jupiter.api.*;

public class PlaylistTest{
  @Test
  public void Test1(){
    Playlist test = new Playlist("Nom",1);
    Chanson ChansonTest = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    test.add(ChansonTest);
    Assertions.assertEquals("Nom: Nom ID: 1\n\t\tTitre =Titre | Duree =3m0s | ID= 1 | Artiste = Artiste | Contenu = Contenu.wav | genre = Classique\n",test.toString());
  }
}
