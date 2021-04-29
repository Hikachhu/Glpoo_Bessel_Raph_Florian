package comp.mycompany.com.JMusicHub.business;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;

public class Adapter{
  public int Conversion(char c,char ...Choix){
    int i=1;
    for (char Actuel : Choix ) {
      if(c==Actuel){
        return i;
      }
      i++;
    }
    return 0;
  }
}
