package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import org.junit.jupiter.api.*;

public class WritePlaylistVolatileTest{
  @Test
  public void test(){

    PlaylistVolatile VolatileTest =new PlaylistVolatile();
    PlaylistVolatile VolatileTestLu =new PlaylistVolatile();
    Playlist testPlaylist = new Playlist("Nom",1);
    Chanson testChanson = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    LivreAudio test=new LivreAudio("Titre",180,1,"Auteur","Contenu.wav",1,1);
    testPlaylist.add(testChanson);
    testPlaylist.add(test);
    VolatileTest.add(testPlaylist);
    Playlist testPlaylist1 = new Playlist("Nom",2);
    Chanson testChanson1 = new Chanson("Titre",180,1,"Artiste","Contenu.wav",1);
    testPlaylist1.add(testChanson1);
    VolatileTest.add(testPlaylist1);


    WritePlaylistVolatile    FichierPlaylist   = new WritePlaylistVolatile();
    FichierPlaylist.writeXML("files/PlaylistTest.xml",VolatileTest);
    VolatileTestLu=FichierPlaylist.readXML("files/PlaylistTest.xml");
    Assertions.assertEquals(VolatileTest.toString(),VolatileTestLu.toString());

  }
}
