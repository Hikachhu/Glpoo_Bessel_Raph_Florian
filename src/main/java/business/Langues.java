package comp.mycompany.com.JMusicHub.business;
import comp.mycompany.com.JMusicHub.business.*;

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
