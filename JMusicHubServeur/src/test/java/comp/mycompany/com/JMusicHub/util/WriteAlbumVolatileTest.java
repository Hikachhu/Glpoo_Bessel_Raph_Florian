package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import org.junit.jupiter.api.*;

public class WriteAlbumVolatileTest{
  @Test
  public void test(){
    AlbumVolatile VolatileTest =new AlbumVolatile();
    AlbumVolatile VolatileTestLu =new AlbumVolatile();
    Album testAlbum= new Album("Titre",180,1,"Artiste",2000);
    Chanson testChanson = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    testAlbum.add(testChanson);
    VolatileTest.add(testAlbum);
    Album testAlbum1= new Album("Titre",180,1,"Artiste",2000);
    Chanson testChanson1 = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    testAlbum1.add(testChanson1);
    VolatileTest.add(testAlbum1);


    WriteAlbumVolatile    FichierAlbums   = new WriteAlbumVolatile();
    FichierAlbums.writeXML("files/AlbumTest.xml",VolatileTest);
    VolatileTestLu=FichierAlbums.readXML("files/AlbumTest.xml");
    Assertions.assertEquals(VolatileTest.toString(),VolatileTestLu.toString());
  }
}
