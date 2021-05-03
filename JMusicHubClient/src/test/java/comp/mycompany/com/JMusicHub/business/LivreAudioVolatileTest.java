package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Scanner;

public class LivreAudioVolatileTest{

  @Test
  public void Test1(){
    LivreAudioVolatile testVolatile=new LivreAudioVolatile();
    LivreAudio test=new LivreAudio("Titre",180,1,"Auteur","Contenu.wav",1,1);
    testVolatile.add(test);
    Assertions.assertEquals("Titre=Titre Duree=3m0s ID=1 Auteur=Auteur Contenu=Contenu.wav langue=Anglais categorie=Roman\n",testVolatile.toString());
  }

  @Test
  public void TestEnsemble(){
    ArrayList<Stockage> Ensemble = new ArrayList<Stockage>();
    StockageVolatile testVolatile=new LivreAudioVolatile();
    Stockage test=new LivreAudio("Titre",180,1,"Auteur","Contenu.wav",1,1);
    testVolatile.add(test);
    Ensemble.add(test);
    Assertions.assertEquals(Ensemble.toString(),testVolatile.getEnsemble().toString());
  }

  @Test
  public void TestTri1(){
    LivreAudioVolatile testVolatile=new LivreAudioVolatile();
    LivreAudio test=new LivreAudio("Titre",180,1,"Auteur","Contenu.wav",1,1);
    testVolatile.add(test);
    Assertions.assertEquals("\tTitre=Titre Duree=3m0s ID=1 Auteur=Auteur Contenu=Contenu.wav langue=Anglais categorie=Roman\n",testVolatile.Tri(0));
  }

  @Test
  public void TestTri2(){
    LivreAudioVolatile testVolatile=new LivreAudioVolatile();
    LivreAudio test=new LivreAudio("Titre",180,1,"Auteur","Contenu.wav",1,1);
    testVolatile.add(test);
    Assertions.assertEquals("ERROR",testVolatile.Tri(-1));
  }

  @Test
  public void Testget(){
    LivreAudioVolatile testVolatile=new LivreAudioVolatile();
    Stockage test=new LivreAudio("Titre",180,1,"Auteur","Contenu.wav",1,1);
    testVolatile.add(test);
    Assertions.assertEquals(test.toString(),testVolatile.get(0).toString());
  }
}
