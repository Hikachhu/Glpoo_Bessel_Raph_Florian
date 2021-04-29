package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import org.junit.jupiter.api.*;

public class MutableIntTest{
  @Test
  public void test1(){
    MutableInt test = MutableInt.getInstance();
    test.setValue(2);
    Assertions.assertEquals(2,test.getValue());
  }

  @Test
  public void test2(){
    MutableInt test = MutableInt.getInstance();
    test.setValue(3);
    Assertions.assertEquals(3,test.getValue());
  }
}
