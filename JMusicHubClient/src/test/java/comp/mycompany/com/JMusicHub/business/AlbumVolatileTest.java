package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Scanner;

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
    Assertions.assertEquals("Numero :0 | Album: Titre ID: 1 Artiste: Artiste DateSortie: 2000 Duree: 3m0s\n\t\tTitre =Titre | Duree =3m0s | ID= 1 | Artiste = Artiste | Contenu = Contenu.wav | genre = Classique\n\nNumero :1 | Album: Titre ID: 1 Artiste: Artiste DateSortie: 2000 Duree: 3m0s\n\t\tTitre =Titre | Duree =3m0s | ID= 1 | Artiste = Artiste | Contenu = Contenu.wav | genre = Classique\n\n",VolatileTest.toString());
  }
    @Test
    public void Test2(){
      AlbumVolatile VolatileTest =new AlbumVolatile();
      Album testAlbum= new Album("Titre",180,1,"Artiste",2000);
      Chanson testChanson = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
      testAlbum.add(testChanson);
      Chanson testChanson2 = new Chanson("Titre2",180,1,"Artiste2","Contenu2.wav",1);
      testAlbum.add(testChanson2);
      VolatileTest.add(testAlbum);
      Assertions.assertEquals("Numero :0 | Album: Titre ID: 1 Artiste: Artiste DateSortie: 2000 Duree: 3m0s\n\t\tTitre =Titre | Duree =3m0s | ID= 1 | Artiste = Artiste | Contenu = Contenu.wav | genre = Classique\n\t\tTitre =Titre2 | Duree =3m0s | ID= 1 | Artiste = Artiste2 | Contenu = Contenu2.wav | genre = Classique\n\n",VolatileTest.toString());
    }

    @Test
    public void TestEnsemble(){
      ArrayList<StockageVolatile> Ensemble = new ArrayList<StockageVolatile>();
      StockageMaster testVolatile=new AlbumVolatile();
      Album testAlbum= new Album("Titre",180,1,"Artiste",2000);
      Stockage testChanson = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
      testAlbum.add(testChanson);
      testVolatile.add(testAlbum);
      Ensemble.add(testAlbum);
      Assertions.assertEquals(Ensemble.toString(),testVolatile.getEnsemble().toString());
    }

    @Test
    public void TestTri1(){
      AlbumVolatile VolatileTest =new AlbumVolatile();
      Album testAlbum= new Album("Titre",180,1,"Artiste",2000);
      Chanson testChanson = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
      testAlbum.add(testChanson);
      VolatileTest.add(testAlbum);
      Assertions.assertEquals("\tAlbum: Titre ID: 1 Artiste: Artiste DateSortie: 2000 Duree: 3m0s\n\t\tTitre =Titre | Duree =3m0s | ID= 1 | Artiste = Artiste | Contenu = Contenu.wav | genre = Classique\n\n",VolatileTest.Tri(0));
    }

    @Test
    public void TestTri2(){
      AlbumVolatile VolatileTest =new AlbumVolatile();
      Assertions.assertEquals("ERROR",VolatileTest.Tri(-1));
    }

    @Test
    public void Testget(){
      AlbumVolatile VolatileTest =new AlbumVolatile();
      Album testAlbum= new Album("Titre",180,1,"Artiste",2000);
      Chanson testChanson = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
      testAlbum.add(testChanson);
      VolatileTest.add(testAlbum);
      Assertions.assertEquals(testAlbum.toString(),VolatileTest.get(0).toString());
    }

    @Test
    public void TestAdd2(){
      AlbumVolatile VolatileTest =new AlbumVolatile();
      Album testAlbum= new Album("Titre",180,1,"Artiste",2000);
      VolatileTest.add(testAlbum);
      Chanson testChanson = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
      VolatileTest.add(0,testChanson);
      Assertions.assertEquals("Numero :0 | Album: Titre ID: 1 Artiste: Artiste DateSortie: 2000 Duree: 3m0s\n\t\tTitre =Titre | Duree =3m0s | ID= 1 | Artiste = Artiste | Contenu = Contenu.wav | genre = Classique\n\n",VolatileTest.toString());
    }

}
