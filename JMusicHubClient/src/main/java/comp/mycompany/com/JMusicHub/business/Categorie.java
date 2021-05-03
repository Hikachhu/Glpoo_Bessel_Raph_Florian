package comp.mycompany.com.JMusicHub.business;
import org.apache.log4j.Logger;
import comp.mycompany.com.JMusicHub.business.*;

public enum Categorie{
  Jeunesse,Roman,Theatre,Discours,Documentaire,INCONNU;
  public static String allCategorie(){
    String s="";
    for (Categorie a : Categorie.values()) {
      s+=(a.ordinal()+" "+a+"\n");
    }
    return s;
  }
}
