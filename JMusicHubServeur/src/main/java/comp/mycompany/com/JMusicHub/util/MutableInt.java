package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import org.apache.log4j.Logger;

public class MutableInt {
    final static Logger logger = Logger.getLogger(MutableInt.class);
    private int value;
    private int maxValue;

    public MutableInt(int value,int maxValue){
      this.value=value;
      this.maxValue=maxValue;
    }

    public void setValue(int value) {
      this.value=value;
      logger.info("Changement de valeur à:"+value);
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
