package musichub.business;
import musichub.business.*;

public enum Langues{
  Francais,Anglais,Italien,Espagnol;

  public static String allLangues(){
    String s="";
    for (Langues a : Langues.values()) {
      s+=a.ordinal()+" "+a+"\n";
    }
    return s;
  }

}
