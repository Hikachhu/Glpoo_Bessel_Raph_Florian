package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import org.junit.jupiter.api.*;

public class AlbumVolatileTest{

  @Test
  public void Test(){
    AlbumVolatile VolatileTest = new AlbumVolatile();
    Assertions.assertEquals("",VolatileTest.toString());
  }

  @Test
  public void Test1(){
    AlbumVolatile VolatileTest = new AlbumVolatile();
      Album testAlbum= new Album("TitreAlbum1",180,1,"Artiste",2000);
        Chanson testChanson = new Chanson("Titre1",180,1,"Artiste","Contenu.wav",1);
        testAlbum.add(testChanson);
      VolatileTest.add(testAlbum);
    Assertions.assertEquals("Album: TitreAlbum1 ID: 1 Artiste: Artiste DateSortie: 2000 Duree: 3m0s\n\t\tTitre =Titre1 | Duree =3m0s | ID= 1 | Artiste = Artiste | Contenu = Contenu.wav | genre = Classique\n\n",VolatileTest.toString());
  }

  @Test
  public void Test2(){
    StockageMaster VolatileTest =new AlbumVolatile();
      StockageVolatile testAlbum= new Album("TitreAlbum1",180,1,"Artiste",2000);
        Chanson testChanson = new Chanson("Titre1",180,1,"Artiste","Contenu.wav",1);
        testAlbum.add(testChanson);
    VolatileTest.add(testAlbum);
      StockageVolatile testAlbum1= new Album("Titre",180,1,"Artiste",2000);
        Chanson testChanson1 = new Chanson("Titre2",180,1,"Artiste","Contenu.wav",1);
        testAlbum1.add(testChanson1);
    VolatileTest.add(testAlbum1);
    Assertions.assertEquals("Album: TitreAlbum1 ID: 1 Artiste: Artiste DateSortie: 2000 Duree: 3m0s\n\t\tTitre =Titre1 | Duree =3m0s | ID= 1 | Artiste = Artiste | Contenu = Contenu.wav | genre = Classique\n\nAlbum: Titre ID: 1 Artiste: Artiste DateSortie: 2000 Duree: 3m0s\n\t\tTitre =Titre2 | Duree =3m0s | ID= 1 | Artiste = Artiste | Contenu = Contenu.wav | genre = Classique\n\n",VolatileTest.toString());
  }
}
