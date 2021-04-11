package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import org.junit.jupiter.api.*;

public class WriteLivreAudioVolatileTest{
  @Test
  public void test(){
    LivreAudioVolatile testVolatile=new LivreAudioVolatile();
    LivreAudioVolatile testVolatilelu=new LivreAudioVolatile();
    LivreAudio test=new LivreAudio("Titre",180,1,"Auteur","Contenu.wav",1,1);
    testVolatile.add(test);
    WriteLivreAudioVolatile    FichierLivreAudio   = new WriteLivreAudioVolatile();
    FichierLivreAudio.writeXML("files/ElementTest.xml",testVolatile);
    testVolatilelu=FichierLivreAudio.readXML("files/ElementTest.xml");
    Assertions.assertEquals(testVolatile.toString(),testVolatilelu.toString());
  }
}
