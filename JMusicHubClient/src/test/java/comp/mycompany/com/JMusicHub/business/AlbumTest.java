package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import org.junit.jupiter.api.*;

public class AlbumTest{

  @Test
  public void Test1(){
    Album test= new Album("Titre",180,1,"Artiste",2000);
    Assertions.assertEquals("Album: Titre ID: 1 Artiste: Artiste DateSortie: 2000 Duree: 3m0s\n",test.toString());
  }

  @Test
  public void Test2(){
    Album testAlbum= new Album("Titre",180,1,"Artiste",2000);
    Stockage testChanson = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    testAlbum.add(testChanson);
    Assertions.assertEquals("Album: Titre ID: 1 Artiste: Artiste DateSortie: 2000 Duree: 3m0s\n\t\tTitre =Titre | Duree =3m0s | ID= 1 | Artiste = Artiste | Contenu = Contenu.wav | genre = Classique\n",testAlbum.toString());
  }

}
