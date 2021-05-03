package comp.mycompany.com.JMusicHub.business;
import comp.mycompany.com.JMusicHub.util.*;
import comp.mycompany.com.JMusicHub.business.*;
import org.apache.log4j.Logger;


/**
 * Permet de convertir une variable en une autre selon une conversion déterminé pour l'adapter à une autre classe
 */
public class Adapter{
  /**
   * Converti une entrée pour renvoyer sa position dans la liste
   * @param  c        Parametre dont nous voulons déterminer la position
   * @param  Choix Liste des possibilités parmis lesquelles trouver c
   * @return          Renvoi la position de C dans la liste des choix
   */
  public int Conversion(char c,char ...Choix){
    final Logger logger = Logger.getLogger(Adapter.class);
    int i=1;
    logger.info("Conversion de "+c+" parmis "+Choix);
    for (char Actuel : Choix ) {
      if(c==Actuel){
        logger.info("Conversion en "+i);
        return i;
      }
      i++;
    }
    logger.info("Valeur non trouvée, renvoi de 0");
    return 0;
  }
}
