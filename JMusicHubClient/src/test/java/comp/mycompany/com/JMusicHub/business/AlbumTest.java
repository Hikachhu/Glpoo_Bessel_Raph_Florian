package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Scanner;

public class AlbumTest{

  @Test
  public void Test1(){
    Album test= new Album("Titre",180,1,"Artiste",2000);
    Assertions.assertEquals("Album: Titre ID: 1 Artiste: Artiste DateSortie: 2000 Duree: 3m0s\n",test.toString());
  }

  @Test
  public void TestTitre(){
    Album testAlbum= new Album("Titre",180,1,"Artiste",2000);
    Stockage testChanson = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    testAlbum.add(testChanson);
    Assertions.assertEquals("Titre",testAlbum.getTitre());
  }

  @Test
  public void TestID(){
    Album testAlbum= new Album("Titre",180,1,"Artiste",2000);
    Stockage testChanson = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    testAlbum.add(testChanson);
    Assertions.assertEquals(1,testAlbum.getID());
  }

  @Test
  public void TestDureeSec(){
    Album testAlbum= new Album("Titre",180,1,"Artiste",2000);
    Stockage testChanson = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    testAlbum.add(testChanson);
    Assertions.assertEquals(180,testAlbum.getDureeSec());
  }

  @Test
  public void TestDureeMin(){
    Album testAlbum= new Album("Titre",180,1,"Artiste",2000);
    Stockage testChanson = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    testAlbum.add(testChanson);
    Assertions.assertEquals("3m0s",testAlbum.getDureeMin());
  }

  @Test
  public void TestArtiste(){
    Album testAlbum= new Album("Titre",180,1,"Artiste",2000);
    Stockage testChanson = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    testAlbum.add(testChanson);
    Assertions.assertEquals("Artiste",testAlbum.getArtiste());
  }

  @Test
  public void TestDateSortie(){
    Album testAlbum= new Album("Titre",180,1,"Artiste",2000);
    Stockage testChanson = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    testAlbum.add(testChanson);
    Assertions.assertEquals(2000,testAlbum.getDateSortie());
  }

  @Test
  public void Testget(){
    Album testAlbum= new Album("Titre",180,1,"Artiste",2000);
    Stockage testChanson = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    testAlbum.add(testChanson);
    Assertions.assertEquals(testChanson.toString(),testAlbum.get(0).toString());
  }

  @Test
  public void TestArrayList(){
    ArrayList<Stockage> Ensemble = new ArrayList<Stockage>();
    Album testAlbum= new Album("Titre",180,1,"Artiste",2000);
    Stockage testChanson = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    testAlbum.add(testChanson);
    Ensemble.add(testChanson);
    Assertions.assertEquals(Ensemble,testAlbum.getEnsemble());
  }

  @Test
  public void TestTriAlbum(){
    ArrayList<Chanson> Ensemble = new ArrayList<Chanson>();
    Album testAlbum= new Album("Titre",180,1,"Artiste",2000);
    Chanson testChanson = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    testAlbum.add(testChanson);
    Ensemble.add(testChanson);
    Assertions.assertEquals(Ensemble,testAlbum.TriAlbum());
  }

  @Test
  public void TestTri(){
    ArrayList<Chanson> Ensemble = new ArrayList<Chanson>();
    Album testAlbum= new Album("Titre",180,1,"Artiste",2000);
    Chanson testChanson = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    testAlbum.add(testChanson);
    Ensemble.add(testChanson);
    Assertions.assertEquals(Ensemble.toString(),testAlbum.Tri(0));
  }

  @Test
  public void TestTriERROR(){
    ArrayList<Chanson> Ensemble = new ArrayList<Chanson>();
    Album testAlbum= new Album("Titre",180,1,"Artiste",2000);
    Assertions.assertEquals("ERROR",testAlbum.Tri(-1));
  }

}
