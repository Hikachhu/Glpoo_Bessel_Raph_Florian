package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import org.junit.jupiter.api.*;

public class AlbumVolatileTest{
  @Test
  public void Test1(){
    AlbumVolatile VolatileTest =new AlbumVolatile();
    Album testAlbum= new Album("Titre",180,1,"Artiste",2000);
    Chanson testChanson = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    testAlbum.add(testChanson);
    VolatileTest.add(testAlbum);
    Album testAlbum1= new Album("Titre",180,1,"Artiste",2000);
    Chanson testChanson1 = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    testAlbum1.add(testChanson1);
    VolatileTest.add(testAlbum1);
    Assertions.assertEquals("Album: Titre ID: 1 Artiste: Artiste DateSortie: 2000 Duree: 3m0s\n\t\tTitre =Titre | Duree =3m0s | ID= 1 | Artiste = Artiste | Contenu = Contenu.wav | genre = Classique\n\nAlbum: Titre ID: 1 Artiste: Artiste DateSortie: 2000 Duree: 3m0s\n\t\tTitre =Titre | Duree =3m0s | ID= 1 | Artiste = Artiste | Contenu = Contenu.wav | genre = Classique\n\n",VolatileTest.toString());
  }
}
