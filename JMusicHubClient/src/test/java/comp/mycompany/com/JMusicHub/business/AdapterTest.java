package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import org.junit.jupiter.api.*;

public class AdapterTest{

  @Test
  public void Test1(){
    Adapter adapter = new Adapter();
    Assertions.assertEquals(2,adapter.Conversion('a','c','a'));
  }

  @Test
  public void Test2(){
    Adapter adapter = new Adapter();
    Assertions.assertEquals(0,adapter.Conversion('a'));
  }

  @Test
  public void Test3(){
    Adapter adapter = new Adapter();
    Assertions.assertEquals(1,adapter.Conversion('a','a','a'));
  }
}
