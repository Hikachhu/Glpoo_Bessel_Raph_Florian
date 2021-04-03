package comp.mycompany.com.JMusicHub.business;
import org.apache.log4j.Logger;
import comp.mycompany.com.JMusicHub.business.*;

public enum Genre{
  Jazz("Jazz"),  Classique("Classique"),  HipHop("Hip-Hop"),  Rock("Rock"),  Pop("Pop"),  Rap("Rap");
  private String Name;

  private Genre(String Name){
    this.Name=Name;
  }

  public static String allGenre(){
    String s="";
    for (Genre a : Genre.values()) {
      s+=a.ordinal()+" "+a+"\n";
    }
    return s;
  }

}
