package comp.mycompany.com.JMusicHub.util;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import org.apache.log4j.Logger;

/**
 * Classe permettant de faire tampon sur la manipulation d'un int
 */
public class MutableInt implements Mutable{
    private static MutableInt instance=null;
    final static Logger logger = Logger.getLogger(MutableInt.class);

      private static int value=0;
      private static int maxValue=0;

    public static MutableInt getInstance(){
      if(instance==null){
        instance=new MutableInt(0,0);
      }
      return instance;
    }

    private MutableInt(int value,int maxValue){
      this.value=value;
      this.maxValue=maxValue;
    }

    /**
     * Modifie la valeur de la variable value
     * @param value valeur à donner à value
     */
    public void setValue(int value) {
      this.value=value;
      logger.info("Changement de valeur à:"+value);
    }

    /**
     * Accesseur de value
     * @return Value
     */
    public int getValue() {
      return value;
    }

    /**
     * Met value à la valeur max
     */
    public void setMax(){
      this.value=this.maxValue;
    }

    public int getMax(){
      return maxValue;
    }
}
