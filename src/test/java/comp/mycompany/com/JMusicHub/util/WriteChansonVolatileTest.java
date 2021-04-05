package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import org.junit.jupiter.api.*;

public class WriteChansonVolatileTest{
  
  @Test
  public void test(){
    Chanson ChansonTest = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    ChansonVolatile VolatileTestLu =new ChansonVolatile();
    ChansonVolatile VolatileTest = new ChansonVolatile();
    VolatileTest.add(ChansonTest);
    WriteChansonVolatile    FichierChansons   = new WriteChansonVolatile();
    FichierChansons.writeXML("files/ElementTest.xml",VolatileTest);
    VolatileTestLu=FichierChansons.readXML("files/ElementTest.xml");
    Assertions.assertEquals(VolatileTest.toString(),VolatileTestLu.toString());
  }
}
