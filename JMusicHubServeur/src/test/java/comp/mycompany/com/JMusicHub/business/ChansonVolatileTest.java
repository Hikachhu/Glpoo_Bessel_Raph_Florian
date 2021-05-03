package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Scanner;

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

    @Test
    public void TestEnsemble(){
      ArrayList<Stockage> Ensemble = new ArrayList<Stockage>();
      StockageVolatile testVolatile=new ChansonVolatile();
      Stockage test =new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
      testVolatile.add(test);
      Ensemble.add(test);
      Assertions.assertEquals(Ensemble.toString(),testVolatile.getEnsemble().toString());
    }

    @Test
    public void TestTri1(){
      StockageVolatile testVolatile=new ChansonVolatile();
      LivreAudio test=new LivreAudio("Titre",180,1,"Auteur","Contenu.wav",1,1);
      testVolatile.add(test);
      Assertions.assertEquals("Titre=Titre Duree=3m0s ID=1 Auteur=Auteur Contenu=Contenu.wav langue=Anglais categorie=Roman",testVolatile.Tri(0));
    }

    @Test
    public void TestTri2(){
      StockageVolatile testVolatile=new ChansonVolatile();
      Stockage test =new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
      testVolatile.add(test);
      Assertions.assertEquals("ERROR",testVolatile.Tri(-1));
    }

    @Test
    public void Testget(){
      StockageVolatile testVolatile=new ChansonVolatile();
      Stockage test =new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
      testVolatile.add(test);
      Assertions.assertEquals(test.toString(),testVolatile.get(0).toString());
    }
}
