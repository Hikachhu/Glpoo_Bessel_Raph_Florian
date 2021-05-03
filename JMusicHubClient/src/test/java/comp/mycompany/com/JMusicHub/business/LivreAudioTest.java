package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import org.junit.jupiter.api.*;

public class LivreAudioTest{
  @Test
  public void Test1(){
    LivreAudio test=new LivreAudio("Titre",180,1,"Auteur","Contenu.wav",1,1);
    Assertions.assertEquals("Titre=Titre Duree=3m0s ID=1 Auteur=Auteur Contenu=Contenu.wav langue=Anglais categorie=Roman",test.toString());
  }

  @Test
  public void TestContenu(){
    LivreAudio test=new LivreAudio("Titre",180,1,"Auteur","Contenu.wav",1,1);
    Assertions.assertEquals("Contenu.wav",test.getContenu());
  }

  @Test
  public void TestAuteur(){
    LivreAudio test=new LivreAudio("Titre",180,1,"Auteur","Contenu.wav",1,1);
    Assertions.assertEquals("Auteur",test.getAuteur());
  }

  @Test
  public void TestDureeSec(){
    LivreAudio test=new LivreAudio("Titre",180,1,"Auteur","Contenu.wav",1,1);
    Assertions.assertEquals(180,test.getDureeSec());
  }

  @Test
  public void TestLangue(){
    LivreAudio test=new LivreAudio("Titre",180,1,"Auteur","Contenu.wav",1,1);
    Assertions.assertEquals(1,test.getLangueNumber());
  }

  @Test
  public void TestCategorie(){
    LivreAudio test=new LivreAudio("Titre",180,1,"Auteur","Contenu.wav",1,1);
    Assertions.assertEquals(1,test.getCategorieNumber());
  }
}
