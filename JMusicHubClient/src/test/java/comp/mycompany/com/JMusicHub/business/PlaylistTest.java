package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import org.junit.jupiter.api.*;


import java.util.ArrayList;
import java.util.Scanner;

public class PlaylistTest{
  @Test
  public void Test1(){
    Playlist test = new Playlist("Nom",1);
    Chanson ChansonTest = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    test.add(ChansonTest);
    Assertions.assertEquals("Nom: Nom ID: 1\n\t\tTitre =Titre | Duree =3m0s | ID= 1 | Artiste = Artiste | Contenu = Contenu.wav | genre = Classique\n",test.toString());
  }

  @Test
  public void TestTitre(){
    Playlist test = new Playlist("Nom",1);
    Chanson ChansonTest = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    test.add(ChansonTest);
    Assertions.assertEquals("Nom",test.getTitre());
  }

  @Test
  public void TestID(){
    Playlist test = new Playlist("Nom",1);
    Chanson ChansonTest = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    test.add(ChansonTest);
    Assertions.assertEquals(1,test.getID());
  }

  @Test
  public void TestTitreChanson(){
    Playlist test = new Playlist("Nom",1);
    Chanson ChansonTest = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    test.add(ChansonTest);
    Assertions.assertEquals("Titre",test.get(0).getTitre());
  }

  @Test
  public void TestContenuChanson(){
    Playlist test = new Playlist("Nom",1);
    Stockage ChansonTest = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    test.add(ChansonTest);
    Assertions.assertEquals("Contenu.wav",test.get(0).getContenu());
  }

  @Test
  public void TestArrayList(){
    ArrayList<Stockage> Ensemble = new ArrayList<Stockage>();
    Playlist test = new Playlist("Nom",1);
    Stockage ChansonTest = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    test.add(ChansonTest);
    Ensemble.add(ChansonTest);
    Assertions.assertEquals(Ensemble,test.getEnsemble());
  }

  @Test
  public void TestTri(){
    Playlist test = new Playlist("Nom",1);
    Stockage ChansonTest = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    test.add(ChansonTest);
    Assertions.assertEquals("Nom: Nom ID: 1\n\t\tTitre =Titre | Duree =3m0s | ID= 1 | Artiste = Artiste | Contenu = Contenu.wav | genre = Classique\n",test.Tri(0));
  }

  @Test
  public void TestAffichage(){
    Playlist test = new Playlist("Nom",1);
    Stockage ChansonTest = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    test.add(ChansonTest);
    Assertions.assertEquals("Titre =Titre | Duree =3m0s | ID= 1 | Artiste = Artiste | Contenu = Contenu.wav | genre = Classique",test.Affiche());
  }

}
