package musichub.business;
import musichub.business.*;

public enum Categorie{
  Jeunesse,Roman,Theatre,Discours,Documentaire;
  public static String allCategorie(){
    String s="";
    for (Categorie a : Categorie.values()) {
      s+=a.ordinal()+" "+a+"\n";
    }
    return s;
  }
}
