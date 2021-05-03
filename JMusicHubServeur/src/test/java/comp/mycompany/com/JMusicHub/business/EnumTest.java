package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Scanner;

public class EnumTest{
  @Test
  public void TestGenre(){
    Assertions.assertEquals("0 Jazz\n1 Classique\n2 HipHop\n3 Rock\n4 Pop\n5 Rap\n6 INCONNU\n",Genre.allGenre());
  }
  @Test
  public void TestLangue(){
    Assertions.assertEquals("0 Francais\n1 Anglais\n2 Italien\n3 Espagnol\n4 INCONNU\n",Langues.allLangues());
  }
  @Test
  public void TestCategorie(){
    Assertions.assertEquals("0 Jeunesse\n1 Roman\n2 Theatre\n3 Discours\n4 Documentaire\n5 INCONNU\n",Categorie.allCategorie());
  }
}
