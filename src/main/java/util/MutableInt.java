package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

public class MutableInt {
    private int value;
    private int maxValue;

    MutableInt(int value,int maxValue){
      this.value=value;
      this.maxValue=maxValue;
    }

    public void setValue(int value) {
      this.value=value;
    }
    public int getValue() {
      return value;
    }

    public void setMax(){
      this.value=this.maxValue;
    }

    public int getMax(){
      return maxValue;
    }
}
